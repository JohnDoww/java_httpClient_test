package helpers;

import java.util.Base64;


public class BaseMethods {

    /**
     * There is a class where we store all methods which we reuse.
     */

    /**
     * The method which turn login and password to the base 64 authorisation token which gets access to the test service.
     * Valid converted authorisation token get back when using.
     */
    public String generateAccessToken(String userLogin, String userPassword) {
        String userCredentials = userLogin + ":" + userPassword;
        String encodedCredentials = Base64.getEncoder().encodeToString(userCredentials.getBytes());
        String userAuthorizationToken = "Basic " + encodedCredentials;

        return userAuthorizationToken;
    }


}
