package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;

public final class RendAnchor extends RendElement {
    private final CustList<RendDynOperationNode> opExpAnch;

    private final StringMap<CustList<RendDynOperationNode>> textPart;

    public RendAnchor(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText,
                      CustList<RendDynOperationNode> _opAnc,
                      StringMap<CustList<RendDynOperationNode>> _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        opExpAnch = _opAnc;
        this.textPart = _textPart;
    }


    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processLink(_cont, (Element) _nextWrite, _read, textPart,opExpAnch, _ctx, _rendStack);
    }

}
