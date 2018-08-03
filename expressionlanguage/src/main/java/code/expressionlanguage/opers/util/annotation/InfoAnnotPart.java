package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.opers.util.AnnotationStruct;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.Struct;
import code.util.EntryCust;
import code.util.NatTreeMap;

abstract class InfoAnnotPart {

    private ParentAnnotPart parent;
    private InfoAnnotPart next;
    private int index;
    
    public int getIndex() {
        return index;
    }
    public void setIndex(int _index) {
        index = _index;
    }
    abstract Struct getInstance();
    static InfoAnnotPart create(Struct _value, ParentAnnotPart _parent) {
        if (_value instanceof AnnotationStruct) {
            CompleteAnnotPart c_ = new CompleteAnnotPart();
            AnnotationStruct a_ = (AnnotationStruct)_value;
            c_.setClassName(a_.getClassName());
            c_.setAnnotation(a_);
            c_.setParent(_parent);
            NatTreeMap<String,Struct> fields_ = new NatTreeMap<String,Struct>();
            for (EntryCust<ClassField, Struct> e: a_.getFields().entryList()) {
                fields_.put(e.getKey().getFieldName(), e.getValue());
            }
            c_.setFields(fields_);
            return c_;
        }
        if (_value instanceof ArrayStruct) {
            ArrayAnnotPart arr_ = new ArrayAnnotPart();
            ArrayStruct a_ = (ArrayStruct)_value;
            arr_.setArray(a_);
            arr_.setParent(_parent);
            return arr_;
        }
        LeafAnnotPart l_ = new LeafAnnotPart();
        l_.setPart(_value);
        l_.setParent(_parent);
        return l_;
    }
    abstract InfoAnnotPart getFirst();
    public ParentAnnotPart getParent() {
        return parent;
    }
    public void setParent(ParentAnnotPart _parent) {
        parent = _parent;
    }
    public InfoAnnotPart getNext() {
        return next;
    }
    public void setNext(InfoAnnotPart _next) {
        next = _next;
    }

}
