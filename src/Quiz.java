// Quiz.java

public class Quiz {
    private int quizId;
    private String title;
    private int timeLimit;
    private boolean isPublished;

    public Quiz(int quizId, String title, int timeLimit, boolean isPublished) {
        this.quizId = quizId;
        this.title = title;
        this.timeLimit = timeLimit;
        this.isPublished = isPublished;
    }

    public int getQuizId() { return quizId; }
    public String getTitle() { return title; }
    public int getTimeLimit() { return timeLimit; }
    public boolean isPublished() { return isPublished; }

    public void setTitle(String title) { this.title = title; }
    public void setTimeLimit(int timeLimit) { this.timeLimit = timeLimit; }
    public void setPublished(boolean published) { isPublished = published; }
}
