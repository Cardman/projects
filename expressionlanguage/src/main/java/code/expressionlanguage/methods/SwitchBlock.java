package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.BadConstructorCall;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;

public final class SwitchBlock extends BracedStack implements BreakableBlock {

    private final String value;
    private int valueOffset;

    private CustList<OperationNode> opValue;

    public SwitchBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        value = _el.getAttribute(ATTRIBUTE_VALUE);
    }

    public SwitchBlock(ContextEl _importingPage, int _indexChild, BracedBlock _m, OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    public int getValueOffset() {
        return valueOffset;
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
        return tr_;
    }

    public String getValue() {
        return value;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opValue);
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        Block first_ = getFirstChild();
        while (first_ != null) {
            Block elt_ = first_;
            if (elt_ instanceof CaseCondition) {
                first_ = first_.getNextSibling();
                continue;
            }
            if (elt_ instanceof DefaultCondition) {
                first_ = first_.getNextSibling();
                continue;
            }
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        _cont.setRootAffect(false);
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opValue.isEmpty()) {
            return;
        }
        if (opValue.last().isVoidArg(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, valueOffset));
            un_.setType(opValue.last().getResultClass().getName());
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        CustList<Block> ch_ = Classes.getDirectChildren(this);
        if (ch_.isEmpty()) {
            return false;
        }
        return ch_.last().canBeLastOfBlockGroup();
    }


    @Override
    public void checkCallConstructor(ContextEl _cont) {
        AnalyzedPageEl p_ = _cont.getAnalyzing();
        p_.setGlobalOffset(valueOffset);
        for (OperationNode o: opValue) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                BadConstructorCall call_ = new BadConstructorCall();
                call_.setFileName(getFile().getFileName());
                call_.setRc(getRowCol(0, valueOffset));
                call_.setLocalOffset(getRowCol(o.getFullIndexInEl(), valueOffset));
                _cont.getClasses().getErrorsDet().add(call_);
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_SWITCH;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_.getBlock() == this) {
                ip_.removeLastBlock();
                processBlock(_cont);
                return;
            }
        }
        SwitchBlockStack if_ = new SwitchBlockStack();
        Block n_ = getFirstChild();
        while (n_ != null) {
            if_.getBlocks().add((BracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        if_.setBlock(this);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        Argument arg_ =  el_.calculateMember(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        if_.setStruct(arg_.getStruct());
        if (if_.getBlocks().isEmpty()) {
            if_.setFinished(true);
            ip_.addBlock(if_);
            return;
        }
        rw_.setBlock(getFirstChild());
        ip_.addBlock(if_);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return getEl();
    }
    @Override
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            return;
        }
        boolean abrupt_ = true;
        boolean def_ = false;
        while (ch_.getNextSibling() != null) {
            if (ch_ instanceof DefaultCondition) {
                def_ = true;
            }
            ch_ = ch_.getNextSibling();
        }
        if (ch_ instanceof DefaultCondition) {
            def_ = true;
        }
        if (_anEl.canCompleteNormally(ch_)) {
            abrupt_ = false;
        } else if (ch_.getFirstChild() == null) {
            abrupt_ = false;
        } else if (!def_) {
            abrupt_ = false;
        }
        IdMap<BreakBlock, BreakableBlock> breakables_;
        breakables_ = _anEl.getBreakables();
        for (EntryCust<BreakBlock, BreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == this && _anEl.isReachable(e.getKey())) {
                abrupt_ = false;
                break;
            }
        }
        if (abrupt_) {
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }
}
