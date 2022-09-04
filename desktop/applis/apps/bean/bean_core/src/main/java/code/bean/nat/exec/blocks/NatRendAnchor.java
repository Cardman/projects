package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.formathtml.Configuration;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class NatRendAnchor extends NatRendElement {
    private final CustList<NatExecOperationNode> opExpAnch;

    private final StringList varNames;
    private final NatExecTextPart textPart;

    public NatRendAnchor(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText,
                         CustList<NatExecOperationNode> _opAnc,
                         StringList _varNames, NatExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        opExpAnch = _opAnc;
        this.varNames = _varNames;
        this.textPart = _textPart;
    }

    void anchor(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack) {
        RendBlockHelp.feed(varNames, opExpAnch, _rendStack);
        RendBlockHelp.processLink(_cont, (Element) _nextWrite, _read, textPart, _rendStack);
    }

}
