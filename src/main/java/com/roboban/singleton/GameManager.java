package com.roboban.singleton;

public class GameManager {
    private static GameManager instance;

    private int currentLevel;
    private int score; // Totalul punctelor (ex. numărul total de mișcări)
    private int movesCount; // Mișcările per nivel curent

    private GameManager() {
        currentLevel = 1;
        score = 0;
        movesCount = 0;
    }

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int level) {
        this.currentLevel = level;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void setScore(int points) {
        this.score = points;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void incrementMoves() {
        this.movesCount++;
        this.score++; // Exemplu: fiecare mișcare adaugă și la scorul global
    }

    public void resetMoves() {
        this.movesCount = 0;
    }
}
