package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.*;
import code.expressionlanguage.methods.util.AbstractCoverageResult;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.util.CustList;
import code.util.StringList;

public final class CaseCondition extends SwitchPartBlock {

    private final String value;
    private CustList<ExecOperationNode> opValue;

    private int valueOffset;

    public CaseCondition(OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_offset);
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        BracedBlock par_ = getParent();
        boolean stCtx_ = f_.isStaticContext();
        if (!(par_ instanceof SwitchBlock)) {
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
            opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(stCtx_));
            return;
        }
        _cont.getCoverage().putBlockOperationsSwitchs(_cont,par_,this);
        SwitchBlock sw_ = (SwitchBlock) par_;
        ClassArgumentMatching resSwitch_ = sw_.getOpValue().last().getResultClass();
        String type_ = resSwitch_.getSingleNameOrEmpty();
        if (!type_.isEmpty()) {
            String id_ = Templates.getIdFromAllTypes(type_);
            GeneType g_ = _cont.getClassBody(id_);
            if (g_ instanceof EnumBlock) {
                for (GeneField f: ContextEl.getFieldBlocks(g_)) {
                    if (!(f instanceof InnerTypeOrElement)) {
                        continue;
                    }
                    InnerTypeOrElement e_ = (InnerTypeOrElement) f;
                    if (!StringList.quickEq(e_.getUniqueFieldName(), value.trim())) {
                        continue;
                    }
                    _cont.setLookLocalClass(id_);
                    _cont.setStaticContext(true);
                    Delimiters d_ = ElResolver.checkSyntax(value, _cont, CustList.FIRST_INDEX);
                    OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, value, _cont, d_);
                    OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _cont);
                    defaultAssignmentBefore(_cont, op_);
                    op_.analyze(_cont);
                    _cont.setLookLocalClass(EMPTY_STRING);
                    op_.tryAnalyzeAssignmentAfter(_cont);
                    op_.setOrder(0);
                    opValue = new CustList<ExecOperationNode>();
                    opValue.add((ExecOperationNode) ExecOperationNode.createExecOperationNode(op_));
                    defaultAssignmentAfter(_cont, op_);
                    checkDuplicateEnumCase(_cont);
                    return;
                }
                opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(stCtx_));
                Argument a_ = opValue.last().getArgument();
                if (Argument.isNullValue(a_)) {
                    checkDuplicateCase(_cont, a_);
                    return;
                }
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(valueOffset);
                un_.setType(opValue.last().getResultClass());
                _cont.getClasses().addError(un_);
                return;
            }
        }
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(stCtx_));
        ExecOperationNode op_ = opValue.last();
        ClassArgumentMatching resCase_ = op_.getResultClass();
        if (resCase_.matchVoid(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(resCase_);
            _cont.getClasses().addError(un_);
            return;
        }
        Argument arg_ = op_.getArgument();
        if (arg_ == null) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(resCase_);
            _cont.getClasses().addError(un_);
        } else {
            checkDuplicateCase(_cont, arg_);
        }
        if (!PrimitiveTypeUtil.canBeUseAsArgument(resSwitch_, resCase_, _cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(resCase_);
            _cont.getClasses().addError(un_);
        }
    }

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    private void checkDuplicateCase(ContextEl _cont, Argument _arg) {
        BracedBlock par_ = getParent();
        Block first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof CaseCondition) {
                CaseCondition c_ = (CaseCondition) first_;
                ExecOperationNode curOp_ = c_.opValue.last();
                Argument a_ = curOp_.getArgument();
                if (a_ != null) {
                    if (_arg.getStruct().sameReference(a_.getStruct())) {
                        UnexpectedTagName un_ = new UnexpectedTagName();
                        un_.setFileName(getFile().getFileName());
                        un_.setIndexFile(getValueOffset()+ getOffset().getOffsetTrim());
                        _cont.getClasses().addError(un_);
                        break;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
    }
    private void checkDuplicateEnumCase(ContextEl _cont) {
        BracedBlock par_ = getParent();
        Block first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof CaseCondition) {
                CaseCondition c_ = (CaseCondition) first_;
                String v_ = c_.value.trim();
                if (StringList.quickEq(v_, value.trim())) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(getValueOffset()+ getOffset().getOffsetTrim());
                    _cont.getClasses().addError(un_);
                    break;
                }
                
            }
            first_ = first_.getNextSibling();
        }
    }
    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack sw_ = (SwitchBlockStack) ip_.getLastStack();
        sw_.setCurentVisitedBlock(this);
        if (sw_.isEntered()) {
            rw_.setBlock(getFirstChild());
            return;
        }
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        sw_.setEntered(true);
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack if_ = (SwitchBlockStack) ip_.getLastStack();
        if (if_.getLastVisitedBlock() == this) {
            rw_.setBlock(if_.getBlock());
        } else {
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        BracedBlock parent_ = getParent();
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverSwitchs().getVal(parent_).getVal(this);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getValueOffset();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ getValue().length()));
    }
}
