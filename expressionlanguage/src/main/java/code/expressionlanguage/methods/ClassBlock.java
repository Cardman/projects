package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.util.*;
import code.util.StringList;

public final class ClassBlock extends RootBlock implements UniqueRootedBlock {

    private final StringList allSuperClasses = new StringList();

    private final StringList allSuperTypes = new StringList();

    private final StringList allInterfaces = new StringList();

    private String importedDirectSuperClass = "";
    private StringList importedDirectSuperInterfaces = new StringList();
    private final boolean finalType;
    private final boolean abstractType;
    private final boolean staticType;

    public ClassBlock(int _idRowCol, int _categoryOffset, String _name, String _packageName, OffsetAccessInfo _access,
                      String _templateDef, IntTreeMap< String> _directSuperTypes,
                      boolean _finalType,
                      boolean _abstractType, boolean _staticType,
                      OffsetsBlock _offset) {
        super(_idRowCol, _categoryOffset, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
        finalType = _finalType;
        abstractType = _abstractType;
        staticType = _staticType;
    }

    @Override
    public boolean isStaticType() {
        return staticType;
    }
    @Override
    public void setupBasicOverrides(ContextEl _context) {
        checkAccess(_context);
    }

    @Override
    public StringList getDirectGenericSuperTypes(Analyzable _classes) {
        StringList interfaces_ = new StringList();
        if (!StringList.quickEq(importedDirectSuperClass, _classes.getStandards().getAliasObject())) {
            interfaces_.add(importedDirectSuperClass);
        }
        interfaces_.addAllElts(importedDirectSuperInterfaces);
        return interfaces_;
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
    public StringList getAllInterfaces() {
        return allInterfaces;
    }

    @Override
    public GeneType belong() {
        return this;
    }

    @Override
    public boolean isFinalType() {
        return finalType;
    }

    @Override
    public boolean isAbstractType() {
        return abstractType;
    }

    @Override
    public boolean mustImplement() {
        return !isAbstractType();
    }

    @Override
    public void buildDirectGenericSuperTypes(ContextEl _classes) {
        IntTreeMap< String> rcs_;
        rcs_ = getRowColDirectSuperTypes();
        int i_ = 0;
        importedDirectSuperInterfaces.clear();
        for (String s: getDirectSuperTypes()) {
            int index_ = rcs_.getKey(i_);
            String s_ = _classes.resolveTypeInherits(s, this,index_,i_);
            i_++;
            String base_ = Templates.getIdFromAllTypes(s_);
            RootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof ClassBlock)) {
                importedDirectSuperInterfaces.add(s_);
            } else {
                importedDirectSuperClass = s_;
            }
        }
        if (importedDirectSuperClass.isEmpty()) {
            importedDirectSuperClass = _classes.getStandards().getAliasObject();
        }
    }

    @Override
    public void buildErrorDirectGenericSuperTypes(ContextEl _classes) {
        importedDirectSuperInterfaces.clear();
        importedDirectSuperClass = _classes.getStandards().getAliasObject();
    }
    @Override
    public String getImportedDirectGenericSuperClass() {
        return importedDirectSuperClass;
    }

    @Override
    public StringList getImportedDirectGenericSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }

    @Override
    public StringList getImportedDirectSuperTypes() {
        StringList l_ = new StringList(importedDirectSuperClass);
        l_.addAllElts(importedDirectSuperInterfaces);
        return l_;
    }
}
