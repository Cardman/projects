package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.exec.RendDynOperationNode;
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
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list) {
        ResultText res_ = new ResultText();
        String pageName_ = _read.getAttribute(ATTRIBUTE_SRC);
        res_.build(pageName_,_cont,_doc);
        opExp = res_.getOpExp();
        texts = res_.getTexts();
        _list.removeAllString(ATTRIBUTE_SRC);
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        String pageName_ = ResultText.render(opExp,texts,_cont);
        if (_cont.getContext().getException() != null) {
            return;
        }
        String lg_ = _cont.getCurrentLanguage();
        String link_ = RendExtractFromResources.getRealFilePath(lg_,pageName_);
        String file_ = _cont.getAnalyzingDoc().getFiles().getVal(link_);
        if (file_ == null) {
            return;
        }
        ((Element)_nextWrite).setAttribute(ATTRIBUTE_SRC,file_);
    }
}
