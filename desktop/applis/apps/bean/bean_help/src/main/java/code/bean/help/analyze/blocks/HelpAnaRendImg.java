package code.bean.help.analyze.blocks;

import code.bean.help.analyze.HelpResultText;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.sml.Element;
import code.util.StringList;

public final class HelpAnaRendImg extends HelpAnaRendElement {

    private StringList texts = new StringList();
    HelpAnaRendImg(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        HelpResultText res_ = new HelpResultText();
        String pageName_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrSrc());
        res_.buildAna(pageName_);
        texts = res_.getTexts();
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrSrc());
    }

    public StringList getTexts() {
        return texts;
    }
}
