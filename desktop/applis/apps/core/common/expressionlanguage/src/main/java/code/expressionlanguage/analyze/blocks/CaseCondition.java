package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.files.ParsedType;
import code.expressionlanguage.analyze.instr.*;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;

public final class CaseCondition extends SwitchPartBlock {

    private final String value;
    private Argument argument;
    private OperationNode root;

    private boolean builtEnum;
    private boolean emptyType;
    private boolean nullCase;

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
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        _page.setGlobalOffset(valueOffset);
        _page.setOffset(0);
        BracedBlock par_ = getParent();
        MethodAccessKind stCtx_ = f_.getStaticContext();
        if (!(par_ instanceof SwitchBlock)) {
            _page.setGlobalOffset(getOffset().getOffsetTrim());
            _page.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordCase(),
                    value,
                    _page.getKeyWords().getKeyWordSwitch());
            //key word len
            _page.addLocError(un_);
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
            root = ElUtil.getRootAnalyzedOperationsReadOnly(value, Calculation.staticCalculation(stCtx_), _page);
            return;
        }
        SwitchBlock sw_ = (SwitchBlock) par_;
        AnaClassArgumentMatching resSwitch_ = sw_.getResult();
        String type_ = resSwitch_.getSingleNameOrEmpty();
        if (!sw_.getInstanceTest().isEmpty()) {
            ParsedType p_ = new ParsedType();
            p_.parse(value);
            String declaringType_ = p_.getInstruction().toString();
            if (StringList.quickEq(declaringType_, _page.getKeyWords().getKeyWordNull())) {
                nullCase = true;
                return;
            }
            _page.setGlobalOffset(valueOffset);
            _page.setOffset(0);
            if (declaringType_.trim().isEmpty()) {
                emptyType = true;
            }
            importedType = ResolvingImportTypes.resolveCorrectType(declaringType_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            variableOffset = valueOffset + declaringType_.length();
            String info_ = value.substring(declaringType_.length());
            variableOffset += StringList.getFirstPrintableCharIndex(info_);
            variableName = info_.trim();
            TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(getFile().getFileName());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                _page.addLocError(d_);
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
            _page.getInfosVars().put(variableName, lv_);
            return;
        }
        EnumBlock e_ = getEnumType(type_, _page);
        if (e_ != null) {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            for (InfoBlock f: ContextUtil.getFieldBlocks(e_)) {
                if (!match(f)) {
                    continue;
                }
                _page.setLookLocalClass(id_);
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                Delimiters d_ = ElResolver.checkSyntax(value, CustList.FIRST_INDEX, _page);
                OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, value, d_, _page);
                OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _page);
                ElUtil.retrieveErrorsAnalyze(op_, _page);
                _page.setLookLocalClass(EMPTY_STRING);
                op_.setOrder(0);
                root = op_;
                builtEnum = true;
                fieldNameOffset = f.getFieldNameOffset();
                typeEnum = id_;
                return;
            }
            root = ElUtil.getRootAnalyzedOperationsReadOnly(value, Calculation.staticCalculation(stCtx_), _page);
            String emp_ = _page.getCurrentEmptyPartErr();
            if (!emp_.isEmpty()) {
                setReachableError(true);
                getErrorsBlock().add(emp_);
            }
            return;
        }
        root = ElUtil.getRootAnalyzedOperationsReadOnly(value, Calculation.staticCalculation(stCtx_), _page);
        String emp_ = _page.getCurrentEmptyPartErr();
        if (!emp_.isEmpty()) {
            setReachableError(true);
            getErrorsBlock().add(emp_);
        }
    }

    private EnumBlock getEnumType(String _type, AnalyzedPageEl _page) {
        if (_type.isEmpty()) {
            return null;
        }
        String id_ = StringExpUtil.getIdFromAllTypes(_type);
        AnaGeneType g_ = _page.getAnaGeneType(id_);
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

    public void setArgument(Argument argument) {
        this.argument = argument;
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

    public void setBuiltEnum(boolean builtEnum) {
        this.builtEnum = builtEnum;
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

    public boolean isNullCase() {
        return nullCase;
    }
}
