package com.lms.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class StudentBook {
    private String stuName;
    private String bookName;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
