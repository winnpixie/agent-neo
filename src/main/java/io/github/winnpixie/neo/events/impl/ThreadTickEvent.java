package io.github.winnpixie.neo.events.impl;

public class ThreadTickEvent {
    private boolean isInGame;

    public ThreadTickEvent(boolean isInGame) {
        this.isInGame = isInGame;
    }

    public boolean isInGame() {
        return isInGame;
    }
}
