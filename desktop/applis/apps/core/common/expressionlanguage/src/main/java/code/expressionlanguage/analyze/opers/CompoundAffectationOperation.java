package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
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
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        SettableElResult elt_ = AffectationOperation.tryGetCastSettable(this);
        if (!isLeftValue(elt_)) {
            StrTypes ops_ = getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //oper len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                    operatorContent.getOper());
            _page.getLocalizer().addError(un_);
            getPartOffsetsChildren().add(new InfoErrorDto(un_,_page,operatorContent.getOper().length()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        WrappOperation.procArr(_page, (OperationNode) elt_);
        settable = elt_;
        elt_.setVariable(false);
        checkFinal(_page);
        setResultClass(AnaClassArgumentMatching.copy(getSettableResClass(), _page.getPrimitiveTypes()));
        StrTypes ops_ = getOperators();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        String op_ = operToSearch(ops_);
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (StringExpUtil.isLogical(op_)) {
            if (logical(clMatchLeft_,clMatchRight_,_page)) {
                natLogicalCoumpound(_page);
                return;
            }
            OperatorConverter res_ = tryGetLogical(_page, op_, this);
            compoundLogical(_page,res_);
            return;
        }
        if (binNum(op_,clMatchLeft_,clMatchRight_,_page)) {
            if (StringUtil.quickEq(op_,"+")) {
                natPlusCompound(_page);
                return;
            }
            natCompoundBinNum(_page);
            return;
        }
        if (bitwise(op_,clMatchLeft_,clMatchRight_,_page)) {
            natCompoundBitwise(_page);
            return;
        }
        if (StringExpUtil.isBinNum(op_)) {
            if (StringUtil.quickEq(op_,"+")) {
                OperatorConverter res_ = tryGetStd(_page, op_, this, groupBinNum(_page));
                compoundPlus(_page,res_);
                return;
            }
            OperatorConverter res_ = tryGetStd(_page, op_, this, groupBinNum(_page));
            compoundBinNum(_page,res_);
            return;
        }
        if (StringExpUtil.isBitwise(op_)) {
            OperatorConverter res_ = tryGetStd(_page, op_, this, groupBinBitwise(_page));
            compoundBitwise(_page,res_);
            return;
        }
        if (isNullSafe()) {
            nullSafe(_page);
            return;
        }
        natCompoundBinNum(_page);
    }

    private void checkFinal(AnalyzedPageEl _page) {
        if (settable instanceof SettableFieldOperation) {
            SettableFieldOperation cst_ = (SettableFieldOperation)settable;
            StringMap<BoolVal> fieldsAfterLast_ = _page.getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(cst_, fieldsAfterLast_, _page)) {
                setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                //field name len
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
            }
        }
    }

    private void nullSafe(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(clMatchRight_);
        mapping_.setParam(getSettableResClass());
        if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
            ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(getSettableResClass().getSingleNameOrEmpty(), clMatchRight_, _page);
            if (res_ != null) {
                clMatchRight_.implicitInfos(res_);
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //oper
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(getSettableResClass().getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                getPartOffsetsChildren().add(new InfoErrorDto(cast_, _page,operatorContent.getOper().length()-1));
            }
        }
    }

    private void natCompoundBitwise(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        boolean okRes_ = false;
        if (clMatchLeft_.isBoolType(_page) && clMatchRight_.isBoolType(_page)) {
            okRes_ = true;
        } else {
            if (AnaTypeUtil.isIntOrderClass(clMatchLeft_, clMatchRight_, _page)) {
                okRes_ = true;
            }
        }
        if (!okRes_) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page);
            //oper len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            getPartOffsetsChildren().add(new InfoErrorDto(cast_, _page,operatorContent.getOper().length()-1));
            return;
        }
        AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
        left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
        right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
        setBool(right_, _page);
    }

    private void natCompoundBinNum(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (!AnaTypeUtil.isFloatOrderClass(clMatchLeft_, clMatchRight_, _page)
                && !AnaTypeUtil.isIntOrderClass(clMatchLeft_, clMatchRight_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page);
            //oper len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            getPartOffsetsChildren().add(new InfoErrorDto(cast_, _page,operatorContent.getOper().length()-1));
        } else {
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
        }
    }

    static OperatorConverter tryGetStd(AnalyzedPageEl _page, String _op, MethodOperation _symb, CustList<CustList<ParamReturn>> _groups) {
        CustList<OperationNode> chidren_ = _symb.getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        CustList<CustList<MethodInfo>> bins_ = addBinariesStd(_page, clMatchLeft_, clMatchRight_);
        return tryGetCustom(_page, _op, null, bins_, _groups, _symb);
    }

    private void natPlusCompound(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        String stringType_ = _page.getAliasString();
        boolean isString_ = clMatchLeft_.matchClass(stringType_);
        if (!AnaTypeUtil.isPureNumberClass(clMatchLeft_, _page)) {
            if (!isString_) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                getPartOffsetsChildren().add(new InfoErrorDto(cast_, _page,operatorContent.getOper().length()-1));
                return;
            }
            concat = true;
            clMatchRight_.setConvertToString(true);
            return;
        }
        if (!AnaTypeUtil.isPureNumberClass(clMatchRight_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page);
            //oper len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            getPartOffsetsChildren().add(new InfoErrorDto(cast_, _page,operatorContent.getOper().length()-1));
            return;
        }
        AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
        if (!AnaTypeUtil.isFloatOrderClass(clMatchLeft_, clMatchRight_, _page)
                && !AnaTypeUtil.isIntOrderClass(clMatchLeft_, clMatchRight_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page);
            //oper len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            getPartOffsetsChildren().add(new InfoErrorDto(cast_, _page,getOper().length()-1));
            return;
        }
        left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
        right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
    }

    static OperatorConverter tryGetLogical(AnalyzedPageEl _page, String _op, MethodOperation _symb) {
        CustList<OperationNode> chidren_ = _symb.getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        ClassMethodIdReturn test_ = tryGetTest(left_, _op, _page, null);
        CustList<CustList<MethodInfo>> bins_ = addBinariesLogical(test_, _page, clMatchLeft_, clMatchRight_);
        return tryGetCustom(_page, _op, test_, bins_, groupBinLogical(_page), _symb);
    }

    static OperatorConverter tryGetCustom(AnalyzedPageEl _page, String _oper, ClassMethodIdReturn _test, CustList<CustList<MethodInfo>> _bins, CustList<CustList<ParamReturn>> _groups, MethodOperation _symb) {
        CustList<OperationNode> chidren_ = _symb.getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        CustList<OperationNode> pair_ = new CustList<OperationNode>(left_, right_);
        OperatorConverter oper_ = tryGetBinaryWithCust(_symb, _oper, _page, _bins, _test, pair_);
        if (oper_ != null) {
            return oper_;
        }
        return tryGetBinaryWithVirtual(_symb, _oper, _page, pair_, _groups);
    }
    private void compoundBitwise(AnalyzedPageEl _page, OperatorConverter _oper) {
        if (_oper != null) {
            custCompound(_page, _oper);
            return;
        }
        natCompoundBitwise(_page);
    }
    private void compoundLogical(AnalyzedPageEl _page, OperatorConverter _oper){
        if (_oper != null) {
            custCompound(_page, _oper);
            return;
        }
        natLogicalCoumpound(_page);
    }
    private void compoundPlus(AnalyzedPageEl _page, OperatorConverter _oper){
        if (_oper != null) {
            custCompound(_page, _oper);
            return;
        }
        natPlusCompound(_page);
    }

    private void compoundBinNum(AnalyzedPageEl _page, OperatorConverter _oper) {
        if (_oper != null) {
            custCompound(_page, _oper);
            return;
        }
        natCompoundBinNum(_page);
    }
    private void natLogicalCoumpound(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (!clMatchLeft_.isBoolType(_page) || !clMatchRight_.isBoolType(_page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page);
            //oper len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            getPartOffsetsChildren().add(new InfoErrorDto(cast_, _page,operatorContent.getOper().length()-1));
            return;
        }
        AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
        left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
        right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
        setBool(right_, _page);
    }

    private void custCompound(AnalyzedPageEl _page, OperatorConverter _cl) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        OperationNode right_ = chidren_.last();
        ClassMethodIdReturn foundTest_ = _cl.getTest();
        if (foundTest_ != null){
            clMatchLeft_.implicitInfosTest(foundTest_);
            functionTest = foundTest_.getPair();
        }
        fct.infos(_cl, _page);
        Mapping map_ = new Mapping();
        map_.setArg(getResultClass());
        map_.setParam(getSettableResClass());
        if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
            ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(getSettableResClass().getSingleNameOrEmpty(), getResultClass(), _page);
            if (res_ != null) {
                conv.infos(res_);
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(getResultClass().getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(getSettableResClass().getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
            }
            setResultClass(AnaClassArgumentMatching.copy(getSettableResClass(), _page.getPrimitiveTypes()));
        }
        setBool(right_, _page);
    }

    private String operToSearch(StrTypes _ops) {
        String op_ = _ops.firstValue();
        op_ = op_.substring(0, op_.length() - 1);
        if (StringUtil.quickEq(op_, "&&&")) {
            op_ = "&&";
        } else {
            if (StringUtil.quickEq(op_, "|||")) {
                op_ = "||";
            }
        }
        return op_;
    }

    private AnaClassArgumentMatching getSettableResClass() {
        return ExplicitOperatorOperation.getSettableResClass(settable);
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
