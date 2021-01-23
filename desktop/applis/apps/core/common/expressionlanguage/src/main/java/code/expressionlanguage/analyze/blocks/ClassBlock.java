package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.fwd.blocks.AnaClassContent;
import code.util.*;

public final class ClassBlock extends RootBlock implements UniqueRootedBlock {

    private final AnaClassContent classContent;

    public ClassBlock(int _idRowCol, String _name, String _packageName, OffsetAccessInfo _access,
                      String _templateDef, IntMap<String> _directSuperTypes,
                      boolean _finalType,
                      boolean _abstractType, boolean _staticType,
                      OffsetsBlock _offset) {
        super(_idRowCol, _packageName, _access, _templateDef, _directSuperTypes, _offset, _name);
        classContent = new AnaClassContent(_finalType,_abstractType,_staticType);
    }

    @Override
    public boolean isStaticType() {
        return classContent.isStaticType();
    }
    @Override
    public void setupBasicOverrides(AnalyzedPageEl _page) {
        checkAccess(_page);
    }

    public boolean isFinalType() {
        return classContent.isFinalType();
    }

    public boolean isAbstractType() {
        return classContent.isAbstractType();
    }

    public AnaClassContent getClassContent() {
        return classContent;
    }

    @Override
    public boolean mustImplement() {
        return !isAbstractType();
    }

}
