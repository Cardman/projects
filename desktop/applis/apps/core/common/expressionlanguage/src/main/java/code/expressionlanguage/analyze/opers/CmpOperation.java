package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public final class CmpOperation extends MethodOperation implements MiddleSymbolOperation {

    private boolean stringCompare;
    private ClassMethodId classMethodId;
    private String op;
    private boolean okNum;
    private int opOffset;
    private int rootNumber = -1;
    private int memberNumber = -1;

    public CmpOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        op = _op.getOperators().firstValue().trim();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        LgNames stds_ = _page.getStandards();
        okNum = true;
        if (chidren_.size() != 2) {
            okNum = false;
            _page.setOkNumOp(false);
            int in_ = Math.min(getOperations().getOperators().size()-1,1);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(in_), _page);
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFileName(_page.getLocalizer().getCurrentFileName());
            int index_ = _page.getLocalizer().getCurrentLocationIndex();
            badNb_.setIndexFile(index_);
            //first oper
            badNb_.buildError(_page.getAnalysisMessages().getOperatorNbDiff(),
                    Integer.toString(2),
                    Integer.toString(chidren_.size()),
                    op
            );
            _page.getLocalizer().addError(badNb_);
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(badNb_.getBuiltError()) +"\" class=\"e\">",index_));
            err_.add(new PartOffset("</a>",index_+getOperations().getOperators().getValue(in_).length()));
            getPartOffsetsChildren().add(err_);
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            return;
        }
        opOffset = getOperations().getOperators().firstKey();
        OperationNode l_ = chidren_.first();
        AnaClassArgumentMatching first_ = l_.getResultClass();
        OperationNode r_ = chidren_.last();
        AnaClassArgumentMatching second_ = r_.getResultClass();
        String op_ = getOperations().getOperators().firstValue().trim();
        OperatorConverter cl_ = getBinaryOperatorOrMethod(this,l_,r_, op_, _page);
        if (cl_.getSymbol() != null) {
            if (!AnaTypeUtil.isPrimitive(cl_.getSymbol().getClassName(), _page)) {
                classMethodId = cl_.getSymbol();
                rootNumber = cl_.getRootNumber();
                memberNumber = cl_.getMemberNumber();
            }
            return;
        }
        String stringType_ = stds_.getAliasString();
        if (first_.matchClass(stringType_) && second_.matchClass(stringType_)) {
            stringCompare = true;
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            Argument arg_ = l_.getArgument();
            checkNull(arg_, _page);
            arg_ = r_.getArgument();
            checkNull(arg_, _page);
            first_.setCheckOnlyNullPe(true);
            second_.setCheckOnlyNullPe(true);
            return;
        }
        if (AnaTypeUtil.isFloatOrderClass(first_,second_, _page)) {
            AnaClassArgumentMatching classFirst_ = AnaTypeUtil.toPrimitive(first_, _page);
            AnaClassArgumentMatching classSecond_ = AnaTypeUtil.toPrimitive(second_, _page);
            l_.getResultClass().setUnwrapObject(classFirst_, _page.getStandards());
            r_.getResultClass().setUnwrapObject(classSecond_, _page.getStandards());
            l_.quickCancel();
            r_.quickCancel();
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            return;
        }
        if (AnaTypeUtil.isIntOrderClass(first_,second_, _page)) {
            AnaClassArgumentMatching classFirst_ = AnaTypeUtil.toPrimitive(first_, _page);
            AnaClassArgumentMatching classSecond_ = AnaTypeUtil.toPrimitive(second_, _page);
            l_.getResultClass().setUnwrapObject(classFirst_, _page.getStandards());
            r_.getResultClass().setUnwrapObject(classSecond_, _page.getStandards());
            l_.quickCancel();
            r_.quickCancel();
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            return;
        }
        okNum = false;
        _page.setOkNumOp(false);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _page);
        StringList expectedTypes_ = new StringList();
        expectedTypes_.add(stds_.getAliasPrimDouble());
        expectedTypes_.add(stds_.getAliasString());
        String res_ = _page.getStandards().getAliasPrimBoolean();
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        int index_ = _page.getLocalizer().getCurrentLocationIndex();
        un_.setIndexFile(index_);
        un_.setFileName(_page.getLocalizer().getCurrentFileName());
        //oper
        un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                StringList.join(new StringList(
                        StringList.join(first_.getNames(),"&"),
                        StringList.join(second_.getNames(),"&")
                ),";"),
                getOp());
        _page.getLocalizer().addError(un_);
        CustList<PartOffset> err_ = new CustList<PartOffset>();
        err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",index_));
        err_.add(new PartOffset("</a>",index_+op.length()));
        getPartOffsetsChildren().add(err_);
        setResultClass(new AnaClassArgumentMatching(res_, _page.getStandards()));
    }
    @Override
    void checkNull(Argument _arg, AnalyzedPageEl _page) {
        if (Argument.isNullValue(_arg)) {
            okNum = false;
            _page.setOkNumOp(false);
        }
        super.checkNull(_arg, _page);
    }
    public boolean isStringCompare() {
        return stringCompare;
    }

    @Override
    public void quickCalculate(AnalyzedPageEl _page) {
        if (!okNum) {
            return;
        }
        tryGetResult(op, classMethodId, stringCompare, this, _page);
    }
    private static void tryGetResult(String _op, ClassMethodId _cl, boolean _str, MethodOperation _to, AnalyzedPageEl _page) {
        if (_cl != null) {
            return;
        }
        CustList<OperationNode> chidren_ = _to.getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        Argument arg_;
        if (_str) {
            arg_ = calculateCommonStr(first_, second_, _op);
        } else {
            arg_ = calculateCommonNb(first_, second_, _op);
        }
        _to.setSimpleArgumentAna(arg_, _page);
    }
    public static Argument calculateCommonNb(Argument _one, Argument _two, String _op) {
        boolean complement_ = false;
        String useOp_ = _op;
        if (StringList.quickEq(_op, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringList.quickEq(_op, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        BooleanStruct arg_;
        if (StringList.quickEq(useOp_, LOWER)) {
            arg_ = NumParsers.quickCalculateLowerNb(_one.getStruct(), _two.getStruct());
        } else {
            arg_ = NumParsers.quickCalculateGreaterNb(_one.getStruct(), _two.getStruct());
        }
        if (complement_) {
            arg_ = arg_.neg();
        }
        return new Argument(arg_);
    }
    public static Argument calculateCommonStr(Argument _one, Argument _two, String _op) {
        boolean complement_ = false;
        String useOp_ = _op;
        if (StringList.quickEq(_op, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringList.quickEq(_op, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        BooleanStruct arg_;
        if (StringList.quickEq(useOp_, LOWER)) {
            arg_ = NumParsers.quickCalculateLowerStr(_one.getStruct(), _two.getStruct());
        } else {
            arg_ = NumParsers.quickCalculateGreaterStr(_one.getStruct(), _two.getStruct());
        }
        if (complement_) {
            arg_ = arg_.neg();
        }
        return new Argument(arg_);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    @Override
    public String getOp() {
        return op;
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

    @Override
    public int getOpOffset() {
        return opOffset;
    }

    @Override
    public int getMemberNumber() {
        return memberNumber;
    }

    @Override
    public int getRootNumber() {
        return rootNumber;
    }
}
