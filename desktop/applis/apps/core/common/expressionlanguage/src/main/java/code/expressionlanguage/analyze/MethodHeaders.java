package code.expressionlanguage.analyze;

import code.expressionlanguage.errors.custom.ErrorList;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.errors.custom.WarningList;
import code.expressionlanguage.errors.stds.StdErrorList;
import code.expressionlanguage.errors.stds.StdWordError;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class MethodHeaders {
    private final StringMap<CustList<MethodHeaderInfo>> explicitCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> explicitIdCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> explicitFromCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> implicitCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> implicitIdCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> implicitFromCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringList typesWithInnerOperators = new StringList();
    private StringList packagesFound = new StringList();

    private final ErrorList errorsDet = new ErrorList();
    private final WarningList warningsDet = new WarningList();
    private final StdErrorList stdErrorDet = new StdErrorList();
    private final StringList messagesErrorDet = new StringList();

    public StringMap<CustList<MethodHeaderInfo>> getExplicitCastMethods() {
        return explicitCastMethods;
    }

    public StringMap<CustList<MethodHeaderInfo>> getExplicitIdCastMethods() {
        return explicitIdCastMethods;
    }

    public StringMap<CustList<MethodHeaderInfo>> getExplicitFromCastMethods() {
        return explicitFromCastMethods;
    }

    public StringMap<CustList<MethodHeaderInfo>> getImplicitCastMethods() {
        return implicitCastMethods;
    }

    public StringMap<CustList<MethodHeaderInfo>> getImplicitIdCastMethods() {
        return implicitIdCastMethods;
    }

    public StringMap<CustList<MethodHeaderInfo>> getImplicitFromCastMethods() {
        return implicitFromCastMethods;
    }

    public StringList getTypesWithInnerOperators() {
        return typesWithInnerOperators;
    }
    public StringList getPackagesFound() {
        return packagesFound;
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

}
