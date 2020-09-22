package com.company;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TaskWrapper<V> implements RunnableFuture<V>, Comparable<TaskWrapper<V>> {
    private TaskType priority;
    private FutureTask<V> task;

    private TaskWrapper(TaskType priority, FutureTask task) {
        this.task = task;
        this.priority = priority;
    }

    private TaskWrapper(TaskType priority, Runnable r) {
        this.task = new FutureTask<V>(r, null);
        this.priority = priority;
    }

    private TaskWrapper(TaskType priority, Callable<V> c) {
        this.task = new FutureTask(c);
        this.priority = priority;
    }

    @Override
    public void run() {
        if (task != null) {
            task.run();
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return task.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return task.isCancelled();
    }

    @Override
    public boolean isDone() {
        return task.isDone();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        try {
            return task.get();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        try {
            return task.get(timeout, unit);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int compareTo(TaskWrapper otherTaskWrapper) {
        return Integer.compare(this.priority.getPriority(), otherTaskWrapper.priority.getPriority());
    }

    @Override
    public String toString() {
        return "priority = " + this.priority + " task= " + this.task.toString();
    }
}