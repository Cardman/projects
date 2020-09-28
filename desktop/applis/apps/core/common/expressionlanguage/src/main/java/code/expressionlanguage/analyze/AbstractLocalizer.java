package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;

public interface AbstractLocalizer {
    String getCurrentFileName();
    int getCurrentLocationIndex();
    void addWarning(FoundWarningInterpret _warning);
    void addError(FoundErrorInterpret _error);
}
