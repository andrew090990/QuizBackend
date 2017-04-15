package ru.andrewquiz.dao;

import java.util.Calendar;

/**
 * Created by Andrew on 15.04.2017.
 */

public abstract class AbstractEntity<PK> {

    public abstract PK getId();

    public abstract void setId(PK id);

    public abstract Calendar getCreatedAt();

    public abstract void setCreatedAt(Calendar createdAt);

    public abstract Calendar getUpdatedAt();

    public abstract void setUpdatedAt(Calendar createdAt);
}
