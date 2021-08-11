package code.bean.nat.analyze.opers;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.TypeInfo;

public final class NatScopeFilterType {
    private final TypeInfo typeInfo;
    private final AnaFormattedRootBlock formatted;
    private final String fullName;

    public NatScopeFilterType(TypeInfo _typeInfo) {
        typeInfo = _typeInfo;
        formatted = _typeInfo.getFormatted();
        fullName = _typeInfo.getTypeId();
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public AnaFormattedRootBlock getFormatted() {
        return formatted;
    }

    public String getFullName() {
        return fullName;
    }

}
