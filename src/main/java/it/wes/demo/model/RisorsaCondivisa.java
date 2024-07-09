package it.wes.demo.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RisorsaCondivisa extends Thread {

    private final Semaphore resourceSemaphore;
    private final String nome;
    private static final int SLEEP_DURATION_MS = 100;

    public RisorsaCondivisa(String nome, Semaphore semaforo) {
        this.nome = nome;
        this.resourceSemaphore = semaforo;
    }

    /**
     * The {@code run} method is called when a thread starts executing. It represents the main logic that will be executed by the thread.
     * Within this method, the thread acquires a lock on the {@code semaforo} object using the {@code acquire} method, and then performs some operations.
     * The thread logs a message indicating that it is inside the thread and sleeps for 50 milliseconds.
     * After sleeping, the thread logs another message indicating that it is outside the thread.
     * Finally, the thread releases the lock on the {@code semaforo} object using the {@code release} method.
     * If an {@code InterruptedException} occurs while sleeping, the thread's interrupt status is set.
     */

    @Override
    public void run() {
        log.info("Risorse disponibili [{}]", resourceSemaphore.availablePermits());
        resourceSemaphore.acquire();
        log.info("👍👍 ENTRA il [{}]", nome);
        try {
            Thread.sleep(SLEEP_DURATION_MS);
        } catch (InterruptedException e) {
            handleInterruptedException(e);
        }
        log.info("👋👋 ESCE il [{}]", nome);
        log.info("-----------------------------------");
        resourceSemaphore.release();
    }

    private void handleInterruptedException(InterruptedException e) {
        Thread.currentThread().interrupt();
        log.error("Thread [{}] was interrupted", nome, e);
    }

}
