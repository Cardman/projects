package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.fwd.blocks.AnaClassContent;
import code.expressionlanguage.fwd.blocks.AnaRootBlockContent;
import code.util.*;

public final class ClassBlock extends RootBlock implements UniqueRootedBlock {

    private final AnaClassContent classContent;

    public ClassBlock(OffsetAccessInfo _access,
                      String _templateDef, IntMap<String> _directSuperTypes,
                      int _offset, AnaRootBlockContent _content, AnaClassContent _classContent) {
        super(_access, _templateDef, _directSuperTypes, _offset, _content);
        classContent = _classContent;
    }

    @Override
    public boolean withoutInstance() {
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
