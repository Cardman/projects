package code.expressionlanguage.opers;

import java.lang.reflect.Array;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtilAdv;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;

@SuppressWarnings("static-method")
public final class ArrOperationAdv extends MethodOperationAdv {
    Struct getElement(Struct _struct, Object _index, ContextEl _conf, int _indexEl) {
//        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        LgNames stds_ = _conf.getStandards();
        String null_;
        String badIndex_;
        null_ = stds_.getAliasNullPe();
        badIndex_ = stds_.getAliasBadIndex();
        if (_struct.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        if (_index == null) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        if (_conf.getClasses() != null) {
            Object array_ = _struct.getInstance();
            int len_ = LgNames.getLength(array_);
            int index_ = ((Number)_index).intValue();
            if (index_ < 0 || index_ >= len_) {
                throw new InvokeException(new StdStruct(new CustomError(StringList.concat(String.valueOf(index_),RETURN_LINE,_conf.joinPages())),badIndex_));
            }
            return LgNames.getElement(array_, index_, _conf);
        }
        Object arrayInst_ = _struct.getInstance();
        int len_ = Array.getLength(arrayInst_);
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(String.valueOf(index_),RETURN_LINE,_conf.joinPages())),badIndex_));
        }
        Object output_ = Array.get(arrayInst_, index_);
        if (output_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return StdStruct.wrapStd(output_, PrimitiveTypeUtilAdv.getQuickComponentType(stds_.getStructClassName(_struct, _conf)));
    }
    static void setElement(Struct _struct, Object _index, Struct _value, ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        String badIndex_;
        String store_;
        null_ = stds_.getAliasNullPe();
        badIndex_ = stds_.getAliasBadIndex();
        store_ = stds_.getAliasStore();
        if (_struct.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        if (_index == null) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        String strClass_ = stds_.getStructClassName(_struct, _conf);
        String valClass_ = stds_.getStructClassName(_value, _conf);
        if (_conf.getClasses() != null) {
            Object instance_ = _struct.getInstance();
            int len_ = LgNames.getLength(instance_);
            int index_ = ((Number)_index).intValue();
            if (index_ < 0 || index_ >= len_) {
                throw new InvokeException(new StdStruct(new CustomError(StringList.concat(String.valueOf(index_),RETURN_LINE,_conf.joinPages())),badIndex_));
            }
            if (!_value.isNull()) {
                String componentType_ = PrimitiveTypeUtilAdv.getQuickComponentType(strClass_);
                String elementType_ = valClass_;
                Mapping mapping_ = new Mapping();
                mapping_.setArg(elementType_);
                mapping_.setParam(componentType_);
                if (!Templates.isCorrect(mapping_, _conf)) {
                    throw new InvokeException(new StdStruct(new CustomError(StringList.concat(componentType_,elementType_,_conf.joinPages())),store_));
                }
            }
            Struct value_;
            if (_value instanceof NumberStruct || _value instanceof CharStruct) {
                String componentType_ = PrimitiveTypeUtilAdv.getQuickComponentType(strClass_);
                ClassArgumentMatching cl_ = new ClassArgumentMatching(componentType_);
                value_ = PrimitiveTypeUtilAdv.convertObject(cl_, _value, _conf);
            } else {
                value_ = _value;
            }
            LgNames.setElement(instance_, index_, value_, _conf);
            return;
        }
        Object arrayInst_ = _struct.getInstance();
        int len_ = Array.getLength(arrayInst_);
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(String.valueOf(index_),RETURN_LINE,_conf.joinPages())),badIndex_));
        }
        if (_value.isNull()) {
            Array.set(arrayInst_, index_, null);
            return;
        }
        String componentType_ = PrimitiveTypeUtilAdv.getQuickComponentType(strClass_);
        String elementType_ = valClass_;
        Mapping mapping_ = new Mapping();
        mapping_.setArg(elementType_);
        mapping_.setParam(componentType_);
        if (!Templates.isCorrect(mapping_, _conf)) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(componentType_,elementType_,_conf.joinPages())),store_));
        }
        Array.set(arrayInst_, index_, _value.getInstance());
    }
    @Override
    void calculateChildren() {
        // TODO Auto-generated method stub

    }

}
