package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){      

    }

    public void SortCards(){Arrays.sort(allCards);} //I learned this from https://www.geeksforgeeks.org/arrays-sort-in-java-with-examples/

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> rankFrequency = new ArrayList<Integer>();
        for (int i = 0; i < Utility.getRanks().length; i++) {
            int count = 0;
            for (Card card : allCards) {
                if (card.getRank().equals(Utility.getRanks()[i])) {
                    count++;
                }
            }
            rankFrequency.set(i, count);
        }
        return rankFrequency;
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFrequency = new ArrayList<Integer>();
        for (int i = 0; i < Utility.getSuits().length; i++) {
            int count = 0;
            for (Card card : allCards) {
                if (card.getSuit().equals(Utility.getSuits()[i])) {
                    count++;
                }
            }
            suitFrequency.set(i, count);
        }
        return suitFrequency;
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
