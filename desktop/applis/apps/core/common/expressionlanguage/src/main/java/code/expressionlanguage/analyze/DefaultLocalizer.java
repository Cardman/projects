package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;

public final class DefaultLocalizer implements AbstractLocalizer {
    private final AnalyzedPageEl context;

    public DefaultLocalizer(AnalyzedPageEl context) {
        this.context = context;
    }

    @Override
    public String getCurrentFileName() {
        return context.getCurrentBlock().getFile().getFileName();
    }

    @Override
    public int getCurrentLocationIndex() {
        return context.getTraceIndex();
    }

    @Override
    public void addWarning(FoundWarningInterpret _warning) {
        context.addLocWarning(_warning);
    }

    @Override
    public void addError(FoundErrorInterpret _error) {
        context.addLocError(_error);
    }
}
