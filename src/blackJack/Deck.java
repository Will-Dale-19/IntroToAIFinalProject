package blackJack;

import java.util.*;

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

        Collections.shuffle(returnList);

        return returnList;
    }

    public void shuffleDeck(){
        ArrayList<Card> shuffledDeck = new ArrayList<>(this.deck);

        Collections.shuffle(shuffledDeck);

        this.deck = new ArrayDeque<>();

        this.deck.addAll(shuffledDeck);
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
