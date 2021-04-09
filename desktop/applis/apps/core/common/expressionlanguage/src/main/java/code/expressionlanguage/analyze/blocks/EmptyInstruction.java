package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;

public final class EmptyInstruction extends Leaf implements BuildableElMethod {

    public EmptyInstruction(int _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        //
    }


}
