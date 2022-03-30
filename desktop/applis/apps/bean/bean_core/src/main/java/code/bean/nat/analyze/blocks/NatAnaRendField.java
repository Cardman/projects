package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;

public final class NatAnaRendField extends AnaRendParentBlock implements NatRendBuildEl {
    private final String prepare;
    private final int prepareOffset;
    private NatOperationNode root;
    NatAnaRendField(OffsetStringInfo _prepare, int _offset) {
        super(_offset);
        prepare = _prepare.getInfo();
        prepareOffset = _prepare.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatAnaRendClass cl_ = (NatAnaRendClass) getParent();
        String intern_ = cl_.getFullName();
        _anaDoc.setInternGlobalClass(intern_);
        root = NatRenderAnalysis.getRootAnalyzedOperations(prepare, 0, _anaDoc, _page);
        _anaDoc.setInternGlobalClass(AnaRendBlockHelp.EMPTY_STRING);
    }

    public int getPrepareOffset() {
        return prepareOffset;
    }

    public NatOperationNode getRoot() {
        return root;
    }
}
