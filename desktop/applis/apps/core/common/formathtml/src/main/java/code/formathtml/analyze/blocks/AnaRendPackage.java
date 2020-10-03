package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendPackage  extends AnaRendParentBlock {
    private String name;
    AnaRendPackage(OffsetStringInfo _name, OffsetsBlock _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (!(getParent() instanceof AnaRendImport)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_anaDoc.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _anaDoc.getRendKeyWords().getKeyWordPackage(),
                    _anaDoc.getRendKeyWords().getKeyWordImport());
            AnalyzingDoc.addError(un_, _anaDoc, _page);
        }
    }

    public String getName() {
        return name;
    }
}
