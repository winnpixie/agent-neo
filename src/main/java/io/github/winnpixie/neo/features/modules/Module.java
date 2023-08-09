package io.github.winnpixie.neo.features.modules;

import io.github.winnpixie.neo.Neo;
import io.github.winnpixie.neo.features.Feature;

public class Module extends Feature {
    private boolean active;

    public Module(String name) {
        super(name);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;

        if (active) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void toggle() {
        setActive(!active);
    }

    public void onEnable() {
        Neo.EVENT_BUS.register(this);
    }

    public void onDisable() {
        Neo.EVENT_BUS.unregister(this);
    }
}
