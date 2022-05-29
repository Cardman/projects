package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ImportedMethod;
import code.expressionlanguage.analyze.MethodHeaderInfo;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.util.ToStringMethodHeader;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;

public final class MethodInfo extends Parametrable {

    private MethodId constraints;
    private MethodId formatted;

    private String returnType = "";

    private boolean finalMethod;

    private int ancestor;

    private boolean abstractMethod;
    private StandardMethod standardMethod;
    private AnaGeneType owner;

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
    public boolean sameParamsVararg(Parametrable _id) {
        return IdentifiableUtil.eqPartial(constraints,_id.getPartialId());
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
