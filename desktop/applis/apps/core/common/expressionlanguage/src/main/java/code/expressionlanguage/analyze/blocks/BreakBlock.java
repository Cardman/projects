package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.util.StringList;
import code.util.core.StringUtil;

public final class BreakBlock extends AbruptBlock {

    private String label;
    private int labelOffset;
    private int labelOffsetRef;
    private StringList errorsRefLabels = new StringList();

    public BreakBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
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
        checkBreakable(_page);
//        ExecBreakBlock exec_ = new ExecBreakBlock(getOffset(),label);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);
    }

    private void checkBreakable(AnalyzedPageEl _page) {
        boolean childOfBreakable_ = false;
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof BreakableBlock) {
                if (label.isEmpty()) {
                    if (b_ instanceof Loop || b_ instanceof SwitchBlock) {
                        childOfBreakable_ = true;
                        break;
                    }
                } else {
                    if (StringUtil.quickEq(label, ((BreakableBlock)b_).getRealLabel())){
                        childOfBreakable_ = true;
                        labelOffsetRef = ((BreakableBlock) b_).getRealLabelOffset();
                        break;
                    }
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            AnalyzedPageEl page_ = _page;
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            if (label.isEmpty()) {
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbrupt(),
                        _page.getKeyWords().getKeyWordBreak(),
                        StringUtil.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordSwitch(),
                                        _page.getKeyWords().getKeyWordFor(),
                                        _page.getKeyWords().getKeyWordForeach(),
                                        _page.getKeyWords().getKeyWordDo(),
                                        _page.getKeyWords().getKeyWordIter(),
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                "|"));
            } else {
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbruptLab(),
                        _page.getKeyWords().getKeyWordBreak(),
                        label,
                        StringUtil.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordSwitch(),
                                        _page.getKeyWords().getKeyWordTry(),
                                        _page.getKeyWords().getKeyWordCatch(),
                                        _page.getKeyWords().getKeyWordFinally(),
                                        _page.getKeyWords().getKeyWordIf(),
                                        _page.getKeyWords().getKeyWordElseif(),
                                        _page.getKeyWords().getKeyWordElse(),
                                        _page.getKeyWords().getKeyWordFor(),
                                        _page.getKeyWords().getKeyWordForeach(),
                                        _page.getKeyWords().getKeyWordDo(),
                                        _page.getKeyWords().getKeyWordIter(),
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                "|"));
            }
            _page.addLocError(un_);
            if (label.isEmpty()) {
                setReachableError(true);
                getErrorsBlock().add(un_.getBuiltError());
            } else {
                errorsRefLabels.add(un_.getBuiltError());
            }
        }
    }

    public int getLabelOffsetRef() {
        return labelOffsetRef;
    }

    public StringList getErrorsRefLabels() {
        return errorsRefLabels;
    }
}
