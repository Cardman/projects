package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.ImportingBlock;
import code.util.*;

public final class ExecFileBlock extends ExecBracedBlock {

    private Ints lineReturns;
    private Ints tabulations;

    private final String fileName;

    private boolean predefined;

    public ExecFileBlock(FileBlock _file) {
        super(_file.getOffset());
        predefined = _file.isPredefined();
        lineReturns=_file.getLineReturns();
        tabulations=_file.getTabulations();
        fileName = _file.getFileName();
    }

    public FileMetrics getMetrics(int _tabWidth) {
        return new FileMetrics(lineReturns,tabulations,_tabWidth);
    }

    public boolean isPredefined() {
        return predefined;
    }

    public String getFileName() {
        return fileName;
    }

    public String getRenderFileName() {
        return fileName+".html";
    }

    public static StringMap<String> errors(ContextEl _cont) {
        return LinkageUtil.errors(_cont);
    }
    public static StringMap<String> export(ContextEl _cont) {
        return LinkageUtil.export(_cont);
    }

}
