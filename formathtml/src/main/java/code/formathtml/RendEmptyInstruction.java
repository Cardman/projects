package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;

public final class RendEmptyInstruction extends RendLeaf implements RendBuildableElMethod,RendPossibleEmpty {
    RendEmptyInstruction(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {

    }

    @Override
    public void processEl(Configuration _cont) {
        processBlock(_cont);
    }
}
