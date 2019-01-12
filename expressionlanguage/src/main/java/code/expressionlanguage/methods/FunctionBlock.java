package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;

public interface FunctionBlock {

    boolean isStaticContext();

    OffsetsBlock getOffset();

}
