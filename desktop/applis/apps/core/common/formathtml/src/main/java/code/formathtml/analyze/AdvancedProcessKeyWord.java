package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.DefaultProcessKeyWord;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.instr.AbstractProcessKeyWord;
import code.expressionlanguage.analyze.instr.ResultAfterInstKeyWord;
import code.expressionlanguage.options.KeyWords;

public final class AdvancedProcessKeyWord implements AbstractProcessKeyWord {
    private final AnalyzedPageEl page;
    private final AnalyzingDoc analyzingDoc;

    public AdvancedProcessKeyWord(AnalyzedPageEl _page, AnalyzingDoc _analyzingDoc) {
        this.page = _page;
        this.analyzingDoc = _analyzingDoc;
    }

    @Override
    public void processInternKeyWord(String _exp, int _fr, Delimiters _d, ResultAfterInstKeyWord _out) {
        KeyWords keyWords_ = page.getKeyWords();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        if (StringExpUtil.startsWithKeyWord(_exp,_fr, keyWordNew_)) {
            int j_ = _fr+keyWordNew_.length();
            int af_ = DefaultProcessKeyWord.extractPrefix(_exp, _d, _out, j_);
            if (af_ < 0){
                return;
            }
            DefaultProcessKeyWord.extractType(_exp,_d,_out,af_);
            return;
        }
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        if (analyzingDoc.isInternGlobal()) {
            if (StringExpUtil.startsWithKeyWord(_exp, _fr,keyWordIntern_)) {
                int afterSuper_ = _fr + keyWordIntern_.length();
                String trim_ = _exp.substring(afterSuper_).trim();
                if (trim_.startsWith(".")) {
                    //_string.charAt(afterSuper_) != EXTERN_CLASS && !foundHat_
                    _out.setNextIndex(_exp.indexOf('.',afterSuper_));
                }
            }
        }
    }

}
