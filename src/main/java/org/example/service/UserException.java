package org.example.service;

/**
 * Represents a user domain exception
 */
public class UserException extends Exception {
    /**
     * Default constructor.
     */
    public UserException() {
        super();
    }

    /**
     * Constructor that allows a specific error message to be specified.
     *
     * @param msg the detail message.
     */
    public UserException(String msg) {
        super(msg);
    }

    static class UserNotFoundException extends UserException {
        public UserNotFoundException(String msg) {
            super(msg);
        }

    }

    static class InvalidPasswdException extends UserException {
        public InvalidPasswdException(String msg) {
            super(msg);
        }
    }

    static class InvalidParameterException extends UserException {
        public InvalidParameterException(String msg) {
            super(msg);
        }
    }
}
