package code.expressionlanguage.errors;

public final class KeyValueMemberName {
    private final String key;
    private final String value;

    public KeyValueMemberName(String _key, String _value) {
        key = _key;
        value = _value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
