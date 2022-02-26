package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendImportForm extends AnaRendParentBlock implements AnaRendBuildEl {
    private final String name;
    AnaRendImportForm(OffsetStringInfo _name, int _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (!(getParent() instanceof AnaRendImport)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(getOffset());
            un_.buildError(_anaDoc.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _anaDoc.getRendKeyWords().getKeyWordForm(),
                    _anaDoc.getRendKeyWords().getKeyWordImport());
            AnalyzingDoc.addError(un_, _page);
        }
    }

    public String getName() {
        return name;
    }
}
