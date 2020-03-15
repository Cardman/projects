package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public final class EqOperation extends MethodOperation implements MiddleSymbolOperation {

    private String oper;
    private ClassMethodId classMethodId;
    private int opOffset;
    public EqOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        oper = _op.getOperators().values().first();
        opOffset = _op.getOperators().firstKey();
    }

    private static boolean calculateEq(Argument _a, Argument _b) {
        return _a.getStruct().sameReference(_b.getStruct());
    }

    @Override
    public void analyze(Analyzable _conf) {
        if (StringList.quickEq(oper.trim(), NEG_BOOL)) {
            UnexpectedOperationAffect badEl_ = new UnexpectedOperationAffect();
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.addError(badEl_);
        }
        String custOp_ = oper.trim();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode opOne_ = chidren_.first();
        OperationNode opTwo_ = chidren_.last();
        ClassArgumentMatching a_ = opOne_.getResultClass();
        ClassArgumentMatching b_ = opTwo_.getResultClass();
        ClassMethodIdReturn cust_ = getOperator(_conf, custOp_, a_, b_);
        if (cust_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(cust_.getReturnType()));
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
        LgNames stds_ = _conf.getStandards();
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (classMethodId != null) {
            return;
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        boolean b_ = calculateEq(first_, second_);
        if (complement_) {
            b_ = !b_;
        }
        Argument arg_ = new Argument(new BooleanStruct(b_));
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    public String getOper() {
        return getOp();
    }

    @Override
    public String getOp() {
        return oper;
    }

    @Override
    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    @Override
    public int getOpOffset() {
        return opOffset;
    }

    /**The execution cannot occur if there is only one character as symbol
    Sample: 1!2 leads to error even if there is two operands*/
    @Override
    public boolean isOkNum() {
        return true;
    }

}
