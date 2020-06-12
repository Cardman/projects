package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.util.*;

public abstract class Block implements AnalyzedBlock {
    public static final String OR_EQ = "|=";
    public static final String AND_EQ = "&=";
    public static final String OR_LOG_EQ = "||=";
    public static final String AND_LOG_EQ = "&&=";
    public static final String NULL_EQ = "??=";
    public static final String XOR_EQ = "^=";
    public static final String PLUS_EQ = "+=";
    public static final String INCR = "++";

    protected static final String VARARG = "...";

    protected static final String DOT = ".";

    protected static final String PAR_LEFT = "(";
    protected static final String PAR_RIGHT = ")";
    protected static final String EMPTY_STRING = "";

    private BracedBlock parent;

    private Block nextSibling;

    private Block previousSibling;

    private OffsetsBlock offset;


    Block(OffsetsBlock _offset) {
        offset = _offset;
    }
    protected final void setParent(BracedBlock _b) {
        parent = _b;
    }
    public final OffsetsBlock getOffset() {
        return offset;
    }

    public void checkLabelReference(ContextEl _an, AnalyzingEl _anEl) {
        if (this instanceof BreakableBlock) {
            String label_ = ((BreakableBlock)this).getRealLabel();
            boolean wc_ = true;
            for (char c: label_.toCharArray()) {
                if (StringList.isDollarWordChar(c)) {
                    continue;
                }
                wc_ = false;
                break;
            }
            if (!wc_) {
                FoundErrorInterpret bad_ = new FoundErrorInterpret();
                bad_.setFileName(getFile().getFileName());
                bad_.setIndexFile(0);
                //label_ len
                bad_.buildError(_an.getAnalysisMessages().getBadLabel());
                _an.addError(bad_);
            } else if (!label_.isEmpty()){
                if (StringList.contains(_anEl.getLabels(), label_)) {
                    FoundErrorInterpret dup_ = new FoundErrorInterpret();
                    dup_.setFileName(getFile().getFileName());
                    dup_.setIndexFile(0);
                    //label_ len
                    dup_.buildError(_an.getAnalysisMessages().getDuplicatedLabel());
                    _an.addError(dup_);
                } else {
                    _anEl.getLabels().add(label_);
                }
            }
        }
    }

    public void reach(ContextEl _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        if (_anEl.canCompleteNormallyGroup(prev_)) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    public boolean accessibleCondition() {
        return true;
    }

    public boolean accessibleForNext() {
        return true;
    }

    public abstract void abrupt(ContextEl _an, AnalyzingEl _anEl);
   public abstract void checkTree(ContextEl _an, AnalyzingEl _anEl);

    protected static boolean tryBuildExpressionLanguageReadOnly(Block _block, ContextEl _cont) {
        if (_block instanceof BuildableElMethod) {
            ((BuildableElMethod)_block).buildExpressionLanguageReadOnly(_cont);
            return true;
        }
        return processOther(_block, _cont);
    }

    private static boolean processOther(Block _block, ContextEl _cont) {
        if (_block instanceof UnclassedBracedBlock) {
            return true;
        }
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFileName(_block.getFile().getFileName());
        un_.setIndexFile(_block.getOffset().getOffsetTrim());
        //defined len first key words
        un_.buildError(_cont.getAnalysisMessages().getUnexpectedBlockExp());
        _cont.addError(un_);
        return false;
    }

    public abstract void processReport(ContextEl _cont, CustList<PartOffset> _parts);
    public final void processBlockAndRemove(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.removeLastBlock();
        processBlock(_conf);
    }
    public final void processBlock(ContextEl _conf) {
        Block n_ = getNextSibling();
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (n_ != null) {
            rw_.setBlock(n_);
            return;
        }
        BracedBlock par_ = getParent();
        if (par_ != ip_.getBlockRoot()) {
            if (par_ instanceof Loop) {
                par_.removeLocalVars(ip_);
            } else {
                par_.removeAllVars(ip_);
            }
            rw_.setBlock(par_);
            par_.exitStack(_conf);
            return;
        }
        ip_.setNullReadWrite();
    }

    public final FunctionBlock getFunction() {
        Block b_ = this;
        while (b_ != null) {
            if (b_ instanceof FunctionBlock) {
                return (FunctionBlock)b_;
            }
            b_ = b_.getParent();
        }
        return null;
    }
    public final FileBlock getFile() {
        Block b_ = this;
        while (!(b_ instanceof FileBlock)) {
            b_ = b_.getParent();
        }
        return (FileBlock) b_;
    }

    public final Block getPreviousSibling() {
        return previousSibling;
    }
    public abstract Block getFirstChild();

    public final Block getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(Block _nextSibling) {
        nextSibling = _nextSibling;
    }
    final void setPreviousSibling(Block _previousSibling) {
        previousSibling = _previousSibling;
    }

    public final BracedBlock getParent() {
        return parent;
    }
}
