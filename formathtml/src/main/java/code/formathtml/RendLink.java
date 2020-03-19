package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.sml.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class RendLink extends RendElement {
    private String content;
    private StringMap<ResultText> opExpTitle;
    RendLink(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list) {
        _list.removeAllString(_cont.getRendKeyWords().getAttrHref());
        _list.removeAllString(_cont.getRendKeyWords().getAttrRel());
        String href_ = getCssHref(_cont,_read);
        opExpTitle = new StringMap<ResultText>();
        if (href_ != null) {
            StringMap<String> files_ = _cont.getAnalyzingDoc().getFiles();
            content = files_.getVal(href_);
            int i_ = CustList.FIRST_INDEX;
            while (_read.hasAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
                _list.removeAllString(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
                String attribute_ = _read.getAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
                int rowsGrId_ = getAttributeDelimiter(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
                ResultText r_ = new ResultText();
                r_.build(attribute_,_cont,rowsGrId_,_doc);
                opExpTitle.addEntry(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)),r_);
                i_++;
            }
        }
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        String fileContent_ = content;
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        if (!opExpTitle.isEmpty()) {
            StringList objects_ = new StringList();
            for (EntryCust<String,ResultText> e:opExpTitle.entryList()) {
                ResultText r_ = e.getValue();
                objects_.add(ResultText.render(r_.getOpExp(), r_.getTexts(),_cont));
                if (_cont.getContext().hasException()) {
                    return;
                }
                curWr_.removeAttribute(e.getKey());
            }
            fileContent_ = StringList.simpleStringsFormat(fileContent_, objects_);
        }
        ElementList heads_ = ownerDocument_.getElementsByTagName(_cont.getRendKeyWords().getKeyWordHead());
        if (fileContent_ != null && heads_.getLength() == CustList.ONE_ELEMENT) {
            Element head_ = heads_.item(CustList.FIRST_INDEX);
            CustList<Element> children_ = new CustList<Element>();
            for (Element c: head_.getChildElements()) {
                if (!StringList.quickEq(c.getTagName(), _cont.getRendKeyWords().getKeyWordStyle())) {
                    continue;
                }
                children_.add(c);
            }
            boolean successAdd_ = children_.isEmpty();
            if (!successAdd_) {
                Element eltStyle_ = children_.last();
                appendText(fileContent_, ownerDocument_, eltStyle_);
            } else {
                Element eltStyle_ = ownerDocument_.createElement(_cont.getRendKeyWords().getKeyWordStyle());
                Text text_ = ownerDocument_.createTextNode(fileContent_);
                eltStyle_.appendChild(text_);
                head_.appendChild(eltStyle_);
            }
        }
    }

}
