package QLearning.action;

import QLearning.Stand;
import agents.states.BlindState;
import agents.states.State;
import blackJack.Card;
import blackJack.Deck;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Random;

public class QTable {
    private HashMap<State, ArrayList<Action>> qTable = new HashMap<>();
    private final int numUniqueCards = 13;

    public QTable(){
        // For all possible states, generate them.
        // There are states of 1 through 5 cards.

        //TODO Change back to 6
        for (int i = 1; i < 6; i++) {
            ArrayList<State> tempStates = new ArrayList<>();
            // The state size is the number of possible states at that
            // card size. Hand of 1 has 13 possibilities, hand of 2 has 13 * 13 = 169, etc.

            // state space is just list of ints, generate all 100,000 in order.
            // not even all 100,000, 1,2,3 = 3, 2, 1.
            while(qTable.size() < Math.pow(10, i)) {
                ArrayList<Integer> newHand = generateNewHand(i);
                State blindState = new BlindState(newHand);
                ArrayList<Action> actionList = new ArrayList<>();
                actionList.add(new Hit());
                actionList.add(new Stand());
                if (!qTable.containsKey(blindState)) {
                    qTable.put(blindState, actionList);
                    tempStates.add(blindState);
                }
                System.out.println(qTable.size());
            }
            //TODO Debug, remove.

        }
    }

    /**
     * Generate a new hand of a specific size.
     * @param handSize The size of the hand to be created.
     * @return The generated hand.
     */
    private ArrayList<Integer> generateNewHand(int handSize){
        ArrayList<Integer> newHand = new ArrayList<>();
        Deck deck = new Deck();



        return newHand;
    }



}
