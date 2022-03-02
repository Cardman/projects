package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class CompoundAffectationOperation extends MethodOperation {

    private SettableElResult settable;
    private final AnaOperatorContent operatorContent;
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final ClassMethodIdMemberIdTypeFct conv = new ClassMethodIdMemberIdTypeFct();
    private AnaTypeFct functionTest;

    private boolean rightBool;
    private boolean concat;

    public CompoundAffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        operatorContent = new AnaOperatorContent();
        operatorContent.setOper(_op.getOperators().firstValue());
        operatorContent.setOpOffset(_op.getOperators().firstKey());
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        SettableElResult elt_ = AffectationOperation.tryGetCastSettable(this);
        if (!isLeftValue(elt_)) {
            StrTypes ops_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //oper len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                    operatorContent.getOper());
            _page.getLocalizer().addError(un_);
            int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
            getPartOffsetsChildren().add(new InfoErrorDto(un_.getBuiltError(),opLocat_,operatorContent.getOper().length()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        settable = elt_;
        elt_.setVariable(false);
        if (settable instanceof SettableFieldOperation) {
            SettableFieldOperation cst_ = (SettableFieldOperation)settable;
            StringMap<Boolean> fieldsAfterLast_ = _page.getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(cst_, fieldsAfterLast_, _page)) {
                setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //field name len
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
            }
        }
        setResultClass(AnaClassArgumentMatching.copy(settable.getResultClass(), _page.getPrimitiveTypes()));
        StrTypes ops_ = getOperations().getOperators();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
        String op_ = ops_.firstValue();
        op_ = op_.substring(0, op_.length() - 1);
        if (StringUtil.quickEq(op_, "&&&")) {
            op_ = "&&";
        } else {
            if (StringUtil.quickEq(op_, "|||")) {
                op_ = "||";
            }
        }
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        OperatorConverter cl_ = getBinaryOperatorOrMethod(this, left_,right_, op_, _page);
        if (cl_ != null) {
            ClassMethodIdReturn foundTest_ = cl_.getTest();
            if (foundTest_ != null){
                clMatchLeft_.implicitInfosTest(foundTest_);
                functionTest = foundTest_.getPair();
            }
            fct.infos(cl_,_page);
            Mapping map_ = new Mapping();
            map_.setArg(getResultClass());
            map_.setParam(settable.getResultClass());
            if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(settable.getResultClass().getSingleNameOrEmpty(), getResultClass(), _page);
                if (res_ != null) {
                    conv.infos(res_);
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(getResultClass().getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(settable.getResultClass().getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    addErr(cast_.getBuiltError());
                }
                setResultClass(AnaClassArgumentMatching.copy(settable.getResultClass(), _page.getPrimitiveTypes()));
            }
            setBool(right_,_page);
            return;
        }
        String stringType_ = _page.getAliasString();
        boolean isString_ = clMatchLeft_.matchClass(stringType_);
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();

        if (StringUtil.quickEq(getOper(), AbsBk.PLUS_EQ)) {
            if (!AnaTypeUtil.isPureNumberClass(clMatchLeft_, _page)) {
                if (!isString_) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    getPartOffsetsChildren().add(new InfoErrorDto(cast_.getBuiltError(),opLocat_,operatorContent.getOper().length()-1));
                    return;
                }
                concat = true;
                clMatchRight_.setConvertToString(true);
                return;
            }
            if (!AnaTypeUtil.isPureNumberClass(clMatchRight_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                getPartOffsetsChildren().add(new InfoErrorDto(cast_.getBuiltError(),opLocat_,operatorContent.getOper().length()-1));
                return;
            }
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            if (!AnaTypeUtil.isFloatOrderClass(clMatchLeft_,clMatchRight_, _page)
                    && !AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                getPartOffsetsChildren().add(new InfoErrorDto(cast_.getBuiltError(),opLocat_,getOper().length()-1));
                return;
            }
            left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            return;
        }
        if (StringUtil.quickEq(getOper(), AbsBk.AND_EQ) || StringUtil.quickEq(getOper(), AbsBk.OR_EQ) || StringUtil.quickEq(getOper(), AbsBk.XOR_EQ)) {
            boolean okRes_ = false;
            if (clMatchLeft_.isBoolType(_page) && clMatchRight_.isBoolType(_page)) {
                okRes_ = true;
            } else {
                if (AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_, _page)) {
                    okRes_ = true;
                }
            }
            if (!okRes_) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                getPartOffsetsChildren().add(new InfoErrorDto(cast_.getBuiltError(),opLocat_,operatorContent.getOper().length()-1));
                return;
            }
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            setBool(right_,_page);
            return;
        }
        if (StringUtil.quickEq(getOper(), AbsBk.AND_LOG_EQ) || StringUtil.quickEq(getOper(), AbsBk.OR_LOG_EQ)
                || StringUtil.quickEq(getOper(), AbsBk.AND_LOG_EQ_SHORT) || StringUtil.quickEq(getOper(), AbsBk.OR_LOG_EQ_SHORT)) {
            if (!clMatchLeft_.isBoolType(_page) || !clMatchRight_.isBoolType(_page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                getPartOffsetsChildren().add(new InfoErrorDto(cast_.getBuiltError(),opLocat_,operatorContent.getOper().length()-1));
                return;
            }
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            setBool(right_,_page);
            return;
        }
        if (isNullSafe()) {
            StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(clMatchRight_);
            mapping_.setParam(settable.getResultClass());
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(settable.getResultClass().getSingleNameOrEmpty(), clMatchRight_, _page);
                if (res_ != null) {
                    clMatchRight_.implicitInfos(res_);
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(settable.getResultClass().getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    getPartOffsetsChildren().add(new InfoErrorDto(cast_.getBuiltError(),opLocat_,operatorContent.getOper().length()-1));
                }
            }
            return;
        }
        if (!AnaTypeUtil.isFloatOrderClass(clMatchLeft_,clMatchRight_, _page)
                && !AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //oper len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            getPartOffsetsChildren().add(new InfoErrorDto(cast_.getBuiltError(),opLocat_,operatorContent.getOper().length()-1));
        } else {
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
        }
    }

    public boolean isNullSafe() {
        return StringUtil.quickEq(getOper(), AbsBk.NULL_EQ) || StringUtil.quickEq(getOper(), AbsBk.NULL_EQ_SHORT);
    }

    private void setBool(OperationNode _right,AnalyzedPageEl _page) {
        rightBool = _right.getResultClass().isBoolType(_page);
    }

    public String getOper() {
        return operatorContent.getOper();
    }

    public int getOpOffset(){
        return operatorContent.getOpOffset();
    }
    public ClassMethodIdMemberIdTypeFct getConv() {
        return conv;
    }
    public AnaTypeFct getFunctionImpl() {
        return conv.getFunction();
    }

    public AnaTypeFct getFunctionTest() {
        return functionTest;
    }

    public SettableElResult getSettable() {
        return settable;
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }
    public boolean isRightBool() {
        return rightBool;
    }

    public boolean isConcat() {
        return concat;
    }

    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }
}
