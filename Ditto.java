/*
    Ditto Class
    AJ Miller
    10/21/2020
 */

// TYPE 1 - WATER
// TYPE 2 - FIRE
// TYPE 3 - GRASS
/**
 * Creates the a Pokemon with moves and hit points
 */
public class Ditto {
    private static final int OUT_OF_TEN = 10;
    private static final int OUT_OF_HUNDRED = 100;
    private int hitPoints;
    private String type;
    private Die die = new Die(OUT_OF_TEN);
    private Die critDie = new Die(OUT_OF_HUNDRED);
    private Die typeChooser = new Die(3);
    private int health = 100;

    public Ditto(){
        hitPoints = health;
        typeChooser.roll();
        int typeValue = typeChooser.getCurrentValue();
        if (typeValue == 1){
            this.type = "water";
        }
        if (typeValue == 2){
            this.type = "grass";
        }
        if (typeValue == 3){
            this.type = "fire";
        }
    }

    private String changeType(){
        typeChooser.roll();
        if (type.equals("fire")){
            while(typeChooser.getCurrentValue() == 2){
                typeChooser.roll();
            }
        } else if(type.equals("grass")){
            while(typeChooser.getCurrentValue() == 3){
                typeChooser.roll();
            }
        } else{
            while(typeChooser.getCurrentValue() == 1){
                typeChooser.roll();
            }
        }
        if (typeChooser.getCurrentValue() == 1){
            this.type = "water";
        }
        if (typeChooser.getCurrentValue() == 2){
            this.type = "fire";
        }
        if (typeChooser.getCurrentValue() == 3){
            this.type = "grass";
        }
        return this.type;
    }

    public void takeDamage(int damage){
        hitPoints -= damage;
    }

    public int getHitPoints(){
        return hitPoints;
    }

    public String getType(){
        return type;
    }

    public int attack(String type, String enemyType, int enemyHealth){
        int damage = 0;
        // WATER TYPE
        if (type.equalsIgnoreCase("water")){
            damage = waterAttack(enemyType, enemyHealth);
        }
        // FIRE TYPE
        if (type.equalsIgnoreCase("fire")){
            damage = fireAttack(enemyType, enemyHealth);
        }
        // GRASS TYPE
        if (type.equalsIgnoreCase("grass")){
            damage = grassAttack(enemyType, enemyHealth);
        }
        return damage;
    }

    private int waterAttack(String enemyType, int enemyHealth) {
        // Tackle, Bubble, and Hunt
        int damage = 0;
        // Tackle
        die.roll();
        int roll = die.getCurrentValue();
        if (roll > 1 && roll <= 6) {
            System.out.println("DITTO ATTEMPTED TACKLE!");
            die.roll();
            if (die.getCurrentValue() != 1) {
                damage = 10;
                damage = rollCritDie(damage);
                System.out.println("DITTO TACKLED YOU AND DID "+ damage +" DAMAGE!");
                if (damage > 10){
                    System.out.println("CRITICAL HIT!");
                }
            } else {
                System.out.println("DITTO'S TACKLE MISSED!");
            }
        }
        if (roll > 6 && roll <=9) {
            System.out.println("DITTO ATTEMPTED BUBBLE!");
            die.roll();
            if (die.getCurrentValue() != 1 && die.getCurrentValue() != 2 && die.getCurrentValue() != 3) {
                if (enemyType.equalsIgnoreCase("fire")) {
                    damage = 20;
                    damage = rollCritDie(damage);
                    System.out.println("DITTO DRENCHED YOU FOR "+damage+" DAMAGE!");
                    System.out.println("IT'S SUPER EFFECTIVE!");
                    if (damage > 20){
                        System.out.println("CRITICAL HIT!");
                    }
                } else if(enemyType.equalsIgnoreCase("grass")){
                    damage = 5;
                    damage = rollCritDie(damage);
                    System.out.println("DITTO SPRAYED YOU FOR "+damage+" DAMAGE!");
                    System.out.println("IT'S NOT VERY EFFECTIVE!");
                    if (damage > 5){
                        System.out.println("CRITICAL HIT!");
                    }
                } else{
                    damage = 15;
                    damage = rollCritDie(damage);
                    System.out.println("DITTO DRENCHED YOU FOR "+damage+" DAMAGE!");
                    if (damage > 15){
                        System.out.println("CRITICAL HIT!");
                    }
                }
            } else {
                System.out.println("DITTO'S BUBBLE MISSED!");
            }
        }
        if(roll == 1){
            System.out.println("DITTO CHOSE TO TRANSFORM!");
            String newType = changeType();
            System.out.println("DITTO HAS CHANGED ITS TYPE TO " + newType.toUpperCase());
        }
        if (roll == 10){
            System.out.println("DITTO CHOSE SUPER FANG!");
            die.roll();
            if (die.getCurrentValue() > 3){
                System.out.println("DITTO SANK HIS FANGS INTO YOU!");
                if (enemyHealth % 10 == 5){
                    enemyHealth += 5;
                }
                damage = (enemyHealth / 2);
            } else{
                System.out.println("DITTO'S SUPER FANG MISSED!");
            }
        }
        return damage;
    }
    private int fireAttack(String enemyType, int enemyHealth){
        // Scratch, Ember, and Hunt
        int damage = 0;
        // Scratch
        die.roll();
        int roll = die.getCurrentValue();
        if (roll > 1 && roll <= 6) {
            System.out.println("DITTO ATTEMPTED SCRATCH!");
            die.roll();
            if (die.getCurrentValue() != 1) {
                damage = 10;
                damage = rollCritDie(damage);
                System.out.println("DITTO SCRATCHED YOU AND DID "+ damage +" DAMAGE!");
                if (damage > 10){
                    System.out.println("CRITICAL HIT!");
                }
            } else {
                System.out.println("DITTO'S SCRATCH MISSED!");
            }
        }
        if (roll > 6 && roll <=9) {
            System.out.println("DITTO ATTEMPTED EMBER!");
            die.roll();
            if (die.getCurrentValue() != 1 && die.getCurrentValue() != 2 && die.getCurrentValue() != 3) {
                if (enemyType.equalsIgnoreCase("grass")) {
                    damage = 20;
                    damage = rollCritDie(damage);
                    System.out.println("DITTO BURNED YOU FOR "+damage+" DAMAGE!");
                    System.out.println("IT'S SUPER EFFECTIVE!");
                    if (damage > 20){
                        System.out.println("CRITICAL HIT!");
                    }
                } else if(enemyType.equalsIgnoreCase("water")){
                    damage = 5;
                    damage = rollCritDie(damage);
                    System.out.println("DITTO BURNED YOU FOR "+damage+" DAMAGE!");
                    System.out.println("IT'S NOT VERY EFFECTIVE!");
                    if (damage > 5){
                        System.out.println("CRITICAL HIT!");
                    }
                } else{
                    damage = 15;
                    damage = rollCritDie(damage);
                    System.out.println("DITTO BURNED YOU FOR "+damage+" DAMAGE!");
                    if (damage > 15){
                        System.out.println("CRITICAL HIT!");
                    }
                }
            } else {
                System.out.println("DITTO'S EMBER MISSED!");
            }
        }
        if(roll == 1){
            System.out.println("DITTO CHOSE TO TRANSFORM!");
            String newType = changeType();
            System.out.println("DITTO HAS CHANGED ITS TYPE TO " + newType.toUpperCase());
        }
        if (roll == 10){
            System.out.println("DITTO CHOSE SUPER FANG!");
            die.roll();
            if (die.getCurrentValue() > 3){
                System.out.println("DITTO SANK HIS FANGS INTO YOU!");
                if (enemyHealth % 10 == 5){
                    enemyHealth += 5;
                }
                damage = (enemyHealth / 2);
            } else{
                System.out.println("DITTO'S SUPER FANG MISSED!");
            }
        }
        return damage;
    }

