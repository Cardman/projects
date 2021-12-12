package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;


public final class LoopBlockStack extends AbstractStask {

    private final LoopBlockStackContent content = new LoopBlockStackContent();

    private final ExecBracedBlock execBlock;

    public LoopBlockStack(ExecBracedBlock _execBlock) {
        execBlock = _execBlock;
    }
    @Override
    public ExecBracedBlock getCurrentVisitedBlock() {
        return getExecBlock();
    }

    public ExecBracedBlock getExecBlock() {
        return execBlock;
    }

    public LoopBlockStackContent getContent() {
        return content;
    }

}
