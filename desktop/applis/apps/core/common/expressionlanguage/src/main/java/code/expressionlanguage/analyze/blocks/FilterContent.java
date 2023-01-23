package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class FilterContent {

    private final String value;
    private final String condition;
    private final ResultExpression res = new ResultExpression();

    private String importedType = "";

    private boolean instance;

    private AnaResultPartType partOffsets = new AnaResultPartType();

    private final StringList nameErrors = new StringList();

    private final String variableName;
    private final int variableOffset;

    private String typeEnum = "";

    private final int valueOffset;
    private final int conditionOffset;
    private EnumBlock enumBlock;
    private final StrTypes offsetsEnum = new StrTypes();
    private final CustList<Argument> stdValues = new CustList<Argument>();
    private final CustList<ClassField> enumValues = new CustList<ClassField>();

    private final String declaringType;
    public FilterContent(OffsetStringInfo _value, String _declaringType, OffsetStringInfo _variable, OffsetStringInfo _condition) {
        declaringType = _declaringType;
        value = _value.getInfo();
        valueOffset = _value.getOffset();
        variableName = _variable.getInfo();
        variableOffset = _variable.getOffset();
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    public String getDeclaringType() {
        return declaringType;
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

    public void buildExpressionLanguageReadOnly(AbsBk _bl,AnalyzedPageEl _page, boolean _instance, String _type) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        MethodAccessKind stCtx_ = f_.getStaticContext();
        _page.setSumOffset(valueOffset);
        _page.zeroOffset();
        if (!variableName.isEmpty()) {
            instanceCase(_bl,_page, stCtx_, _instance);
            return;
        }
        String id_ = StringExpUtil.getIdFromAllTypes(_type);
        AnaGeneType g_ = _page.getAnaGeneType(id_);
        if (g_ instanceof EnumBlock && allWordsOrEmpty(value)) {
            EnumBlock e_ = (EnumBlock) g_;
            enumBlock = e_;
            typeEnum = id_;
            feedElts(_page, e_, value, offsetsEnum);
            return;
        }
        _page.setAcceptCommaInstr(true);
        _page.setSumOffset(res.getSumOffset());
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, Calculation.staticCalculation(stCtx_), _page));
        _page.setAcceptCommaInstr(false);
        String emp_ = _page.getCurrentEmptyPartErr();
        if (!emp_.isEmpty()) {
            _bl.addErrorBlock(emp_);
        }
    }

    private void instanceCase(AbsBk _bl,AnalyzedPageEl _page, MethodAccessKind _stCtx, boolean _inst) {
        if (!_inst) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_bl.getFile());
            un_.setIndexFile(valueOffset);
            //key word len
            un_.buildError(_page.getAnalysisMessages().getCaseTypeVar());
            _page.addLocError(un_);
            _bl.addErrorBlock(un_.getBuiltError());
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
            _page.setSumOffset(res.getSumOffset());
            _page.zeroOffset();
            res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, Calculation.staticCalculation(_stCtx), _page));
            AnaClassArgumentMatching resultClass_ = res.getRoot().getResultClass();
            if (!resultClass_.isBoolType(_page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_bl.getFile());
                un_.setIndexFile(conditionOffset);
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        StringUtil.join(resultClass_.getNames(), ExportCst.JOIN_TYPES));
                _page.addLocError(un_);
                _bl.addErrorBlock(un_.getBuiltError());
            }
            resultClass_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        }
        if (res_.isError()) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(_bl.getFile());
            d_.setIndexFile(variableOffset);
            //variable name
            d_.setBuiltError(res_.getMessage());
            _page.addLocError(d_);
            nameErrors.add(d_.getBuiltError());
        }
    }

    public static void feedElts(AnalyzedPageEl _page, EnumBlock _e, String _value, StrTypes _offsetsEnum) {
        int sum_ = 0;
        for (String s : StringUtil.splitChar(_value, ',')) {
            boolean added_ = false;
            String trimPart_ = s.trim();
            int k_ = sum_ + StringExpUtil.getOffset(s);
            if (StringUtil.quickEq(trimPart_, _page.getKeyWords().getKeyWordNull())) {
                _offsetsEnum.addEntry(k_, trimPart_);
                added_ = true;
            } else {
                for (InnerTypeOrElement f : _e.getEnumBlocks()) {
                    if (StringUtil.contains(f.getElements().getFieldName(), trimPart_)) {
                        _offsetsEnum.addEntry(k_, trimPart_);
                        added_ = true;
                        break;
                    }
                }
            }
            if (!added_) {
                _offsetsEnum.addEntry(k_, trimPart_);
            }
            sum_ += s.length() + 1;
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

    public void removeAllVars(AnalyzedPageEl _ip) {
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

    public boolean isCaseWhen() {
        return !condition.isEmpty();
    }
}