package code.bean.nat.exec.opers;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.opers.RendMethodOperation;
import code.formathtml.exec.opers.RendPossibleIntermediateDotted;

public abstract class NatInvokingOperation extends RendMethodOperation implements RendPossibleIntermediateDotted {
    private final boolean intermediate;

    protected NatInvokingOperation(
            ExecOperationContent _content, boolean _intermediateDottedOperation) {
        super(_content);
        intermediate = _intermediateDottedOperation;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
