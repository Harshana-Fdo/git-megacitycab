package com.example.megacitycab.factory;



import com.example.megacitycab.model.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserFactoryTest {

    @Test
    public void inserRegisitrasiontDetails(){

        User user = new User();
        assertNotEquals(null, user);
    }

}