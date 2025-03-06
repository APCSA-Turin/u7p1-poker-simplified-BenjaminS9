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

    public void addCard(Card c){ //adds the card to the hand
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){ 
        if (allCards.size() < 5) { //makes sure allCards isn't already filled with 5 cards
            for (Card card : hand) { //adds all cards from hand to allCards
            allCards.add(card);
            }
            for (Card card : communityCards) { //adds all cards from the community cards to allCards
                allCards.add(card);
            }
        }

        if (isRoyal() && isFlush()) { //checks for a flush and the highest straight possible
            return "Royal Flush";
        } else if (isStraight() && isFlush()) { //checks for a straight and a flush
            return "Straight Flush";
        } else if (isQuads()) { //checks for 4 same cards
            return "Four of a Kind";
        } else if (isTrips() && isPair()) { //checks for a pair and a triple
            return "Full House";
        } else if (isFlush()) { //checks for a flush
            return "Flush";
        } else if (isStraight()) { //checks for a straight
            return "Straight";
        } else if (isTrips()) { //checks for 3 of the same cards
            return "Three of a Kind";
        } else if (isTwoPair()) { //checks for 2 sets of 2 same cards
            return "Two Pair";
        } else if (isPair()) { //checks for 2 same cards
            return "A Pair";
        } else if (isHigh()) { //checks if the hand has the highest card
            return "High Card";
        } else {
            return "Nothing";
        }
    }

    public void sortAllCards() { //sorts cards in rank order
        for (int i = Utility.getRanks().length - 1; i >= 0; i--) { //iterates through all possible ranks
            for (int j = 0; j < allCards.size(); j++) { //iterates through each card in allCards
                if (allCards.get(j).getRank().equals(Utility.getRanks()[i])) { //checks if the card is equal to the rank
                    allCards.add(0, allCards.get(j)); //adds a new instance card to the beginning of the list
                    allCards.remove(j + 1); //removes the duplicate copy of the original card
                    j--; //moves the index back one to make up for the newly inserted card
                }
            }
        }
    }

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> rankFrequency = new ArrayList<Integer>();
        for (int i = 0; i < Utility.getRanks().length; i++) { //initializes the list with 0 at each index
            rankFrequency.add(0);
        }
        for (int i = 0; i < Utility.getRanks().length; i++) {
            int count = 0;
            for (Card card : allCards) { //iterates through all cards
                if (card.getRank().equals(Utility.getRanks()[i])) { //checks if the card's rank is equal to the currently checked rank
                    count++;
                }
            }
            rankFrequency.set(i, count); //adds the amount of cards that have a certain rank
        }
        return rankFrequency;
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFrequency = new ArrayList<Integer>();
        for (int i = 0; i < Utility.getSuits().length; i++) { //initilizes the suits list with 0s
            suitFrequency.add(0);
        }
        for (int i = 0; i < Utility.getSuits().length; i++) { //iterates through each suit
            int count = 0;
            for (Card card : allCards) { //iterates through all cards
                if (card.getSuit().equals(Utility.getSuits()[i])) { //checks if the checked suit has the same suit as a card in hand
                    count++;
                }
            }
            suitFrequency.set(i, count); //adds the amount of times a suit appears in the hand
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
                if (count == 5) { //checks if five consecutive ranks has appeared only once
                    isStraight = true;
                }
            } else { //resets the count to 0 if a rank does not occur consecutively
                count = 0;
            }
        }
        return isStraight;
    }

    public boolean isFlush() {
        ArrayList<Integer> suitFreq = findSuitFrequency();
        boolean isFlush = false;
        for (int freq : suitFreq) {
            if (freq == 5) { //checks if each of the four suits has appeared 5 times between all the cards
                isFlush = true;
            }
        }
        return isFlush;
    }

    public boolean isRoyal() {
        ArrayList<Integer> rankFreq = findRankingFrequency();
        boolean isRoyal = true;
        for (int i = 8; i < rankFreq.size(); i++) { //iterates through the highest 5 ranks, from 10 to A
            if (rankFreq.get(i) != 1) { //checks if any of the ranks aren't present or appear more than once to determine a straight
                isRoyal = false;
            }
        }
        return isRoyal;
    }

    public boolean isQuads() {
        ArrayList<Integer> rankFreq = findRankingFrequency();
        boolean isQuads = false;
        for (int freq : rankFreq) {
            if (freq == 4) { //checks if any rank appears exactly four times
                isQuads = true;
            }
        }
        return isQuads;
    }

    public boolean isTrips() {
        ArrayList<Integer> rankFreq = findRankingFrequency();
        boolean isTrips = false;
        for (int freq : rankFreq) {
            if (freq == 3) { //checks if any rank appears exactly three times
                isTrips = true;
            }
        }
        return isTrips;
    }

    public boolean isTwoPair() {
        ArrayList<Integer> rankFreq = findRankingFrequency();
        int pairCount = 0;
        for (int freq : rankFreq) {
            if (freq == 2) { //checkes if any rank appears exactly twice
                pairCount++;
            }
        }
        if (pairCount == 2) { //once two ranks have appeared exactly twice, it is a two pair
            return true;
        }
        return false;
    }

    public boolean isPair() {
        ArrayList<Integer> rankFreq = findRankingFrequency();
        boolean isPair = false;
        for (int freq : rankFreq) {
            if (freq == 2) { //checks for any rank to have appeared twice
                isPair = true;
            }
        }
        return isPair;
    }

    public boolean isHigh() {
        boolean isHigh = false;
        String high = allCards.get(0).getRank(); //sets the highest rank to the first card
        for (int i = 1; i < allCards.size(); i++) { //goes through every card other than the first
            if (Utility.getRankValue(allCards.get(i).getRank()) > Utility.getRankValue(high)) { //checks if the new card is higher than the current card
                high = allCards.get(i).getRank();
            }
        }
        for (Card card : hand) { //checks if the highest card between all cards is from the player's hand
            if (card.getRank().equals(high)) {
                isHigh = true;
            }
        }
        return isHigh;
    }
}
