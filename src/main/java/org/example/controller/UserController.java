package org.example.controller;

import org.example.service.IdentityParameters;
import org.example.service.UserException;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * default crossOrigin configured to allow * for such case frontend can't set 'credentials' as 'include' mode
 */
@CrossOrigin
@RestController
public class UserController {
    @Resource
    private UserService userService;


    @PostMapping(path = "/user/signup")
    public String signup(@RequestParam("username") String username, @RequestParam("password") String password,
                         @RequestParam("phone") String phone, @RequestParam("email") String email) {
        String result = null;
        IdentityParameters identityParameters = new IdentityParameters();
        identityParameters.setName(username);
        identityParameters.setPasswd(password);
        identityParameters.setPhone(phone);
        identityParameters.setEmail(email);

        int signupCode = userService.signup(identityParameters);
        switch (signupCode) {
            case 0:
                result = "succ";
                break;
            case -2:
                result = "user exists";
                break;
            case -4:
                result = "password not  valid;";
                break;
            case -3:
                result = "uername not  valid;";
                break;
            case -5:
                result = "email not  valid;";
                break;
            case -6:
                result = "phone not  valid;";
                break;
        }
        return result;
    }

    @PostMapping(path = "/user/login")
    public Resp login(@RequestParam("phone") String phone, @RequestParam("email") String email, @RequestParam("password") String password) {
        IdentityParameters identityParameters = new IdentityParameters();
        identityParameters.setPasswd(password);
        identityParameters.setPhone(phone);
        identityParameters.setEmail(email);
        String result = null;
        try {
            userService.login(identityParameters);
            result = "success";
        } catch (UserException.InvalidParameterException paramException) {
            result = "paramEerror";
        } catch (UserException.UserNotFoundException userNotFoundException) {
            result = "userNotFound";
        } catch (UserException.InvalidPasswdException invalidPasswdException) {
            result = "invalidPasswdException";
        } catch (UserException userException) {
            userException.printStackTrace();
            result = "failure";
        }
        return Resp.newInstance(0, result);
    }


}
