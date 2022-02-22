package ru.varvara.sportscompetition;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.*;

@Service("resultsProcessor")
public class ResultsProcessorBean implements ResultsProcessor {

    private List<Runner> runnerBeans = new ArrayList<>();

    public ResultsProcessorBean() {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getFileFromResources("data.csv")))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            Runner runner = (RunnerBean) ApplicationContextHolder.getApplicationContext().getBean("runnerPrototype");
            runner.setRunnerBean(line);
            runnerBeans.add(runner);
        }
    }

    public ResultsProcessorBean(List<Runner> runnerBeans) {
        this.runnerBeans = runnerBeans;
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    @Override
    public void printFastestRunners(int distanceKm, int N) {
        List<Runner> res = new ArrayList<>();

        for (Runner runner : runnerBeans) {
            if (runner.getDistanceKm() == distanceKm) {
                res.add(runner);
            }
        }

        Collections.sort(res, (r1, r2) -> {
            return (int) (r2.getSpeedMetreMin() - r1.getSpeedMetreMin());});

        for(int i = 0; i < N && i < res.size(); i++){
            System.out.println(res.get(i).toString());
        }
    }

    @Override
    public void printFastestRunners(String gender, int N) {
        List<Runner> res = new ArrayList<>();

        for (Runner runner : runnerBeans) {
            if (runner.getGender().equalsIgnoreCase(gender)) {
                res.add(runner);
            }
        }

        Collections.sort(res, (r1, r2) -> {
            return (int) (r2.getSpeedMetreMin() - r1.getSpeedMetreMin());});

        for(int i = 0; i < N && i < res.size(); i++){
            System.out.println(res.get(i).toString());
        }
    }

    public void printRunners(){
        for (Runner runner : runnerBeans) {
            System.out.println(runner);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(runnerBeans);
    }
}
