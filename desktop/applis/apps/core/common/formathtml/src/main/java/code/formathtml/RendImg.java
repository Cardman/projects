package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;

public final class RendImg extends RendElement {

    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();

    RendImg(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultText res_ = new ResultText();
        String pageName_ = _read.getAttribute(_cont.getRendKeyWords().getAttrSrc());
        int rowsGrId_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrSrc());
        res_.build(pageName_,_cont,rowsGrId_,_doc, _anaDoc, _page);
        opExp = res_.getOpExp();
        texts = res_.getTexts();
        _list.removeAllString(_cont.getRendKeyWords().getAttrSrc());
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        String pageName_ = ResultText.render(opExp,texts,_cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        String lg_ = _cont.getCurrentLanguage();
        String link_ = RendExtractFromResources.getRealFilePath(lg_,pageName_);
        String file_ = _cont.getFiles().getVal(link_);
        if (file_ == null) {
            return;
        }
        ((Element)_nextWrite).setAttribute(_cont.getRendKeyWords().getAttrSrc(),file_);
    }
}
