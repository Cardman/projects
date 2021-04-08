package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ConstructorMetaInfo extends AbsAnnotatedStruct implements AnnotatedParamStruct {

    private static final String EMPTY_STRING = "";

    private final String declaringClass;
    private final String formDeclaringClass;
    private final ConstructorId realId;
    private final AccessEnum access;
    private final ConstructorId fid;
    private final String returnType;
    private final boolean invokable;
    private String fileName = EMPTY_STRING;
    private ExecTypeFunction pair;
    private StandardType standardType;

    public ConstructorMetaInfo(){
        invokable = false;
        declaringClass = "";
        formDeclaringClass = "";
        realId = new ConstructorId("",new StringList(),false);
        fid = new ConstructorId("",new StringList(),false);
        access = AccessEnum.PRIVATE;
        returnType = "";
        pair = new ExecTypeFunction(null,null);
    }
    public ConstructorMetaInfo(String _declaringClass, AccessEnum _access, ConstructorId _realId, String _returnType,
                               ConstructorId _fid, String _formDeclaringClass) {
        invokable = true;
        declaringClass = StringUtil.nullToEmpty(_declaringClass);
        access = _access;
        realId = _realId;
        returnType = StringUtil.nullToEmpty(_returnType);
        fid = _fid;
        formDeclaringClass = StringUtil.nullToEmpty(_formDeclaringClass);
        pair = new ExecTypeFunction(null,null);
    }

    public CustList<CustList<ExecOperationNode>> getAnnotationsOps(){
        ExecNamedFunctionBlock fct_ = pair.getFct();
        if (fct_ != null) {
            return fct_.getAnnotationsOps();
        }
        return new CustList<CustList<ExecOperationNode>>();
    }
    public CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams(){
        ExecNamedFunctionBlock fct_ = pair.getFct();
        if (fct_ != null) {
            return fct_.getAnnotationsOpsParams();
        }
        return new CustList<CustList<CustList<ExecOperationNode>>>();
    }

    public ExecMemberCallingsBlock getCallee() {
        return pair.getFct();
    }

    public ExecRootBlock getPairType() {
        return pair.getType();
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public void pair(ExecRootBlock _type, ExecNamedFunctionBlock _fct) {
        pair = new ExecTypeFunction(_type, _fct);
        setOwner(_type);
    }

    public void setPair(ExecTypeFunction _pair) {
        pair = _pair;
        setOwner(_pair.getType());
    }

    public StandardType getStandardType() {
        return standardType;
    }

    public void setStandardType(StandardType _standardType) {
        standardType = _standardType;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = StringUtil.nullToEmpty(_fileName);
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
        ExecNamedFunctionBlock fct_ = pair.getFct();
        if (fct_ != null) {
            return fct_.getAnonymousLambda();
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }
    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        ExecNamedFunctionBlock fct_ = pair.getFct();
        if (fct_ != null) {
            return fct_.getSwitchMethods();
        }
        return new CustList<ExecAbstractSwitchMethod>();
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
