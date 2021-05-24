package code.formathtml.exec.opers;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class RendInvokingOperation extends RendMethodOperation implements RendPossibleIntermediateDotted {
    private final boolean intermediate;

    protected RendInvokingOperation(
            ExecOperationContent _content, boolean _intermediateDottedOperation) {
        super(_content);
        intermediate = _intermediateDottedOperation;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
