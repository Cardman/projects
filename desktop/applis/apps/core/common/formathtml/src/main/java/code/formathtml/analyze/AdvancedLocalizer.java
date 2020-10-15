package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.analyze.AbstractLocalizer;

public final class AdvancedLocalizer implements AbstractLocalizer {
    private final AnalyzedPageEl page;
    private final AnalyzingDoc analyzingDoc;

    public AdvancedLocalizer(AnalyzedPageEl _page, AnalyzingDoc _analyzingDoc) {
        this.page = _page;
        this.analyzingDoc = _analyzingDoc;
    }

    @Override
    public String getCurrentFileName() {
        return analyzingDoc.getFileName();
    }

    @Override
    public int getCurrentLocationIndex() {
        return AnalyzingDoc.getCurrentLocationIndex(page, analyzingDoc);
    }

    @Override
    public void addWarning(FoundWarningInterpret _warning) {
        AnalyzingDoc.addWarning(_warning, analyzingDoc, page);
    }

    @Override
    public void addError(FoundErrorInterpret _error) {
        AnalyzingDoc.addError(_error,analyzingDoc, page);
    }
}
