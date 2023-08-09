package io.github.winnpixie.neo.stubcraft.client;

import io.github.winnpixie.neo.stubcraft.StubClass;
import io.github.winnpixie.neo.stubcraft.client.entity.StubLocalPlayer;
import io.github.winnpixie.neo.stubcraft.client.multiplayer.StubClientLevel;
import io.github.winnpixie.neo.stubcraft.mappings.ClassInfo;
import io.github.winnpixie.neo.stubcraft.mappings.MappingsLoader;
import io.github.winnpixie.neo.utilities.reflection.ReflectedClass;
import io.github.winnpixie.neo.utilities.reflection.ReflectedField;

public class StubClient extends StubClass {
    private static final StubClientLevel CLIENT_LEVEL = new StubClientLevel();
    private static final StubLocalPlayer PLAYER = new StubLocalPlayer();

    private static final ClassInfo CLASS_INFO;
    private static final ReflectedClass CLIENT_CLASS;
    private static final ReflectedField INSTANCE_FIELD;
    private static final ReflectedField CLIENTLEVEL_FIELD;
    private static final ReflectedField PLAYER_FIELD;
    private static final ReflectedField RIGHTCLICKDELAY_FIELD;

    static {
        CLASS_INFO = MappingsLoader.getClass("net.minecraft.client.Minecraft");

        try {
            CLIENT_CLASS = ReflectedClass.getClass(CLASS_INFO.getObfuscatedName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            INSTANCE_FIELD = CLIENT_CLASS.getDeclaredField(CLASS_INFO.getField("instance"));
            CLIENTLEVEL_FIELD = CLIENT_CLASS.getDeclaredField(CLASS_INFO.getField("level"));
            PLAYER_FIELD = CLIENT_CLASS.getDeclaredField(CLASS_INFO.getField("player"));
            RIGHTCLICKDELAY_FIELD = CLIENT_CLASS.getDeclaredField(CLASS_INFO.getField("rightClickDelay"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getGameInstance() {
        try {
            return INSTANCE_FIELD.getValue(null);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public static StubClientLevel getClientLevel() {
        try {
            CLIENT_LEVEL.setRealInstance(CLIENTLEVEL_FIELD.getValue(getGameInstance()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return CLIENT_LEVEL;
    }

    public static StubLocalPlayer getPlayer() {
        try {
            PLAYER.setRealInstance(PLAYER_FIELD.getValue(getGameInstance()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return PLAYER;
    }


    public static int getRightClickDelay() {
        try {
            return RIGHTCLICKDELAY_FIELD.getInt(getGameInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setRightClickDelay(int delay) {
        try {
            RIGHTCLICKDELAY_FIELD.setInt(getGameInstance(), delay);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
