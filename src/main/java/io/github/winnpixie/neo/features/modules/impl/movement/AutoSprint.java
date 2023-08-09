package io.github.winnpixie.neo.features.modules.impl.movement;

import io.github.winnpixie.neo.events.Handler;
import io.github.winnpixie.neo.events.impl.ThreadTickEvent;
import io.github.winnpixie.neo.features.modules.Module;
import io.github.winnpixie.neo.stubcraft.client.StubClient;

public class AutoSprint extends Module {
    public AutoSprint() {
        super("AutoSprint");
    }

    @Handler
    private void onThreadTick(ThreadTickEvent event) {
        if (!event.isInGame()) return;

        StubClient.getPlayer().setSprinting(true);
    }
}
