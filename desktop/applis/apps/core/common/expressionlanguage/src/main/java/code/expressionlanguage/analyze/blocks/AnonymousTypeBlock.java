package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.util.IntMap;

public final class AnonymousTypeBlock extends RootBlock implements UniqueRootedBlock {

    private int indexEnd;
    private int numberAnonType = -1;

    public AnonymousTypeBlock(int _idRowCol, String _packageName, OffsetAccessInfo _access, String _templateDef, IntMap<String> _directSuperTypes, int _offset, String _name) {
        super(_idRowCol, _packageName, _access, _templateDef, _directSuperTypes, _offset,_name);
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
        return true;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int _indexEnd) {
        this.indexEnd = _indexEnd;
    }

    public int getNumberAnonType() {
        return numberAnonType;
    }

    public void setNumberAnonType(int _numberAnonType) {
        this.numberAnonType = _numberAnonType;
    }

}
