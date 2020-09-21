package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
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
    private CustList<RendDynOperationNode> opExpAnch;
    private StringList texts = new StringList();
    private StringList varNames = new StringList();

    private StringMap<ResultText> opExpTitle;

    private StringMap<String> preformatted;
    RendTitledAnchor(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        ResultText res_ = ResultText.buildAnchor(_cont,this, _doc, _read, _list, _anaDoc, _page);
        varNames = res_.getVarNames();
        opExpAnch = res_.getOpExpAnchor();
        opExp = res_.getOpExp();
        texts = res_.getTexts();
        _list.removeAllString(_cont.getRendKeyWords().getAttrValue());
        String value_ = _read.getAttribute(_cont.getRendKeyWords().getAttrValue());
        int offMessage_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrValue());
        preformatted = getPre(_cont,value_,offMessage_, _anaDoc, _page);
        if (preformatted.isEmpty()) {
            removeUseLess(_cont,_read, _list);
            return;
        }
        opExpTitle = new StringMap<ResultText>();
        for (EntryCust<String,String> e: preformatted.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(), _read.hasAttribute(_cont.getRendKeyWords().getAttrEscapedAmp())));
        }
        int i_ = CustList.FIRST_INDEX;
        while (_read.hasAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            _list.removeAllString(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            String attribute_ = _read.getAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            ResultText r_ = new ResultText();
            int rowsGrId_ = getAttributeDelimiter(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            r_.build(attribute_, rowsGrId_,_doc, _anaDoc, _page);
            opExpTitle.addEntry(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)),r_);
            i_++;
        }
        _list.removeAllString(_cont.getRendKeyWords().getAttrTitle());
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
//        ImportingPage ip_ = _cont.getLastPage();
//        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
//        ip_.setLookForAttrValue(true);
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrValue());
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrEscapedAmp());
        StringList objects_ = new StringList();
        for (EntryCust<String,ResultText> e:opExpTitle.entryList()) {
            ResultText r_ = e.getValue();
            objects_.add(ResultText.render(r_.getOpExp(), r_.getTexts(),_cont));
            if (_cont.getContext().hasException()) {
                incrAncNb(_cont, (Element) _nextWrite);
                return;
            }
            curWr_.removeAttribute(e.getKey());
        }
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrTitle(), StringList.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        ownerDocument_.renameNode(curWr_, _cont.getRendKeyWords().getKeyWordAnchor());
        processLink(_cont,curWr_,_read,varNames,opExp,texts, opExpAnch);
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
        ResultText.reduce(opExp);
    }
}
