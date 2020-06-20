package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.methods.RootBlock;
import code.util.StringList;

public final class ExecEnumBlock extends ExecRootBlock implements ExecUniqueRootedBlock {

    private final StringList allSuperTypes = new StringList();

    public ExecEnumBlock(RootBlock _offset) {
        super(_offset);
    }

    @Override
    public boolean isFinalType() {
        return true;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public boolean isStaticType() {
        return true;
    }

}
