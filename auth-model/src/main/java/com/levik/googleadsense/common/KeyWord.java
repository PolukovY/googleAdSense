package com.levik.googleadsense.common;

import java.util.Objects;

public class KeyWord {
    private long id;
    private String name;

    public KeyWord() {
    }

    public KeyWord(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyWord keyWord = (KeyWord) o;
        return getId() == keyWord.getId() &&
                Objects.equals(getName(), keyWord.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "KeyWord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
