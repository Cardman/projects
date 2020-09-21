package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.util.AnalyzingDoc;

public final class RendPackage extends RendParentBlock {
    private String name;
    RendPackage(OffsetStringInfo _name, OffsetsBlock _offset) {
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
                    _cont.getRendKeyWords().getKeyWordPackage(),
                    _cont.getRendKeyWords().getKeyWordImport());
            Configuration.addError(un_, _anaDoc, _page);
        }
    }

    public String getName() {
        return name;
    }
}
