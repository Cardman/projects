package code.formathtml;

import code.formathtml.util.AnalyzingDoc;

public interface RendBuildableElMethod extends RendWithEl {
    void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc, AnalyzingDoc _anaDoc);
}
