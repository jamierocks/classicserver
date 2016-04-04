package uk.jamierocks.classicserver.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the user type (op or not).
 */
public enum UserType {

    NORMAL(0),
    OPERATOR(100);

    private static Map<Integer, UserType> intToUserTypeMap = new HashMap<>();

    private final int id;

    UserType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static UserType fromId(int id) {
        if (intToUserTypeMap.containsKey(id)) {
            return intToUserTypeMap.get(id);
        }
        return null;
    }
}
