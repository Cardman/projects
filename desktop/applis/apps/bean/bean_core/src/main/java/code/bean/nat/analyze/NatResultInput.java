package code.bean.nat.analyze;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.opers.AffectationNatOperation;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.analyze.opers.NatSettableElResult;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;

public final class NatResultInput {

    private static final String EMPTY_STRING = "";

    private NatOperationNode opsReadRoot;
    private NatOperationNode opsValueRoot;
    private String classNameNat = EMPTY_STRING;
    private NatOperationNode settable;

    public void build(Element _read, String _varValue, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String name_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrName());
        if (!name_.isEmpty()) {
            tryBuildInputResult(name_, _anaDoc, _page);
        }
        if (_read.hasAttribute(_varValue)) {
            String value_ = _read.getAttribute(_varValue);
            opsValueRoot = NatRenderAnalysis.getRootAnalyzedOperations(value_, 0, _anaDoc, _page);
        }
    }

    public void tryBuildInputResult(String _name, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        opsReadRoot = NatRenderAnalysis.getRootAnalyzedOperations(_name, 0, _anaDoc, _page);
        NatOperationNode res_ = opsReadRoot;
        NatSettableElResult settable_ = AffectationNatOperation.castDottedTo(res_);
        setClassNameNat(((NatOperationNode) settable_).getNames());
        setSettable((NatOperationNode) settable_);
    }

    public String getClassNameNat() {
        return classNameNat;
    }

    public void setClassNameNat(String _className) {
        classNameNat = _className;
    }

    public NatOperationNode getOpsReadRoot() {
        return opsReadRoot;
    }

    public NatOperationNode getOpsValueRoot() {
        return opsValueRoot;
    }

    public NatOperationNode getSettable() {
        return settable;
    }

    public void setSettable(NatOperationNode _settable) {
        settable = _settable;
    }

}
