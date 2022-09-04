package code.bean.nat.exec.blocks;

import code.formathtml.exec.blocks.RendElem;
import code.sml.Element;
import code.util.StringMap;

public final class NatRendStdElement extends NatRendElement implements RendElem {

    public NatRendStdElement(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText) {
        super(_read, _execAttributes, _execAttributesText);
    }

}
