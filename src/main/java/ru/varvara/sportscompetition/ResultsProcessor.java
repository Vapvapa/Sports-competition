package ru.varvara.sportscompetition;

public interface ResultsProcessor {
    void printFastestRunners(int distanceKm, int N);
    void printFastestRunners(String gender, int N);
}
