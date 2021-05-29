package code.expressionlanguage.analyze.opers.util;

public final class AnnotationFieldInfo {
    private final String type;
    private final boolean optional;

    public AnnotationFieldInfo(String _type, boolean _optional) {
        type = _type;
        optional = _optional;
    }

    public String getType() {
        return type;
    }

    public boolean isOptional() {
        return optional;
    }
}
