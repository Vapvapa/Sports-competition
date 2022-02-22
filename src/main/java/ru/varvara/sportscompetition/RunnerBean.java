package ru.varvara.sportscompetition;

import java.util.Objects;

public class RunnerBean implements Runner, Comparable<RunnerBean> {
    private String surname;
    private String name;
    private String gender;
    private int distanceKm;
    private String time;

    @Override
    public void setRunnerBean(String line) throws ExceptionInInitializerError {
        line = line.replaceAll(",","");
        String[] components = line.split(" ");

        if (components.length != 6){
            throw new ExceptionInInitializerError("Неправильные значения в файле, RunnerBean не может быть создан! " +
                    "Образец: Иван Иванов, М, 10 км, 55:20");
        }
        this.surname = components[0];
        this.name = components[1];
        this.gender = components[2];
        this.distanceKm = Integer.parseInt(components[3]);
        this.time = components[5]; // components[4] == "км"
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getDistanceKm() {
        return distanceKm;
    }

    public String getTime() {
        return time;
    }

    public long getSpeedMetreMin() {
        String secTime = time == null || time.length() < 2 ?
                time : time.substring(time.length() - 2);

        String minTime = time == null || time.length() < 4 ?
                "0" : time.substring(time.length() - 5, time.length() - 3);

        String hoursTime = time == null || time.length() < 6 ?
                "0" : time.substring(0, time.length() - 6);

        long min = Long.parseLong(secTime) / 60 + Long.parseLong(minTime) + Long.parseLong(hoursTime) * 60;

        return ((long) distanceKm * 1000 / min);
    }

    @Override
    public String toString() {
        return  surname + " " + name + ", " + gender +
                ", distance = " + distanceKm + " km" + ", time = " + time +
                ", speed = " + String.valueOf(this.getSpeedMetreMin()) + " metre/min";
    }

    @Override
    public boolean equals(Runner o) {
        if (this.getSpeedMetreMin() == o.getSpeedMetreMin()) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunnerBean that = (RunnerBean) o;
        return RunnerBean.equals(this.getSpeedMetreMin(), that.getSpeedMetreMin());
    }

    private static boolean equals(long speedMetreMin, long speedMetreMin1) {
        return speedMetreMin >= speedMetreMin1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

    @Override
    public int compareTo(RunnerBean o) {
        return (int) (this.getSpeedMetreMin() - o.getSpeedMetreMin());
    }
}
