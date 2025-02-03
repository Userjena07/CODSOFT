import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrectAnswer(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }

    // Getter for correctAnswerIndex
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class Task4_QuizApplication {
    private static final int TIME_LIMIT = 10; // Time limit for each question in seconds
    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;

    public static void main(String[] args) {
        // Sample questions
        questions.add(new Question("What is the capital of Odisha?", new String[]{"1. Cuttack", "2. Rourkela", "3. Bhubaneswar", "4. Berhampur"}, 2));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 1));
        questions.add(new Question("What is the largest ocean on Earth?", new String[]{"1. Atlantic Ocean", "2. Indian Ocean", "3. Arctic Ocean", "4. Pacific Ocean"}, 3));
        questions.add(new Question("Who is the Father of Indian Constitution'?", new String[]{"1. APJ Abdul Kalam", "2. Jawaharlal Nehru", "3. BR Ambedker", "4. Mahatma Gandhi"}, 2));
        questions.add(new Question("What is the chemical symbol for water?", new String[]{"1. CO2", "2. H2O", "3. O2", "4. NaCl"}, 1));

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {
            askQuestion(questions.get(i), scanner);
        }

        displayResults();
        scanner.close();
    }

    private static void askQuestion(Question question, Scanner scanner) {
        System.out.println("\n" + question.getQuestionText());
        String[] options = question.getOptions();
        for (String option : options) {
            System.out.println(option);
        }

        // Start timer
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                scanner.nextLine(); // Consume the input to prevent blocking
            }
        };
        timer.schedule(task, TIME_LIMIT * 1000); // Schedule the task

        // Get user input
        System.out.print("Your answer (1-4): ");
        int answerIndex = -1;
        try {
            answerIndex = scanner.nextInt() - 1; // Convert to 0-based index
        } catch (Exception e) {
            System.out.println("Invalid input. Moving to the next question.");
        }

        // Cancel the timer if the user answers in time
        timer.cancel();

        // Check the answer
        if (answerIndex >= 0 && answerIndex < options.length) {
            if (question.isCorrectAnswer(answerIndex)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer was: " + options[question.getCorrectAnswerIndex()]);
            }
        } else {
            System.out.println("Invalid answer. Please select a valid option.");
        }
    }

    private static void displayResults() {
        System.out.println("\n--- Quiz Completed ---");
        System.out.println("Your final score: " + score + "/" + questions.size());
    }
}