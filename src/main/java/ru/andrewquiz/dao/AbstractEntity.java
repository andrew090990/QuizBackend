package ru.andrewquiz.dao;

/**
 * Created by Andrew on 15.04.2017.
 */

public abstract class AbstractEntity<PK> {

    public abstract PK getId();

    public abstract void setId(PK id);

    public abstract void attachChildrenToParent();
}
