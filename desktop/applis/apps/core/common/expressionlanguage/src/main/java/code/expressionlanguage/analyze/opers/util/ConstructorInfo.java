package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
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

    private StandardType standardType;
    private StandardConstructor constructor;
    private boolean synthetic;

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
    public boolean sameParamsVararg(Parametrable _id) {
        return IdentifiableUtil.eqPartial(constraints,_id.getPartialId());
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

    public StandardType getStandardType() {
        return standardType;
    }

    public void setStandardType(StandardType _standardType) {
        standardType = _standardType;
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

    public boolean isSynthetic() {
        return synthetic;
    }

    public void setSynthetic(boolean _s) {
        this.synthetic = _s;
    }
}
