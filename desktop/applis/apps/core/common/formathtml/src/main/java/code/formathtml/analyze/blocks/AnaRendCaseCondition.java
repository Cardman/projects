package code.formathtml.analyze.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.*;
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
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.core.StringUtil;

public final class AnaRendCaseCondition extends AnaRendSwitchPartCondition {

    private final String value;
    private OperationNode root;
    private final ResultExpression resultExpression = new ResultExpression();

    private final int classNameOffset;
    private final String className;
    private final int variableOffset;
    private final int valueOffset;
    private final StrTypes offsetsEnum = new StrTypes();
    private final CustList<Argument> stdValues = new CustList<Argument>();
    private final CustList<ClassField> enumValues = new CustList<ClassField>();


    AnaRendCaseCondition(OffsetStringInfo _className, OffsetStringInfo _variable, OffsetStringInfo _value, int _offset) {
        super(_offset);
        classNameOffset = _className.getOffset();
        className = _className.getInfo();
        variableOffset = _variable.getOffset();
        setVariableName(_variable.getInfo());
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(valueOffset);
        _page.zeroOffset();
        AnaRendParentBlock par_ = getParent();
        if (!(par_ instanceof AnaRendSwitchBlock)) {
            _page.setGlobalOffset(getOffset());
            _page.zeroOffset();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordCase(),
                    value,
                    _page.getKeyWords().getKeyWordSwitch());
            AnalyzingDoc.addError(un_, _page);
            root = RenderAnalysis.getRootAnalyzedOperations(value, 0, _anaDoc, _page,resultExpression);
            return;
        }
        AnaRendSwitchBlock sw_ = (AnaRendSwitchBlock) par_;
        AnaClassArgumentMatching resSwitch_ = sw_.getResult();
        String type_ = resSwitch_.getSingleNameOrEmpty();
        String variableName_ = getVariableName();
        boolean instance_ = sw_.isInstance();
        if (!variableName_.isEmpty()) {
            if (!instance_) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(getOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                        _page.getKeyWords().getKeyWordCase(),
                        value);
                AnalyzingDoc.addError(un_, _page);
            }
            _page.setGlobalOffset(classNameOffset);
            _page.zeroOffset();
            setImportedClassName(ResolvingTypes.resolveCorrectType(className, _page).getResult(_page));
            TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName_, _page);
            if (!res_.isError()) {
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(getImportedClassName());
                lv_.setConstType(ConstType.FIX_VAR);
                _page.getInfosVars().put(variableName_, lv_);
            }
            if (!value.trim().isEmpty()) {
                _page.setGlobalOffset(valueOffset);
                _page.zeroOffset();
                root = RenderAnalysis.getRootAnalyzedOperations(value, 0, _anaDoc, _page,resultExpression);
                AnaClassArgumentMatching resultClass_ = root.getResultClass();
                if (!resultClass_.isBoolType(_page)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(valueOffset);
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(resultClass_.getNames(),AND_ERR));
                    AnalyzingDoc.addError(un_, _page);
                }
                resultClass_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            }
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFile(_page.getCurrentFile());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                AnalyzingDoc.addError(d_, _page);
            }
            return;
        }
        String id_ = StringExpUtil.getIdFromAllTypes(type_);
        AnaGeneType g_ = _page.getAnaClassBody(id_);
        if (g_ instanceof EnumBlock && CaseCondition.allWordsOrEmpty(value)) {
            int sum_ = 0;
            EnumBlock e_ = (EnumBlock)g_;
            for (String s: StringUtil.splitChar(value,',')) {
                boolean added_ = false;
                String trimPart_ = s.trim();
                int k_ = sum_ + StringExpUtil.getOffset(s);
                if (StringUtil.quickEq(trimPart_,_page.getKeyWords().getKeyWordNull())) {
                    offsetsEnum.addEntry(k_, trimPart_);
                    added_ = true;
                } else {
                    for (InnerTypeOrElement f: e_.getEnumBlocks()) {
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
            for (IndexStrPart v: offsetsEnum.getValues()) {
                boolean added_ = false;
                if (StringUtil.quickEq(v.getPart(),_page.getKeyWords().getKeyWordNull())) {
                    checkDuplicateListedValue(_anaDoc,_page,Argument.createVoid());
                    stdValues.add(Argument.createVoid());
                    added_ = true;
                } else {
                    for (InnerTypeOrElement f: e_.getEnumBlocks()) {
                        if (StringUtil.contains(f.getFieldName(), v.getPart())) {
                            ClassField pair_ = new ClassField(f.getImportedClassName(), v.getPart());
                            checkDuplicateListedEnum(_anaDoc,_page, pair_, StringUtil.concat(pair_.getClassName(), ".", pair_.getFieldName()));
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
                            _page.getKeyWords().getKeyWordCase(),
                            value);
                    AnalyzingDoc.addError(un_, _page);
                    break;
                }
            }
            return;
        }
        processNumValues(_anaDoc,instance_,resSwitch_, _page);
//        check(_anaDoc, _page, resSwitch_, instance_);
    }

    private void processNumValues(AnalyzingDoc _anaDoc, boolean _instance, AnaClassArgumentMatching _resSwitch, AnalyzedPageEl _page) {
        _page.setAcceptCommaInstr(true);
        _page.setGlobalOffset(valueOffset);
        _page.zeroOffset();
        root = RenderAnalysis.getRootAnalyzedOperations(value, 0, _anaDoc, _page,resultExpression);
        _page.setAcceptCommaInstr(false);
        if (root instanceof DeclaringOperation) {
            CustList<OperationNode> childrenNodes_ = ((DeclaringOperation) root).getChildrenNodes();
            StrTypes children_ = ((DeclaringOperation) root).getChildren();
            int len_ = childrenNodes_.size();
            for (int i = 0; i < len_; i++) {
                OperationNode ch_ = childrenNodes_.get(i);
                String value_ = StrTypes.value(children_, i);
                checkRetrieve(_anaDoc,_instance,_resSwitch,ch_.getResultClass(),_page, ch_, value_);
            }
        } else {
            checkRetrieve(_anaDoc,_instance,_resSwitch,root.getResultClass(),_page, root, value);
        }
    }

    private void checkRetrieve(AnalyzingDoc _anaDoc, boolean _instance,AnaClassArgumentMatching _resSwitch, AnaClassArgumentMatching _resCase, AnalyzedPageEl _page, OperationNode _ch, String _value) {
        ClassField classField_ = ElUtil.tryGetAccess(_ch);
        if (classField_ != null) {
            checkDuplicateListedEnum(_anaDoc,_page, classField_, StringUtil.concat(classField_.getClassName(), ".", classField_.getFieldName()));
            checkInh(_anaDoc, _page, _resSwitch, _instance, _resCase, StringUtil.concat(classField_.getClassName(), ".", classField_.getFieldName()));
            enumValues.add(classField_);
        } else {
            Argument argument_ = _ch.getArgument();
            if (argument_ != null) {
                checkDuplicateListedValue(_anaDoc,_page,argument_);
                checkInh(_anaDoc, _page, _resSwitch, _instance, _resCase, AnaApplyCoreMethodUtil.getString(argument_, _page));
                stdValues.add(argument_);
            } else {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(valueOffset);
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                        _page.getKeyWords().getKeyWordCase(),
                        _value);
                AnalyzingDoc.addError(un_, _page);
            }
        }
    }

    private void checkDuplicateListedEnum(AnalyzingDoc _anaDoc, AnalyzedPageEl _page, ClassField _classField, String _display) {
        AnaRendParentBlock par_ = getParent();
        AnaRendBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof AnaRendCaseCondition) {
                AnaRendCaseCondition c_ = (AnaRendCaseCondition) first_;
                for (ClassField p: c_.enumValues) {
                    if (_classField.eq(p)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_page.getCurrentFile());
                        un_.setIndexFile(getValueOffset()+ getOffset());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                _page.getKeyWords().getKeyWordCase(),
                                _display,
                                _page.getKeyWords().getKeyWordSwitch());
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
                un_.setIndexFile(getValueOffset()+ getOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                        _page.getKeyWords().getKeyWordCase(),
                        _display,
                        _page.getKeyWords().getKeyWordSwitch());
                AnalyzingDoc.addError(un_, _page);
                return;
            }
        }
    }
    private void checkDuplicateListedValue(AnalyzingDoc _anaDoc, AnalyzedPageEl _page, Argument _value) {
        AnaRendParentBlock par_ = getParent();
        AnaRendBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof AnaRendCaseCondition) {
                AnaRendCaseCondition c_ = (AnaRendCaseCondition) first_;
                for (Argument p: c_.stdValues) {
                    if (_value.getStruct().sameReference(p.getStruct())) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_page.getCurrentFile());
                        un_.setIndexFile(getValueOffset()+ getOffset());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                _page.getKeyWords().getKeyWordCase(),
                                AnaApplyCoreMethodUtil.getString(_value, _page),
                                _page.getKeyWords().getKeyWordSwitch());
                        AnalyzingDoc.addError(un_, _page);
                        return;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
        for (Argument p: stdValues) {
            if (_value.getStruct().sameReference(p.getStruct())) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(getValueOffset()+ getOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                        _page.getKeyWords().getKeyWordCase(),
                        AnaApplyCoreMethodUtil.getString(_value, _page),
                        _page.getKeyWords().getKeyWordSwitch());
                AnalyzingDoc.addError(un_, _page);
                return;
            }
        }
    }
    private void checkInh(AnalyzingDoc _anaDoc, AnalyzedPageEl _page, AnaClassArgumentMatching _resSwitch, boolean _instance, AnaClassArgumentMatching _resCase, String _string) {
        Mapping m_ = new Mapping();
        m_.setArg(_resCase);
        m_.setParam(_resSwitch);
        if (!_instance &&!AnaInherits.isCorrectOrNumbers(m_, _page)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(valueOffset);
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseValue(),
                    _page.getKeyWords().getKeyWordCase(),
                    _string,
                    StringUtil.join(_resSwitch.getNames(),AND_ERR));
            AnalyzingDoc.addError(un_, _page);
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        if (!getVariableName().isEmpty()) {
            _ip.getInfosVars().removeKey(getVariableName());
        }
    }

    public OperationNode getRoot() {
        return root;
    }

    public CustList<Argument> getStdValues() {
        return stdValues;
    }

    public CustList<ClassField> getEnumValues() {
        return enumValues;
    }

    public int getValueOffset() {
        return valueOffset;
    }

}
