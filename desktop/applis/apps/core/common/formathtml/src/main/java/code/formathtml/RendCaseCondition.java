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
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;
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
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(valueOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrValue());
        RendParentBlock par_ = getParent();
        if (!(par_ instanceof RendSwitchBlock)) {
            _page.setGlobalOffset(getOffset().getOffsetTrim());
            _page.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordCase(),
                    value,
                    _page.getKeyWords().getKeyWordSwitch());
            Configuration.addError(un_, _anaDoc, _page);
            opValue = RenderExpUtil.getAnalyzedOperations(value, 0, _anaDoc, _page);
            return;
        }
        RendSwitchBlock sw_ = (RendSwitchBlock) par_;
        if (!sw_.getInstanceTest().isEmpty()) {
            if (StringList.quickEq(value, _page.getKeyWords().getKeyWordNull())) {
                setImportedClassName("");
                return;
            }
            _page.setGlobalOffset(classNameOffset);
            setImportedClassName(ResolvingImportTypes.resolveCorrectType(className, _page));
            TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(getVariableName(), _page);
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_anaDoc.getFileName());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                Configuration.addError(d_, _anaDoc, _page);
                return;
            }
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(getImportedClassName());
            lv_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(getVariableName(), lv_);
            return;
        }
        AnaClassArgumentMatching resSwitch_ = sw_.getResult();
        String type_ = resSwitch_.getSingleNameOrEmpty();
        if (!type_.isEmpty()) {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            AnaGeneType g_ = _page.getAnaClassBody(id_);
            if (g_ instanceof EnumBlock) {
                for (InfoBlock f: ClassesUtil.getFieldBlocks((RootBlock) g_)) {
                    if (!(f instanceof InnerTypeOrElement)) {
                        continue;
                    }
                    InnerTypeOrElement e_ = (InnerTypeOrElement) f;
                    if (!StringList.contains(e_.getFieldName(), value.trim())) {
                        continue;
                    }
                    _page.setLookLocalClass(id_);
                    _page.setAccessStaticContext(MethodAccessKind.STATIC);
                    Delimiters d_ = ElResolver.checkSyntax(value, CustList.FIRST_INDEX, _page);
                    OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, value, d_, _page);
                    OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _page);
                    op_.analyze(_page);
                    _page.setLookLocalClass(EMPTY_STRING);
                    op_.setOrder(0);
                    opValue = new CustList<RendDynOperationNode>();
                    opValue.add(RendDynOperationNode.createExecOperationNode(op_, _page));
                    checkDuplicateEnumCase(_anaDoc, _page);
                    return;
                }
                opValue = RenderExpUtil.getAnalyzedOperations(value, 0, _anaDoc, _page);
                Argument a_ = opValue.last().getArgument();
                if (Argument.isNullValue(a_)) {
                    checkDuplicateCase(a_, _anaDoc, _page);
                    return;
                }
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(valueOffset);
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                        _page.getKeyWords().getKeyWordCase(),
                        value);
                Configuration.addError(un_, _anaDoc, _page);
                return;
            }
        }
        opValue = RenderExpUtil.getAnalyzedOperations(value, 0, _anaDoc, _page);
        RendDynOperationNode op_ = opValue.last();
        AnaClassArgumentMatching resCase_ = _anaDoc.getCurrentRoot().getResultClass();
        Argument arg_ = op_.getArgument();
        if (arg_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(valueOffset);
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                    _page.getKeyWords().getKeyWordCase(),
                    value);
            Configuration.addError(un_, _anaDoc, _page);
        } else {
            checkDuplicateCase(arg_, _anaDoc, _page);
            Mapping m_ = new Mapping();
            m_.setArg(resCase_);
            m_.setParam(resSwitch_);
            if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(valueOffset);
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseValue(),
                        _page.getKeyWords().getKeyWordCase(),
                        AnaApplyCoreMethodUtil.getString(arg_, _page),
                        StringList.join(resSwitch_.getNames(),AND_ERR));
                Configuration.addError(un_, _anaDoc, _page);
            }
        }
    }

    private void checkDuplicateCase(Argument _arg, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
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
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                _page.getKeyWords().getKeyWordCase(),
                                AnaApplyCoreMethodUtil.getString(_arg, _page),
                                _page.getKeyWords().getKeyWordSwitch());
                        Configuration.addError(un_, _anaDoc, _page);
                        break;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
    }
    private void checkDuplicateEnumCase(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
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
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                            _page.getKeyWords().getKeyWordCase(),
                            value.trim(),
                            _page.getKeyWords().getKeyWordSwitch());
                    Configuration.addError(un_, _anaDoc, _page);
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
