package code.bean.nat.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendLink;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendLink extends NatRendElement {
    private final String content;

    public NatRendLink(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, String _content) {
        super(_read, _execAttributes, _execAttributesText);
        this.content = _content;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, RendStackCall _rendStack) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        RendLink.procLink(_cont, content,ownerDocument_);
    }

}
