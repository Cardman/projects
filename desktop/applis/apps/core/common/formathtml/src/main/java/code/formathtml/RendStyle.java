package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.util.AnalyzingDoc;
import code.sml.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendStyle extends RendElement {
    RendStyle(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
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
            StringMap<String> files_ = _cont.getFiles();
            String file_ = files_.getVal(r);
            if (file_ != null) {
                filesContents_.add(file_);
            }
        }
        appendText(StringList.join(filesContents_, RETURN_LINE),ownerDocument_,curWr_);
    }
}
