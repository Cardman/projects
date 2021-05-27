package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.linkage.LinkageUtil;
import code.maths.litteralcom.StrTypes;
import code.util.*;
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
        SettableElResult elt_ = AffectationOperation.tryGetSettable(this);
        if (!isLeftValue(elt_)) {
            StrTypes ops_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //oper len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                    operatorContent.getOper());
            _page.getLocalizer().addError(un_);
            int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset(ExportCst.anchorErr(un_.getBuiltError()),opLocat_));
            err_.add(new PartOffset(ExportCst.END_ANCHOR,opLocat_+ operatorContent.getOper().length()));
            getPartOffsetsChildren().add(err_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        settable = elt_;
        if (settable instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)settable;
            StringMap<Boolean> fieldsAfterLast_ = _page.getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(cst_, fieldsAfterLast_, _page)) {
                setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //field name len
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
            }
        }
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
            AnaFormattedRootBlock test_ = foundTest_.getFormattedType();
            if (test_ != null) {
                clMatchLeft_.implicitInfosTest(foundTest_);
                functionTest = foundTest_.getPair();
            }
            fct.infos(cl_,_page);
            Mapping map_ = new Mapping();
            map_.setArg(getResultClass());
            map_.setParam(left_.getResultClass());
            if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(left_.getResultClass().getSingleNameOrEmpty(), getResultClass(), _page);
                if (res_.isFoundMethod()) {
                    conv.infos(res_);
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(getResultClass().getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(left_.getResultClass().getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    addErr(cast_.getBuiltError());
                }
                setResultClass(AnaClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(clMatchLeft_, _page), _page.getPrimitiveTypes()));
            }
            setBool(right_,_page);
            return;
        }
        setResultClass(AnaClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(clMatchLeft_, _page), _page.getPrimitiveTypes()));
        elt_.setVariable(false);
        String stringType_ = _page.getAliasString();
        boolean isString_ = clMatchLeft_.matchClass(stringType_);
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();

        if (StringUtil.quickEq(operatorContent.getOper(), AbsBk.PLUS_EQ)) {
            if (!AnaTypeUtil.isPureNumberClass(clMatchLeft_, _page)) {
                if (!isString_) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    CustList<PartOffset> err_ = new CustList<PartOffset>();
                    err_.add(new PartOffset(ExportCst.anchorErr(cast_.getBuiltError()),opLocat_));
                    err_.add(new PartOffset(ExportCst.END_ANCHOR,opLocat_+ operatorContent.getOper().length()-1));
                    getPartOffsetsChildren().add(err_);
                    return;
                }
                concat = true;
                clMatchRight_.setConvertToString(true);
                return;
            }
            if (!AnaTypeUtil.isPureNumberClass(clMatchRight_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset(ExportCst.anchorErr(cast_.getBuiltError()),opLocat_));
                err_.add(new PartOffset(ExportCst.END_ANCHOR,opLocat_+ operatorContent.getOper().length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            if (!AnaTypeUtil.isFloatOrderClass(clMatchLeft_,clMatchRight_, _page)
                    && !AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset(ExportCst.anchorErr(cast_.getBuiltError()),opLocat_));
                err_.add(new PartOffset(ExportCst.END_ANCHOR,opLocat_+ operatorContent.getOper().length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            return;
        }
        if (StringUtil.quickEq(operatorContent.getOper(), AbsBk.AND_EQ) || StringUtil.quickEq(operatorContent.getOper(), AbsBk.OR_EQ) || StringUtil.quickEq(operatorContent.getOper(), AbsBk.XOR_EQ)) {
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
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset(ExportCst.anchorErr(cast_.getBuiltError()),opLocat_));
                err_.add(new PartOffset(ExportCst.END_ANCHOR,opLocat_+ operatorContent.getOper().length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            setBool(right_,_page);
            return;
        }
        if (StringUtil.quickEq(operatorContent.getOper(), AbsBk.AND_LOG_EQ) || StringUtil.quickEq(operatorContent.getOper(), AbsBk.OR_LOG_EQ)
                || StringUtil.quickEq(operatorContent.getOper(), AbsBk.AND_LOG_EQ_SHORT) || StringUtil.quickEq(operatorContent.getOper(), AbsBk.OR_LOG_EQ_SHORT)) {
            if (!clMatchLeft_.isBoolType(_page) || !clMatchRight_.isBoolType(_page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset(ExportCst.anchorErr(cast_.getBuiltError()),opLocat_));
                err_.add(new PartOffset(ExportCst.END_ANCHOR,opLocat_+ operatorContent.getOper().length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            setBool(right_,_page);
            return;
        }
        if (StringUtil.quickEq(operatorContent.getOper(), AbsBk.NULL_EQ) || StringUtil.quickEq(operatorContent.getOper(), AbsBk.NULL_EQ_SHORT)) {
            StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(clMatchRight_);
            mapping_.setParam(clMatchLeft_);
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(clMatchLeft_.getSingleNameOrEmpty(), clMatchRight_, _page);
                if (res_.isFoundMethod()) {
                    clMatchRight_.implicitInfos(res_);
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    CustList<PartOffset> err_ = new CustList<PartOffset>();
                    err_.add(new PartOffset(ExportCst.anchorErr(cast_.getBuiltError()),opLocat_));
                    err_.add(new PartOffset(ExportCst.END_ANCHOR,opLocat_+ operatorContent.getOper().length()-1));
                    getPartOffsetsChildren().add(err_);
                }
            }
            setResultClass(AnaClassArgumentMatching.copy(clMatchLeft_, _page.getPrimitiveTypes()));
            return;
        }
        if (!AnaTypeUtil.isFloatOrderClass(clMatchLeft_,clMatchRight_, _page)
                && !AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_page.getLocalizer().getCurrentFileName());
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //oper len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset(ExportCst.anchorErr(cast_.getBuiltError()),opLocat_));
            err_.add(new PartOffset(ExportCst.END_ANCHOR,opLocat_+ operatorContent.getOper().length()-1));
            getPartOffsetsChildren().add(err_);
        } else {
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
        }
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
