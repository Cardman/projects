package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.AbstractCurrentGlobalBlock;

public final class AdvancedCurrentGlobalBlock implements AbstractCurrentGlobalBlock {
    private final AnalyzedPageEl page;
    private final AnalyzingDoc analyzingDoc;

    public AdvancedCurrentGlobalBlock(AnalyzedPageEl page, AnalyzingDoc analyzingDoc) {
        this.page = page;
        this.analyzingDoc = analyzingDoc;
    }

    private static AccessedBlock getAccessingImportingBlock(AccessedBlock _r, RootBlock _root) {
        AccessedBlock a_;
        if (_root != null) {
            a_ = _root;
        } else {
            a_ = _r;
        }
        return a_;
    }

    private static AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl, AnalyzedPageEl _analyzing) {
        String gl_ = _analyzing.getGlobalClass();
        RootBlock root_ = _analyzing.getAnaClassBody(StringExpUtil.getIdFromAllTypes(gl_));
        return getAccessingImportingBlock(_bl, root_);
    }

    @Override
    public AccessedBlock getCurrentGlobalBlockImporting() {
        return analyzingDoc.getCurrentDoc();
    }

    @Override
    public ExecAccessingImportingBlock getImportingAcces() {
        return analyzingDoc.getCurrentDoc();
    }

    @Override
    public AccessedBlock getCurrentGlobalBlock() {
        return analyzingDoc.getCurrentDoc();
    }

    @Override
    public AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl) {
        return getCurrentGlobalBlock(_bl, page);
    }
}
