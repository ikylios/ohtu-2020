
package ohtu;

public class Player implements Comparable <Player> {
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setGoals(String goals) {
        this.goals = Integer.parseInt(goals);
    }

    public int getGoals() {
        return goals;
    }

    public void setAssists(String assists) {
        this.assists = Integer.parseInt(assists);
    }

    public int getAssists() {
        return assists;
    }

    
    @Override
    public String toString() {
        int score = getAssists() + getAssists();
        return name + " " + team + " " + goals + " + " + assists + " = " + score;
    }

    @Override
    public int compareTo(Player p) {
        return Integer.compare(p.getAssists()+p.getGoals(), this.getAssists()+this.getGoals());
    }
}
