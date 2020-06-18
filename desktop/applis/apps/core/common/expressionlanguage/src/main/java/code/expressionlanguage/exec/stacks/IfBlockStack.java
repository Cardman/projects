package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.expressionlanguage.methods.BracedBlock;


public final class IfBlockStack extends IfStack implements RemovableVars {

    private ExecBracedBlock execBlock;
    private ExecBracedBlock execLastBlock;
    private ExecBracedBlock execCurentVisitedBlock;

    public ExecBracedBlock getBlock() {
        return execBlock;
    }

    public void setCurrentVisitedBlock(ExecBracedBlock _execCurentVisitedBlock) {
        execCurentVisitedBlock = _execCurentVisitedBlock;
    }

    public void setExecBlock(ExecBracedBlock _execBlock) {
        execBlock = _execBlock;
    }

    public ExecBracedBlock getLastBlock() {
        return execLastBlock;
    }

    public void setExecLastBlock(ExecBracedBlock execLastBlock) {
        this.execLastBlock = execLastBlock;
    }

    public ExecBracedBlock getCurrentVisitedBlock() {
        return execCurentVisitedBlock;
    }
}
