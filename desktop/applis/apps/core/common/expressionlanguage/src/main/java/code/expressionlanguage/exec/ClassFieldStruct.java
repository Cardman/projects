package code.expressionlanguage.exec;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ClassFieldStruct {
    private final ClassField classField;
    private Struct struct;

    public ClassFieldStruct(ClassField classField, Struct struct) {
        this.classField = classField;
        this.struct = struct;
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

    public void setStruct(Struct struct) {
        this.struct = struct;
    }
}
