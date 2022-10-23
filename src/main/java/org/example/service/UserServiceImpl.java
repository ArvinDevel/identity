package org.example.service;

import org.apache.commons.lang3.StringUtils;
import org.example.entity.User;
import org.example.repository.UserRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Default Implementation of {@link UserService}
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepo userRepo;


    /**
     * * 1. validate input,    1.1 if valid password
     * * 2. check user exists
     * * 3. persistence
     *
     * @param identityParameters
     * @return
     */
    @Override
    public int signup(IdentityParameters identityParameters) {
        String username = identityParameters.getName();
        String password = identityParameters.getPasswd();
        String email = identityParameters.getEmail();
        String phone = identityParameters.getPhone();

        // -6
        boolean isPhoneValid = StringUtils.isNumeric(phone);
        // -5
        boolean isEmailValid = StringUtils.containsAny(email, "@");
        // -3
        boolean isPasswdValid = StringUtils.length(password) > 5;
        // -4
        boolean isUserNameValid = StringUtils.isNotEmpty(username);

        if (!isPhoneValid) {
            return -6;
        }

        if (!isEmailValid) {
            return -5;
        }

        if (!isPasswdValid) {
            return -3;
        }

        if (!isUserNameValid) {
            return -4;
        }

        // step2
        boolean isSameEmailFound = userRepo.findUserByEmail(email) != null;
        boolean isSamePhoneFound = userRepo.findUserByPhone(phone) != null;

        if (isSameEmailFound || isSamePhoneFound) {
            return -2;
        }

        User newUser = new User();
        newUser.setName(username);
        newUser.setPasswd(password);
        newUser.setPhone(phone);
        newUser.setEmail(email);

        userRepo.save(newUser);
        return 0;
    }

    /**
     * **
     * * 1. validate input
     * * 2. validate user exists
     * * 3. verify password
     *
     * @param identityParameters
     * @throws UserException
     */
    @Override
    public void login(IdentityParameters identityParameters) throws UserException {
        String password = identityParameters.getPasswd();
        String email = identityParameters.getEmail();
        String phone = identityParameters.getPhone();
        boolean invalidPasswd = StringUtils.isBlank(password);
        boolean invalidId = StringUtils.isAllBlank(email, phone);
        if (invalidPasswd || invalidId) {
            throw new UserException.InvalidParameterException("parameters invalid");
        }
        User userByEmail = userRepo.findUserByEmail(email);
        User userByPhone = userRepo.findUserByPhone(phone);
        if (null == userByEmail && null == userByPhone) {
            throw new UserException.UserNotFoundException("User " + email + " " + phone + " does not exist");
        }

        if (userByEmail != null && !password.equals(userByEmail.getPasswd())) {
            throw new UserException.InvalidPasswdException("Passwords do not match");
        }

        if (userByPhone != null && !password.equals(userByPhone.getPasswd())) {
            throw new UserException.InvalidPasswdException("Passwords do not match");
        }
    }
}
