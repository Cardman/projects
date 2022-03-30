package code.bean.help.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendStdEl;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class HelpRendStdElement extends HelpRendElement implements RendStdEl {

    public HelpRendStdElement(Element _read, StringMap<ExecTextPart> _execAttributes) {
        super(_read, _execAttributes);
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, NatRendStackCall _rendStack) {
        //
    }
}
