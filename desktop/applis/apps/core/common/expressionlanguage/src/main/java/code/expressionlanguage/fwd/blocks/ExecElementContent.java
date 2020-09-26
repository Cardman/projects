package code.expressionlanguage.fwd.blocks;

public final class ExecElementContent {

    private final String fieldName;

    private final int fieldNameOffest;
    public ExecElementContent(AnaElementContent _cont) {
        fieldName = _cont.getFieldName();
        fieldNameOffest = _cont.getFieldNameOffest();
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getFieldNameOffest() {
        return fieldNameOffest;
    }
}
