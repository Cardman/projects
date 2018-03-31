package code.expressionlanguage;

import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class FileIndex implements Equallable<FileIndex>, Displayable {

    private static final String SEP = ":";

    private final String fileName;
    private final int index;

    public FileIndex(String _fileName, int _index) {
        fileName = _fileName;
        index = _index;
    }

    public int getIndex() {
        return index;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String display() {
        return StringList.concat(fileName,SEP,Integer.toString(index));
    }

    @Override
    public boolean eq(FileIndex _g) {
        if (!StringList.quickEq(fileName, _g.fileName)) {
            return false;
        }
        if (index != _g.index) {
            return false;
        }
        return true;
    }

}
