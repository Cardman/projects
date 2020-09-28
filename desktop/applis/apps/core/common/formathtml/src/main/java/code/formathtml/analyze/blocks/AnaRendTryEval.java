package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.Configuration;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;

public final class AnaRendTryEval extends AnaRendParentBlock implements AnaRendEval,AnaRendLocBreakableBlock {
    private String label;
    private int labelOffset;
    AnaRendTryEval(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnaRendBlock nBlock_ = getNextSibling();
        if (!(nBlock_ instanceof AnaRendAbstractCatchEval)) {
            if (!(nBlock_ instanceof AnaRendFinallyEval)) {
                if (!(nBlock_ instanceof AnaRendPossibleEmpty)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_anaDoc.getFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                            _page.getKeyWords().getKeyWordTry(),
                            StringList.join(
                                    new StringList(
                                            _page.getKeyWords().getKeyWordCatch(),
                                            _page.getKeyWords().getKeyWordFinally()
                                    ),
                                    OR_ERR));
                    AnalyzingDoc.addError(un_, _anaDoc, _page);
                } else if (!(nBlock_.getNextSibling() instanceof AnaRendAbstractCatchEval)){
                    if (!(nBlock_.getNextSibling() instanceof AnaRendFinallyEval)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_anaDoc.getFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                                _page.getKeyWords().getKeyWordTry(),
                                StringList.join(
                                        new StringList(
                                                _page.getKeyWords().getKeyWordCatch(),
                                                _page.getKeyWords().getKeyWordFinally()
                                        ),
                                        OR_ERR));
                        AnalyzingDoc.addError(un_, _anaDoc, _page);
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
