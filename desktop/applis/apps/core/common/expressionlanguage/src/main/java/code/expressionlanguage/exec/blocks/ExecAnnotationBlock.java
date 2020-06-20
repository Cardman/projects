package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.methods.RootBlock;
import code.util.StringList;

public final class ExecAnnotationBlock extends ExecRootBlock implements ExecInterfacable {
    private final StringList allSuperTypes = new StringList();

    public ExecAnnotationBlock(RootBlock _offset) {
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
