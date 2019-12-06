package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.StringList;


public final class MethodMetaInfo implements AnnotatedStruct {

    private static final String EMPTY_STRING = "";

    private final String className;
    private final String formClassName;

    private final MethodId realId;
    private final MethodId fid;

    private final AccessEnum access;
    private final MethodModifier modifier;

    private final String returnType;
    private String fileName = EMPTY_STRING;
    public MethodMetaInfo(AccessEnum _access, String _className, MethodId _realId, MethodModifier _modifier, String _returnType,
                          MethodId _fid, String _formClassName) {
        access = _access;
        className = _className;
        realId = _realId;
        modifier = _modifier;
        returnType = _returnType;
        fid = _fid;
        formClassName = _formClassName;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    public String getClassName() {
        return className;
    }
    public String getFormClassName() {
        return formClassName;
    }
    public String getName() {
        return realId.getName();
    }
    public MethodId getFid() {
        return fid;
    }
    public StringList getParameterNames() {
        return new StringList(realId.getParametersTypes());
    }
    public boolean isVararg() {
        return realId.isVararg();
    }
    public MethodId getRealId() {
        return realId;
    }
    public boolean isStatic() {
        return modifier == MethodModifier.STATIC;
    }
    public boolean isStaticCall() {
        return modifier == MethodModifier.STATIC_CALL;
    }
    public boolean isInstanceMethod() {
        return !isWideStatic();
    }
    public boolean isWideStatic() {
        return modifier == MethodModifier.STATIC || modifier == MethodModifier.STATIC_CALL;
    }
    public boolean isAbstract() {
        return modifier == MethodModifier.ABSTRACT;
    }
    
    public boolean isFinal() {
        return modifier == MethodModifier.FINAL;
    }
    
    public boolean isNormal() {
        return modifier == MethodModifier.NORMAL;
    }
    public boolean isPublic() {
        return access == AccessEnum.PUBLIC;
    }
    
    public boolean isProtected() {
        return access == AccessEnum.PROTECTED;
    }
    
    public boolean isPackage() {
        return access == AccessEnum.PACKAGE;
    }

    public boolean isPrivate() {
        return access == AccessEnum.PRIVATE;
    }

    public String getReturnType() {
        return returnType;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasMethod();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof MethodMetaInfo)) {
            return false;
        }
        MethodMetaInfo info_ = (MethodMetaInfo) _other;
        if (!StringList.quickEq(className, info_.className)) {
            return false;
        }
        return realId.eq(info_.realId);
    }

}
