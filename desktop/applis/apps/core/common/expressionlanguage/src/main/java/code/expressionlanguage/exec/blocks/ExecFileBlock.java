package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.ImportingBlock;
import code.util.*;

public final class ExecFileBlock extends ExecBracedBlock implements ImportingBlock {

    private Ints lineReturns;
    private Ints tabulations;

    private StringList imports;

    private final String fileName;

    private boolean predefined;

    private int tabWidth;
    public ExecFileBlock(FileBlock _file, int _tabWidth) {
        super(_file.getOffset());
        predefined = _file.isPredefined();
        tabWidth = _tabWidth;
        lineReturns=_file.getLineReturns();
        tabulations=_file.getTabulations();
        imports = _file.getImports();
        fileName = _file.getFileName();
    }

    public int getRowFile(int _sum) {
        int len_ = lineReturns.size();
        int i_ = 0;
        int s_ = Math.max(0,_sum);
        while (i_ < len_) {
            if (s_ <= lineReturns.get(i_)) {
                break;
            }
            i_++;
        }
        return i_;
    }
    public int getColFile(int _sum, int _row) {
        int j_ = 0;
        int s_ = Math.max(0,_sum);
        int begin_ = lineReturns.get(_row - 1)+1;
        for (int j = begin_; j < s_; j++) {
            if (tabulations.containsObj(j)) {
                j_ += tabWidth;
                j_ -= j_ % tabWidth;
            } else {
                j_++;
            }
        }
        return j_+1;
    }
    public boolean isPredefined() {
        return predefined;
    }

    @Override
    public StringList getImports() {
        return imports;
    }

    public String getFileName() {
        return fileName;
    }

    public String getRenderFileName() {
        return fileName+".html";
    }

    public static StringMap<String> export(ContextEl _cont) {
        return LinkageUtil.export(_cont);
    }

}
