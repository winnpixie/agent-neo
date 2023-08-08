package io.github.winnpixie.neo.stubcraft.client;

import io.github.winnpixie.neo.stubcraft.Stub;
import io.github.winnpixie.neo.stubcraft.client.entities.StubPlayer;
import io.github.winnpixie.neo.utilities.reflection.ReflectedClass;
import io.github.winnpixie.neo.utilities.reflection.ReflectedField;

public class StubClient extends Stub {
    public static final StubClient INSTANCE = new StubClient();

    private static final ReflectedClass realClass;
    private static final ReflectedField instanceField;
    private static final ReflectedField playerField;

    static {
        try {
            realClass = ReflectedClass.getClass("enn");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            instanceField = realClass.getDeclaredField("F");
            playerField = realClass.getDeclaredField("t");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private final StubPlayer player = new StubPlayer();

    public static Object getGameInstance() {
        try {
            return instanceField.getValue(null);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public StubPlayer getPlayer() {
        try {
            player.setRealInstance(playerField.getValue(getGameInstance()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return player;
    }
}
