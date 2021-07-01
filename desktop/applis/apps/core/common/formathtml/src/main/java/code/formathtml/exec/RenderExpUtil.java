package code.formathtml.exec;

import code.expressionlanguage.Argument;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.variables.ArgumentsPair;

import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.*;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.*;

public final class RenderExpUtil {
    private RenderExpUtil() {
    }

    public static Argument calculateReuse(CustList<RendDynOperationNode> _nodes, Argument _arg, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        Argument globalArgument_ = _rendStackCall.getLastPage().getGlobalArgument();
        _rendStackCall.getLastPage().setGlobalArgumentStruct(_arg.getStruct());
        Argument argument_ = calculateReuse(_nodes, _advStandards, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().setGlobalArgumentStruct(globalArgument_.getStruct());
        return argument_;
    }
    public static Argument calculateReuse(CustList<RendDynOperationNode> _nodes, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        IdMap<RendDynOperationNode,ArgumentsPair> arguments_;
        arguments_ = getAllArgs(_nodes, _advStandards, _ctx, _rendStackCall);
        return Argument.getNullableValue(arguments_.lastValue().getArgument());
    }

    public static IdMap<RendDynOperationNode,ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
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
                a_.calculate(arguments_, _advStandards, _ctx, _rendStackCall);
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
        if (_o.getNextSibling() != null&&_pair.isArgumentTest()){
            RendMethodOperation par_ = _o.getParent();
            if (par_ instanceof RendQuickOperation){
                st_ = ((RendQuickOperation)par_).getAbsorbingValue();
            }
            if (par_ instanceof RendCompoundAffectationOperation){
                RendCompoundAffectationOperation p_ = (RendCompoundAffectationOperation) par_;
                st_ = ExpressionLanguage.absCompound(p_,st_);
            }
        }
        return RendDynOperationNode.getNextIndex(_o, st_);
    }

}
