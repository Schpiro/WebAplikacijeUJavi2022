package hr.tvz.Bilandzija.hardwareapp.repository;

import hr.tvz.Bilandzija.hardwareapp.model.enums.TypeOfHardware;
import hr.tvz.Bilandzija.hardwareapp.model.pojo.Hardware;
import hr.tvz.Bilandzija.hardwareapp.repository.interfaces.HardwareRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository {
    private static final String SELECT_ALL =
            "Select name, code, price, type_of_hardware, available_stock from hardware";


    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcHardwareRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("code");
    }


    @Override
    public Set<Hardware> findAll() {
        return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(Integer code) {
        try{
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void delete(Integer code) {
        jdbc.update("DELETE FROM hardware WHERE code = ?", code);
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try {
            hardware.setCode(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> update(Integer code, Hardware updatedHardware) {
        int executed = jdbc.update("UPDATE hardware set " +
                        "name = ?, " +
                        "price = ?, " +
                        "type_of_hardware = ?, " +
                        "available_stock = ? " +
                        "WHERE code = ?",
                updatedHardware.getName(),
                updatedHardware.getCode(),
                updatedHardware.getPrice(),
                updatedHardware.getType(),
                updatedHardware.getAvailableStock()
        );

        if(executed > 0){
            return Optional.of(updatedHardware);
        } else {
            return Optional.empty();
        }
    }

    private Hardware mapRowToHardware(ResultSet resultSet, int i) throws SQLException {
        return new Hardware(
                resultSet.getString("name"),
                resultSet.getInt("code"),
                resultSet.getInt("price"),
                TypeOfHardware.valueOf(resultSet.getString("type_of_hardware")),
                resultSet.getInt("available_stock")
        );
    }

    private Integer saveHardwareDetails(Hardware hardware) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", hardware.getName());
        values.put("code", hardware.getCode());
        values.put("price", hardware.getPrice());
        values.put("type_of_hardware", hardware.getType());
        values.put("available_stock", hardware.getAvailableStock());

        return inserter.executeAndReturnKey(values).intValue();
    }
}
