package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.DefaultCondition;
import code.expressionlanguage.exec.blocks.ExecDefaultCondition;
import code.expressionlanguage.exec.blocks.ExecInstanceDefaultCondition;

public final class ReachDefaultCondition extends ReachSwitchPartBlock {
    private DefaultCondition meta;
    protected ReachDefaultCondition(DefaultCondition _info) {
        super(_info);
        meta = _info;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
//        if (checkDefault(_page)){
//            ExecDefaultCondition exec_ = new ExecDefaultCondition(getOffset());
//            exec_.setFile(_page.getBlockToWrite().getFile());
//            _page.getBlockToWrite().appendChild(exec_);
//            _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
//            _page.getCoverage().putBlockOperations(exec_,getInfo());
//        }
    }

}
