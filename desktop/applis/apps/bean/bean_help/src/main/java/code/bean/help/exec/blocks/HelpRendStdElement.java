package code.bean.help.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatExecTextPart;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendStdEl;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class HelpRendStdElement extends HelpRendElement implements RendStdEl {

    public HelpRendStdElement(Element _read, StringMap<NatExecTextPart> _execAttributes) {
        super(_read, _execAttributes);
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack) {
        //
    }
}
