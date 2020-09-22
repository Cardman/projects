package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.util.*;

public final class ClassBlock extends ImmutableNameRootBlock implements UniqueRootedBlock {

    private final boolean finalType;
    private final boolean abstractType;
    private final boolean staticType;

    public ClassBlock(int _idRowCol, String _name, String _packageName, OffsetAccessInfo _access,
                      String _templateDef, IntMap<String> _directSuperTypes,
                      boolean _finalType,
                      boolean _abstractType, boolean _staticType,
                      OffsetsBlock _offset) {
        super(_idRowCol, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
        finalType = _finalType;
        abstractType = _abstractType;
        staticType = _staticType;
    }

    @Override
    public boolean isStaticType() {
        return staticType;
    }
    @Override
    public void setupBasicOverrides(AnalyzedPageEl _page) {
        checkAccess(_page);
    }

    public boolean isFinalType() {
        return finalType;
    }

    public boolean isAbstractType() {
        return abstractType;
    }

    @Override
    public boolean mustImplement() {
        return !isAbstractType();
    }

}
