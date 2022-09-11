package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.IndexesComparator;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.Ints;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ExecArrayTemplates {
    private ExecArrayTemplates() {
    }

    public static Struct newCustomArrayOrExc(String _className, Ints _dims, ContextEl _cont, StackCall _stackCall) {
        return newCustomArrayOrExc(new Ints(),_className,_dims,_cont, _stackCall);
    }

    public static Struct newCustomArrayOrExc(Ints _filter, String _className, Ints _dims, ContextEl _cont, StackCall _stackCall) {
        Ints dims_ = new Ints();
        String size_;
        LgNames lgNames_ = _cont.getStandards();
        size_ = lgNames_.getContent().getCoreNames().getAliasBadSize();
        if (_dims.isEmpty()) {
            return new ErrorStruct(_cont,size_, _stackCall);
        }
        int j_ = 0;
        for (int s: _dims) {
            if (s < 0) {
                if (_filter.isValidIndex(j_)) {
                    int off_ = _filter.get(j_);
                    _stackCall.setOffset(off_);
                }
                return new ErrorStruct(_cont, StringUtil.concat(Long.toString(s),"<0"),size_, _stackCall);
            }
            dims_.add(s);
            j_++;
        }
        return newCustomArray(_className, dims_, _cont);
    }

    public static ArrayStruct newCustomArray(String _className, Ints _dims, ContextEl _cont) {
        ArrayStructSortedElementByIndexes indexesArray_;
        indexesArray_ = new ArrayStructSortedElementByIndexes(new IndexesComparator());
        ArrayStruct output_ = new ArrayStruct(_dims.first(), StringExpUtil.getPrettyArrayType(_className, _dims.size()));
        Ints dims_ = new Ints();
        indexesArray_.put(new Ints(), output_);
        int glDim_ = _dims.size();
        int i_ = IndexConstants.FIRST_INDEX;
        Struct defClass_ = ExecClassArgumentMatching.defaultValue(_className, _cont);
        for (int i : _dims) {
            dims_.add(i);
            int nextIndex_ = i_ + 1;
            if (nextIndex_ >= glDim_) {
                for (Ints k: dims_.getAllIndexes()) {
                    indexesArray_.put(k, defClass_);
                }
                continue;
            }
            int curDim_ = _dims.get(nextIndex_);
            String formattedClass_ = StringExpUtil.getPrettyArrayType(_className, glDim_-nextIndex_);
            for (Ints k: dims_.getAllIndexes()) {
                Struct value_ = new ArrayStruct(curDim_, formattedClass_);
                indexesArray_.put(k, value_);
            }
            i_++;
        }
        for (EntryCust<Ints,Struct> e: indexesArray_.entryList()) {
            Ints key_ = e.getKey();
            Struct value_ = e.getValue();
            if (key_.isEmpty()) {
                continue;
            }
            Ints ind_ = new Ints(key_);
            ind_.removeQuicklyLast();
            int lastIndex_ = key_.last();
            Struct str_ = indexesArray_.getVal(ind_);
            ArrayStruct arr_ = ExecArrayFieldOperation.getArray(str_, _cont);
            trySet(arr_,lastIndex_,value_);
        }
        return output_;
    }

    static void trySet(ArrayStruct _arr, int _index, Struct _value) {
        if (_index < 0 || _index >= _arr.getLength()) {
            return;
        }
        _arr.set(_index, _value);
    }

    public static Struct getElement(Struct _struct, Struct _index, ContextEl _conf, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        if (!(_struct instanceof ArrayStruct)) {
            return procNoArr(_struct, _conf, _stackCall);
        }
        if (_index == NullStruct.NULL_VALUE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        if (!(_index instanceof NumberStruct)) {
            String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            String type_ = _struct.getClassName(_conf);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, type_, cast_, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        ArrayStruct arr_ = (ArrayStruct) _struct;
        int index_ = NumParsers.convertToNumber(_index).intStruct();
        if (index_ < 0 || index_ >= arr_.getLength()) {
            String cast_ = stds_.getContent().getCoreNames().getAliasBadIndex();
            StringBuilder mess_ = getIndexMessage(_index, arr_);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, mess_.toString(), cast_, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        Struct elt_ = arr_.get(index_);
        _stackCall.getInitializingTypeInfos().addSensibleField(arr_, elt_);
        return elt_;
    }

    public static Struct getRange(Struct _struct, Struct _index, ContextEl _conf, StackCall _stackCall) {
        if (!(_struct instanceof ArrayStruct)) {
            return procNoArr(_struct, _conf, _stackCall);
        }
        if (!(_index instanceof RangeStruct)) {
            return nullArg(_struct, _index, _conf, _stackCall);
        }
        RangeStruct ind_ = (RangeStruct) _index;
        ArrayStruct arr_ = (ArrayStruct) _struct;
        return getRange(_conf, _stackCall, ind_, arr_);
    }

    private static Struct nullArg(Struct _struct, Struct _index, ContextEl _conf, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        if (_index == NullStruct.NULL_VALUE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String type_ = _struct.getClassName(_conf);
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, type_, cast_, _stackCall)));
        return NullStruct.NULL_VALUE;
    }

    private static Struct procNoArr(Struct _struct, ContextEl _conf, StackCall _stackCall) {
        return nullArg(Argument.getNull(_struct), _struct, _conf, _stackCall);
    }

    private static WithoutParentIdStruct getRange(ContextEl _conf, StackCall _stackCall, RangeStruct _ind, ArrayStruct _arr) {
        Struct err_ = boundErr(_conf, _stackCall, _ind, _arr);
        if (err_ != NullStruct.NULL_VALUE) {
            _stackCall.setCallingState(new CustomFoundExc(err_));
            return NullStruct.NULL_VALUE;
        }
        long lower_ = _ind.getLower();
        long step_ = _ind.getStep();
        long upper_ = upper(_ind, _arr);
        if (step_ < 0){
            int count_ = countNegStep(lower_, step_, upper_);
            ArrayStruct sub_ = new ArrayStruct(count_, _arr.getClassName());
            int insert_ = 0;
            long i_ = upper_-1L;
            while (i_ >= lower_) {
                sub_.set(insert_, _arr.get((int) i_));
                insert_++;
                i_ += step_;
            }
            addSensible(_arr, _stackCall,sub_);
            return sub_;
        }
        int count_ = countPosStep(lower_, step_, upper_);
        ArrayStruct sub_ = new ArrayStruct(count_, _arr.getClassName());
        int insert_ = 0;
        long i_ = lower_;
        while (i_ < upper_) {
            sub_.set(insert_, _arr.get((int) i_));
            insert_++;
            i_ += step_;
        }
        addSensible(_arr, _stackCall,sub_);
        return sub_;
    }

    public static Struct setRange(Struct _struct, RangeStruct _index, Struct _value, ContextEl _conf, StackCall _stackCall) {
        if (!(_struct instanceof ArrayStruct)) {
            return procNoArr(_struct, _conf, _stackCall);
        }
        if (!(_value instanceof ArrayStruct)) {
            return nullArg(_struct, _value, _conf, _stackCall);
        }
        ArrayStruct arr_ = (ArrayStruct) _struct;
        return setRange(_conf, _stackCall, _index, arr_, (ArrayStruct)_value);
    }

    private static WithoutParentIdStruct setRange(ContextEl _conf, StackCall _stackCall, RangeStruct _ind, ArrayStruct _arr, ArrayStruct _value) {
        LgNames stds_ = _conf.getStandards();
        for (Struct s: _value.list()) {
            Struct value_ = Argument.getNull(s);
            ErrorType errorType_ = safeObjectArr(value_.getClassName(_conf), _conf, _arr);
            if (errorType_ == ErrorType.NPE) {
                String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
                return NullStruct.NULL_VALUE;
            }
            if (errorType_ != ErrorType.NOTHING) {
                String cast_ = stds_.getContent().getCoreNames().getAliasStore();
                StringBuilder mess_ = buildStoreError(value_, _conf, _arr);
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, mess_.toString(), cast_, _stackCall)));
                return NullStruct.NULL_VALUE;
            }
        }
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_arr)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return NullStruct.NULL_VALUE;
        }
        Struct err_ = boundErr(_conf, _stackCall, _ind, _arr);
        if (err_ != NullStruct.NULL_VALUE) {
            _stackCall.setCallingState(new CustomFoundExc(err_));
            return NullStruct.NULL_VALUE;
        }
        long lower_ = _ind.getLower();
        long step_ = _ind.getStep();
        long upper_ = upper(_ind, _arr);
        if (step_ < 0){
            return negStep(_conf, _stackCall, _arr, _value, lower_, step_, upper_);
        }
        return posStep(_conf, _stackCall, _arr, _value, lower_, step_, upper_);
    }

    private static Struct boundErr(ContextEl _conf, StackCall _stackCall, RangeStruct _ind, ArrayStruct _arr) {
        LgNames stds_ = _conf.getStandards();
        if (_ind.isUnlimited()) {
            if (_ind.getLower() > _arr.getLength()) {
                String cast_ = stds_.getContent().getCoreNames().getAliasBadIndex();
                StringBuilder mess_ = indexMessCom(_arr,_ind.getLower());
                return new ErrorStruct(_conf, mess_.toString(), cast_, _stackCall);
            }
        } else {
            if (_ind.getUpper() > _arr.getLength()) {
                String cast_ = stds_.getContent().getCoreNames().getAliasBadIndex();
                StringBuilder mess_ = indexMessCom(_arr,_ind.getUpper());
                return new ErrorStruct(_conf, mess_.toString(), cast_, _stackCall);
            }
        }
        return NullStruct.NULL_VALUE;
    }

    private static WithoutParentIdStruct posStep(ContextEl _conf, StackCall _stackCall, ArrayStruct _arr, ArrayStruct _value, long _lower, long _step, long _upper) {
        LgNames stds_ = _conf.getStandards();
        int countCheck_ = countPosStep(_lower, _step, _upper);
        if (countCheck_ != _value.getLength()) {
            String cast_ = stds_.getContent().getCoreNames().getAliasBadIndex();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, countCheck_+"!="+ _value.getLength(), cast_, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        int insert_ = 0;
        long i_ = _lower;
        while (i_ < _upper) {
            _arr.set((int) i_, _value.get(insert_));
            insert_++;
            i_ += _step;
        }
        return _value;
    }

    private static WithoutParentIdStruct negStep(ContextEl _conf, StackCall _stackCall, ArrayStruct _arr, ArrayStruct _value, long _lower, long _step, long _upper) {
        LgNames stds_ = _conf.getStandards();
        int countCheck_ = countNegStep(_lower, _step, _upper);
        if (countCheck_ != _value.getLength()) {
            String cast_ = stds_.getContent().getCoreNames().getAliasBadIndex();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, countCheck_+"!="+ _value.getLength(), cast_, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        int insert_ = 0;
        long i_ = _upper - 1L;
        while (i_ >= _lower) {
            _arr.set((int) i_, _value.get(insert_));
            insert_++;
            i_ += _step;
        }
        return _value;
    }

    private static int countPosStep(long _lower, long _step, long _upper) {
        int countCheck_ = 0;
        long iCheck_ = _lower;
        while (iCheck_ < _upper) {
            countCheck_++;
            iCheck_ += _step;
        }
        return countCheck_;
    }

    private static int countNegStep(long _lower, long _step, long _upper) {
        int countCheck_ = 0;
        long iCheck_ = _upper - 1L;
        while (iCheck_ >= _lower) {
            countCheck_++;
            iCheck_ += _step;
        }
        return countCheck_;
    }

    private static long upper(RangeStruct _ind, ArrayStruct _arr) {
        long upper_;
        if (_ind.isUnlimited()) {
            upper_ = _arr.getLength();
        } else {
            upper_ = _ind.getUpper();
        }
        return upper_;
    }

    private static void addSensible(Struct _struct, StackCall _stackCall, ArrayStruct _elt) {
        for (Struct s: _elt.list()){
            _stackCall.getInitializingTypeInfos().addSensibleField(_struct, s);
        }
    }

    public static void setElement(Struct _struct, Struct _index, Struct _value, ContextEl _conf, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        if (_struct == NullStruct.NULL_VALUE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return;
        }
        if (!(_struct instanceof ArrayStruct)) {
            String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            String type_ = Argument.getNull(_struct).getClassName(_conf);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, type_, cast_, _stackCall)));
            return;
        }
        if (_index == NullStruct.NULL_VALUE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return;
        }
        if (!(_index instanceof NumberStruct)) {
            String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            String type_ = _struct.getClassName(_conf);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, type_, cast_, _stackCall)));
            return;
        }
        ArrayStruct arr_ = (ArrayStruct) _struct;
        int index_ = NumParsers.convertToNumber(_index).intStruct();
        if (index_ < 0 || index_ >= arr_.getLength()) {
            String cast_ = stds_.getContent().getCoreNames().getAliasBadIndex();
            StringBuilder mess_ = getIndexMessage(_index, arr_);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, mess_.toString(), cast_, _stackCall)));
            return;
        }
        Struct value_ = Argument.getNull(_value);
        ErrorType errorType_ = safeObjectArr(value_.getClassName(_conf), _conf, arr_);
        if (errorType_ == ErrorType.NPE) {
            String npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return;
        }
        if (errorType_ != ErrorType.NOTHING) {
            String cast_ = stds_.getContent().getCoreNames().getAliasStore();
            StringBuilder mess_ = buildStoreError(value_, _conf, arr_);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, mess_.toString(), cast_, _stackCall)));
            return;
        }
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(arr_)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        arr_.set(index_, value_);
    }

    private static ErrorType safeObjectArr(String _value, ContextEl _context, ArrayStruct _arr) {
        String arrType_ = _arr.getClassName();
        String param_ = StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(arrType_));
        return ExecInherits.safeObject(param_,_value,_context);
    }

    private static StringBuilder buildStoreError(Struct _value, ContextEl _context, ArrayStruct _arr) {
        String arrType_ = _arr.getClassName();
        String param_ = StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(arrType_));
        String arg_ = _value.getClassName(_context);
        StringBuilder mess_ = new StringBuilder();
        mess_.append(arg_);
        mess_.append("!=");
        mess_.append(param_);
        return mess_;
    }

    private static StringBuilder getIndexMessage(Struct _index, ArrayStruct _arr) {
        int index_ = NumParsers.convertToNumber(_index).intStruct();
        return indexMessCom(_arr, index_);
    }

    private static StringBuilder indexMessCom(ArrayStruct _arr, int _index) {
        StringBuilder mess_ = new StringBuilder();
        if (_index < 0) {
            mess_.append(_index);
            mess_.append("<0");
        } else {
            mess_.append(_index);
            mess_.append(">=");
            mess_.append(_arr.getLength());
        }
        return mess_;
    }

    public static void setCheckedElements(CustList<Argument> _args, Struct _arr, ContextEl _context, StackCall _stackCall) {
        int len_ = _args.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            Argument chArg_ = _args.get(i);
            IntStruct ind_ = new IntStruct(i);
            setElement(_arr, ind_, chArg_.getStruct(), _context, _stackCall);
            if (_context.callsOrException(_stackCall)) {
                return;
            }
        }
    }
}
