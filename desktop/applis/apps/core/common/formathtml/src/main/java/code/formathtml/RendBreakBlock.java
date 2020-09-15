package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.structs.Struct;
import code.formathtml.stacks.*;
import code.formathtml.util.AnalyzingDoc;
import code.util.StringList;

public final class RendBreakBlock extends RendLeaf implements RendBuildableElMethod,RendCallingFinally {

    private String label;
    private int labelOffset;
    RendBreakBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }


    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc) {
        boolean childOfBreakable_ = false;
        RendParentBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof RendBreakableBlock) {
                if (label.isEmpty()) {
                    if (b_ instanceof RendLoop || b_ instanceof RendSwitchBlock) {
                        childOfBreakable_ = true;
                        break;
                    }
                } else {
                    if (StringList.quickEq(label, ((RendBreakableBlock)b_).getRealLabel())){
                        childOfBreakable_ = true;
                        break;
                    }
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            AnalyzedPageEl page_ = _cont.getContext().getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            if (label.isEmpty()) {
                un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedAbrupt(),
                        _cont.getKeyWords().getKeyWordBreak(),
                        StringList.join(
                                new StringList(
                                        _cont.getKeyWords().getKeyWordSwitch(),
                                        _cont.getKeyWords().getKeyWordFor(),
                                        _cont.getKeyWords().getKeyWordForeach(),
                                        _cont.getKeyWords().getKeyWordDo(),
                                        _cont.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            } else {
                un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedAbruptLab(),
                        _cont.getKeyWords().getKeyWordBreak(),
                        label,
                        StringList.join(
                                new StringList(
                                        _cont.getKeyWords().getKeyWordSwitch(),
                                        _cont.getKeyWords().getKeyWordTry(),
                                        _cont.getKeyWords().getKeyWordCatch(),
                                        _cont.getKeyWords().getKeyWordFinally(),
                                        _cont.getKeyWords().getKeyWordIf(),
                                        _cont.getKeyWords().getKeyWordElseif(),
                                        _cont.getKeyWords().getKeyWordElse(),
                                        _cont.getKeyWords().getKeyWordFor(),
                                        _cont.getKeyWords().getKeyWordForeach(),
                                        _cont.getKeyWords().getKeyWordDo(),
                                        _cont.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            }
            Configuration.addError(un_, _anaDoc, _cont.getContext().getAnalyzing());
        }
    }

    @Override
    public void processEl(Configuration _cont) {
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendRemovableVars stack_;
        while (true) {
            RendRemovableVars bl_ = ip_.getRendLastStack();
            stack_ = bl_;
            if (label.isEmpty()) {
                if (bl_ instanceof RendLoopBlockStack || bl_ instanceof RendSwitchBlockStack) {
                    break;
                }
            } else {
                RendParentBlock par_ = bl_.getBlock();
                if (par_ instanceof RendBreakableBlock) {
                    RendBreakableBlock br_ = (RendBreakableBlock) par_;
                    if (StringList.quickEq(label, br_.getRealLabel())){
                        break;
                    }
                }
            }
            if (ImportingPage.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        RendBlock forLoopLoc_ = stack_.getLastBlock();
        rw_.setRead(forLoopLoc_);
        if (stack_ instanceof RendLoopBlockStack) {
            ((RendLoopBlockStack)stack_).setFinished(true);
        }
    }

    @Override
    public RendAbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new RendAbruptCallingFinally(this);
    }
}
