package com.webserver.db;

import com.webserver.models.User;

public class UserDB extends MemoryDB<User> {
    {
        this.indexedRows.put("1c", new User("1c", "Pesho", "1234"));
        this.indexedRows.put("2c", new User("2c", "Gosho", "asd"));
        this.indexedRows.put("3c", new User("3c", "valeri@abv.bg", "asd"));
    }

    public User getByEmail(String email) {
        return this.getBy(u -> u.getEmail().equals(email));
    }
}
