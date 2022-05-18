package com.codeworks.mvc.new_features.pow;

import com.codeworks.mvc.new_features.pow.Pow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PowTest {
    private Pow pow;

    @Before
    public void setUp() {
        pow = new Pow();
    }

    @Test
    public void should_execute() {
        Assert.assertEquals(Integer.valueOf(4), pow.execute(2));
    }
}
