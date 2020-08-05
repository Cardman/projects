package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.*;

public abstract class ImmutableNameRootBlock extends RootBlock {

    private final String name;

    ImmutableNameRootBlock(int _idRowCol, String _name,
                           String _packageName, OffsetAccessInfo _access, String _templateDef,
                           IntMap<String> _directSuperTypes, OffsetsBlock _offset) {
        super(_idRowCol, _packageName,_access,_templateDef,_directSuperTypes,_offset);
        name = _name.trim();
    }

    @Override
    public String getName() {
        return name;
    }
}
