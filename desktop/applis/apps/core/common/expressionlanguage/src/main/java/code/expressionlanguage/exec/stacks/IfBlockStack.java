package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;


public final class IfBlockStack extends AbstractStask implements ConditionBlockStack,EnteredStack {

    private final ExecBracedBlock execBlock;
    private final ExecBracedBlock execLastBlock;
    private ExecBracedBlock execCurentVisitedBlock;

    private boolean entered;

    public IfBlockStack(ExecBracedBlock _execBlock, ExecBracedBlock _execLastBlock) {
        execBlock = _execBlock;
        execLastBlock = _execLastBlock;
    }
    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean _entered) {
        entered = _entered;
    }

    public ExecBracedBlock getBlock() {
        return execBlock;
    }

    public void setCurrentVisitedBlock(ExecBracedBlock _execCurentVisitedBlock) {
        execCurentVisitedBlock = _execCurentVisitedBlock;
    }

    public ExecBracedBlock getLastBlock() {
        return execLastBlock;
    }

    public ExecBracedBlock getCurrentVisitedBlock() {
        return execCurentVisitedBlock;
    }
}
