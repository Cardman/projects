package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendTryEval extends AnaRendParentBlock implements AnaRendEval,AnaRendLocBreakableBlock {
    private final String label;
    private final int labelOffset;
    AnaRendTryEval(OffsetStringInfo _label, int _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnaRendBlock nBlock_ = getNextSibling();
        if (!(nBlock_ instanceof AnaRendAbstractCatchEval)) {
            if (!(nBlock_ instanceof AnaRendFinallyEval)) {
                if (!isPossibleEmpty(nBlock_)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(getOffset());
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                            _page.getKeyWords().getKeyWordTry(),
                            StringUtil.join(
                                    new StringList(
                                            _page.getKeyWords().getKeyWordCatch(),
                                            _page.getKeyWords().getKeyWordFinally()
                                    ),
                                    OR_ERR));
                    AnalyzingDoc.addError(un_, _page);
                } else if (!(nBlock_.getNextSibling() instanceof AnaRendAbstractCatchEval)){
                    if (!(nBlock_.getNextSibling() instanceof AnaRendFinallyEval)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_page.getCurrentFile());
                        un_.setIndexFile(getOffset());
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                                _page.getKeyWords().getKeyWordTry(),
                                StringUtil.join(
                                        new StringList(
                                                _page.getKeyWords().getKeyWordCatch(),
                                                _page.getKeyWords().getKeyWordFinally()
                                        ),
                                        OR_ERR));
                        AnalyzingDoc.addError(un_, _page);
                    }
                }
            }
        }
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return labelOffset;
    }

}
