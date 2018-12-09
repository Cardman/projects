package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.structs.AnnotationStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatStringTreeMap;
import code.util.StringList;


final class CompleteAnnotPart extends ParentAnnotPart {

    private String className;

    private NatStringTreeMap<Struct> fields;
    private AnnotationStruct annotation;
    @Override
    String getBegin() {
        return StringList.concat("@",className,"(");
    }

    @Override
    String getEnd() {
        return ")";
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

    @Override
    AnnotationStruct getInstance() {
        return annotation;
    }
    public AnnotationStruct getAnnotation() {
        return annotation;
    }

    public void setAnnotation(AnnotationStruct _annotation) {
        annotation = _annotation;
    }

    public NatStringTreeMap<Struct> getFields() {
        return fields;
    }

    public void setFields(NatStringTreeMap<Struct> _fields) {
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
