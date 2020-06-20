package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.*;

public final class InterfaceBlock extends RootBlock {

    private final boolean staticType;

    public InterfaceBlock(int _idRowCol, int _categoryOffset, String _name, String _packageName, OffsetAccessInfo _access,
                          String _templateDef, IntMap< String> _directSuperTypes, boolean _staticType, OffsetsBlock _offset) {
        super(_idRowCol, _categoryOffset, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
        staticType = _staticType;
    }

    @Override
    public boolean isStaticType() {
        return staticType;
    }
    @Override
    public void setupBasicOverrides(ContextEl _context,ExecRootBlock _exec) {
        useSuperTypesOverrides(_context);
    }

    @Override
    public boolean mustImplement() {
        return false;
    }
}
