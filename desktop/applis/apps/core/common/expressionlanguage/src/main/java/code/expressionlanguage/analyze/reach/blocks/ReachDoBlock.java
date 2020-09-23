package code.expressionlanguage.analyze.reach.blocks;


import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.DoBlock;
import code.expressionlanguage.exec.blocks.ExecDoBlock;

public final class ReachDoBlock extends ReachBracedBlock implements ReachLoop {
    private String label;
    protected ReachDoBlock(DoBlock _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
//        ExecDoBlock exec_ = new ExecDoBlock(getOffset(),label);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }
}
