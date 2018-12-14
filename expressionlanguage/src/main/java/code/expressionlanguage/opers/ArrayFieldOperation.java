package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.errors.custom.UndefinedFieldError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;

public final class ArrayFieldOperation extends AbstractFieldOperation {

    public ArrayFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching cl_ = getPreviousResultClass();
        String aliasLength_ = _conf.getStandards().getAliasLength();
        if (StringList.quickEq(str_, aliasLength_)) {
            Argument arg_ = getPreviousArgument();
            if (Argument.isNullValue(arg_)) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(static_);
            }
            cl_.setCheckOnlyNullPe(true);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
            return;
        }
        UndefinedFieldError und_ = new UndefinedFieldError();
        und_.setClassName(cl_.getNames().join(""));
        und_.setFileName(_conf.getCurrentFileName());
        und_.setIndexFile(_conf.getCurrentLocationIndex());
        _conf.getClasses().addError(und_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
    }
    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeNotBoolAssignmentAfter(_conf);
    }
    @Override
    Argument getCommonArgument(Argument _previous, ExecutableCode _conf) {
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument arg_ = _previous;
        Struct inst_ = arg_.getStruct();
        if (inst_ instanceof ArrayStruct) {
            ArrayStruct arr_ = (ArrayStruct) inst_;
            a_ = new Argument();
            a_.setStruct(new IntStruct(arr_.getInstance().length));
            return a_;
        }
        String cast_;
        cast_ = _conf.getStandards().getAliasCast();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        String argCl_ = arg_.getObjectClassName(_conf.getContextEl());
        String arrObj_ = _conf.getStandards().getAliasObject();
        arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(arrObj_);
        _conf.setException(new ErrorStruct(_conf, StringList.concat(argCl_,RETURN_LINE,arrObj_,RETURN_LINE),cast_));
        a_ = new Argument();
        return a_;
    }

    @Override
    public void tryCalculateNode(ContextEl _conf,
            EqList<SortedClassField> _list, SortedClassField _current) {
        if (!isIntermediateDottedOperation()) {
            return;
        }
        Argument arg_ = getPreviousArgument();
        Argument a_ = new Argument();
        if (!(arg_.getStruct() instanceof ArrayStruct)) {
            return;
        }
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        a_.setStruct(new IntStruct(arr_.getInstance().length));
        setSimpleArgumentAna(a_,_conf);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        if (isCalculated()) {
            return;
        }
        Argument arg_ = getPreviousArgument();
        Argument a_ = new Argument();
        if (arg_ == null ||!(arg_.getStruct() instanceof ArrayStruct)) {
            return;
        }
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        a_.setStruct(new IntStruct(arr_.getInstance().length));
        setSimpleArgumentAna(a_,_conf);
    }
}
