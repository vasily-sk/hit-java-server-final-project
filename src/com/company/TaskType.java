package com.company;

public enum TaskType {
    IO(1), COMPUTATIONAL(2), UNKNOWN(3);

    int priority;

    TaskType(int priority) {
        this.priority = this.priority;
    }

    public int getPriority() {
        return priority;
    }

    public void getPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "priority=" + priority;
    }
}

