package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.stacks.LoopStack;
import code.formathtml.stacks.*;
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
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
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
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
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
            RendParentBlock cur_ = bl_.getCurrentVisitedBlock();
            cur_.removeAllVars(ip_);
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
            if (ImportingPage.setRemovedCallingFinallyToProcess(ip_,bl_,this)) {
                return;
            }
        }
        RendBlock forLoopLoc_ = stack_.getLastBlock();
        rw_.setRead(forLoopLoc_);
        if (stack_ instanceof LoopStack) {
            ((LoopStack)stack_).setFinished(true);
        }
    }
}
