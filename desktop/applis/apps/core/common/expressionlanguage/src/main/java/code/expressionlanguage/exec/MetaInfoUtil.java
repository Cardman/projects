package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.CustList;
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
        ExecRootBlock type_ = _formatted.getRootBlock();
        String name_ = _formatted.getFormatted();
        CustList<MethodMetaInfo> infos_;
        infos_ = new CustList<MethodMetaInfo>();
        CustList<MethodMetaInfo> infosBlock_;
        infosBlock_ = new CustList<MethodMetaInfo>();
        CustList<MethodMetaInfo> infosExplicits_;
        CustList<MethodMetaInfo> infosImplicits_;
        CustList<MethodMetaInfo> infosTrues_;
        CustList<MethodMetaInfo> infosFalses_;
        infosExplicits_ = new CustList<MethodMetaInfo>();
        infosImplicits_ = new CustList<MethodMetaInfo>();
        infosTrues_ = new CustList<MethodMetaInfo>();
        infosFalses_ = new CustList<MethodMetaInfo>();
        CustList<FieldMetaInfo> infosFields_;
        infosFields_ = new CustList<FieldMetaInfo>();
        CustList<ConstructorMetaInfo> infosConst_;
        infosConst_ = new CustList<ConstructorMetaInfo>();
        String fileName_ = type_.getFile().getFileName();
        StringList inners_ = new StringList();
        boolean existCtor_ = false;
        for (ExecRootBlock b: type_.getChildrenTypes()) {
            inners_.add(b.getFullName());
        }
        for (ExecInfoBlock b: type_.getAllFields()) {

            for (String f: b.getFieldName()) {
                FieldMetaInfo met_ = new FieldMetaInfo(_context, b,name_, f, _formatted);
                infosFields_.add(met_);
            }
        }
        for (ExecBlock b: type_.getAllFct()) {
            if (b instanceof ExecOverridableBlock) {
                ExecOverridableBlock method_ = (ExecOverridableBlock) b;
                MethodMetaInfo met_ = new MethodMetaInfo(_formatted,_context, method_, method_.getKind() == ExecMethodKind.EXPLICIT_CAST || method_.getKind() == ExecMethodKind.IMPLICIT_CAST
                        || method_.getKind() == ExecMethodKind.TRUE_OPERATOR  || method_.getKind() == ExecMethodKind.FALSE_OPERATOR);
                infos_.add(met_);
                if (method_.getKind() == ExecMethodKind.EXPLICIT_CAST) {
                    met_ = new MethodMetaInfo(_formatted,_context, method_, true);
                    infosExplicits_.add(met_);
                }
                if (method_.getKind() == ExecMethodKind.IMPLICIT_CAST) {
                    met_ = new MethodMetaInfo(_formatted,_context, method_, true);
                    infosImplicits_.add(met_);
                }
                if (method_.getKind() == ExecMethodKind.TRUE_OPERATOR) {
                    met_ = new MethodMetaInfo(_formatted,_context, method_, true);
                    infosTrues_.add(met_);
                }
                if (method_.getKind() == ExecMethodKind.FALSE_OPERATOR) {
                    met_ = new MethodMetaInfo(_formatted,_context, method_, true);
                    infosFalses_.add(met_);
                }
            }
            if (b instanceof ExecInitBlock) {
                ExecInitBlock method_ = (ExecInitBlock) b;
                MethodMetaInfo met_ = new MethodMetaInfo(_formatted,_context,method_);
                infos_.add(met_);
                met_ = new MethodMetaInfo(_formatted,_context,method_);
                infosBlock_.add(met_);
            }
            if (b instanceof ExecAnnotationMethodBlock) {
                ExecAnnotationMethodBlock method_ = (ExecAnnotationMethodBlock) b;
                MethodMetaInfo met_ = new MethodMetaInfo(_formatted, method_);
                infos_.add(met_);
            }
            if (b instanceof ExecConstructorBlock) {
                existCtor_ = true;
                ExecConstructorBlock method_ = (ExecConstructorBlock) b;
                ConstructorMetaInfo met_ = new ConstructorMetaInfo(_formatted, method_,_context);
                infosConst_.add(met_);
            }
        }
        if (!existCtor_) {
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_formatted, null,_context);
            infosConst_.add(met_);
        }
        if (type_ instanceof ExecEnumBlock) {
            String valueOf_ = _context.getStandards().getContent().getPredefTypes().getAliasEnumPredValueOf();
            String values_ = _context.getStandards().getContent().getPredefTypes().getAliasEnumValues();
            String string_ = _context.getStandards().getContent().getCharSeq().getAliasString();
            MethodId id_ = new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_));
            String ret_ = type_.getWildCardString();
            MethodMetaInfo met_ = new MethodMetaInfo(_formatted, id_, ret_);
            infos_.add(met_);
            id_ = new MethodId(MethodAccessKind.STATIC, values_, new StringList());
            ret_ = StringExpUtil.getPrettyArrayType(ret_);
            met_ = new MethodMetaInfo(_formatted, id_, ret_);
            infos_.add(met_);
        }
        ExecRootBlock par_ = type_.getParentType();
        String format_;
        if (par_ != null) {
            String gene_ = par_.getGenericString();
            if (ExecInherits.correctNbParameters(name_, _context)) {
                format_ = ExecInherits.quickFormat(_formatted, gene_);
            } else {
                format_ = par_.getFullName();
            }
        } else {
            format_ = "";
        }
        AccessEnum acc_ = type_.getAccess();
        boolean st_ = type_.withoutInstance();
        if (type_ instanceof ExecInterfaceBlock) {
            ClassMetaInfo cl_ = new ClassMetaInfo(name_, type_.getImportedDirectGenericSuperInterfaces(), format_, inners_,
                    infosFields_, infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, ClassCategory.INTERFACE, st_, acc_);
            cl_.setFileName(fileName_);
            cl_.formatted(_formatted);
            cl_.getBlocsInfos().addAllElts(infosBlock_);
            return cl_;
        }
        if (type_ instanceof ExecAnnotationBlock) {
            ClassMetaInfo cl_ = new ClassMetaInfo(name_, new StringList(), format_, inners_,
                    infosFields_, infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, ClassCategory.ANNOTATION, st_, acc_);
            cl_.setFileName(fileName_);
            cl_.formatted(_formatted);
            cl_.getBlocsInfos().addAllElts(infosBlock_);
            return cl_;
        }
        ClassCategory cat_ = ClassCategory.CLASS;
        if (type_ instanceof ExecEnumBlock) {
            cat_ = ClassCategory.ENUM;
        }
        boolean abs_ = true;
        boolean final_ = true;
        if (type_ instanceof ExecClassBlock) {
            abs_ = ((ExecClassBlock)type_).isAbstractType();
            final_ = ((ExecClassBlock)type_).isFinalType();
        } else if (type_ instanceof ExecRecordBlock) {
            abs_ = false;
            if (((ExecRecordBlock)type_).isMutable()) {
                cat_ = ClassCategory.SPE_MU_CLASS;
            } else {
                cat_ = ClassCategory.SPE_CLASS;
            }
        }
        String superClass_ = type_.getImportedDirectGenericSuperClass();
        StringList superInterfaces_ = type_.getImportedDirectGenericSuperInterfaces();
        ClassMetaInfo cl_ = new ClassMetaInfo(name_, superClass_, superInterfaces_, format_, inners_,
                infosFields_, infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, cat_, abs_, st_, final_, acc_);
        cl_.setFileName(fileName_);
        cl_.formatted(_formatted);
        cl_.getBlocsInfos().addAllElts(infosBlock_);
        return cl_;
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
        CustList<MethodMetaInfo> infos_;
        infos_ = new CustList<MethodMetaInfo>();
        CustList<FieldMetaInfo> infosFields_;
        infosFields_ = new CustList<FieldMetaInfo>();
        CustList<ConstructorMetaInfo> infosConst_;
        infosConst_ = new CustList<ConstructorMetaInfo>();
        StringList inners_ = new StringList();
        boolean existCtor_ = false;
        CustList<MethodMetaInfo> infosExplicits_;
        infosExplicits_ = new CustList<MethodMetaInfo>();
        CustList<MethodMetaInfo> infosImplicits_;
        infosImplicits_ = new CustList<MethodMetaInfo>();
        CustList<MethodMetaInfo> infosTrues_;
        infosTrues_ = new CustList<MethodMetaInfo>();
        CustList<MethodMetaInfo> infosFalses_;
        infosFalses_ = new CustList<MethodMetaInfo>();
        ExecFormattedRootBlock formatted_ = new ExecFormattedRootBlock((ExecRootBlock) null, _name);
        for (StandardField f: _type.getFields()) {
            String ret_ = f.getImportedClassName();
            String decl_ = _type.getFullName();
            FieldMetaInfo met_ = new FieldMetaInfo(f.getFieldName(), ret_, decl_, formatted_);
            infosFields_.add(met_);
        }
        for (StandardMethod m: _type.getMethods()) {
            String decl_ = _type.getFullName();
            MethodMetaInfo met_ = new MethodMetaInfo(m, decl_, formatted_);
            infos_.add(met_);
        }
        for (StandardConstructor d: _type.getConstructors()) {
            existCtor_ = true;
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_context,_type,d,_name, formatted_);
            infosConst_.add(met_);
        }
        if (!existCtor_) {
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_context,_type,null,_name, formatted_);
            infosConst_.add(met_);
        }
        boolean st_ = _type.withoutInstance();
        if (_type instanceof StandardInterface) {
            return new ClassMetaInfo(_name, ((StandardInterface)_type).getDirectInterfaces(), "",inners_,infosFields_,infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, ClassCategory.INTERFACE,st_,AccessEnum.PUBLIC);
        }
        ClassCategory cat_ = ClassCategory.CLASS;
        boolean abs_ = ((StandardClass) _type).isAbstractStdType();
        boolean final_ = ((StandardClass) _type).isFinalStdType();
        String superClass_ = ((StandardClass) _type).getSuperClass();
        StringList superInterfaces_ = _type.getDirectInterfaces();
        return new ClassMetaInfo(_name, superClass_, superInterfaces_, "",inners_,infosFields_,infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, cat_, abs_, st_, final_,AccessEnum.PUBLIC);
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
