package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class MappingLocalType {
    private final String fullName;
    private final String suffixedName;
    private final RootBlock type;
    private final String parentTypeFullName;
    private final String parentTypeGenericString;

    public MappingLocalType(String _fullName, String _suffixedName, RootBlock _type, String _parFullName, String _parGenericString) {
        this.fullName = _fullName;
        this.suffixedName = _suffixedName;
        parentTypeFullName = _parFullName;
        this.type = _type;
        parentTypeGenericString = _parGenericString;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSuffixedName() {
        return suffixedName;
    }

    public String getParentTypeGenericString() {
        return parentTypeGenericString;
    }

    public String getParentFullName() {
        return parentTypeFullName;
    }

    public RootBlock getType() {
        return type;
    }
}
