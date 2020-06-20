package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecCaseCondition;
import code.expressionlanguage.exec.blocks.ExecEnumBlock;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.instr.*;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;

public final class CaseCondition extends SwitchPartBlock {

    private final String value;
    private Argument argument;
    private OperationNode root;

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
        MemberCallingsBlock f_ = _cont.getAnalyzing().getCurrentFct();
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
            CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(stCtx_));
            ExecOperationNode last_ = op_.last();
            root = _cont.getCoverage().getCurrentRoot();
            argument = last_.getArgument();
            ExecCaseCondition exec_ = new ExecCaseCondition(getOffset(),value,valueOffset, op_);
            page_.getBlockToWrite().appendChild(exec_);
            page_.getAnalysisAss().getMappingMembers().put(exec_,this);
            page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
            _cont.getCoverage().putBlockOperations(_cont, exec_,this);
            return;
        }
        _cont.getCoverage().putBlockOperationsSwitchs(_cont,par_,this);
        SwitchBlock sw_ = (SwitchBlock) par_;
        ClassArgumentMatching resSwitch_ = sw_.getResult();
        String type_ = resSwitch_.getSingleNameOrEmpty();
        ExecEnumBlock e_ = getEnumType(_cont, type_);
        if (e_ != null) {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            for (GeneField f: ContextUtil.getFieldBlocks(e_)) {
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
                root = op_;
                builtEnum = true;
                typeEnum = id_;
                CustList<ExecOperationNode> ops_ = new CustList<ExecOperationNode>();
                ops_.add(ExecOperationNode.createExecOperationNode(op_));
                checkDuplicateEnumCase(_cont);
                ExecCaseCondition exec_ = new ExecCaseCondition(getOffset(),value,valueOffset, ops_);
                page_.getBlockToWrite().appendChild(exec_);
                page_.getAnalysisAss().getMappingMembers().put(exec_,this);
                page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
                _cont.getCoverage().putBlockOperations(_cont, exec_,this);
                return;
            }
            CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(stCtx_));
            root = _cont.getCoverage().getCurrentRoot();
            ExecOperationNode last_ = op_.last();
            argument = last_.getArgument();
            processNullValue(_cont);
            ExecCaseCondition exec_ = new ExecCaseCondition(getOffset(),value,valueOffset, op_);
            page_.getBlockToWrite().appendChild(exec_);
            page_.getAnalysisAss().getMappingMembers().put(exec_,this);
            page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
            _cont.getCoverage().putBlockOperations(_cont, exec_,this);
            return;
        }
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(stCtx_));
        ExecOperationNode last_ = op_.last();
        root = _cont.getCoverage().getCurrentRoot();
        argument = last_.getArgument();
        processNumValues(_cont, resSwitch_, last_.getResultClass());
        ExecCaseCondition exec_ = new ExecCaseCondition(getOffset(),value,valueOffset, op_);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

    private ExecEnumBlock getEnumType(ContextEl _cont,String _type) {
        if (_type.isEmpty()) {
            return null;
        }
        String id_ = StringExpUtil.getIdFromAllTypes(_type);
        GeneType g_ = _cont.getClassBody(id_);
        if (g_ instanceof ExecEnumBlock) {
            return (ExecEnumBlock) g_;
        }
        return null;

    }
    private boolean match(GeneField _f) {
        if (!(_f instanceof ExecInnerTypeOrElement)) {
            return false;
        }
        ExecInnerTypeOrElement e_ = (ExecInnerTypeOrElement) _f;
        return StringList.quickEq(e_.getUniqueFieldName(), value.trim());
    }
    private void processNullValue(ContextEl _cont) {
        if (Argument.isNullValue(argument)) {
            checkDuplicateCase(_cont, argument);
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

    private void processNumValues(ContextEl _cont, ClassArgumentMatching _resSwitch, ClassArgumentMatching _resCase) {
        if (argument == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            //key word len
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedCaseVar(),
                    _cont.getKeyWords().getKeyWordCase(),
                    value);
            _cont.addError(un_);
        } else {
            checkDuplicateCase(_cont, argument);
            Mapping m_ = new Mapping();
            m_.setArg(_resCase);
            m_.setParam(_resSwitch);
            if (!AnaTemplates.isCorrectOrNumbers(m_,_cont)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(valueOffset);
                //key word len
                un_.buildError(_cont.getAnalysisMessages().getUnexpectedCaseValue(),
                        _cont.getKeyWords().getKeyWordCase(),
                        ExecCatOperation.getString(argument,_cont),
                        StringList.join(_resSwitch.getNames(),"&"));
                _cont.addError(un_);
            }
        }
    }

    private void checkDuplicateCase(ContextEl _cont, Argument _arg) {
        BracedBlock par_ = getParent();
        Block first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof CaseCondition) {
                CaseCondition c_ = (CaseCondition) first_;
                Argument a_ = c_.getArgument();
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

    public Argument getArgument() {
        return argument;
    }

    public OperationNode getRoot() {
        return root;
    }

    public String getTypeEnum() {
        return typeEnum;
    }

    public boolean isBuiltEnum() {
        return builtEnum;
    }
}
