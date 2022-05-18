package com.codeworks.mvc.new_features.strings;

import java.util.stream.Stream;

public class NewStringMethods {
    public String strip(String value) {
        return value.strip();
    }

    public String stripLeading(String value) {
        return value.stripLeading();
    }

    public String stripTrailing(String value) {
        return value.stripTrailing();
    }

    public boolean isBlank(String value) {
        return value.isBlank();
    }

    public String repeat(String value, Integer n) {
        return value.repeat(n);
    }

    public Stream<String> splitToLines(String value) {
        return value.lines();
    }
}
