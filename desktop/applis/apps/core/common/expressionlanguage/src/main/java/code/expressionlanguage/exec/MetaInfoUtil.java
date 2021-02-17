package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.inherits.Templates;
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
            return getCustomClassMetaInfo(c, _name, _context);
        }
        return new ClassMetaInfo(_context.getStandards().getContent().getCoreNames().getAliasVoid(),_context, ClassCategory.VOID,"");
    }

    public static ClassMetaInfo getCustomClassMetaInfo(ExecRootBlock _type, String _name, ContextEl _context) {
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
        String fileName_ = _type.getFile().getFileName();
        StringList inners_ = new StringList();
        boolean existCtor_ = false;
        for (ExecRootBlock b: _type.getChildrenTypes()) {
            inners_.add(b.getFullName());
        }
        for (ExecInfoBlock b: _type.getAllFields()) {
            String ret_ = b.getImportedClassName();
            boolean staticElement_ = b.isStaticField();
            boolean finalElement_ = b.isFinalField();

            String idType_ = _type.getFullName();
            String formCl_ = tryFormatType(idType_, _name, _context);
            for (String f: b.getFieldName()) {
                FieldMetaInfo met_ = new FieldMetaInfo(_name, f, ret_, staticElement_, finalElement_, b.getAccess(), formCl_);
                met_.setFileName(fileName_);
                met_.setAnnotableBlock(b);
                met_.setDeclaring(_type);
                infosFields_.add(met_);
            }
        }
        for (ExecBlock b: _type.getAllFct()) {
            if (b instanceof ExecOverridableBlock) {
                ExecOverridableBlock method_ = (ExecOverridableBlock) b;
                MethodId id_ = method_.getId();
                String ret_ = method_.getImportedReturnType();
                boolean param_ = id_.getKind() == MethodAccessKind.STATIC_CALL || method_.getKind() == ExecMethodKind.EXPLICIT_CAST || method_.getKind() == ExecMethodKind.IMPLICIT_CAST
                        || method_.getKind() == ExecMethodKind.TRUE_OPERATOR  || method_.getKind() == ExecMethodKind.FALSE_OPERATOR;
                MethodId fid_ = tryFormatId(_name, _context, id_);
                String idType_ = _type.getFullName();
                String formCl_ = tryFormatType(idType_, _name, _context);
                String idCl_ = _type.getFullName();
                if (param_) {
                    idCl_ = _name;
                }
                MethodMetaInfo met_ = new MethodMetaInfo(_name,method_.getAccess(), idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                met_.setCallee(method_);
                met_.pair(_type,method_);
                met_.setFileName(fileName_);
                met_.setExpCast(method_.getKind() == ExecMethodKind.EXPLICIT_CAST || method_.getKind() == ExecMethodKind.IMPLICIT_CAST
                        || method_.getKind() == ExecMethodKind.TRUE_OPERATOR  || method_.getKind() == ExecMethodKind.FALSE_OPERATOR);
                infos_.add(met_);
                if (method_.getKind() == ExecMethodKind.EXPLICIT_CAST) {
                    met_ = new MethodMetaInfo(_name,method_.getAccess(), idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                    met_.setCallee(method_);
                    met_.pair(_type,method_);
                    met_.setFileName(fileName_);
                    met_.setExpCast(true);
                    infosExplicits_.add(met_);
                }
                if (method_.getKind() == ExecMethodKind.IMPLICIT_CAST) {
                    met_ = new MethodMetaInfo(_name,method_.getAccess(), idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                    met_.setCallee(method_);
                    met_.pair(_type,method_);
                    met_.setFileName(fileName_);
                    met_.setExpCast(true);
                    infosImplicits_.add(met_);
                }
                if (method_.getKind() == ExecMethodKind.TRUE_OPERATOR) {
                    met_ = new MethodMetaInfo(_name,method_.getAccess(), idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                    met_.setCallee(method_);
                    met_.pair(_type,method_);
                    met_.setFileName(fileName_);
                    met_.setExpCast(true);
                    infosTrues_.add(met_);
                }
                if (method_.getKind() == ExecMethodKind.FALSE_OPERATOR) {
                    met_ = new MethodMetaInfo(_name,method_.getAccess(), idCl_, id_, method_.getModifier(), ret_, fid_, formCl_);
                    met_.setCallee(method_);
                    met_.pair(_type,method_);
                    met_.setFileName(fileName_);
                    met_.setExpCast(true);
                    infosFalses_.add(met_);
                }
            }
            if (b instanceof ExecInitBlock) {
                ExecInitBlock method_ = (ExecInitBlock) b;
                MethodId id_ = method_.getId();
                String ret_ = _context.getStandards().getContent().getCoreNames().getAliasVoid();
                String idType_ = _type.getFullName();
                String formCl_ = tryFormatType(idType_, _name, _context);
                String idCl_ = _type.getFullName();
                MethodModifier mod_;
                if (method_ instanceof ExecInstanceBlock) {
                    mod_ = MethodModifier.FINAL;
                } else {
                    mod_ = MethodModifier.STATIC;
                }
                MethodMetaInfo met_ = new MethodMetaInfo(_name,idCl_, id_,mod_, ret_, formCl_);
                met_.setCallee(method_);
                met_.setFileName(fileName_);
                met_.pair(_type,null);
                infos_.add(met_);
                met_ = new MethodMetaInfo(_name,idCl_, id_,mod_, ret_, formCl_);
                met_.setCallee(method_);
                met_.setFileName(fileName_);
                met_.pair(_type,null);
                infosBlock_.add(met_);
            }
            if (b instanceof ExecAnnotationMethodBlock) {
                ExecAnnotationMethodBlock method_ = (ExecAnnotationMethodBlock) b;
                MethodId id_ = method_.getId();
                String ret_ = method_.getImportedReturnType();
                String formCl_ = _type.getFullName();
                MethodMetaInfo met_ = new MethodMetaInfo(_name, AccessEnum.PUBLIC,_type.getFullName(), id_, MethodModifier.ABSTRACT, ret_, id_, formCl_);
                met_.setCallee(method_);
                met_.pair(_type,method_);
                met_.setFileName(fileName_);
                infos_.add(met_);
            }
            if (b instanceof ExecConstructorBlock) {
                existCtor_ = true;
                ExecConstructorBlock method_ = (ExecConstructorBlock) b;
                ConstructorId id_ = method_.getGenericId(_type.getGenericString());
                String ret_ = method_.getImportedReturnType();
                String idType_ = _type.getFullName();
                String formCl_ = tryFormatType(idType_, _name, _context);
                ConstructorId fid_ = tryFormatId(_name, _context, id_);
                ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, method_.getAccess(), id_, ret_, fid_, formCl_);
                met_.pair(_type,method_);
                met_.setFileName(fileName_);
                infosConst_.add(met_);
            }
        }
        if (!existCtor_) {
            ConstructorId id_ = new ConstructorId(_name, new StringList(), false);
            AccessEnum acc_ = _type.getAccess();
            String ret_ = _context.getStandards().getContent().getCoreNames().getAliasVoid();
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, acc_, id_, ret_, id_, _name);
            met_.setFileName(fileName_);
            met_.pair(_type,null);
            infosConst_.add(met_);
        }
        if (_type instanceof ExecEnumBlock) {
            String valueOf_ = _context.getStandards().getContent().getPredefTypes().getAliasEnumPredValueOf();
            String values_ = _context.getStandards().getContent().getPredefTypes().getAliasEnumValues();
            String string_ = _context.getStandards().getContent().getCharSeq().getAliasString();
            MethodId id_ = new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_));
            String ret_ = _type.getWildCardString();
            String decl_ = _type.getFullName();
            MethodMetaInfo met_ = new MethodMetaInfo(_name,AccessEnum.PUBLIC,decl_, id_, MethodModifier.STATIC, ret_, id_, decl_);
            met_.setFileName(fileName_);
            met_.pair(_type,null);
            infos_.add(met_);
            id_ = new MethodId(MethodAccessKind.STATIC, values_, new StringList());
            ret_ = StringExpUtil.getPrettyArrayType(ret_);
            met_ = new MethodMetaInfo(_name,AccessEnum.PUBLIC,decl_, id_, MethodModifier.STATIC, ret_, id_, decl_);
            met_.setFileName(fileName_);
            met_.pair(_type,null);
            infos_.add(met_);
        }
        ExecRootBlock par_ = _type.getParentType();
        String format_;
        if (par_ != null) {
            String gene_ = par_.getGenericString();
            if (ExecInherits.correctNbParameters(_name, _context)) {
                format_ = ExecInherits.quickFormat(_type,_name, gene_);
            } else {
                format_ = par_.getFullName();
            }
        } else {
            format_ = "";
        }
        AccessEnum acc_ = _type.getAccess();
        boolean st_ = _type.withoutInstance();
        if (_type instanceof ExecInterfaceBlock) {
            ClassMetaInfo cl_ = new ClassMetaInfo(_name, _type.getImportedDirectGenericSuperInterfaces(), format_, inners_,
                    infosFields_, infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, ClassCategory.INTERFACE, st_, acc_);
            cl_.setFileName(fileName_);
            cl_.setRootBlock(_type);
            cl_.getBlocsInfos().addAllElts(infosBlock_);
            return cl_;
        }
        if (_type instanceof ExecAnnotationBlock) {
            ClassMetaInfo cl_ = new ClassMetaInfo(_name, new StringList(), format_, inners_,
                    infosFields_, infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, ClassCategory.ANNOTATION, st_, acc_);
            cl_.setFileName(fileName_);
            cl_.setRootBlock(_type);
            cl_.getBlocsInfos().addAllElts(infosBlock_);
            return cl_;
        }
        ClassCategory cat_ = ClassCategory.CLASS;
        if (_type instanceof ExecEnumBlock) {
            cat_ = ClassCategory.ENUM;
        }
        boolean abs_ = true;
        boolean final_ = true;
        if (_type instanceof ExecClassBlock) {
            abs_ = ((ExecClassBlock)_type).isAbstractType();
            final_ = ((ExecClassBlock)_type).isFinalType();
        } else if (_type instanceof ExecRecordBlock) {
            abs_ = false;
            if (((ExecRecordBlock)_type).isMutable()) {
                cat_ = ClassCategory.SPE_MU_CLASS;
            } else {
                cat_ = ClassCategory.SPE_CLASS;
            }
        }
        String superClass_ = _type.getImportedDirectGenericSuperClass();
        StringList superInterfaces_ = _type.getImportedDirectGenericSuperInterfaces();
        ClassMetaInfo cl_ = new ClassMetaInfo(_name, superClass_, superInterfaces_, format_, inners_,
                infosFields_, infosExplicits_,infosImplicits_,infosTrues_,infosFalses_,infos_, infosConst_, cat_, abs_, st_, final_, acc_);
        cl_.setFileName(fileName_);
        cl_.setRootBlock(_type);
        cl_.getBlocsInfos().addAllElts(infosBlock_);
        return cl_;
    }

    public static ConstructorId tryFormatId(String _name, ContextEl _context, ConstructorId _id) {
        ConstructorId fid_;
        if (ExecInherits.correctNbParameters(_name, _context)) {
            fid_ = _id.reflectFormat(_name, _context);
        } else {
            fid_ = _id;
        }
        return fid_;
    }

    public static String tryFormatType(String _idType, String _name, ContextEl _context) {
        String formCl_ = _idType;
        if (ExecInherits.correctNbParameters(_name, _context)) {
            formCl_ = _name;
        }
        return formCl_;
    }

    public static MethodId tryFormatId(String _name, ContextEl _context, MethodId _id) {
        MethodId fid_;
        if (ExecInherits.correctNbParameters(_name, _context)) {
            fid_ = _id.reflectFormat(_name, _context);
        } else {
            fid_ = _id;
        }
        return fid_;
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
        if (StringUtil.quickEq(_name, Templates.SUB_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.SUB_TYPE)) {
            StringList upperBounds_ = new StringList(_name.substring(Templates.SUB_TYPE.length()));
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.SUP_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList(_name.substring(Templates.SUB_TYPE.length()));
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.PREFIX_VAR_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.VARIABLE,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith("~")) {
            StringList upperBounds_ = new StringList(_name.substring("~".length()));
            StringList lowerBounds_ = new StringList(_name.substring("~".length()));
            return new ClassMetaInfo(_name, ClassCategory.REF_TYPE,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(Templates.ARR_BEG_STRING)&&_name.contains(Templates.PREFIX_VAR_TYPE)) {
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
        if (_name.startsWith(Templates.ARR_BEG_STRING)) {
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
        String k_ = _type.getFullName();
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
        for (StandardField f: _type.getFields()) {
            String ret_ = f.getImportedClassName();
            String decl_ = _type.getFullName();
            FieldMetaInfo met_ = new FieldMetaInfo(k_, f.getFieldName(), ret_, true, true, AccessEnum.PUBLIC, decl_);
            infosFields_.add(met_);
        }
        for (StandardMethod m: _type.getMethods()) {
            MethodId id_ = m.getId();
            String ret_ = m.getImportedReturnType();
            String decl_ = _type.getFullName();
            MethodMetaInfo met_ = new MethodMetaInfo(_name,AccessEnum.PUBLIC,decl_, id_, m.getModifier(), ret_, id_, decl_);
            met_.setStdCallee(m);
            infos_.add(met_);
        }
        for (StandardConstructor d: _type.getConstructors()) {
            existCtor_ = true;
            ConstructorId id_ = new ConstructorId(_name, d.getImportedParametersTypes(), d.isVarargs());
            String decl_ = _type.getFullName();
            String ret_ = d.getImportedReturnType();
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, AccessEnum.PUBLIC, id_, ret_, id_, decl_);
            met_.setStandardType(_type);
            infosConst_.add(met_);
        }
        if (!existCtor_) {
            ConstructorId id_ = new ConstructorId(_name, new StringList(), false);
            String ret_ = _context.getStandards().getContent().getCoreNames().getAliasVoid();
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(_name, AccessEnum.PUBLIC, id_, ret_, id_, _name);
            met_.setStandardType(_type);
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
        String currentClassName_ = call_.getGlobalClass();
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
        String curClass_ = _stackCall.getLastPage().getGlobalClass();
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
                _stackCall.setCallingState(new NotInitializedClass(idClass_,c_, _arg));
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
