package code.bean.help.analyze.blocks;

import code.bean.nat.analyze.blocks.*;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringMap;

public final class HelpAnaRendMessage extends NatAnaRendParentBlock implements NatRendBuildEl {

    private final Element elt;

    private StringMap<String> preformatted;

    HelpAnaRendMessage(Element _elt) {
        super();
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String value_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        preformatted = AnaRendBlockHelp.getPre(value_, _anaDoc);

    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

}
