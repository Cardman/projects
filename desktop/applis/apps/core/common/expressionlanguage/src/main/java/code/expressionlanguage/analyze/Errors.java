package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.util.CustList;

public final class Errors {
    private CustList<FileBlock> files = new CustList<FileBlock>();

    public void putFile(FileBlock _file) {
        files.add(_file);
    }

    public CustList<FileBlock> getFiles() {
        return files;
    }
}
