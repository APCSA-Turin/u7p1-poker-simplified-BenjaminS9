package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int p1Value = Utility.getHandRanking(p1Hand);
        int p2Value = Utility.getHandRanking(p2Hand);
        if (p1Value > p2Value) { //player 1 wins if they have the higher rank value
            return "Player 1 wins!";
        } else if (p2Value > p1Value) { //player 2 wins if they have the higher rank value
            return "Player 2 wins!";
        } else { //in the case of a tie, the highest value card in the hand is checked
            int p1Highest = 0;
            for (Card card : p1.getHand()) { //finds the highest card in player 1's hand
                if (p1Highest < Utility.getRankValue(card.getRank())) {
                    p1Highest = Utility.getRankValue(card.getRank());
                }
            }
            for (Card card : p2.getHand()) { //compares the highest card in player 1's hand to the cards player 2 has
                if (Utility.getRankValue(card.getRank()) > p1Highest) { //if player 2 has a higher card, he wins
                    return "Player 2 wins!";
                } else if (Utility.getRankValue(card.getRank()) == p1Highest) { //if they have the same value cards, it's a tie
                    return "Tie!";
                }
            }
            return "Player 1 wins!";
        }
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}