package code.mock;

import code.expressionlanguage.filenames.DefaultNameValidating;
import code.threads.FileStruct;
import code.util.StringList;
import code.util.StringMap;

public final class MockFileSet {
    private static final String SL = "/";
    private final DefaultNameValidating validating;
    private final StringMap<FileStruct> files;
    private final MockMillis mockMillis;
    private final StringList roots;
    private String currentPath = SL;

    public MockFileSet(long _initMillis, long[] _incrs, String[] _roots) {
        files = new StringMap<FileStruct>();
        validating = new DefaultNameValidating(new StringList());
        mockMillis = new MockMillis(_initMillis, _incrs);
        roots = new StringList(_roots);
        initRootsFolders();
    }
    public void initRootsFolders() {
        for (String s: roots) {
            files.addEntry(s, new FileStruct(null, mockMillis.millis()));
        }
        currentPath = roots.first();
    }
    public StringMap<FileStruct> getFiles() {
        return files;
    }

    public DefaultNameValidating getValidating() {
        return validating;
    }

    public MockMillis getMockMillis() {
        return mockMillis;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String _path) {
        String abs_ = MockFile.absolute(this, _path);
        if (!files.contains(abs_)) {
            return;
        }
        this.currentPath = abs_+ SL;
    }

    public String linkedRoot(String _root) {
        String link_ = "";
        for (String r: getRoots()) {
            if (_root.startsWith(r)) {
                link_ = r;
                break;
            }
        }
        return link_;
    }

    public StringList getRoots() {
        return roots;
    }
}
