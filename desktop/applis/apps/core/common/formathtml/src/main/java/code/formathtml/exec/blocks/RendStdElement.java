package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.NavigationCore;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;

public final class RendStdElement extends RendElement implements RendElem {

    public RendStdElement(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText) {
        super(_read, _execAttributes, _execAttributesText, true);
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        Document ownerDocument_ = rw_.getDocument();
        NavigationCore.simpleAppendChild(ownerDocument_, rw_, _nextWrite);
    }
}
