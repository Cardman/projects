package code.expressionlanguage.opers.util;

import java.lang.reflect.Field;

public class FieldResult {

    private FieldMetaInfo id;

    private Field field;

    private SearchingMemberStatus status;

    public FieldMetaInfo getId() {
        return id;
    }

    public void setId(FieldMetaInfo _id) {
        id = _id;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field _field) {
        field = _field;
    }

    public SearchingMemberStatus getStatus() {
        return status;
    }

    public void setStatus(SearchingMemberStatus _status) {
        status = _status;
    }
}
