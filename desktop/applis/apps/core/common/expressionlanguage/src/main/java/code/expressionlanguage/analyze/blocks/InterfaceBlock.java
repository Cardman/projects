package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.fwd.blocks.AnaRootBlockContent;
import code.util.*;

public final class InterfaceBlock extends RootBlock {

    private final boolean staticType;

    public InterfaceBlock(OffsetAccessInfo _access,
                          String _templateDef, IntMap<String> _directSuperTypes, boolean _staticType, int _offset, AnaRootBlockContent _content) {
        super(_access, _templateDef, _directSuperTypes, _offset, _content);
        staticType = _staticType;
    }

    @Override
    public boolean withoutInstance() {
        return staticType;
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
