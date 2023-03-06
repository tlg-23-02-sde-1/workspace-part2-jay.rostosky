package com.duckrace;

import java.util.List;
import static com.duckrace.Reward.*;  // OK to use * for static imports

class DuckRacerTest {

    public static void main(String[] args) {
        DuckRacer racer5 = new DuckRacer(5, "Chris");
        System.out.println(racer5);  // toString() automatically called

        racer5.win(DEBIT_CARD);
        racer5.win(DEBIT_CARD);
        racer5.win(PRIZES);
        racer5.win(DEBIT_CARD);

        System.out.println(racer5);
        System.out.println();

        // This is a read-only, yet "live" view of the underlying List<Reward> in the DuckRacer
        List<Reward> rewards = racer5.getRewards();  // 4 in here now
        System.out.println(rewards);

        // rewards.add(PRIZES);  // UnsupportedOperationException
        // rewards.add(PRIZES);

        racer5.win(PRIZES);
        racer5.win(PRIZES);

        System.out.println(rewards);  // you'll see 6 now
    }
}