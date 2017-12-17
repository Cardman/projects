package code.expressionlanguage.variables;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;

public final class LoopVariable {

    private static final String EXTENDED_EXPRESSION = "extendedExpression";

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

    private String extendedExpression;

    private String className = Object.class.getName();

    private String indexClassName = Long.class.getName();

    public String getInfos(ContextEl _context) {
        String lv_ = INDEX+SEP_KEY_VAL+index+AS+indexClassName+SEP_INFO;
        lv_ += ELEMENT+SEP_KEY_VAL+element.getInstance()+AS+className+SEP_INFO;
        lv_ += STEP+SEP_KEY_VAL+step;
        lv_ += SEP_INFO;
        if (container == null) {
            lv_ += CONTAINER + SEP_KEY_VAL + container;
        } else {
            lv_ += CONTAINER + SEP_KEY_VAL + container.getClassName(_context);
        }
        lv_ += SEP_INFO;
        lv_ += EXTENDED_EXPRESSION+SEP_KEY_VAL+extendedExpression;
        lv_ += SEP_INFO;
        return lv_;
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

    public Object getElement() {
        return element.getInstance();
    }

    public void setElement(Object _element) {
        if (_element instanceof Struct) {
            element = (Struct) _element;
        } else {
            element = StdStruct.wrapStd(_element);
        }
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

    public String getExtendedExpression() {
        return extendedExpression;
    }

    public void setExtendedExpression(String _extendedExpression) {
        extendedExpression = _extendedExpression;
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
