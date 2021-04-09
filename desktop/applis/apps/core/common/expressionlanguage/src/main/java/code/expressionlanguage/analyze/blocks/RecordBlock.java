package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.util.IntMap;

public final class RecordBlock extends RootBlock {

    private final boolean mutable;
    public RecordBlock(boolean _mutable,int _idRowCol, String _name, String _packageName, OffsetAccessInfo _access,
                       String _templateDef, IntMap<String> _directSuperTypes,
                       int _offset) {
        super(_idRowCol, _packageName, _access, _templateDef, _directSuperTypes, _offset, _name);
        mutable = _mutable;
    }

    @Override
    public boolean withoutInstance() {
        return true;
    }
    @Override
    public void setupBasicOverrides(AnalyzedPageEl _page) {
        useSuperTypesOverrides(_page);
    }

    @Override
    public boolean mustImplement() {
        return true;
    }

    public boolean isMutable() {
        return mutable;
    }
}
