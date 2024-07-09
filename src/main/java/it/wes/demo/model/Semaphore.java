package it.wes.demo.model;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Semaphore {

    private int threadLimit;

    public Semaphore(int threadLimit) {
        if (threadLimit < 0) {
            throw new IllegalArgumentException("Il numero di thread concorrenti deve essere non negativo");
        }
        this.threadLimit = threadLimit;
    }

    /**
     * Decreases the value of the semaphore by 1 if the current value is greater than 0,
     * otherwise it waits until the value becomes greater than 0.
     * This method is synchronized to ensure thread safety.
     * If an InterruptedException occurs while waiting, an error message is logged
     * and the interrupt status is reset.
     */
    public synchronized void acquire() {
        while (threadLimit == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("Thread interrupted while waiting on semaphore", e);
                Thread.currentThread().interrupt();
                // Optionally, rethrow the exception or handle it according to your use case
            }
        }
        threadLimit--;
    }

    /**
     * Increases the value of the semaphore by 1 and notifies any waiting threads.
     */
    public synchronized void release() {
        threadLimit++;
        notify();
    }

    /**
     * Returns the current number of available permits.
     * @return the number of available permits
     */
    public synchronized int availablePermits() {
        return threadLimit;
    }
}
