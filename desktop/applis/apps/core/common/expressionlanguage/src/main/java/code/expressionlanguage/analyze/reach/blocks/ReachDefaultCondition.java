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
        if (checkDefault(_page)){
            ExecDefaultCondition exec_ = new ExecDefaultCondition(getOffset());
            exec_.setFile(_page.getBlockToWrite().getFile());
            _page.getBlockToWrite().appendChild(exec_);
            _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
            _page.getCoverage().putBlockOperations(exec_,getInfo());
        }
    }

    private boolean checkDefault(AnalyzedPageEl _page) {
        ReachBracedBlock b_ = getParent();
        if (!(b_ instanceof ReachSwitchBlock)) {
            return true;
        } else {
            _page.getCoverage().putBlockOperationsSwitchs(b_.getInfo(),getInfo());
            ReachSwitchBlock s_ = (ReachSwitchBlock) b_;
            String instanceTest_ = s_.getInstanceTest();
            if (instanceTest_.isEmpty()) {
                return true;
            }
            ExecInstanceDefaultCondition exec_ = new ExecInstanceDefaultCondition(getOffset(),meta.getVariableName(), instanceTest_, meta.getVariableOffset());
            exec_.setFile(_page.getBlockToWrite().getFile());
            _page.getBlockToWrite().appendChild(exec_);
            _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
            _page.getCoverage().putBlockOperations(exec_,getInfo());
            return false;
        }
    }
}
