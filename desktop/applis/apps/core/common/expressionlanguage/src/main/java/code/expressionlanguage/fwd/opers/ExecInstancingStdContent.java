package code.expressionlanguage.fwd.opers;

public final class ExecInstancingStdContent {
    private final String fieldName;
    private final int blockIndex;

    public ExecInstancingStdContent(AnaInstancingStdContent _cont) {
        fieldName = _cont.getFieldName();
        blockIndex = _cont.getBlockIndex();
    }
    public String getFieldName() {
        return fieldName;
    }

    public int getBlockIndex() {
        return blockIndex;
    }

}
