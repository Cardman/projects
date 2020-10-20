package code.expressionlanguage.exec.annotation;

import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatStringTreeMap;
import code.util.core.StringUtil;


final class CompleteAnnotPart extends ParentAnnotPart {

    private Struct struct;
    private String className;

    private NatStringTreeMap<Struct> fields;
    @Override
    String getBegin() {
        return StringUtil.concat("@",className,"(");
    }

    @Override
    String getEnd() {
        return ")";
    }

    void setClassName(String _className) {
        className = _className;
    }

    void setFields(NatStringTreeMap<Struct> _fields) {
        fields = _fields;
    }

    Struct getStruct() {
        return struct;
    }

    void setStruct(Struct _struct) {
        struct = _struct;
    }

    @Override
    CustList<StackObject> getStack() {
        CustList<StackObject> elts_ = new CustList<StackObject>();
        for (EntryCust<String, Struct> s: fields.entryList()) {
            StackAnnotation st_ = new StackAnnotation();
            st_.setName(s.getKey());
            st_.setValue(s.getValue());
            elts_.add(st_);
        }
        return elts_;
    }
}
