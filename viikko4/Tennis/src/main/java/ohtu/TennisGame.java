package ohtu;

import org.checkerframework.checker.units.qual.A;

public class TennisGame {
    
    private int player1_score = 0;
    private int player2_score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1_score++;
        else
            player2_score++;
    }
    
    public String getScore() {
        String result = "";

        if (player1_score >= 4 || player2_score >= 4) {
            return endGameChecker();
        }

        String player1Call = scoreToCall(player1_score);
        String player2Call = scoreToCall(player2_score);

        if (player1Call.equals(player2Call)) {
            player2Call = "All";
        }

        result = player1Call + "-" + player2Call;
        
        if (result.contains("Win")) {
            return "Win for " + winningParty();
        }
        return result;  
    }

    public String scoreToCall(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return endGameChecker();
        }
    }

    public String winningParty() {
        if (player1_score > player2_score) {
            return "player1";
        } 
        return  "player2";
    }

    public String endGameChecker() {
        int scoreDifference = player1_score - player2_score;

        if (Math.abs(scoreDifference) >= 2) {
            return "Win for " + winningParty();
        } else if (Math.abs(scoreDifference) == 1) {
            return "Advantage " + winningParty();
        } else {
            return "Deuce";
        }
    }

}