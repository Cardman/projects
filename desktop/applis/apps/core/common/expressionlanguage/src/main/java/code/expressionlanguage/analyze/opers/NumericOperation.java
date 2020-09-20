package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
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

    static AnaClassArgumentMatching getIntResultClass(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        int oa_ = AnaTypeUtil.getIntOrderClass(_a, _page);
        int ob_ = AnaTypeUtil.getIntOrderClass(_b, _page);
        int max_ = Math.max(oa_, ob_);
        AnaClassArgumentMatching arg_ = getMaxWrap(_a, oa_, _b, ob_);
        LgNames stds_ = _page.getStandards();
        int intOrder_ = AnaTypeUtil.getIntOrderClass(stds_.getAliasPrimInteger(), _page);
        if (max_ < intOrder_) {
            arg_ = new AnaClassArgumentMatching(stds_.getAliasPrimInteger(),PrimitiveTypes.INT_WRAP);
        }
        return AnaTypeUtil.toPrimitive(arg_, _page);
    }
    static AnaClassArgumentMatching getFloatResultClass(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        int oa_ = AnaTypeUtil.getFloatOrderClass(_a, _page);
        int ob_ = AnaTypeUtil.getFloatOrderClass(_b, _page);
        AnaClassArgumentMatching arg_ = getMaxWrap(_a, oa_, _b, ob_);
        return AnaTypeUtil.toPrimitive(arg_, _page);
    }

    private static AnaClassArgumentMatching getMaxWrap(AnaClassArgumentMatching _a, int _oa, AnaClassArgumentMatching _b, int _ob) {
        AnaClassArgumentMatching arg_;
        if (_oa > _ob) {
            arg_ = _a;
        } else {
            arg_ = _b;
        }
        return arg_;
    }

    @Override
    public final void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode l_ = chidren_.first();
        AnaClassArgumentMatching a_ = l_.getResultClass();
        IntTreeMap< String> ops_ = getOperations().getOperators();
        OperationNode r_ = chidren_.last();
        AnaClassArgumentMatching c_ = r_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        okNum = true;
        OperatorConverter cl_ = getBinaryOperatorOrMethod(this,l_,r_, ops_.firstValue(), _page);
        if (cl_.getSymbol() != null) {
            if (!AnaTypeUtil.isPrimitive(cl_.getSymbol().getClassName(), _page)) {
                classMethodId = cl_.getSymbol();
                rootNumber = cl_.getRootNumber();
                memberNumber = cl_.getMemberNumber();
            }
            return;
        }
        ResultOperand res_ = analyzeOper(a_, ops_.firstValue(), c_, _page);
        if (!res_.isCatString()) {
            l_.quickCancel();
            r_.quickCancel();
        } else {
            l_.cancelArgumentString();
            r_.cancelArgumentString();
        }
        setCatenize(res_);
        okNum = _page.isOkNumOp();
        a_ = res_.getResult();
        setResultClass(AnaClassArgumentMatching.copy(a_, _page.getStandards()));
    }

    abstract ResultOperand analyzeOper(AnaClassArgumentMatching _a, String _op, AnaClassArgumentMatching _b, AnalyzedPageEl _page);

    abstract Argument calculateOperAna(Argument _a, String _op, Argument _b, AnalyzedPageEl _page);

    @Override
    public void quickCalculate(AnalyzedPageEl _page) {
        if (classMethodId != null || !okNum) {
            return;
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        IntTreeMap< String> ops_ = getOperations().getOperators();
        Argument c_ = chidren_.last().getArgument();
        Argument r_;
        r_ = calculateOperAna(a_, ops_.firstValue(), c_, _page);
        if (r_.isNull()) {
            return;
        }
        a_ = r_;
        setSimpleArgumentAna(a_, _page);
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
