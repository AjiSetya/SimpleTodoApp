package com.nbs.simpletodoapp.db;

import com.orm.SugarRecord;

/**
 * Created by Sidiq on 26/02/2016.
 */
public class Note extends SugarRecord{
    String title, task, date;
    Long id;

    public Note(){

    }

    public Note(Long id, String title, String task, String date){
        this.id = id;
        this.title = title;
        this.task = task;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
