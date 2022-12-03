package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.formathtml.exec.blocks.RendLink;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendLink extends NatRendElement {
    private final String content;

    public NatRendLink(Element _read, StringMap<NatExecTextPart> _execAttributes, String _content) {
        super(_read, _execAttributes);
        this.content = _content;
    }

    void link(NatConfigurationCore _cont, Node _nextWrite) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        RendLink.procLink(_cont.getRendKeyWords().getKeyWordsTags(), content,ownerDocument_);
    }

}
