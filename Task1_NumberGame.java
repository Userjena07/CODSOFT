import java.util.Random;
import java.util.Scanner;

public class Task1_NumberGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 100;
    private static int score = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            playAgain = playRound(scanner);
            if (playAgain) {
                System.out.println("Let's play again!");
            }
        } while (playAgain);

        System.out.println("Thanks for playing! Your final score is: " + score);
        scanner.close();
    }

    private static boolean playRound(Scanner scanner) {
        Random random = new Random();
        int numberToGuess = random.nextInt(RANGE_MAX - RANGE_MIN + 1) + RANGE_MIN;
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("A new number has been generated between " + RANGE_MIN + " and " + RANGE_MAX + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess the number.");

        while (attempts < MAX_ATTEMPTS && !hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < RANGE_MIN || userGuess > RANGE_MAX) {
                System.out.println("Please guess a number within the range of " + RANGE_MIN + " to " + RANGE_MAX + ".");
                attempts--; // Do not count this attempt
                continue;
            }

            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You've guessed the number: " + numberToGuess);
                score += (MAX_ATTEMPTS - attempts + 1); // Score based on remaining attempts
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("Sorry! You've used all your attempts. The number was: " + numberToGuess);
        }

        return askToPlayAgain(scanner);
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next().trim().toLowerCase();
        return response.equals("yes");
    }
}