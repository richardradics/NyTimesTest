package io.richardradics.test.nytimes.core.listener;

public interface OnItemClick<T> {
    void onClick(T object);
}