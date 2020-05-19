package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.FoundWarningInterpret;

public final class DefaultLocalizer implements AbstractLocalizer {
    private final ContextEl context;

    public DefaultLocalizer(ContextEl context) {
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
