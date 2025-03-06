package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public  void initializeDeck(){ //hint.. use the utility class
        for (String suit : Utility.getSuits()) { //for each suit
            for (String rank : Utility.getRanks()) { //each rank is added for that suit
                cards.add(new Card(rank, suit));
            }
        }
    }

    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards); //I learned this from https://www.geeksforgeeks.org/how-to-shuffle-the-elements-of-array-in-java/
    }

    public  Card drawCard(){
        if (!isEmpty()) { //first checks if there are cards left to be drawn in the deck
            Card draw = cards.get(0);
            cards.remove(0); //removes the top card of the deck
            return draw; //returns the card that was removed
        }
        return null;
    }

    public  boolean isEmpty(){ //checks if there is at least one element in deck
        if (cards.size() < 1) {
            return true;
        }
        return false;
    }

   


}