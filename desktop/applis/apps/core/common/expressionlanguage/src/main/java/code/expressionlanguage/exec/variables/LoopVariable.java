package code.expressionlanguage.exec.variables;

import code.util.core.StringUtil;

public final class LoopVariable {

    private long index;

    private String indexClassName;

    public long getIndex() {
        return index;
    }

    public void setIndex(long _index) {
        index = _index;
    }

    public String getIndexClassName() {
        return indexClassName;
    }

    public void setIndexClassName(String _indexClassName) {
        indexClassName = StringUtil.nullToEmpty(_indexClassName);
    }
}
