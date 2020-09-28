package code.formathtml.exec;

import code.expressionlanguage.Argument;

import code.expressionlanguage.exec.variables.ArgumentsPair;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.opers.*;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public final class RenderExpUtil {
    private RenderExpUtil() {
    }

    public static Argument calculateReuse(CustList<RendDynOperationNode> _nodes, Configuration _context, Argument _arg) {
        Argument globalArgument_ = _context.getLastPage().getGlobalArgument();
        _context.getLastPage().setGlobalArgumentStruct(_arg.getStruct(),_context);
        Argument argument_ = calculateReuse(_nodes, _context);
        _context.getLastPage().setGlobalArgumentStruct(globalArgument_.getStruct(),_context);
        return argument_;
    }
    public static Argument calculateReuse(CustList<RendDynOperationNode> _nodes, Configuration _context) {
        IdMap<RendDynOperationNode,ArgumentsPair> arguments_;
        arguments_ = getAllArgs(_nodes,_context);
        return Argument.getNullableValue(arguments_.lastValue().getArgument());
    }

    public static IdMap<RendDynOperationNode,ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, Configuration _context) {
        IdMap<RendDynOperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<RendDynOperationNode,ArgumentsPair>();
        for (RendDynOperationNode o: _nodes) {
            ArgumentsPair a_ = new ArgumentsPair();
            a_.setArgument(o.getArgument());
            a_.setImplicits(o.getImplicits());
            a_.setImplicitsTest(o.getImplicitsTest());
            arguments_.addEntry(o, a_);
        }
        int fr_ = 0;
        int len_ = _nodes.size();
        while (fr_ < len_) {
            RendDynOperationNode o = arguments_.getKey(fr_);
            ArgumentsPair pair_ = arguments_.getValue(fr_);
            if (!(o instanceof RendCalculableOperation)) {
                Argument a_ = Argument.getNullableValue(o.getArgument());
                o.setSimpleArgument(a_,_context,arguments_);
                fr_ = getNextIndex(_context,len_,o, pair_);
                continue;
            }
            if (pair_.getArgument() != null) {
                o.setSimpleArgument(pair_.getArgument(),_context,arguments_);
                fr_ = getNextIndex(_context,len_,o, pair_);
                continue;
            }
            RendCalculableOperation a_ = (RendCalculableOperation)o;
            a_.calculate(arguments_, _context);
            fr_ = getNextIndex(_context,len_,o, pair_);
        }
        return arguments_;
    }

    private static int getNextIndex(Configuration _conf, int _max,RendDynOperationNode o, ArgumentsPair pair_) {
        if (_conf.getContext().hasException()) {
            return _max;
        }
        Argument res_ = Argument.getNullableValue(pair_.getArgument());
        Struct st_ = res_.getStruct();
        if (o.getNextSibling() != null&&pair_.isArgumentTest()){
            RendMethodOperation par_ = o.getParent();
            if (par_ instanceof RendAndOperation){
                st_ = BooleanStruct.of(false);
            }
            if (par_ instanceof RendOrOperation){
                st_ = BooleanStruct.of(true);
            }
            if (par_ instanceof RendCompoundAffectationOperation){
                RendCompoundAffectationOperation p_ = (RendCompoundAffectationOperation) par_;
                if (StringList.quickEq(p_.getOper(),"&&=")) {
                    st_ = BooleanStruct.of(false);
                }
                if (StringList.quickEq(p_.getOper(),"||=")) {
                    st_ = BooleanStruct.of(true);
                }
            }
        }
        return RendDynOperationNode.getNextIndex(o, st_);
    }

}
