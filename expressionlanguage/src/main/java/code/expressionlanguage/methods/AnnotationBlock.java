package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.common.GeneType;
import code.util.NatTreeMap;
import code.util.StringList;

public final class AnnotationBlock extends RootBlock {

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    public AnnotationBlock(ContextEl _importingPage,
            BracedBlock _m, int _idRowCol, int _categoryOffset, String _name,
            String _packageName, OffsetAccessInfo _access, String _templateDef,
            NatTreeMap<Integer, String> _directSuperTypes, OffsetsBlock _offset) {
        super(_importingPage, _m, _idRowCol, _categoryOffset, _name,
                _packageName, _access, _templateDef, _directSuperTypes, _offset);
    }

    @Override
    public boolean isStaticType() {
        return true;
    }
    @Override
    public StringList getDirectGenericSuperTypesBuild(Analyzable _classes) {
        return new StringList(getDirectSuperTypes());
    }

    @Override
    public StringList getDirectGenericSuperTypes(Analyzable _classes) {
        return new StringList(_classes.getStandards().getAliasAnnotation());
    }

    @Override
    public StringList getAllSuperClasses() {
        return allSuperClasses;
    }

    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public boolean isFinalType() {
        return true;
    }

    @Override
    public boolean isAbstractType() {
        return false;
    }

    @Override
    public StringList getAllInterfaces() {
        return getAllSuperClasses();
    }

    @Override
    public boolean mustImplement() {
        return false;
    }

    @Override
    public GeneType belong() {
        return this;
    }

    @Override
    public void setupBasicOverrides(ContextEl _context) {
        useSuperTypesOverrides(_context);
    }

    @Override
    public void buildDirectGenericSuperTypes(ContextEl _classes) {
    }

    @Override
    public void buildErrorDirectGenericSuperTypes(ContextEl _classes) {
    }
    @Override
    public StringList getAllGenericInterfaces(Analyzable _classes) {
        return new StringList();
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    public StringList getImportedDirectSuperTypes() {
        return allSuperTypes;
    }
    
}
