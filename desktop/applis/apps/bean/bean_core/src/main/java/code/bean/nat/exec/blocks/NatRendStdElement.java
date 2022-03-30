package code.bean.nat.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendStdEl;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendStdElement extends NatRendElement implements RendStdEl {

    public NatRendStdElement(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText) {
        super(_read, _execAttributes, _execAttributesText);
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, RendStackCall _rendStack) {
        //
    }
}
