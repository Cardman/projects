package code.bean.help.analyze.blocks;

import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.blocks.NatRendBuildEl;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;

public final class HelpAnaRendMessage extends AnaRendParentBlock implements NatRendBuildEl {

    private final Element elt;

    private StringMap<String> preformatted;
    private StringList varNames = new StringList();

    HelpAnaRendMessage(Element _elt, int _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String value_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        preformatted = AnaRendBlockHelp.getPre(value_, _anaDoc);
//        for (Element n: elt.getChildElements()) {
//            String attribute_ = n.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
//            args.add(AnaRendBlockHelp.EMPTY_STRING);
//            roots.add(NatRenderAnalysis.getRootAnalyzedOperations(attribute_, 0, _anaDoc, _page));
//        }
        //if (!element_.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
        varNames = new StringList();

    }

    public StringList getVarNames() {
        return varNames;
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

}
