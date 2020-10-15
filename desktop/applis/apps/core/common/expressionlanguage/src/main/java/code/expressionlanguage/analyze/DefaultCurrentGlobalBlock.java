package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.util.CustList;

public final class DefaultCurrentGlobalBlock implements AbstractCurrentGlobalBlock {
    private final AnalyzedPageEl page;

    public DefaultCurrentGlobalBlock(AnalyzedPageEl _page) {
        this.page = _page;
    }

    @Override
    public AccessedBlock getCurrentGlobalBlockImporting() {
        return page.getImportingTypes();
    }

    @Override
    public AccessingImportingBlock getImportingAcces() {
        return page.getImportingAcces();
    }

    @Override
    public AccessedBlock getCurrentGlobalBlock() {
        return page.getImporting();
    }

    @Override
    public AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl) {
        CustList<PartOffset> offs_ = page.getCurrentParts();
        offs_.clear();
        return _bl;
    }
}
