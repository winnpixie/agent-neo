package io.github.winnpixie.loader;

import io.github.winnpixie.loader.ui.main.MainWindow;

import java.util.logging.Logger;

public class AgentSmith {
    public static final Logger LOGGER = Logger.getLogger(AgentSmith.class.getName());

    public static void main(String[] args) {
        new MainWindow(640, 480);
    }
}
