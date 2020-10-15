package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class MappingLocalType {
    private final String fullName;
    private final String suffixedName;
    private final RootBlock parentType;
    private final RootBlock type;

    public MappingLocalType(String _fullName, String _suffixedName, RootBlock _parentType,RootBlock _type) {
        this.fullName = _fullName;
        this.suffixedName = _suffixedName;
        this.parentType = _parentType;
        this.type = _type;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSuffixedName() {
        return suffixedName;
    }

    public RootBlock getParentType() {
        return parentType;
    }

    public RootBlock getType() {
        return type;
    }
}
