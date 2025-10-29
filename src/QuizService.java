import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizService {

    public static List<Quiz> getAllPublishedQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "SELECT * FROM quizzes WHERE is_published = TRUE";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                quizzes.add(new Quiz(
                        rs.getInt("quiz_id"),
                        rs.getString("title"),
                        rs.getInt("time_limit_minutes"),
                        rs.getBoolean("is_published")
                ));
            }
        } catch (SQLException e) {
            UIHelper.printError("Error loading quizzes: " + e.getMessage());
        }
        return quizzes;
    }

    public static List<Question> getQuizQuestions(int quizId) {
        List<Question> questions = new ArrayList<>();
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "SELECT * FROM questions WHERE quiz_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                questions.add(new Question(
                        rs.getInt("question_id"),
                        rs.getInt("quiz_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_answer")
                ));
            }
        } catch (SQLException e) {
            UIHelper.printError("Error loading questions: " + e.getMessage());
        }
        return questions;
    }

    public static boolean createQuiz(String title, int timeLimit) {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "INSERT INTO quizzes (title, time_limit_minutes, is_published) VALUES (?, ?, FALSE)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setInt(2, timeLimit);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            UIHelper.printError("Error creating quiz: " + e.getMessage());
            return false;
        }
    }

    public static boolean addQuestion(int quizId, String question, String a, String b, String c, String d, String correct) {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "INSERT INTO questions (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quizId);
            stmt.setString(2, question);
            stmt.setString(3, a);
            stmt.setString(4, b);
            stmt.setString(5, c);
            stmt.setString(6, d);
            stmt.setString(7, correct);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            UIHelper.printError("Error adding question: " + e.getMessage());
            return false;
        }
    }

    public static boolean publishQuiz(int quizId) {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "UPDATE quizzes SET is_published = TRUE WHERE quiz_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quizId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            UIHelper.printError("Error publishing quiz: " + e.getMessage());
            return false;
        }
    }

    public static void saveAttempt(int userId, int quizId, int score, int total) {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "INSERT INTO quiz_attempts (user_id, quiz_id, score, total_questions) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, quizId);
            stmt.setInt(3, score);
            stmt.setInt(4, total);
            stmt.executeUpdate();
        } catch (SQLException e) {
            UIHelper.printError("Error saving attempt: " + e.getMessage());
        }
    }

    public static void displayLeaderboard(int quizId) {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "SELECT u.username, qa.score, qa.total_questions, qa.attempted_at " +
                    "FROM quiz_attempts qa JOIN users u ON qa.user_id = u.user_id " +
                    "WHERE qa.quiz_id = ? ORDER BY qa.score DESC, qa.attempted_at ASC LIMIT 10";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();

            UIHelper.printLeaderboardHeader();

            int rank = 1;
            while (rs.next()) {
                int score = rs.getInt("score");
                int total = rs.getInt("total_questions");
                double accuracy = (score * 100.0) / total;

                UIHelper.printLeaderboardRow(
                        rank++,
                        rs.getString("username"),
                        score + "/" + total,
                        accuracy,
                        rs.getTimestamp("attempted_at").toString().substring(0, 16)
                );
            }

            if (rank == 1) {
                UIHelper.printWarning("No attempts yet for this quiz!");
            }

        } catch (SQLException e) {
            UIHelper.printError("Error loading leaderboard: " + e.getMessage());
        }
    }

    public static void displayAllQuizzes() {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "SELECT * FROM quizzes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println(UIHelper.BRIGHT_YELLOW + UIHelper.BOLD +
                    String.format("   %-5s %-30s %-10s %-10s", "ID", "Title", "Time", "Published") + UIHelper.RESET);
            UIHelper.printDivider();

            while (rs.next()) {
                String published = rs.getBoolean("is_published") ? "✓" : "✗";
                System.out.println(String.format("   %-5d %-30s %-10d %-10s",
                        rs.getInt("quiz_id"),
                        rs.getString("title"),
                        rs.getInt("time_limit_minutes"),
                        published
                ));
            }
        } catch (SQLException e) {
            UIHelper.printError("Error loading quizzes: " + e.getMessage());
        }
    }

    public static void displayUserScores(int userId) {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "SELECT q.title, qa.score, qa.total_questions, qa.attempted_at " +
                    "FROM quiz_attempts qa JOIN quizzes q ON qa.quiz_id = q.quiz_id " +
                    "WHERE qa.user_id = ? ORDER BY qa.attempted_at DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            System.out.println(UIHelper.BRIGHT_CYAN + UIHelper.BOLD +
                    String.format("   %-30s %-12s %-12s %-20s", "Quiz", "Score", "Percentage", "Date") + UIHelper.RESET);
            UIHelper.printDivider();

            while (rs.next()) {
                int score = rs.getInt("score");
                int total = rs.getInt("total_questions");
                double percentage = (score * 100.0) / total;

                System.out.println(String.format("   %-30s %-12s %-12s %-20s",
                        rs.getString("title"),
                        score + "/" + total,
                        String.format("%.1f%%", percentage),
                        rs.getTimestamp("attempted_at").toString().substring(0, 16)
                ));
            }
        } catch (SQLException e) {
            UIHelper.printError("Error loading scores: " + e.getMessage());
        }
    }
}
