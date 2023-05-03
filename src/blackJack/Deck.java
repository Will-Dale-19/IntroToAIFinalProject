package blackJack;

import java.util.ArrayDeque;
import java.util.ArrayList;

import blackJack.Card.*;

public class Deck {

    private ArrayDeque<Card> deck = new ArrayDeque<>();

    public Deck(){
        for(Suit suit : Card.Suit.values()){
            deck.addAll(buildSuit(suit));
        }
    }

    private ArrayList<Card> buildSuit(Suit suit){
        ArrayList<Card> returnList = new ArrayList<>();
        for(int i = 2; i < 11; i++){
            Card card = new Card(suit, "" + i);
            returnList.add(card);
        }
        returnList.add(new Card(suit, "J"));
        returnList.add(new Card(suit, "Q"));
        returnList.add(new Card(suit, "K"));
        returnList.add(new Card(suit, "A"));

        return returnList;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Card card : deck){
            s.append(card.toString()).append("\n");
        }
        return s.toString();
    }


}
