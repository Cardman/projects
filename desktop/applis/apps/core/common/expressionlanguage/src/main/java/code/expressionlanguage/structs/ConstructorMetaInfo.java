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

    private final String className;
    private final String formClassName;
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

    public ExecNamedFunctionBlock getAnnotableBlockParam() {
        return annotableBlock;
    }

    public void setAnnotableBlock(ExecNamedFunctionBlock annotableBlock) {
        this.annotableBlock = annotableBlock;
    }

    public ExecNamedFunctionBlock getCallee() {
        return callee;
    }

    public void setCallee(ExecNamedFunctionBlock callee) {
        this.callee = callee;
    }

    public ExecRootBlock getDeclaring() {
        return declaring;
    }

    public void setDeclaring(ExecRootBlock declaring) {
        this.declaring = declaring;
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

    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        if (callee != null) {
            return callee.getAnonymousLambda();
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }
    @Override
    public String getDeclaringClass() {
        return getClassName();
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
        if (!StringUtil.quickEq(className, info_.className)) {
            return false;
        }
        return realId.eq(info_.realId);
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(StringUtil.concat(className,";",realId.getSignature(_an)));
    }

    public StringList getParametersTypes() {
        return realId.getParametersTypes();
    }

    public boolean isInvokable() {
        return invokable;
    }
}
