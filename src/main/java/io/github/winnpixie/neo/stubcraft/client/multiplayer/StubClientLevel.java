package io.github.winnpixie.neo.stubcraft.client.multiplayer;

import io.github.winnpixie.neo.stubcraft.mappings.ClassInfo;
import io.github.winnpixie.neo.stubcraft.mappings.MappingsLoader;
import io.github.winnpixie.neo.stubcraft.world.level.StubLevel;
import io.github.winnpixie.neo.stubcraft.world.level.entity.StubLevelEntityGetter;
import io.github.winnpixie.neo.utilities.reflection.ReflectedClass;
import io.github.winnpixie.neo.utilities.reflection.ReflectedMethod;

import java.lang.reflect.InvocationTargetException;

public class StubClientLevel extends StubLevel {
    private final StubLevelEntityGetter LEVELENTITYGETTER = new StubLevelEntityGetter();

    private static final ClassInfo CLASS_INFO;
    private static final ReflectedClass LEVEL_CLASS;
    private static final ReflectedMethod LEVELENTITYGETTER_METHOD;

    static {
        CLASS_INFO = MappingsLoader.getClass("net.minecraft.client.multiplayer.ClientLevel");

        try {
            LEVEL_CLASS = ReflectedClass.getClass(CLASS_INFO.getObfuscatedName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            LEVELENTITYGETTER_METHOD = LEVEL_CLASS.getDeclaredMethod(CLASS_INFO.getMethod("getEntities"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public StubLevelEntityGetter getEntities() {
        try {
            LEVELENTITYGETTER.setRealInstance(LEVELENTITYGETTER_METHOD.invoke(super.getRealInstance()));
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return LEVELENTITYGETTER;
    }
}
