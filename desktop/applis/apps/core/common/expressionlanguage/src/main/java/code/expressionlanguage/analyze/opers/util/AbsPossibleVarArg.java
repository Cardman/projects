package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.functionid.Identifiable;

public abstract class AbsPossibleVarArg {
    private final ParametrableContent parametrableContent = new ParametrableContent();
    private AnaFormattedRootBlock formattedType;

    public AnaTypeFct getPair() {
        return getParametrableContent().getPair();
    }

    public MemberId getMemberId() {
        return getParametrableContent().getMemberId();
    }

    public boolean isVarArgToCall() {
        return getParametrableContent().isVarArgToCall();
    }

    public AnaFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public void setFormattedType(AnaFormattedRootBlock _formattedType) {
        this.formattedType = _formattedType;
    }

    public ParametrableContent getParametrableContent() {
        return parametrableContent;
    }

    public abstract Identifiable ident();
}
