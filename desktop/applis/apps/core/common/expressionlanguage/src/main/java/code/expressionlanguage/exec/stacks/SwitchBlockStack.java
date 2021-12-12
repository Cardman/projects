package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;

public final class SwitchBlockStack extends AbstractStask implements ConditionBlockStack {

    private ExecBracedBlock execBlock;

    private ExecBracedBlock execLastVisitedBlock;

    private ExecBracedBlock execCurrentVisitedBlock;

    public SwitchBlockStack(ExecBracedBlock _execBlock, ExecBracedBlock _execLastVisitedBlock) {
        execBlock = _execBlock;
        execLastVisitedBlock = _execLastVisitedBlock;
    }
    public ExecBracedBlock getBlock() {
        return execBlock;
    }

    @Override
    public void setCurrentVisitedBlock(ExecBracedBlock _execCurrentVisitedBlock) {
        this.execCurrentVisitedBlock = _execCurrentVisitedBlock;
    }

    @Override
    public ExecBracedBlock getCurrentVisitedBlock() {
        return execCurrentVisitedBlock;
    }

    public ExecBracedBlock getExecLastVisitedBlock() {
        return execLastVisitedBlock;
    }

}
