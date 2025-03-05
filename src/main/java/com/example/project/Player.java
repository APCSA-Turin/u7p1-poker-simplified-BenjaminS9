package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand = new ArrayList<Card>();
    private ArrayList<Card> allCards = new ArrayList<Card>(); //the current community cards + hand
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
        if (allCards.size() < 5) {
            for (Card card : hand) {
            allCards.add(card);
            }
            for (Card card : communityCards) {
            allCards.add(card);
            }
        }

        if (isRoyal() && isFlush()) {
            return "Royal Flush";
        } else if (isStraight() && isFlush()) {
            return "Straight Flush";
        } else if (isQuads()) {
            return "Four of a Kind";
        } else if (isTrips() && isPair()) {
            return "Full House";
        } else if (isFlush()) {
            return "Flush";
        } else if (isStraight()) {
            return "Straight";
        } else if (isTrips()) {
            return "Three of a Kind";
        } else if (isTwoPair()) {
            return "Two Pair";
        } else if (isPair()) {
            return "A Pair";
        } else if (isHigh()) {
            return "High Card";
        } else {
            return "Nothing";
        }
    }

    public void sortAllCards() {
        for (int i = Utility.getRanks().length - 1; i >= 0; i--) {
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
            rankFrequency.add(0);
        }
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
            suitFrequency.add(0);
        }
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

    public boolean isStraight() {
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
        return isStraight;
    }

    public boolean isFlush() {
        ArrayList<Integer> suitFreq = findSuitFrequency();
        boolean isFlush = false;
        for (int freq : suitFreq) {
            if (freq == 5) {
                isFlush = true;
            }
        }
        return isFlush;
    }

    public boolean isRoyal() {
        ArrayList<Integer> rankFreq = findRankingFrequency();
        boolean isRoyal = true;
        for (int i = 8; i < rankFreq.size(); i++) {
            if (rankFreq.get(i) != 1) {
                isRoyal = false;
            }
        }
        return isRoyal;
    }

    public boolean isQuads() {
        ArrayList<Integer> rankFreq = findRankingFrequency();
        boolean isQuads = false;
        for (int freq : rankFreq) {
            if (freq == 4) {
                isQuads = true;
            }
        }
        return isQuads;
    }

    public boolean isTrips() {
        ArrayList<Integer> rankFreq = findRankingFrequency();
        boolean isTrips = false;
        for (int freq : rankFreq) {
            if (freq == 3) {
                isTrips = true;
            }
        }
        return isTrips;
    }

    public boolean isTwoPair() {
        ArrayList<Integer> rankFreq = findRankingFrequency();
        int pairCount = 0;
        for (int freq : rankFreq) {
            if (freq == 2) {
                pairCount++;
            }
        }
        if (pairCount == 2) {
            return true;
        }
        return false;
    }

    public boolean isPair() {
        ArrayList<Integer> rankFreq = findRankingFrequency();
        boolean isPair = false;
        for (int freq : rankFreq) {
            if (freq == 2) {
                isPair = true;
            }
        }
        return isPair;
    }

    public boolean isHigh() {
        boolean isHigh = false;
        String high = allCards.get(0).getRank();
        for (int i = 1; i < allCards.size(); i++) {
            if (Utility.getRankValue(allCards.get(i).getRank()) > Utility.getRankValue(high)) {
                high = allCards.get(i).getRank();
            }
        }
        for (Card card : hand) {
            if (card.getRank().equals(high)) {
                isHigh = true;
            }
        }
        return isHigh;
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.addCard(new Card("A", "♠"));
        player.addCard(new Card("6", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("5", "♣"));
        communityCards.add(new Card("2", "♠"));
        communityCards.add(new Card("3", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        System.out.println(handResult);
    }
}
