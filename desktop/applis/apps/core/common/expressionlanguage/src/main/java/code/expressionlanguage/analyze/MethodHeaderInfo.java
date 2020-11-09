package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.MethodId;

public final class MethodHeaderInfo {
    private final RootBlock root;
    private final MethodId id;
    private final int rootNumber;
    private final int nameNumber;
    private final String importedReturnType;
    private final AccessEnum access;

    public MethodHeaderInfo(RootBlock _root, MethodId _id, int _rootNumber, int _nameNumber, String _importedReturnType, AccessEnum _access) {
        root = _root;
        this.id = _id;
        this.rootNumber = _rootNumber;
        this.nameNumber = _nameNumber;
        this.importedReturnType = _importedReturnType;
        this.access = _access;
    }

    public RootBlock getRoot() {
        return root;
    }

    public MethodId getId() {
        return id;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public int getNameNumber() {
        return nameNumber;
    }

    public String getImportedReturnType() {
        return importedReturnType;
    }

    public AccessEnum getAccess() {
        return access;
    }
}
