package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.symbols.AnaOperCat;
import code.expressionlanguage.analyze.symbols.AnaOperDir;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.symbol.*;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.stds.StandardMethod;

public abstract class ReachOperationNode {

    protected static final String DIFF = "!=";

    protected static final String EMPTY_STRING = "";


    private final OperationNode info;
    private ReachMethodOperation parent;

    private ReachOperationNode nextSibling;


    private ReachPossibleIntermediateDotted siblingSet;

    ReachOperationNode(OperationNode _info) {
        info = _info;
    }

    public static ReachMethodOperation creatReachOperationNode(OperationNode _oper) {
//        if (_oper instanceof VarargOperation) {
//            return new ReachNoopOperation(_oper);
//        }
        if (InvokingOperation.getDeltaCount(_oper) != 0) {
            return new ReachNoopOperation(_oper);
        }
        if (_oper instanceof ArrayFieldOperation) {
            return new ReachArrayFieldOperation(_oper);
        }
        if (_oper instanceof SettableAbstractFieldOperation) {
            return new ReachFieldOperation((SettableAbstractFieldOperation) _oper);
        }
        if (_oper instanceof StaticInfoOperation) {
            return new ReachStaticInfoOperation((StaticInfoOperation) _oper);
        }
        if (_oper instanceof DefaultValueOperation) {
            return new ReachDefaultValueOperation((DefaultValueOperation) _oper);
        }
        if (_oper instanceof RangeOperation) {
            return range(_oper);
        }
        if (_oper instanceof SymbolOperation) {
            SymbolOperation s_ = (SymbolOperation) _oper;
            if (!s_.isOkNum() || s_.getFct().getFunction() != null) {
                return new ReachStdOperation(_oper);
            }
        }
        if (_oper instanceof QuickOperation) {
            QuickOperation s_ = (QuickOperation) _oper;
            if (!s_.isOkNum() || s_.getFct().getFunction() != null) {
                return new ReachStdOperation(_oper);
            }
        }
        return symbolFct(_oper);
    }

    private static ReachMethodOperation range(OperationNode _oper) {
        RangeOperation r_ = (RangeOperation) _oper;
        if (r_.isOkNum()) {
            return new ReachRangeOperation((RangeOperation) _oper);
        }
        return new ReachStdOperation(_oper);
    }

    private static ReachMethodOperation symbolFct(OperationNode _oper) {
        if (_oper instanceof AbstractUnaryOperation) {
            AbstractUnaryOperation a_ = (AbstractUnaryOperation) _oper;
            if (a_.getChildrenNodes().size() == 1) {
                return pureUnary(_oper);
            }
        }
        if (_oper instanceof AbstractTernaryOperation) {
            return new ReachTernaryOperation(_oper);
        }
        if (_oper instanceof AndOperation) {
            return new ReachAndOperation(_oper);
        }
        if (_oper instanceof OrOperation) {
            return new ReachOrOperation(_oper);
        }
        if (_oper instanceof NullSafeOperation) {
            return new ReachNullSafeOperation(_oper);
        }
        if (_oper instanceof CmpOperation) {
            CmpOperation c_ = (CmpOperation) _oper;
            return new ReachCmpOperation(c_, ForwardInfos.cmp(c_));
        }
        if (_oper instanceof EqOperation) {
            EqOperation c_ = (EqOperation) _oper;
            return new ReachNumericOperation(c_,new AnaOperDir(ForwardInfos.eq(c_)));
        }
        if (_oper instanceof NumericOperation) {
            return numeric((NumericOperation) _oper);
        }
        if (_oper instanceof DotOperation) {
            DotOperation d_ = (DotOperation) _oper;
            return new ReachDotOperation(d_);
        }
        if (_oper instanceof AffectationOperation) {
            AffectationOperation d_ = (AffectationOperation) _oper;
            return new ReachAffectationOperation(d_);
        }
        return fct(_oper);
    }

    private static ReachMethodOperation fct(OperationNode _oper) {
        if (_oper instanceof PossibleIntermediateDotted && _oper instanceof AbstractCallFctOperation) {
            AbstractCallFctOperation f_ = (AbstractCallFctOperation) _oper;
            if (f_.getClassMethodId() != null) {
                StandardMethod standardMethod_ = f_.getStandardMethod();
                if (standardMethod_ != null) {
                    if (!f_.isStaticMethod()) {
                        return new ReachInstanceFctStdOperation(standardMethod_, f_, _oper);
                    }
                    return new ReachStaticFctStdOperation(standardMethod_, f_, _oper);
                }
                if (!f_.isStaticMethod()) {
                    boolean int_ = ((PossibleIntermediateDotted) _oper).isIntermediateDottedOperation();
                    return new ReachIntermStdOperation(_oper, int_);
                }
            }
        }
        return defFct(_oper);
    }

