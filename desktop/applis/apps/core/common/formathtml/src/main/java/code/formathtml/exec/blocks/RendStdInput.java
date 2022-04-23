package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefFieldUpdates;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;

public final class RendStdInput extends RendInput {

    public RendStdInput(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText,
                        CustList<RendDynOperationNode> _opsValue,
                        CustList<RendDynOperationNode> _opsConverterField,
                        DefFieldUpdates _f) {
        super(_read, _execAttributes, _execAttributesText, _opsValue, _opsConverterField, _f);
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        DefFetchedObjs def_ = processIndexes(_cont, _read, (Element) _nextWrite, _ctx, _rendStack, new CustList<RendDynOperationNode>());
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        prStack(_cont,(Element) _nextWrite,def_,_rendStack.getLastPage().getGlobalArgument(),_rendStack);
    }
}
