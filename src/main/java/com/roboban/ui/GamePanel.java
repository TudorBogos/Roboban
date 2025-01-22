package com.roboban.ui;

import com.roboban.model.Board;
import com.roboban.model.Box;
import com.roboban.model.Direction;
import com.roboban.builder.LevelBuilder;
import com.roboban.mediator.GameMediator;
import com.roboban.singleton.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private final GameWindow parent;
    private Board board;
    private GameMediator mediator;

    private final JLabel movesLabel;
    private final JButton resetButton;

    public GamePanel(GameWindow parent, int levelNumber) {
        this.parent = parent;

        setLayout(new BorderLayout());

        // Top panel: afișăm numărul de mișcări și adăugăm butonul de reset
        JPanel topPanel = new JPanel();
        movesLabel = new JLabel("Mișcări: 0");
        resetButton = new JButton("Reset Level");

        topPanel.add(movesLabel);
        topPanel.add(resetButton);
        add(topPanel, BorderLayout.NORTH);

        // Setăm focus pentru KeyListener
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        // Buton reset: reconstruim nivelul curent
        resetButton.addActionListener(e -> resetLevel());

        // Inițializăm nivelul
        initLevel(levelNumber);
    }

    private void initLevel(int levelNumber) {
        // Setăm nivelul curent în GameManager
        GameManager.getInstance().resetMoves();

        // Construim board-ul cu LevelBuilder
        board = new LevelBuilder()
                .setDimensions(8, 8)
                .addPlayer(3, 3)
                .addBox(4, 3)
                .build();

        if (levelNumber == 1) {
            board.getTileAt(6, 3).setGoalTile(true);
        } else if (levelNumber == 2) {
            board.getTileAt(7, 7).setGoalTile(true);
            board.getPlayer().setPosition(2, 2);
            for (Box box : board.getBoxes()) {
                box.setPosition(5, 2);
            }
        }

        // Cream un mediator pentru board-ul actual
        mediator = new GameMediator(board);

        // Actualizăm afișajul mișcărilor
        updateMovesLabel();

        // Redesenăm panoul
        repaint();
    }

    private void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                mediator.movePlayer(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                mediator.movePlayer(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                mediator.movePlayer(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                mediator.movePlayer(Direction.RIGHT);
                break;
            default:
                return;
        }

        // Incrementăm numărul de mișcări în GameManager
        GameManager.getInstance().incrementMoves();
        updateMovesLabel();

        // Verificăm dacă nivelul e complet
        checkLevelComplete();

        // Redesenăm board-ul
        repaint();
    }

    private void resetLevel() {
        initLevel(GameManager.getInstance().getCurrentLevel());
    }

    private void checkLevelComplete() {
        boolean levelComplete = board.getBoxes().stream()
                .anyMatch(box -> board.getTileAt(box.getX(), box.getY()).isGoalTile());

        if (levelComplete) {
            JOptionPane.showMessageDialog(this, "Nivel complet!");

            int currentLevel = GameManager.getInstance().getCurrentLevel();

            if (currentLevel == 1) {
                // Setăm nivelul curent la 2 și afișăm al doilea nivel
                GameManager.getInstance().setCurrentLevel(2);
                parent.showLevel2();
                // Ieșim pentru a preveni verificări ulterioare
            } else if (currentLevel == 2) {
                // Jocul s-a terminat, revenim la meniu
                JOptionPane.showMessageDialog(this, "Felicitări! Ai terminat jocul cu " + GameManager.getInstance().getScore());
                parent.showMainMenu();
            }
        }
    }


    private void updateMovesLabel() {
        int moves = GameManager.getInstance().getMovesCount();
        movesLabel.setText("Mișcări: " + moves);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dimensiunea celulelor
        int cellSize = 50;
        int offsetX = 50;
        int offsetY = 100;

        // Desenăm grid-ul
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int x = offsetX + col * cellSize;
                int y = offsetY + row * cellSize;

                if (board.getTileAt(col, row).isGoalTile()) {
                    g.setColor(Color.YELLOW); // Tile de finish
                } else {
                    g.setColor(Color.LIGHT_GRAY); // Tile normal
                }
                g.fillRect(x, y, cellSize, cellSize);

                // Contur
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }

        // Desenăm player-ul
        int playerX = offsetX + board.getPlayer().getX() * cellSize;
        int playerY = offsetY + board.getPlayer().getY() * cellSize;
        g.setColor(Color.BLUE); // Player-ul e albastru
        g.fillOval(playerX + 5, playerY + 5, cellSize - 10, cellSize - 10);

        // Desenăm cutiile
        for (Box box : board.getBoxes()) {
            int boxX = offsetX + box.getX() * cellSize;
            int boxY = offsetY + box.getY() * cellSize;
            g.setColor(Color.RED); // Cutiile sunt roșii
            g.fillRect(boxX + 5, boxY + 5, cellSize - 10, cellSize - 10);
        }
    }
}
