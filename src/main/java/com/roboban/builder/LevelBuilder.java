package com.roboban.builder;

import com.roboban.model.Board;
import com.roboban.model.Box;
import com.roboban.model.Player;

public class LevelBuilder {
    //  Reține obiectul Board pe care îl construim
    private Board board;

    public LevelBuilder setDimensions(int width, int height) {
        board = new Board(width, height);
        return this;
    }

    public LevelBuilder addPlayer(int x, int y) {
        if (board != null) {
            Player player = new Player(x, y);
            board.setPlayer(player);
        }
        return this;
    }

    public LevelBuilder addBox(int x, int y) {
        if (board != null) {
            Box box = new Box(x, y);
            board.addBox(box);
        }
        return this;
    }

    //  Poți adăuga alte elemente de decor, pereți etc.

    public Board build() {
        return this.board;
    }
}
