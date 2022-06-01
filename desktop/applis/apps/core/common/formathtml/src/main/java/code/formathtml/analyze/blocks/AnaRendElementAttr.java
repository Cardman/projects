package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.AnalyzingDoc;

public interface AnaRendElementAttr {
    void processAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page);
}
