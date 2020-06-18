package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.GeneInterface;
import code.expressionlanguage.methods.RootBlock;
import code.util.StringList;

public final class ExecInterfaceBlock extends ExecRootBlock implements GeneInterface,ExecInterfacable {
    private final StringList allSuperTypes = new StringList();
    private StringList importedDirectSuperInterfaces = new StringList();
    private final boolean staticType;
    public ExecInterfaceBlock(RootBlock _offset) {
        super(_offset);
        staticType = _offset.isStaticType();
    }

    @Override
    public boolean isFinalType() {
        return false;
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
        return staticType;
    }

    @Override
    public StringList getImportedDirectSuperTypes() {
        return new StringList(importedDirectSuperInterfaces);
    }

    @Override
    public void buildTypes(RootBlock _uniq) {
        importedDirectSuperInterfaces = _uniq.getImportedDirectSuperTypes();
    }

    public StringList getImportedDirectSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }
}
