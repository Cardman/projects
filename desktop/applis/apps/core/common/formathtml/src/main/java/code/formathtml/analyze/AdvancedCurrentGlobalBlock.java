package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.AbstractCurrentGlobalBlock;
import code.util.CustList;
import code.util.StringList;

public final class AdvancedCurrentGlobalBlock implements AbstractCurrentGlobalBlock {
    private final AnalyzedPageEl page;
    private final AnalyzingDoc analyzingDoc;

    public AdvancedCurrentGlobalBlock(AnalyzedPageEl _page, AnalyzingDoc _analyzingDoc) {
        this.page = _page;
        this.analyzingDoc = _analyzingDoc;
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
        RootBlock root_ = _analyzing.getGlobalType().getRootBlock();
        return getAccessingImportingBlock(_bl, root_);
    }

    @Override
    public CustList<StringList> getCurrentGlobalBlockImportingTypes() {
        return analyzingDoc.getImportTypes();
    }

    @Override
    public AccessingImportingBlock getImportingAcces() {
        return analyzingDoc.getCurrentDoc();
    }

    @Override
    public AccessedBlock getCurrentGlobalBlock() {
        return page.getImporting();
    }

    @Override
    public AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl) {
        return getCurrentGlobalBlock(_bl, page);
    }
}
