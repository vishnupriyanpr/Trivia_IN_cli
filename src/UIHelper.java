public class UIHelper {

    // ANSI Colors
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_MAGENTA = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BOLD = "\u001B[1m";
    public static final String DIM = "\u001B[2m";
    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String BLINK = "\u001B[5m";
    public static final String REVERSE = "\u001B[7m";
    public static final String HIDDEN = "\u001B[8m";
    public static final String STRIKETHROUGH = "\u001B[9m";

    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_MAGENTA = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public static void printBanner() {
        clearScreen();
        String[] colors = {BRIGHT_RED, BRIGHT_YELLOW, BRIGHT_GREEN, BRIGHT_CYAN, BRIGHT_BLUE, BRIGHT_MAGENTA};

        String[] bannerLines = {
                "╔═══════════════════════════════════════════════════════════════════════════╗",
                "║                                                                           ║",
                "║     ██████╗ ██╗   ██╗██╗███████╗    ██████╗ ██╗      █████╗ ████████╗     ║",
                "║    ██╔═══██╗██║   ██║██║╚══███╔╝    ██╔══██╗██║     ██╔══██╗╚══██╔══╝     ║",
                "║    ██║   ██║██║   ██║██║  ███╔╝     ██████╔╝██║     ███████║   ██║        ║",
                "║    ██║▄▄ ██║██║   ██║██║ ███╔╝      ██╔═══╝ ██║     ██╔══██║   ██║        ║",
                "║    ╚██████╔╝╚██████╔╝██║███████╗    ██║     ███████╗██║  ██║   ██║        ║",
                "║     ╚══▀▀═╝  ╚═════╝ ╚═╝╚══════╝    ╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝        ║",
                "║                                                                           ║",
                "║                   ⚡ ULTIMATE QUIZ PLATFORM ⚡                              ║",
                "║                                                                           ║",
                "║                                                                           ║",
                "╚═══════════════════════════════════════════════════════════════════════════╝"
        };

        for (int i = 0; i < bannerLines.length; i++) {
            System.out.println(colors[i % colors.length] + BOLD + bannerLines[i] + RESET);
            pause(80);
        }

        System.out.println("\n" + BRIGHT_CYAN + BOLD + "                           Loading System..." + RESET);
        animatedProgressBar();
        pause(300);
    }

    public static void animatedProgressBar() {
        System.out.print("\n                    " + BRIGHT_CYAN + "[" + RESET);
        for (int i = 0; i < 40; i++) {
            System.out.print(BRIGHT_GREEN + "█" + RESET);
            System.out.flush();
            pause(30);
        }
        System.out.println(BRIGHT_CYAN + "]" + RESET + " " + BRIGHT_GREEN + BOLD + "100%" + RESET);
    }

    public static void printHeader(String title) {
        System.out.println("\n" + BRIGHT_CYAN + BOLD + "╔════════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(BRIGHT_CYAN + BOLD + "║" + RESET + " " + BRIGHT_YELLOW + BOLD + centerText("⚡ " + title + " ⚡", 70) + RESET + BRIGHT_CYAN + BOLD + " ║" + RESET);
        System.out.println(BRIGHT_CYAN + BOLD + "╚════════════════════════════════════════════════════════════════════════╝" + RESET);
    }

    public static void printAnimatedHeader(String title) {
        String[] frames = {"⚡", "✨", "💫", "⭐", "🌟", "✨", "⚡"};
        for (int i = 0; i < 3; i++) {
            clearScreen();
            System.out.println("\n" + BRIGHT_CYAN + BOLD + "╔════════════════════════════════════════════════════════════════════════╗" + RESET);
            System.out.println(BRIGHT_CYAN + BOLD + "║" + RESET + " " + BRIGHT_YELLOW + BOLD + centerText(frames[i % frames.length] + " " + title + " " + frames[i % frames.length], 70) + RESET + BRIGHT_CYAN + BOLD + " ║" + RESET);
            System.out.println(BRIGHT_CYAN + BOLD + "╚════════════════════════════════════════════════════════════════════════╝" + RESET);
            pause(200);
        }
    }

    public static void printSubHeader(String subtitle) {
        System.out.println(BRIGHT_CYAN + "┌────────────────────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(BRIGHT_CYAN + "│ " + WHITE + BOLD + subtitle + RESET + BRIGHT_CYAN + padding(subtitle.length(), 70) + "│" + RESET);
        System.out.println(BRIGHT_CYAN + "└────────────────────────────────────────────────────────────────────────┘" + RESET);
    }

    public static void printGlitchHeader(String title) {
        String[] glitchChars = {"#", "@", "$", "%", "&", "*"};
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                System.out.print("\r" + BRIGHT_RED + BOLD + glitchChars[i % glitchChars.length].repeat(title.length()) + RESET);
            } else {
                System.out.print("\r" + BRIGHT_YELLOW + BOLD + title + RESET);
            }
            System.out.flush();
            pause(50);
        }
        System.out.println("\r" + BRIGHT_CYAN + BOLD + "⚡ " + title + " ⚡" + RESET + "          ");
    }

    public static void printMenu(String[] options) {
        System.out.println();
        for (int i = 0; i < options.length; i++) {
            String color = switch(i % 5) {
                case 0 -> BRIGHT_RED;
                case 1 -> BRIGHT_GREEN;
                case 2 -> BRIGHT_YELLOW;
                case 3 -> BRIGHT_CYAN;
                default -> BRIGHT_MAGENTA;
            };

            System.out.println(color + BOLD + "   ╔═[" + (i + 1) + "]═══════════════════════════════════════════════════════╗" + RESET);
            System.out.println(color + "   ║" + RESET + " " + WHITE + BOLD + options[i] + padding(stripAnsi(options[i]).length(), 53) + color + "║" + RESET);
            System.out.println(color + BOLD + "   ╚═══════════════════════════════════════════════════════════╝" + RESET);
            pause(50);
        }
        System.out.println();
        System.out.print(BRIGHT_GREEN + BOLD + "   ┌─[" + BRIGHT_CYAN + "Your Choice" + BRIGHT_GREEN + "]\n   └──➤ " + RESET);
    }

    public static void printStylishMenu(String[] options) {
        System.out.println();
        String[] icons = {"🎯", "🚀", "⚡", "🔥", "💎", "🏆", "🎮", "⭐"};

        for (int i = 0; i < options.length; i++) {
            System.out.println(BRIGHT_CYAN + "   ▸ " + RESET +
                    BRIGHT_YELLOW + BOLD + "[" + (i + 1) + "]" + RESET + " " +
                    icons[i % icons.length] + " " +
                    WHITE + options[i] + RESET);
            pause(50);
        }
        System.out.println();
        System.out.print(BRIGHT_GREEN + BOLD + "   ➜ " + RESET);
    }

    public static void printSuccess(String message) {
        System.out.println("\n" + BG_GREEN + BLACK + BOLD + " ✓ SUCCESS " + RESET + " " + BRIGHT_GREEN + message + RESET);
    }

    public static void printError(String message) {
        System.out.println("\n" + BG_RED + WHITE + BOLD + " ✗ ERROR " + RESET + " " + BRIGHT_RED + message + RESET);
    }

    public static void printInfo(String message) {
        System.out.println(BG_CYAN + BLACK + BOLD + " ℹ INFO " + RESET + " " + BRIGHT_CYAN + message + RESET);
    }

    public static void printWarning(String message) {
        System.out.println(BG_YELLOW + BLACK + BOLD + " ⚠ WARNING " + RESET + " " + BRIGHT_YELLOW + message + RESET);
    }

    public static void printDivider() {
        System.out.println(BRIGHT_CYAN + "   " + "─".repeat(72) + RESET);
    }

    public static void printDoubleDivider() {
        System.out.println(BRIGHT_RED + "   " + "═".repeat(72) + RESET);
    }

    public static void printRainbowDivider() {
        String[] colors = {BRIGHT_RED, BRIGHT_YELLOW, BRIGHT_GREEN, BRIGHT_CYAN, BRIGHT_BLUE, BRIGHT_MAGENTA};
        System.out.print("   ");
        for (int i = 0; i < 72; i++) {
            System.out.print(colors[i % colors.length] + "─" + RESET);
        }
        System.out.println();
    }

    public static void printQuestion(int num, int total, String question) {
        System.out.println("\n" + BRIGHT_MAGENTA + BOLD + "   ╔═══════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(BRIGHT_MAGENTA + BOLD + "   ║ " + BG_MAGENTA + BLACK + " Question " + num + "/" + total + " " + RESET + BRIGHT_MAGENTA + "                                                        ║" + RESET);
        System.out.println(BRIGHT_MAGENTA + BOLD + "   ╚═══════════════════════════════════════════════════════════════════════╝" + RESET);

        String wrapped = wrapText(question, 65);
        System.out.println("\n   " + WHITE + BOLD + wrapped + RESET + "\n");
    }

    public static void printOption(String label, String text) {
        String color = switch(label) {
            case "A" -> BRIGHT_RED;
            case "B" -> BRIGHT_GREEN;
            case "C" -> BRIGHT_YELLOW;
            case "D" -> BRIGHT_CYAN;
            default -> WHITE;
        };

        String bg = switch(label) {
            case "A" -> BG_RED;
            case "B" -> BG_GREEN;
            case "C" -> BG_YELLOW;
            case "D" -> BG_CYAN;
            default -> "";
        };

        System.out.println("      " + bg + BLACK + BOLD + " " + label + " " + RESET + " " + color + text + RESET);
    }

    public static void printScore(int score, int total) {
        clearScreen();
        double percentage = (score * 100.0) / total;
        String grade;
        String color;
        String emoji;

        if (percentage >= 90) {
            grade = "OUTSTANDING";
            color = BRIGHT_GREEN;
            emoji = "🏆";
        } else if (percentage >= 75) {
            grade = "EXCELLENT";
            color = GREEN;
            emoji = "⭐";
        } else if (percentage >= 60) {
            grade = "GOOD";
            color = BRIGHT_YELLOW;
            emoji = "👍";
        } else if (percentage >= 50) {
            grade = "PASS";
            color = YELLOW;
            emoji = "✔️";
        } else {
            grade = "NEEDS IMPROVEMENT";
            color = BRIGHT_RED;
            emoji = "📚";
        }

        System.out.println("\n" + BRIGHT_CYAN + BOLD + """
        ╔═══════════════════════════════════════════════════════════════════════════╗
        ║                          📊 QUIZ RESULTS 📊                               ║
        ╚═══════════════════════════════════════════════════════════════════════════╝
        """ + RESET);

        System.out.println(BRIGHT_YELLOW + "   ┌──────────────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(BRIGHT_YELLOW + "   │                                                                  │" + RESET);
        System.out.println(BRIGHT_YELLOW + "   │" + RESET + "     " + WHITE + BOLD + "Score:      " + color + BOLD + score + " / " + total + "                                        " + RESET + BRIGHT_YELLOW + "│" + RESET);
        System.out.println(BRIGHT_YELLOW + "   │" + RESET + "     " + WHITE + BOLD + "Percentage: " + color + BOLD + String.format("%.2f%%", percentage) + "                                      " + RESET + BRIGHT_YELLOW + "│" + RESET);
        System.out.println(BRIGHT_YELLOW + "   │" + RESET + "     " + WHITE + BOLD + "Grade:      " + color + BOLD + grade + " " + emoji + "                         " + RESET + BRIGHT_YELLOW + "│" + RESET);
        System.out.println(BRIGHT_YELLOW + "   │                                                                  │" + RESET);
        System.out.println(BRIGHT_YELLOW + "   └──────────────────────────────────────────────────────────────────┘" + RESET);

        printRainbowProgressBar((int)percentage);

        if (percentage >= 90) {
            printFireworks();
        } else if (percentage >= 75) {
            printStars();
        }
    }

    public static void printRainbowProgressBar(int percentage) {
        int filled = percentage / 5;
        int empty = 20 - filled;
        String[] colors = {BRIGHT_RED, BRIGHT_YELLOW, BRIGHT_GREEN, BRIGHT_CYAN, BRIGHT_BLUE, BRIGHT_MAGENTA};

        System.out.print("\n   " + BRIGHT_CYAN + "[" + RESET);
        for (int i = 0; i < filled; i++) {
            System.out.print(colors[i % colors.length] + "█" + RESET);
        }
        System.out.print(DIM + "░".repeat(empty) + RESET);
        System.out.println(BRIGHT_CYAN + "]" + RESET + " " + BRIGHT_YELLOW + BOLD + percentage + "%" + RESET + "\n");
    }

    public static void printFireworks() {
        String[] firework = {"   ✦", "  ✦ ✦", " ✦ ⭐ ✦", "✦ ⭐ ⭐ ✦", " ✦ ⭐ ✦", "  ✦ ✦", "   ✦"};
        System.out.println();
        for (String frame : firework) {
            System.out.print("\r" + BRIGHT_YELLOW + centerText(frame, 70) + RESET);
            System.out.flush();
            pause(100);
        }
        System.out.println();
    }

    public static void printStars() {
        System.out.print("\n   ");
        for (int i = 0; i < 10; i++) {
            System.out.print(BRIGHT_YELLOW + "⭐ " + RESET);
            System.out.flush();
            pause(50);
        }
        System.out.println("\n");
    }

    public static void printLeaderboardHeader() {
        System.out.println("\n" + BRIGHT_RED + BOLD + """
        ╔═══════════════════════════════════════════════════════════════════════════╗
        ║                              🏆 LEADERBOARD 🏆                            ║
        ║                              ⚡ TOP PERFORMERS ⚡                           ║
        ╚═══════════════════════════════════════════════════════════════════════════╝
        """ + RESET);

        System.out.println(BG_CYAN + BLACK + BOLD + String.format("   %-6s %-20s %-12s %-10s %-20s",
                "RANK", "USERNAME", "SCORE", "ACCURACY", "DATE") + RESET);
        printDivider();
    }

    public static void printLeaderboardRow(int rank, String username, String score, double accuracy, String date) {
        String medal = "";
        String color = WHITE;

        switch(rank) {
            case 1 -> { medal = "🥇"; color = BRIGHT_YELLOW; }
            case 2 -> { medal = "🥈"; color = WHITE; }
            case 3 -> { medal = "🥉"; color = YELLOW; }
            default -> { medal = "  "; color = BRIGHT_CYAN; }
        }

        System.out.println(color + BOLD + medal + String.format(" %-5s %-20s %-12s %-10s %-20s",
                rank, username, score, String.format("%.1f%%", accuracy), date) + RESET);
    }

    public static void printInput(String prompt) {
        System.out.print(BRIGHT_GREEN + BOLD + "   ➜ " + RESET + BRIGHT_CYAN + prompt + BRIGHT_YELLOW + " : " + RESET);
    }

    public static void printWelcome(String username, String role) {
        clearScreen();
        System.out.println("\n" + BRIGHT_GREEN + BOLD + """
        ╔═══════════════════════════════════════════════════════════════════════════╗
        ║                           ✨ WELCOME ✨                                   ║
        ╚═══════════════════════════════════════════════════════════════════════════╝
        """ + RESET);

        System.out.println("   " + BRIGHT_CYAN + "╭─ User Information" + RESET);
        System.out.println("   " + BRIGHT_CYAN + "│" + RESET);
        System.out.println("   " + BRIGHT_CYAN + "├─ Username: " + RESET + BRIGHT_YELLOW + BOLD + username + RESET);
        System.out.println("   " + BRIGHT_CYAN + "├─ Role:     " + RESET + BRIGHT_RED + BOLD + role + RESET);
        System.out.println("   " + BRIGHT_CYAN + "│" + RESET);
        System.out.println("   " + BRIGHT_CYAN + "╰─ " + RESET + BRIGHT_GREEN + "Access Granted ✓" + RESET + "\n");
    }

    public static void printGoodbye() {
        clearScreen();
        String[] colors = {BRIGHT_RED, BRIGHT_YELLOW, BRIGHT_GREEN, BRIGHT_CYAN, BRIGHT_BLUE, BRIGHT_MAGENTA};

        String[] goodbyeText = {
                "╔═══════════════════════════════════════════════════════════════════════════╗",
                "║                                                                           ║",
                "║                    ████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗          ║",
                "║                    ╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝          ║",
                "║                       ██║   ███████║███████║██╔██╗ ██║█████╔╝           ║",
                "║                       ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗           ║",
                "║                       ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗          ║",
                "║                       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝          ║",
                "║                                                                           ║",
                "║                     🎯 Thanks for using Quiz Platform! 🎯                ║",
                "║                          See you again soon! 👋                           ║",
                "║                                                                           ║",
                "╚═══════════════════════════════════════════════════════════════════════════╝"
        };

        for (int i = 0; i < goodbyeText.length; i++) {
            System.out.println(colors[i % colors.length] + BOLD + goodbyeText[i] + RESET);
            pause(80);
        }
        System.out.println();
    }

    public static void printLoading(String text) {
        System.out.print("\n   " + BRIGHT_CYAN + BOLD + text + " " + RESET);
        String[] spinner = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};
        for (int i = 0; i < 25; i++) {
            System.out.print(BRIGHT_YELLOW + BOLD + spinner[i % spinner.length] + RESET);
            pause(80);
            System.out.print("\b");
        }
        System.out.println(BRIGHT_GREEN + BOLD + "✓" + RESET + "\n");
    }

    public static void printTypingEffect(String text, int delayMs) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            pause(delayMs);
        }
        System.out.println();
    }

    public static void printBox(String text) {
        int length = stripAnsi(text).length();
        System.out.println(BRIGHT_CYAN + "   ┌" + "─".repeat(length + 2) + "┐" + RESET);
        System.out.println(BRIGHT_CYAN + "   │ " + RESET + text + BRIGHT_CYAN + " │" + RESET);
        System.out.println(BRIGHT_CYAN + "   └" + "─".repeat(length + 2) + "┘" + RESET);
    }

    private static String centerText(String text, int width) {
        int padding = (width - stripAnsi(text).length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    private static String padding(int textLen, int width) {
        int pad = width - textLen - 2;
        return " ".repeat(Math.max(0, pad));
    }

    private static String stripAnsi(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    private static String wrapText(String text, int width) {
        if (text.length() <= width) return text;
        int breakPoint = text.lastIndexOf(' ', width);
        if (breakPoint == -1) breakPoint = width;
        return text.substring(0, breakPoint) + "\n   " + wrapText(text.substring(breakPoint).trim(), width);
    }

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
