package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;

public final class EmptyInstruction extends Leaf implements BuildableElMethod {

    public EmptyInstruction(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        buildEmptyEl(_cont);
    }

    @Override
    public void processEl(ContextEl _cont) {
        processBlock(_cont);
    }

}
