package code.threads;

public final class FileStruct {
    private byte[] content;
    private long lastDate;
    public FileStruct(byte[] _content,AbstractThreadFactory _threadFact) {
        setDatedContent(_content,_threadFact);
    }
    public FileStruct(byte[] _content, long _lastDate) {
        setContent(_content);
        lastDate = _lastDate;
    }

    public byte[] getContent() {
        return content;
    }

    public void setDatedContent(byte[] _content,AbstractThreadFactory _threadFact) {
        setContent(_content);
        updateLastDate(_threadFact);
    }

    public void setContent(byte[] _content) {
        content = _content;
    }

    public long getLastDate() {
        return lastDate;
    }

    public void updateLastDate(AbstractThreadFactory _threadFact) {
        lastDate = _threadFact.millis();
    }
}
