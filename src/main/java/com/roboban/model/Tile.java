package com.roboban.model;

public class Tile {
    //  Un tile are coordonate, poate fi walkable sau nu, și poate fi un goalTile
    private int x;
    private int y;
    private boolean walkable;
    private boolean goalTile;

    public Tile(int x, int y, boolean walkable, boolean goalTile) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.goalTile = goalTile;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean isGoalTile() {
        return goalTile;
    }

    public void setGoalTile(boolean goalTile) {
        this.goalTile = goalTile;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }
}
