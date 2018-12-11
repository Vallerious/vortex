package com.webserver.db;

import com.webserver.models.User;

/**
 * Whatchamacallit? Hmmm Maybe "caveman DIC"?
 */
public abstract class DBInstance {
    private static UserDB userDB;

    static {
        userDB = new UserDB();
    }

    private DBInstance() {
    }

    public static UserDB getUserDBInstance() {
        return userDB;
    }
}
