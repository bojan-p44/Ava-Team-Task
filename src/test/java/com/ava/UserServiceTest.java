package com.ava;

import com.ava.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testFindUserById(){

    }
}
