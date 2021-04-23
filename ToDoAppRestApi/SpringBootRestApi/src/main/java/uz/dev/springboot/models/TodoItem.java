package uz.dev.springboot.models;

import javax.persistence.*;

@Entity
@Table(name = "todo_item")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;


    private String title;
    private String body;
    private boolean completed;

    public TodoItem() {
    }

    public TodoItem(Long _id, String title, String body, boolean completed) {
        this._id = _id;
        this.title = title;
        this.body = body;
        this.completed = completed;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
