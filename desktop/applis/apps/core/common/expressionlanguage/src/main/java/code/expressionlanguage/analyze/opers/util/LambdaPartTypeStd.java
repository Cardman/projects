package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;

public final class LambdaPartTypeStd extends AbsLambdaPartTypeImpl {

    public LambdaPartTypeStd(String _cl, CustList<AnaResultPartType> _parts) {
        super(_cl,_parts);
    }

    @Override
    public String resolve(boolean _wrap, int _offset, String _in, AnalyzedPageEl _page) {
        AnaResultPartType resolved_ = ResolvingTypes.resolveCorrectTypeAccessible(_offset, _in, _page);
        getPartOffsets().add(resolved_);
        String arg_ = resolved_.getResult(_page);
        if (_wrap) {
            arg_ = StringExpUtil.getPrettyArrayType(arg_);
        }
        return arg_;
    }
}
