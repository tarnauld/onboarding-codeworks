package com.codeworks.mvc.new_features.toarray;

import com.codeworks.mvc.new_features.toarray.ToArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ToArrayTest {
    private ToArray toArray;

    @Before
    public void setUp() {
        toArray = new ToArray();
    }

    @Test
    public void should_execute() {
        List<String> list = List.of("foo", "bar", "baz");

        var array = toArray.execute(list);
        Object[] o = new Object[] {"foo", "bar", "baz"};

        Assert.assertArrayEquals(o, array);
    }
}
