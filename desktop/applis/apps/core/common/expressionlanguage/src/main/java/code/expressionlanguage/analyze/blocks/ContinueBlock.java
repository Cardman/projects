package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ContinueBlock extends AbruptBlock implements LabelAbruptBlock {

    private final String label;
    private final int labelOffset;
    private int labelOffsetRef=-1;
    private final StringList errorsRefLabels = new StringList();

    public ContinueBlock(OffsetStringInfo _label, int _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        checkLoop(_page);
//        ExecContinueBlock exec_ = new ExecContinueBlock(getOffset(),label);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);
    }

    private void checkLoop(AnalyzedPageEl _page) {
        boolean childOfLoop_ = false;
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (BreakBlock.isLoop(b_) && exitLoop((BreakableBlock) b_)) {
                childOfLoop_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            _page.setSumOffset(getOffset());
            _page.zeroOffset();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            if (label.isEmpty()) {
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbrupt(),
                        _page.getKeyWords().getKeyWordContinue(),
                        StringUtil.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordFor(),
                                        _page.getKeyWords().getKeyWordForeach(),
                                        _page.getKeyWords().getKeyWordDo(),
                                        _page.getKeyWords().getKeyWordIter(),
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                ExportCst.JOIN_BLOCK));
            } else {
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbruptLab(),
                        _page.getKeyWords().getKeyWordContinue(),
                        label,
                        StringUtil.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordFor(),
                                        _page.getKeyWords().getKeyWordForeach(),
                                        _page.getKeyWords().getKeyWordDo(),
                                        _page.getKeyWords().getKeyWordIter(),
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                ExportCst.JOIN_BLOCK));
            }
            _page.addLocError(un_);
            if (label.isEmpty()) {
                addErrorBlock(un_.getBuiltError());
            } else {
                errorsRefLabels.add(un_.getBuiltError());
            }
        }
    }
    private boolean exitLoop(BreakableBlock _b) {
        if (label.isEmpty()) {
            labelOffsetRef = _b.getRealLabelInfo().getOffset();
            return true;
        }
        if (StringUtil.quickEq(label, _b.getRealLabelInfo().getInfo())){
            labelOffsetRef = _b.getRealLabelInfo().getOffset();
            return true;
        }
        return false;
    }

    public int getLabelOffsetRef() {
        return labelOffsetRef;
    }

    public StringList getErrorsRefLabels() {
        return errorsRefLabels;
    }
}
