package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.util.AnalyzingDoc;

public final class RendImportForm extends RendParentBlock {
    private String name;
    RendImportForm(OffsetStringInfo _name, OffsetsBlock _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (!(getParent() instanceof RendImport)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_anaDoc.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _cont.getRendKeyWords().getKeyWordForm(),
                    _cont.getRendKeyWords().getKeyWordImport());
            Configuration.addError(un_, _anaDoc, _page);
        }
    }

    public String getName() {
        return name;
    }
}
