import java.util.Random; // Imports the Random class from the java.util package, which provides methods to generate random numbers.
import java.io.*; // Imports all the classes from the java.io package, which is used for input and output operations (e.g., reading and writing files).
import java.math.*; // Imports all the classes from the java.math package, which provides mathematical operations (e.g., BigDecimal, BigInteger).
import java.util.regex.*; // Imports all the classes from the java.util.regex package, which provides regular expression capabilities for pattern matching.

class Player { // Defines a class named Player
    private String name; // Private variable to store the player's name
    private int health; // Private variable to store the player's health
    private int strength; // Private variable to store the player's strength
    private int attack; // Private variable to store the player's attack power
    private Random random; // Private variable to generate random numbers

    public Player(String name, int health, int strength, int attack) { // Constructor to initialize the player's attributes
        this.name = name; // Sets the player's name
        this.health = health; // Sets the player's health
        this.strength = strength; // Sets the player's strength
        this.attack = attack; // Sets the player's attack power
        this.random = new Random(); // Initializes the random number generator
    }

    public String getName() { // Method to get the player's name
        return name; // Returns the player's name
    }

    public int getHealth() { // Method to get the player's health
        return health; // Returns the player's health
    }

    public void setHealth(int health) { // Method to set the player's health
        this.health = health; // Updates the player's health
    }

    public int getStrength() { // Method to get the player's strength
        return strength; // Returns the player's strength
    }

    public int getAttack() { // Method to get the player's attack power
        return attack; // Returns the player's attack power
    }

    public void attack(Player opponent) { // Method to perform an attack on another player
        int attackRoll = random.nextInt(6) + 1; // Rolls a random number between 1 and 6 for the attack
        int damage = attack * attackRoll; // Calculates the damage based on attack power and attack roll

        int defendRoll = random.nextInt(6) + 1; // Rolls a random number between 1 and 6 for the defense
        int defense = opponent.getStrength() * defendRoll; // Calculates the defense based on opponent's strength and defense roll

        int damageTaken = Math.max(damage - defense, 0); // Calculates the damage taken after defense

        opponent.setHealth(opponent.getHealth() - damageTaken); // Reduces the opponent's health by the damage taken

        System.out.printf("%s attacks %s with a roll of %d, deals %d damage. %s rolls a defense of %d, defends %d damage. %s's health reduced to %d.%n",
                name, opponent.getName(), attackRoll, damage, opponent.getName(), defendRoll, defense, opponent.getName(), opponent.getHealth()); // Prints the attack and defense results
    }
}
public class Main { // Defines the Main class

    public static void main(String[] args) { // Main method where the program execution starts
        Player playerA = new Player("Player A", 50, 5, 10); // Creates a new player A with specified attributes
        Player playerB = new Player("Player B", 100, 10, 5); // Creates a new player B with specified attributes

        while (playerA.getHealth() > 0 && playerB.getHealth() > 0) { // Loop until one player's health drops to 0 or below
            Player attacker = (playerA.getHealth() < playerB.getHealth()) ? playerA : playerB; // Determines the attacker based on health
            Player defender = (attacker == playerA) ? playerB : playerA; // Sets the defender as the other player

            attacker.attack(defender); // Attacker attacks the defender
            if (defender.getHealth() <= 0) { // Checks if the defender's health is zero or less
                System.out.printf("%s wins the game!%n", attacker.getName()); // Declares the attacker as the winner
                break; // Breaks the loop if a player wins
            }

            attacker = defender; // Switches the roles of attacker and defender
            defender = (attacker == playerA) ? playerB : playerA; // Ensures the correct assignment of the new defender

            attacker.attack(defender); // New attacker attacks the new defender
            if (defender.getHealth() <= 0) { // Checks if the new defender's health is zero or less
                System.out.printf("%s wins the game!%n", attacker.getName()); // Declares the new attacker as the winner
                break; // Breaks the loop if a player wins
            }
        }
    }
}
