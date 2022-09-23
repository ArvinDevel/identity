package org.example.service;

import org.apache.commons.lang3.StringUtils;
import org.example.entity.User;
import org.example.repository.UserRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Default Implementation of {@link UserService}
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepo userRepo;


    /**
     * 1. validate input
     * 2. check user exists
     * 3. validate password
     * 4. persistence
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public int signup(String username, String password) {
        boolean invalidInput = StringUtils.isAnyBlank(username, password);
        if (invalidInput) {
            return -1;
        }
        User user = userRepo.findUserByName(username);
        if (null != user) {
            return -2;
        }

        if (password.length() < 6) {
            return -3;
        }
        User newUser = new User();
        newUser.setName(username);
        newUser.setPasswd(password);
        newUser.setStatus("active");
        newUser.setRegistrationDate(new Date());
        userRepo.save(newUser);
        return 0;
    }

    /**
     * 1. validate input
     * 2. validate user exists
     * 3. verify password
     *
     * @param username
     * @param password
     * @throws UserException
     */
    @Override
    public void login(String username, String password) throws UserException {
        boolean invalidInput = StringUtils.isAnyBlank(username, password);
        if (invalidInput) {
            throw new UserException.InvalidParameterException("parameters invalid");
        }
        User user = userRepo.findUserByName(username);
        if (null == user) {
            throw new UserException.UserNotFoundException("User " + username + " does not exist");
        }
        if (!password.equals(user.getPasswd())) {
            throw new UserException.InvalidPasswdException("Passwords do not match");
        }
    }
}
