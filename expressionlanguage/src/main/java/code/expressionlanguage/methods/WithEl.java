package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;

public interface WithEl {

    void buildExpressionLanguage(ContextEl _cont);

    void processEl(ContextEl _cont);

    OffsetsBlock getOffset();
}
