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
        checkFinal(this,_page, settable);
        setResultClass(AnaClassArgumentMatching.copy(getSettableResClass(), _page.getPrimitiveTypes()));
        StrTypes ops_ = getOperators();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        String op_ = operToSearch(ops_);
        if (StringExpUtil.isLogical(op_)) {
            logical(_page, op_);
            return;
        }
        if (StringExpUtil.isBinNum(op_)) {
            binNum(_page, op_);
            return;
        }
        if (StringExpUtil.isBitwise(op_)) {
            bitwise(_page, op_);
            return;
        }
        if (StringExpUtil.isShiftOper(op_)) {
            shiftOper(_page, op_);
            return;
        }
        nullSafe(_page);
    }

    private void shiftOper(AnalyzedPageEl _page, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (AnaTypeUtil.isIntOrderClass(clMatchLeft_, clMatchRight_, _page)) {
            natCompoundShiftNum(_page);
            return;
        }
        OperatorConverter res_ = tryGetStd(_page, _op, this, groupBinShift(_page));
        compoundShift(_page,res_);
    }

    private void bitwise(AnalyzedPageEl _page, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (bitwise(clMatchLeft_, clMatchRight_, _page)) {
            natCompoundBitwise(_page);
            return;
        }
        OperatorConverter res_ = tryGetStd(_page, _op, this, groupBinBitwise(_page));
        compoundBitwise(_page, res_);
    }

    private void binNum(AnalyzedPageEl _page, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (StringUtil.quickEq(_op, "+")) {
            if (compoundPlusBinNatOper(clMatchLeft_, clMatchRight_, _page)) {
                natPlusCompound(_page);
                return;
            }
            OperatorConverter res_ = tryGetStd(_page, _op, this, groupBinNum(_page));
            compoundPlus(_page, res_);
            return;
        }
        if (binNum(clMatchLeft_, clMatchRight_, _page)) {
            natCompoundBinNum(_page);
            return;
        }
        OperatorConverter res_ = tryGetStd(_page, _op, this, groupBinNum(_page));
        compoundBinNum(_page, res_);
    }

    private void logical(AnalyzedPageEl _page, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (logical(clMatchLeft_, clMatchRight_, _page)) {
            natLogicalCoumpound(_page);
            return;
        }
        OperatorConverter res_ = tryGetLogical(_page, _op, this);
        compoundLogical(_page,res_);
    }

    static void checkFinal(OperationNode _cur,AnalyzedPageEl _page, SettableElResult _settable) {
        if (_settable instanceof SettableFieldOperation) {
            SettableFieldOperation cst_ = (SettableFieldOperation) _settable;
            StringMap<BoolVal> fieldsAfterLast_ = _page.getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(cst_, fieldsAfterLast_, _page)) {
                _cur.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                //field name len
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _page.getLocalizer().addError(un_);
                _cur.addErr(un_.getBuiltError());
            }
        }
    }

    private void nullSafe(AnalyzedPageEl _page) {
        convertRight(_page, operatorContent.getOper().length() - 1, getSettableResClass(),this);
    }

    static void convertRight(AnalyzedPageEl _page, int _length, AnaClassArgumentMatching _settableResClass, MethodOperation _curr) {
        CustList<OperationNode> chidren_ = _curr.getChildrenNodes();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(clMatchRight_);
        mapping_.setParam(_settableResClass);
        if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
            ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_settableResClass.getSingleNameOrEmpty(), clMatchRight_, _page);
            if (res_ != null) {
                clMatchRight_.implicitInfos(res_);
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //oper
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(_settableResClass.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                _curr.getPartOffsetsChildren().add(new InfoErrorDto(cast_, _page, _length));
            }
        }
    }

    private void natCompoundBitwise(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (!bitwise(clMatchLeft_, clMatchRight_, _page)) {
            notFound(_page);
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
        if (!binNum(clMatchLeft_,clMatchRight_,_page)) {
            notFound(_page);
        } else {
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
        }
    }

    private void notFound(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        FoundErrorInterpret cast_ = new FoundErrorInterpret();
        cast_.setFile(_page.getCurrentFile());
        cast_.setIndexFile(_page);
        //oper len
        cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
        _page.getLocalizer().addError(cast_);
        getPartOffsetsChildren().add(new InfoErrorDto(cast_, _page,operatorContent.getOper().length()-1));
    }

    private void natCompoundShiftNum(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (!AnaTypeUtil.isIntOrderClass(clMatchLeft_, clMatchRight_, _page)) {
            notFound(_page);
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
        if (isString_) {
            concat = true;
            clMatchRight_.setConvertToString(true);
            return;
        }
        if (!binNum(clMatchLeft_, clMatchRight_, _page)) {
            notFound(_page);
            return;
        }
        AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
        left_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
        right_.getResultClass().setUnwrapObject(unwrapped_, _page.getPrimitiveTypes());
    }
    private static boolean compoundPlusBinNatOper(AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        String stringType_ = _page.getAliasString();
        boolean isString_ = _left.matchClass(stringType_);
        if (isString_) {
            return plusBinNatOper(_left, _right, _page);
        }
        return binNum(_left, _right, _page);
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

    private void compoundShift(AnalyzedPageEl _page, OperatorConverter _oper) {
        if (_oper != null) {
            custCompound(_page, _oper);
            return;
        }
        natCompoundShiftNum(_page);
    }
    private void natLogicalCoumpound(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeft_ = left_.getResultClass();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (!logical(clMatchLeft_,clMatchRight_,_page)) {
            notFound(_page);
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
            functionTest = foundTest_.getParametrableContent().getPair();
        }
        fct.infos(_cl, _page);
        tryImplicit(this,_page, getSettableResClass(), conv);
        setBool(right_, _page);
    }

    static void tryImplicit(OperationNode _current,AnalyzedPageEl _page, AnaClassArgumentMatching _settableResClass, ClassMethodIdMemberIdTypeFct _conv) {
        Mapping map_ = new Mapping();
        map_.setArg(_current.getResultClass());
        map_.setParam(_settableResClass);
        if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
            ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_settableResClass.getSingleNameOrEmpty(), _current.getResultClass(), _page);
            if (res_ != null) {
                _conv.infos(res_);
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(_current.getResultClass().getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(_settableResClass.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                _current.addErr(cast_.getBuiltError());
            }
            _current.setResultClass(AnaClassArgumentMatching.copy(_settableResClass, _page.getPrimitiveTypes()));
        }
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
