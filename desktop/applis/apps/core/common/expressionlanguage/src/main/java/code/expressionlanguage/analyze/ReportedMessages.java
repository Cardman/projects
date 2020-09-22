package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.errors.custom.ErrorList;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.analyze.errors.custom.WarningList;
import code.expressionlanguage.analyze.errors.stds.StdErrorList;
import code.expressionlanguage.analyze.errors.stds.StdWordError;
import code.util.StringList;
import code.util.StringMap;

public final class ReportedMessages {

    private final ErrorList errorsDet = new ErrorList();
    private final WarningList warningsDet = new WarningList();
    private final StdErrorList stdErrorDet = new StdErrorList();
    private final StringList messagesErrorDet = new StringList();

    private StringMap<String> errors = new StringMap<String>();


    public boolean isAllEmptyErrors() {
        return isEmptyMessageError()&&isEmptyStdError()&&isEmptyErrors();
    }
    public boolean isEmptyErrors() {
        return errorsDet.isEmpty();
    }
    public String displayErrors() {
        return errorsDet.display();
    }
    public void addError(FoundErrorInterpret _error) {
        errorsDet.add(_error);
    }

    public String displayMessageErrors() {
        return messagesErrorDet.display();
    }
    public boolean isEmptyMessageError() {
        return messagesErrorDet.isEmpty();
    }
    public void addMessageError(String _std) {
        messagesErrorDet.add(_std);
    }

    public String displayStdErrors() {
        return stdErrorDet.display();
    }
    public boolean isEmptyStdError() {
        return stdErrorDet.isEmpty();
    }
    public void addStdError(StdWordError _std) {
        stdErrorDet.add(_std);
    }

    public boolean isEmptyWarnings() {
        return warningsDet.isEmpty();
    }
    public void addWarning(FoundWarningInterpret _warning) {
        warningsDet.add(_warning);
    }
    public String displayWarnings() {
        return warningsDet.display();
    }

    public StringMap<String> getErrors() {
        return errors;
    }

    public void setErrors(StringMap<String> errors) {
        this.errors = errors;
    }
}
