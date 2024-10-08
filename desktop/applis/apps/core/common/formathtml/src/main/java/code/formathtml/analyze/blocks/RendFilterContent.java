package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.EnumBlock;
import code.expressionlanguage.analyze.blocks.FilterContent;
import code.expressionlanguage.analyze.blocks.InnerTypeOrElement;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.DeclaringOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.core.StringUtil;

public final class RendFilterContent {

    private String importedClassName = "";
    private String variableName;
    private final String value;
    private final String condition;
    private final ResultExpression resultExpressionValue = new ResultExpression();
    private final ResultExpression resultExpressionCondition = new ResultExpression();

    private final int classNameOffset;
    private final String className;
    private final int variableOffset;
    private final int valueOffset;
    private final int conditionOffset;
    private final StrTypes offsetsEnum = new StrTypes();
    private final CustList<Struct> stdValues = new CustList<Struct>();
    private final CustList<ClassField> enumValues = new CustList<ClassField>();
    private String keyWord = "";
    private String keyWordContainer = "";
    RendFilterContent(OffsetStringInfo _className, OffsetStringInfo _variable, OffsetStringInfo _value, OffsetStringInfo _condition) {
        classNameOffset = _className.getOffset();
        className = _className.getInfo();
        variableOffset = _variable.getOffset();
        setVariableName(_variable.getInfo());
        value = _value.getInfo();
        valueOffset = _value.getOffset();
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String _k) {
        this.keyWord = _k;
    }

    public String getKeyWordContainer() {
        return keyWordContainer;
    }

