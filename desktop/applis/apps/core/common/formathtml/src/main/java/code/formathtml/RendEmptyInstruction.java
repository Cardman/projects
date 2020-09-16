package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.util.AnalyzingDoc;
import code.util.IntTreeMap;
import code.util.StringMap;

public final class RendEmptyInstruction extends RendLeaf implements RendWithEl,RendPossibleEmpty {
    RendEmptyInstruction(OffsetsBlock _offset) {
        super(_offset);
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc) {

    }

    @Override
    public void processEl(Configuration _cont) {
        processBlock(_cont);
    }
}
