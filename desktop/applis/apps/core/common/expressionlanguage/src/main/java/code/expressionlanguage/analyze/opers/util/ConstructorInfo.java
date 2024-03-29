package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ConstructorBlock;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.IdentifiableUtil;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;
import code.util.StringList;

public final class ConstructorInfo extends Parametrable {

    private ConstructorId constraints;
    private ConstructorId formatted;

    private StandardConstructor constructor;

    public ConstructorInfo(RootBlock _type, String _clCurName, ConstructorBlock _b) {
        ConstructorId ctor_ = _b.getId().copy(StringExpUtil.getIdFromAllTypes(_clCurName));
        setOriginalReturnType(_type.getGenericString());
        setCust(_b);
        getParametrableContent().setFileName(_type.getFile().getFileName());
        memberId(_type.getNumberAll(), _b.getCtorNumber());
        setParametersNames(_b.getParametersNames());
        constructorId(_clCurName, ctor_);
        pair(_type, _b);
    }
    public ConstructorInfo(RootBlock _type, String _clCurName) {
        ConstructorId ctor_ = new ConstructorId("", new StringList(), false).copy(StringExpUtil.getIdFromAllTypes(_clCurName));
        setOriginalReturnType(_type.getGenericString());
        getParametrableContent().setFileName(_type.getFile().getFileName());
        memberId(_type.getNumberAll(), -1);
        constructorId(_clCurName, ctor_);
        pair(_type, null);
    }
    public ConstructorInfo(StandardType _type, String _clCurName, StandardConstructor _s) {
        ConstructorId ctor_ = _s.getId().copy(StringExpUtil.getIdFromAllTypes(_clCurName));
        setOriginalReturnType(_type.getGenericString());
        setStandardType(_type);
        setConstructor(_s);
        setParametersNames(_s.getParametersNames());
        constructorId(_clCurName, ctor_);
    }

    @Override
    public MethodId toId() {
        return ConstructorId.to(getConstraints());
    }

    public ConstructorId getConstraints() {
        return constraints;
    }

    public void constructorId(String _className,ConstructorId _constraints) {
        setClassName(_className);
        constraints = _constraints;
    }

    public void pair(RootBlock _root, NamedFunctionBlock _fct) {
        getParametrableContent().getPair().setType(_root);
        getParametrableContent().getPair().setFunction(_fct);
    }
    @Override
    public String getReturnType() {
        return constraints.getName();
    }

    @Override
    public boolean isVararg() {
        return constraints.isVararg();
    }

    @Override
    public Identifiable getPartialId() {
        return getConstraints();
    }

    public void format(AnalyzedPageEl _page) {
        if (!getFormattedFilter().getStCall().isEmpty()) {
            StringList params_ = IdentifiableUtil.incomplete(constraints);
            formatted = ConstructorId.to(getClassName(), params_, constraints);
            setFormattedParams(params_);
            return;
        }
        StringList params_ = AnaInherits.wildCardFormatParams(getClassName(), constraints, _page);
        formatted = ConstructorId.to(getClassName(), params_, constraints);
        setFormattedParams(params_);
    }

    public void reformat(String _foundType,AnalyzedPageEl _page) {
        setClassName(AnaInherits.getOverridingFullTypeByBases(_foundType, getClassName(), _page));
        StringList params_ = AnaInherits.wildCardFormatParams(getClassName(), constraints, _page);
        formatted = ConstructorId.to(getClassName(), params_, constraints);
        setFormattedParams(params_);
    }

    @Override
    public Identifiable getGeneFormatted() {
        return getFormatted();
    }

    public ConstructorId getFormatted() {
        return formatted;
    }

    public StandardConstructor getConstructor() {
        return constructor;
    }

    public void setConstructor(StandardConstructor _constructor) {
        this.constructor = _constructor;
    }

    public void setStCall(String _stCall) {
        getFormattedFilter().setStCall(_stCall);
    }
}