    private static ReachMethodOperation defFct(OperationNode _oper) {
        if (_oper instanceof ArrOperation|| _oper instanceof CallDynMethodOperation) {
            return new ReachIntermStdOperation(_oper, true);
        }
        if (_oper instanceof StandardInstancingOperation) {
            StandardInstancingOperation s_ = (StandardInstancingOperation) _oper;
            if (s_.getInstancingCommonContent().getConstId() != null) {
                return new ReachInstancingOperation((StandardInstancingOperation) _oper);
            }
        }
        return new ReachStdOperation(_oper);
    }

    private static ReachMethodOperation pureUnary(OperationNode _oper) {
        if (_oper instanceof IdOperation || _oper instanceof AssocationOperation || _oper instanceof FirstOptOperation) {
            return new ReachIdOperation(_oper);
        }
        if (_oper instanceof NamedArgumentOperation) {
            return new ReachNamedArgumentOperation((NamedArgumentOperation) _oper);
        }
        if (_oper instanceof CastOperation) {
            return new ReachCastOperation((CastOperation) _oper);
        }
        if (_oper instanceof ExplicitOperation) {
            return new ReachExtCastOperation((ExplicitOperation) _oper);
        }
        if (_oper instanceof ImplicitOperation) {
            return new ReachExtCastOperation((ImplicitOperation) _oper);
        }
        if (_oper instanceof UnaryBooleanOperation) {
            return new ReachNumericOperation(_oper, new AnaOperDir(new CommonOperNegBool()));
        }
        if (_oper instanceof UnaryBinOperation) {
            return new ReachNumericOperation(_oper, new AnaOperDir(new CommonOperNegNum()));
        }
        if (_oper instanceof UnaryOperation) {
            return new ReachNumericOperation(_oper, new AnaOperDir(ForwardInfos.unary((UnaryOperation) _oper)));
        }
        return new ReachStdOperation(_oper);
    }

    private static ReachMethodOperation numeric(NumericOperation _num) {
        if (_num instanceof AddOperation) {
            AddOperation c_ = (AddOperation) _num;
            if (c_.isCatString()) {
                return new ReachNumericOperation(c_,new AnaOperCat());
            }
            return new ReachNumericOperation(c_,new AnaOperDir(ForwardInfos.additiveNum(c_)));
        }
        if (_num instanceof MultOperation) {
            MultOperation c_ = (MultOperation) _num;
            return new ReachNumericOperation(c_,new AnaOperDir(ForwardInfos.multiplicative(c_)));
        }
        if (_num instanceof BitOperation) {
            return new ReachNumericOperation(_num,new AnaOperDir(ForwardInfos.bitwise((BitOperation) _num)));
        }
        return new ReachNumericOperation(_num,new AnaOperDir(ForwardInfos.shiftRotate((BitShiftRotateOperation)_num)));
    }

    public final void setRelativeOffsetPossibleAnalyzable(AnalyzedPageEl _page) {
        _page.setOffset(info.getIndexInEl());
    }

    public abstract ReachOperationNode getFirstChild();

    public ReachMethodOperation getParent() {
        return parent;
    }

    public void setParent(ReachMethodOperation _parent) {
        this.parent = _parent;
    }

    public ReachOperationNode getNextSibling() {
        return nextSibling;
    }

    public void setNextSibling(ReachOperationNode _nextSibling) {
        this.nextSibling = _nextSibling;
    }

    public final Argument getArgument() {
        return getInfo().getArgument();
    }

    public final void setSimpleArgument(Argument _argument) {
        getInfo().setSimpleArgument(_argument);
    }

    public final void setSimpleArgumentAna(Argument _argument) {
        setArgAna(this, _argument);
    }
    private static void setArgAna(ReachOperationNode _op, Argument _argument) {
        setNextArgument(_op, _argument);
        byte unwrapObjectNb_ = _op.info.getResultClass().getUnwrapObjectNb();
        if (unwrapObjectNb_ > -1) {
            if (_argument.isNull()) {
                return;
            }
            _argument.setStruct(NumParsers.unwrapObject(unwrapObjectNb_, _argument.getStruct()));
        }
        _op.setSimpleArgument(_argument);
    }

    static void setNextArgument(ReachOperationNode _op, Argument _argument) {
        ReachPossibleIntermediateDotted n_ = _op.getSiblingSet();
        if (n_ != null) {
            n_.setPreviousArgument(_argument);
        }
    }

    public ReachPossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    void checkNull(Argument _arg, AnalyzedPageEl _page) {
        if (Argument.isNullValue(_arg)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFile(_page.getCurrentFile());
            static_.setIndexFile(_page);
            //leaf or header parent or first operator
            static_.buildError(_page.getAnalysisMessages().getNullValue(),
                    _page.getAliasNullPe());
            _page.getLocalizer().addError(static_);
            getInfo().addErr(static_.getBuiltError());
        }
    }
    public void setSiblingSet(ReachPossibleIntermediateDotted _siblingSet) {
        this.siblingSet = _siblingSet;
    }

    public OperationNode getInfo() {
        return info;
    }
    public AnaClassArgumentMatching getResultClass() {
        return info.getResultClass();
    }

    public int getIndexChild() {
        return info.getIndexChild();
    }

    public int getOrder() {
        return info.getOrder();
    }
}
