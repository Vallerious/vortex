package com.webserver.handlers;

import com.webserver.db.DBInstance;
import com.webserver.db.UserDB;
import com.webserver.enums.StatusCodes;
import com.webserver.models.User;
import com.webserver.objects.Request;
import com.webserver.objects.Response;
import com.webserver.parsers.ResponseSerializer;
import com.webserver.session.ISession;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.io.UnsupportedEncodingException;

public class LoginRequestHandler extends PostRequestHandler {
    public LoginRequestHandler(ResponseSerializer responseSerializer, ISession session) {
        super("/login", responseSerializer, session);
    }

    public LoginRequestHandler(ResponseSerializer responseSerializer) {
        super("/login", responseSerializer);
    }

    @Override
    protected byte[] handle_internal(Request req, Response res) {
        try {
            String email = req.body("email");

            UserDB userDBInstance = DBInstance.getUserDBInstance();

            User loggingUser = userDBInstance.getByEmail(email);

            String password = req.body("password");

            if (loggingUser == null || !loggingUser.getPassword().equals(password)) {
                throw new NotFound();
            }

            if (this.session != null) {
                this.session.login(loggingUser.getId());

                res.setStatusCode(StatusCodes.Found);
                res.getHeaders().put("Set-Cookie", "authToken=" + loggingUser.getId());
                res.getHeaders().put("Location", "/index");
            }
        } catch (UnsupportedEncodingException uso) {
            // Ignore this as we are setting correct encoding.
        } catch (NotFound nfe) {
            res.setStatusCode(StatusCodes.Forbidden);
        }

        return this.responseSerializer.toBytes(res);
    }


}
