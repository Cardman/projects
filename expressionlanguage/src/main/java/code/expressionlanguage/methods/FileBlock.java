package code.expressionlanguage.methods;

import code.expressionlanguage.files.OffsetsBlock;
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
        super(_offset);
        predefined = _predefined;
        tabWidth = _tabWidth;
    }
    public final int getRowFile(int _sum) {
        int len_ = lineReturns.size();
        int i_ = 0;
        while (i_ < len_) {
            if (_sum < lineReturns.get(i_)) {
                break;
            }
            i_ += 2;
        }
        return i_/2;
    }
    public final int getColFile(int _sum, int _row) {
        int r_ = _row * 2;
        int j_ = 0;
        if (r_ < lineReturns.size()) {
            j_ = leftSpaces.get(_row - 1);
            int begin_ = lineReturns.get(r_ - 1)+1;
            for (int j = begin_; j <= _sum; j++) {
                if (tabulations.containsObj(j)) {
                    j_ += tabWidth;
                    j_ -= j_ % tabWidth;
                } else {
                    j_++;
                }
            }
        }
        return j_;
    }

    public int getTabWidth() {
        return tabWidth;
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

    public Numbers<Integer> getImportsOffset() {
        return importsOffset;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }
}
