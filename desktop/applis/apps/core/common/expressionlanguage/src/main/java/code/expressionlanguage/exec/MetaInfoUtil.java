package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.EntryCust;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MetaInfoUtil {
    private MetaInfoUtil() {
    }

    private static ClassMetaInfo getCustomClassMetaInfo(String _name, ContextEl _context) {
        String base_ = StringExpUtil.getIdFromAllTypes(_name);
        for (ExecRootBlock c: _context.getClasses().getClassBodies()) {
            String k_ = c.getFullName();
            if (!StringUtil.quickEq(k_, base_)) {
                continue;
            }
            return getCustomClassMetaInfo(new ExecFormattedRootBlock(c,_name), _context);
        }
        return new ClassMetaInfo(_context.getStandards().getContent().getCoreNames().getAliasVoid(),_context, ClassCategory.VOID,"");
    }

    public static ClassMetaInfo getCustomClassMetaInfo(ExecFormattedRootBlock _formatted, ContextEl _context) {
        return new ClassMetaInfo(_formatted, _context);
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

    public static ClassMetaInfo getExtendedClassMetaInfo(ContextEl _context, String _name, ClassMetaInfo _classOwner) {
        return getExtendedClassMetaInfo(_context,_name,_classOwner.getVariableOwner());
    }

    public static ClassMetaInfo getExtendedClassMetaInfo(ContextEl _context, String _name, AnnotatedMemberStruct _member) {
        return getExtendedClassMetaInfo(_context,_name,_member.getFormDeclaringClass());
    }

    public static ClassMetaInfo getExtendedClassMetaInfo(ContextEl _context, String _name, String _variableOwner) {
        if (StringUtil.quickEq(_name, StringExpUtil.SUB_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(StringExpUtil.SUB_TYPE)) {
            StringList upperBounds_ = new StringList(_name.substring(StringExpUtil.SUB_TYPE.length()));
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(StringExpUtil.SUP_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList(_name.substring(StringExpUtil.SUB_TYPE.length()));
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.VARIABLE,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith("~")) {
            StringList upperBounds_ = new StringList(_name.substring("~".length()));
            StringList lowerBounds_ = new StringList(_name.substring("~".length()));
            return new ClassMetaInfo(_name, ClassCategory.REF_TYPE,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(AbstractReplacingType.ARR_BEG_STRING)&&_name.contains(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
            return new ClassMetaInfo(_name, _context, ClassCategory.ARRAY, _variableOwner);
        }
        return getClassMetaInfo(_context,_name);
    }

    public static Struct getClassMetaInfo(ContextEl _context, AnnotatedMemberStruct _member) {
        String formDeclaringClass_ = _member.getFormDeclaringClass();
        if (formDeclaringClass_.isEmpty()) {
            return NullStruct.NULL_VALUE;
        }
        return getClassMetaInfo(_context, formDeclaringClass_);
    }

    public static ClassMetaInfo getClassMetaInfo(ContextEl _context, String _name) {
        if (ExecClassArgumentMatching.isPrimitive(_name, _context)) {
            return new ClassMetaInfo(_name, _context, ClassCategory.PRIMITIVE,"");
        }
        if (_name.startsWith(AbstractReplacingType.ARR_BEG_STRING)) {
            return new ClassMetaInfo(_name, _context, ClassCategory.ARRAY, "");
        }
        String base_ = StringExpUtil.getIdFromAllTypes(_name);
        LgNames stds_ = _context.getStandards();
        for (EntryCust<String, StandardType> c: stds_.getStandards().entryList()) {
            String k_ = c.getKey();
            if (!StringUtil.quickEq(k_, base_)) {
                continue;
            }
            StandardType clblock_ = c.getValue();
            return getClassMetaInfo(_context,clblock_, _name);
        }
        return getCustomClassMetaInfo(_name, _context);
    }

    public static ClassMetaInfo getClassMetaInfo(ContextEl _context, StandardType _type, String _name) {
        return new ClassMetaInfo(_context, _type, _name);
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
        int indexFileType_ = call_.getTraceIndex();
        ExecFileBlock f_ = call_.getFile();
        String fileName_;
        int row_;
        int col_;
        if (f_ != null) {
            fileName_ = f_.getFileName();
            FileMetrics metrics_ = f_.getMetrics(_cont.getTabWidth());
            row_ = metrics_.getRowFile(indexFileType_);
            col_ = metrics_.getColFile(indexFileType_,row_);
        } else {
            fileName_ = "";
            row_ = 0;
            col_ = 0;
        }
        String currentClassName_ = call_.getGlobalClass().getFormatted();
        ExecBlock bl_ = call_.getBlockRoot();
        if (bl_ instanceof ExecReturnableWithSignature) {
            String signature_ =((ExecReturnableWithSignature)bl_).getSignature(_cont);
            return new StackTraceElementStruct(fileName_,row_,col_,indexFileType_,currentClassName_,signature_);
        }
        String signature_ = "";
        return new StackTraceElementStruct(fileName_,row_,col_,indexFileType_,currentClassName_,signature_);
    }

    public static boolean hasToExit(ContextEl _cont, String _className, Argument _arg, StackCall _stackCall) {
        Classes classes_ = _cont.getClasses();
        String idClass_ = StringExpUtil.getIdFromAllTypes(_className);
        String curClass_ = _stackCall.getLastPage().getGlobalClass().getFormatted();
        curClass_ = StringExpUtil.getIdFromAllTypes(curClass_);
        if (StringUtil.quickEq(curClass_, idClass_)) {
            return false;
        }
        ExecRootBlock c_ = classes_.getClassBody(idClass_);
        if (c_ != null) {
            DefaultLockingClass locks_ = _cont.getLocks();
            if (_stackCall.getInitializingTypeInfos().isInitEnums()) {
                InitClassState res_ = locks_.getState(idClass_);
                if (res_ != InitClassState.SUCCESS) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    return true;
                }
                return false;
            }
            InitClassState res_ = locks_.getProgressingState(idClass_);
            if (res_ == InitClassState.NOT_YET) {
                _stackCall.setCallingState(new NotInitializedClass(new ExecFormattedRootBlock(c_,idClass_),c_, _arg));
                return true;
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(idClass_, _cont, _stackCall);
                _stackCall.setCallingState(new CustomFoundExc(causing_));
                return true;
            }
        }
        return false;
    }
}
