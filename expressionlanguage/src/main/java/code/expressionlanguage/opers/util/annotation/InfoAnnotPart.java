package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.opers.util.ClassFieldStruct;
import code.expressionlanguage.structs.AnnotationStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.NatStringTreeMap;

abstract class InfoAnnotPart {

    private ParentAnnotPart parent;
    private InfoAnnotPart next;
    private int index;
    
    int getIndex() {
        return index;
    }
    void setIndex(int _index) {
        index = _index;
    }

    static InfoAnnotPart create(Struct _value, ParentAnnotPart _parent) {
        if (_value instanceof AnnotationStruct) {
            CompleteAnnotPart c_ = new CompleteAnnotPart();
            AnnotationStruct a_ = (AnnotationStruct)_value;
            c_.setClassName(a_.getClassName());
            c_.setParent(_parent);
            NatStringTreeMap<Struct> fields_ = new NatStringTreeMap<Struct>();
            for (ClassFieldStruct e: a_.getFields()) {
                fields_.put(e.getClassField().getFieldName(), e.getStruct());
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

    ParentAnnotPart getParent() {
        return parent;
    }
    void setParent(ParentAnnotPart _parent) {
        parent = _parent;
    }
    InfoAnnotPart getNext() {
        return next;
    }
    void setNext(InfoAnnotPart _next) {
        next = _next;
    }

}
