package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.analyze.blocks.ClassBlock;
import code.util.StringList;

public final class ExecClassBlock extends ExecRootBlock implements ExecUniqueRootedBlock {

    private final boolean finalType;
    private final boolean abstractType;
    private final boolean staticType;

    public ExecClassBlock(ClassBlock _offset) {
        super(_offset);
        finalType = _offset.isFinalType();
        abstractType = _offset.isAbstractType();
        staticType = _offset.isStaticType();
    }

    @Override
    public boolean isFinalType() {
        return finalType;
    }

    @Override
    public boolean isAbstractType() {
        return abstractType;
    }

    @Override
    public boolean isStaticType() {
        return staticType;
    }


}
