package code.expressionlanguage.opers.util;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.annotation.ExportAnnotationUtil;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.StringList;

public final class AnnotationStruct implements FieldableStruct {

    private final String className;

    private final ObjectMap<ClassField,Struct> fields;

    public AnnotationStruct(String _className,
            ObjectMap<ClassField,Struct> _fields) {
        fields = _fields;
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    @Override
    public boolean isNull() {
        return false;
    }

    public String toAnnotationString() {
        return ExportAnnotationUtil.exportAnnotation(this);
    }
    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof AnnotationStruct)) {
            return false;
        }
        AnnotationStruct a_ = (AnnotationStruct) _other;
        return StringList.quickEq(ExportAnnotationUtil.exportAnnotation(a_), ExportAnnotationUtil.exportAnnotation(this));
    }

    @Override
    public Struct getStruct(ClassField _classField) {
        return fields.getVal(_classField);
    }

    @Override
    public void setStruct(ClassField _classField, Struct _value) {
        for (EntryCust<ClassField, Struct> e: fields.entryList()) {
            if (e.getKey().eq(_classField)) {
                e.setValue(_value);
                return;
            }
        }
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public Object getInstance() {
        return null;
    }

    @Override
    public ObjectMap<ClassField,Struct> getFields() {
        return fields;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean isArray() {
        return false;
    }
}
