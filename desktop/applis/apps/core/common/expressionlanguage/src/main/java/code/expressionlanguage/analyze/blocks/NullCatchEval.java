package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;

public final class NullCatchEval extends AbstractCatchEval {

    public NullCatchEval(int _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
//        _page.getCoverage().putCatches(this);
//        ExecNullCatchEval exec_ = new ExecNullCatchEval(getOffset());
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);
    }

}
