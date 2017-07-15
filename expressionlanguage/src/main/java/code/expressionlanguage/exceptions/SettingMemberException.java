package code.expressionlanguage.exceptions;

public class SettingMemberException extends RuntimeException {

    public SettingMemberException() {
    }

    public SettingMemberException(String _message) {
        super(_message);
    }

    public SettingMemberException(Throwable _cause) {
        super(_cause);
    }

    public SettingMemberException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
