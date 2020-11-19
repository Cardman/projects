package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.util.*;

public final class EnumBlock extends ImmutableNameRootBlock implements UniqueRootedBlock {

    private boolean canHaveElements = true;
    private int ltGt;
    public EnumBlock(int _idRowCol, String _name, String _packageName, OffsetAccessInfo _access,
                     String _templateDef, IntMap<String> _directSuperTypes, OffsetsBlock _offset) {
        super(_idRowCol, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
    }

    @Override
    public boolean isStaticType() {
        return true;
    }
    @Override
    public void setupBasicOverrides(AnalyzedPageEl _page) {
        checkAccess(_page);
    }

    @Override
    public boolean mustImplement() {
        for (Block b: ClassesUtil.getDirectChildren(this)) {
            if (b instanceof ElementBlock) {
                return true;
            }
        }
        return false;
    }

    public boolean isCanHaveElements() {
        return canHaveElements;
    }

    public void setCanHaveElements(boolean _canHaveElements) {
        canHaveElements = _canHaveElements;
    }

    public int getLtGt() {
        return ltGt;
    }

    public void setLtGt(int _ltGt) {
        ltGt = _ltGt;
    }
}
