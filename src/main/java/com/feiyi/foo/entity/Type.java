package com.feiyi.foo.entity;

/**
 * Created by feiyi123 on 21/12/22.
 */
public enum Type {
    INTEGER;

    public static Type fromString(String typeString) {
        Type result = null;
        for (Type type : Type.values()) {
            if (type.name().equalsIgnoreCase(typeString)) {
                result = type;
            }
        }
        if (result == null) {
            throw new RuntimeException("Type is invalid: " + typeString);
        }

        return result;
    }

    public boolean isInteger() {
        return this.equals(Type.INTEGER);
    }

}
