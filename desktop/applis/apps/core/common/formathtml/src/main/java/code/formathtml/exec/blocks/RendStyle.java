package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class RendStyle extends RendElement {

    public RendStyle(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText) {
        super(_read, _execAttributes, _execAttributesText,true);
    }

    @Override
    protected boolean processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        ElementList links_ = ownerDocument_.getElementsByTagName(_cont.getRendKeyWords().getKeyWordLink());
        int len_ = links_.getLength();
        StringList refs_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            Element link_ = links_.item(i);
            String href_ = getCssHref(_cont,link_);
            if (href_ != null) {
                refs_.add(href_);
            }
        }
        StringList filesContents_ = new StringList();
        for (String r: refs_) {
            StringMap<String> files_ = _cont.getFiles();
            String file_ = files_.getVal(r);
            if (file_ != null) {
                filesContents_.add(file_);
            }
        }
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        NavigationCore.simpleAppendChild(ownerDocument_, rw_, _nextWrite);
        NavigationCore.appendText(StringUtil.join(filesContents_, RETURN_LINE),ownerDocument_,curWr_);
        return _ctx.callsOrException(_rendStack.getStackCall());
    }
}
