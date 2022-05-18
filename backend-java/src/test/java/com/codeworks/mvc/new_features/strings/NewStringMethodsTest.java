package com.codeworks.mvc.new_features.strings;

import com.codeworks.mvc.new_features.strings.NewStringMethods;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class NewStringMethodsTest {
    private NewStringMethods newStringMethods;

    @Before
    public void setUp() {
        newStringMethods = new NewStringMethods();
    }

    @Test
    public void should_strip_string() {
        Assert.assertEquals("", newStringMethods.strip("     "));
        Assert.assertEquals("a", newStringMethods.strip("    a     "));
    }

    @Test
    public void should_strip_leading_string() {
        Assert.assertEquals("", newStringMethods.stripLeading("     "));
        Assert.assertEquals("a     ", newStringMethods.stripLeading("      a     "));
    }

    @Test
    public void should_strip_tailing_string() {
        Assert.assertEquals("", newStringMethods.stripTrailing("     "));
        Assert.assertEquals("      a", newStringMethods.stripTrailing("      a      "));
    }

    @Test
    public void should_check_if_string_is_blank() {
        Assert.assertTrue(newStringMethods.isBlank(""));
        Assert.assertTrue(newStringMethods.isBlank("      "));
        Assert.assertFalse(newStringMethods.isBlank("     a"));
    }

    @Test
    public void should_repeat_string() {
        Assert.assertEquals("aaaa", newStringMethods.repeat("a", 4));
    }

    @Test
    public void should_split_to_lines() {
        Assert.assertArrayEquals(List.of("a", "b", "c").toArray(), newStringMethods.splitToLines("a\nb\nc\n").toArray());
    }
}
