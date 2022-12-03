package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class NatRendAnchor extends NatRendElementForm {
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

    void anchor(NatConfigurationCore _cont, Node _nextWrite, NatRendStackCall _rendStack) {
        NatRendElementForm.feed(varNames, opExpAnch, _rendStack);
        NatRendElementForm.processLink(_cont, (Element) _nextWrite, textPart, _rendStack);
    }

}
