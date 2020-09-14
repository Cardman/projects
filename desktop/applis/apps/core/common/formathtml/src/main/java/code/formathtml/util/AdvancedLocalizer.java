package code.formathtml.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.types.AbstractLocalizer;
import code.formathtml.Configuration;

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
        return Configuration.getCurrentLocationIndex(page, analyzingDoc);
    }

    @Override
    public void addWarning(FoundWarningInterpret _warning) {
        Configuration.addWarning(_warning, analyzingDoc, page);
    }

    @Override
    public void addError(FoundErrorInterpret _error) {
        Configuration.addError(_error,analyzingDoc, page);
    }
}
