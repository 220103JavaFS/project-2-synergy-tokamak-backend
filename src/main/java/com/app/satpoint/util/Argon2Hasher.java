package com.app.satpoint.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Utility class to create a hash of user passwords that will then be stored in the DB
 * Will also verify a user's inputted pw when a user tries to log in
 * Uses Argon2id - recommended by OWASP for pw hashing
 */
public class Argon2Hasher {

    private static Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
//    private static String hash; //test
//
////    test
//    public static void main(String... args) {
//        hash = hash("password");
//        System.out.println(hash);
//        System.out.println(verify(hash, "123dfs"));
//        System.out.println(verify("dfsdf", "222222"));
//    }

    /**
     * Method used when storing a pw in the DB
     * This method will has a user's pw
     * @param password
     * @return
     */
    static String hash(String password) {
        return argon2.hash(2, 15360, 1, password.toCharArray());
    }

    /**
     * Used when a user logs in
     * It takes the hashed pw from the DB and compares with the user's inputted pw
     * @param userOutPW
     * @param password
     * @return true if pw's match, else false
     */
    public static boolean verify(String userOutPW, String password) {
        return argon2.verify(userOutPW, password.toCharArray());
        //return true;

    }
}