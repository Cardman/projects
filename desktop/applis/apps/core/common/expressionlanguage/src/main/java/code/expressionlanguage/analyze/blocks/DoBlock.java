package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.util.StringList;

public final class DoBlock extends BracedBlock implements Loop {

    private String label;
    private int labelOffset;

    public DoBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        Block nextSibling_ = getNextSibling();
        if (nextSibling_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                    _page.getKeyWords().getKeyWordDo(),
                    StringList.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordWhile()
                            ),
                            "|"));
            _page.addLocError(un_);
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
            return;
        }
        if (!(nextSibling_ instanceof DoWhileCondition)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(nextSibling_.getFile().getFileName());
            un_.setIndexFile(nextSibling_.getOffset().getOffsetTrim());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                    _page.getKeyWords().getKeyWordDo(),
                    StringList.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordWhile()
                            ),
                            "|"));
            _page.addLocError(un_);
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
        }
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
    }

}
