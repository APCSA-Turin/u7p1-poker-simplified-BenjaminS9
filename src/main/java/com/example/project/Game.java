package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int p1Value = Utility.getHandRanking(p1Hand);
        int p2Value = Utility.getHandRanking(p2Hand);
        if (p1Value > p2Value) {
            return "Player 1 wins!";
        } else if (p2Value > p1Value) {
            return "Player 2 wins!";
        } else {
            return "Tie!";
        }
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}