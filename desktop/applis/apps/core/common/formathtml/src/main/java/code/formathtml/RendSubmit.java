package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.util.AnalyzingDoc;
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

    private StringMap<String> preformatted;
    RendSubmit(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(_cont.getRendKeyWords().getAttrMessage());
        String value_ = _read.getAttribute(_cont.getRendKeyWords().getAttrMessage());
        int offMessage_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrMessage());
        preformatted = getPre(_cont,value_,offMessage_, _anaDoc, _page);
        if (preformatted.isEmpty()) {
            removeUseLess(_cont,_read, _list);
            return;
        }
        opExp = new StringMap<ResultText>();
        for (EntryCust<String,String> e: preformatted.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(), _read.hasAttribute(_cont.getRendKeyWords().getAttrEscapedAmp())));
        }
        int i_ = CustList.FIRST_INDEX;
        while (_read.hasAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            _list.removeAllString(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            String attribute_ = _read.getAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            int rowsGrId_ = getAttributeDelimiter(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            ResultText r_ = new ResultText();
            r_.build(attribute_,_cont,rowsGrId_,_doc, _anaDoc, _page);
            opExp.addEntry(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)),r_);
            i_++;
        }
        _list.removeAllString(_cont.getRendKeyWords().getAttrValue());
        _list.removeAllString(_cont.getRendKeyWords().getAttrType());
    }

    @Override
    public void reduce(Configuration _context) {
        if (preformatted.isEmpty()) {
            return;
        }
        super.reduce(_context);
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
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrMessage());
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrEscapedAmp());
        StringList objects_ = new StringList();
        for (EntryCust<String,ResultText> e:opExp.entryList()) {
            ResultText r_ = e.getValue();
            objects_.add(ResultText.render(r_.getOpExp(), r_.getTexts(),_cont));
            if (_cont.getContext().hasException()) {
                return;
            }
            curWr_.removeAttribute(e.getKey());
        }
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrValue(), StringList.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrType(), _cont.getRendKeyWords().getValueSubmit());
        ownerDocument_.renameNode(curWr_, _cont.getRendKeyWords().getKeyWordInput());
    }
}
