package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.sml.*;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class NatRendLink extends NatRendElement {
    private final String content;

    public NatRendLink(Element _read, StringMap<NatExecTextPart> _execAttributes, String _content) {
        super(_read, _execAttributes);
        this.content = StringUtil.nullToEmpty(_content);
    }

    void link(NatConfigurationCore _cont, Node _nextWrite) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        ElementList heads_ = ownerDocument_.getElementsByTagName(_cont.getRendKeyWords().getKeyWordsTags().getKeyWordHead());
        if (heads_.getLength() == 0) {
            return;
        }
        Element head_ = heads_.item(IndexConstants.FIRST_INDEX);
        NavigationCore.prHeader(_cont.getRendKeyWords().getKeyWordsTags(), content,ownerDocument_,head_);
    }

}
