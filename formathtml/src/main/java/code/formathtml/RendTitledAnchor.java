package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.exec.RendDynOperationNode;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class RendTitledAnchor extends RendElement {
    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();

    private StringMap<ResultText> opExpTitle;

    private String preformatted;
    RendTitledAnchor(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _all, StringList _list) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        ResultText res_ = ResultText.buildAnchor(_cont, _doc, _read, _all, _list);
        opExp = res_.getOpExp();
        texts = res_.getTexts();
        _list.removeAllString(ATTRIBUTE_VALUE);
        String value_ = _read.getAttribute(ATTRIBUTE_VALUE);
        preformatted = getPre(_cont,value_);
        if (preformatted == null) {
            return;
        }
        opExpTitle = new StringMap<ResultText>();
        preformatted = DocumentBuilder.transformSpecialChars(preformatted, _read.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
        int i_ = CustList.FIRST_INDEX;
        while (_read.hasAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)))) {
            _list.removeAllString(StringList.concat(TAG_PARAM,Long.toString(i_)));
            String attribute_ = _read.getAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
            ResultText r_ = new ResultText();
            r_.build(attribute_,_cont,_doc);
            opExpTitle.addEntry(StringList.concat(TAG_PARAM,Long.toString(i_)),r_);
            i_++;
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
        curWr_.removeAttribute(ATTRIBUTE_VALUE);
        curWr_.removeAttribute(ATTRIBUTE_ESCAPED_EAMP);
        StringList objects_ = new StringList();
        for (EntryCust<String,ResultText> e:opExpTitle.entryList()) {
            ResultText r_ = e.getValue();
            objects_.add(ResultText.render(r_.getOpExp(), r_.getTexts(),_cont));
            if (_cont.getContext().hasExceptionOrFailInit()) {
                _cont.getAnchorsNames().add(EMPTY_STRING);
                incrAncNb(_cont, (Element) _nextWrite);
                return;
            }
            curWr_.removeAttribute(e.getKey());
        }
        curWr_.setAttribute(ATTRIBUTE_TITLE, StringList.simpleStringsFormat(preformatted, objects_));
        ownerDocument_.renameNode(curWr_, TAG_A);
        processLink(_cont,curWr_,_read,varNames,opExp,texts);
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
        ResultText.reduce(opExp);
    }
}
