package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.util.*;

public final class EnumBlock extends RootBlock implements UniqueRootedBlock {

    private boolean canHaveElements = true;
    private boolean allow = true;
    private final CustList<InnerTypeOrElement> enumBlocks = new CustList<InnerTypeOrElement>();
    private int ltGt;
    public EnumBlock(int _idRowCol, String _name, String _packageName, OffsetAccessInfo _access,
                     String _templateDef, IntMap<String> _directSuperTypes, int _offset) {
        super(_access, _templateDef, _directSuperTypes, _offset, contentRoot(_idRowCol, _packageName, _name));
    }

    @Override
    public boolean withoutInstance() {
        return true;
    }
    @Override
    public void setupBasicOverrides(AnalyzedPageEl _page) {
        checkAccess(_page);
    }

    @Override
    public boolean mustImplement() {
        for (AbsBk b: ClassesUtil.getDirectChildren(this)) {
            if (b instanceof ElementBlock) {
                return true;
            }
        }
        return false;
    }

    public CustList<InnerTypeOrElement> getEnumBlocks() {
        return enumBlocks;
    }

    public boolean isCanHaveElements() {
        return canHaveElements;
    }

    public void setCanHaveElements(boolean _canHaveElements) {
        canHaveElements = _canHaveElements;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean _allow) {
        this.allow = _allow;
    }

    public int getLtGt() {
        return ltGt;
    }

    public void setLtGt(int _ltGt) {
        ltGt = _ltGt;
    }
}
