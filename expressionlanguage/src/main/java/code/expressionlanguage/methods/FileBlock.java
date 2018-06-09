package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.util.Numbers;
import code.util.StringList;

public final class FileBlock extends BracedBlock {

    private Numbers<Integer> lineReturns = new Numbers<Integer>();
    private Numbers<Integer> leftSpaces = new Numbers<Integer>();

    private StringList imports = new StringList();

    private Numbers<Integer> importsOffset = new Numbers<Integer>();

    private String fileName;

    private boolean predefined;

    public FileBlock(OffsetsBlock _offset, boolean _predefined) {
        super(null, 0, null, _offset);
        predefined = _predefined;
    }

    public FileBlock() {
        super(null, null, 0, null);
    }

    public boolean isPredefined() {
        return predefined;
    }

    public Numbers<Integer> getLeftSpaces() {
        return leftSpaces;
    }

    public Numbers<Integer> getLineReturns() {
        return lineReturns;
    }

    public StringList getImports() {
        return imports;
    }

    public Numbers<Integer> getImportsOffset() {
        return importsOffset;
    }

    @Override
    public String getTagName() {
        return null;
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
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }
}
