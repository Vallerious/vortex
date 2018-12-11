package com.webserver.session;

public interface ISession {

    void login(String userId);

    void logout(String userId);

}
