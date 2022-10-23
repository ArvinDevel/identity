package org.example.service;

import org.example.repository.UserRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    UserRepo userRepo;
    @InjectMocks
    UserServiceImpl userService;

    /**
     * equivalent to {@link @Before} in junit4
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    public static void classLevelInit() {
    }

    @Test
    public void testEmptyUsername() throws Exception {
        IdentityParameters params = new IdentityParameters();
        params.setName("");
        int result = userService.signup(params);
        assertEquals(-1, result);
    }


}