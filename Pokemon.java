/*
    Pokemon Class
    AJ Miller
    10/21/2020
 */
// TYPE 1 - WATER
// TYPE 2 - FIRE
// TYPE 3 - GRASS
/**
 * Creates the a Pokemon with moves and hit points
 */
public class Pokemon {
    private static final int OUT_OF_TEN = 10;
    private static final int OUT_OF_HUNDRED = 100;
    private int hitPoints;
    private String type;
    private String name;
    private int evolutionLevel;
    private Die die = new Die(OUT_OF_TEN);
    private Die critDie = new Die(OUT_OF_HUNDRED);
    private static final int DEFAULT_HEALTH = 80;
    private static int adder = 0;
    private static int adder2 = 0;
    private static boolean isCrit = false;
    private Die evolveDie = new Die(2);

    public Pokemon(String type){
        hitPoints = DEFAULT_HEALTH;
        this.type = type;
        evolutionLevel = 1;
        switch(type) {
            case "water":
                name = "Squirtle";
                break;
            case "fire":
                name = "Charmander";
                break;
            case "grass" :
                name = "Bulbasaur";
                break;
        }
    }

    public String getName(){
        return name;
    }

    public void takeDamage(int damage){
        hitPoints -= damage;
    }

    public void heal(){
        hitPoints = DEFAULT_HEALTH;
    }

    public int getHitPoints(){
        return hitPoints;
    }

    public String getType(){
        return type;
    }

    public int attack(String move, String enemyType){
        int damage = 0;
        // WATER TYPE
        if (type.equalsIgnoreCase("water")){
            damage = waterAttack(move, enemyType);
        }
        // FIRE TYPE
        if (type.equalsIgnoreCase("fire")){
            damage = fireAttack(move, enemyType);
        }
        // GRASS TYPE
        if (type.equalsIgnoreCase("grass")){
            damage = grassAttack(move, enemyType);
        }
        return damage;
    }

