package code.expressionlanguage.variables;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;

public final class LoopVariable {

    private static final String AS = " as ";

    private static final String STEP = "step";

    private static final String CONTAINER = "container";

    private static final String ELEMENT = "element";

    private static final String INDEX = "index";

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

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

    public String getInfos(ContextEl _context) {
        StringBuilder lv_ = new StringBuilder(INDEX).append(SEP_KEY_VAL).append(index).append(AS).append(indexClassName).append(SEP_INFO);
        lv_.append(ELEMENT).append(AS).append(className).append(SEP_INFO);
        lv_.append(STEP).append(SEP_KEY_VAL).append(step);
        lv_.append(SEP_INFO);
        if (container == null) {
            lv_.append(CONTAINER).append(SEP_KEY_VAL);
        } else {
            LgNames stds_ = _context.getStandards();
            lv_.append(CONTAINER).append(SEP_KEY_VAL).append(stds_.getStructClassName(container, _context));
        }
        lv_.append(SEP_INFO);
        return lv_.toString();
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

    public void setElement(Number _element) {
        element = NumberStruct.wrapNb(_element);
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
