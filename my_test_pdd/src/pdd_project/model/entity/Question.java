package pdd_project.model.entity;

import java.util.List;

/**
 * Created by Alexey Sushko
 * Создадим сущность вопроса где будут поля самого текста вопроса, его порядкового номера, массива с вариантами ответов
 * и собственно правильным вариантом ответа
 */
public class Question {

    private List<String> answers=null;
    private String rightAnswers;
    private String title;
    private String description;

    public Question(List<String> answers, String rightAnswers, String title, String description) {

        this.answers = answers;
        this.rightAnswers = rightAnswers;
        this.title = title;
        this.description = description;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(String rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Question{" +
                "  answers=" + answers +
                ", rightAnswers=" + rightAnswers +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (answers != null ? !answers.equals(question.answers) : question.answers != null) return false;
        return rightAnswers != null ? rightAnswers.equals(question.rightAnswers) : question.rightAnswers == null;
    }

    @Override
    public int hashCode() {
        int result = answers != null ? answers.hashCode() : 0;
        result = 31 * result + (rightAnswers != null ? rightAnswers.hashCode() : 0);
        return result;
    }
}
