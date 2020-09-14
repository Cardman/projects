package code.formathtml;

import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;

public final class RendImportForm extends RendParentBlock {
    private String name;
    RendImportForm(OffsetStringInfo _name, OffsetsBlock _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        if (!(getParent() instanceof RendImport)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_cont.getAnalyzingDoc().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_cont.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _cont.getRendKeyWords().getKeyWordForm(),
                    _cont.getRendKeyWords().getKeyWordImport());
            Configuration.addError(un_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
        }
    }

    public String getName() {
        return name;
    }
}
