package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendEscImg extends NatRendElement {

    public NatRendEscImg(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText) {
        super(_read, _execAttributes, _execAttributesText);
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, NatRendStackCall _rendStack) {
        _nextWrite.getOwnerDocument().renameNode(_nextWrite,_cont.getRendKeyWords().getKeyWordImg());
    }
}
