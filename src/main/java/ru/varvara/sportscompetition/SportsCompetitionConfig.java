package ru.varvara.sportscompetition;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("ru.varvara.sportscompetition")
public class SportsCompetitionConfig {
    @Bean
    @Scope("prototype")
    public Runner runnerPrototype() {
        return new RunnerBean();
    }
}
