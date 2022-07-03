package code.mock;

import code.stream.core.AbstractZipStreamOut;
import code.stream.core.ContentTime;
import code.util.StringMap;

public final class MockZipStreamOut implements AbstractZipStreamOut {
    private final StringMap<ContentTime> files = new StringMap<ContentTime>();

    @Override
    public boolean closeEntry() {
        return true;
    }

    @Override
    public void close() {
        closeEntry();
    }

    @Override
    public boolean putNextEntry(String _key, long _lastModifTime, byte[] _content) {
        files.put(_key,new ContentTime(_content,_lastModifTime));
        return true;
    }

    @Override
    public boolean putNextEmptyEntry(String _key, long _lastModifTime) {
        files.put(_key,new ContentTime(null,_lastModifTime));
        return true;
    }

    @Override
    public byte[] byteArray() {
        return new MockZipFact().zipBinFiles(files);
    }
}
