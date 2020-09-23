package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.exec.blocks.ExecEmptyInstruction;

public final class ReachEmptyInstruction extends ReachLeaf implements ReachBuildableElMethod {
    protected ReachEmptyInstruction(Block _info) {
        super(_info);
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
//        ExecEmptyInstruction exec_ = new ExecEmptyInstruction(getOffset());
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }
}
