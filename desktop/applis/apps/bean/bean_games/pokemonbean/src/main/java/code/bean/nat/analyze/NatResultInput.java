package code.bean.nat.analyze;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.opers.*;
import code.sml.Element;
import code.sml.NatAnalyzingDoc;

public final class NatResultInput {

    private final NatOperationNode opsReadRoot;
    private final NatOperationNode opsValueRoot;
    private final String classNameNat;
    private final NatOperationNode settable;

    public NatResultInput(Element _read, String _varValue, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String name_ = _read.getAttribute(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrName());
        opsReadRoot = NatRenderAnalysis.getRootAnalyzedOperations(name_, 0, _anaDoc, _page);
        NatOperationNode settable_ = castDottedTo(opsReadRoot);
        classNameNat = settable_.getNames();
        settable = settable_;
        String value_ = _read.getAttribute(_varValue);
        opsValueRoot = NatRenderAnalysis.getRootAnalyzedOperations(value_, 0, _anaDoc, _page);
    }

    public static NatOperationNode castDottedTo(NatOperationNode _root) {
        NatOperationNode elt_;
        if (!(_root instanceof AbstractDotNatOperation)) {
            elt_ = _root;
        } else {
            elt_ = ((MethodNatOperation)_root).getChildrenNodes().last();
        }
        return elt_;
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
