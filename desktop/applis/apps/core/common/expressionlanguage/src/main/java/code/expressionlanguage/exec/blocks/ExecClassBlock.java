package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.methods.ClassBlock;
import code.expressionlanguage.methods.RootBlock;
import code.util.StringList;

public final class ExecClassBlock extends ExecRootBlock implements ExecUniqueRootedBlock {

    private final StringList allSuperTypes = new StringList();

    private String importedDirectSuperClass = "";
    private StringList importedDirectSuperInterfaces = new StringList();
    private final boolean finalType;
    private final boolean abstractType;
    private final boolean staticType;

    public ExecClassBlock(ClassBlock _offset) {
        super(_offset);
        finalType = _offset.isFinalType();
        abstractType = _offset.isAbstractType();
        staticType = _offset.isStaticType();
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
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public boolean isStaticType() {
        return staticType;
    }

    @Override
    public StringList getImportedDirectSuperTypes() {
        StringList l_ = new StringList(importedDirectSuperClass);
        l_.addAllElts(importedDirectSuperInterfaces);
        return l_;
    }

    @Override
    public void buildTypes(StringList _ints,String _sup) {
        importedDirectSuperInterfaces = _ints;
        importedDirectSuperClass = _sup;
    }

    @Override
    public String getImportedDirectGenericSuperClass() {
        return importedDirectSuperClass;
    }

    @Override
    public StringList getImportedDirectGenericSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }


}
