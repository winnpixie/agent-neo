package io.github.winnpixie.neo.stubcraft.world.level;

import io.github.winnpixie.neo.stubcraft.StubClass;
import io.github.winnpixie.neo.stubcraft.mappings.ClassInfo;
import io.github.winnpixie.neo.stubcraft.mappings.MappingsLoader;
import io.github.winnpixie.neo.utilities.reflection.ReflectedClass;

public class StubLevel extends StubClass {
    private static final ClassInfo CLASS_INFO;
    private static final ReflectedClass LEVEL_CLASS;

    static {
        CLASS_INFO = MappingsLoader.getClass("net.minecraft.world.level.Level");

        try {
            LEVEL_CLASS = ReflectedClass.getClass(CLASS_INFO.getObfuscatedName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
