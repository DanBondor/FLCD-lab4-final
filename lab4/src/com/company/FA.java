package com.company;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FA {
    private String initState;
    private List<String> finalStates;
    private List<String> alphabet;
    private List<String> states;
    private Map<Pair<String, String>, String> transitions;

    public FA()
    {
        this.finalStates = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.states = new ArrayList<>();
        this.transitions = new HashMap<>();
    }

    public boolean verifyDFA()
    {
        ArrayList<Pair<String, String>> delta= new ArrayList<>();
        for(Pair<String, String> pair: this.transitions.keySet()){
            String state = pair.getKey();
            Pair<String, String> deltaPair = new Pair(state, this.transitions.get(pair));
            if(delta.contains(deltaPair))
                return false;
            delta.add(deltaPair);
        }
        return true;
    }

    public boolean checkSequence(String sequence) {
        String currentState = this.initState;
        for (int i = 0; i < sequence.length(); i++) {
            int found = 0;
            for (Pair<String, String> pair : this.transitions.keySet()) {
                String s1 = sequence.substring(i, i + 1);
                String s2 = this.transitions.get(pair);
                if (pair.getKey().equals(currentState) && s2.equals(s1)) {
                    currentState = pair.getValue();
                    found = 1;
                }
            }
            if (found == 0) {
                return false;
            }
        }
        if (this.finalStates.contains(currentState))
            return true;
        return false;
    }

    public void readFromFile() throws FileNotFoundException {
        File file = new File("C:\\Users\\Dan_B\\Documents\\FLC\\lab4\\src\\com\\company\\FA.in");
        Scanner scanner = new Scanner(file);

        states = Arrays.asList(scanner.nextLine().split(" "));
        alphabet = Arrays.asList(scanner.nextLine().split(" "));
        initState = scanner.nextLine();
        finalStates = Arrays.asList(scanner.nextLine().split(" "));

        while(scanner.hasNext())
        {
            String[] aux = scanner.nextLine().split(" ");
            transitions.put(new Pair<>(aux[0], aux[1]), aux[2]);
        }
    }

    public String printStates()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object state: this.states)
        {
            stringBuilder.append(state);
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

    public String printAlphabet()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object a: this.alphabet)
        {
            stringBuilder.append(a);
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

    public String printInitState()
    {
        return this.initState;
    }

    public String printFinalStates()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object state: this.finalStates)
        {
            stringBuilder.append(state);
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

    public String printTransitions()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object key: this.transitions.keySet())
        {
            stringBuilder.append(key).append(" ").append(transitions.get(key));
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }
}
