package code.bean.help.exec.blocks;

import code.bean.nat.exec.blocks.NatExecTextPart;
import code.bean.nat.exec.blocks.NatRendElem;
import code.sml.Element;
import code.util.StringMap;

public final class HelpRendStdElement extends HelpRendElement implements NatRendElem {

    public HelpRendStdElement(Element _read, StringMap<NatExecTextPart> _execAttributes) {
        super(_read, _execAttributes);
    }

}
