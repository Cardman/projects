package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.util.IntMap;

public abstract class StaticRootBlock extends RootBlock {
    protected StaticRootBlock(int _idRowCol, String _name, String _packageName, OffsetAccessInfo _access,
                       String _templateDef, IntMap<String> _directSuperTypes,
                       int _offset) {
        super(_access, _templateDef, _directSuperTypes, _offset, contentRoot(_idRowCol, _packageName, _name));
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
        return false;
    }

}
