package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;

public final class SwitchBlockStack extends AbstractStask {

    private ExecBracedBlock execBlock;

    private ExecBracedBlock execLastVisitedBlock;

    private ExecBracedBlock execCurrentVisitedBlock;

    @Override
    public ExecBracedBlock getBlock() {
        return execBlock;
    }

    public void setExecBlock(ExecBracedBlock _execBlock) {
        execBlock = _execBlock;
    }

    @Override
    public void setCurrentVisitedBlock(ExecBracedBlock execCurrentVisitedBlock) {
        this.execCurrentVisitedBlock = execCurrentVisitedBlock;
    }

    @Override
    public ExecBracedBlock getLastBlock() {
        return execBlock;
    }

    @Override
    public ExecBracedBlock getCurrentVisitedBlock() {
        return execCurrentVisitedBlock;
    }

    public ExecBracedBlock getExecLastVisitedBlock() {
        return execLastVisitedBlock;
    }

    public void setExecLastVisitedBlock(ExecBracedBlock _execLastVisitedBlock) {
        execLastVisitedBlock = _execLastVisitedBlock;
    }

}
