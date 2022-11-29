package code.bean.nat.exec.blocks;

import code.formathtml.Configuration;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendEscImg extends NatRendElement {

    public NatRendEscImg(Element _read, StringMap<NatExecTextPart> _execAttributes) {
        super(_read, _execAttributes);
    }

    void escImg(Configuration _cont, Node _nextWrite) {
        _nextWrite.getOwnerDocument().renameNode(_nextWrite,_cont.getRendKeyWords().getKeyWordImg());
    }
}
