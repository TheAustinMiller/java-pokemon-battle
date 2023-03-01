/*
    Pokemon Battle Class
    AJ Miller
    10/21/2020
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        intro();
        String pokemonType = input.next();
        while (!isValidType(pokemonType)){
            System.out.println("Please choose a valid pokemon...");
            System.out.print("I CHOOSE ");
            pokemonType = input.next();
        }

        pokemonType = pokemonType.toUpperCase();
        String type = "";
        switch(pokemonType) {
            case "SQUIRTLE":
                type = "water";
                break;
            case "CHARMANDER":
                type = "fire";
                break;
            case "BULBASAUR":
                type = "grass";
                break;
        }
        Pokemon pokemon = new Pokemon(type);
        Ditto ditto = new Ditto();
        int round = 1;
        System.out.println("BEGIN BATTLE!");
        while (!someoneWon(pokemon, ditto)){
            report(pokemon, ditto);
            System.out.println(battle(pokemon, ditto, round, input));
            System.out.println();
            round++;
        }
    }

    private static void intro(){
        System.out.println("WELCOME TO AJ'S POKEMON BATTLE SIMULATOR V1!\n");
        System.out.println("PLEASE CHOOSE YOUR POKEMON TO BATTLE THE SHAPE-SHIFTING DITTO WITH:");
        System.out.println("\t(1) Squirtle -> Moves: {Tackle, Bubble, Hunt, Evolve}");
        System.out.println("\t(2) Charmander -> Moves: {Scratch, Ember, Hunt, Evolve}");
        System.out.println("\t(3) Bulbasaur -> Moves: {Headbutt, VineWhip, Hunt, Evolve}\n");
        System.out.print("I CHOOSE ");
    }

    private static void report(Pokemon pokemon, Ditto ditto){
        System.out.println();
        System.out.println("Your health: " + pokemon.getHitPoints());
        System.out.println("Ditto's health: " + ditto.getHitPoints());
        System.out.println("Ditto's current type: " + ditto.getType().toUpperCase());
        System.out.println();
    }

    private static boolean someoneWon(Pokemon pokemon, Ditto ditto){
        boolean flag = false;
        if (pokemon.getHitPoints() <= 0){
            flag = true;
        }
        if (ditto.getHitPoints() <= 0){
            flag = true;
        }
        return flag;
    }

    private static String battle(Pokemon pokemon, Ditto ditto, int round, Scanner input){
        System.out.println("BEGIN ROUND " + round + "!");
        System.out.println("What move will you choose");
        printMoves(pokemon);
        System.out.print("I CHOOSE ");
        String move = input.next().toLowerCase();
        while(!pokemon.isValidMove(move)){
            System.out.println("Please enter a valid move...");
            System.out.print("I CHOOSE ");
            move = input.next();
        }
        int pokemonDamage = pokemon.attack(move, ditto.getType());
        ditto.takeDamage(pokemonDamage);
        if (someoneWon(pokemon, ditto)){
            System.out.println();
            System.out.println("YOUR " + pokemon.getName().toUpperCase() + " HAS DEFEATED THE DITTO IN " + round + " ROUNDS!");
             return scoreCalculator(pokemon, ditto, round);
        }
        System.out.println();
        int dittoAttack = ditto.attack(ditto.getType(), pokemon.getType(), pokemon.getHitPoints());
        pokemon.takeDamage(dittoAttack);
        if (someoneWon(pokemon, ditto)){
            System.out.println();
            return "YOUR " + pokemon.getName().toUpperCase() + " HAS BEEN DEFEATED BY THE DITTO";
        }
        return "END OF ROUND "+ round + "!";
    }

    private static void printMoves(Pokemon pokemon){
        if (pokemon.getType().equals("water")){
            System.out.println(pokemon.getName().toUpperCase()+" -> Moves: {Tackle, Bubble, Hunt, Evolve}");
        }
        if (pokemon.getType().equals("fire")){
            System.out.println(pokemon.getName().toUpperCase()+" -> Moves: {Scratch, Ember, Hunt, Evolve}");
        }
        if (pokemon.getType().equals("grass")){
            System.out.println(pokemon.getName().toUpperCase()+" -> Moves: {Headbutt, VineWhip, Hunt, Evolve}");
        }
    }

    private static String scoreCalculator(Pokemon pokemon, Ditto ditto, int round){
        double rounder = (1.0/round);
        double difference = pokemon.getHitPoints() - ditto.getHitPoints();
        return "YOUR SCORE: " + (int)((rounder * difference) * 1000.0);
    }

    private static boolean isValidType(String t){
        boolean flag = false;
        if (t.equalsIgnoreCase("squirtle")){
            flag = true;
        }
        if (t.equalsIgnoreCase("charmander")){
            flag = true;
        }
        if (t.equalsIgnoreCase("bulbasaur")){
            flag = true;
        }
        return flag;
    }
}
