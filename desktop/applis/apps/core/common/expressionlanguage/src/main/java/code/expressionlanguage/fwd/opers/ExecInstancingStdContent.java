package code.expressionlanguage.fwd.opers;

import code.util.StringMap;

public final class ExecInstancingStdContent {
    private final String fieldName;
    private final int blockIndex;
    private final StringMap<String> infos;

    public ExecInstancingStdContent(AnaInstancingStdContent _cont) {
        fieldName = _cont.getFieldName();
        blockIndex = _cont.getBlockIndex();
        infos = _cont.getInfos();
    }
    public String getFieldName() {
        return fieldName;
    }

    public int getBlockIndex() {
        return blockIndex;
    }

    public StringMap<String> getInfos() {
        return infos;
    }
}
