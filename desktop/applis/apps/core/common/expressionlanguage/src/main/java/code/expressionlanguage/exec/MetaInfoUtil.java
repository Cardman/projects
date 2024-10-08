package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.StackTraceElementStruct;
import code.expressionlanguage.structs.Struct;

public final class MetaInfoUtil {
    private MetaInfoUtil() {
    }

    public static boolean isAbstractType(GeneType _type) {
        if (_type instanceof StandardClass) {
            return ((StandardClass)_type).isAbstractStdType();
        }
        if (_type instanceof ExecClassBlock) {
            return ((ExecClassBlock)_type).isAbstractType();
        }
        return !(_type instanceof ExecRecordBlock);
    }

    public static ArrayStruct newStackTraceElementArrayFull(StackCall _stackCall) {
        return _stackCall.getFullStack().newStackTraceElementArray(_stackCall);
    }

    public static ArrayStruct newStackTraceElementArray(ContextEl _cont, StackCall _stackCall) {
        int count_ = _stackCall.nbPages();
        String cl_ = _cont.getStandards().getContent().getStackElt().getAliasStackTraceElement();
        cl_ = StringExpUtil.getPrettyArrayType(cl_);
        ArrayStruct array_ = new ArrayStruct(count_, cl_);
        for (int i = 0; i < count_; i++) {
            array_.set(i, newStackTraceElement(_cont,i, _stackCall));
        }
        return array_;
    }

    public static StackTraceElementStruct newStackTraceElement(ContextEl _cont, int _index, StackCall _stackCall) {
        return newStackTraceElement(_cont, _stackCall.getCall(_index), _stackCall.getCallCondition());
    }

    public static StackTraceElementStruct newStackTraceElement(ContextEl _cont, AbstractPageEl _call, int _d) {
        ExecFileBlock f_ = _call.getFile();
        RowColumnIndex rci_;
        String fileName_ = ExecFileBlock.name(f_);
        if (f_ != null) {
            int trace_ = _call.getTraceIndex();
            if (fileName_.isEmpty()) {
                trace_ -= _d;
            }
            rci_ = RowColumnIndex.calculate(f_, trace_, _cont.getTabWidth());
        } else {
            rci_ = RowColumnIndex.def();
        }
        String currentClassName_ = _call.getGlobalClass().getFormatted();
        ExecBlock bl_ = _call.getBlockRoot();
        if (bl_ instanceof ExecReturnableWithSignature) {
            String signature_ =((ExecReturnableWithSignature)bl_).getSignature(_cont);
            return new StackTraceElementStruct(fileName_,rci_,currentClassName_,signature_);
        }
        String signature_ = "";
        return new StackTraceElementStruct(fileName_,rci_,currentClassName_,signature_);
    }

    public static CallingState state(ContextEl _cont, GeneType _className, Struct _arg, StackCall _stackCall) {
        if (_stackCall.getLastPage().getBlockRootType() == _className) {
            return null;
        }
        if (_className instanceof ExecRootBlock) {
            DefaultLockingClass locks_ = _cont.getLocks();
            InitClassState res_ = locks_.getState((ExecRootBlock) _className);
            if (_stackCall.getInitializingTypeInfos().isInitEnums()) {
                if (res_ != InitClassState.SUCCESS) {
                    return new CustomFoundExc(null,true);
                }
                return null;
            }
            if (res_ == InitClassState.NOT_YET) {
                return new NotInitializedClass(new ExecFormattedRootBlock((ExecRootBlock) _className,_className.getFullName()),(ExecRootBlock) _className, _arg);
            }
            if (res_ == InitClassState.ERROR) {
                return new CustomFoundExc(new CausingErrorStruct(_className.getFullName(), _cont, _stackCall));
            }
        }
        return null;
    }

    public static CallingState stateAfterInit(ContextEl _cont, GeneType _className, StackCall _stackCall) {
        if (_className instanceof ExecRootBlock && _cont.getLocks().getState((ExecRootBlock) _className) == InitClassState.ERROR) {
            return new CustomFoundExc(new CausingErrorStruct(_className.getFullName(), _cont, _stackCall));
        }
        return null;
    }
}
