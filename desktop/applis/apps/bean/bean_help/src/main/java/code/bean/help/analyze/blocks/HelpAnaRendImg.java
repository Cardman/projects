package code.bean.help.analyze.blocks;

import code.bean.help.analyze.HelpResultText;
import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.blocks.NatAnaRendDocumentBlock;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.sml.Element;
import code.util.StringList;

public final class HelpAnaRendImg extends HelpAnaRendElement {

    private StringList texts = new StringList();
    HelpAnaRendImg(Element _elt) {
        super(_elt);
    }

    @Override
    protected void processAttributes(NatAnaRendDocumentBlock _doc, Element _read, StringList _list, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
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
