package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.util.CustList;

public final class EmptyInstruction extends Leaf implements BuildableElMethod {

    public EmptyInstruction(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        buildEmptyEl(_cont);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
    }
    @Override
    public void processEl(ContextEl _cont) {
        processBlock(_cont);
    }

}
