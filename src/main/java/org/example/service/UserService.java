package org.example.service;

/**
 * Communication types(mainly for encapluate failure representations):
 * option 1.return code representate the result of operation,eg: 0--succ, 1-- specific failure
 * option 2.exception code representate the result of operation,eg: XXException -- failure1
 * option 3.rarely used(Result passed in as argument)
 */
public interface UserService {
    /**
     * honor option1
     * return code desc:
     * 0: succ;
     * -1: input error;
     * -2: user exists;
     * -3: password not valid;
     */
    int signup(IdentityParameters identityParameters);

    /**
     * honor option2
     * it's better Frontend ensure that the parameters are not empty
     */
    void login(IdentityParameters identityParameters) throws UserException;

    /**
     * option3: result encapluate the result of operation
     * @param result
     */
//    void mock(String result);

}
