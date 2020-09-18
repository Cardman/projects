package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.instr.OperationsSequence;

import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.*;

public abstract class NumericOperation extends MethodOperation implements MiddleSymbolOperation {
    private ClassMethodId classMethodId;
    private String op;
    private int opOffset;
    private boolean okNum;
    private int rootNumber = -1;
    private int memberNumber = -1;

    public NumericOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        op = _op.getOperators().firstValue();
        opOffset = _op.getOperators().firstKey();
    }

    static AnaClassArgumentMatching getIntResultClass(AnaClassArgumentMatching _a, ContextEl _cont, AnaClassArgumentMatching _b) {
        int oa_ = AnaTypeUtil.getIntOrderClass(_a, _cont);
        int ob_ = AnaTypeUtil.getIntOrderClass(_b, _cont);
        return getQuickResultClass(_a, oa_, _cont, _b, ob_);
    }
    static AnaClassArgumentMatching getFloatResultClass(AnaClassArgumentMatching _a, ContextEl _cont, AnaClassArgumentMatching _b) {
        int oa_ = AnaTypeUtil.getFloatOrderClass(_a, _cont);
        int ob_ = AnaTypeUtil.getFloatOrderClass(_b, _cont);
        return getQuickResultClass(_a, oa_, _cont, _b, ob_);
    }
    static AnaClassArgumentMatching getQuickResultClass(AnaClassArgumentMatching _a, int _oa, ContextEl _cont, AnaClassArgumentMatching _b, int _ob) {
        AnaClassArgumentMatching arg_;
        int max_ = Math.max(_oa, _ob);
        if (_oa > _ob) {
            arg_ = _a;
        } else {
            arg_ = _b;
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        int intOrder_ = AnaTypeUtil.getIntOrderClass(stds_.getAliasPrimInteger(), _cont);
        if (max_ < intOrder_) {
            arg_ = new AnaClassArgumentMatching(stds_.getAliasPrimInteger(),PrimitiveTypes.INT_WRAP);
        }
        return AnaTypeUtil.toPrimitive(arg_, page_);
    }

    @Override
    public final void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode l_ = chidren_.first();
        AnaClassArgumentMatching a_ = l_.getResultClass();
        IntTreeMap< String> ops_ = getOperations().getOperators();
        OperationNode r_ = chidren_.last();
        AnaClassArgumentMatching c_ = r_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
        okNum = true;
        OperatorConverter cl_ = getBinaryOperatorOrMethod(this,l_,r_, ops_.firstValue(), _conf);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        if (cl_.getSymbol() != null) {
            if (!AnaTypeUtil.isPrimitive(cl_.getSymbol().getClassName(),page_)) {
                classMethodId = cl_.getSymbol();
                rootNumber = cl_.getRootNumber();
                memberNumber = cl_.getMemberNumber();
            }
            return;
        }
        ResultOperand res_ = analyzeOper(a_, ops_.firstValue(), c_, _conf);
        if (!res_.isCatString()) {
            l_.quickCancel();
            r_.quickCancel();
        } else {
            l_.cancelArgumentString();
            r_.cancelArgumentString();
        }
        setCatenize(res_);
        okNum = page_.isOkNumOp();
        a_ = res_.getResult();
        setResultClass(AnaClassArgumentMatching.copy(a_,page_.getStandards()));
    }

    abstract ResultOperand analyzeOper(AnaClassArgumentMatching _a, String _op, AnaClassArgumentMatching _b, ContextEl _cont);

    abstract Argument calculateOperAna(Argument _a, String _op, Argument _b, AnalyzedPageEl _page);

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
        r_ = calculateOperAna(a_, ops_.firstValue(), c_, _conf.getAnalyzing());
        if (r_.isNull()) {
            return;
        }
        a_ = r_;
        setSimpleArgumentAna(a_, _conf.getAnalyzing());
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

    public int getRootNumber() {
        return rootNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }
}
