package com.roboban.adapter;

//  Adapter care mapează interfața noastră la librăria externă
public class AudioAdapter implements GameAudioInterface {
    private ExternalSoundLib soundLib;

    public AudioAdapter(ExternalSoundLib soundLib) {
        this.soundLib = soundLib;
    }

    @Override
    public void playMusic(String track) {
        soundLib.playTrack(track);
    }

    @Override
    public void stopMusic() {
        soundLib.stop();
    }
}
