package org.example.service;

/**
 * Communication types(mainly for encapluate failure representations):
 * option 1.return code representate the result of operation,eg: 0--succ, 1-- specific failure
 * option 2.exception code representate the result of operation,eg: XXException -- failure1
 */
public interface UserService {
    /**
     * honor option1
     * return code desc:
     * 0: succ;
     * -1: input error;
     * -2: user exists;
     * -3: password not valid;
     *
     * @param username
     * @param password
     */
    int signup(String username, String password);

    /**
     * honor option2
     * it's better Frontend ensure that the parameters are not empty
     *
     * @param username
     * @param password
     */
    void login(String username, String password) throws UserException;

}
