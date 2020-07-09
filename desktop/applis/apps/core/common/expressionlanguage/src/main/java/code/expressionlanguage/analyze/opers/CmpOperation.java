package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.AliasNumber;
import code.expressionlanguage.stds.LgNames;
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

    public CmpOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        op = _op.getOperators().firstValue().trim();
    }

    @Override
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        LgNames stds_ = _conf.getStandards();
        okNum = true;
        if (chidren_.size() != 2) {
            okNum = false;
            _conf.getAnalyzing().setOkNumOp(false);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(1), _conf);
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            int index_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            badNb_.setIndexFile(index_);
            //first oper
            badNb_.buildError(_conf.getAnalysisMessages().getOperatorNbDiff(),
                    Integer.toString(2),
                    Integer.toString(chidren_.size()),
                    op
            );
            _conf.getAnalyzing().getLocalizer().addError(badNb_);
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(badNb_.getBuiltError()) +"\" class=\"e\">",index_));
            err_.add(new PartOffset("</a>",index_+getOperations().getOperators().getValue(1).length()));
            getPartOffsetsChildren().add(err_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            return;
        }
        opOffset = getOperations().getOperators().firstKey();
        ClassArgumentMatching first_ = chidren_.first().getResultClass();
        ClassArgumentMatching second_ = chidren_.last().getResultClass();
        String op_ = getOperations().getOperators().firstValue().trim();
        ClassMethodId cl_ = getBinaryOperatorOrMethod(this,first_,second_, op_, _conf);
        if (cl_ != null) {
            classMethodId = cl_;
            return;
        }
        String stringType_ = stds_.getAliasString();
        if (first_.matchClass(stringType_) && second_.matchClass(stringType_)) {
            stringCompare = true;
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            Argument arg_ = chidren_.first().getArgument();
            checkNull(arg_,_conf);
            arg_ = chidren_.last().getArgument();
            checkNull(arg_,_conf);
            first_.setCheckOnlyNullPe(true);
            second_.setCheckOnlyNullPe(true);
            return;
        }
        if (PrimitiveTypeUtil.isFloatOrderClass(first_,second_, _conf)) {
            ClassArgumentMatching classFirst_ = PrimitiveTypeUtil.toPrimitive(first_,  _conf);
            ClassArgumentMatching classSecond_ = PrimitiveTypeUtil.toPrimitive(second_,  _conf);
            chidren_.first().getResultClass().setUnwrapObject(classFirst_);
            chidren_.last().getResultClass().setUnwrapObject(classSecond_);
            chidren_.first().cancelArgument();
            chidren_.last().cancelArgument();
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            return;
        }
        if (PrimitiveTypeUtil.isIntOrderClass(first_,second_, _conf)) {
            ClassArgumentMatching classFirst_ = PrimitiveTypeUtil.toPrimitive(first_,  _conf);
            ClassArgumentMatching classSecond_ = PrimitiveTypeUtil.toPrimitive(second_,  _conf);
            chidren_.first().getResultClass().setUnwrapObject(classFirst_);
            chidren_.last().getResultClass().setUnwrapObject(classSecond_);
            chidren_.first().cancelArgument();
            chidren_.last().cancelArgument();
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            return;
        }
        okNum = false;
        _conf.getAnalyzing().setOkNumOp(false);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
        StringList expectedTypes_ = new StringList();
        expectedTypes_.add(stds_.getAliasPrimDouble());
        expectedTypes_.add(stds_.getAliasString());
        String res_ = _conf.getStandards().getAliasPrimBoolean();
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        int index_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
        un_.setIndexFile(index_);
        un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
        //oper
        un_.buildError(_conf.getAnalysisMessages().getUnexpectedOperandTypes(),
                StringList.join(new StringList(
                        StringList.join(first_.getNames(),"&"),
                        StringList.join(second_.getNames(),"&")
                ),";"),
                getOp());
        _conf.getAnalyzing().getLocalizer().addError(un_);
        CustList<PartOffset> err_ = new CustList<PartOffset>();
        err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",index_));
        err_.add(new PartOffset("</a>",index_+op.length()));
        getPartOffsetsChildren().add(err_);
        setResultClass(new ClassArgumentMatching(res_));
    }
    @Override
    void checkNull(Argument _arg, ContextEl _an) {
        if (Argument.isNullValue(_arg)) {
            okNum = false;
            _an.getAnalyzing().setOkNumOp(false);
        }
        super.checkNull(_arg,_an);
    }
    public boolean isStringCompare() {
        return stringCompare;
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        if (!okNum) {
            return;
        }
        tryGetResult(_conf, op, classMethodId, stringCompare, this);
    }
    public static void tryGetResult(ContextEl _conf, String _op, ClassMethodId _cl, boolean _str, MethodOperation _to) {
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
        _to.setSimpleArgumentAna(arg_, _conf);
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
            arg_ = AliasNumber.quickCalculateLowerNb(_one.getStruct(), _two.getStruct());
        } else {
            arg_ = AliasNumber.quickCalculateGreaterNb(_one.getStruct(), _two.getStruct());
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
            arg_ = AliasNumber.quickCalculateLowerStr(_one.getStruct(), _two.getStruct());
        } else {
            arg_ = AliasNumber.quickCalculateGreaterStr(_one.getStruct(), _two.getStruct());
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
}
