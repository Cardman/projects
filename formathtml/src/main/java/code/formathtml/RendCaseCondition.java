package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.Delimiters;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.InnerTypeOrElement;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendSwitchBlockStack;
import code.util.CustList;
import code.util.StringList;

public final class RendCaseCondition extends RendParentBlock implements RendBuildableElMethod {

    private final String value;
    private CustList<RendDynOperationNode> opValue;

    private int valueOffset;
    RendCaseCondition(OffsetStringInfo _value, OffsetsBlock _offset) {
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
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        _cont.getAnalyzingDoc().setAttribute(ATTRIBUTE_VALUE);
        RendParentBlock par_ = getParent();
        MethodAccessKind stCtx_ = _doc.getStaticContext();
        if (!(par_ instanceof RendSwitchBlock)) {
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
            opValue = RenderExpUtil.getAnalyzedOperations(value,0, _cont, Calculation.staticCalculation(stCtx_));
            return;
        }
        RendSwitchBlock sw_ = (RendSwitchBlock) par_;
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
                    _cont.setAccessStaticContext(MethodAccessKind.STATIC);
                    Delimiters d_ = ElResolver.checkSyntax(value, _cont, CustList.FIRST_INDEX);
                    OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, value, _cont, d_);
                    OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _cont);
                    op_.analyze(_cont);
                    _cont.setLookLocalClass(EMPTY_STRING);
                    op_.setOrder(0);
                    opValue = new CustList<RendDynOperationNode>();
                    opValue.add(RendDynOperationNode.createExecOperationNode(op_));
                    checkDuplicateEnumCase(_cont);
                    return;
                }
                opValue = RenderExpUtil.getAnalyzedOperations(value, 0,_cont, Calculation.staticCalculation(stCtx_));
                Argument a_ = opValue.last().getArgument();
                if (Argument.isNullValue(a_)) {
                    checkDuplicateCase(_cont, a_);
                    return;
                }
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(_cont.getCurrentFileName());
                un_.setIndexFile(valueOffset);
                un_.setType(opValue.last().getResultClass());
                _cont.getClasses().addError(un_);
                return;
            }
        }
        opValue = RenderExpUtil.getAnalyzedOperations(value,0, _cont, Calculation.staticCalculation(stCtx_));
        RendDynOperationNode op_ = opValue.last();
        ClassArgumentMatching resCase_ = op_.getResultClass();
        if (resCase_.matchVoid(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(resCase_);
            _cont.getClasses().addError(un_);
            return;
        }
        Argument arg_ = op_.getArgument();
        if (arg_ == null) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(resCase_);
            _cont.getClasses().addError(un_);
        } else {
            checkDuplicateCase(_cont, arg_);
        }
        if (!PrimitiveTypeUtil.canBeUseAsArgument(resSwitch_, resCase_, _cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(resCase_);
            _cont.getClasses().addError(un_);
        }
    }

    private void checkDuplicateCase(Configuration _cont, Argument _arg) {
        RendParentBlock par_ = getParent();
        RendBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof RendCaseCondition) {
                RendCaseCondition c_ = (RendCaseCondition) first_;
                RendDynOperationNode curOp_ = c_.opValue.last();
                Argument a_ = curOp_.getArgument();
                if (a_ != null) {
                    if (_arg.getStruct().sameReference(a_.getStruct())) {
                        UnexpectedTagName un_ = new UnexpectedTagName();
                        un_.setFileName(_cont.getCurrentFileName());
                        un_.setIndexFile(getValueOffset()+ getOffset().getOffsetTrim());
                        _cont.getClasses().addError(un_);
                        break;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
    }
    private void checkDuplicateEnumCase(Configuration _cont) {
        RendParentBlock par_ = getParent();
        RendBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof RendCaseCondition) {
                RendCaseCondition c_ = (RendCaseCondition) first_;
                String v_ = c_.value.trim();
                if (StringList.quickEq(v_, value.trim())) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(_cont.getCurrentFileName());
                    un_.setIndexFile(getValueOffset()+ getOffset().getOffsetTrim());
                    _cont.getClasses().addError(un_);
                    break;
                }

            }
            first_ = first_.getNextSibling();
        }
    }
    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getFirstChild());
    }

    @Override
    public void exitStack(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendSwitchBlockStack if_ = (RendSwitchBlockStack) ip_.getRendLastStack();
        if (if_.getLastVisitedBlock() == this) {
            rw_.setRead(if_.getBlock());
        } else {
            rw_.setRead(getNextSibling());
        }
    }

    public CustList<RendDynOperationNode> getOpValue() {
        return opValue;
    }
}
