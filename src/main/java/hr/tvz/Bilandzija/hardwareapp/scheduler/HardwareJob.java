package hr.tvz.Bilandzija.hardwareapp.scheduler;

import hr.tvz.Bilandzija.hardwareapp.configuration.SecurityConfiguration;
import hr.tvz.Bilandzija.hardwareapp.model.dto.HardwareDTO;
import hr.tvz.Bilandzija.hardwareapp.repository.interfaces.HardwareRepository;
import hr.tvz.Bilandzija.hardwareapp.service.interfaces.HardwareService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.stream.Collectors;

public class HardwareJob extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private HardwareRepository hardwareRepository;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("\nOvo su trenutno dostupni hardwarei"+
                "\n-----------------------------------\n"+
                hardwareRepository.findAll().stream()
                                    .map(hardware -> hardware.getName() + " - " + hardware.getAvailableStock())
                                    .collect(Collectors.toList())+
                "\n-----------------------------------");

    }
}
