package pl.anportfolio;

import java.time.LocalDate;

public class Task {

    private String name;
    private String date;
    private boolean importance;

    public Task(String name, String date, boolean importance) {
        this.name = name;
        this.date = date;
        this.importance = importance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isImportance() {
        return importance;
    }

    public void setImportance(boolean importance) {
        this.importance = importance;
    }

    @Override
    public String toString() {
        return name + ", " + date + ", " + importance;
    }
}
