package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecAnnotableParametersBlock;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.StringList;


public final class MethodMetaInfo extends WithoutParentStruct implements AnnotatedParamStruct {

    private static final String EMPTY_STRING = "";

    private final String className;
    private final String formClassName;

    private final MethodId realId;
    private final MethodId fid;

    private final AccessEnum access;
    private final MethodModifier modifier;

    private final String returnType;
    private String fileName = EMPTY_STRING;
    private boolean expCast;
    private boolean invokable;
    private ExecAnnotableParametersBlock annotableBlock;
    private ExecMemberCallingsBlock callee;

    public MethodMetaInfo() {
        invokable = false;
        className = "";
        formClassName = "";
        realId = new MethodId(MethodAccessKind.INSTANCE,"",new StringList());
        fid = new MethodId(MethodAccessKind.INSTANCE,"",new StringList());
        access = AccessEnum.PRIVATE;
        modifier = MethodModifier.NORMAL;
        returnType = "";
    }
    public MethodMetaInfo(AccessEnum _access, String _className, MethodId _realId, MethodModifier _modifier, String _returnType,
                          MethodId _fid, String _formClassName) {
        invokable = true;
        access = _access;
        className = _className;
        realId = _realId;
        modifier = _modifier;
        returnType = _returnType;
        fid = _fid;
        formClassName = _formClassName;
    }

    public void setInvokable(boolean invokable) {
        this.invokable = invokable;
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

    public void setExpCast(boolean expCast) {
        this.expCast = expCast;
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
    public MethodAccessKind getKind() {
        return realId.getKind();
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
    public String getClassName(ContextEl _contextEl) {
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

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(StringList.concat(className,".", getSignature(_an)));
    }

    public String getSignature(ContextEl _an) {
        return realId.getSignature(_an);
    }

    public boolean isInvokable() {
        return invokable;
    }

    public boolean isExpCast() {
        return expCast;
    }
}
