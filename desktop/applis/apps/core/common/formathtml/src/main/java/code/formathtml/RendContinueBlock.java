package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.structs.Struct;
import code.formathtml.stacks.RendAbruptCallingFinally;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendRemovableVars;
import code.formathtml.util.AnalyzingDoc;
import code.util.StringList;

public final class RendContinueBlock extends RendLeaf implements RendWithEl,RendCallingFinally {

    private String label;
    private int labelOffset;

    RendContinueBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
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
            _page.setGlobalOffset(getOffset().getOffsetTrim());
            _page.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            if (label.isEmpty()) {
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbrupt(),
                        _page.getKeyWords().getKeyWordContinue(),
                        StringList.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordFor(),
                                        _page.getKeyWords().getKeyWordForeach(),
                                        _page.getKeyWords().getKeyWordDo(),
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            } else {
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbruptLab(),
                        _page.getKeyWords().getKeyWordContinue(),
                        label,
                        StringList.join(
                                new StringList(
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
