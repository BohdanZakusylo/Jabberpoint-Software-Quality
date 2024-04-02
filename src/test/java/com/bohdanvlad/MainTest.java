package com.bohdanvlad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{

    @Test
    void testMaven()
    {
        assertEquals(2, Main.testMaven());
    }

    @Test
    void testDtap()
    {
        assertEquals(3, Main.testDtap());
    }
}
