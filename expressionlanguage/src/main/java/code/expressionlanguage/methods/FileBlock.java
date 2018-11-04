package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.sml.RowCol;
import code.util.Numbers;
import code.util.StringList;

public final class FileBlock extends BracedBlock implements ImportingBlock {

    private Numbers<Integer> lineReturns = new Numbers<Integer>();
    private Numbers<Integer> leftSpaces = new Numbers<Integer>();
    private Numbers<Integer> tabulations = new Numbers<Integer>();

    private StringList imports = new StringList();

    private Numbers<Integer> importsOffset = new Numbers<Integer>();

    private String fileName;

    private boolean predefined;

    private int tabWidth;

    public FileBlock(OffsetsBlock _offset, boolean _predefined, int _tabWidth) {
        super(null, 0, null, _offset);
        predefined = _predefined;
        tabWidth = _tabWidth;
    }
    public final RowCol getRowColFile(int _sum) {
        RowCol rc_ = new RowCol();
        int len_ = lineReturns.size();
        int i_ = 0;
        while (i_ < len_) {
            if (_sum < lineReturns.get(i_)) {
                int j_;
                if (i_ > 0) {
                    j_ = leftSpaces.get(i_ / 2 - 1);
                    int begin_ = lineReturns.get(i_ - 1)+1;
                    for (int j = begin_; j <= _sum; j++) {
                        if (tabulations.containsObj(j)) {
                            j_ += tabWidth;
                            j_ -= j_ % tabWidth;
                        } else {
                            j_++;
                        }
                    }
                } else {
                    j_ = _sum;
                }
                rc_.setCol(j_);
                rc_.setRow(i_/2);
                break;
            }
            i_ += 2;
        }
        return rc_;
    }
    public boolean isPredefined() {
        return predefined;
    }

    public Numbers<Integer> getLeftSpaces() {
        return leftSpaces;
    }

    public Numbers<Integer> getTabulations() {
        return tabulations;
    }
    public Numbers<Integer> getLineReturns() {
        return lineReturns;
    }

    @Override
    public StringList getImports() {
        return imports;
    }

    @Override
    public Numbers<Integer> getImportsOffset() {
        return importsOffset;
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return null;
    }
}
