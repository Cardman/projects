package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.ParsedType;
import code.expressionlanguage.analyze.instr.*;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class CaseCondition extends SwitchPartBlock {

    private final String value;
    private Argument argument;
    private final ResultExpression res = new ResultExpression();

    private boolean builtEnum;
    private boolean nullCase;
    private boolean nullCaseEnum;

    private String importedType = EMPTY_STRING;
    private String impType = EMPTY_STRING;

    private boolean instance;

    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    private final StringList nameErrors = new StringList();

    private String variableName = EMPTY_STRING;
    private int variableOffset;

    private String typeEnum = EMPTY_STRING;

    private final int valueOffset;
    private int fieldNameOffset=-1;
    private EnumBlock enumBlock;

    public CaseCondition(OffsetStringInfo _value, int _offset) {
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
        _page.zeroOffset();
        BracedBlock par_ = getParent();
        MethodAccessKind stCtx_ = f_.getStaticContext();
        if (!(par_ instanceof SwitchBlock)&&!(par_ instanceof SwitchMethodBlock)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordCase(),
                    value,
                    _page.getKeyWords().getKeyWordSwitch());
            //key word len
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
            res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, value, Calculation.staticCalculation(stCtx_), _page));
            impType = res.getRoot().getResultClass().getSingleNameOrEmpty();
            return;
        }
        String type_;
        boolean instance_;
        if (par_ instanceof SwitchBlock) {
            SwitchBlock sw_ = (SwitchBlock) par_;
            setSwitchParent(sw_);
            AnaClassArgumentMatching resSwitch_ = sw_.getResult();
            type_ = resSwitch_.getSingleNameOrEmpty();
            instance_ = sw_.isInstance();
        } else {
            SwitchMethodBlock sw_ = (SwitchMethodBlock) par_;
            setSwitchMethod(sw_);
            AnaClassArgumentMatching resSwitch_ = sw_.getResult();
            type_ = resSwitch_.getSingleNameOrEmpty();
            instance_ = sw_.isInstance();
        }
        EnumBlock e_ = getEnumType(type_, _page);
        if (e_ != null) {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            for (InnerTypeOrElement f: e_.getEnumBlocks()) {
                if (!StringUtil.contains(f.getFieldName(), value.trim())) {
                    continue;
                }
                enumBlock = e_;
                _page.setLookLocalClass(id_);
                _page.setAccessStaticContext(MethodAccessKind.STATIC);
                Delimiters d_ = ElResolver.checkSyntax(value, IndexConstants.FIRST_INDEX, _page);
                OperationsSequence opTwo_ = ElResolver.getOperationsSequence(IndexConstants.FIRST_INDEX, value, d_, _page);
                OperationNode op_ = OperationNode.createOperationNode(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, null, opTwo_, _page);
                ElUtil.retrieveErrorsAnalyze(op_, _page);
                _page.setLookLocalClass(EMPTY_STRING);
                op_.setOrder(0);
                res.setRoot(op_);
                impType = res.getRoot().getResultClass().getSingleNameOrEmpty();
                builtEnum = true;
                fieldNameOffset = f.getFieldNameOffset();
                typeEnum = id_;
                return;
            }
            res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, value, Calculation.staticCalculation(stCtx_), _page));
            impType = res.getRoot().getResultClass().getSingleNameOrEmpty();
            String emp_ = _page.getCurrentEmptyPartErr();
            if (!emp_.isEmpty()) {
                addErrorBlock(emp_);
            }
            return;
        }
        if (instance_) {
            ParsedType p_ = new ParsedType();
            p_.parse(value);
            String declaringType_ = p_.getInstruction().toString();
            if (StringUtil.quickEq(declaringType_, _page.getKeyWords().getKeyWordNull())) {
                nullCase = true;
                instance = true;
                return;
            }
            _page.setGlobalOffset(valueOffset);
            _page.zeroOffset();
            String keyWordNew_ = _page.getKeyWords().getKeyWordNew();
            String varName_ = "";
            if (p_.isOk(new CustList<String>(keyWordNew_))) {
                varName_ = value.substring(declaringType_.length());
            }
            String trimVar_ = varName_.trim();
            if (StringExpUtil.isTypeLeafPart(trimVar_)) {
                instance = true;
                importedType = ResolvingTypes.resolveCorrectType(declaringType_, _page);
                impType = importedType;
                partOffsets.addAllElts(_page.getCurrentParts());
                variableOffset = valueOffset + declaringType_.length();
                variableOffset += StringUtil.getFirstPrintableCharIndex(varName_);
                variableName = trimVar_;
                TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
                if (res_.isError()) {
                    FoundErrorInterpret d_ = new FoundErrorInterpret();
                    d_.setFileName(getFile().getFileName());
                    d_.setIndexFile(variableOffset);
                    //variable name
                    d_.setBuiltError(res_.getMessage());
                    _page.addLocError(d_);
                    nameErrors.add(d_.getBuiltError());
                    return;
                }
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(importedType);
                lv_.setRef(variableOffset);
                lv_.setConstType(ConstType.FIX_VAR);
                _page.getInfosVars().put(variableName, lv_);
                return;
            }
        }
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, value, Calculation.staticCalculation(stCtx_), _page));
        impType = res.getRoot().getResultClass().getSingleNameOrEmpty();
        String emp_ = _page.getCurrentEmptyPartErr();
        if (!emp_.isEmpty()) {
            addErrorBlock(emp_);
        }
    }

    private static EnumBlock getEnumType(String _type, AnalyzedPageEl _page) {
        String id_ = StringExpUtil.getIdFromAllTypes(_type);
        AnaGeneType g_ = _page.getAnaGeneType(id_);
        if (g_ instanceof EnumBlock) {
            return (EnumBlock) g_;
        }
        return null;

    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        if (!variableName.isEmpty()) {
            _ip.getInfosVars().removeKey(variableName);
        }
    }

    public ResultExpression getRes() {
        return res;
    }

    public EnumBlock getEnumBlock() {
        return enumBlock;
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _argument) {
        this.argument = _argument;
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public String getTypeEnum() {
        return typeEnum;
    }

    public boolean isBuiltEnum() {
        return builtEnum;
    }

    public void setBuiltEnum(boolean _builtEnum) {
        this.builtEnum = _builtEnum;
    }

    public boolean isNullCaseEnum() {
        return nullCaseEnum;
    }

    public void setNullCaseEnum(boolean _nullCaseEnum) {
        this.nullCaseEnum = _nullCaseEnum;
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

    public boolean isInstance() {
        return instance;
    }

    public String getImpType() {
        return impType;
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
