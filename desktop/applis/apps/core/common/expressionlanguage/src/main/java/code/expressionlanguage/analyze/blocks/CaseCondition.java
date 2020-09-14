package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.files.ParsedType;
import code.expressionlanguage.instr.*;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
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
    private boolean emptyType;

    private String importedType = EMPTY_STRING;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    private final StringList nameErrors = new StringList();

    private String variableName = EMPTY_STRING;
    private int variableOffset;

    private String typeEnum = EMPTY_STRING;

    private int valueOffset;
    private int fieldNameOffset=-1;

    private StringList emptErrs = new StringList();

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
            un_.buildError(_cont.getAnalyzing().getAnalysisMessages().getUnexpectedCaseDef(),
                    _cont.getAnalyzing().getKeyWords().getKeyWordCase(),
                    value,
                    _cont.getAnalyzing().getKeyWords().getKeyWordSwitch());
            //key word len
            _cont.getAnalyzing().addLocError(un_);
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
            CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(stCtx_));
            ExecOperationNode last_ = op_.last();
            root = page_.getCurrentRoot();
            argument = last_.getArgument();
            ExecNullCaseCondition exec_ = new ExecNullCaseCondition(getOffset(),valueOffset);
            exec_.setFile(page_.getBlockToWrite().getFile());
            page_.getBlockToWrite().appendChild(exec_);
            page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
            page_.getCoverage().putBlockOperations(exec_,this);
            return;
        }
        page_.getCoverage().putBlockOperationsSwitchs(par_,this);
        SwitchBlock sw_ = (SwitchBlock) par_;
        ClassArgumentMatching resSwitch_ = sw_.getResult();
        String type_ = resSwitch_.getSingleNameOrEmpty();
        if (!sw_.getInstanceTest().isEmpty()) {
            page_.setGlobalOffset(variableOffset);
            page_.setOffset(0);
            if (importedType.isEmpty()) {
                ExecNullInstanceCaseCondition exec_ = new ExecNullInstanceCaseCondition(getOffset(),valueOffset);
                exec_.setFile(page_.getBlockToWrite().getFile());
                page_.getBlockToWrite().appendChild(exec_);
                page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
                page_.getCoverage().putBlockOperations(exec_,this);
                return;
            }
            ExecInstanceCaseCondition exec_ = new ExecInstanceCaseCondition(getOffset(),variableName, importedType,valueOffset);
            exec_.setFile(page_.getBlockToWrite().getFile());
            page_.getBlockToWrite().appendChild(exec_);
            page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
            page_.getCoverage().putBlockOperations(exec_,this);
            TokenErrorMessage res_ = ManageTokens.partVar(page_).checkTokenVar(variableName, page_);
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(getFile().getFileName());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                _cont.getAnalyzing().addLocError(d_);
                nameErrors.add(d_.getBuiltError());
                if (!emptyType&&variableName.trim().isEmpty()) {
                    setReachableError(true);
                    getErrorsBlock().add(d_.getBuiltError());
                }
                return;
            }
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(importedType);
            lv_.setRef(variableOffset);
            lv_.setConstType(ConstType.FIX_VAR);
            _cont.getAnalyzing().getInfosVars().put(variableName, lv_);
            return;
        }
        EnumBlock e_ = getEnumType(_cont, type_);
        if (e_ != null) {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            for (InfoBlock f: ContextUtil.getFieldBlocks(e_)) {
                if (!match(f)) {
                    continue;
                }
                page_.setLookLocalClass(id_);
                page_.setAccessStaticContext(MethodAccessKind.STATIC);
                Delimiters d_ = ElResolver.checkSyntax(value, _cont, CustList.FIRST_INDEX);
                OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, value, _cont, d_);
                OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _cont);
                ElUtil.retrieveErrorsAnalyze(_cont,op_);
                page_.setLookLocalClass(EMPTY_STRING);
                op_.setOrder(0);
                root = op_;
                builtEnum = true;
                fieldNameOffset = f.getFieldNameOffset();
                typeEnum = id_;
                CustList<ExecOperationNode> ops_ = new CustList<ExecOperationNode>();
                ops_.add(ExecOperationNode.createExecOperationNode(op_,_cont));
                checkDuplicateEnumCase(_cont);
                ExecEnumCaseCondition exec_ = new ExecEnumCaseCondition(getOffset(),value,valueOffset);
                exec_.setFile(page_.getBlockToWrite().getFile());
                page_.getBlockToWrite().appendChild(exec_);
                page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
                page_.getCoverage().putBlockOperations(exec_,this);
                return;
            }
            CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(stCtx_));
            String emp_ = page_.getCurrentEmptyPartErr();
            if (!emp_.isEmpty()) {
                setReachableError(true);
                getErrorsBlock().add(emp_);
            }
            root = page_.getCurrentRoot();
            ExecOperationNode last_ = op_.last();
            argument = last_.getArgument();
            if (!Argument.isNullValue(argument)) {
                builtEnum = true;
            }
            processNullValue(_cont);
            ExecNullCaseCondition exec_ = new ExecNullCaseCondition(getOffset(),valueOffset);
            exec_.setFile(page_.getBlockToWrite().getFile());
            page_.getBlockToWrite().appendChild(exec_);
            page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
            page_.getCoverage().putBlockOperations(exec_,this);
            return;
        }
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(stCtx_));
        String emp_ = page_.getCurrentEmptyPartErr();
        if (!emp_.isEmpty()) {
            setReachableError(true);
            getErrorsBlock().add(emp_);
        }
        ExecOperationNode last_ = op_.last();
        root = page_.getCurrentRoot();
        argument = last_.getArgument();
        processNumValues(_cont, resSwitch_, last_.getResultClass());
        if (argument == null) {
            ExecBracedBlock exec_ = new ExecUnclassedBracedBlock(getOffset());
            exec_.setFile(page_.getBlockToWrite().getFile());
            page_.getBlockToWrite().appendChild(exec_);
            page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
            page_.getCoverage().putBlockOperations(exec_,this);
            return;
        }
        ExecBracedBlock exec_;
        if (!argument.isNull()) {
            exec_ = new ExecStdCaseCondition(getOffset(),valueOffset, argument);
        } else {
            exec_ = new ExecNullCaseCondition(getOffset(),valueOffset);
        }
        exec_.setFile(page_.getBlockToWrite().getFile());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        page_.getCoverage().putBlockOperations(exec_,this);
    }

    private EnumBlock getEnumType(ContextEl _cont,String _type) {
        if (_type.isEmpty()) {
            return null;
        }
        String id_ = StringExpUtil.getIdFromAllTypes(_type);
        AnaGeneType g_ = _cont.getAnalyzing().getAnaGeneType(id_);
        if (g_ instanceof EnumBlock) {
            return (EnumBlock) g_;
        }
        return null;

    }
    private boolean match(InfoBlock _f) {
        if (!(_f instanceof InnerTypeOrElement)) {
            return false;
        }
        InnerTypeOrElement e_ = (InnerTypeOrElement) _f;
        return StringList.contains(e_.getFieldName(), value.trim());
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
        un_.buildError(_cont.getAnalyzing().getAnalysisMessages().getUnexpectedCaseVar(),
                _cont.getAnalyzing().getKeyWords().getKeyWordCase(),
                value);
        _cont.getAnalyzing().addLocError(un_);
        emptErrs.add(un_.getBuiltError());
    }

    private void processNumValues(ContextEl _cont, ClassArgumentMatching _resSwitch, ClassArgumentMatching _resCase) {
        if (argument == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            //key word len
            un_.buildError(_cont.getAnalyzing().getAnalysisMessages().getUnexpectedCaseVar(),
                    _cont.getAnalyzing().getKeyWords().getKeyWordCase(),
                    value);
            _cont.getAnalyzing().addLocError(un_);
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
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
                un_.buildError(_cont.getAnalyzing().getAnalysisMessages().getUnexpectedCaseValue(),
                        _cont.getAnalyzing().getKeyWords().getKeyWordCase(),
                        AnaApplyCoreMethodUtil.getString(argument,_cont),
                        StringList.join(_resSwitch.getNames(),"&"));
                _cont.getAnalyzing().addLocError(un_);
                setReachableError(true);
                getErrorsBlock().add(un_.getBuiltError());
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
                        un_.buildError(_cont.getAnalyzing().getAnalysisMessages().getUnexpectedCaseDup(),
                                _cont.getAnalyzing().getKeyWords().getKeyWordCase(),
                                AnaApplyCoreMethodUtil.getString(_arg,_cont),
                                _cont.getAnalyzing().getKeyWords().getKeyWordSwitch());
                        _cont.getAnalyzing().addLocError(un_);
                        setReachableError(true);
                        getErrorsBlock().add(un_.getBuiltError());
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
                    un_.buildError(_cont.getAnalyzing().getAnalysisMessages().getUnexpectedCaseDup(),
                            _cont.getAnalyzing().getKeyWords().getKeyWordCase(),
                            value.trim(),
                            _cont.getAnalyzing().getKeyWords().getKeyWordSwitch());
                    _cont.getAnalyzing().addLocError(un_);
                    setReachableError(true);
                    getErrorsBlock().add(un_.getBuiltError());
                    break;
                }
                
            }
            first_ = first_.getNextSibling();
        }
    }

    @Override
    public void reach(ContextEl _an, AnalyzingEl _anEl) {
        BracedBlock par_ = getParent();
        if (!(par_ instanceof SwitchBlock)) {
            super.reach(_an, _anEl);
            return;
        }
        SwitchBlock s_ = (SwitchBlock) par_;
        if (s_.getInstanceTest().isEmpty()) {
            super.reach(_an, _anEl);
            return;
        }
        ParsedType p_ = new ParsedType();
        p_.parse(value);
        String declaringType_ = p_.getInstruction().toString();
        if (StringList.quickEq(declaringType_, _an.getAnalyzing().getKeyWords().getKeyWordNull())) {
            StringList classes_ = new StringList();
            Block b_ = getPreviousSibling();
            while (b_ != null) {
                if (b_ instanceof CaseCondition) {
                    classes_.add(((CaseCondition)b_).importedType);
                }
                b_ = b_.getPreviousSibling();
            }
            if (!StringList.contains(classes_,"")) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
            return;
        }
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        if (declaringType_.trim().isEmpty()) {
            emptyType = true;
        }
        importedType = ResolvingImportTypes.resolveCorrectType(_an,declaringType_);
        partOffsets.addAllElts(_an.getAnalyzing().getCurrentParts());
        variableOffset = valueOffset + declaringType_.length();
        String info_ = value.substring(declaringType_.length());
        variableOffset += StringList.getFirstPrintableCharIndex(info_);
        variableName = info_.trim();
        StringList classes_ = new StringList();
        Block b_ = getPreviousSibling();
        while (b_ != null) {
            if (b_ instanceof CaseCondition) {
                classes_.add(((CaseCondition)b_).importedType);
            }
            b_ = b_.getPreviousSibling();
        }
        _anEl.setArgMapping(importedType);
        boolean reachCatch_ = true;
        for (String c: classes_) {
            _anEl.setParamMapping(c);
            if (_anEl.isCorrectMapping(_an)) {
                reachCatch_ = false;
                break;
            }
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        if (!variableName.isEmpty()) {
            _ip.getInfosVars().removeKey(variableName);
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

    public StringList getEmptErrs() {
        return emptErrs;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getVariableOffset() {
        return variableOffset;
    }

    public String getImportedType() {
        return importedType;
    }

    public StringList getNameErrors() {
        return nameErrors;
    }

    public int getFieldNameOffset() {
        return fieldNameOffset;
    }
}
