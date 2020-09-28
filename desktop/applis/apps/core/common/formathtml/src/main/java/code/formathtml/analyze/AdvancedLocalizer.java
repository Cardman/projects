package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.types.AbstractLocalizer;

public final class AdvancedLocalizer implements AbstractLocalizer {
    private final AnalyzedPageEl page;
    private final AnalyzingDoc analyzingDoc;

    public AdvancedLocalizer(AnalyzedPageEl page, AnalyzingDoc analyzingDoc) {
        this.page = page;
        this.analyzingDoc = analyzingDoc;
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
