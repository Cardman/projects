package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.util.CustList;

public final class DefaultLocalizer {
    private final AnalyzedPageEl context;

    public DefaultLocalizer(AnalyzedPageEl _context) {
        this.context = _context;
    }

    public String getCurrentFileName() {
        return AnalyzedPageEl.getFileName(context.getCurrentFile());
    }

    public int getCurrentLocationIndex() {
        return context.getTraceIndex();
    }

    public void addWarning(FoundWarningInterpret _warning) {
        context.addLocWarning(_warning);
    }

    public void addErrors(CustList<FoundErrorInterpret> _errors) {
        context.addLocErrors(_errors);
    }
    public void addError(FoundErrorInterpret _error) {
        context.addLocError(_error);
    }
}
