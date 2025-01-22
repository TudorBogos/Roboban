package com.roboban.ui;

import com.roboban.singleton.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private GameWindow parent;

    public MainMenuPanel(GameWindow parent) {
        this.parent = parent;
        setLayout(new GridBagLayout());

        JLabel title = new JLabel("Roboban");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");

        // Buton Start -> Resetează nivelele și trece la Level1
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Resetăm nivelul în GameManager (ex: la 1)
                GameManager.getInstance().setCurrentLevel(1);
                GameManager.getInstance().setScore(0);

                // Reconstruiește panourile de joc (Board-urile) prin createGamePanels()
                parent.createGamePanels();

                // Afișăm Level1
                parent.showLevel1();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        gbc.gridy = 1;
        add(startButton, gbc);

        gbc.gridy = 2;
        add(exitButton, gbc);
    }
}
