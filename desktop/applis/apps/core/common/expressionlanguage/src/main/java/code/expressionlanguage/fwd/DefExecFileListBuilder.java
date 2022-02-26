package code.expressionlanguage.fwd;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.exec.blocks.ExecAbstractFileBlock;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.coverage.Coverage;
import code.util.CustList;
import code.util.EntryCust;

public final class DefExecFileListBuilder implements AbstractExecFileListBuilder {
    private final AnalyzedPageEl analyzed;
    private final Coverage coverage;

    public DefExecFileListBuilder(AnalyzedPageEl _page, Forwards _forwards){
        analyzed = _page;
        coverage = _forwards.getCoverage();
    }
    @Override
    public CustList<ExecAbstractFileBlock> build() {
        CustList<ExecAbstractFileBlock> files_ = new CustList<ExecAbstractFileBlock>();
        for (EntryCust<String, FileBlock> f: analyzed.getFilesBodies().entryList()) {
            FileBlock content_ = f.getValue();
            ExecAbstractFileBlock exFile_ = new ExecFileBlock(content_.getMetricsCore(), content_.getFileName(),content_.getFileEscapedCalc());
            coverage.putFile(content_);
            files_.add(exFile_);
        }
        return files_;
    }
}
