package code.expressionlanguage.variables;
import code.expressionlanguage.opers.util.Struct;

public final class LoopVariable {

    private static final String EXTENDED_EXPRESSION = "extendedExpression";

    private static final String AS = " as ";

    private static final String STEP = "step";

    private static final String MAP = "map";

    private static final String ARRAY = "array";

    private static final String LIST = "list";

    private static final String SEP_FIELD = ",";

    private static final String ELEMENT = "element";

    private static final String INDEX = "index";

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

    private long index;

    private Struct element = new Struct();

    private long step;
    
    private Struct container;

    private Object array;

    private Object list;

    private Object map;

    private String extendedExpression;

    private String className = Object.class.getName();

    private String indexClassName = Long.class.getName();

    @Override
    public String toString() {
        String lv_ = INDEX+SEP_KEY_VAL+index+AS+indexClassName+SEP_INFO;
        lv_ += ELEMENT+SEP_KEY_VAL+element.getInstance()+AS+className+SEP_INFO;
        lv_ += STEP+SEP_KEY_VAL+step;
        lv_ += SEP_INFO;
        try {
            lv_ += ARRAY+SEP_KEY_VAL+array.getClass().getName()+SEP_FIELD+array;
        } catch (Error _0) {
            try {
                lv_ += ARRAY+SEP_KEY_VAL+array;
            } catch (Error _1) {
                lv_ += ARRAY+SEP_KEY_VAL;
            } catch (RuntimeException _1) {
                lv_ += ARRAY+SEP_KEY_VAL;
            }
        } catch (RuntimeException _0) {
            try {
                lv_ += ARRAY+SEP_KEY_VAL+array;
            } catch (Error _1) {
                lv_ += ARRAY+SEP_KEY_VAL;
            } catch (RuntimeException _1) {
                lv_ += ARRAY+SEP_KEY_VAL;
            }
        }
        lv_ += SEP_INFO;
        try {
            lv_ += LIST+SEP_KEY_VAL+list.getClass().getName()+SEP_FIELD+list;
        } catch (Error _0) {
            try {
                lv_ += LIST+SEP_KEY_VAL+list;
            } catch (Error _1) {
                lv_ += LIST+SEP_KEY_VAL;
            } catch (RuntimeException _1) {
                lv_ += LIST+SEP_KEY_VAL;
            }
        } catch (RuntimeException _0) {
            try {
                lv_ += LIST+SEP_KEY_VAL+list;
            } catch (Error _1) {
                lv_ += LIST+SEP_KEY_VAL;
            } catch (RuntimeException _1) {
                lv_ += LIST+SEP_KEY_VAL;
            }
        }
        lv_ += SEP_INFO;
        try {
            lv_ += MAP+SEP_KEY_VAL+map.getClass().getName()+SEP_FIELD+map;
        } catch (RuntimeException _0) {
            try {
                lv_ += MAP+SEP_KEY_VAL+map;
            } catch (Error _1) {
                lv_ += MAP+SEP_KEY_VAL;
            } catch (RuntimeException _1) {
                lv_ += MAP+SEP_KEY_VAL;
            }
        } catch (Error _0) {
            try {
                lv_ += MAP+SEP_KEY_VAL+map;
            } catch (Error _1) {
                lv_ += MAP+SEP_KEY_VAL;
            } catch (RuntimeException _1) {
                lv_ += MAP+SEP_KEY_VAL;
            }
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
            element = new Struct();
        }
    }

    public Object getElement() {
        return element.getInstance();
    }

    public void setElement(Object _element) {
        if (_element instanceof Struct) {
            element = (Struct) _element;
        } else if (_element == null) {
            element = new Struct();
        } else {
            element = new Struct(_element);
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
            container = new Struct();
        }
    }    

    public Object getArray() {
        return array;
    }

    public void setArray(Object _array) {
        array = _array;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object _list) {
        list = _list;
    }

    public Object getMap() {
        return map;
    }

    public void setMap(Object _mapCast) {
        map = _mapCast;
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
