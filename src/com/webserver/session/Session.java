package com.webserver.session;

import java.util.HashSet;
import java.util.Set;

public class Session implements ISession {
    private Set<String> loggedUsers;

    public Session() {
        this.loggedUsers = new HashSet<>();
    }

    @Override
    public void login(String userId) {
        if (!userId.isEmpty()) {
            this.loggedUsers.add(userId);
        }
    }

    @Override
    public void logout(String userId) {
        if (!userId.isEmpty()) {
            this.loggedUsers.remove(userId);
        }
    }
}
