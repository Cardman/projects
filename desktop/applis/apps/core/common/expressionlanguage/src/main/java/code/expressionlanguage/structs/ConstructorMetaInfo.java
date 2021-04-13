package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ConstructorMetaInfo extends AbsAnnotatedStruct implements AnnotatedParamStruct {

    private final String declaringClass;
    private final String formDeclaringClass;
    private final ConstructorId realId;
    private final AccessEnum access;
    private final ConstructorId fid;
    private final String returnType;
    private final boolean invokable;
    private final String fileName;
    private final ExecTypeFunction pair;
    private final StandardType standardType;

    public ConstructorMetaInfo(){
        invokable = false;
        declaringClass = "";
        formDeclaringClass = "";
        realId = new ConstructorId("",new StringList(),false);
        fid = new ConstructorId("",new StringList(),false);
        access = AccessEnum.PRIVATE;
        returnType = "";
        pair = new ExecTypeFunction(null,null);
        fileName = "";
        standardType = null;
    }
    public ConstructorMetaInfo(ExecTypeFunction _pair,ContextEl _conf, ExecLambdaCommonContent _common, String _declaringClass, ConstructorId _realId) {
        String className_ = StringExpUtil.getIdFromAllTypes(_declaringClass);
        ConstructorId fid_ = MetaInfoUtil.tryFormatId(_declaringClass, _conf, _realId);
        invokable = true;
        declaringClass = StringUtil.nullToEmpty(_declaringClass);
        access = AccessEnum.PUBLIC;
        realId = _realId;
        returnType = StringUtil.nullToEmpty(_common.getReturnFieldType());
        fid = fid_;
        formDeclaringClass = StringUtil.nullToEmpty(className_);
        pair = _pair;
        setOwner(_pair.getType());
        fileName = _common.getFileName();
        standardType = null;
    }
    public ConstructorMetaInfo(StandardType _standardType,ContextEl _conf, ExecLambdaCommonContent _common, String _declaringClass, ConstructorId _realId) {
        standardType = _standardType;
        String className_ = StringExpUtil.getIdFromAllTypes(_declaringClass);
        ConstructorId fid_ = MetaInfoUtil.tryFormatId(_declaringClass, _conf, _realId);
        invokable = true;
        declaringClass = StringUtil.nullToEmpty(_declaringClass);
        access = AccessEnum.PUBLIC;
        realId = _realId;
        returnType = StringUtil.nullToEmpty(_common.getReturnFieldType());
        fid = fid_;
        formDeclaringClass = StringUtil.nullToEmpty(className_);
        pair = new ExecTypeFunction(null,null);
        fileName = _common.getFileName();
    }
    public ConstructorMetaInfo(ExecRootBlock _type, ExecConstructorBlock _ctor, ContextEl _context, String _declaringClass) {
        ConstructorId id_ = new ConstructorId(_declaringClass, new StringList(), false);
        AccessEnum acc_ = _type.getAccess();
        String ret_ = _context.getStandards().getContent().getCoreNames().getAliasVoid();
        ConstructorId fid_ = id_;
        String idType_ = _type.getFullName();
        String formCl_ = MetaInfoUtil.tryFormatType(idType_, _declaringClass, _context);
        if (_ctor != null) {
            id_ = _ctor.getGenericId(_type.getGenericString());
            ret_ = _ctor.getImportedReturnType();
            acc_ = _ctor.getAccess();
            fid_ = MetaInfoUtil.tryFormatId(_declaringClass, _context, id_);
        }
        invokable = true;
        declaringClass = StringUtil.nullToEmpty(_declaringClass);
        access = acc_;
        realId = id_;
        returnType = StringUtil.nullToEmpty(ret_);
        fid = fid_;
        formDeclaringClass = StringUtil.nullToEmpty(formCl_);
        pair = new ExecTypeFunction(_type,_ctor);
        setOwner(_type);
        fileName = _type.getFile().getFileName();
        standardType = null;
    }
    public ConstructorMetaInfo(ContextEl _context, StandardType _std, StandardConstructor _ctor, String _declaringClass) {
        ConstructorId id_ = new ConstructorId(_declaringClass, new StringList(), false);
        String ret_ = _context.getStandards().getContent().getCoreNames().getAliasVoid();
        if (_ctor != null) {
            id_ = new ConstructorId(_declaringClass, _ctor.getImportedParametersTypes(), _ctor.isVarargs());
            ret_ = _ctor.getImportedReturnType();
        }
        String decl_ = _std.getFullName();
        standardType = _std;
        invokable = true;
        declaringClass = StringUtil.nullToEmpty(_declaringClass);
        access = AccessEnum.PUBLIC;
        realId = id_;
        returnType = StringUtil.nullToEmpty(ret_);
        fid = id_;
        formDeclaringClass = StringUtil.nullToEmpty(decl_);
        pair = new ExecTypeFunction(null,null);
        fileName = "";
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

    public StandardType getStandardType() {
        return standardType;
    }

    @Override
    public String getFileName() {
        return fileName;
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
