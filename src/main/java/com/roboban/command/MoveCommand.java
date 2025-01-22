package com.roboban.command;

import com.roboban.model.*;

//  Comandă de mișcare pentru player
public class MoveCommand implements Command {
    private Board board;
    private Player player;
    private Direction direction;

    public MoveCommand(Board board, Player player, Direction direction) {
        this.board = board;
        this.player = player;
        this.direction = direction;
    }

    @Override
    public void execute() {
        //  Calculez noile coordonate
        int newX = player.getX();
        int newY = player.getY();

        switch (direction) {
            case UP:
                newY--;
                break;
            case DOWN:
                newY++;
                break;
            case LEFT:
                newX--;
                break;
            case RIGHT:
                newX++;
                break;
        }

        Tile nextTile = board.getTileAt(newX, newY);

        if (nextTile != null && nextTile.isWalkable()) {
            //  Verificăm dacă este vreo cutie acolo
            Box box = findBox(newX, newY);
            if (box != null) {
                //  Împinge cutia
                int pushX = newX + (newX - player.getX());
                int pushY = newY + (newY - player.getY());

                Tile pushTile = board.getTileAt(pushX, pushY);

                //  Dacă tile-ul unde împingem cutia e valid
                if (pushTile != null && pushTile.isWalkable() && findBox(pushX, pushY) == null) {
                    box.setPosition(pushX, pushY);
                    //  Mutăm și playerul
                    player.setPosition(newX, newY);
                }
            } else {
                //  Nu e cutie, deci ne putem mișca direct
                player.setPosition(newX, newY);
            }
        }
    }

    private Box findBox(int x, int y) {
        for (Box b : board.getBoxes()) {
            if (b.getX() == x && b.getY() == y) {
                return b;
            }
        }
        return null;
    }
}