    public void setKeyWordContainer(String _k) {
        this.keyWordContainer = _k;
    }
    void buildExpressionLanguage(AnaRendBlock _bl,AnalyzingDoc _anaDoc, AnalyzedPageEl _page, AnaClassArgumentMatching _resSwitch, boolean _instance) {
        String type_ = _resSwitch.getSingleNameOrEmpty();
        String variableName_ = getVariableName();
        if (className.isEmpty()) {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            AnaGeneType g_ = _page.getAnaClassBody(id_);
            if (g_ instanceof EnumBlock && FilterContent.allWordsOrEmpty(value)) {
                enumElements(_bl, _page, (EnumBlock) g_);
                analyzeCondition(_anaDoc, _page);
                return;
            }
            processNumValues(_bl, _anaDoc, _instance, _resSwitch, _page);
            analyzeCondition(_anaDoc, _page);
            return;
        }
        if (!_instance) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_bl.getOffset());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                    getKeyWord(),
                    value);
            AnalyzingDoc.addError(un_, _page);
        }
        _page.setSumOffset(classNameOffset);
        _page.zeroOffset();
        if (StringUtil.quickEq(className.trim(), _page.getKeyWords().getKeyWordVar())) {
            setImportedClassName(type_);
        } else {
            setImportedClassName(ResolvingTypes.resolveCorrectType(className, _page).getResult(_page));
        }
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName_, _page);
        if (!res_.isError()) {
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(getImportedClassName());
            lv_.setConstType(ConstType.FIX_VAR);
            lv_.setFinalVariable(true);
            _page.getInfosVars().put(variableName_, lv_);
        }
        analyzeCondition(_anaDoc, _page);
        if (variableName_.trim().isEmpty()) {
            return;
        }
        if (res_.isError()) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(_page.getCurrentFile());
            d_.setIndexFile(variableOffset);
            //variable name
            d_.setBuiltError(res_.getMessage());
            AnalyzingDoc.addError(d_, _page);
        }
    }

    private void analyzeCondition(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (!condition.trim().isEmpty()) {
            _page.setSumOffset(resultExpressionCondition.getSumOffset());
            _page.zeroOffset();
            RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page, resultExpressionCondition);
            AnaClassArgumentMatching resultClass_ = resultExpressionCondition.getRoot().getResultClass();
            if (!resultClass_.isBoolType(_page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(conditionOffset);
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        StringUtil.join(resultClass_.getNames(), AnaRendBlock.AND_ERR));
                AnalyzingDoc.addError(un_, _page);
            }
            resultClass_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        }
    }

    private void enumElements(AnaRendBlock _bl, AnalyzedPageEl _page, EnumBlock _e) {
        FilterContent.feedElts(_page, _e,value,offsetsEnum);
        for (IndexStrPart v: offsetsEnum.getValues()) {
            boolean added_ = false;
            if (StringUtil.quickEq(v.getPart(), _page.getKeyWords().getKeyWordNull())) {
                checkDuplicateListedValue(_bl,_page,NullStruct.NULL_VALUE);
                stdValues.add(NullStruct.NULL_VALUE);
                added_ = true;
            } else {
                for (InnerTypeOrElement f: _e.getEnumBlocks()) {
                    if (StringUtil.contains(f.getElements().getFieldName(), v.getPart())) {
                        ClassField pair_ = new ClassField(f.getImportedClassName(), v.getPart());
                        checkDuplicateListedEnum(_bl,_page, pair_, StringUtil.concat(pair_.getClassName(), ".", pair_.getFieldName()));
                        enumValues.add(pair_);
                        added_ = true;
                        break;
                    }
                }
            }
            if (!added_) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(valueOffset);
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                        getKeyWord(),
                        value);
                AnalyzingDoc.addError(un_, _page);
                break;
            }
        }
    }

    private void processNumValues(AnaRendBlock _bl, AnalyzingDoc _anaDoc, boolean _instance, AnaClassArgumentMatching _resSwitch, AnalyzedPageEl _page) {
        _page.setAcceptCommaInstr(true);
        _page.setEvaluatingCaseCondition(true);
        _page.setSumOffset(resultExpressionValue.getSumOffset());
        _page.zeroOffset();
        RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,resultExpressionValue);
        _page.setAcceptCommaInstr(false);
        _page.setEvaluatingCaseCondition(false);
        OperationNode r_ = resultExpressionValue.getRoot();
        if (r_ instanceof DeclaringOperation) {
            CustList<OperationNode> childrenNodes_ = ((DeclaringOperation) r_).getChildrenNodes();
            StrTypes children_ = ((DeclaringOperation) r_).getChildren();
            int len_ = childrenNodes_.size();
            for (int i = 0; i < len_; i++) {
                OperationNode ch_ = childrenNodes_.get(i);
                String value_ = StrTypes.value(children_, i);
                checkRetrieve(_bl, _instance,_resSwitch,ch_.getResultClass(),_page, ch_, value_);
            }
        } else {
            checkRetrieve(_bl, _instance,_resSwitch,r_.getResultClass(),_page, r_, value);
        }
    }

    private void checkRetrieve(AnaRendBlock _bl, boolean _instance, AnaClassArgumentMatching _resSwitch, AnaClassArgumentMatching _resCase, AnalyzedPageEl _page, OperationNode _ch, String _value) {
        ClassField classField_ = ElUtil.tryGetAccess(_ch);
        if (classField_ != null) {
            checkDuplicateListedEnum(_bl,_page, classField_, StringUtil.concat(classField_.getClassName(), ".", classField_.getFieldName()));
            checkInh(_page, _resSwitch, _instance, _resCase, StringUtil.concat(classField_.getClassName(), ".", classField_.getFieldName()));
            enumValues.add(classField_);
        } else {
            Struct argument_ = _ch.getArgument();
            if (argument_ != null) {
                checkDuplicateListedValue(_bl,_page,argument_);
                checkInh(_page, _resSwitch, _instance, _resCase, AnaApplyCoreMethodUtil.getString(argument_, _page));
                stdValues.add(argument_);
            } else {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(valueOffset);
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                        getKeyWord(),
                        _value);
                AnalyzingDoc.addError(un_, _page);
            }
        }
    }

    private void checkDuplicateListedEnum(AnaRendBlock _bl, AnalyzedPageEl _page, ClassField _classField, String _display) {
        AnaRendParentBlock par_ = _bl.getParent();
        AnaRendBlock first_ = par_.getFirstChild();
        while (first_ != _bl) {
            if (first_ instanceof WithRendFilterContent) {
                WithRendFilterContent c_ = (WithRendFilterContent) first_;
                for (ClassField p: listEnums(c_.getFilterContent())) {
                    if (_classField.eq(p)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_page.getCurrentFile());
                        un_.setIndexFile(getValueOffset()+ _bl.getOffset());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                getKeyWord(),
                                _display,
                                getKeyWordContainer());
                        AnalyzingDoc.addError(un_, _page);
                        return;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
        for (ClassField p: enumValues) {
            if (_classField.eq(p)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(getValueOffset()+ _bl.getOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                        getKeyWord(),
                        _display,
                        getKeyWordContainer());
                AnalyzingDoc.addError(un_, _page);
                return;
            }
        }
    }
    private void checkDuplicateListedValue(AnaRendBlock _bl, AnalyzedPageEl _page, Struct _value) {
        AnaRendParentBlock par_ = _bl.getParent();
        AnaRendBlock first_ = par_.getFirstChild();
        while (first_ != _bl) {
            if (first_ instanceof WithRendFilterContent) {
                WithRendFilterContent c_ = (WithRendFilterContent) first_;
                for (Struct p: listStd(c_.getFilterContent())) {
                    if (_value.sameReference(p)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_page.getCurrentFile());
                        un_.setIndexFile(getValueOffset()+ _bl.getOffset());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                getKeyWord(),
                                AnaApplyCoreMethodUtil.getString(_value, _page),
                                getKeyWordContainer());
                        AnalyzingDoc.addError(un_, _page);
                        return;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
        for (Struct p: stdValues) {
            if (_value.sameReference(p)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(getValueOffset()+ _bl.getOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                        getKeyWord(),
                        AnaApplyCoreMethodUtil.getString(_value, _page),
                        getKeyWordContainer());
                AnalyzingDoc.addError(un_, _page);
                return;
            }
        }
    }
    private void checkInh(AnalyzedPageEl _page, AnaClassArgumentMatching _resSwitch, boolean _instance, AnaClassArgumentMatching _resCase, String _string) {
        Mapping m_ = new Mapping();
        m_.setArg(_resCase);
        m_.setParam(_resSwitch);
        if (!_instance &&!AnaInherits.isCorrectOrNumbers(m_, _page)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(valueOffset);
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseValue(),
                    getKeyWord(),
                    _string,
                    StringUtil.join(_resSwitch.getNames(),AnaRendBlock.AND_ERR));
            AnalyzingDoc.addError(un_, _page);
        }
    }
    public void removeAllVars(AnalyzedPageEl _ip) {
        if (!getVariableName().isEmpty()) {
            _ip.getInfosVars().removeKey(getVariableName());
        }
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public void setImportedClassName(String _importedClassName) {
        this.importedClassName = _importedClassName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String _variableName) {
        this.variableName = _variableName;
    }

    public CustList<Struct> getStdValues() {
        return stdValues;
    }

    public CustList<ClassField> getEnumValues() {
        return enumValues;
    }

    public ResultExpression getResCondition() {
        return resultExpressionCondition;
    }

    public ResultExpression getResValue() {
        return resultExpressionValue;
    }

    public String getCondition() {
        return condition;
    }

    public int getConditionOffset() {
        return conditionOffset;
    }

    private static CustList<ClassField> listEnums(RendFilterContent _f) {
        if (_f.getResCondition().getRoot() != null) {
            return new CustList<ClassField>();
        }
        return _f.getEnumValues();
    }

    private static CustList<Struct> listStd(RendFilterContent _f) {
        if (_f.getResCondition().getRoot() != null) {
            return new CustList<Struct>();
        }
        return _f.getStdValues();
    }
    public String getValue() {
        return value;
    }

    public int getValueOffset() {
        return valueOffset;
    }
}
