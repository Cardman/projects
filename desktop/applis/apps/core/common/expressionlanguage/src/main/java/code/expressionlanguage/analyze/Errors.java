package code.expressionlanguage.analyze;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.util.CustList;

public final class Errors {
    private CustList<FileBlock> files = new CustList<FileBlock>();

    public void putFile(ContextEl _context, FileBlock _file) {
        if (!_context.getAnalyzing().isGettingErrors()) {
            return;
        }
        files.add(_file);
    }

    public CustList<FileBlock> getFiles() {
        return files;
    }
}
