package code.expressionlanguage.exec;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public final class ClassFieldStruct {
    private final ClassField classField;
    private Struct struct;

    public ClassFieldStruct(ClassField _classField, Struct _struct) {
        this.classField = _classField;
        this.struct = _struct;
    }
    public static CustList<ClassFieldStruct> staticFields(StringMap<StringMap<Struct>> _staticFields) {
        CustList<ClassFieldStruct> sum_ = new CustList<ClassFieldStruct>();
        for (EntryCust<String, StringMap<Struct>> c: _staticFields.entryList()) {
            for (EntryCust<String, Struct> e: c.getValue().entryList()) {
                if (e.getValue() == null) {
                    continue;
                }
                sum_.add(new ClassFieldStruct(new ClassField(c.getKey(),e.getKey()),e.getValue()));
            }
        }
        return sum_;
    }
    public static ClassFieldStruct getPair(CustList<ClassFieldStruct> _list, ClassField _id) {
        for (ClassFieldStruct c: _list) {
            if (c.getClassField().eq(_id)) {
                return c;
            }
        }
        return null;
    }

    public ClassField getClassField() {
        return classField;
    }

    public Struct getStruct() {
        return struct;
    }

    public void setStruct(Struct _struct) {
        this.struct = _struct;
    }
}
