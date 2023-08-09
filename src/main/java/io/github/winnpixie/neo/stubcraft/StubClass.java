package io.github.winnpixie.neo.stubcraft;

public class StubClass {
    private Object realInstance;

    public StubClass() {
        this(null);
    }

    public StubClass(Object instance) {
        this.realInstance = instance;
    }

    public Object getRealInstance() {
        return realInstance;
    }

    public void setRealInstance(Object realInstance) {
        this.realInstance = realInstance;
    }
}
