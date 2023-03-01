package com.entertainment.client;

import com.entertainment.Television;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

class TelevisionClient {

    public static void main(String[] args) {
        Television tvA = new Television("Sony", 50);
        Television tvB = new Television("Sony", 50);
        Television tvC = new Television("Sony", 52);
        Television tvD = new Television("Sony", 12);

        // compare behavior of == and equals()
        System.out.println("tvA == tvB: "       + (tvA == tvB));        // always false
        System.out.println("tvA.equals(tvB): "  + tvA.equals(tvB));     // true now!
        System.out.println();

        // let's create a Set<Television> and see what happens
        Set<Television> tvs = new TreeSet<>();
        tvs.add(tvA);
        tvs.add(tvB);
        tvs.add(tvC);
        tvs.add(tvD);

        // show what's happening with the Set
        System.out.println("The size of the Set is: " + tvs.size());
        for (Television tv : tvs) {
            System.out.println(tv);
        }
    }
}