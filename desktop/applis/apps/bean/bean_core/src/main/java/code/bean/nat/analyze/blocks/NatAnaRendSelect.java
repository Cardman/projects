package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatResultInput;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendBuildEl;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.formathtml.util.InputInfo;
import code.sml.Element;

public final class NatAnaRendSelect extends AnaRendParentBlock implements AnaRendBuildEl {
    private NatOperationNode rootRead;
    private NatOperationNode rootValue;
    private NatOperationNode rootMap;
    private String varName = AnaRendBlockHelp.EMPTY_STRING;
    private InputInfo varNames = new InputInfo();
    private String id = AnaRendBlockHelp.EMPTY_STRING;
    private String idClass = AnaRendBlockHelp.EMPTY_STRING;
    private String idName = AnaRendBlockHelp.EMPTY_STRING;
    private final Element elt;
    private String className = AnaRendBlockHelp.EMPTY_STRING;
    private NatResultInput resultInput;

    NatAnaRendSelect(Element _elt, int _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        NatResultInput r_ = new NatResultInput();
        r_.build(elt,_anaDoc.getRendKeyWords().getAttrVarValue(), _anaDoc, _page);
        varNames = r_.getVarNamesParams();
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        varName = r_.getVarName();
        resultInput = r_;
        id = r_.getId();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        className = r_.getClassName();
        String map_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrMap());
        rootMap = NatRenderAnalysis.getRootAnalyzedOperations(map_, 0, _anaDoc, _page);
    }

    public NatOperationNode getRootValue() {
        return rootValue;
    }

    public NatOperationNode getRootRead() {
        return rootRead;
    }

    public String getVarName() {
        return varName;
    }

    public String getId() {
        return id;
    }

    public String getIdName() {
        return idName;
    }

    public String getIdClass() {
        return idClass;
    }

    public String getClassName() {
        return className;
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

    public InputInfo getVarNames() {
        return varNames;
    }
}
