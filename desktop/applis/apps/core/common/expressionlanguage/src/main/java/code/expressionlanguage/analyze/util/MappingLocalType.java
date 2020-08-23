package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class MappingLocalType {
    private final String fullName;
    private final String suffixedName;
    private final RootBlock parentType;
    private final RootBlock type;

    public MappingLocalType(String fullName, String suffixedName, RootBlock parentType,RootBlock type) {
        this.fullName = fullName;
        this.suffixedName = suffixedName;
        this.parentType = parentType;
        this.type = type;
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
