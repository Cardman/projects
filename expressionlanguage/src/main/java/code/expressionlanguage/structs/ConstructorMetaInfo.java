package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.StringList;

public final class ConstructorMetaInfo implements AnnotatedStruct {

    private static final String EMPTY_STRING = "";

    private final String className;
    private final String formClassName;
    private final ConstructorId realId;
    private final AccessEnum access;
    private final ConstructorId fid;
    private final String returnType;
    private String fileName = EMPTY_STRING;
    public ConstructorMetaInfo(String _className, AccessEnum _access, ConstructorId _realId, String _returnType,
                               ConstructorId _fid, String _formClassName) {
        className = _className;
        access = _access;
        realId = _realId;
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
    public String getClassName() {
        return className;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public ConstructorId getRealId() {
        return realId;
    }
    public boolean isVararg() {
        return realId.isVararg();
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
    
    public String getFormClassName() {
        return formClassName;
    }
    
    public String getName() {
        return formClassName;
    }

    public ConstructorId getFid() {
        return fid;
    }

    public String getReturnType() {
        return returnType;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasConstructor();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ConstructorMetaInfo)) {
            return false;
        }
        ConstructorMetaInfo info_ = (ConstructorMetaInfo) _other;
        if (!StringList.quickEq(className, info_.className)) {
            return false;
        }
        return realId.eq(info_.realId);
    }

}
