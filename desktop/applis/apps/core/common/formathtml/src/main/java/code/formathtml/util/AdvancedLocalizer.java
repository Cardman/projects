package code.formathtml.util;

import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.types.AbstractLocalizer;
import code.formathtml.Configuration;

public final class AdvancedLocalizer implements AbstractLocalizer {
    private final Configuration context;

    public AdvancedLocalizer(Configuration context) {
        this.context = context;
    }

    @Override
    public String getCurrentFileName() {
        return context.getCurrentFileName();
    }

    @Override
    public int getCurrentLocationIndex() {
        return context.getCurrentLocationIndex();
    }

    @Override
    public void addWarning(FoundWarningInterpret _warning) {
        context.addWarning(_warning);
    }

    @Override
    public void addError(FoundErrorInterpret _error) {
        context.addError(_error);
    }
}
