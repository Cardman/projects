package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.NatResultInput;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.sml.Element;

public final class NatAnaRendSelect extends NatAnaRendParentBlock implements NatRendBuildEl {
    private NatOperationNode rootRead;
    private NatOperationNode rootValue;
    private NatOperationNode rootMap;
    private final Element elt;
    private String classNameNat = AnaRendBlockHelp.EMPTY_STRING;
    private NatResultInput resultInput;

    NatAnaRendSelect(Element _elt) {
        super();
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatResultInput r_ = new NatResultInput();
        r_.build(elt,_anaDoc.getRendKeyWords().getAttrVarValue(), _anaDoc, _page);
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        resultInput = r_;
        classNameNat = r_.getClassNameNat();
        String map_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrMap());
        rootMap = NatRenderAnalysis.getRootAnalyzedOperations(map_, 0, _anaDoc, _page);
    }

    public NatOperationNode getRootValue() {
        return rootValue;
    }

    public NatOperationNode getRootRead() {
        return rootRead;
    }

    public String getClassNameNat() {
        return classNameNat;
    }

    public Element getElt() {
        return elt;
    }

    public NatOperationNode getRootMap() {
        return rootMap;
    }

    public NatResultInput getResultInput() {
        return resultInput;
    }

}
