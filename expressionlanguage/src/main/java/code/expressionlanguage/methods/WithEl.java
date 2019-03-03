package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;

public interface WithEl {

    void processEl(ContextEl _cont);

    OffsetsBlock getOffset();
}
