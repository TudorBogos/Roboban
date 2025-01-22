package com.roboban.mediator;

import com.roboban.model.Board;
import com.roboban.model.Direction;
import com.roboban.command.Command;
import com.roboban.command.MoveCommand;

import java.util.ArrayList;
import java.util.List;

//  Acesta coordonează interacțiunile dintre entități
public class GameMediator {
    private final Board board;

    //  Păstrează o istorie a comenzilor dacă vrei să faci undo
    private final List<Command> history = new ArrayList<>();

    public GameMediator(Board board) {
        this.board = board;
    }

    public void movePlayer(Direction direction) {
        //  Creez o comandă de mișcare
        Command move = new MoveCommand(board, board.getPlayer(), direction);
        move.execute();
        //history.add(move);
    }

}
