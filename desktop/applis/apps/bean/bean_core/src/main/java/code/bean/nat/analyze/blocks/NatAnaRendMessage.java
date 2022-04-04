package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class NatAnaRendMessage extends NatAnaRendParentBlock implements NatRendBuildEl {

    private final Element elt;
    private CustList<NatOperationNode> roots;

    private StringMap<String> preformatted;
    private final StringMap<CustList<NatOperationNode>> callsRoots = new StringMap<CustList<NatOperationNode>>();
    private final StringList args = new StringList();
    private StringList varNames = new StringList();

    NatAnaRendMessage(Element _elt) {
        super();
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        roots = new CustList<NatOperationNode>();
        String value_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        preformatted = AnaRendBlockHelp.getPre(value_, _anaDoc);
        for (Element n: elt.getChildElements()) {
            String attribute_ = n.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
            args.add(AnaRendBlockHelp.EMPTY_STRING);
            roots.add(NatRenderAnalysis.getRootAnalyzedOperations(attribute_, 0, _anaDoc, _page));
        }
        //if (!element_.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
        int l_ = roots.size();
        StringList varNames_ = new StringList();
        for (int i = 0; i< l_; i++) {
            String varLoc_ = AnaRendBlockHelp.lookForVar(varNames_);
            varNames_.add(varLoc_);
        }
        varNames = varNames_;
        for (EntryCust<String,String> e: preformatted.entryList()) {
            callsRoots.addEntry(e.getKey(),new CustList<NatOperationNode>());
        }

    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }

    public StringMap<CustList<NatOperationNode>> getCallsRoots() {
        return callsRoots;
    }

    public StringList getVarNames() {
        return varNames;
    }

    public StringList getArgs() {
        return args;
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

}
