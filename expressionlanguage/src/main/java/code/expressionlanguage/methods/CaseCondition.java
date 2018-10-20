package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Delimiters;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.StaticAccessOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.util.CustList;
import code.util.StringList;

public final class CaseCondition extends SwitchPartBlock implements IncrCurrentGroup, IncrNextGroup {

    private final String value;
    private CustList<OperationNode> opValue;
    private boolean possibleSkipNexts;

    private int valueOffset;

    public CaseCondition(ContextEl _importingPage,
            int _indexChild, BracedBlock _m, OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public String getValue() {
        return value;
    }

    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
    }

    @Override
    boolean isAlwaysExitable() {
        return getFirstChild() == null || !isPossibleSkipNexts();
    }

    boolean isPossibleSkipNexts() {
        return possibleSkipNexts;
    }

    void setPossibleSkipNexts(boolean _possibleSkipNexts) {
        possibleSkipNexts = _possibleSkipNexts;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        _cont.setRootAffect(false);
        BracedBlock par_ = getParent();
        if (!(par_ instanceof SwitchBlock)) {
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().addError(un_);
            return;
        }
        SwitchBlock sw_ = (SwitchBlock) par_;
        ClassArgumentMatching resSwitch_ = sw_.getOpValue().last().getResultClass();
        StringList names_ = resSwitch_.getNames();
        if (names_.size() == 1) {
            String exp_ = names_.first();
            String id_ = Templates.getIdFromAllTypes(exp_);
            GeneType g_ = _cont.getClassBody(id_);
            if (g_ instanceof EnumBlock) {
                for (GeneField f: ContextEl.getFieldBlocks(g_)) {
                    if (!StringList.quickEq(f.getFieldName(), value.trim())) {
                        continue;
                    }
                    _cont.setLookLocalClass(id_);
                    _cont.setStaticContext(true);
                    Delimiters d_ = ElResolver.checkSyntax(value, _cont, CustList.FIRST_INDEX);
                    _cont.setAnalyzingRoot(true);
                    OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, value, _cont, d_);
                    OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _cont);
                    _cont.setAnalyzingRoot(false);
                    defaultAssignmentBefore(_cont, op_);
                    op_.setStaticBlock(true);
                    op_.analyze(_cont);
                    _cont.setLookLocalClass(EMPTY_STRING);
                    op_.tryAnalyzeAssignmentAfter(_cont);
                    opValue = new CustList<OperationNode>();
                    opValue.add(op_);
                    return;
                }
                opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(f_.isStaticContext()));
                if (opValue.isEmpty()) {
                    return;
                }
                if (Argument.isNullValue(opValue.last().getArgument())) {
                    return;
                }
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, valueOffset));
                un_.setType(opValue.last().getResultClass());
                _cont.getClasses().addError(un_);
                return;
            }
        }
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opValue.isEmpty()) {
            return;
        }
        if (opValue.last().isVoidArg(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, valueOffset));
            un_.setType(opValue.last().getResultClass());
            _cont.getClasses().addError(un_);
        }
        if (opValue.last().getArgument() == null) {
            OperationNode last_ = opValue.last();
            if (!(last_ instanceof SettableAbstractFieldOperation)) {
                if (!(last_ instanceof DotOperation)) {
                    UnexpectedTypeError un_ = new UnexpectedTypeError();
                    un_.setFileName(getFile().getFileName());
                    un_.setRc(getRowCol(0, valueOffset));
                    un_.setType(opValue.last().getResultClass());
                    _cont.getClasses().addError(un_);
                    return;
                }
                DotOperation d_ = (DotOperation) last_;
                if (!(d_.getFirstChild() instanceof StaticAccessOperation)) {
                    UnexpectedTypeError un_ = new UnexpectedTypeError();
                    un_.setFileName(getFile().getFileName());
                    un_.setRc(getRowCol(0, valueOffset));
                    un_.setType(opValue.last().getResultClass());
                    _cont.getClasses().addError(un_);
                    return;
                }
                if (!(d_.getFirstChild().getNextSibling() instanceof SettableAbstractFieldOperation)) {
                    UnexpectedTypeError un_ = new UnexpectedTypeError();
                    un_.setFileName(getFile().getFileName());
                    un_.setRc(getRowCol(0, valueOffset));
                    un_.setType(opValue.last().getResultClass());
                    _cont.getClasses().addError(un_);
                    return;
                }
                last_ = d_.getFirstChild().getNextSibling();
            }
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation) last_;
            ClassField clField_ = cst_.getFieldId();
            if (clField_ == null) {
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, valueOffset));
                un_.setType(opValue.last().getResultClass());
                _cont.getClasses().addError(un_);
                return;
            }
            if (!cst_.getFieldMetaInfo().isEnumField()) {
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, valueOffset));
                un_.setType(opValue.last().getResultClass());
                _cont.getClasses().addError(un_);
                return;
            }
        }
        ClassArgumentMatching resCase_ = opValue.last().getResultClass();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(resSwitch_, resCase_, _cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, valueOffset));
            un_.setType(opValue.last().getResultClass());
            _cont.getClasses().addError(un_);
        }
    }

    public ClassField getFieldId() {
        OperationNode last_ = opValue.last();
        if (!(last_ instanceof SettableAbstractFieldOperation)) {
            DotOperation d_ = (DotOperation) last_;
            last_ = d_.getFirstChild().getNextSibling();
            return ((SettableAbstractFieldOperation)last_).getFieldId();
        }
        return ((SettableAbstractFieldOperation)last_).getFieldId();
    }
    public CustList<OperationNode> getOpValue() {
        return opValue;
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return true;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack sw_ = (SwitchBlockStack) ip_.getLastStack();
        sw_.setCurentVisitedBlock(this);
        if (sw_.isEntered()) {
            if (!hasChildNodes()) {
                if (sw_.getLastVisitedBlock() == this) {
                    sw_.setFinished(true);
                    rw_.setBlock(sw_.getBlock());
                    return;
                }
                rw_.setBlock(getNextSibling());
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        }
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        if (hasChildNodes()) {
            sw_.setEntered(true);
        } else {
            if (sw_.getLastVisitedBlock() != this) {
                sw_.setEntered(true);
                rw_.setBlock(getNextSibling());
                return;
            }
            sw_.setFinished(true);
            rw_.setBlock(sw_.getBlock());
            return;
        }
        rw_.setBlock(getFirstChild());
        return;
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack if_ = (SwitchBlockStack) ip_.getLastStack();
        if (if_.getLastVisitedBlock() == this) {
            if_.setFinished(true);
            rw_.setBlock(if_.getBlock());
        } else {
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return getValueEl();
    }
}
