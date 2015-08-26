package com.core.utils;

import java.io.Serializable;
import java.lang.reflect.Field;

public class Persistence implements Serializable, Cloneable {

    public static final long serialVersionUID = 100500L;

    @Override
    public String toString() {
        return toString(0);
    }

    public Persistence clone() {
        try {
            return (Persistence) super.clone();
        } catch (CloneNotSupportedException c) {
            return null;
        }
    }

    public String toString(int spaces) {
        StringBuilder text = new StringBuilder();
        byte defaultSpacesCount = 4;
        spaces += defaultSpacesCount;
        try {
            Class klass = getClass();
            Class superclass = klass.getSuperclass();
            if (spaces != defaultSpacesCount)
                text.append("\n");
            if (!superclass.getName().equals("com.core.utils.Persistence")) {
                text.append(getSpaces(spaces - 4));
                text.append(superclass.getSimpleName()).append("\n");
                for (Field superdata : superclass.getDeclaredFields()) {
                    superdata.setAccessible(true);
                    text.append(getSpaces(spaces));
                    text.append(superdata.getName());
                    text.append(" : ");
                    if (superdata.get(this) instanceof Persistence) {
                        text.append(((Persistence) superdata.get(this)).toString(spaces));
                    } else {
                        text.append(superdata.get(this));
                    }
                    text.append(" \n");
                }
                text.append("\n\n");
            }
            if (spaces != defaultSpacesCount)
                text.append("\n");
            text.append(getSpaces(spaces));
            text.append(klass.getSimpleName()).append("\n");
            for (Field current : klass.getDeclaredFields()) {
                current.setAccessible(true);
                text.append(getSpaces(spaces));
                text.append(current.getName());
                text.append(" : ");
                if (current.get(this) instanceof Persistence) {
                    text.append(((Persistence) current.get(this)).toString(spaces));
                } else {
                    text.append(current.get(this));
                }
                text.append(" \n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return text.toString();
    }

    private String getSpaces(int amount) {
        StringBuilder spaces = new StringBuilder("");
        for (int i = 0; i < amount; i++)
            spaces.append(" ");
        return spaces.toString();
    }

    public void assertNotNull() {
        String errorsList = "";
        try {
            for (Field current : this.getClass().getDeclaredFields())
                if (current.get(this) == null)
                    errorsList += current.getName() + "\n";
        } catch (Exception ex) {
        }
        if (!errorsList.equals(""))
            throw new AssertionError("Fields are null: \n" + errorsList);
    }

}
