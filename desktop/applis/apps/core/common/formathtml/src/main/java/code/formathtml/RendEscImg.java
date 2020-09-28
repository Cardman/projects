package code.formathtml;

import code.formathtml.exec.blocks.ExecTextPart;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.StringMap;

public final class RendEscImg extends RendElement {

    public RendEscImg(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText) {
        super(_offsetTrim, read, execAttributes, execAttributesText);
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        _nextWrite.getOwnerDocument().renameNode(_nextWrite,_cont.getRendKeyWords().getKeyWordImg());
    }
}
