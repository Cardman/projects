package code.threads;

public interface AbstractDateFactory {
    AbstractDate newDate(long _millis);
    long timeZone(long _millis);
}
