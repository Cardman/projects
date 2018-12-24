package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatStringTreeMap;
import code.util.StringList;


final class CompleteAnnotPart extends ParentAnnotPart {

    private String className;

    private NatStringTreeMap<Struct> fields;
    @Override
    String getBegin() {
        return StringList.concat("@",className,"(");
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
