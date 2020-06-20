package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;

public final class TryBlockStack extends TryStack implements RemovableVars {

    private AbruptCallingFinally calling;

    private ExecBracedBlock execBlock;

    private ExecBracedBlock execLastBlock;

    private ExecBracedBlock execCurrentBlock;

    @Override
    public ExecBracedBlock getBlock() {
        return execBlock;
    }

    public void setExecBlock(ExecBracedBlock _execBlock) {
        execBlock = _execBlock;
    }

    @Override
    public ExecBracedBlock getLastBlock() {
        return execLastBlock;
    }


    public void setExecLastBlock(ExecBracedBlock _execLastBlock) {
        execLastBlock = _execLastBlock;
    }

    @Override
    public ExecBracedBlock getCurrentVisitedBlock() {
        return execCurrentBlock;
    }

    @Override
    public void setCurrentVisitedBlock(ExecBracedBlock _bl) {
        execCurrentBlock = _bl;
    }

    public AbruptCallingFinally getCalling() {
        return calling;
    }

    public void setCalling(AbruptCallingFinally _calling) {
        calling = _calling;
    }


}
