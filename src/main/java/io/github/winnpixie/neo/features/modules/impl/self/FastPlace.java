package io.github.winnpixie.neo.features.modules.impl.self;

import io.github.winnpixie.neo.events.Handler;
import io.github.winnpixie.neo.events.impl.ThreadTickEvent;
import io.github.winnpixie.neo.features.modules.Module;
import io.github.winnpixie.neo.stubcraft.client.StubClient;

public class FastPlace extends Module {
    public FastPlace() {
        super("FastPlace");
    }

    @Handler
    private void onThreadTick(ThreadTickEvent event) {
        StubClient.setRightClickDelay(0);
    }
}
