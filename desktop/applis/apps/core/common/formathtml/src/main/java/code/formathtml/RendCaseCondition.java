package code.formathtml;

import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
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
import code.formathtml.util.AnalyzingDoc;
import code.util.CustList;
import code.util.StringList;

public final class RendCaseCondition extends RendSwitchPartCondition implements RendWithEl {

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
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc) {
        AnalyzedPageEl page_ = _cont.getContext().getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrValue());
        RendParentBlock par_ = getParent();
        if (!(par_ instanceof RendSwitchBlock)) {
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedCaseDef(),
                    _cont.getContext().getAnalyzing().getKeyWords().getKeyWordCase(),
                    value,
                    _cont.getContext().getAnalyzing().getKeyWords().getKeyWordSwitch());
            Configuration.addError(un_, _anaDoc, _cont.getContext().getAnalyzing());
            opValue = RenderExpUtil.getAnalyzedOperations(value,valueOffset,0, _cont, _anaDoc, page_);
            return;
        }
        RendSwitchBlock sw_ = (RendSwitchBlock) par_;
        if (!sw_.getInstanceTest().isEmpty()) {
            if (StringList.quickEq(value, _cont.getContext().getAnalyzing().getKeyWords().getKeyWordNull())) {
                setImportedClassName("");
                return;
            }
            page_.setGlobalOffset(classNameOffset);
            setImportedClassName(ResolvingImportTypes.resolveCorrectType(_cont.getContext(),className));
            TokenErrorMessage res_ = ManageTokens.partVar(page_).checkTokenVar(getVariableName(), page_);
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_anaDoc.getFileName());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                Configuration.addError(d_, _anaDoc, _cont.getContext().getAnalyzing());
                return;
            }
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(getImportedClassName());
            lv_.setConstType(ConstType.FIX_VAR);
            _cont.getContext().getAnalyzing().getInfosVars().put(getVariableName(), lv_);
            return;
        }
        AnaClassArgumentMatching resSwitch_ = sw_.getResult();
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
                    Delimiters d_ = ElResolver.checkSyntax(value, _cont.getContext(), CustList.FIRST_INDEX, page_);
                    OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, value, _cont.getContext(), d_, page_);
                    OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _cont.getContext(), page_);
                    op_.analyze(_cont.getContext());
                    page_.setLookLocalClass(EMPTY_STRING);
                    op_.setOrder(0);
                    opValue = new CustList<RendDynOperationNode>();
                    opValue.add(RendDynOperationNode.createExecOperationNode(op_,_cont.getContext(), page_));
                    checkDuplicateEnumCase(_cont, _anaDoc);
                    return;
                }
                opValue = RenderExpUtil.getAnalyzedOperations(value, valueOffset,0,_cont, _anaDoc, page_);
                Argument a_ = opValue.last().getArgument();
                if (Argument.isNullValue(a_)) {
                    checkDuplicateCase(_cont, a_, _anaDoc);
                    return;
                }
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(valueOffset);
                un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedCaseVar(),
                        _cont.getContext().getAnalyzing().getKeyWords().getKeyWordCase(),
                        value);
                Configuration.addError(un_, _anaDoc, _cont.getContext().getAnalyzing());
                return;
            }
        }
        opValue = RenderExpUtil.getAnalyzedOperations(value,valueOffset,0, _cont, _anaDoc, page_);
        RendDynOperationNode op_ = opValue.last();
        AnaClassArgumentMatching resCase_ = page_.getCurrentRoot().getResultClass();
        Argument arg_ = op_.getArgument();
        if (arg_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(valueOffset);
            un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedCaseVar(),
                    _cont.getContext().getAnalyzing().getKeyWords().getKeyWordCase(),
                    value);
            Configuration.addError(un_, _anaDoc, _cont.getContext().getAnalyzing());
        } else {
            checkDuplicateCase(_cont, arg_, _anaDoc);
            Mapping m_ = new Mapping();
            m_.setArg(resCase_);
            m_.setParam(resSwitch_);
            if (!AnaTemplates.isCorrectOrNumbers(m_,_cont.getContext())) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(valueOffset);
                un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedCaseValue(),
                        _cont.getContext().getAnalyzing().getKeyWords().getKeyWordCase(),
                        AnaApplyCoreMethodUtil.getString(arg_, _cont.getContext().getAnalyzing()),
                        StringList.join(resSwitch_.getNames(),AND_ERR));
                Configuration.addError(un_, _anaDoc, _cont.getContext().getAnalyzing());
            }
        }
    }

    private void checkDuplicateCase(Configuration _cont, Argument _arg, AnalyzingDoc _anaDoc) {
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
                        un_.setFileName(_anaDoc.getFileName());
                        un_.setIndexFile(getValueOffset()+ getOffset().getOffsetTrim());
                        un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedCaseDup(),
                                _cont.getContext().getAnalyzing().getKeyWords().getKeyWordCase(),
                                AnaApplyCoreMethodUtil.getString(_arg, _cont.getContext().getAnalyzing()),
                                _cont.getContext().getAnalyzing().getKeyWords().getKeyWordSwitch());
                        Configuration.addError(un_, _anaDoc, _cont.getContext().getAnalyzing());
                        break;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
    }
    private void checkDuplicateEnumCase(Configuration _cont, AnalyzingDoc _anaDoc) {
        RendParentBlock par_ = getParent();
        RendBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof RendCaseCondition) {
                RendCaseCondition c_ = (RendCaseCondition) first_;
                String v_ = c_.value.trim();
                if (StringList.quickEq(v_, value.trim())) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_anaDoc.getFileName());
                    un_.setIndexFile(getValueOffset()+ getOffset().getOffsetTrim());
                    un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedCaseDup(),
                            _cont.getContext().getAnalyzing().getKeyWords().getKeyWordCase(),
                            value.trim(),
                            _cont.getContext().getAnalyzing().getKeyWords().getKeyWordSwitch());
                    Configuration.addError(un_, _anaDoc, _cont.getContext().getAnalyzing());
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
