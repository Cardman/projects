package code.formathtml.classes;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.Configuration;
import code.formathtml.RendDocumentBlock;
import code.formathtml.RendParentBlock;
import code.formathtml.util.AnalyzingDoc;

public final class SimpleRender extends RendParentBlock {
    public SimpleRender() {
        super(new OffsetsBlock());
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {

    }
}
