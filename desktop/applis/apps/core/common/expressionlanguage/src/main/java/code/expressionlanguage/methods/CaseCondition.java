package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.*;
import code.expressionlanguage.methods.util.AbstractCoverageResult;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.exec.ExecCatOperation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;

public final class CaseCondition extends SwitchPartBlock {

    private final String value;
    private CustList<ExecOperationNode> opValue;

    private boolean builtEnum;

    private String typeEnum = EMPTY_STRING;

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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        BracedBlock par_ = getParent();
        MethodAccessKind stCtx_ = f_.getStaticContext();
        if (!(par_ instanceof SwitchBlock)) {
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedCaseDef(),
                    _cont.getKeyWords().getKeyWordCase(),
                    value,
                    _cont.getKeyWords().getKeyWordSwitch());
            //key word len
            _cont.addError(un_);
            opValue = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(stCtx_));
            return;
        }
        _cont.getCoverage().putBlockOperationsSwitchs(_cont,par_,this);
        SwitchBlock sw_ = (SwitchBlock) par_;
        ClassArgumentMatching resSwitch_ = sw_.getOpValue().last().getResultClass();
        String type_ = resSwitch_.getSingleNameOrEmpty();
        EnumBlock e_ = getEnumType(_cont, type_);
        if (e_ != null) {
            String id_ = Templates.getIdFromAllTypes(type_);
            for (GeneField f: ContextEl.getFieldBlocks(e_)) {
                if (!match(f)) {
                    continue;
                }
                page_.setLookLocalClass(id_);
                page_.setAccessStaticContext(MethodAccessKind.STATIC);
                Delimiters d_ = ElResolver.checkSyntax(value, _cont, CustList.FIRST_INDEX);
                OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, value, _cont, d_);
                OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _cont);
                op_.analyze(_cont);
                page_.setLookLocalClass(EMPTY_STRING);
                op_.setOrder(0);
                builtEnum = true;
                typeEnum = id_;
                opValue = new CustList<ExecOperationNode>();
                opValue.add(ExecOperationNode.createExecOperationNode(op_));
                checkDuplicateEnumCase(_cont);
                return;
            }
            opValue = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(stCtx_));
            processNullValue(_cont);
            return;
        }
        opValue = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(stCtx_));
        processNumValues(_cont, resSwitch_);
    }

    private EnumBlock getEnumType(ContextEl _cont,String _type) {
        if (_type.isEmpty()) {
            return null;
        }
        String id_ = Templates.getIdFromAllTypes(_type);
        GeneType g_ = _cont.getClassBody(id_);
        if (g_ instanceof EnumBlock) {
            return (EnumBlock) g_;
        }
        return null;

    }
    private boolean match(GeneField _f) {
        if (!(_f instanceof InnerTypeOrElement)) {
            return false;
        }
        InnerTypeOrElement e_ = (InnerTypeOrElement) _f;
        return StringList.quickEq(e_.getUniqueFieldName(), value.trim());
    }
    private void processNullValue(ContextEl _cont) {
        Argument a_ = opValue.last().getArgument();
        if (Argument.isNullValue(a_)) {
            checkDuplicateCase(_cont, a_);
            return;
        }
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFileName(getFile().getFileName());
        un_.setIndexFile(valueOffset);
        //key word len
        un_.buildError(_cont.getAnalysisMessages().getUnexpectedCaseVar(),
                _cont.getKeyWords().getKeyWordCase(),
                value);
        _cont.addError(un_);
    }

    private void processNumValues(ContextEl _cont, ClassArgumentMatching _resSwitch) {
        ExecOperationNode op_ = opValue.last();
        ClassArgumentMatching resCase_ = op_.getResultClass();
        Argument arg_ = op_.getArgument();
        if (arg_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            //key word len
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedCaseVar(),
                    _cont.getKeyWords().getKeyWordCase(),
                    value);
            _cont.addError(un_);
        } else {
            checkDuplicateCase(_cont, arg_);
            Mapping m_ = new Mapping();
            m_.setArg(resCase_);
            m_.setParam(_resSwitch);
            if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(valueOffset);
                //key word len
                un_.buildError(_cont.getAnalysisMessages().getUnexpectedCaseValue(),
                        _cont.getKeyWords().getKeyWordCase(),
                        ExecCatOperation.getString(arg_,_cont),
                        StringList.join(_resSwitch.getNames(),"&"));
                _cont.addError(un_);
            }
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
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(getFile().getFileName());
                        un_.setIndexFile(getValueOffset()+ getOffset().getOffsetTrim());
                        //key word len
                        un_.buildError(_cont.getAnalysisMessages().getUnexpectedCaseDup(),
                                _cont.getKeyWords().getKeyWordCase(),
                                ExecCatOperation.getString(_arg,_cont),
                                _cont.getKeyWords().getKeyWordSwitch());
                        _cont.addError(un_);
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
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(getValueOffset()+ getOffset().getOffsetTrim());
                    //key word len
                    un_.buildError(_cont.getAnalysisMessages().getUnexpectedCaseDup(),
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
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        rw_.setBlock(getFirstChild());
        ip_.getLastStack().setCurrentVisitedBlock(this);
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
        if (builtEnum) {
            GeneType type_ = _cont.getClassBody(typeEnum);
            int delta_ = -1;
            for (Block b: Classes.getDirectChildren((Block) type_)) {
                if (!(b instanceof InnerTypeOrElement)) {
                    continue;
                }
                InnerTypeOrElement f_ = (InnerTypeOrElement)b;
                if (!StringList.quickEq(f_.getUniqueFieldName(),getValue())) {
                    continue;
                }
                delta_ = f_.getFieldNameOffset();
            }
            String file_ = ((RootBlock) type_).getFile().getRenderFileName();
            String currentFileName_ = _cont.getCoverage().getCurrentFileName();
            String rel_ = ElUtil.relativize(currentFileName_,file_+"#m"+delta_);
            tag_ = "<a title=\""+ElUtil.transform(typeEnum +"."+ getValue())+"\" href=\""+rel_+"\">";
            _parts.add(new PartOffset(tag_,off_));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_,off_+getValue().length()));
        } else {
            int offsetEndBlock_ = off_ + getValue().length();
            ElUtil.buildCoverageReport(_cont,off_,this,getOpValue(),offsetEndBlock_,_parts);
        }
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ getValue().length()));
    }
}
