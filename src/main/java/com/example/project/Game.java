package com.example.project;
import java.util.ArrayList;
import java.util.Scanner;


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
        Scanner scan = new Scanner(System.in);
        System.out.println("------------Welcome to Simplified Poker------------");
        System.out.print("Start the game by typing start: ");
        String response = scan.nextLine();
        if (response.equals("start")) { //starts the game if the player types "start"
            //initializes the deck and players
            Deck deck = new Deck();
            Player p1 = new Player();
            Player p2 = new Player();
            ArrayList<Card> communityCards = new ArrayList<Card>();
            while (response.equals("start") && deck.getCards().size() >= 7) { //continues the game until the player says otherwise or there are not enough cards for a round
                for (int i = 0; i < 3; i++) { //adds 3 cards to community cards
                    communityCards.add(deck.drawCard());
                }
                for (int i = 0; i < 2; i++) { //adds 2 cards to both players' hands
                    p1.addCard(deck.drawCard());
                    p2.addCard(deck.drawCard());
                }

                System.out.println("Community Cards: " + communityCards);
                System.out.println("P1 Hand: " + p1.getHand());
                System.out.println("P2 Hand: " + p2.getHand());
                String p1Hand = p1.playHand(communityCards); //determines the hand ranking of player 1
                String p2Hand = p2.playHand(communityCards); //determines the hand ranking of player 2
                System.out.println("Player 1 has " + p1Hand);
                System.out.println("Player 2 has " + p2Hand);
                String winner = determineWinner(p1, p2, p1Hand, p2Hand, communityCards);
                System.out.println(winner); //displays the winner of the round

                for (int i = 2; i >= 0; i--) { //removes all cards from community cards
                    communityCards.remove(i);
                }
                p1.removeAllCards(); //removes cards from player 1's hand
                p2.removeAllCards(); //removes cards from player 2's hand

                System.out.println("---------------------------------------------------");
                System.out.print("Type start to start the next round: "); //prompts user to start next round
                response = scan.nextLine();
            }
            System.out.println("Game over! You ran out of cards in the deck or you have stopped the game.");
        }
    }
        
    public static void main(String[] args) {
        play();
    }
}