package com.meancat.chm;

import com.google.common.base.Stopwatch;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SingleThreaded {
    private static final int WRITE_COUNT = 1000;
    private static final int READ_COUNT = 1_000_000;

    // jit
    public long hashSum;

    private List<String> knownKeys = new ArrayList<>();
    private Random r = new Random();

    public void runTest(Map<String, Object> map) {
//        holder = new HashSet<>();

        // populate list of keys first outside of timer
        knownKeys = new ArrayList<>();
        for(int i=0; i < WRITE_COUNT; i++) {
            String k = String.valueOf(r.nextLong());
            knownKeys.add(k);

        }
        Stopwatch sw = Stopwatch.createStarted();
        doWrite(map);
        doRead(map);
        sw = sw.stop();

        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }

    private void doWrite(Map<String, Object> map) {
        // write some values
        for(String k : knownKeys) {
            map.put(k, new Object());
        }
    }

    private void doRead(Map<String, Object> map) {
        // read a lot of values
        for (int i=0; i < READ_COUNT; i++) {
            String k = knownKeys.get(r.nextInt(knownKeys.size()));
            Object o = map.get(k);
            hashSum += o.hashCode();
        }
    }
}