    private int waterAttack(String move, String enemyType) {
        // Tackle, Bubble, and Hunt
        int damage = 0;
        // Tackle
        if (move.equalsIgnoreCase("tackle")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED TACKLE!");
            die.roll();
            if (die.getCurrentValue() != 1) {
                damage = 10;
                damage = rollCritDie(damage);
                damage = damage + adder + adder2;
                System.out.println(name.toUpperCase() + " TACKLED THE DITTO AND DID "+ damage +" DAMAGE!");
                if (isCrit){
                    System.out.println("CRITICAL HIT!");
                    isCrit = false;
                }
            } else {
                System.out.println(name.toUpperCase() +"'S TACKLE MISSED!");
            }
        }
        if (move.equalsIgnoreCase("bubble")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED BUBBLE!");
            die.roll();
            if (die.getCurrentValue() != 1 && die.getCurrentValue() != 2 && die.getCurrentValue() != 3) {
                if (enemyType.equalsIgnoreCase("fire")) {
                    damage = 20;
                    damage = rollCritDie(damage);
                    damage = damage + adder + adder2;
                    System.out.println(name.toUpperCase() + " DRENCHED THE DITTO FOR " + damage + " DAMAGE!");
                    System.out.println("IT'S SUPER EFFECTIVE!");
                    if (isCrit) {
                        System.out.println("CRITICAL HIT!");
                        isCrit = false;
                    }
                } else if (enemyType.equalsIgnoreCase("grass")) {
                    damage = 5;
                    damage = rollCritDie(damage);
                    damage = damage + adder + adder2;
                    System.out.println(name.toUpperCase() + " SPRAYED THE DITTO FOR " + damage + " DAMAGE!");
                    System.out.println("IT'S NOT VERY EFFECTIVE!");
                    if (isCrit) {
                        System.out.println("CRITICAL HIT!");
                        isCrit = false;
                    }
                } else {
                    damage = 15;
                    damage = rollCritDie(damage);
                    damage = damage + adder + adder2;
                    System.out.println(name.toUpperCase() + " DRENCHED THE DITTO FOR " + damage + " DAMAGE!");
                    if (isCrit) {
                        System.out.println("CRITICAL HIT!");
                        isCrit = false;
                    }
                }
            } else {
                System.out.println(name.toUpperCase() +"'S BUBBLE MISSED!");
            }
        }
        if (move.equalsIgnoreCase("hunt")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED TO HUNT!");
            die.roll();
            if (die.getCurrentValue() == 1 || die.getCurrentValue() == 2) {
                if (evolutionLevel == 3){
                    if (die.getCurrentValue() <= 4){
                        int difference = 80 - getHitPoints();
                        heal();
                        System.out.println(name.toUpperCase() + " FOUND A SITRUS BERRY AND HEALED " + difference + " HITPOINTS");
                    }
                } else if(die.getCurrentValue() <= 3){
                    int difference = 80 - getHitPoints();
                    heal();
                    System.out.println(name.toUpperCase() + " FOUND A SITRUS BERRY AND HEALED " + difference + " HITPOINTS");
                }

            } else {
                System.out.println(name.toUpperCase() + " DID NOT FIND ANYTHING");
            }
        }
        if (move.equalsIgnoreCase("evolve")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED TO EVOLVE!");
            evolveDie.roll();
            if (evolveDie.getCurrentValue() == 1) {
                System.out.print(name.toUpperCase() + " HAS EVOLVED INTO A ");
                if (name.equalsIgnoreCase("squirtle")){
                    name = "wartortle";
                    adder = 5;
                    evolutionLevel = 2;
                } else{
                    name = "blastoise";
                    adder2 = 5;
                    evolutionLevel = 3;
                }
                System.out.print(name.toUpperCase());
            } else {
                System.out.println(name.toUpperCase() + " COULD NOT EVOLVE");
            }
        }
        return damage;
    }
    private int fireAttack (String move, String enemyType){
        // Scratch, Ember, and Hunt
        int damage = 0;
        // Scratch
        if (move.equalsIgnoreCase("scratch")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED SCRATCH!");
            die.roll();
            if (die.getCurrentValue() != 1) {
                damage = 10;
                damage = rollCritDie(damage);
                damage = damage + adder + adder2;
                System.out.println(name.toUpperCase() + " SCRATCHED THE DITTO AND DID " + damage + " DAMAGE!");
                if (isCrit){
                    System.out.println("CRITICAL HIT!");
                    isCrit = false;
                }
            } else {
                System.out.println(name.toUpperCase() + "'S SCRATCH MISSED!");
            }
        }
        if (move.equalsIgnoreCase("ember")) {
            System.out.println(name.toUpperCase()+ " ATTEMPTED EMBER!");
            die.roll();
            if (die.getCurrentValue() != 1 && die.getCurrentValue() != 2 && die.getCurrentValue() != 3) {
                if (enemyType.equalsIgnoreCase("grass")) {
                    damage = 20;
                    damage = rollCritDie(damage);
                    damage = damage + adder + adder2;
                    System.out.println(name.toUpperCase() + " BURNED THE DITTO FOR " + damage + " DAMAGE!");
                    System.out.println("IT'S SUPER EFFECTIVE!");
                    if (isCrit) {
                        System.out.println("CRITICAL HIT!");
                        isCrit = false;
                    }
                } else if (enemyType.equalsIgnoreCase("water")) {
                    damage = 5;
                    damage = rollCritDie(damage);
                    damage = damage + adder + adder2;
                    System.out.println(name.toUpperCase() + " BURNED THE DITTO FOR " + damage + " DAMAGE!");
                    System.out.println("IT'S NOT VERY EFFECTIVE!");
                    if (isCrit) {
                        System.out.println("CRITICAL HIT!");
                        isCrit = false;
                    }
                } else {
                    damage = 15;
                    damage = rollCritDie(damage);
                    damage = damage + adder + adder2;
                    System.out.println(name.toUpperCase() + " BURNED THE DITTO FOR " + damage + " DAMAGE!");
                    if (isCrit) {
                        System.out.println("CRITICAL HIT!");
                        isCrit = false;
                    }
                }

            } else {
                System.out.println(name.toUpperCase() + "'S EMBER MISSED!");
            }
        }

        if (move.equalsIgnoreCase("hunt")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED TO HUNT!");
            die.roll();
            if (die.getCurrentValue() == 1 || die.getCurrentValue() == 2) {
                if (evolutionLevel == 3){
                    if (die.getCurrentValue() <= 4){
                        int difference = 80 - getHitPoints();
                        heal();
                        System.out.println(name.toUpperCase() + " FOUND A SITRUS BERRY AND HEALED " + difference + " HITPOINTS");
                    }
                } else if(die.getCurrentValue() <= 3){
                    int difference = 80 - getHitPoints();
                    heal();
                    System.out.println(name.toUpperCase() + " FOUND A SITRUS BERRY AND HEALED " + difference + " HITPOINTS");
                }

            } else {
                System.out.println(name.toUpperCase() + " DID NOT FIND ANYTHING");
            }
        }
        if (move.equalsIgnoreCase("evolve")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED TO EVOLVE!");
            evolveDie.roll();
            if (evolveDie.getCurrentValue() == 1) {
                System.out.print(name.toUpperCase() + " HAS EVOLVED INTO A ");
                if (name.equalsIgnoreCase("charmander")){
                    name = "charmeleon";
                    adder = 5;
                    evolutionLevel = 2;
                } else{
                    name = "charizard";
                    adder2 = 5;
                    evolutionLevel = 3;
                }
                System.out.print(name.toUpperCase());
            } else {
                System.out.println(name.toUpperCase() + " COULD NOT EVOLVE");
            }
        }
        return damage;
    }

