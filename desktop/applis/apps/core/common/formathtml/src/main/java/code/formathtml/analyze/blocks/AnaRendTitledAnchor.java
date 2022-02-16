package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendTitledAnchor extends AnaRendElement {
    private CustList<OperationNode> roots;
    private OperationNode root;
    private StringList texts = new StringList();
    private StringList varNames = new StringList();

    private StringMap<ResultText> opExpTitle;

    private StringMap<String> preformatted;
    AnaRendTitledAnchor(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        roots = new CustList<OperationNode>();
        ResultText res_ = ResultText.buildAnchor(this, _read, _list, _anaDoc, _page);
        varNames = res_.getVarNames();
        root = res_.getOpExpAnchorRoot();
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        String value_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        int offMessage_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrValue());
        preformatted = getPre(value_,offMessage_, _anaDoc, _page);
        opExpTitle = new StringMap<ResultText>();
        if (preformatted.isEmpty()) {
            removeUseLess(_read, _list, _anaDoc.getRendKeyWords());
            return;
        }
        for (EntryCust<String,String> e: preformatted.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(), _read.hasAttribute(_anaDoc.getRendKeyWords().getAttrEscapedAmp())));
        }
        int i_ = IndexConstants.FIRST_INDEX;
        while (_read.hasAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            _list.removeAllString(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            String attribute_ = _read.getAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            ResultText r_ = new ResultText();
            int rowsGrId_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            r_.buildIdAna(attribute_, rowsGrId_, _anaDoc, _page);
            opExpTitle.addEntry(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)),r_);
            i_++;
        }
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrTitle());
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

    public StringMap<ResultText> getOpExpTitle() {
        return opExpTitle;
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public StringList getVarNames() {
        return varNames;
    }

    public OperationNode getRoot() {
        return root;
    }
}
