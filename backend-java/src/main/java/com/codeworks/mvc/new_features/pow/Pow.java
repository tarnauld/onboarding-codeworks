package com.codeworks.mvc.new_features.pow;

public class Pow {

    @FunctionalInterface
    interface Executable {
        Integer pow(Integer value);
    }

    public Integer execute(Integer value) {
        Executable e = (var v) -> v * v;
        return e.pow(value);
    }
}
