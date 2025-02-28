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
        for (Card card : hand) {
            allCards.add(card);
        }
        for (Card card : communityCards) {
            allCards.add(card);
        }
        sortAllCards();

        ArrayList<Integer> rankFreq = findRankingFrequency();
        int count = 0;
        boolean isStraight = false;
        for (int freq : rankFreq) {
            if (freq == 1) {
                count++;
                if (count == 5) {
                    isStraight = true;
                }
            } else {
                count = 0;
            }
        }

        ArrayList<Integer> suitFreq = findSuitFrequency();
        boolean isFlush = false;
        for (int freq : suitFreq) {
            if (freq == 5) {
                isFlush = true;
            }
        }

        
    }

    public void sortAllCards() {
        for (int i = Utility.getRanks().length; i >= 0; i--) {
            for (int j = 0; j < allCards.size(); j++) {
                if (allCards.get(j).getRank().equals(Utility.getRanks()[i])) {
                    allCards.add(0, allCards.get(j));
                    allCards.remove(j + 1);
                    j--;
                }
            }
        }
    }

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
