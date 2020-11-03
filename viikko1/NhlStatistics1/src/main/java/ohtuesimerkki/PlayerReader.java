package ohtuesimerkki;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerReader implements Reader {

    private Scanner scanner;

    public PlayerReader(String pageUrl) {
        try {
            URL url = new URL(pageUrl);
            scanner = new Scanner(url.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        while (scanner.hasNextLine()) {
            String[] parts =  scanner.nextLine().split(";");            
            
            if (parts.length > 3) {
                players.add(new Player(parts[0].trim(), parts[1], extractInt(parts[3]), extractInt(parts[4])));
            }
        }
/*
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri",   "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));
*/ 
        return players;
    }

    private int extractInt(String str) {
        return Integer.parseInt(str.trim());
    }
}
