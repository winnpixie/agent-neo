package io.github.winnpixie.neo.events;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private final Map<Class<?>, List<MethodListener>> REGISTRY_MAP = new HashMap<>();
    private final Map<Object, List<MethodListener>> CACHE_MAP = new HashMap<>();

    public void register(Object owner) {
        if (CACHE_MAP.containsKey(owner)) {
            CACHE_MAP.get(owner).forEach(this::register);
            return;
        }

        for (Method method : owner.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Handler.class)) continue;
            if (method.getParameterCount() != 1) continue;
            if (!method.isAccessible()) method.setAccessible(true);

            MethodListener listener = new MethodListener(method, owner);
            register(listener);

            // Cache
            List<MethodListener> cachedListeners = CACHE_MAP.computeIfAbsent(owner, v -> new CopyOnWriteArrayList<>());
            cachedListeners.add(listener);
        }
    }

    public void register(MethodListener listener) {
        List<MethodListener> listeners = REGISTRY_MAP.computeIfAbsent(listener.getEventClass(), v -> new CopyOnWriteArrayList<>());
        listeners.add(listener);
    }

    public void unregister(Object owner) {
        if (!CACHE_MAP.containsKey(owner)) return;

        CACHE_MAP.get(owner).forEach(this::unregister);
    }

    public void unregister(MethodListener listener) {
        REGISTRY_MAP.values().forEach(listeners -> listeners.remove(listener));
    }

    public <T> T dispatch(T event) {
        List<MethodListener> listeners = REGISTRY_MAP.get(event.getClass());
        if (listeners == null || listeners.isEmpty()) return event;

        listeners.forEach(listener -> listener.invoke(event));
        return event;
    }
}
