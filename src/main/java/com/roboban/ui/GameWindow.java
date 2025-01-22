package com.roboban.ui;

import com.roboban.adapter.AudioAdapter;
import com.roboban.adapter.ExternalSoundLib;
import com.roboban.singleton.GameManager;

import javax.swing.*;
import java.awt.*;

//  Clasa principală care gestionează toate panourile (meniu, nivele)
public class GameWindow extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel containerPanel;

    // Panourile
    private final MainMenuPanel mainMenuPanel;
    private GamePanel gamePanelLevel1;
    private GamePanel gamePanelLevel2;

    // Pentru muzică
    private final ExternalSoundLib externalLib;
    private final AudioAdapter audioAdapter;

    public GameWindow() {
        setTitle("Roboban");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        //  Inițializăm librăria de sunet și adaptorul
        externalLib = new ExternalSoundLib();
        audioAdapter = new AudioAdapter(externalLib);

        //  Redăm muzica la pornirea ferestrei
        audioAdapter.playMusic("src/main/resources/muzica.wav");


        //  Inițiem managerul și setăm nivelul curent la 1 (opțional)
        GameManager.getInstance().setCurrentLevel(1);

        // Layout & container
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);

        // Cream panourile
        mainMenuPanel = new MainMenuPanel(this);
        createGamePanels();
        // -> creează gamePanelLevel1 și gamePanelLevel2

        // Adăugăm panourile în container
        containerPanel.add(mainMenuPanel, "MainMenu");
        containerPanel.add(gamePanelLevel1, "Level1");
        containerPanel.add(gamePanelLevel2, "Level2");

        add(containerPanel);
        setVisible(true);
    }


    /// Metodă pentru a crea / recrea panourile de joc (nivel 1 și 2).
    public void createGamePanels() {
        if (gamePanelLevel1 != null) {
            containerPanel.remove(gamePanelLevel1);
        }
        if (gamePanelLevel2 != null) {
            containerPanel.remove(gamePanelLevel2);
        }

        // Creăm panouri noi pentru nivelurile 1 și 2
        gamePanelLevel1 = new GamePanel(this, 1);
        gamePanelLevel2 = new GamePanel(this, 2);

        containerPanel.add(gamePanelLevel1, "Level1");
        containerPanel.add(gamePanelLevel2, "Level2");
    }


    @Override
    public void dispose() {
        audioAdapter.stopMusic();
        super.dispose();
    }

    // Metode pentru a schimba ecranele
    public void showMainMenu() {
        cardLayout.show(containerPanel, "MainMenu");
    }

    public void showLevel1() {
        cardLayout.show(containerPanel, "Level1");
    }

    public void showLevel2() {
        cardLayout.show(containerPanel, "Level2");
    }
}
