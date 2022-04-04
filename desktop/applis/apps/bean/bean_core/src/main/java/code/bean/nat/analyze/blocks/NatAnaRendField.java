package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;

public final class NatAnaRendField extends NatAnaRendParentBlock implements NatRendBuildEl {
    private final String prepare;
    private NatOperationNode root;
    NatAnaRendField(String _prepare) {
        super();
        prepare = _prepare;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatAnaRendClass cl_ = (NatAnaRendClass) getParent();
        String intern_ = cl_.getFullName();
        _anaDoc.setInternGlobalClass(intern_);
        root = NatRenderAnalysis.getRootAnalyzedOperations(prepare, 0, _anaDoc, _page);
        _anaDoc.setInternGlobalClass(AnaRendBlockHelp.EMPTY_STRING);
    }

    public NatOperationNode getRoot() {
        return root;
    }
}
