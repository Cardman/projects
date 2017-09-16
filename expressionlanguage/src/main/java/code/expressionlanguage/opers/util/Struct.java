package code.expressionlanguage.opers.util;
import java.lang.reflect.Field;

import code.expressionlanguage.PrimitiveTypeUtil;
import code.serialize.ConverterMethod;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.StringList;

public final class Struct {

    private final String className;

    private final Object instance;

    private final ObjectMap<ClassField,Struct> fields;

    private final Struct parent;

    public Struct() {
        this(null, (String)null);
    }
    public Struct(Object _instance) {
        this(_instance, PrimitiveTypeUtil.getAliasArrayClass(_instance.getClass()));
    }

    public Struct(Object _instance, Struct _parent) {
        this(_instance, PrimitiveTypeUtil.getAliasArrayClass(_instance.getClass()), new ObjectMap<ClassField,Struct>(), _parent);
    }

    public Struct(Object _instance, String _className) {
        this(_instance, _className, new ObjectMap<ClassField,Struct>());
    }
    public Struct(Object _instance, String _className,
            ObjectMap<ClassField,Struct> _fields) {
        this(_instance, _className, _fields, null);
    }

    public Struct(Object _instance, String _className,
            ObjectMap<ClassField,Struct> _fields, Struct _parent) {
        className = _className;
        instance = _instance;
        fields = _fields;
        parent = _parent;
    }

    public static Struct wrapOrId(Object _element) {
        if (_element instanceof Struct) {
            return (Struct) _element;
        }
        return new Struct(_element);
    }

    public boolean isNull() {
        return instance == null;
    }

    public boolean sameReference(Struct _other) {
        return instance == _other.instance;
    }

    public Struct getStruct(Field _field) {
        Object o_ = ConverterMethod.getField(_field, instance);
        Struct s_;
        if (o_ == null) {
            s_ = new Struct();
        } else {
            s_ = new Struct(o_);
        }
        return s_;
    }

    public Struct getStruct(ClassField _classField, Field _field) {
        Struct s_ = fields.getVal(_classField);
        if (s_ != null) {
            return s_;
        }
        return getStruct(_field);
    }

    public void setStruct(ClassField _classField, Struct _value) {
        for (EntryCust<ClassField, Struct> e: fields.entryList()) {
            if (e.getKey().eq(_classField)) {
                e.setValue(_value);
                return;
            }
        }
    }

    public Boolean isJavaObject() {
        if (instance == null) {
            return null;
        }
        String prettyClassName_ = PrimitiveTypeUtil.getAliasArrayClass(instance.getClass());
        return StringList.quickEq(className, prettyClassName_);
    }

    public String getClassName() {
        return className;
    }

    public String getRealClassName() {
        if (instance == null) {
            return null;
        }
        return instance.getClass().getName();
    }

    public Object getInstance() {
        return instance;
    }

    public ObjectMap<ClassField,Struct> getFields() {
        return fields;
    }

    public Struct getParent() {
        if (parent != null) {
            return parent;
        }
        Boolean javaObj_ = isJavaObject();
        if (javaObj_ == null) {
            return new Struct();
        }
        if (!javaObj_) {
            return new Struct();
        }
        Class<?> ret_ = instance.getClass();
        if (ret_.isArray()) {
            return new Struct();
        }
        Class<?> enclosing_;
        enclosing_ = ret_.getEnclosingClass();
        if (enclosing_ == null) {
            return new Struct();
        }
        for (Field f: ret_.getDeclaredFields()) {
            if (f.getType().isArray()) {
                continue;
            }
            if (!f.isSynthetic()) {
                continue;
            }
            f.setAccessible(true);
            Object parent_ = ConverterMethod.getField(f, instance);
            return new Struct(parent_);
        }
        return new Struct();
    }
}
