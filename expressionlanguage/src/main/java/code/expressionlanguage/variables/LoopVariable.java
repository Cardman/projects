package code.expressionlanguage.variables;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class LoopVariable {

    private long index;

    private Struct element = NullStruct.NULL_VALUE;

    private long step;
    
    private Struct container;

    private String className;

    private String indexClassName;

    private boolean finalVariable;

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public void setFinalVariable(boolean _finalVariable) {
        finalVariable = _finalVariable;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long _index) {
        index = _index;
    }

    public Struct getStruct() {
        return element;
    }

    public void setStruct(Struct _element) {
        element = _element;
        if (element == null) {
            element = NullStruct.NULL_VALUE;
        }
    }

    public void setElement(Long _element) {
        element = new LongStruct(_element);
    }

    public long getStep() {
        return step;
    }

    public void setStep(long _step) {
        step = _step;
    }

    public Struct getContainer() {
        return container;
    }

    public void setContainer(Struct _container) {
        container = _container;
        if (_container == null) {
            container = NullStruct.NULL_VALUE;
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        if (_className.isEmpty()) {
            return;
        }
        className = _className;
    }

    public String getIndexClassName() {
        return indexClassName;
    }

    public void setIndexClassName(String _indexClassName) {
        if (_indexClassName.isEmpty()) {
            return;
        }
        indexClassName = _indexClassName;
    }
}
