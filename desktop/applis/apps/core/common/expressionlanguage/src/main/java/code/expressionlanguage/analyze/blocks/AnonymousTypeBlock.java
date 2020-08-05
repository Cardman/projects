package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.IntMap;

public final class AnonymousTypeBlock extends RootBlock implements UniqueRootedBlock {
    private String name = "";
    private int indexEnd;
    public AnonymousTypeBlock(int _idRowCol, String _packageName, OffsetAccessInfo _access, String _templateDef, IntMap<String> _directSuperTypes, OffsetsBlock _offset) {
        super(_idRowCol, _packageName, _access, _templateDef, _directSuperTypes, _offset);
    }

    @Override
    public boolean isStaticType() {
        return true;
    }

    @Override
    public void setupBasicOverrides(ContextEl _context) {
        checkAccess(_context);
    }

    @Override
    public boolean mustImplement() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int indexEnd) {
        this.indexEnd = indexEnd;
    }
}
