package code.stream.core;

public final class ContentTime {
    private final byte[] content;
    private final long lastModifTime;

    public ContentTime(byte[] _content, long _lastModifTime) {
        this.content = _content;
        this.lastModifTime = _lastModifTime;
    }

    public byte[] getContent() {
        return content;
    }

    public long getLastModifTime() {
        return lastModifTime;
    }
}
