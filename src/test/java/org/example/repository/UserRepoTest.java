package org.example.repository;

import org.example.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
class UserRepoTest {
    @Resource
    private UserRepo userRepo;

    /**
     * verifies that the user exists in the repository or not
     *
     * @throws Exception
     */
    @Test
    public void testUserRepo() throws Exception {
        User user = userRepo.findUserByEmail("John Smith");
        assertNull(user);
        Assertions.assertEquals(null, user);
    }

}