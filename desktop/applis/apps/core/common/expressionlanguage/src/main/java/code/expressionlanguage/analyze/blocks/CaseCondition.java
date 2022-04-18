package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.instr.*;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class CaseCondition extends SwitchPartBlock {

    private final String value;
    private final String condition;
    private final ResultExpression res = new ResultExpression();

    private String importedType = EMPTY_STRING;

    private boolean instance;

    private AnaResultPartType partOffsets = new AnaResultPartType();

    private final StringList nameErrors = new StringList();

    private final String variableName;
    private final int variableOffset;

    private String typeEnum = EMPTY_STRING;

    private final int valueOffset;
    private final int conditionOffset;
    private EnumBlock enumBlock;
    private final StrTypes offsetsEnum = new StrTypes();
    private final CustList<Argument> stdValues = new CustList<Argument>();
    private final CustList<ClassField> enumValues = new CustList<ClassField>();

    private int indexTypeVarCase = -1;
    private final String declaringType;
    public CaseCondition(OffsetStringInfo _value, int _offset, String _declaringType, OffsetStringInfo _variable, OffsetStringInfo _condition) {
        super(_offset);
        declaringType = _declaringType;
        value = _value.getInfo();
        valueOffset = _value.getOffset();
        variableName = _variable.getInfo();
        variableOffset = _variable.getOffset();
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public int getConditionOffset() {
        return conditionOffset;
    }

    public String getCondition() {
        return condition;
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
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordCase(),
                    value,
                    _page.getKeyWords().getKeyWordSwitch());
            //key word len
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
            res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, value, Calculation.staticCalculation(stCtx_), _page));
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
        _page.setGlobalOffset(valueOffset);
        _page.zeroOffset();
        if (!variableName.isEmpty()) {
            if (!instance_) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(getFile());
                un_.setIndexFile(valueOffset);
                //key word len
                un_.buildError(_page.getAnalysisMessages().getCaseTypeVar());
                _page.addLocError(un_);
                addErrorBlock(un_.getBuiltError());
            }
            instance = true;
            partOffsets = ResolvingTypes.resolveCorrectType(declaringType, _page);
            importedType = partOffsets.getResult(_page);
            TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
            if (!res_.isError()) {
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(importedType);
                lv_.setRef(variableOffset);
                lv_.setConstType(ConstType.FIX_VAR);
                lv_.setFinalVariable(true);
                _page.getInfosVars().put(variableName, lv_);
            }
            if (!condition.isEmpty()) {
                _page.setGlobalOffset(conditionOffset);
                _page.zeroOffset();
                res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, condition, Calculation.staticCalculation(stCtx_), _page));
                AnaClassArgumentMatching resultClass_ = res.getRoot().getResultClass();
                if (!resultClass_.isBoolType(_page)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(getFile());
                    un_.setIndexFile(conditionOffset);
                    //key word len
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(resultClass_.getNames(), ExportCst.JOIN_TYPES));
                    _page.addLocError(un_);
                    addErrorBlock(un_.getBuiltError());
                }
                resultClass_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            }
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFile(getFile());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                _page.addLocError(d_);
                nameErrors.add(d_.getBuiltError());
            }
            return;
        }
        String id_ = StringExpUtil.getIdFromAllTypes(type_);
        AnaGeneType g_ = _page.getAnaGeneType(id_);
        if (g_ instanceof EnumBlock && allWordsOrEmpty(value)) {
            EnumBlock e_ = (EnumBlock) g_;
            enumBlock = e_;
            typeEnum = id_;
            int sum_ = 0;
            for (String s : StringUtil.splitChar(value, ',')) {
                boolean added_ = false;
                String trimPart_ = s.trim();
                int k_ = sum_ + StringExpUtil.getOffset(s);
                if (StringUtil.quickEq(trimPart_, _page.getKeyWords().getKeyWordNull())) {
                    offsetsEnum.addEntry(k_, trimPart_);
                    added_ = true;
                } else {
                    for (InnerTypeOrElement f : e_.getEnumBlocks()) {
                        if (StringUtil.contains(f.getFieldName(), trimPart_)) {
                            offsetsEnum.addEntry(k_, trimPart_);
                            added_ = true;
                            break;
                        }
                    }
                }
                if (!added_) {
                    offsetsEnum.addEntry(k_, trimPart_);
                }
                sum_ += s.length() + 1;
            }
            return;
        }
        _page.setAcceptCommaInstr(true);
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, value, Calculation.staticCalculation(stCtx_), _page));
        _page.setAcceptCommaInstr(false);
        String emp_ = _page.getCurrentEmptyPartErr();
        if (!emp_.isEmpty()) {
            addErrorBlock(emp_);
        }
    }

    public static boolean allWordsOrEmpty(String _value) {
        boolean allWords_ = true;
        for (String s: StringUtil.splitChar(_value,',')) {
            String trim_ = s.trim();
            if (!trim_.isEmpty()&&!StringExpUtil.isTypeLeafPart(trim_)) {
                allWords_ = false;
            }
        }
        return allWords_;
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

    public StrTypes getOffsetsEnum() {
        return offsetsEnum;
    }

    public CustList<Argument> getStdValues() {
        return stdValues;
    }

    public CustList<ClassField> getEnumValues() {
        return enumValues;
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public String getTypeEnum() {
        return typeEnum;
    }

    public AnaResultPartType getPartOffsets() {
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

    public StringList getNameErrors() {
        return nameErrors;
    }

    public int getIndexTypeVarCase() {
        return indexTypeVarCase;
    }

    public void setIndexTypeVarCase(int _indexTypeVarCase) {
        indexTypeVarCase = _indexTypeVarCase;
    }

    public boolean isCaseWhen() {
        return !condition.isEmpty();
    }
}
