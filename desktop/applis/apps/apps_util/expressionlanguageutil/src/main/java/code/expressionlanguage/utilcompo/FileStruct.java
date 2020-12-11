package code.expressionlanguage.utilcompo;

public final class FileStruct {
    private byte[] content;
    private long lastDate;
    public FileStruct(byte[] _content) {
        setDatedContent(_content);
    }
    public FileStruct(byte[] _content, long _lastDate) {
        setContent(_content);
        lastDate = _lastDate;
    }

    public byte[] getContent() {
        return content;
    }

    public void setDatedContent(byte[] _content) {
        setContent(_content);
        updateLastDate();
    }

    public void setContent(byte[] _content) {
        content = _content;
    }

    public long getLastDate() {
        return lastDate;
    }

    public void updateLastDate() {
        lastDate = System.currentTimeMillis();
    }
}
