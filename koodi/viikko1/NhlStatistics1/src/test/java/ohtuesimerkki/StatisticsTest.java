package ohtuesimerkki;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  

    @Test
    public void searchReturnsPlayer() {
        Player Semenko = new Player("Semenko", "EDM", 4, 12);
        
        Player result = stats.search("e");

        assertEquals(Semenko.getName(), result.getName());
    }

    @Test
    public void searchReturnsNull() {
        Player result = stats.search("timo");

        assertEquals(null, result);
    }
    
    @Test
    public void selectsTeam() {
        ArrayList<Player> edm = new ArrayList<>();
        edm.add(new Player("Semenko", "EDM", 4, 12));
        edm.add(new Player("Kurri",   "EDM", 37, 53));
        edm.add(new Player("Gretzky", "EDM", 35, 89));

        List<Player> result = stats.team("EDM");

        boolean allGood = true;
        for (int i = 0; i < edm.size(); i++) {
            if (edm.get(i).getName() != result.get(i).getName()) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);

    //    assertEquals(edm.get(0).getName(), result.get(0).getName());
    }

    @Test
    public void selectsTopScorers() {
        ArrayList<Player> tops = new ArrayList<>();
        
        tops.add(new Player("Gretzky", "EDM", 35, 89));
        tops.add(new Player("Lemieux", "PIT", 45, 54));
        tops.add(new Player("Yzerman", "DET", 42, 56));
        tops.add(new Player("Kurri",   "EDM", 37, 53));

        List<Player> result = stats.topScorers(3);

        boolean allGood = true;

        for (int i = 0; i < tops.size(); i++) {
            if (tops.get(i).getName() != result.get(i).getName()) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

}