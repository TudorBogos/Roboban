package com.roboban.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    //  Matricea de tile-uri
    private Tile[][] tiles;
    private int width;
    private int height;

    //  Referință către player
    private Player player;

    //  Listă de cutii
    private List<Box> boxes = new ArrayList<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[height][width];
        //  Inițializare tile-uri goale
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = new Tile(j, i, true, false);
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void addBox(Box box) {
        this.boxes.add(box);
    }

    public List<Box> getBoxes() {
        return this.boxes;
    }

    public Tile getTileAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null; //  În afara limitei
        }
        return tiles[y][x];
    }

    public void setTile(int x, int y, Tile tile) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            tiles[y][x] = tile;
        }
    }

    // Metodă exemplu de afișare
    public void printBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (player != null && player.getX() == j && player.getY() == i) {
                    System.out.print("P ");
                } else if (isBoxAt(j, i)) {
                    System.out.print("B ");
                } else {
                    System.out.print(tiles[i][j].isWalkable() ? ". " : "# ");
                }
            }
            System.out.println();
        }
    }

    private boolean isBoxAt(int x, int y) {
        for (Box b : boxes) {
            if (b.getX() == x && b.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
