package com.meancat.chm;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class App
{

    private static final int ITERATIONS = 15;

    public static void main( String[] args )
    {
        long dontcare = 0L;
        System.out.println("ConcurrentHashMap:");
        for(int i=0; i < ITERATIONS; i++) {
            SingleThreaded t = new SingleThreaded();
            t.runTest(new ConcurrentHashMap<>());
            dontcare += t.hashSum;
        }

        System.out.println("HashMap:");
        for(int i=0; i < ITERATIONS; i++) {
            SingleThreaded t = new SingleThreaded();
            t.runTest(new HashMap<>());
            dontcare += t.hashSum;
        }
        System.out.println("magic number: " + dontcare);
    }
}
