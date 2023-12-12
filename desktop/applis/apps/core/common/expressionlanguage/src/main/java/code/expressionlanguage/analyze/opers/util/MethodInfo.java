package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ImportedMethod;
import code.expressionlanguage.analyze.MethodHeaderInfo;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.util.ToStringMethodHeader;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class MethodInfo extends Parametrable {

    private MethodId constraints;
    private MethodId formatted;

    private String returnType = "";

    private boolean finalMethod;

    private int ancestor;

    private boolean abstractMethod;
    private StandardMethod standardMethod;
    private AnaGeneType owner;
    private CommonOperSymbol virtualCall;
    public MethodInfo() {
    }
    public MethodInfo(AnalyzedPageEl _page, OperatorBlock _op, MethodId _id) {
        String ret_ = _op.getImportedReturnType();
        classMethodId("", _id);
        setReturnType(ret_);
        setOriginalReturnType(ret_);
        memberId(_op);
        getParametrableContent().setFileName(_op.getFile().getFileName());
        format(true, _page);
    }
    public MethodInfo(AnalyzedPageEl _page, String _op, String _className, String _returnType, StringList _params, CommonOperSymbol _vir) {
        setVirtualCall(_vir);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, _op, _params);
        classMethodId(_className,id_);
        setReturnType(_returnType);
        format(true, _page);
    }
    public MethodInfo(AnalyzedPageEl _page, FormattedFilter _formattedFilter, ImportedMethod _e) {
        ClassMethodId m_ = _e.getId();
        MethodId id_ = m_.getConstraints();
        classMethodId(_e);
        setFormattedFilter(_formattedFilter);
        format(id_.getKind() == MethodAccessKind.STATIC, _page);
        pairMemberId(_e);
    }
    public MethodInfo(AnalyzedPageEl _page, ImportedMethod _e) {
        classMethodId(_e);
        format(true, _page);
        pairMemberId(_e);
    }
    public MethodInfo(AnalyzedPageEl _page, NamedCalledFunctionBlock _m, ScopeFilterType _scType, MethodId _id) {
        AnaFormattedRootBlock f_ = _scType.getFormatted();
        RootBlock r_ = f_.getRootBlock();
        String formattedClass_ = f_.getFormatted();
        getParametrableContent().setFileName(_m.getFile().getFileName());
        setParametersNames(_m.getParametersNames());
        String returnTypeGet_ = _m.getReturnTypeGet();
        if (!returnTypeGet_.isEmpty()) {
            pairMemberId(formattedClass_,_page,returnTypeGet_,r_,_m,_id);
        } else {
            pairMemberId(formattedClass_,_page,_m.getImportedReturnType(),r_,_m,_id);
        }
        setAncestor(_scType.getAnc());
        setFormattedFilter(_scType.getFormattedFilter());
        format(_id.getKind() == MethodAccessKind.STATIC, _page);
    }
    public MethodInfo(AnalyzedPageEl _page, StandardMethod _m, int _anc, String _formattedClass, MethodId _id, FormattedFilter _formatted){
        types(_formattedClass,_page,_m.getImportedReturnType());
        setStandardMethod(_m);
        setParametersNames(_m.getParametersNames());
        classMethodId(_formattedClass,_id);
        setAncestor(_anc);
        setFormattedFilter(_formatted);
        format(_id.getKind() == MethodAccessKind.STATIC, _page);
    }
    public MethodInfo(AnalyzedPageEl _page,MethodHeaderInfo _m, String _formattedClass){
        pairMemberId(_formattedClass,_page,_m);
        setAncestor(0);
        format(false, _page);
    }
    public MethodInfo(AnalyzedPageEl _page, RootBlock _r, int _ancestor, MethodId _id, String _ret){
        String idClass_ = StringExpUtil.getIdFromAllTypes(_r.getGenericString());
        setOwner(_r);
        getParametrableContent().setFileName(_r.getFile().getFileName());
        classMethodId(idClass_,_id);
        format(true, _page);
        setReturnType(_ret);
        setOriginalReturnType(_ret);
        setAncestor(_ancestor);
        memberId(_r);
    }
    public MethodInfo(ToStringMethodHeader _m, String _formattedClass){
        String ret_ = _m.getImportedReturnType();
        MethodId id_ = _m.getId();
        memberId(_m);
        setAbstractMethod(_m.isAbstractMethod());
        setFinalMethod(_m.isFinalMethod());
        classMethodId(_formattedClass,id_);
        setReturnType(ret_);
        setAncestor(0);
        formatWithoutParams();
    }
    public MethodInfo(AnalyzedPageEl _page,String _fct, int _len, MethodId _id, StandardMethod _e){
        StandardType stdType_ = _page.getFctType();
        StringList all_ = StringExpUtil.getAllTypes(_fct);
        String ret_ = all_.last();
        String name_ = _id.getName();
        CustList<String> param_;
        if (all_.size() == 1) {
            param_ = new StringList();
            for (int i = 0; i < _len; i++) {
                param_.add(_page.getAliasObject());
            }
        } else {
            param_ = all_.leftMinusOne(all_.size() - 2);
        }
        setOwner(stdType_);
        setOriginalReturnType(_page.getAliasObject());
        setStandardType(stdType_);
        setStandardMethod(_e);
        setParametersNames(_e.getParametersNames());
        classMethodId(_fct,_id);
        String retBase_;
        boolean refRet_;
        if (StringUtil.quickEq(ret_, StringExpUtil.SUB_TYPE)) {
            retBase_ = _page.getAliasObject();
            refRet_ = false;
        } else if (ret_.startsWith("~")) {
            retBase_ = ret_.substring(1);
            refRet_ = true;
        } else {
            retBase_ = ret_;
            refRet_ = false;
        }
        setReturnType(retBase_);
        setAncestor(0);
        StringList cls_ = new StringList();
        CustList<BoolVal> refs_ = new CustList<BoolVal>();
        for (String c: param_) {
            if (StringUtil.quickEq(c, StringExpUtil.SUB_TYPE)) {
                cls_.add(_page.getAliasObject());
                refs_.add(BoolVal.FALSE);
            } else if (c.startsWith("~")) {
                cls_.add(c.substring(1));
                refs_.add(BoolVal.TRUE);
            } else {
                cls_.add(c);
                refs_.add(BoolVal.FALSE);
            }
        }
        format(refRet_,
                name_,cls_,refs_);

    }
    public CommonOperSymbol getVirtualCall() {
        return virtualCall;
    }

    public void setVirtualCall(CommonOperSymbol _v) {
        this.virtualCall = _v;
    }

    public MethodId getConstraints() {
        return constraints;
    }

    public void classMethodId(ImportedMethod _e) {
        ClassMethodId m_ = _e.getId();
        classMethodId(m_.getClassName(),m_.getConstraints());
    }
    public void pairMemberId(ImportedMethod _m) {
        pair(_m.getType(),_m.getCustMethod());
        setOwner(_m.getOwner());
        MemberId memberId_ = _m.getMemberId();
        memberId(memberId_.getRootNumber(),memberId_.getMemberNumber());
        getParametrableContent().setFileName(_m.getFileName());
        setReturnType(_m.getReturnType());
        setOriginalReturnType(_m.getReturnType());
        trySetParamNames(this, _m);
        setStandardMethod(_m.getStandardMethod());
        setStandardType(_m.getStd());
        setCust(_m.getCustMethod());
    }
    public void pairMemberId(String _formattedClass, AnalyzedPageEl _page,MethodHeaderInfo _m) {
        pairInfos(_formattedClass, _page, _m.getImportedReturnType(), _m.getRoot(), _m.getFunction(), _m.getId());
        memberId(_m.getRootNumber(),_m.getNameNumber());
    }
    public void pairMemberId(String _formattedClass, AnalyzedPageEl _page,String _importedReturnType, RootBlock _root, NamedCalledFunctionBlock _function, MethodId _id) {
        pairInfos(_formattedClass, _page, _importedReturnType,_root, _function, _id);
        memberId(_root,_function);
        setCust(_function);
    }

    private void pairInfos(String _formattedClass, AnalyzedPageEl _page, String _importedReturnType, RootBlock _root, NamedFunctionBlock _function, MethodId _id) {
        types(_formattedClass, _page, _importedReturnType);
        classMethodId(_formattedClass, _id);
        getParametrableContent().setFileName(_root.getFile().getFileName());
        pair(_root, _function);
    }

    public void classMethodId(String _className, MethodId _id) {
        setClassName(_className);
        constraints = _id;
    }
    private static void trySetParamNames(MethodInfo _mloc, ImportedMethod _custMethod) {
        if (_custMethod.getCustMethod() != null) {
            _mloc.setParametersNames(_custMethod.getCustMethod().getParametersNames());
        }
        if (_custMethod.getStandardMethod() != null) {
            _mloc.setParametersNames(_custMethod.getStandardMethod().getParametersNames());
        }
    }
    public void pair(RootBlock _root, NamedFunctionBlock _fct) {
        owner = _root;
        getParametrableContent().getPair().setType(_root);
        getParametrableContent().getPair().setFunction(_fct);
    }

    public void types(String _formattedClass, AnalyzedPageEl _page, String _originalReturnType) {
        String ret_ = _originalReturnType;
        ret_ = AnaInherits.wildCardFormatReturn(_formattedClass, ret_, _page);
        setOriginalReturnType(_originalReturnType);
        returnType = ret_;
    }

    public static String retIndexSet(ClassMethodIdReturn _id, AnalyzedPageEl _page) {
        return AnaInherits.wildCardFormatReturn(_id.getFormattedType().getFormatted(),((NamedCalledFunctionBlock) _id.getParametrableContent().getPair().getFunction()).getReturnTypeGet(),_page);
    }
    @Override
    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String _returnType) {
        returnType = _returnType;
    }

    @Override
    public boolean isVararg() {
        return constraints.isVararg();
    }

    @Override
    public Identifiable getPartialId() {
        return toId();
    }

    @Override
    public MethodId toId() {
        return getConstraints();
    }

    public int getAncestor() {
        return ancestor;
    }

    public void setAncestor(int _ancestor) {
        ancestor = _ancestor;
    }

    public void reformat(String _foundType,AnalyzedPageEl _page) {
        setClassName(AnaInherits.getOverridingFullTypeByBases(_foundType, getClassName(), _page));
        StringList params_ = AnaInherits.wildCardFormatParams(getClassName(), constraints, _page);
        setFormattedParams(params_);
        formatted = buildFormatted(MethodId.getKind(false), params_, constraints);
        returnType = AnaInherits.wildCardFormatReturn(getClassName(), getOriginalReturnType(),_page);
    }

    public void format(boolean _keepParams, AnalyzedPageEl _page) {
        if (!getFormattedFilter().getStCall().isEmpty()) {
            StringList params_ = IdentifiableUtil.incomplete(constraints);
            setFormattedParams(params_);
            formatted = buildFormatted(MethodId.getKind(_keepParams), params_, constraints);
            return;
        }
        StringList params_ = AnaInherits.wildCardFormatParams(getClassName(), constraints, _page);
        setFormattedParams(params_);
        formatted = buildFormatted(MethodId.getKind(_keepParams), params_, constraints);
    }

    public void format(boolean _retRef, String _name, StringList _classNames, CustList<BoolVal> _refParam) {
        setFormattedParams(_classNames);
        formatted = buildFormatted(MethodAccessKind.INSTANCE, _classNames, new MethodId(_retRef, MethodAccessKind.INSTANCE,
                _name,_classNames,_refParam,false));
    }

    private static MethodId buildFormatted(MethodAccessKind _keepParams, StringList _params, MethodId _id) {
        return MethodId.to(_keepParams, _params, _id);
    }

    public void formatWithoutParams() {
        setFormattedParams(new StringList());
        formatted = new MethodId(MethodId.getKind(false), constraints.getName(), getFormattedParams());
    }

    @Override
    public Identifiable getGeneFormatted() {
        return getFormatted();
    }

    public MethodId getFormatted() {
        return formatted;
    }

    public boolean same(MethodId _id) {
        return formatted.eq(_id);
    }
    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public void setAbstractMethod(boolean _abstractMethod) {
        abstractMethod = _abstractMethod;
    }

    public boolean isFinalMethod() {
        return finalMethod;
    }

    public void setFinalMethod(boolean _finalMethod) {
        finalMethod = _finalMethod;
    }

    public void memberId(RootBlock _type) {
        memberId(_type.getNumberAll(),-1);
    }
    public void memberId(RootBlock _r, NamedCalledFunctionBlock _m) {
        if (AbsBk.isOverBlock(_m)) {
            setAbstractMethod(_m.isAbstractMethod());
            setFinalMethod(_m.isFinalMethod());
            memberId(_r.getNumberAll(),_m.getNameOverrideNumber());
        } else {
            memberId(_r.getNumberAll(),_m.getNameNumber());
        }
    }
    public void memberId(OperatorBlock _oper) {
        pair(null,_oper);
        memberId(-1,_oper.getOperatorNumber());
    }
    public void memberId(ToStringMethodHeader _toString) {
        memberId(_toString.getNumberRoot(),_toString.getNumberAll());
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

    public void setFormattedFilter(FormattedFilter _formattedFilter) {
        getFormattedFilter().setStCall(_formattedFilter.getStCall());
        getFormattedFilter().setReturnType(_formattedFilter.getReturnType());
    }

    public AnaGeneType getOwner() {
        return owner;
    }

    public void setOwner(AnaGeneType _own) {
        this.owner = _own;
    }
}
