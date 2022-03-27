package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.StackTraceElementStruct;

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
        AbstractPageEl call_ = _stackCall.getCall(_index);
        ExecFileBlock f_ = call_.getFile();
        RowColumnIndex rci_;
        String fileName_;
        if (f_ != null) {
            fileName_ = f_.getFileName();
            int trace_ = call_.getTraceIndex();
            rci_ = RowColumnIndex.calculate(f_, trace_, _cont.getTabWidth());
        } else {
            fileName_ = "";
            rci_ = RowColumnIndex.def();
        }
        String currentClassName_ = call_.getGlobalClass().getFormatted();
        ExecBlock bl_ = call_.getBlockRoot();
        if (bl_ instanceof ExecReturnableWithSignature) {
            String signature_ =((ExecReturnableWithSignature)bl_).getSignature(_cont);
            return new StackTraceElementStruct(fileName_,rci_,currentClassName_,signature_);
        }
        String signature_ = "";
        return new StackTraceElementStruct(fileName_,rci_,currentClassName_,signature_);
    }

    public static boolean hasToExit(ContextEl _cont, GeneType _className, Argument _arg, StackCall _stackCall) {
        if (_stackCall.getLastPage().getGlobalClass().getRootBlock() == _className) {
            return false;
        }
        if (_className instanceof ExecRootBlock) {
            DefaultLockingClass locks_ = _cont.getLocks();
            InitClassState res_ = locks_.getState((ExecRootBlock) _className);
            if (_stackCall.getInitializingTypeInfos().isInitEnums()) {
                if (res_ != InitClassState.SUCCESS) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    return true;
                }
                return false;
            }
            if (res_ == InitClassState.NOT_YET) {
                locks_.initClass((ExecRootBlock) _className);
                _stackCall.setCallingState(new NotInitializedClass(new ExecFormattedRootBlock((ExecRootBlock) _className,_className.getFullName()),(ExecRootBlock) _className, _arg));
                return true;
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(_className.getFullName(), _cont, _stackCall);
                _stackCall.setCallingState(new CustomFoundExc(causing_));
                return true;
            }
        }
        return false;
    }
}
