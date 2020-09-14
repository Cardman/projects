package code.formathtml.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.instr.AbstractProcessKeyWord;
import code.expressionlanguage.instr.ResultAfterInstKeyWord;
import code.expressionlanguage.options.KeyWords;

public final class AdvancedProcessKeyWord implements AbstractProcessKeyWord {
    private final AnalyzedPageEl page;
    private final AnalyzingDoc analyzingDoc;

    public AdvancedProcessKeyWord(AnalyzedPageEl page, AnalyzingDoc analyzingDoc) {
        this.page = page;
        this.analyzingDoc = analyzingDoc;
    }

    @Override
    public void processInternKeyWord(String _exp, int _fr, ResultAfterInstKeyWord _out) {
        processInternKeyWord(_exp,_fr,_out, page, analyzingDoc);
    }

    private static void processInternKeyWord(String _string, int _fr,
                                             ResultAfterInstKeyWord _out, AnalyzedPageEl analyzing, AnalyzingDoc analyzingDoc) {
        KeyWords keyWords_ = analyzing.getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        String sub_ = _string.substring(_fr);
        int i_ = _fr;
        if (analyzingDoc.isInternGlobal()) {
            if (StringExpUtil.startsWithKeyWord(sub_, keyWordIntern_)) {
                int afterSuper_ = i_ + keyWordIntern_.length();
                String trim_ = _string.substring(afterSuper_).trim();
                if (trim_.startsWith(".")) {
                    //_string.charAt(afterSuper_) != EXTERN_CLASS && !foundHat_
                    i_ = _string.indexOf('.',afterSuper_);
                    _out.setNextIndex(i_);
                }
            }
        }
    }

}
