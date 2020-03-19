package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.sml.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendStyle extends RendElement {
    RendStyle(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list) {
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        ElementList links_ = ownerDocument_.getElementsByTagName(_cont.getRendKeyWords().getKeyWordLink());
        int len_ = links_.getLength();
        StringList refs_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            Element link_ = links_.item(i);
            String href_ = getCssHref(_cont,link_);
            if (href_ != null) {
                refs_.add(href_);
            }
        }
        StringList filesContents_ = new StringList();
        for (String r: refs_) {
            StringMap<String> files_ = _cont.getAnalyzingDoc().getFiles();
            String file_ = files_.getVal(r);
            if (file_ != null) {
                filesContents_.add(file_);
            }
        }
        appendText(StringList.join(filesContents_, RETURN_LINE),ownerDocument_,curWr_);
    }
}
