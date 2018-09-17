package ru.andrewquiz.dao;

import java.util.Calendar;

/**
 * Created by Andrew on 15.04.2017.
 */

public interface Trackable {

    public Calendar getCreatedAt();

    public void setCreatedAt(Calendar createdAt);

    public Calendar getUpdatedAt();

    public void setUpdatedAt(Calendar createdAt);
}
