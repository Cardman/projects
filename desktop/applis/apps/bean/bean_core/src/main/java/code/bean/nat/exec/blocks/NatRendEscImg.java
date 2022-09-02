package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendEscImg extends NatRendElement {

    public NatRendEscImg(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText) {
        super(_read, _execAttributes, _execAttributesText);
    }

    @Override
    protected NatParentBlock processExecAttr(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack) {
        _nextWrite.getOwnerDocument().renameNode(_nextWrite,_cont.getRendKeyWords().getKeyWordImg());
        return this;
    }
}
