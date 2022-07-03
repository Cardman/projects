package code.threads;

public interface AbstractDate {
    String format(AbstractDateFactory _offset,String _format);
    String format(long _offset,String _format);
}
