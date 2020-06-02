package code.expressionlanguage.types;

import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.FoundWarningInterpret;

public interface AbstractLocalizer {
    String getCurrentFileName();
    int getCurrentLocationIndex();
    void addWarning(FoundWarningInterpret _warning);
    void addError(FoundErrorInterpret _error);
}
