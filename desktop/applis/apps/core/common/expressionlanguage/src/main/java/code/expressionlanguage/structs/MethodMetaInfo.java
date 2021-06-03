
package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ShownCache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;


public final class MethodMetaInfo extends AbsAnnotatedStruct implements AnnotatedParamStruct {

    private final String className;
    private final String formDeclaringClass;

    private final MethodId realId;
    private final MethodId fid;

    private final AccessEnum access;
    private final MethodModifier modifier;

    private final String returnType;
    private final String fileName;
    private final boolean directCast;
    private final boolean expCast;
    private final boolean invokable;
    private final ExecMemberCallingsBlock callee;
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formatted;
    private final StandardMethod stdCallee;
    private final Cache cache;

    public MethodMetaInfo() {
        invokable = false;
        className = "";
        formDeclaringClass = "";
        realId = new MethodId(MethodAccessKind.INSTANCE,"",new StringList());
        fid = new MethodId(MethodAccessKind.INSTANCE,"",new StringList());
        access = AccessEnum.PRIVATE;
        modifier = MethodModifier.NORMAL;
        returnType = "";
        pair = new ExecTypeFunction((ExecRootBlock)null,null);
        formatted = ExecFormattedRootBlock.defValue();
        fileName = "";
        stdCallee = null;
        callee = null;
        cache = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ContextEl _cont, String _declaringClass, MethodId _realId, String _returnType) {
        LgNames lgNames_ = _cont.getStandards();
        String realInstClassName_ = StringExpUtil.getPrettyArrayType(lgNames_.getContent().getCoreNames().getAliasObject());
        String idRealCl_ = StringExpUtil.getIdFromAllTypes(realInstClassName_);
        invokable = true;
        access = AccessEnum.PUBLIC;
        className = StringUtil.nullToEmpty(idRealCl_);
        realId = _realId;
        modifier = MethodModifier.FINAL;
        returnType = StringUtil.nullToEmpty(_returnType);
        fid = _realId;
        formatted = new ExecFormattedRootBlock((ExecRootBlock)null,className);
        formDeclaringClass = StringUtil.nullToEmpty(_declaringClass);
        pair = new ExecTypeFunction((ExecRootBlock)null,null);
        fileName = "";
        stdCallee = null;
        callee = null;
        cache = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, MethodId _realId, String _returnType) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        invokable = true;
        access = AccessEnum.PUBLIC;
        className = StringUtil.nullToEmpty(type_.getFullName());
        realId = _realId;
        modifier = MethodModifier.STATIC;
        returnType = StringUtil.nullToEmpty(_returnType);
        fid = _realId;
        formDeclaringClass = StringUtil.nullToEmpty(type_.getFullName());
        pair = new ExecTypeFunction(_formatted,null);
        callee = null;
        fileName = type_.getFile().getFileName();
        formatted = _formatted;
        setOwner(type_);
        stdCallee = null;
        cache = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, ExecAnnotationMethodBlock _annot) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        MethodId id_ = _annot.getId();
        invokable = true;
        access = AccessEnum.PUBLIC;
        className = StringUtil.nullToEmpty(type_.getFullName());
        realId = id_;
        modifier = MethodModifier.ABSTRACT;
        returnType = StringUtil.nullToEmpty(_annot.getImportedReturnType());
        fid = id_;
        formDeclaringClass = StringUtil.nullToEmpty(type_.getFullName());
        pair = new ExecTypeFunction(_formatted,_annot);
        callee = _annot;
        fileName = type_.getFile().getFileName();
        formatted = _formatted;
        setOwner(type_);
        stdCallee = null;
        cache = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, ContextEl _context, ExecOverridableBlock _over, boolean _expCast) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        String formatted_ = _formatted.getFormatted();
        expCast = _expCast;
        MethodId id_ = _over.getId();
        String ret_ = _over.getImportedReturnType();
        boolean param_ = id_.getKind() == MethodAccessKind.STATIC_CALL || _over.getKind() == ExecMethodKind.EXPLICIT_CAST || _over.getKind() == ExecMethodKind.IMPLICIT_CAST
                || _over.getKind() == ExecMethodKind.TRUE_OPERATOR  || _over.getKind() == ExecMethodKind.FALSE_OPERATOR;
        MethodId fid_ = tryFormatId(formatted_, _context, id_);
        String formCl_ = formCl(_context, formatted_);
        String idCl_ = type_.getFullName();
        if (param_) {
            idCl_ = formatted_;
        }
        invokable = true;
        access = _over.getAccess();
        className = StringUtil.nullToEmpty(idCl_);
        realId = id_;
        modifier = _over.getModifier();
        returnType = StringUtil.nullToEmpty(ret_);
        fid = fid_;
        formDeclaringClass = StringUtil.nullToEmpty(formCl_);
        pair = new ExecTypeFunction(_formatted,_over);
        callee = _over;
        fileName = type_.getFile().getFileName();
        setOwner(type_);
        formatted = _formatted;
        stdCallee = null;
        cache = null;
        directCast = false;
    }
    public MethodMetaInfo(ContextEl _cont, ExecAnonymousFunctionBlock _f, ExecFormattedRootBlock _formatted) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        String formatted_ = _formatted.getFormatted();
        String idType_ = "";
        String idCl_ = "";
        if (type_ != null) {
            idType_ = type_.getFullName();
            idCl_ = type_.getFullName();
        }
        String formCl_ = tryFormatType(idType_, formatted_, _cont);
        LgNames standards_ = _cont.getStandards();
        MethodId id_ = _f.getId();
        boolean param_ = id_.getKind() == MethodAccessKind.STATIC_CALL;
        if (param_) {
            idCl_ = formatted_;
        }
        invokable = true;
        access = _f.getAccess();
        className = StringUtil.nullToEmpty(idCl_);
        realId = id_;
        modifier = _f.getModifier();
        returnType = StringUtil.nullToEmpty(_f.getImportedReturnType());
        fid =  tryFormatId(formatted_, _cont, realId);
        formDeclaringClass = StringUtil.nullToEmpty(formCl_);
        pair = new ExecTypeFunction(type_,_f);
        callee = _f;
        fileName = _f.getFile().getFileName();
        cache = new ShownCache(_f, standards_.getContent().getCoreNames().getAliasObject());
        setOwner(type_);
        formatted = _formatted;
        stdCallee = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(StandardMethod _std, String _className, ExecFormattedRootBlock _formatted) {
        invokable = true;
        access = AccessEnum.PUBLIC;
        className = StringUtil.nullToEmpty(_className);
        stdCallee = _std;
        realId = _std.getId();
        modifier = _std.getModifier();
        returnType = StringUtil.nullToEmpty(_std.getImportedReturnType());
        fid = realId;
        formDeclaringClass = StringUtil.nullToEmpty(_className);
        pair = new ExecTypeFunction((ExecRootBlock)null,null);
        formatted = _formatted;
        fileName = "";
        callee = null;
        cache = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ExecOperatorBlock _oper) {
        realId = _oper.getId();
        fid = realId;
        invokable = true;
        access = _oper.getAccess();
        className = "";
        modifier = MethodModifier.STATIC;
        returnType = StringUtil.nullToEmpty(_oper.getImportedReturnType());
        formDeclaringClass = "";
        fileName = _oper.getFile().getFileName();
        pair = new ExecTypeFunction((ExecRootBlock)null,_oper);
        formatted = ExecFormattedRootBlock.defValue();
        callee = _oper;
        stdCallee = null;
        cache = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(Cache _cache, ExecLambdaCommonContent _common, ExecLambdaMethodContent _meth, ExecFormattedRootBlock _declaringClass, MethodId _realId, ExecTypeFunction _pair) {
        cache = _cache;
        boolean abstractMethod_ = false;
        boolean expCast_ = false;
        if (_meth != null) {
            abstractMethod_ = _meth.isAbstractMethod();
            expCast_ = _meth.isExpCast();
        }
        MethodModifier met_;
        if (abstractMethod_) {
            met_ = MethodModifier.ABSTRACT;
        } else if (_realId.getKind() == MethodAccessKind.STATIC) {
            met_ = MethodModifier.STATIC;
        } else if (_realId.getKind() == MethodAccessKind.STATIC_CALL) {
            met_ = MethodModifier.STATIC_CALL;
        } else {
            met_ = MethodModifier.NORMAL;
        }
        invokable = true;
        access = AccessEnum.PUBLIC;
        expCast = expCast_;
        String className_;
        if (_realId.getKind() == MethodAccessKind.STATIC_CALL || expCast_) {
            className_ = _declaringClass.getFormatted();
        } else {
            className_ = StringExpUtil.getIdFromAllTypes(_declaringClass.getFormatted());
        }
        className = StringUtil.nullToEmpty(className_);
        realId = _realId;
        modifier = met_;
        returnType = StringUtil.nullToEmpty(_common.getReturnFieldType());
        fid = tryFormatId(_declaringClass, _realId);
        formDeclaringClass = StringUtil.nullToEmpty(formCl(_declaringClass));
        pair = _pair;
        callee = _pair.getFct();
        fileName = _common.getFileName();
        setOwner(_pair.getType());
        formatted = _declaringClass;
        stdCallee = null;
        directCast = false;
    }
    public MethodMetaInfo(ExecLambdaCommonContent _common, ExecFormattedRootBlock _declaringClass, MethodId _realId, ExecTypeFunction _pair) {
        String idCl_ = StringExpUtil.getIdFromAllTypes(_common.getFormattedType().getFormatted());
        invokable = true;
        access = AccessEnum.PUBLIC;
        className = StringUtil.nullToEmpty(idCl_);
        realId = _realId;
        modifier = MethodModifier.STATIC;
        returnType = StringUtil.nullToEmpty(_common.getReturnFieldType());
        fid = tryFormatId(_declaringClass, _realId);
        formDeclaringClass = StringUtil.nullToEmpty(formCl(_declaringClass));
        pair = _pair;
        callee = _pair.getFct();
        fileName = _common.getFileName();
        setOwner(_pair.getType());
        formatted = _declaringClass;
        stdCallee = null;
        cache = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ExecLambdaCommonContent _common, ExecFormattedRootBlock _declaringClass, MethodId _realId, MethodModifier _modifier, boolean _directCast) {
        invokable = true;
        access = AccessEnum.PUBLIC;
        className = StringUtil.nullToEmpty(_declaringClass.getFormatted());
        realId = _realId;
        modifier = _modifier;
        returnType = StringUtil.nullToEmpty(_common.getReturnFieldType());
        fid = tryFormatId(_declaringClass, _realId);
        formDeclaringClass = StringUtil.nullToEmpty(formCl(_declaringClass));
        directCast = _directCast;
        pair = new ExecTypeFunction((ExecRootBlock)null,null);
        formatted = _declaringClass;
        callee = null;
        fileName = _common.getFileName();
        stdCallee = null;
        cache = null;
        expCast = false;
    }
    public MethodMetaInfo(ExecLambdaCommonContent _common, ExecFormattedRootBlock _declaringClass, MethodId _realId, MethodModifier _modifier, StandardMethod _stdCallee) {
        String from_ = StringExpUtil.getIdFromAllTypes(_declaringClass.getFormatted());
        invokable = true;
        access = AccessEnum.PUBLIC;
        className = StringUtil.nullToEmpty(from_);
        realId = _realId;
        modifier = _modifier;
        returnType = StringUtil.nullToEmpty(_common.getReturnFieldType());
        fid = tryFormatId(_declaringClass, _realId);
        formDeclaringClass = StringUtil.nullToEmpty(formCl(_declaringClass));
        pair = new ExecTypeFunction((ExecRootBlock)null,null);
        formatted = _declaringClass;
        callee = null;
        stdCallee = _stdCallee;
        fileName = _common.getFileName();
        cache = null;
        directCast = false;
        expCast = false;
    }

    public MethodMetaInfo(ContextEl _cont, ExecAbstractSwitchMethod _f, ExecFormattedRootBlock _formatted) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        String formatted_ = _formatted.getFormatted();
        String idType_ = "";
        String idCl_ = "";
        if (type_ != null) {
            idType_ = type_.getFullName();
            idCl_ = type_.getFullName();
        }
        MethodId id_ = _f.getId();
        boolean param_ = id_.getKind() == MethodAccessKind.STATIC_CALL;
        if (param_) {
            idCl_ = formatted_;
        }
        LgNames standards_ = _cont.getStandards();
        String formCl_ = tryFormatType(idType_, formatted_, _cont);
        invokable = true;
        access = AccessEnum.PUBLIC;
        className = StringUtil.nullToEmpty(idCl_);
        realId = id_;
        modifier = _f.getModifier();
        returnType = StringUtil.nullToEmpty(_f.getRetType());
        fid = tryFormatId(formatted_, _cont, realId);
        formDeclaringClass = StringUtil.nullToEmpty(formCl_);
        pair = new ExecTypeFunction(type_,null);
        callee = _f;
        fileName = _f.getFile().getFileName();
        cache = new ShownCache(_f, standards_.getContent().getCoreNames().getAliasObject());
        setOwner(type_);
        formatted = _formatted;
        stdCallee = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, ContextEl _context, ExecInitBlock _meth) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        String formatted_ = _formatted.getFormatted();
        MethodId id_ = _meth.getId();
        String ret_ = _context.getStandards().getContent().getCoreNames().getAliasVoid();
        String formCl_ = formCl(_context, formatted_);
        String idCl_ = type_.getFullName();
        MethodModifier mod_;
        if (_meth instanceof ExecInstanceBlock) {
            mod_ = MethodModifier.FINAL;
        } else {
            mod_ = MethodModifier.STATIC;
        }
        invokable = false;
        access = AccessEnum.PRIVATE;
        className = StringUtil.nullToEmpty(idCl_);
        realId = id_;
        modifier = mod_;
        returnType = StringUtil.nullToEmpty(ret_);
        fid = id_;
        formDeclaringClass = StringUtil.nullToEmpty(formCl_);
        pair = new ExecTypeFunction(_formatted,null);
        setOwner(type_);
        formatted = _formatted;
        fileName = type_.getFile().getFileName();
        stdCallee = null;
        callee = _meth;
        cache = null;
        directCast = false;
        expCast = false;
    }
    private static String formCl(ContextEl _cont, String _declaringClass) {
        String idCl_ = StringExpUtil.getIdFromAllTypes(_declaringClass);
        return tryFormatType(idCl_, _declaringClass, _cont);
    }
    private static String formCl(ExecFormattedRootBlock _declaringClass) {
        return _declaringClass.getFormatted();
    }

    public CustList<CustList<ExecOperationNode>> getAnnotationsOps(){
        if (callee instanceof ExecAnnotableParamBlock) {
            return ((ExecAnnotableParamBlock)callee).getAnnotationsOps();
        }
        return new CustList<CustList<ExecOperationNode>>();
    }
    public CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams(){
        if (callee instanceof ExecAnnotableParamBlock) {
            return ((ExecAnnotableParamBlock)callee).getAnnotationsOpsParams();
        }
        return new CustList<CustList<CustList<ExecOperationNode>>>();
    }

    public ExecFormattedRootBlock getFormatted() {
        return formatted;
    }

    public ExecMemberCallingsBlock getCallee() {
        return callee;
    }

    public ExecRootBlock getPairType() {
        return pair.getType();
    }

    public ExecNamedFunctionBlock getPairFct() {
        return pair.getFct();
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public StandardMethod getStdCallee() {
        return stdCallee;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public String getFormDeclaringClass() {
        return formDeclaringClass;
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
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        if (callee != null) {
            return callee.getAnonymousLambda();
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }
    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        if (callee != null) {
            return callee.getSwitchMethods();
        }
        return new CustList<ExecAbstractSwitchMethod>();
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getReflect().getAliasMethod();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof MethodMetaInfo)) {
            return false;
        }
        MethodMetaInfo info_ = (MethodMetaInfo) _other;
        if (!StringUtil.quickEq(className, info_.className)) {
            return false;
        }
        return realId.eq(info_.realId);
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(className);
    }
    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(StringUtil.concat(className,".", getSignature(_an)));
    }

    public String getSignature(ContextEl _an) {
        return realId.getSignature(_an);
    }

    public boolean isInvokable() {
        return invokable;
    }

    public boolean isDirectCast() {
        return directCast;
    }

    public boolean isExpCast() {
        return expCast;
    }

    public Cache getCache() {
        return cache;
    }

}
