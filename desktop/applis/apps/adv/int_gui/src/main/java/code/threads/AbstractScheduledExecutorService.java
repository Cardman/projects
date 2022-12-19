package code.threads;

public interface AbstractScheduledExecutorService extends AbstractShutdownExecutorService {
    AbstractFuture scheduleAtFixedRate(Runnable _command,
                        long _initialDelay,
                        long _period);
    AbstractFuture scheduleAtFixedRateNanos(Runnable _command,
                        long _initialDelay,
                        long _period);
}
