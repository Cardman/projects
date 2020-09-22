package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.structs.Struct;
import code.formathtml.stacks.*;
import code.formathtml.util.AnalyzingDoc;
import code.util.StringList;

public final class RendBreakBlock extends RendLeaf implements RendWithEl,RendCallingFinally {

    private String label;
    private int labelOffset;
    RendBreakBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }


    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
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
            _page.setGlobalOffset(getOffset().getOffsetTrim());
            _page.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            if (label.isEmpty()) {
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbrupt(),
                        _page.getKeyWords().getKeyWordBreak(),
                        StringList.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordSwitch(),
                                        _page.getKeyWords().getKeyWordFor(),
                                        _page.getKeyWords().getKeyWordForeach(),
                                        _page.getKeyWords().getKeyWordDo(),
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            } else {
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbruptLab(),
                        _page.getKeyWords().getKeyWordBreak(),
                        label,
                        StringList.join(
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
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            }
            Configuration.addError(un_, _anaDoc, _page);
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
