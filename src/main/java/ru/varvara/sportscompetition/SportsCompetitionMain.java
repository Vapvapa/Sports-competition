package ru.varvara.sportscompetition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SportsCompetitionMain {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(SportsCompetitionConfig.class);

        ResultsProcessorBean resultsProcessor = context.getBean("resultsProcessor", ResultsProcessorBean.class);

        //resultsProcessor.printRunners();

        //resultsProcessor.printFastestRunners(10, 100);
        resultsProcessor.printFastestRunners("М", 3);
    }
}
