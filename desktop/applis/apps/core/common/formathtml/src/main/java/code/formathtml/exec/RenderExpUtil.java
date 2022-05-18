package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.opers.CompoundedOperator;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.*;
import code.util.CustList;
import code.util.IdMap;

public final class RenderExpUtil {
    private RenderExpUtil() {
    }

    public static IdMap<RendDynOperationNode,ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, ContextEl _ctx, RendStackCall _rendStackCall) {
        IdMap<RendDynOperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<RendDynOperationNode,ArgumentsPair>();
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
                Argument a_ = Argument.getNullableValue(o.getArgument());
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
        return arguments_;
    }

    private static int getNextIndex(int _max, RendDynOperationNode _o, ArgumentsPair _pair, ContextEl _context, RendStackCall _stackCall) {
        if (_context.callsOrException(_stackCall.getStackCall())) {
            return _max;
        }
        Argument res_ = Argument.getNullableValue(_pair.getArgument());
        Struct st_ = res_.getStruct();
        if (isAncSettable(_o) &&_pair.isArgumentTest()){
            RendMethodOperation par_ = _o.getParent();
            return par_.getOrder();
        }
        return getNextIndex(_o, st_);
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
