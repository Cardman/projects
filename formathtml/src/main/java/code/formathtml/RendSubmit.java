package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class RendSubmit extends RendElement {

    private StringMap<ResultText> opExp;

    private String preformatted;
    RendSubmit(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _all, StringList _list) {
        _list.removeAllString(ATTRIBUTE_VALUE_SUBMIT);
        String value_ = _read.getAttribute(ATTRIBUTE_VALUE_SUBMIT);
        preformatted = getPre(_cont,value_);
        if (preformatted == null) {
            return;
        }
        opExp = new StringMap<ResultText>();
        preformatted = DocumentBuilder.transformSpecialChars(preformatted, _read.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
        int i_ = CustList.FIRST_INDEX;
        while (_read.hasAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)))) {
            _list.removeAllString(StringList.concat(TAG_PARAM,Long.toString(i_)));
            String attribute_ = _read.getAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
            ResultText r_ = new ResultText();
            r_.build(attribute_,_cont,_doc);
            opExp.addEntry(StringList.concat(TAG_PARAM,Long.toString(i_)),r_);
            i_++;
        }
        _list.removeAllString(ATTRIBUTE_VALUE);
        _list.removeAllString(ATTRIBUTE_TYPE);
    }

    @Override
    public void reduce(Configuration _context) {
        if (preformatted == null) {
            return;
        }
        for (EntryCust<String,ResultText> e:opExp.entryList()) {
            ResultText.reduce(e.getValue().getOpExp());
        }
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
//        ImportingPage ip_ = _cont.getLastPage();
//        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
//        ip_.setLookForAttrValue(true);
        curWr_.removeAttribute(ATTRIBUTE_VALUE_SUBMIT);
        curWr_.removeAttribute(ATTRIBUTE_ESCAPED_EAMP);
        StringList objects_ = new StringList();
        for (EntryCust<String,ResultText> e:opExp.entryList()) {
            ResultText r_ = e.getValue();
            objects_.add(ResultText.render(r_.getOpExp(), r_.getTexts(),_cont));
            if (_cont.getContext().hasExceptionOrFailInit()) {
                return;
            }
            curWr_.removeAttribute(e.getKey());
        }
        curWr_.setAttribute(ATTRIBUTE_VALUE, StringList.simpleStringsFormat(preformatted, objects_));
        curWr_.setAttribute(ATTRIBUTE_TYPE, SUBMIT_TYPE);
        ownerDocument_.renameNode(curWr_, INPUT_TAG);
    }
}
