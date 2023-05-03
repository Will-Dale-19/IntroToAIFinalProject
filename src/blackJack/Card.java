package blackJack;

public class Card {
    private final Suit suit;
    private int value;
    private final String face;

    public enum Suit {
        SPADE("♠"),
        DIAMOND("♦"),
        HEART("♥"),
        CLUB("♣");
        private final String val;
        Suit(String suit){
            val = suit;
        };
    }

    public Card(Suit suit, String face){
        this.suit = suit;

        this.face = face;
        switch (face) {
            case "2", "3", "4", "5", "6", "7", "8", "9", "10" -> this.value = Integer.parseInt(face);
            case "J", "Q", "K" -> this.value = 10;
            case "A" -> this.value = 11;
        }
    }

    public int getValue(){
        return value;
    }

    @Override
    public String toString(){
        return suit.val + face ;
    }



}
