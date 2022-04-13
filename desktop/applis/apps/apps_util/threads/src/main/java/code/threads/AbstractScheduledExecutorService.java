package code.threads;

public interface AbstractScheduledExecutorService {
    AbstractFuture scheduleAtFixedRate(Runnable _command,
                        long _initialDelay,
                        long _period);
    AbstractFuture scheduleAtFixedRateNanos(Runnable _command,
                        long _initialDelay,
                        long _period);
    void shutdown();
}
