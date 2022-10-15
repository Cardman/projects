package code.bean.nat.analyze;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.opers.AffectationNatOperation;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.analyze.opers.NatSettableElResult;
import code.sml.Element;

public final class NatResultInput {

    private final NatOperationNode opsReadRoot;
    private final NatOperationNode opsValueRoot;
    private final String classNameNat;
    private final NatOperationNode settable;

    public NatResultInput(Element _read, String _varValue, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String name_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrName());
        opsReadRoot = NatRenderAnalysis.getRootAnalyzedOperations(name_, 0, _anaDoc, _page);
        NatSettableElResult settable_ = AffectationNatOperation.castDottedTo(opsReadRoot);
        classNameNat = ((NatOperationNode) settable_).getNames();
        settable = (NatOperationNode) settable_;
        String value_ = _read.getAttribute(_varValue);
        opsValueRoot = NatRenderAnalysis.getRootAnalyzedOperations(value_, 0, _anaDoc, _page);
    }

    public String getClassNameNat() {
        return classNameNat;
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

}
