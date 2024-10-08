package code.formathtml.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.opers.CompoundedOperator;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.*;
import code.util.CustList;
import code.util.IdMap;

public final class RenderExpUtil {
    private RenderExpUtil() {
    }

    public static Struct getFinalArg(CustList<RendDynOperationNode> _nodes, ContextEl _ctx, RendStackCall _rendStackCall) {
        IdMap<RendDynOperationNode, ArgumentsPair> all_ = getAllArgs(_nodes, _ctx, _rendStackCall);
        if (all_.isEmpty()) {
            return null;
        }
        return ArgumentListCall.getNull(all_.lastValue().getArgument());
    }
    public static IdMap<RendDynOperationNode,ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, ContextEl _ctx, RendStackCall _rendStackCall) {
        IdMap<RendDynOperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<RendDynOperationNode,ArgumentsPair>();
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return arguments_;
        }
        for (RendDynOperationNode o: _nodes) {
            ArgumentsPair a_ = new ArgumentsPair();
            a_.setArgument(o.getArgument());
            arguments_.addEntry(o, a_);
        }
        int fr_ = 0;
        int len_ = _nodes.size();
        while (fr_ < len_) {
            RendDynOperationNode o = arguments_.getKey(fr_);
            o.setRelativeOffsetPossibleLastPage(_rendStackCall);
            ArgumentsPair pair_ = arguments_.getValue(fr_);
            if (!(o instanceof RendCalculableOperation)) {
                Struct a_ = ArgumentListCall.getNull(o.getArgument());
                o.setSimpleArgument(a_, arguments_, _ctx, _rendStackCall);
                fr_ = getNextIndex(len_,o, pair_, _ctx, _rendStackCall);
            } else if (pair_.getArgument() != null) {
                o.setSimpleArgument(pair_.getArgument(), arguments_, _ctx, _rendStackCall);
                fr_ = getNextIndex(len_,o, pair_, _ctx, _rendStackCall);
            } else {
                RendCalculableOperation a_ = (RendCalculableOperation)o;
                a_.calculate(arguments_, _ctx, _rendStackCall);
                fr_ = getNextIndex(len_,o, pair_, _ctx, _rendStackCall);
            }
        }
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            arguments_.clear();
        }
        return arguments_;
    }

    private static int getNextIndex(int _max, RendDynOperationNode _o, ArgumentsPair _pair, ContextEl _context, RendStackCall _stackCall) {
        if (_context.callsOrException(_stackCall.getStackCall())) {
            return _max;
        }
        Struct res_ = ArgumentListCall.getNull(_pair.getArgument());
        if (isAncSettable(_o) &&_pair.isArgumentTest()){
            RendMethodOperation par_ = _o.getParent();
            return par_.getOrder();
        }
        return getNextIndex(_o, res_);
    }
    private static boolean isAncSettable(RendDynOperationNode _oper) {
        RendMethodOperation par_ = _oper.getParent();
        if (par_ instanceof CompoundedOperator && !(par_ instanceof RendCompoundAffectationOperation)){
            return par_.getFirstChild() == _oper;
        }
        return par_ instanceof RendCompoundAffectationOperation && _oper == ((RendAbstractAffectOperation) par_).getSettableAnc();
    }

    private static int getNextIndex(RendDynOperationNode _operation, Struct _value) {
        int index_ = _operation.getIndexChild();
        RendMethodOperation par_ = _operation.getParent();
        if (ExpressionLanguage.safeDotShort(_value,index_,par_ instanceof RendSafeDotOperation)) {
            RendDynOperationNode last_ = par_.getChildrenNodes().last();
            if (!(last_ instanceof RendAbstractLambdaOperation)) {
                return shortCutNul(par_, last_, par_.getOrder());
            }
        }
        if (par_ instanceof RendRefTernaryOperation) {
            if (index_ == 1) {
                return par_.getOrder();
            }
            if (index_ == 0 && BooleanStruct.isFalse(_value)) {
                return RendDynOperationNode.getOrder(_operation.getNextSibling()) + 1;
            }
        }
        return _operation.getOrder() + 1;
    }

    private static int shortCutNul(RendMethodOperation _par, RendDynOperationNode _last, int _order) {
        RendMethodOperation p_ = _par;
        while (p_ != null) {
            RendDynOperationNode set_ = null;
            if (p_ instanceof RendAbstractAffectOperation) {
                set_ = ((RendAbstractAffectOperation) p_).getSettable();
            }
            if (set_ == _last) {
                return p_.getOrder();
            }
            p_ = p_.getParent();
        }
        return _order;
    }
}
