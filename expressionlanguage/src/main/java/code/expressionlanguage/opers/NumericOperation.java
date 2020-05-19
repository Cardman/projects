package code.expressionlanguage.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.*;

public abstract class NumericOperation extends MethodOperation implements MiddleSymbolOperation {
    private ClassMethodId classMethodId;
    private String op;
    private int opOffset;
    private boolean okNum;

    public NumericOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        op = _op.getOperators().firstValue();
        opOffset = _op.getOperators().firstKey();
    }

    static ClassArgumentMatching getResultClass(ClassArgumentMatching _a, ContextEl _cont, ClassArgumentMatching _b) {
        int oa_ = PrimitiveTypeUtil.getOrderClass(_a, _cont);
        String exp_ = _cont.getStandards().getAliasNumber();
        boolean ok_ = true;
        if (oa_ == 0) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_cont.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_cont.getAnalyzing().getLocalizer().getCurrentFileName());
            //operator
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(_a.getNames(),"&"));
            _cont.getAnalyzing().getLocalizer().addError(un_);
            _cont.getAnalyzing().setOkNumOp(false);
            ok_ = false;
        }
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b, _cont);
        if (ob_ == 0) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_cont.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_cont.getAnalyzing().getLocalizer().getCurrentFileName());
            //operator
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(_a.getNames(),"&"));
            _cont.getAnalyzing().getLocalizer().addError(un_);
            _cont.getAnalyzing().setOkNumOp(false);
            ok_ = false;
        }
        if (!ok_) {
            return new ClassArgumentMatching(exp_);
        }
        return getQuickResultClass(_a, oa_, _cont, _b, ob_);
    }

    static ClassArgumentMatching getQuickResultClass(ClassArgumentMatching _a, int _oa, ContextEl _cont, ClassArgumentMatching _b, int _ob) {
        ClassArgumentMatching arg_;
        int max_ = Math.max(_oa, _ob);
        if (_oa > _ob) {
            arg_ = _a;
        } else {
            arg_ = _b;
        }
        LgNames stds_ = _cont.getStandards();
        int intOrder_ = PrimitiveTypeUtil.getOrderClass(stds_.getAliasPrimInteger(), _cont);
        if (max_ < intOrder_) {
            arg_ = new ClassArgumentMatching(stds_.getAliasPrimInteger());
        }
        return PrimitiveTypeUtil.toPrimitive(arg_, _cont);
    }

    @Override
    public final void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        ClassArgumentMatching a_ = chidren_.first().getResultClass();
        ResultOperand r_;
        IntTreeMap< String> ops_ = getOperations().getOperators();
        ClassArgumentMatching c_ = chidren_.last().getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
        ClassMethodIdReturn cust_ = getOperator(_conf, ops_.firstValue(), a_, c_);
        okNum = true;
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
        r_ = analyzeOper(a_, ops_.firstValue(), c_, _conf);
        if (!r_.isCatString()) {
            chidren_.first().cancelArgument();
            chidren_.last().cancelArgument();
        } else {
            chidren_.first().cancelArgumentString();
            chidren_.last().cancelArgumentString();
        }
        setCatenize(r_);
        okNum = _conf.getAnalyzing().isOkNumOp();
        a_ = r_.getResult();
        setResultClass(new ClassArgumentMatching(a_));
    }

    abstract ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, ContextEl _cont);
    @Override
    public final void analyzeAssignmentBeforeNextSibling(ContextEl _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    @Override
    public final void analyzeAssignmentAfter(ContextEl _conf) {
        analyzeStdAssignmentAfter(_conf);
    }
    abstract Argument calculateOperAna(Argument _a, String _op, Argument _b, ContextEl _an);

    @Override
    public void quickCalculate(ContextEl _conf) {
        if (classMethodId != null || !okNum) {
            return;
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        IntTreeMap< String> ops_ = getOperations().getOperators();
        Argument c_ = chidren_.last().getArgument();
        Argument r_;
        r_ = calculateOperAna(a_, ops_.firstValue(), c_, _conf);
        if (r_.isNull()) {
            return;
        }
        a_ = r_;
        setSimpleArgumentAna(a_, _conf);
    }

    @Override
    final void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
    abstract void setCatenize(ResultOperand _res);

    @Override
    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    @Override
    public String getOp() {
        return op;
    }

    @Override
    public int getOpOffset() {
        return opOffset;
    }
    
    @Override
    public boolean isOkNum() {
        return okNum;
    }
}
