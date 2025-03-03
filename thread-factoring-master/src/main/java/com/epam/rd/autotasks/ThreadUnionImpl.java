package com.epam.rd.autotasks;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUnionImpl implements ThreadUnion {
    private final String name;
    private final AtomicInteger threadCount = new AtomicInteger(0);
    private final Set<Thread> activeThreads = Collections.synchronizedSet(new HashSet<>());
    private final List<FinishedThreadResult> finishedResults = Collections.synchronizedList(new ArrayList<>());
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);

    public ThreadUnionImpl(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        if (isShutdown.get()) {
            throw new IllegalStateException("Thread has been shutdown, cannot create new thread");
        }
        int threadNumber = threadCount.getAndIncrement();
        Thread thread = new Thread(() -> {
            if (isShutdown.get()) {
                Thread.currentThread().interrupt();
            }
            try {
                activeThreads.add(Thread.currentThread());
                runnable.run();
                finishedResults.add(new FinishedThreadResult(Thread.currentThread().getName()));
            } catch (Throwable throwable) {
                finishedResults.add(new FinishedThreadResult(Thread.currentThread().getName(), throwable));
            } finally {
                activeThreads.remove(Thread.currentThread());
            }
        });
        thread.setName(name + "-worker-" + threadNumber);
        return thread;
    }

    @Override
    public int totalSize() {
        return threadCount.get();
    }

    @Override
    public int activeSize() {
        return activeThreads.size();
    }

    @Override
    public void shutdown() {
        isShutdown.set(true);
        synchronized (activeThreads) {
            for (Thread thread : activeThreads) {
                thread.interrupt();
            }
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown.get();
    }

    @Override
    public void awaitTermination() {
        List<Thread> threadsToWait;
        synchronized (activeThreads) {
            threadsToWait = new ArrayList<>(activeThreads);
        }
        for (Thread thread : threadsToWait) {
            try {
                if (thread.isAlive()) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public boolean isFinished() {
        return isShutdown.get() && activeThreads.isEmpty();
    }

    @Override
    public List<FinishedThreadResult> results() {
        return Collections.unmodifiableList(finishedResults);
    }
}