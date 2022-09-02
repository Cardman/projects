package code.bean.help.exec.blocks;

import code.bean.nat.exec.blocks.NatExecTextPart;
import code.formathtml.exec.blocks.RendElem;
import code.sml.Element;
import code.util.StringMap;

public final class HelpRendStdElement extends HelpRendElement implements RendElem {

    public HelpRendStdElement(Element _read, StringMap<NatExecTextPart> _execAttributes) {
        super(_read, _execAttributes);
    }

}
