package com.app.satpoint.util;

import com.app.satpoint.exceptions.MyException;
/**
 * Utility class used to implement server-side validation of user input
 */
public final class AppValidator {

        private static String pattern;
        public AppValidator(){}

        public static boolean isEmpty(String input){
            if(input == null) return true;
            if(input.trim() == "") {
                return true;
            }
            return false;
        }

        public static boolean isValidName(String firstname, String lastname) {
            if(!isEmpty(firstname) && !isEmpty(lastname)) {
                String name = firstname + " " + lastname;

                pattern = "[A-Za-z]{3,20} [A-Za-z]{3,20}";
                if(!name.matches(pattern)) {
                    throw new MyException("Invalid Name entry.");
                }
                return true;
            }
            else if(!isEmpty(firstname)) {
                pattern = "[A-Za-z]{3,20}";
                if(!firstname.matches(pattern)) {
                    throw new MyException("Invalid firstname entry.");
                }
                return true;
            }
            else if(!isEmpty(lastname)) {
                pattern = "[A-Za-z]{3,20}";
                if (!lastname.matches(pattern)) {
                    throw new MyException("Invalid lastname entry.");
                }
                return true;
            }
            throw new MyException("No name provided");
        }

        public static boolean isValidEmail(String email) {
            if(isEmpty(email)) {
                throw new MyException("No email provided");
            }
            pattern = "[A-Za-z]{1,32}([A-Za-z]|[0-9]){0,32}@[A-Za-z]{1,121}([A-Za-z]|[0-9]){0,121}.[a-z]{3}";
            if(!email.matches(pattern)){
                throw new MyException("Invalid email entry.");
            }
            return true;
        }

        public static boolean isValidUserId(int userId) {
            if(userId > 0) {
                return true;
            }
            throw new MyException("No userId provided");
        }
        public static boolean isValidSecret(String secret) {
            if(isEmpty(secret)) {
                throw new MyException("No password provided");
            }
            return true;
        }

        public static boolean isValidUserName(String username) {
            if(isEmpty(username)) {
                throw new MyException("No username provided");
            }
            return true;
        }

    }


