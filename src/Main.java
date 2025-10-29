import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        UIHelper.printBanner();
        UIHelper.pause(500);

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else if (currentUser.isAdmin()) {
                showAdminMenu();
            } else {
                showStudentMenu();
            }
        }
    }

    private static void showLoginMenu() {
        UIHelper.clearScreen();
        UIHelper.printAnimatedHeader("AUTHENTICATION");
        String[] options = {"🔐 Login to Your Account", "📝 Create New Account", "🚪 Exit Application"};
        UIHelper.printMenu(options);

        int choice = getChoice();

        switch (choice) {
            case 1 -> login();
            case 2 -> register();
            case 3 -> {
                UIHelper.printGoodbye();
                UIHelper.pause(2000);
                System.exit(0);
            }
            default -> {
                UIHelper.printError("Invalid choice! Please select 1-3");
                UIHelper.pause(1500);
            }
        }
    }

    private static void login() {
        UIHelper.clearScreen();
        UIHelper.printHeader("LOGIN");
        UIHelper.printSubHeader("Enter your credentials");

        UIHelper.printInput("Username");
        String username = scanner.nextLine();
        UIHelper.printInput("Password");
        String password = scanner.nextLine();

        UIHelper.printLoading("Authenticating");
        currentUser = AuthService.login(username, password);

        if (currentUser != null) {
            UIHelper.printSuccess("Login successful! Welcome back!");
            UIHelper.printWelcome(currentUser.getUsername(), currentUser.getRole());
            UIHelper.pause(2500);
        } else {
            UIHelper.printError("Invalid credentials! Please try again.");
            UIHelper.pause(2000);
        }
    }

    private static void register() {
        UIHelper.clearScreen();
        UIHelper.printHeader("REGISTER NEW ACCOUNT");
        UIHelper.printSubHeader("Create your quiz account");

        UIHelper.printInput("Username");
        String username = scanner.nextLine();
        UIHelper.printInput("Password");
        String password = scanner.nextLine();
        UIHelper.printInput("Role (STUDENT/ADMIN)");
        String role = scanner.nextLine().toUpperCase();

        if (!role.equals("STUDENT") && !role.equals("ADMIN")) {
            UIHelper.printError("Invalid role! Must be STUDENT or ADMIN");
            UIHelper.pause(2000);
            return;
        }

        UIHelper.printLoading("Creating account");

        if (AuthService.register(username, password, role)) {
            UIHelper.printSuccess("Registration successful! You can now login.");
            UIHelper.printStars();
        } else {
            UIHelper.printError("Registration failed! Username might be taken.");
        }
        UIHelper.pause(2500);
    }

    private static void showAdminMenu() {
        UIHelper.clearScreen();
        UIHelper.printHeader("ADMIN DASHBOARD");
        UIHelper.printSubHeader("Logged in as: " + currentUser.getUsername() + " (Administrator)");

        String[] options = {
                "➕ Create New Quiz",
                "❓ Add Questions to Quiz",
                "✅ Publish Quiz",
                "🏆 View Leaderboard",
                "📊 View All Quizzes",
                "🔙 Logout"
        };
        UIHelper.printStylishMenu(options);

        int choice = getChoice();

        switch (choice) {
            case 1 -> createQuiz();
            case 2 -> addQuestions();
            case 3 -> publishQuiz();
            case 4 -> viewLeaderboard();
            case 5 -> viewAllQuizzes();
            case 6 -> logout();
            default -> {
                UIHelper.printError("Invalid choice!");
                UIHelper.pause(1500);
            }
        }
    }

    private static void showStudentMenu() {
        UIHelper.clearScreen();
        UIHelper.printHeader("STUDENT DASHBOARD");
        UIHelper.printSubHeader("Welcome back, " + currentUser.getUsername() + "!");

        String[] options = {
                "📝 Take a Quiz",
                "🏆 View Leaderboard",
                "📊 View My Scores",
                "🔙 Logout"
        };
        UIHelper.printStylishMenu(options);

        int choice = getChoice();

        switch (choice) {
            case 1 -> takeQuiz();
            case 2 -> viewLeaderboard();
            case 3 -> viewMyScores();
            case 4 -> logout();
            default -> {
                UIHelper.printError("Invalid choice!");
                UIHelper.pause(1500);
            }
        }
    }

    private static void createQuiz() {
        UIHelper.clearScreen();
        UIHelper.printHeader("CREATE NEW QUIZ");
        UIHelper.printDivider();

        UIHelper.printInput("Quiz Title");
        String title = scanner.nextLine();
        UIHelper.printInput("Time Limit (minutes)");
        int time = scanner.nextInt();
        scanner.nextLine();

        UIHelper.printLoading("Creating quiz");

        if (QuizService.createQuiz(title, time)) {
            UIHelper.printSuccess("Quiz '" + title + "' created successfully!");
            UIHelper.printInfo("Don't forget to add questions and publish it!");
        } else {
            UIHelper.printError("Failed to create quiz!");
        }
        UIHelper.printWarning("Press ENTER to continue...");
        scanner.nextLine();
    }

    private static void addQuestions() {
        UIHelper.clearScreen();
        UIHelper.printHeader("ADD QUESTIONS");

        UIHelper.printInput("Enter Quiz ID");
        int quizId = scanner.nextInt();
        scanner.nextLine();

        UIHelper.printInput("How many questions to add");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= count; i++) {
            UIHelper.clearScreen();
            UIHelper.printHeader("ADD QUESTIONS");
            UIHelper.printInfo("Adding question " + i + " of " + count);
            UIHelper.printDivider();

            UIHelper.printInput("Question Text");
            String q = scanner.nextLine();
            UIHelper.printInput("Option A");
            String a = scanner.nextLine();
            UIHelper.printInput("Option B");
            String b = scanner.nextLine();
            UIHelper.printInput("Option C");
            String c = scanner.nextLine();
            UIHelper.printInput("Option D");
            String d = scanner.nextLine();
            UIHelper.printInput("Correct Answer (A/B/C/D)");
            String correct = scanner.nextLine().toUpperCase();

            QuizService.addQuestion(quizId, q, a, b, c, d, correct);
            UIHelper.printSuccess("Question " + i + " added!");
            UIHelper.pause(500);
        }

        UIHelper.printSuccess("All " + count + " questions added successfully!");
        UIHelper.printWarning("Press ENTER to continue...");
        scanner.nextLine();
    }

    private static void publishQuiz() {
        UIHelper.clearScreen();
        UIHelper.printHeader("PUBLISH QUIZ");

        UIHelper.printInput("Enter Quiz ID to publish");
        int quizId = scanner.nextInt();
        scanner.nextLine();

        UIHelper.printLoading("Publishing quiz");

        if (QuizService.publishQuiz(quizId)) {
            UIHelper.printSuccess("Quiz published! Students can now take it!");
            UIHelper.printFireworks();
        } else {
            UIHelper.printError("Failed to publish quiz!");
        }
        UIHelper.printWarning("Press ENTER to continue...");
        scanner.nextLine();
    }

    private static void viewAllQuizzes() {
        UIHelper.clearScreen();
        UIHelper.printHeader("ALL QUIZZES");
        QuizService.displayAllQuizzes();
        UIHelper.printWarning("\nPress ENTER to go back...");
        scanner.nextLine();
    }

    private static void takeQuiz() {
        UIHelper.clearScreen();
        List<Quiz> quizzes = QuizService.getAllPublishedQuizzes();

        if (quizzes.isEmpty()) {
            UIHelper.printWarning("No quizzes available at the moment!");
            UIHelper.pause(2000);
            return;
        }

        UIHelper.printHeader("AVAILABLE QUIZZES");
        UIHelper.printDivider();

        for (int i = 0; i < quizzes.size(); i++) {
            Quiz q = quizzes.get(i);
            String color = switch(i % 5) {
                case 0 -> UIHelper.BRIGHT_RED;
                case 1 -> UIHelper.BRIGHT_GREEN;
                case 2 -> UIHelper.BRIGHT_YELLOW;
                case 3 -> UIHelper.BRIGHT_CYAN;
                default -> UIHelper.BRIGHT_MAGENTA;
            };

            System.out.println(color + UIHelper.BOLD + "   [" + (i + 1) + "] " + UIHelper.RESET +
                    UIHelper.WHITE + q.getTitle() + UIHelper.RESET +
                    UIHelper.DIM + " (" + q.getTimeLimit() + " mins)" + UIHelper.RESET);
        }

        UIHelper.printDivider();
        UIHelper.printInput("Select quiz number");
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (choice < 0 || choice >= quizzes.size()) {
            UIHelper.printError("Invalid quiz selection!");
            UIHelper.pause(2000);
            return;
        }

        Quiz selectedQuiz = quizzes.get(choice);
        List<Question> questions = QuizService.getQuizQuestions(selectedQuiz.getQuizId());

        UIHelper.clearScreen();
        UIHelper.printHeader("QUIZ: " + selectedQuiz.getTitle());
        UIHelper.printInfo("Total Questions: " + questions.size());
        UIHelper.printInfo("Time Limit: " + selectedQuiz.getTimeLimit() + " minutes");
        UIHelper.printRainbowDivider();
        UIHelper.printWarning("Press ENTER to begin...");
        scanner.nextLine();

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            UIHelper.clearScreen();
            Question q = questions.get(i);
            UIHelper.printQuestion(i + 1, questions.size(), q.getQuestionText());
            UIHelper.printOption("A", q.getOptionA());
            UIHelper.printOption("B", q.getOptionB());
            UIHelper.printOption("C", q.getOptionC());
            UIHelper.printOption("D", q.getOptionD());

            UIHelper.printInput("\nYour answer");
            String answer = scanner.nextLine().toUpperCase();

            if (answer.equals(q.getCorrectAnswer())) {
                UIHelper.printSuccess("Correct! Excellent work! 🎉");
                score++;
            } else {
                UIHelper.printError("Wrong! The correct answer was: " + q.getCorrectAnswer());
            }

            if (i < questions.size() - 1) {
                UIHelper.printWarning("Press ENTER for next question...");
                scanner.nextLine();
            }
        }

        UIHelper.printScore(score, questions.size());
        QuizService.saveAttempt(currentUser.getUserId(), selectedQuiz.getQuizId(), score, questions.size());

        UIHelper.printWarning("\nPress ENTER to continue...");
        scanner.nextLine();
    }

    private static void viewLeaderboard() {
        UIHelper.clearScreen();
        UIHelper.printInput("Enter Quiz ID");
        int quizId = scanner.nextInt();
        scanner.nextLine();

        UIHelper.printLoading("Loading leaderboard");
        QuizService.displayLeaderboard(quizId);

        UIHelper.printWarning("\nPress ENTER to go back...");
        scanner.nextLine();
    }

    private static void viewMyScores() {
        UIHelper.clearScreen();
        UIHelper.printHeader("MY SCORES");
        QuizService.displayUserScores(currentUser.getUserId());
        UIHelper.printWarning("\nPress ENTER to go back...");
        scanner.nextLine();
    }

    private static void logout() {
        UIHelper.printLoading("Logging out");
        currentUser = null;
        UIHelper.printSuccess("Logged out successfully!");
        UIHelper.pause(1500);
    }

    private static int getChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}
