package code.expressionlanguage.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NumberStruct;
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
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badNb_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //first oper
            badNb_.buildError(_conf.getAnalysisMessages().getOperatorNbDiff(),
                    Integer.toString(2),
                    Integer.toString(chidren_.size()),
                    op
            );
            _conf.getAnalyzing().getLocalizer().addError(badNb_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            return;
        }
        opOffset = getOperations().getOperators().firstKey();
        ClassArgumentMatching first_ = chidren_.first().getResultClass();
        ClassArgumentMatching second_ = chidren_.last().getResultClass();
        String op_ = getOperations().getOperators().firstValue().trim();
        ClassMethodIdReturn cust_ = getOperator(_conf, op_, first_, second_);
        if (cust_.isFoundMethod()) {
            setResultClass(voidToObject(new ClassArgumentMatching(cust_.getReturnType()),_conf));
            String foundClass_ = cust_.getRealClass();
            foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            MethodId id_ = cust_.getRealId();
            classMethodId = new ClassMethodId(foundClass_, id_);
            MethodId realId_ = cust_.getRealId();
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            for (OperationNode o: chidren_) {
                firstArgs_.add(o.getResultClass());
            }
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _conf);
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
        if (first_.matchClass(stringType_) || second_.matchClass(stringType_)) {
            okNum = false;
            _conf.getAnalyzing().setOkNumOp(false);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
            String res_ = stds_.getAliasPrimBoolean();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //oper
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedOperandTypes(),
                    StringList.join(new StringList(
                            StringList.join(first_.getNames(),"&"),
                            StringList.join(second_.getNames(),"&")
                    ),";"),
                    getOp());
            _conf.getAnalyzing().getLocalizer().addError(un_);
            setResultClass(new ClassArgumentMatching(res_));
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
//        int orderClass_ = PrimitiveTypeUtil.getOrderClass(first_, _conf);
//        int orderClassTwo_ = PrimitiveTypeUtil.getOrderClass(second_, _conf);
//        if (orderClass_ > 0) {
//            if (orderClassTwo_ > 0) {
//                ClassArgumentMatching classFirst_ = PrimitiveTypeUtil.toPrimitive(first_,  _conf);
//                ClassArgumentMatching classSecond_ = PrimitiveTypeUtil.toPrimitive(second_,  _conf);
//                chidren_.first().getResultClass().setUnwrapObject(classFirst_);
//                chidren_.last().getResultClass().setUnwrapObject(classSecond_);
//                chidren_.first().cancelArgument();
//                chidren_.last().cancelArgument();
//                setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
//                return;
//            }
//            okNum = false;
//            _conf.getAnalyzing().setOkNumOp(false);
//            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
//            String res_ = stds_.getAliasPrimBoolean();
//            FoundErrorInterpret un_ = new FoundErrorInterpret();
//            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
//            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
//            //oper
//            un_.buildError(_conf.getAnalysisMessages().getUnexpectedOperandTypes(),
//                    StringList.join(new StringList(
//                            StringList.join(first_.getNames(),"&"),
//                            StringList.join(second_.getNames(),"&")
//                    ),";"),
//                    getOp());
//            _conf.getAnalyzing().getLocalizer().addError(un_);
//            setResultClass(new ClassArgumentMatching(res_));
//            return;
//        }
        okNum = false;
        _conf.getAnalyzing().setOkNumOp(false);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
        StringList expectedTypes_ = new StringList();
        expectedTypes_.add(stds_.getAliasPrimDouble());
        expectedTypes_.add(stds_.getAliasString());
        String res_ = _conf.getStandards().getAliasPrimBoolean();
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
        un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
        //oper
        un_.buildError(_conf.getAnalysisMessages().getUnexpectedOperandTypes(),
                StringList.join(new StringList(
                        StringList.join(first_.getNames(),"&"),
                        StringList.join(second_.getNames(),"&")
                ),";"),
                getOp());
        _conf.getAnalyzing().getLocalizer().addError(un_);
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
    public void analyzeAssignmentBeforeNextSibling(ContextEl _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    @Override
    public void analyzeAssignmentAfter(ContextEl _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        if (!okNum) {
            return;
        }
        tryGetResult(_conf, op, classMethodId, stringCompare, this);
    }
    public static void tryGetResult(ContextEl _conf, String _op, ClassMethodId _cl, boolean _str, ParentOperable _to) {
        if (_cl != null) {
            return;
        }
        CustList<Operable> chidren_ = _to.getChildrenOperable();
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
            arg_ = NumberStruct.quickCalculateLowerNb(_one.getStruct(), _two.getStruct());
        } else {
            arg_ = NumberStruct.quickCalculateGreaterNb(_one.getStruct(), _two.getStruct());
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
            arg_ = NumberStruct.quickCalculateLowerStr(_one.getStruct(), _two.getStruct());
        } else {
            arg_ = NumberStruct.quickCalculateGreaterStr(_one.getStruct(), _two.getStruct());
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
