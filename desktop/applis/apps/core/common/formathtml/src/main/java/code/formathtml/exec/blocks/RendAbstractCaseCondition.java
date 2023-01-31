package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecFilterContent;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendAbstractCaseCondition extends RendParentBlock implements RendWithEl {

    private final RendOperationNodeListOff exp;
    private final ExecFilterContent content;

    public RendAbstractCaseCondition(CustList<RendDynOperationNode> _list, int _offset, String _c, String _v, CustList<Argument> _stdValues, CustList<ClassField> _enumValues) {
        exp = new RendOperationNodeListOff(_list, _offset);
        content = new ExecFilterContent(_c, _v, _stdValues, _enumValues);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getFirstChild());
        setVisited(ip_,this);
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        if (!content.getVariableName().isEmpty()) {
            _ip.removeRefVar(content.getVariableName());
        }
    }
    public RendOperationNodeListOff getExp() {
        return exp;
    }

    public ExecFilterContent getContent() {
        return content;
    }
}