    private int grassAttack (String move, String enemyType){
        // Headbutt, Vine Whip, and Hunt
        int damage = 0;
        // Headbutt
        if (move.equalsIgnoreCase("headbutt")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED HEADBUTT!");
            die.roll();
            if (die.getCurrentValue() != 1) {
                damage = 10;
                damage = rollCritDie(damage);
                damage = damage + adder + adder2;
                System.out.println(name.toUpperCase() + " HEADBUTTED THE DITTO AND DID " + damage + " DAMAGE!");
                if (isCrit){
                    System.out.println("CRITICAL HIT!");
                    isCrit = false;
                }
            } else {
                System.out.println(name.toUpperCase() + "'S HEADBUTT MISSED!");
            }
        }
        if (move.equalsIgnoreCase("vinewhip") || move.equalsIgnoreCase("vine whip")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED VINE WHIP!");
            die.roll();
            if (die.getCurrentValue() != 1 && die.getCurrentValue() != 2 && die.getCurrentValue() != 3) {
                if (enemyType.equalsIgnoreCase("water")) {
                    damage = 20;
                    damage = rollCritDie(damage);
                    damage = damage + adder + adder2;
                    System.out.println(name.toUpperCase() + " WHIPPED THE DITTO FOR " + damage + " DAMAGE!");
                    System.out.println("IT'S SUPER EFFECTIVE!");
                    if (isCrit) {
                        System.out.println("CRITICAL HIT!");
                        isCrit = false;
                    }
                } else if (enemyType.equalsIgnoreCase("fire")) {
                    damage = 5;
                    damage = rollCritDie(damage);
                    damage = damage + adder + adder2;
                    System.out.println(name.toUpperCase() + " WHIPPED THE DITTO FOR " + damage + " DAMAGE!");
                    System.out.println("IT'S NOT VERY EFFECTIVE!");
                    if (isCrit) {
                        System.out.println("CRITICAL HIT!");
                        isCrit = false;
                    }
                } else {
                    damage = 15;
                    damage = rollCritDie(damage);
                    damage = damage + adder + adder2;
                    System.out.println(name.toUpperCase() + " WHIPPED THE DITTO FOR " + damage + " DAMAGE!");
                    if (isCrit) {
                        System.out.println("CRITICAL HIT!");
                        isCrit = false;
                    }
                }

            } else {
                System.out.println(name.toUpperCase()+"'S VINE WHIP MISSED!");
            }
        }
        if (move.equalsIgnoreCase("hunt")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED TO HUNT!");
            die.roll();
            if (die.getCurrentValue() == 1 || die.getCurrentValue() == 2) {
                if (evolutionLevel == 3){
                    if (die.getCurrentValue() <= 4){
                        int difference = 80 - getHitPoints();
                        heal();
                        System.out.println(name.toUpperCase() + " FOUND A SITRUS BERRY AND HEALED " + difference + " HITPOINTS");
                    }
                } else if(die.getCurrentValue() <= 3){
                    int difference = 80 - getHitPoints();
                    heal();
                    System.out.println(name.toUpperCase() + " FOUND A SITRUS BERRY AND HEALED " + difference + " HITPOINTS");
                }

            } else {
                System.out.println(name.toUpperCase() + " DID NOT FIND ANYTHING");
            }
        }
        if (move.equalsIgnoreCase("evolve")) {
            System.out.println(name.toUpperCase() + " ATTEMPTED TO EVOLVE!");
            evolveDie.roll();
            if (evolveDie.getCurrentValue() == 1) {
                System.out.print(name.toUpperCase() + " HAS EVOLVED INTO A ");
                if (name.equalsIgnoreCase("bulbasaur")){
                    name = "ivysaur";
                    adder = 5;
                    evolutionLevel = 2;
                } else{
                    name = "venusaur";
                    adder2 = 5;
                    evolutionLevel = 3;
                }
                System.out.print(name.toUpperCase());
            } else {
                System.out.println(name.toUpperCase() + " COULD NOT EVOLVE");
            }
        }
        return damage;
    }

    private int rollCritDie(int damage){
        critDie.roll();
        if (critDie.getCurrentValue() >= 95){
            damage += damage;
            isCrit = true;
        }
        return damage;
    }

    public boolean isValidMove(String move){
        boolean flag = false;
        if (type.equalsIgnoreCase("water")){
            if (move.equalsIgnoreCase("tackle")){
                flag = true;
            }
            if (move.equalsIgnoreCase("bubble")){
                flag = true;
            }
        }
        if (type.equalsIgnoreCase("fire")){
            if (move.equalsIgnoreCase("scratch")){
                flag = true;
            }
            if (move.equalsIgnoreCase("ember")){
                flag = true;
            }
        }
        if (type.equalsIgnoreCase("grass")){
            if (move.equalsIgnoreCase("vinewhip") || move.equalsIgnoreCase("vine") || move.equalsIgnoreCase("vine whip")){
                flag = true;
            }
            if (move.equalsIgnoreCase("headbutt")){
                flag = true;
            }
        }

        if (move.equalsIgnoreCase("hunt")){
            flag = true;
        }

        if (move.equalsIgnoreCase("evolve") && evolutionLevel != 3){
            flag = true;
        }
        return flag;
    }
}
