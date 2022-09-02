package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendElem;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendStdElement extends NatRendElement implements RendElem {

    public NatRendStdElement(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText) {
        super(_read, _execAttributes, _execAttributesText);
    }

    @Override
    protected NatParentBlock processExecAttr(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack) {
        return this;
    }
}
