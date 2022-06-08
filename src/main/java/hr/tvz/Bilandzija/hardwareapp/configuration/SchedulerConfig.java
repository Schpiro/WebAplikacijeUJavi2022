package hr.tvz.Bilandzija.hardwareapp.configuration;

import hr.tvz.Bilandzija.hardwareapp.scheduler.HardwareJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail hardwareJobDetail() {
        return
                JobBuilder.newJob(HardwareJob.class).withIdentity("hardwareJob").storeDurably().build();
    }

    @Bean
    public Trigger hardwareJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10).repeatForever();
        return TriggerBuilder.newTrigger().forJob(hardwareJobDetail())
                .withIdentity("hardwareTrigger").withSchedule(scheduleBuilder).build();
    }
}
