package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.maths.litteralcom.StrTypes;
import code.util.core.StringUtil;

final class AnaWildCardPartType extends AnaPrefPartType {

    AnaWildCardPartType(AnaParentPartType _parent, int _index, int _indexInType, String _prefix, StrTypes _operators, AnalysisMessages _messages, StrTypes _values) {
        super(_parent, _index, _indexInType, _prefix,_operators, _messages,_values);
    }

    @Override
    void anaWildCommon(String _ch, String _base, AnalyzedPageEl _page) {
        if (StringUtil.quickEq(_base.trim(), _page.getAliasFct())) {
            return;
        }
        String ch_ = StringUtil.concat(getBegin(),_ch);
        setAnalyzedType(ch_);
    }
}
