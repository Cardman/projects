package code.bean.nat.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class NatRendAnchor extends NatRendElement {
    private final CustList<RendDynOperationNode> opExpAnch;

    private final StringList varNames;
    private final ExecTextPart textPart;

    public NatRendAnchor(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText,
                         CustList<RendDynOperationNode> _opAnc,
                         StringList _varNames, ExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        opExpAnch = _opAnc;
        this.varNames = _varNames;
        this.textPart = _textPart;
    }


    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, RendStackCall _rendStack) {
        RendBlockHelp.feed(varNames, opExpAnch, _rendStack);
        RendBlockHelp.processLink(_cont, (Element) _nextWrite, _read, textPart, _stds, _rendStack);
    }

}
