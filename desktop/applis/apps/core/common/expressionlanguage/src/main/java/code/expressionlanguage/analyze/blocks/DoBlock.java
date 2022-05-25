package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringList;
import code.util.core.StringUtil;

public final class DoBlock extends LabelledOtherBlock implements Loop {

    public DoBlock(OffsetStringInfo _label, int _offset) {
        super(_offset,_label);
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        AbsBk nextSibling_ = getNextSibling();
        if (nextSibling_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                    _page.getKeyWords().getKeyWordDo(),
                    StringUtil.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordWhile()
                            ),
                            ExportCst.JOIN_BLOCK));
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
            return;
        }
        if (!(nextSibling_ instanceof DoWhileCondition)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(nextSibling_.getFile());
            un_.setIndexFile(nextSibling_.getOffset());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                    _page.getKeyWords().getKeyWordDo(),
                    StringUtil.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordWhile()
                            ),
                            ExportCst.JOIN_BLOCK));
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
        }
    }

}
