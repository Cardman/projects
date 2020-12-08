package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendCallDynMethodOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private String fctName;
    public RendCallDynMethodOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, String _fctName, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        fctName = _fctName;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        if (StringUtil.quickEq(fctName, _context.getStandards().getContent().getReflect().getAliasMetaInfo())) {
            Argument res_ = ExecInvokingOperation.getMetaInfo(previous_, _context);
            setSimpleArgument(res_, _conf, _nodes, _context);
            return;
        }
        if (StringUtil.quickEq(fctName, _context.getStandards().getContent().getReflect().getAliasInstance())) {
            Argument res_ = ExecInvokingOperation.getInstanceCall(previous_, _context);
            setSimpleArgument(res_, _conf, _nodes, _context);
            return;
        }
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(getArgument(_conf,previous_, _nodes, _context), _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    public Argument getArgument(Configuration _conf,Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context) {
        return ExecInvokingOperation.prepareCallDynNormal(_previous, fectchPosArgs(_all), _context);
    }

    private CustList<ArgumentsPair> fectchPosArgs(IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        CustList<ArgumentsPair> out_ = new CustList<ArgumentsPair>();
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        for (RendDynOperationNode o: chidren_) {
            ArgumentsPair a_ = new ArgumentsPair();
            if (o instanceof RendWrappOperation) {
                a_.setWrapper(getArgumentPair(_nodes,o).getWrapper());
            } else {
                a_.setArgument(getArgumentPair(_nodes,o).getArgument());
            }
            out_.add(a_);
        }
        return out_;
    }
}
