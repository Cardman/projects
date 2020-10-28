package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ConstructorMetaInfo extends WithoutParentStruct implements AnnotatedParamStruct {

    private static final String EMPTY_STRING = "";

    private final String declaringClass;
    private final String formDeclaringClass;
    private final ConstructorId realId;
    private final AccessEnum access;
    private final ConstructorId fid;
    private final String returnType;
    private final boolean invokable;
    private String fileName = EMPTY_STRING;
    private ExecNamedFunctionBlock annotableBlock;
    private ExecNamedFunctionBlock callee;
    private ExecRootBlock declaring;

    public ConstructorMetaInfo(){
        invokable = false;
        declaringClass = "";
        formDeclaringClass = "";
        realId = new ConstructorId("",new StringList(),false);
        fid = new ConstructorId("",new StringList(),false);
        access = AccessEnum.PRIVATE;
        returnType = "";
    }
    public ConstructorMetaInfo(String _declaringClass, AccessEnum _access, ConstructorId _realId, String _returnType,
                               ConstructorId _fid, String _formDeclaringClass) {
        invokable = true;
        declaringClass = _declaringClass;
        access = _access;
        realId = _realId;
        returnType = _returnType;
        fid = _fid;
        formDeclaringClass = _formDeclaringClass;
    }

    public ExecAnnotableBlock getAnnotableBlock() {
        return getAnnotableBlockParam();
    }

    public ExecNamedFunctionBlock getAnnotableBlockParam() {
        return annotableBlock;
    }

    public void setAnnotableBlock(ExecNamedFunctionBlock _annotableBlock) {
        this.annotableBlock = _annotableBlock;
    }

    public ExecNamedFunctionBlock getCallee() {
        return callee;
    }

    public void setCallee(ExecNamedFunctionBlock _callee) {
        this.callee = _callee;
    }

    public ExecRootBlock getDeclaring() {
        return declaring;
    }

    public void setDeclaring(ExecRootBlock _declaring) {
        this.declaring = _declaring;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
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
    
    public String getFormDeclaringClass() {
        return formDeclaringClass;
    }
    
    public String getName() {
        return formDeclaringClass;
    }

    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        if (callee != null) {
            return callee.getAnonymousLambda();
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }
    @Override
    public String getDeclaringClass() {
        return declaringClass;
    }

    public ConstructorId getFid() {
        return fid;
    }

    public String getReturnType() {
        return returnType;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getReflect().getAliasConstructor();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ConstructorMetaInfo)) {
            return false;
        }
        ConstructorMetaInfo info_ = (ConstructorMetaInfo) _other;
        if (!StringUtil.quickEq(declaringClass, info_.declaringClass)) {
            return false;
        }
        return realId.eq(info_.realId);
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(StringUtil.concat(declaringClass,";",realId.getSignature(_an)));
    }

    public StringList getParametersTypes() {
        return realId.getParametersTypes();
    }

    public boolean isInvokable() {
        return invokable;
    }
}
