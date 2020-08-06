package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecAnnotableParametersBlock;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.StringList;

public final class ConstructorMetaInfo extends WithoutParentStruct implements AnnotatedParamStruct {

    private static final String EMPTY_STRING = "";

    private final String className;
    private final String formClassName;
    private final ConstructorId realId;
    private final AccessEnum access;
    private final ConstructorId fid;
    private final String returnType;
    private final boolean invokable;
    private String fileName = EMPTY_STRING;
    private ExecAnnotableParametersBlock annotableBlock;
    private ExecMemberCallingsBlock callee;

    public ConstructorMetaInfo(){
        invokable = false;
        className = "";
        formClassName = "";
        realId = new ConstructorId("",new StringList(),false);
        fid = new ConstructorId("",new StringList(),false);
        access = AccessEnum.PRIVATE;
        returnType = "";
    }
    public ConstructorMetaInfo(String _className, AccessEnum _access, ConstructorId _realId, String _returnType,
                               ConstructorId _fid, String _formClassName) {
        invokable = true;
        className = _className;
        access = _access;
        realId = _realId;
        returnType = _returnType;
        fid = _fid;
        formClassName = _formClassName;
    }

    public ExecAnnotableBlock getAnnotableBlock() {
        return getAnnotableBlockParam();
    }

    public ExecAnnotableParametersBlock getAnnotableBlockParam() {
        return annotableBlock;
    }

    public void setAnnotableBlock(ExecAnnotableParametersBlock annotableBlock) {
        this.annotableBlock = annotableBlock;
    }

    public ExecMemberCallingsBlock getCallee() {
        return callee;
    }

    public void setCallee(ExecMemberCallingsBlock callee) {
        this.callee = callee;
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
    public String getClassName(ContextEl _contextEl) {
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

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(StringList.concat(className,";",realId.getSignature(_an)));
    }

    public StringList getParametersTypes() {
        return realId.getParametersTypes();
    }

    public boolean isInvokable() {
        return invokable;
    }
}
