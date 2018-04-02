package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;

public final class FileBlock extends BracedBlock {

    private Numbers<Integer> lineReturns = new Numbers<Integer>();
    private Numbers<Integer> leftSpaces = new Numbers<Integer>();

    private StringList imports = new StringList();

    private Numbers<Integer> importsOffset = new Numbers<Integer>();

    public FileBlock(OffsetsBlock _offset) {
        super(null, 0, null, _offset);
    }

    public FileBlock() {
        super(null, null, 0, null);
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

    @Override
    public NatTreeMap<String, String> getClassNames(ContextEl _stds) {
        return new NatTreeMap<String, String>();
    }

}
