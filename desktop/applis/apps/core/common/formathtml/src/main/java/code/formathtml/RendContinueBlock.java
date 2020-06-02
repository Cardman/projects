package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.structs.Struct;
import code.formathtml.stacks.RendAbruptCallingFinally;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendRemovableVars;
import code.formathtml.stacks.RendTryBlockStack;
import code.util.StringList;

public final class RendContinueBlock extends RendLeaf implements RendBuildableElMethod,RendCallingFinally {

    private String label;
    private int labelOffset;

    RendContinueBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        boolean childOfLoop_ = false;
        RendParentBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof RendLoop) {
                if (label.isEmpty()) {
                    childOfLoop_ = true;
                    break;
                }
                if (StringList.quickEq(label, ((RendBreakableBlock)b_).getRealLabel())){
                    childOfLoop_ = true;
                    break;
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            if (label.isEmpty()) {
                un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedAbrupt(),
                        _cont.getKeyWords().getKeyWordContinue(),
                        StringList.join(
                                new StringList(
                                        _cont.getKeyWords().getKeyWordFor(),
                                        _cont.getKeyWords().getKeyWordForeach(),
                                        _cont.getKeyWords().getKeyWordDo(),
                                        _cont.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            } else {
                un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedAbruptLab(),
                        _cont.getKeyWords().getKeyWordContinue(),
                        label,
                        StringList.join(
                                new StringList(
                                        _cont.getKeyWords().getKeyWordFor(),
                                        _cont.getKeyWords().getKeyWordForeach(),
                                        _cont.getKeyWords().getKeyWordDo(),
                                        _cont.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            }
            _cont.addError(un_);
        }
    }


    @Override
    public void processEl(Configuration _cont) {
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendLoop loop_;
        while (true) {
            RendRemovableVars bl_ = ip_.getRendLastStack();
            if (bl_ instanceof RendLoopBlockStack) {
                RendParentBlock br_ = bl_.getBlock();
                if (label.isEmpty()) {
                    br_.removeLocalVars(ip_);
                    loop_ = (RendLoop) br_;
                    break;
                }
                if (StringList.quickEq(label, ((RendBreakableBlock) br_).getRealLabel())){
                    br_.removeLocalVars(ip_);
                    loop_ = (RendLoop) br_;
                    break;
                }
            }
            if (ImportingPage.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.getRendReadWrite().setRead((RendBlock) loop_);
        loop_.processLastElementLoop(_conf);
    }

    @Override
    public RendAbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new RendAbruptCallingFinally(this);
    }
}
