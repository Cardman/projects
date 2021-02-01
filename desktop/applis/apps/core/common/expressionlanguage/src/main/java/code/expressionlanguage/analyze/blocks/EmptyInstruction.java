package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public final class EmptyInstruction extends Leaf implements BuildableElMethod {

    public EmptyInstruction(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        //
    }


}
