package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;

public class EmptyInstruction extends Leaf {

    public EmptyInstruction(ContextEl _importingPage, BracedBlock _m,
            OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
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
