package com.core.utils;

import java.io.Serializable;

public class Pair<F, S> implements Serializable {
    private F first;
    private S second;

    public Pair() {
    }

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public int hashCode() {
        int firstHash = first == null ? 0x30f : first.hashCode();
        int secondHash = second == null ? 0x30f : second.hashCode();
        return firstHash ^ secondHash;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair<?, ?> another = (Pair<?, ?>) obj;
            boolean firstCheck, secondCheck;
            if (first == null && another.first == null) {
                firstCheck = true;
            } else {
                firstCheck = first != null && first.equals(another.first);
            }
            if (second == null && another.second == null) {
                secondCheck = true;
            } else {
                secondCheck = second != null && second.equals(another.second);
            }
            return firstCheck && secondCheck;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer text = new StringBuffer();
        text.append("First:");
        if (first != null)
            text.append(first.toString());
        else
            text.append("null");
        text.append(" Second:");
        if (second != null)
            text.append(second.toString());
        else
            text.append("null");
        text.append(" \n");
        return text.toString();
    }

}
