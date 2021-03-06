package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.IntTreeMap;
import code.util.StringMap;

public final class AnaRendEmptyInstruction extends AnaRendLeaf {
    AnaRendEmptyInstruction(OffsetsBlock _offset) {
        super(_offset);
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        //
    }
}
