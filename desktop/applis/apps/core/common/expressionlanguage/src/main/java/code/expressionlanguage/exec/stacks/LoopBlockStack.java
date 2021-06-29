package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;


public final class LoopBlockStack extends AbstractStask {

    private final LoopBlockStackContent content = new LoopBlockStackContent();

    private ExecBracedBlock execBlock;

    @Override
    public ExecBracedBlock getCurrentVisitedBlock() {
        return execBlock;
    }

    @Override
    public void setCurrentVisitedBlock(ExecBracedBlock _bl) {
        execBlock = _bl;
    }

    public LoopBlockStackContent getContent() {
        return content;
    }

}
