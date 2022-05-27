package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.opers.IdFctOperation;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringList;

public final class ResolvedIdBuilder {
    private final CustList<AnaResultPartType> typesAna;

    public ResolvedIdBuilder(int _from, String _fromType, StringList _params, String _className, AnalyzedPageEl _page) {
        typesAna = IdFctOperation.types(_from, _params, _fromType, _className, _page);
    }
    public ResolvedId build(int _from, boolean _retRef, String _name, MethodAccessKind _static, StringList _params, AnalyzedPageEl _page) {
        StringList classNames_ = IdFctOperation.typesStr(typesAna, _page);
        return new ResolvedId(new MethodId(_retRef, _static, _name, classNames_,IdFctOperation.refs(_from,_from+classNames_.size(),_params), IdFctOperation.vararg(_from,_params) != -1),new InfoErrorDto(""),typesAna);

    }
}
