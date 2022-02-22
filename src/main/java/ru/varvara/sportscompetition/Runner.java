package ru.varvara.sportscompetition;

public interface Runner {
    void setRunnerBean(String line) throws ExceptionInInitializerError;

    String getSurname();
    String getName();
    String getGender();
    int getDistanceKm();
    String getTime();

    long getSpeedMetreMin();

    boolean equals(Runner runner);
}
