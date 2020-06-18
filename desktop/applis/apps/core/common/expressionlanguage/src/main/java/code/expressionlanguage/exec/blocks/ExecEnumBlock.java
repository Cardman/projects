package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.methods.RootBlock;
import code.util.StringList;

public final class ExecEnumBlock extends ExecRootBlock implements ExecUniqueRootedBlock {

    private final StringList allSuperTypes = new StringList();

    private String importedDirectSuperClass = "";
    private StringList importedDirectSuperInterfaces = new StringList();
    public ExecEnumBlock(RootBlock _offset) {
        super(_offset);
    }

    @Override
    public boolean isFinalType() {
        return true;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public boolean isStaticType() {
        return true;
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
