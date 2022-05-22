package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.util.CustList;

public final class LambdaPartTypeId extends AbsLambdaPartTypeImpl {

    private final String from;
    public LambdaPartTypeId(String _cl, CustList<AnaResultPartType> _parts, String _fromType) {
        super(_cl,_parts);
        from = _fromType;
    }

    @Override
    public String resolve(boolean _wrap, int _offset, String _in, AnalyzedPageEl _page) {
        AnaResultPartType resolved_ = ResolvingTypes.resolveCorrectAccessibleType(_offset, _in, from, _page);
        getPartOffsets().add(resolved_);
        return resolved_.getResult(_page);
    }
}