    private int grassAttack (String enemyType, int enemyHealth){
        // Headbutt, Vine Whip, and Hunt
        int damage = 0;
        // Headbutt
        die.roll();
        int roll = die.getCurrentValue();
        if (roll > 1 && roll <= 6) {
            System.out.println("DITTO ATTEMPTED HEADBUTT!");
            die.roll();
            if (die.getCurrentValue() != 1) {
                damage = 10;
                damage = rollCritDie(damage);
                System.out.println("DITTO HEADBUTTED YOU AND DID "+ damage +" DAMAGE!");
                if (damage > 10){
                    System.out.println("CRITICAL HIT!");
                }
            } else {
                System.out.println("DITTO'S HEADBUTT MISSED!");
            }
        }
        if (roll > 6 && roll <=9) {
            System.out.println("DITTO ATTEMPTED VINE WHIP!");
            die.roll();
            if (die.getCurrentValue() != 1 && die.getCurrentValue() != 2 && die.getCurrentValue() != 3) {
                if (enemyType.equalsIgnoreCase("water")) {
                    damage = 20;
                    damage = rollCritDie(damage);
                    System.out.println("DITTO WHIPPED YOU FOR "+damage+" DAMAGE!");
                    System.out.println("IT'S SUPER EFFECTIVE!");
                    if (damage > 20){
                        System.out.println("CRITICAL HIT!");
                    }
                } else if(enemyType.equalsIgnoreCase("fire")){
                    damage = 5;
                    damage = rollCritDie(damage);
                    System.out.println("DITTO WHIPPED YOU FOR "+damage+" DAMAGE!");
                    System.out.println("IT'S NOT VERY EFFECTIVE!");
                    if (damage > 5){
                        System.out.println("CRITICAL HIT!");
                    }
                } else{
                    damage = 15;
                    damage = rollCritDie(damage);
                    System.out.println("DITTO WHIPPED YOU FOR "+damage+" DAMAGE!");
                    if (damage > 15){
                        System.out.println("CRITICAL HIT!");
                    }
                }
            } else {
                System.out.println("DITTO'S VINE WHIP MISSED!");
            }
        }
        if(roll == 1){
            System.out.println("DITTO CHOSE TO TRANSFORM!");
            String newType = changeType();
            System.out.println("DITTO HAS CHANGED ITS TYPE TO " + newType.toUpperCase());
        }
        if (roll == 10){
            System.out.println("DITTO CHOSE SUPER FANG!");
            critDie.roll();
            if (critDie.getCurrentValue() > 15){
                if (enemyHealth % 10 == 5){
                    enemyHealth += 5;
                }
                damage = (enemyHealth / 2);
                System.out.println("DITTO SANK HIS FANGS INTO YOU FOR " + damage + " DAMAGE!");
            } else{
                System.out.println("DITTO'S SUPER FANG MISSED!");
            }
        }
        return damage;
    }

    private int rollCritDie(int damage){
        critDie.roll();
        if (critDie.getCurrentValue() >= 95){
            damage += damage;
        }
        return damage;
    }

}
