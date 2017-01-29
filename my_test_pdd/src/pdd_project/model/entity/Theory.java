package pdd_project.model.entity;

/**
 * Created by Alexey Sushko
 * Сущность теории в которой только два поля: сам текст теории и его порядковый номер
 */
public class Theory {
    private String textTheory;
    private String title;

    public Theory(String textTheory, String title){
        this.textTheory = textTheory;
        this.title = title;
    }

    public String getTextTheory() {
        return textTheory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Theory{" +
                "textTheory='" + textTheory + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
