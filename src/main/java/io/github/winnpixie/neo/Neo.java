package io.github.winnpixie.neo;

import io.github.winnpixie.neo.events.EventBus;
import io.github.winnpixie.neo.events.impl.ThreadTickEvent;
import io.github.winnpixie.neo.features.modules.ModuleManager;
import io.github.winnpixie.neo.stubcraft.client.StubClient;
import io.github.winnpixie.neo.stubcraft.mappings.MappingsLoader;

import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

public class Neo {
    public static final Logger LOGGER = Logger.getLogger(Neo.class.getName());
    public static final EventBus EVENT_BUS = new EventBus();
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();

    private static boolean running = true;

    private static void initialize() {
        LOGGER.info("BEGIN Neo:init");

        new Thread(() -> {
            LOGGER.info("Downloading 1.20.1 Obfuscation Mappings");
            MappingsLoader.load("https://piston-data.mojang.com/v1/objects/6c48521eed01fe2e8ecdadbd5ae348415f3c47da/client.txt");

            MODULE_MANAGER.load();

            while (running) {
                EVENT_BUS.dispatch(new ThreadTickEvent(StubClient.getPlayer().getRealInstance() != null));

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
