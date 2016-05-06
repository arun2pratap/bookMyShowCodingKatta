package com.bookMyShow.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idnasi on 5/5/2016.
 */
public class Screen implements Comparable{
    private String name;
    private int capacity;
    private List<Timing> timings = new ArrayList<>();

    public Screen(String name, int capacity, List<Timing> timings){
        this.name = name;
        this.capacity = capacity;
        this.timings = timings;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        return name.equals(((Screen)o).getName()) ? 0:1;
    }
}
