package pdd_project.model.entity;

/**
 * Created by Alexey Sushko
 * Сущность пользователя.
 * Пользователь имеет поля id- которое не является уникальным,
 * name - является уникальным полем, password - не уникальное поле, хранится только в шифрованом виде,
 * status - для всех одинаков, progress - показывает процент последнего прохождения экзамена
 */
public class User {

    private int id = -1;
    private String name = null;
    private String password = null;
    private boolean status = false;
    private int progress = -1;

    public User(int id, String name, String password, boolean status, int progress) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.status = status;
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", progress=" + progress +
                '}';
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
