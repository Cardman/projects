package code.formathtml;

import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendSwitchBlockStack;
import code.util.CustList;
import code.util.StringList;

public final class RendCaseCondition extends RendSwitchPartCondition implements RendBuildableElMethod {

    private final String value;
    private CustList<RendDynOperationNode> opValue;

    private final int classNameOffset;
    private final String className;
    private final int variableOffset;
    private int valueOffset;
    RendCaseCondition(OffsetStringInfo _className, OffsetStringInfo _variable,OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_offset);
        classNameOffset = _className.getOffset();
        className = _className.getInfo();
        variableOffset = _variable.getOffset();
        setVariableName(_variable.getInfo());
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
        _cont.getAnalyzingDoc().setAttribute(_cont.getRendKeyWords().getAttrValue());
        RendParentBlock par_ = getParent();
        if (!(par_ instanceof RendSwitchBlock)) {
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCaseDef(),
                    _cont.getKeyWords().getKeyWordCase(),
                    value,
                    _cont.getKeyWords().getKeyWordSwitch());
            _cont.addError(un_);
            opValue = RenderExpUtil.getAnalyzedOperations(value,valueOffset,0, _cont);
            return;
        }
        RendSwitchBlock sw_ = (RendSwitchBlock) par_;
        if (!sw_.getInstanceTest().isEmpty()) {
            if (StringList.quickEq(value, _cont.getContext().getKeyWords().getKeyWordNull())) {
                setImportedClassName("");
                return;
            }
            page_.setGlobalOffset(classNameOffset);
            setImportedClassName(ResolvingImportTypes.resolveCorrectType(_cont.getContext(),className));
            TokenErrorMessage res_ = ManageTokens.partVar(_cont.getContext()).checkTokenVar(_cont.getContext(), getVariableName());
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_cont.getCurrentFileName());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                _cont.addError(d_);
                return;
            }
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(getImportedClassName());
            lv_.setConstType(ConstType.FIX_VAR);
            _cont.getAnalyzing().getInfosVars().put(getVariableName(), lv_);
            return;
        }
        ClassArgumentMatching resSwitch_ = sw_.getOpValue().last().getResultClass();
        String type_ = resSwitch_.getSingleNameOrEmpty();
        if (!type_.isEmpty()) {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            AnaGeneType g_ = _cont.getContext().getAnalyzing().getAnaClassBody(id_);
            if (g_ instanceof EnumBlock) {
                for (InfoBlock f: ClassesUtil.getFieldBlocks((RootBlock) g_)) {
                    if (!(f instanceof InnerTypeOrElement)) {
                        continue;
                    }
                    InnerTypeOrElement e_ = (InnerTypeOrElement) f;
                    if (!StringList.contains(e_.getFieldName(), value.trim())) {
                        continue;
                    }
                    page_.setLookLocalClass(id_);
                    page_.setAccessStaticContext(MethodAccessKind.STATIC);
                    Delimiters d_ = ElResolver.checkSyntax(value, _cont.getContext(), CustList.FIRST_INDEX);
                    OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, value, _cont.getContext(), d_);
                    OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _cont.getContext());
                    op_.analyze(_cont.getContext());
                    page_.setLookLocalClass(EMPTY_STRING);
                    op_.setOrder(0);
                    opValue = new CustList<RendDynOperationNode>();
                    opValue.add(RendDynOperationNode.createExecOperationNode(op_,_cont.getContext()));
                    checkDuplicateEnumCase(_cont);
                    return;
                }
                opValue = RenderExpUtil.getAnalyzedOperations(value, valueOffset,0,_cont);
                Argument a_ = opValue.last().getArgument();
                if (Argument.isNullValue(a_)) {
                    checkDuplicateCase(_cont, a_);
                    return;
                }
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_cont.getCurrentFileName());
                un_.setIndexFile(valueOffset);
                un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCaseVar(),
                        _cont.getKeyWords().getKeyWordCase(),
                        value);
                _cont.addError(un_);
                return;
            }
        }
        opValue = RenderExpUtil.getAnalyzedOperations(value,valueOffset,0, _cont);
        RendDynOperationNode op_ = opValue.last();
        ClassArgumentMatching resCase_ = op_.getResultClass();
        Argument arg_ = op_.getArgument();
        if (arg_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(valueOffset);
            un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCaseVar(),
                    _cont.getKeyWords().getKeyWordCase(),
                    value);
            _cont.addError(un_);
        } else {
            checkDuplicateCase(_cont, arg_);
            Mapping m_ = new Mapping();
            m_.setArg(resCase_);
            m_.setParam(resSwitch_);
            if (!AnaTemplates.isCorrectOrNumbers(m_,_cont.getContext())) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_cont.getCurrentFileName());
                un_.setIndexFile(valueOffset);
                un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCaseValue(),
                        _cont.getKeyWords().getKeyWordCase(),
                        AnaApplyCoreMethodUtil.getString(arg_,_cont.getContext()),
                        StringList.join(resSwitch_.getNames(),AND_ERR));
                _cont.addError(un_);
            }
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
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_cont.getCurrentFileName());
                        un_.setIndexFile(getValueOffset()+ getOffset().getOffsetTrim());
                        un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCaseDup(),
                                _cont.getKeyWords().getKeyWordCase(),
                                AnaApplyCoreMethodUtil.getString(_arg,_cont.getContext()),
                                _cont.getKeyWords().getKeyWordSwitch());
                        _cont.addError(un_);
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
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_cont.getCurrentFileName());
                    un_.setIndexFile(getValueOffset()+ getOffset().getOffsetTrim());
                    un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCaseDup(),
                            _cont.getKeyWords().getKeyWordCase(),
                            value.trim(),
                            _cont.getKeyWords().getKeyWordSwitch());
                    _cont.addError(un_);
                    break;
                }

            }
            first_ = first_.getNextSibling();
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        if (!getVariableName().isEmpty()) {
            _ip.getInfosVars().removeKey(getVariableName());
        }
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        if (!getVariableName().isEmpty()) {
            _ip.getValueVars().removeKey(getVariableName());
        }
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getFirstChild());
        ip_.getRendLastStack().setCurrentVisitedBlock(this);
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
