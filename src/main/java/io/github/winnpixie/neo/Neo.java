package io.github.winnpixie.neo;

import io.github.winnpixie.neo.stubcraft.client.StubClient;

import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

public class Neo {
    public static final Logger LOGGER = Logger.getLogger(Neo.class.getName());

    private static boolean running = true;

    private static void initialize() {
        LOGGER.info("BEGIN Neo:init");

        new Thread(() -> {
            while (running) {
                // TODO: Do stuff.
                if (StubClient.INSTANCE.getPlayer().getRealInstance() != null) {
                    StubClient.INSTANCE.getPlayer().setSprinting(true);
                    LOGGER.info("SPRINT");
                }

                try {
                    Thread.sleep(50L); // 20t/s = 50ms/t
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Neo_20tps_thread").start();
        Runtime.getRuntime().addShutdownHook(new Thread(Neo::shutdown));

        LOGGER.info("END Neo:init");
    }

    public static void premain(String args, Instrumentation inst) {
        initialize();
    }

    public static void agentmain(String args, Instrumentation inst) {
        initialize();
    }

    public static void shutdown() {
        running = false;
    }
}
