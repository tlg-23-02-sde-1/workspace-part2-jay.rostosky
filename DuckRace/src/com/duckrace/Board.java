package com.duckrace;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * This is a lookup table of ids to student names.
 * When a duck wins for the first time, we need to find out who that is.
 * This lookup table could be hardcoded with the data, or we could read the data 
 * in from a file, so that no code changes would need to be made for each cohort.
 *
 * Map<Integer,String> studentIdMap;
 * 
 * Integer    String
 * =======    ======
 *    1       John
 *    2       Jane
 *    3       Danny
 *    4       Armando
 *    5       Sheila
 *    6       Tess
 * 
 *
 * We also need a data structure to hold the results of all winners.
 * This data structure should facilitate easy lookup, retrieval, and storage by id.
 *
 * Map<Integer,DuckRacer> racerMap;
 *
 * Integer    DuckRacer
 * =======    =========
 *            id    name     wins   rewards
 *            --    ----     ----   -------
 *    5        5    Sheila     2    PRIZES, PRIZES
 *    6        6    Tess       1    PRIZES
 *   13       13    Zed        3    PRIZES, DEBIT_CARD, DEBIT_CARD
 *   17       17    Dom        1    DEBIT_CARD
 */

public class Board implements Serializable {
    // static variables and methods
    private static final String dataFilePath = "data/board.dat";
    private static final long serialVersionUID = 1;

    /*
     * If file data/board.dat exists, read that binary file into a Board object,
     * otherwise create and return new.
     */
    public static Board getInstance() {
        Board board = null;

        if (Files.exists(Path.of(dataFilePath))) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFilePath))) {
                board = (Board) in.readObject();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            board = new Board();
        }
        return board;
    }

    private final Map<Integer,String> studentIdMap = loadStudentIdMap();
    private final Map<Integer,DuckRacer> racerMap  = new TreeMap<>();

    /*
     * Updates the board (racerMap) by making a DuckRacer win.
     *
     * This could mean fetching an existing DuckRacer from racerMap,
     * or we might need to create a new DuckRacer, put it in the map,
     * and then make it win.
     *
     * NOTE: either way, it needs to "win".
     */
    public void update(int id, Reward reward) {
        DuckRacer racer = null;

        if (racerMap.containsKey(id)) {
            racer = racerMap.get(id);  // fetch existing
        }
        else {
            racer = new DuckRacer(id, studentIdMap.get(id));  // create new
            racerMap.put(id, racer);  // put in map (easy to forget this step)
        }
        // either way, it needs to "win" and we need to save the Board
        racer.win(reward);
        save();
    }

    /*
     * TODO: render the data "pretty," i.e., like we see in class.
     * This includes column headings, spacing it all out nicely, etc.
     */
    public void show() {
        if (racerMap.isEmpty()) {
            System.out.println("There are currently no winners on the board\n");
        }
        else {
            StringBuilder board = new StringBuilder();
            board.append("Duck Race Results\n");
            board.append("=================\n");
            board.append("\n");
            board.append("id    name      wins    rewards\n");
            board.append("--    ----      ----    -------\n");

            for (DuckRacer racer : racerMap.values()) {
                String rewardsString = racer.getRewards().toString();
                String rewards = rewardsString.substring(1, rewardsString.length() - 1);

                String row = String.format("%2d    %-9s %4d    %s\n",
                        racer.getId(), racer.getName(), racer.getWins(), rewards);
                board.append(row);
            }
            System.out.println(board);

            /*
            System.out.println();
            System.out.println("Duck Race Results");
            System.out.println("=================\n");
            System.out.println("id    name      wins    rewards");
            System.out.println("--    ----      ----    -------");

            Collection<DuckRacer> allRacers = racerMap.values();  // return Collection<V>
            for (DuckRacer racer : allRacers) {
                // System.out.println(racer);  // toString() automatically called
                System.out.printf("%s    %s        %s       %s\n",
                        racer.getId(), racer.getName(), racer.getWins(), racer.getRewards());
            }
            */
        }
    }

    public int maxId() {
        return studentIdMap.size();
    }

    /*
     * Writes the state of this Board object to binary file data/board.dat.
     */
    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            out.writeObject(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Populates studentIdMap from file conf/student-ids.csv
     */
    private Map<Integer,String> loadStudentIdMap() {
        // declare return value
        Map<Integer,String> idMap = new HashMap<>();

        try {
            List<String> lines = Files.readAllLines(Path.of("conf/student-ids.csv"));

            // for each line in lines, we want to split the string into "tokens"
            // then convert to Integer, String, so we can put this in the map
            for (String line : lines) {
                String[] tokens = line.split(",");  // ["1", "Caleb"]
                Integer id = Integer.valueOf(tokens[0]);
                String name = tokens[1];
                idMap.put(id, name);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return idMap;
    }
}