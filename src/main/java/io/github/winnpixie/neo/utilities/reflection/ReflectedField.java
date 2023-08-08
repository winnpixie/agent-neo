package io.github.winnpixie.neo.utilities.reflection;

import java.lang.reflect.Field;

public class ReflectedField extends ReflectedMember<Field> {
    public ReflectedField(Field field, ReflectedClass owner) {
        super(field, owner);
    }

    public <T> T getValue(Object owner) throws IllegalAccessException {
        return (T) getMember().get(owner);
    }

    public byte getByte(Object owner) throws IllegalAccessException {
        return getMember().getByte(owner);
    }

    public short getShort(Object owner) throws IllegalAccessException {
        return getMember().getShort(owner);
    }

    public int getInt(Object owner) throws IllegalAccessException {
        return getMember().getInt(owner);
    }

    public long getLong(Object owner) throws IllegalAccessException {
        return getMember().getLong(owner);
    }

    public float getFloat(Object owner) throws IllegalAccessException {
        return getMember().getFloat(owner);
    }

    public double getDouble(Object owner) throws IllegalAccessException {
        return getMember().getDouble(owner);
    }

    public char getChar(Object owner) throws IllegalAccessException {
        return getMember().getChar(owner);
    }

    public boolean getBoolean(Object owner) throws IllegalAccessException {
        return getMember().getBoolean(owner);
    }

    public void setValue(Object owner, Object value) throws IllegalAccessException {
        getMember().set(owner, value);
    }

    public void setByte(Object owner, byte value) throws IllegalAccessException {
        getMember().setByte(owner, value);
    }

    public void setShort(Object owner, short value) throws IllegalAccessException {
        getMember().setShort(owner, value);
    }

    public void setInt(Object owner, int value) throws IllegalAccessException {
        getMember().setInt(owner, value);
    }

    public void setLong(Object owner, long value) throws IllegalAccessException {
        getMember().setLong(owner, value);
    }

    public void setFloat(Object owner, float value) throws IllegalAccessException {
        getMember().setFloat(owner, value);
    }

    public void setDouble(Object owner, double value) throws IllegalAccessException {
        getMember().setDouble(owner, value);
    }

    public void setBoolean(Object owner, boolean value) throws IllegalAccessException {
        getMember().setBoolean(owner, value);
    }
}
