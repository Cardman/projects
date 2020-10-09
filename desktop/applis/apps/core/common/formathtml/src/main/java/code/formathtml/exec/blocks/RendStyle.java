package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class RendStyle extends RendElement {

    public RendStyle(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText) {
        super(_offsetTrim, read, execAttributes, execAttributesText);
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx) {
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
        appendText(StringUtil.join(filesContents_, RETURN_LINE),ownerDocument_,curWr_);
    }
}
