import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class UIHelper {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[91m";
    public static final String PINK = "\u001B[96m";
    public static final String MAGENTA = "\u001B[96m";
    public static final String VIOLET = "\u001B[99m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[96m";
    public static final String WHITE = "\u001B[97m";
    public static final String GRAY = "\u001B[90m";
    public static final String BOLD = "\u001B[1m";
    public static final String DIM = "\u001B[2m";
    
    static {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                System.setProperty("file.encoding", "UTF-8");
                System.setProperty("sun.stdout.encoding", "UTF-8");
                System.setProperty("sun.stderr.encoding", "UTF-8");
                System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));
                new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
            }
        } catch (Exception e) {}
    }
    
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
        }
    }
    
    public static void printBanner() {
        clearScreen();
        System.out.println(RED + BOLD);
        System.out.println("  ╔════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("  ║                                                                                        ║");
        System.out.println("  ║   ████████╗██████╗ ██╗██╗   ██╗██╗ █████╗     ██╗███╗   ██╗      ██████╗██╗     ██╗    ║");
        System.out.println("  ║   ╚══██╔══╝██╔══██╗██║██║   ██║██║██╔══██╗    ██║████╗  ██║     ██╔════╝██║     ██║    ║");
        System.out.println("  ║      ██║   ██████╔╝██║██║   ██║██║███████║    ██║██╔██╗ ██║     ██║     ██║     ██║    ║");
        System.out.println("  ║      ██║   ██╔══██╗██║╚██╗ ██╔╝██║██╔══██║    ██║██║╚██╗██║     ██║     ██║     ██║    ║");
        System.out.println("  ║      ██║   ██║  ██║██║ ╚████╔╝ ██║██║  ██║    ██║██║ ╚████║     ╚██████╗███████╗██║    ║");
        System.out.println("  ║      ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═══╝  ╚═╝╚═╝  ╚═╝    ╚═╝╚═╝  ╚═══╝      ╚═════╝╚══════╝╚═╝    ║");
        System.out.println("  ║                                                                                        ║");
        System.out.println("  ║                           " + PINK + "⚡ ULTIMATE TRIVIA PLATFORM ⚡" + RED + "                               ║");
        System.out.println("  ║                                                                                        ║");
        System.out.println("  ╚════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.print(RESET);
        System.out.println(VIOLET + BOLD + "                                  ⏳ Loading System..." + RESET);
        animatedProgressBar();
        pause(500);
    }
    
    public static void animatedProgressBar() {
        String[] colors = {RED, PINK, VIOLET, BLUE};
        System.out.print("\n                  " + CYAN + "[");
        for (int i = 0; i < 40; i++) {
            System.out.print(colors[i % 4] + "█");
            System.out.flush();
            pause(25);
        }
        System.out.println(CYAN + "] " + PINK + BOLD + "100%" + RESET);
    }
    
    public static void printHeader(String title) {
        System.out.println("\n" + PINK + BOLD +
            "  ╔════════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(PINK + BOLD +
            "  ║" + RESET + " " + RED + BOLD + centerText("⚡ " + title + " ⚡", 86) + RESET + PINK + BOLD + "                                ║" + RESET);
        System.out.println(PINK + BOLD +
            "  ╚════════════════════════════════════════════════════════════════════════════════════════╝" + RESET);
    }
    
    public static void printSubHeader(String subtitle) {
        System.out.println(VIOLET + "  ┌──────────────────────────────────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(VIOLET + "  │ " + WHITE + BOLD + subtitle + padding(subtitle, 84) + VIOLET + " │" + RESET);
        System.out.println(VIOLET + "  └──────────────────────────────────────────────────────────────────────────────────────┘" + RESET);
    }
    
    public static void printMenu(String[] options) {
        String[] icons = {"🎯", "🚀", "⚡", "🔥", "💎", "🏆", "🎮", "⭐"};
        System.out.println();
        for (int i = 0; i < options.length; i++) {
            String color = getMenuColor(i);
            String icon = icons[i % icons.length];
            System.out.println(color + BOLD + "     ╔═[" + (i + 1) + "]════════════════════════════════════════════════════════════════════════╗" + RESET);
            System.out.println(color + "     ║" + RESET + " " + icon + " " + WHITE + options[i] + padding(options[i] + " " + icon, 73) + color + "║" + RESET);
            System.out.println(color + BOLD + "     ╚════════════════════════════════════════════════════════════════════════════╝" + RESET);
            pause(50);
        }
        System.out.println();
        System.out.print(PINK + BOLD + "     ┌─[" + CYAN + "Your Choice" + PINK + "]\n     └──➤ " + RESET);
    }
    
    private static String getMenuColor(int index) {
        return switch(index % 4) {
            case 0 -> RED;
            case 1 -> PINK;
            case 2 -> VIOLET;
            default -> BLUE;
        };
    }
    
    public static void printSuccess(String message) {
        System.out.println("\n" + PINK + BOLD + "  ✓ SUCCESS: " + RESET + WHITE + message + RESET);
    }
    
    public static void printError(String message) {
        System.out.println("\n" + RED + BOLD + "  ✗ ERROR: " + RESET + WHITE + message + RESET);
    }
    
    public static void printInfo(String message) {
        System.out.println(VIOLET + BOLD + "  ℹ INFO: " + RESET + WHITE + message + RESET);
    }
    
    public static void printWarning(String message) {
        System.out.println(CYAN + BOLD + "  ⚠ WARNING: " + RESET + WHITE + message + RESET);
    }
    
    public static void printDivider() {
        System.out.println(VIOLET + "  " + "─".repeat(88) + RESET);
    }
    
    public static void printDoubleDivider() {
        System.out.println(RED + "  " + "═".repeat(88) + RESET);
    }
    
    public static void printRainbowDivider() {
        String[] colors = {RED, PINK, VIOLET, BLUE};
        System.out.print("  ");
        for (int i = 0; i < 88; i++) {
            System.out.print(colors[i % colors.length] + "─" + RESET);
        }
        System.out.println();
    }
    
    public static void printQuestion(int num, int total, String question) {
        System.out.println("\n" + RED + BOLD +
            "  ╔════════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(RED + BOLD +
            "  ║ " + WHITE + "Question " + num + " of " + total + padding("Question " + num + " of " + total, 76) + RED + BOLD + "║" + RESET);
        System.out.println(RED + BOLD +
            "  ╚════════════════════════════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println("\n  " + WHITE + BOLD + wrapText(question, 84) + RESET + "\n");
    }
    
    public static void printOption(String label, String text) {
        String color = switch(label) {
            case "A" -> RED;
            case "B" -> PINK;
            case "C" -> VIOLET;
            case "D" -> BLUE;
            default -> WHITE;
        };
        String icon = "▶";
        System.out.println("      " + color + BOLD + "[" + label + "] " + icon + " " + WHITE + text + RESET);
    }
    
    public static void printScore(int score, int total) {
        clearScreen();
        double percentage = (score * 100.0) / total;
        String grade;
        String color;
        String emoji;
        
        if (percentage >= 90) {
            grade = "OUTSTANDING";
            color = PINK;
            emoji = "🏆";
        } else if (percentage >= 75) {
            grade = "EXCELLENT";
            color = VIOLET;
            emoji = "⭐";
        } else if (percentage >= 60) {
            grade = "GOOD";
            color = BLUE;
            emoji = "👍";
        } else if (percentage >= 50) {
            grade = "PASS";
            color = CYAN;
            emoji = "✔";
        } else {
            grade = "NEEDS IMPROVEMENT";
            color = RED;
            emoji = "📚";
        }
        
        System.out.println("\n" + PINK + BOLD +
            "  ╔════════════════════════════════════════════════════════════════════════════════════════╗\n" +
            "  ║                              📊 QUIZ RESULTS 📊                                       ║\n" +
            "  ╚════════════════════════════════════════════════════════════════════════════════════════╝\n" + RESET);
        
        System.out.println(VIOLET + "  ┌──────────────────────────────────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(VIOLET + "  │                                                                                      │" + RESET);
        System.out.println(VIOLET + "  │      " + WHITE + BOLD + "Score:       " + color + BOLD + score + " / " + total + 
            "                                                                  " + VIOLET + "│" + RESET);
        System.out.println(VIOLET + "  │      " + WHITE + BOLD + "Percentage:  " + color + BOLD + String.format("%.2f%%", percentage) +
            "                                                               " + VIOLET + "│" + RESET);
        System.out.println(VIOLET + "  │      " + WHITE + BOLD + "Grade:       " + color + BOLD + grade + " " + emoji +
            "                                                       " + VIOLET + "│" + RESET);
        System.out.println(VIOLET + "  │                                                                                      │" + RESET);
        System.out.println(VIOLET + "  └──────────────────────────────────────────────────────────────────────────────────────┘" + RESET);
        
        printRainbowProgressBar((int)percentage);
        
        if (percentage >= 90) {
            celebrateHighScore();
        } else if (percentage >= 75) {
            printStars();
        }
    }
    
    public static void printRainbowProgressBar(int percentage) {
        int filled = percentage / 5;
        int empty = 20 - filled;
        String[] colors = {RED, PINK, VIOLET, BLUE};
        
        System.out.print("\n  " + VIOLET + "[");
        for (int i = 0; i < filled; i++) {
            System.out.print(colors[i % colors.length] + "█");
        }
        System.out.print(GRAY + "░".repeat(empty));
        System.out.println(VIOLET + "] " + PINK + BOLD + percentage + "%" + RESET + "\n");
    }
    
    public static void celebrateHighScore() {
        String[] frames = {"  ✦  ", " ✦ ✦ ", "✦ ⭐ ✦", "⭐ 🏆 ⭐", "✦ ⭐ ✦", " ✦ ✦ ", "  ✦  "};
        System.out.println();
        for (String frame : frames) {
            System.out.print("\r" + PINK + BOLD + centerText(frame, 88) + RESET);
            System.out.flush();
            pause(120);
        }
        System.out.println("\n\n" + PINK + BOLD + centerText("🎉 PERFECT SCORE! AMAZING WORK! 🎉", 88) + RESET + "\n");
    }
    
    public static void printStars() {
        System.out.print("\n  ");
        String[] stars = {"⭐", "✨", "💫"};
        for (int i = 0; i < 15; i++) {
            System.out.print(PINK + stars[i % stars.length] + " " + RESET);
            System.out.flush();
            pause(50);
        }
        System.out.println("\n");
    }
    
    public static void printLeaderboardHeader() {
        System.out.println("\n" + RED + BOLD +
            "  ╔════════════════════════════════════════════════════════════════════════════════════════╗\n" +
            "  ║                               🏆 LEADERBOARD 🏆                                       ║\n" +
            "  ║                             ⚡ TOP PERFORMERS ⚡                                      ║\n" +
            "  ╚════════════════════════════════════════════════════════════════════════════════════════╝\n" + RESET);
        
        System.out.println(VIOLET + BOLD + String.format("  %-8s %-20s %-12s %-12s %-20s", 
            "RANK", "USERNAME", "SCORE", "ACCURACY", "DATE") + RESET);
        printDivider();
    }
    
    public static void printLeaderboardRow(int rank, String username, String score, double accuracy, String date) {
        String medal = "";
        String color = WHITE;
        
        switch(rank) {
            case 1 -> { medal = "🥇"; color = RED; }
            case 2 -> { medal = "🥈"; color = PINK; }
            case 3 -> { medal = "🥉"; color = VIOLET; }
            default -> { medal = "  "; color = BLUE; }
        }
        
        System.out.println(color + BOLD + String.format("  %s %-6s %-20s %-12s %-12s %-20s", 
            medal, rank, username, score, String.format("%.1f%%", accuracy), date) + RESET);
    }
    
    public static void printInput(String prompt) {
        System.out.print(PINK + BOLD + "  ➜ " + WHITE + prompt + VIOLET + " : " + RESET);
    }
    
    public static void printWelcome(String username, String role) {
        clearScreen();
        System.out.println("\n" + PINK + BOLD +
            "  ╔════════════════════════════════════════════════════════════════════════════════════════╗\n" +
            "  ║                                 ✨ WELCOME ✨                                         ║\n" +
            "  ╚════════════════════════════════════════════════════════════════════════════════════════╝\n" + RESET);
        
        System.out.println("  " + VIOLET + "┌── 👤 User Information" + RESET);
        System.out.println("  " + VIOLET + "│" + RESET);
        System.out.println("  " + VIOLET + "├── Username: " + RESET + PINK + BOLD + username + RESET);
        System.out.println("  " + VIOLET + "├── Role:     " + RESET + RED + BOLD + role + RESET);
        System.out.println("  " + VIOLET + "│" + RESET);
        System.out.println("  " + VIOLET + "└── " + RESET + PINK + "✓ Access Granted!" + RESET + "\n");
    }
    
    public static void printGoodbye() {
        clearScreen();
        String[] colors = {RED, PINK, VIOLET, BLUE};
        
        String[] goodbyeText = {
            "╔════════════════════════════════════════════════════════════════════════════════════════╗",
            "║                                                                                        ║",
            "║     ████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗    ██╗   ██╗ ██████╗ ██╗   ██╗         ║",
            "║     ╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝    ╚██╗ ██╔╝██╔═══██╗██║   ██║         ║",
            "║        ██║   ███████║███████║██╔██╗ ██║█████╔╝      ╚████╔╝ ██║   ██║██║   ██║         ║",
            "║        ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗       ╚██╔╝  ██║   ██║██║   ██║         ║",
            "║        ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗       ██║   ╚██████╔╝╚██████╔╝         ║",
            "║        ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝       ╚═╝    ╚═════╝  ╚═════╝          ║",
            "║                                                                                        ║",
            "║                       🎯 Thanks for using Trivia_IN_cli! 🎯                           ║",
            "║                               See you again soon! 👋                                   ║",
            "║                                                                                        ║",
            "╚════════════════════════════════════════════════════════════════════════════════════════╝"
        };
        
        System.out.println();
        for (int i = 0; i < goodbyeText.length; i++) {
            System.out.println("  " + colors[i % colors.length] + BOLD + goodbyeText[i] + RESET);
            pause(80);
        }
        System.out.println();
    }
    
    public static void printLoading(String text) {
        System.out.print("\n  " + VIOLET + BOLD + text + " " + RESET);
        String[] spinner = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};
        for (int i = 0; i < 25; i++) {
            System.out.print(PINK + BOLD + spinner[i % spinner.length] + RESET);
            pause(80);
            System.out.print("\b");
        }
        System.out.println(PINK + BOLD + "✓" + RESET + "\n");
    }
    
    private static String centerText(String text, int width) {
        int len = stripAnsi(text).length();
        int padding = (width - len) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }
    
    private static String padding(String text, int width) {
        int pad = width - stripAnsi(text).length();
        return " ".repeat(Math.max(0, pad));
    }
    
    private static String stripAnsi(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "")
                   .replaceAll("[🎯🚀⚡🔥💎🏆🎮⭐✓✗ℹ⚠➜👤✨💫🥇🥈🥉📊📚👍✔⏳▶🎉]", "");
    }
    
    private static String wrapText(String text, int width) {
        if (text.length() <= width) return text;
        int breakPoint = text.lastIndexOf(' ', width);
        if (breakPoint == -1) breakPoint = width;
        return text.substring(0, breakPoint) + "\n  " + wrapText(text.substring(breakPoint).trim(), width);
    }
    
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
