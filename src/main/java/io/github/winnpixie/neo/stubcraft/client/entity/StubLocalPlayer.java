package io.github.winnpixie.neo.stubcraft.client.entity;

import io.github.winnpixie.neo.stubcraft.mappings.ClassInfo;
import io.github.winnpixie.neo.stubcraft.mappings.MappingsLoader;
import io.github.winnpixie.neo.stubcraft.world.entity.StubLivingEntity;
import io.github.winnpixie.neo.utilities.reflection.ReflectedClass;

public class StubLocalPlayer extends StubLivingEntity {
    private static final ClassInfo CLASS_INFO;
    private static final ReflectedClass LOCALPLAYER_CLASS;

    static {
        CLASS_INFO = MappingsLoader.getClass("net.minecraft.client.player.LocalPlayer");

        try {
            LOCALPLAYER_CLASS = ReflectedClass.getClass(CLASS_INFO.getObfuscatedName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
