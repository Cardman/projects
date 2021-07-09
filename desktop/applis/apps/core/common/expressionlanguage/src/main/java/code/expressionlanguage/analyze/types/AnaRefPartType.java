package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.maths.litteralcom.StrTypes;
import code.util.core.StringUtil;

final class AnaRefPartType extends AnaPrefPartType {

    AnaRefPartType(AnaParentPartType _parent, int _index, int _indexInType, String _prefix, StrTypes _operators) {
        super(_parent, _index, _indexInType, _prefix,_operators);
    }

    @Override
    void anaWildCommon(String _ch, String _base, AnalyzedPageEl _page) {
        if (!StringUtil.quickEq(_base.trim(), _page.getAliasFct())) {
            return;
        }
        String ch_ = StringUtil.concat(getBegin(),_ch);
        setAnalyzedType(ch_);
    }
}
