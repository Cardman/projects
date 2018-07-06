package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;

public class AliasReflection {
    private String aliasClass;
    private String aliasGetClass;
    private String aliasGetDeclaredMethods;
    private String aliasGetDeclaredConstructors;
    private String aliasGetDeclaredFields;
    private String aliasGetSuperClass;
    private String aliasGetGenericSuperClass;
    private String aliasGetInterfaces;
    private String aliasGetGenericInterfaces;
    private String aliasGetComponentType;
    private String aliasGetParameterTypes;
    private String aliasGetTypeParameters;
    private String aliasGetNameParameters;
    private String aliasGetGenericReturnType;
    private String aliasGetReturnType;
    private String aliasIsFinal;
    private String aliasIsStatic;
    private String aliasIsVarargs;
    private String aliasIsNormal;
    private String aliasIsPublic;
    private String aliasIsProtected;
    private String aliasIsPackage;
    private String aliasIsPrivate;
    private String aliasIsClass;
    private String aliasIsInterface;
    private String aliasIsEnum;
    private String aliasIsPrimitive;
    private String aliasIsArray;
    private String aliasIsInstance;
    private String aliasIsAssignableFrom;
    private String aliasInit;
    private String aliasDefaultInstance;
    private String aliasEnumValueOf;
    private String aliasGetEnumConstants;
    private String aliasGetGenericBounds;
    private String aliasGetBounds;
    private String aliasArrayNewInstance;
    private String aliasArrayGet;
    private String aliasArraySet;
    private String aliasArrayGetLength;
    private String aliasMakeGeneric;
    private String aliasGetAllClasses;
    private String aliasConstructor;
    private String aliasField;
    private String aliasMethod;
    private String aliasInvoke;
    private String aliasNewInstance;
    private String aliasIsAbstract;
    private String aliasIsPolymorph;
    private String aliasSetPolymorph;
    private String aliasGetName;
    private String aliasGetField;
    private String aliasSetField;
    private String aliasForName;
    private String aliasGetDeclaringClass;
    public void build(LgNames _stds) {
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardClass stdcl_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        String aliasObject = _stds.getAliasObject();
        String aliasString = _stds.getAliasString();
        String aliasPrimBoolean = _stds.getAliasPrimBoolean();
        String aliasVoid = _stds.getAliasVoid();
        stdcl_ = new StandardClass(aliasClass, fields_, constructors_, methods_, aliasObject , MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasGetClass, params_, aliasClass, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString,aliasPrimBoolean);
        method_ = new StandardMethod(aliasForName, params_, aliasClass, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean,aliasClass);
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasConstructor), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString,aliasPrimBoolean,aliasPrimBoolean,aliasClass);
        method_ = new StandardMethod(aliasGetDeclaredMethods, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasConstructor), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredMethods, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClass);
        method_ = new StandardMethod(aliasMakeGeneric, params_, aliasClass, true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAllClasses, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasClass, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasConstructor, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasNewInstance, params_, aliasObject, true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasConstructor, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasField, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasGetField, params_, aliasObject, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasSetField, params_, aliasVoid, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasField, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasMethod, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasInvoke, params_, aliasObject, true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean);
        method_ = new StandardMethod(aliasSetPolymorph, params_, aliasVoid, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPolymorph, params_, aliasPrimBoolean, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAbstract, params_, aliasPrimBoolean, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasMethod, stdcl_);
    }
    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = LgNames.getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasClass_ = ref_.aliasClass;
        String aliasMethod_ = ref_.aliasMethod;
        String aliasConstructor_ = ref_.aliasConstructor;
        String aliasField_ = ref_.aliasField;
        if (StringList.quickEq(type_, ref_.aliasMethod)) {
            if (StringList.quickEq(name_, ref_.aliasIsPolymorph)) {
                MethodMetaInfo method_ = (MethodMetaInfo) _struct;
                result_.setResult(new BooleanStruct(method_.isPolymorph()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsAbstract)) {
                MethodMetaInfo method_ = (MethodMetaInfo) _struct;
                result_.setResult(new BooleanStruct(method_.isAbstract()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasSetPolymorph)) {
                MethodMetaInfo method_ = (MethodMetaInfo) _struct;
                Boolean poly_ = (Boolean) args_[0].getInstance();
                method_.setPolymorph(poly_);
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
        }
        if (StringList.quickEq(type_, aliasClass_)) {
            if (StringList.quickEq(name_, ref_.aliasGetName)) {
                result_.setResult(new StringStruct(((ClassMetaInfo)_struct).getName()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetAllClasses)) {
                CustList<ClassMetaInfo> classes_  = new CustList<ClassMetaInfo>();
                for (EntryCust<String, RootBlock> c: _cont.getClasses().getClassesBodies().entryList()) {
                    ObjectNotNullMap<MethodId, MethodMetaInfo> infos_;
                    infos_ = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
                    StringMap<FieldMetaInfo> infosFields_;
                    infosFields_ = new StringMap<FieldMetaInfo>();
                    ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> infosConst_;
                    infosConst_ = new ObjectNotNullMap<ConstructorId, ConstructorMetaInfo>();
                    String k_ = c.getKey();
                    RootBlock clblock_ = c.getValue();
                    String forName_ = Templates.getGenericString(k_, _cont);
                    CustList<Block> bl_ = Classes.getDirectChildren(clblock_);
                    for (Block b: bl_) {
                        if (b instanceof InfoBlock) {
                            InfoBlock method_ = (InfoBlock) b;
                            String m_ = method_.getFieldName();
                            String ret_ = method_.getImportedClassName();
                            boolean enumElement_ = b instanceof ElementBlock;
                            boolean staticElement_ = method_.isStaticField();
                            boolean finalElement_ = method_.isFinalField();
                            FieldMetaInfo met_ = new FieldMetaInfo(forName_, m_, ret_, staticElement_, finalElement_, enumElement_);
                            infosFields_.put(m_, met_);
                        }
                        if (b instanceof MethodBlock) {
                            MethodBlock method_ = (MethodBlock) b;
                            MethodId id_ = method_.getId();
                            String ret_ = method_.getImportedReturnType();
                            AccessEnum acc_ = method_.getAccess();
                            MethodMetaInfo met_ = new MethodMetaInfo(acc_,method_.getDeclaringType(), id_, method_.getModifier(), ret_);
                            infos_.put(id_, met_);
                        }
                        if (b instanceof ConstructorBlock) {
                            ConstructorBlock method_ = (ConstructorBlock) b;
                            ConstructorId id_ = method_.getGenericId();
                            ConstructorMetaInfo met_ = new ConstructorMetaInfo(forName_, id_);
                            infosConst_.put(id_, met_);
                        }
                    }
                    if (clblock_ instanceof InterfaceBlock) {
                        classes_.add(new ClassMetaInfo(forName_, ((InterfaceBlock)clblock_).getImportedDirectSuperInterfaces(), infosFields_,infos_, infosConst_, ClassCategory.INTERFACE));
                        continue;
                    }
                    ClassCategory cat_ = ClassCategory.CLASS;
                    if (clblock_ instanceof EnumBlock) {
                        cat_ = ClassCategory.ENUM;
                    } else if (clblock_ instanceof InterfaceBlock) {
                        cat_ = ClassCategory.INTERFACE;
                    }
                    boolean abs_ = clblock_.isAbstractType();
                    boolean final_ = clblock_.isFinalType();
                    classes_.add(new ClassMetaInfo(forName_, ((UniqueRootedBlock) clblock_).getImportedDirectGenericSuperClass(), infosFields_,infos_, infosConst_, cat_, abs_, final_));
                }
                for (EntryCust<String, StandardType> c: _cont.getStandards().getStandards().entryList()) {
                    ObjectNotNullMap<MethodId, MethodMetaInfo> infos_;
                    infos_ = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
                    StringMap<FieldMetaInfo> infosFields_;
                    infosFields_ = new StringMap<FieldMetaInfo>();
                    ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> infosConst_;
                    infosConst_ = new ObjectNotNullMap<ConstructorId, ConstructorMetaInfo>();
                    String k_ = c.getKey();
                    String forName_ = Templates.getGenericString(k_, _cont);
                    StandardType clblock_ = c.getValue();
                    for (StandardField f: clblock_.getFields().values()) {
                        String m_ = f.getFieldName();
                        String ret_ = f.getClassName();
                        boolean staticElement_ = f.isStaticField();
                        boolean finalElement_ = f.isFinalField();
                        FieldMetaInfo met_ = new FieldMetaInfo(k_, m_, ret_, staticElement_, finalElement_, false);
                        infosFields_.put(m_, met_);
                    }
                    for (StandardMethod m: clblock_.getMethods().values()) {
                        MethodId id_ = m.getId();
                        String ret_ = m.getImportedReturnType();
                        AccessEnum acc_ = m.getAccess();
                        MethodMetaInfo met_ = new MethodMetaInfo(acc_,m.getDeclaringType(), id_, m.getModifier(), ret_);
                        infos_.put(id_, met_);
                    }
                    for (StandardConstructor d: clblock_.getConstructors()) {
                        ConstructorId id_ = d.getGenericId();
                        ConstructorMetaInfo met_ = new ConstructorMetaInfo(forName_, id_);
                        infosConst_.put(id_, met_);
                    }
                    if (clblock_ instanceof StandardInterface) {
                        classes_.add(new ClassMetaInfo(forName_, ((StandardInterface)clblock_).getDirectInterfaces(), infosFields_,infos_, infosConst_, ClassCategory.INTERFACE));
                        continue;
                    }
                    ClassCategory cat_ = ClassCategory.CLASS;
                    if (clblock_ instanceof StandardInterface) {
                        cat_ = ClassCategory.INTERFACE;
                    }
                    boolean abs_ = clblock_.isAbstractType();
                    boolean final_ = clblock_.isFinalType();
                    classes_.add(new ClassMetaInfo(forName_, ((StandardClass) clblock_).getSuperClass(_cont), infosFields_,infos_, infosConst_, cat_, abs_, final_));
                }
                Struct[] ctorsArr_ = new Struct[classes_.size()];
                int index_ = 0;
                for (ClassMetaInfo e: classes_) {
                    ctorsArr_[index_] = e;
                    index_++;
                }
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                ArrayStruct str_ = new ArrayStruct(ctorsArr_, className_);
                result_.setResult(str_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetClass)) {
                Struct str_ = args_[0];
                if (str_.isNull()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                } else {
                    String className_ = lgNames_.getStructClassName(str_, _cont);
                    result_.setResult(_cont.getExtendedClassMetaInfo(className_));
                }
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetDeclaredConstructors)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> ctors_;
                ctors_ = cl_.getConstructorsInfos();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasConstructor_);
                if (args_.length == 0) {
                    Struct[] ctorsArr_ = new Struct[ctors_.size()];
                    int index_ = 0;
                    for (EntryCust<ConstructorId, ConstructorMetaInfo> e: ctors_.entryList()) {
                        ctorsArr_[index_] = e.getValue();
                        index_++;
                    }
                    ArrayStruct str_ = new ArrayStruct(ctorsArr_, className_);
                    result_.setResult(str_);
                    return result_;
                }
                Boolean vararg_ = (Boolean) args_[0].getInstance();
                StringList classesNames_ = new StringList();
                for (Struct s: ((Struct[])args_[1].getInstance())) {
                    classesNames_.add(((ClassMetaInfo)s).getName());
                }
                CustList<ConstructorMetaInfo> candidates_;
                candidates_ = new CustList<ConstructorMetaInfo>();
                String instClassName_ = cl_.getName();
                ConstructorId idToSearch_ = new ConstructorId(instClassName_, classesNames_, vararg_);
                for (EntryCust<ConstructorId, ConstructorMetaInfo> e: ctors_.entryList()) {
                    ConstructorId id_ = e.getKey();
                    if (id_.format(instClassName_, _cont).eq(idToSearch_)) {
                        candidates_.add(e.getValue());
                    }
                }
                if (ctors_.isEmpty()) {
                    if (classesNames_.isEmpty() && !vararg_) {
                        candidates_.add(new ConstructorMetaInfo(instClassName_, idToSearch_));
                    }
                }
                Struct[] ctorsArr_ = new Struct[candidates_.size()];
                int index_ = 0;
                for (ConstructorMetaInfo c: candidates_) {
                    ctorsArr_[index_] = c;
                    index_++;
                }
                ArrayStruct str_ = new ArrayStruct(ctorsArr_, className_);
                result_.setResult(str_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetDeclaredMethods)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                ObjectNotNullMap<MethodId, MethodMetaInfo> methods_;
                methods_ = cl_.getMethodsInfos();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasMethod_);
                if (args_.length == 0) {
                    Struct[] methodsArr_ = new Struct[methods_.size()];
                    int index_ = 0;
                    for (EntryCust<MethodId, MethodMetaInfo> e: methods_.entryList()) {
                        methodsArr_[index_] = e.getValue();
                        index_++;
                    }
                    ArrayStruct str_ = new ArrayStruct(methodsArr_, className_);
                    result_.setResult(str_);
                    return result_;
                }
                String methodName_ = (String) args_[0].getInstance();
                Boolean static_ = (Boolean) args_[1].getInstance();
                Boolean vararg_ = (Boolean) args_[2].getInstance();
                StringList classesNames_ = new StringList();
                for (Struct s: ((Struct[])args_[3].getInstance())) {
                    classesNames_.add(((ClassMetaInfo)s).getName());
                }
                CustList<MethodMetaInfo> candidates_;
                candidates_ = new CustList<MethodMetaInfo>();
                String instClassName_ = cl_.getName();
                MethodId idToSearch_ = new MethodId(static_, methodName_, classesNames_, vararg_);
                for (EntryCust<MethodId, MethodMetaInfo> e: methods_.entryList()) {
                    MethodId id_ = e.getKey();
                    if (id_.format(instClassName_, _cont).eq(idToSearch_)) {
                        candidates_.add(e.getValue());
                    }
                }
                Struct[] methodsArr_ = new Struct[candidates_.size()];
                int index_ = 0;
                for (MethodMetaInfo c: candidates_) {
                    methodsArr_[index_] = c;
                    index_++;
                }
                ArrayStruct str_ = new ArrayStruct(methodsArr_, className_);
                result_.setResult(str_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetDeclaredFields)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                StringMap<FieldMetaInfo> fields_;
                fields_ = cl_.getFieldsInfos();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasField_);
                if (args_.length == 0) {
                    Struct[] ctorsArr_ = new Struct[fields_.size()];
                    int index_ = 0;
                    for (EntryCust<String, FieldMetaInfo> e: fields_.entryList()) {
                        ctorsArr_[index_] = e.getValue();
                        index_++;
                    }
                    ArrayStruct str_ = new ArrayStruct(ctorsArr_, className_);
                    result_.setResult(str_);
                    return result_;
                }
                String fieldName_ = (String) args_[0].getInstance();
                FieldMetaInfo meta_ = fields_.getVal(fieldName_);
                Struct[] ctorsArr_;
                if (meta_ != null) {
                    ctorsArr_ = new Struct[1];
                    ctorsArr_[0] = meta_;
                } else {
                    ctorsArr_ = new Struct[0];
                }
                ArrayStruct str_ = new ArrayStruct(ctorsArr_, className_);
                result_.setResult(str_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasMakeGeneric)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                StringList classesNames_ = new StringList();
                for (Struct s: ((Struct[])args_[0].getInstance())) {
                    classesNames_.add(((ClassMetaInfo)s).getName());
                }
                String res_ = Templates.getMadeVarTypes(cl_.getName(), classesNames_, _cont);
                if (res_ == null) {
                    result_.setResult(NullStruct.NULL_VALUE);
                } else {
                    result_.setResult(_cont.getExtendedClassMetaInfo(res_));
                }
                return result_;
            }
        }
        return result_;
    }
    public String getAliasClass() {
        return aliasClass;
    }
    public void setAliasClass(String _aliasClass) {
        aliasClass = _aliasClass;
    }
    public String getAliasGetClass() {
        return aliasGetClass;
    }
    public void setAliasGetClass(String _aliasGetClass) {
        aliasGetClass = _aliasGetClass;
    }
    public String getAliasGetDeclaredMethods() {
        return aliasGetDeclaredMethods;
    }
    public void setAliasGetDeclaredMethods(String _aliasGetDeclaredMethods) {
        aliasGetDeclaredMethods = _aliasGetDeclaredMethods;
    }
    public String getAliasGetDeclaredConstructors() {
        return aliasGetDeclaredConstructors;
    }
    public void setAliasGetDeclaredConstructors(String _aliasGetDeclaredConstructors) {
        aliasGetDeclaredConstructors = _aliasGetDeclaredConstructors;
    }
    public String getAliasGetDeclaredFields() {
        return aliasGetDeclaredFields;
    }
    public void setAliasGetDeclaredFields(String _aliasGetDeclaredFields) {
        aliasGetDeclaredFields = _aliasGetDeclaredFields;
    }
    public String getAliasGetSuperClass() {
        return aliasGetSuperClass;
    }
    public void setAliasGetSuperClass(String _aliasGetSuperClass) {
        aliasGetSuperClass = _aliasGetSuperClass;
    }
    public String getAliasGetGenericSuperClass() {
        return aliasGetGenericSuperClass;
    }
    public void setAliasGetGenericSuperClass(String _aliasGetGenericSuperClass) {
        aliasGetGenericSuperClass = _aliasGetGenericSuperClass;
    }
    public String getAliasGetInterfaces() {
        return aliasGetInterfaces;
    }
    public void setAliasGetInterfaces(String _aliasGetInterfaces) {
        aliasGetInterfaces = _aliasGetInterfaces;
    }
    public String getAliasGetGenericInterfaces() {
        return aliasGetGenericInterfaces;
    }
    public void setAliasGetGenericInterfaces(String _aliasGetGenericInterfaces) {
        aliasGetGenericInterfaces = _aliasGetGenericInterfaces;
    }
    public String getAliasGetComponentType() {
        return aliasGetComponentType;
    }
    public void setAliasGetComponentType(String _aliasGetComponentType) {
        aliasGetComponentType = _aliasGetComponentType;
    }
    public String getAliasGetParameterTypes() {
        return aliasGetParameterTypes;
    }
    public void setAliasGetParameterTypes(String _aliasGetParameterTypes) {
        aliasGetParameterTypes = _aliasGetParameterTypes;
    }
    public String getAliasGetTypeParameters() {
        return aliasGetTypeParameters;
    }
    public void setAliasGetTypeParameters(String _aliasGetTypeParameters) {
        aliasGetTypeParameters = _aliasGetTypeParameters;
    }
    public String getAliasGetNameParameters() {
        return aliasGetNameParameters;
    }
    public void setAliasGetNameParameters(String _aliasGetNameParameters) {
        aliasGetNameParameters = _aliasGetNameParameters;
    }
    public String getAliasGetGenericReturnType() {
        return aliasGetGenericReturnType;
    }
    public void setAliasGetGenericReturnType(String _aliasGetGenericReturnType) {
        aliasGetGenericReturnType = _aliasGetGenericReturnType;
    }
    public String getAliasGetReturnType() {
        return aliasGetReturnType;
    }
    public void setAliasGetReturnType(String _aliasGetReturnType) {
        aliasGetReturnType = _aliasGetReturnType;
    }
    public String getAliasIsFinal() {
        return aliasIsFinal;
    }
    public void setAliasIsFinal(String _aliasIsFinal) {
        aliasIsFinal = _aliasIsFinal;
    }
    public String getAliasIsStatic() {
        return aliasIsStatic;
    }
    public void setAliasIsStatic(String _aliasIsStatic) {
        aliasIsStatic = _aliasIsStatic;
    }
    public String getAliasIsVarargs() {
        return aliasIsVarargs;
    }
    public void setAliasIsVarargs(String _aliasIsVarargs) {
        aliasIsVarargs = _aliasIsVarargs;
    }
    public String getAliasIsNormal() {
        return aliasIsNormal;
    }
    public void setAliasIsNormal(String _aliasIsNormal) {
        aliasIsNormal = _aliasIsNormal;
    }
    public String getAliasIsPublic() {
        return aliasIsPublic;
    }
    public void setAliasIsPublic(String _aliasIsPublic) {
        aliasIsPublic = _aliasIsPublic;
    }
    public String getAliasIsProtected() {
        return aliasIsProtected;
    }
    public void setAliasIsProtected(String _aliasIsProtected) {
        aliasIsProtected = _aliasIsProtected;
    }
    public String getAliasIsPackage() {
        return aliasIsPackage;
    }
    public void setAliasIsPackage(String _aliasIsPackage) {
        aliasIsPackage = _aliasIsPackage;
    }
    public String getAliasIsPrivate() {
        return aliasIsPrivate;
    }
    public void setAliasIsPrivate(String _aliasIsPrivate) {
        aliasIsPrivate = _aliasIsPrivate;
    }
    public String getAliasIsClass() {
        return aliasIsClass;
    }
    public void setAliasIsClass(String _aliasIsClass) {
        aliasIsClass = _aliasIsClass;
    }
    public String getAliasIsInterface() {
        return aliasIsInterface;
    }
    public void setAliasIsInterface(String _aliasIsInterface) {
        aliasIsInterface = _aliasIsInterface;
    }
    public String getAliasIsEnum() {
        return aliasIsEnum;
    }
    public void setAliasIsEnum(String _aliasIsEnum) {
        aliasIsEnum = _aliasIsEnum;
    }
    public String getAliasIsPrimitive() {
        return aliasIsPrimitive;
    }
    public void setAliasIsPrimitive(String _aliasIsPrimitive) {
        aliasIsPrimitive = _aliasIsPrimitive;
    }
    public String getAliasIsArray() {
        return aliasIsArray;
    }
    public void setAliasIsArray(String _aliasIsArray) {
        aliasIsArray = _aliasIsArray;
    }
    public String getAliasIsInstance() {
        return aliasIsInstance;
    }
    public void setAliasIsInstance(String _aliasIsInstance) {
        aliasIsInstance = _aliasIsInstance;
    }
    public String getAliasIsAssignableFrom() {
        return aliasIsAssignableFrom;
    }
    public void setAliasIsAssignableFrom(String _aliasIsAssignableFrom) {
        aliasIsAssignableFrom = _aliasIsAssignableFrom;
    }
    public String getAliasInit() {
        return aliasInit;
    }
    public void setAliasInit(String _aliasInit) {
        aliasInit = _aliasInit;
    }
    public String getAliasDefaultInstance() {
        return aliasDefaultInstance;
    }
    public void setAliasDefaultInstance(String _aliasDefaultInstance) {
        aliasDefaultInstance = _aliasDefaultInstance;
    }
    public String getAliasEnumValueOf() {
        return aliasEnumValueOf;
    }
    public void setAliasEnumValueOf(String _aliasEnumValueOf) {
        aliasEnumValueOf = _aliasEnumValueOf;
    }
    public String getAliasGetEnumConstants() {
        return aliasGetEnumConstants;
    }
    public void setAliasGetEnumConstants(String _aliasGetEnumConstants) {
        aliasGetEnumConstants = _aliasGetEnumConstants;
    }
    public String getAliasGetGenericBounds() {
        return aliasGetGenericBounds;
    }
    public void setAliasGetGenericBounds(String _aliasGetGenericBounds) {
        aliasGetGenericBounds = _aliasGetGenericBounds;
    }
    public String getAliasGetBounds() {
        return aliasGetBounds;
    }
    public void setAliasGetBounds(String _aliasGetBounds) {
        aliasGetBounds = _aliasGetBounds;
    }
    public String getAliasArrayNewInstance() {
        return aliasArrayNewInstance;
    }
    public void setAliasArrayNewInstance(String _aliasArrayNewInstance) {
        aliasArrayNewInstance = _aliasArrayNewInstance;
    }
    public String getAliasArrayGet() {
        return aliasArrayGet;
    }
    public void setAliasArrayGet(String _aliasArrayGet) {
        aliasArrayGet = _aliasArrayGet;
    }
    public String getAliasArraySet() {
        return aliasArraySet;
    }
    public void setAliasArraySet(String _aliasArraySet) {
        aliasArraySet = _aliasArraySet;
    }
    public String getAliasArrayGetLength() {
        return aliasArrayGetLength;
    }
    public void setAliasArrayGetLength(String _aliasArrayGetLength) {
        aliasArrayGetLength = _aliasArrayGetLength;
    }
    public String getAliasMakeGeneric() {
        return aliasMakeGeneric;
    }
    public void setAliasMakeGeneric(String _aliasMakeGeneric) {
        aliasMakeGeneric = _aliasMakeGeneric;
    }
    public String getAliasGetAllClasses() {
        return aliasGetAllClasses;
    }
    public void setAliasGetAllClasses(String _aliasGetAllClasses) {
        aliasGetAllClasses = _aliasGetAllClasses;
    }
    public String getAliasConstructor() {
        return aliasConstructor;
    }
    public void setAliasConstructor(String _aliasConstructor) {
        aliasConstructor = _aliasConstructor;
    }
    public String getAliasField() {
        return aliasField;
    }
    public void setAliasField(String _aliasField) {
        aliasField = _aliasField;
    }
    public String getAliasMethod() {
        return aliasMethod;
    }
    public void setAliasMethod(String _aliasMethod) {
        aliasMethod = _aliasMethod;
    }
    public String getAliasInvoke() {
        return aliasInvoke;
    }
    public void setAliasInvoke(String _aliasInvoke) {
        aliasInvoke = _aliasInvoke;
    }
    public String getAliasNewInstance() {
        return aliasNewInstance;
    }
    public void setAliasNewInstance(String _aliasNewInstance) {
        aliasNewInstance = _aliasNewInstance;
    }
    public String getAliasIsAbstract() {
        return aliasIsAbstract;
    }
    public void setAliasIsAbstract(String _aliasIsAbstract) {
        aliasIsAbstract = _aliasIsAbstract;
    }
    public String getAliasIsPolymorph() {
        return aliasIsPolymorph;
    }
    public void setAliasIsPolymorph(String _aliasIsPolymorph) {
        aliasIsPolymorph = _aliasIsPolymorph;
    }
    public String getAliasSetPolymorph() {
        return aliasSetPolymorph;
    }
    public void setAliasSetPolymorph(String _aliasSetPolymorph) {
        aliasSetPolymorph = _aliasSetPolymorph;
    }
    public String getAliasGetName() {
        return aliasGetName;
    }
    public void setAliasGetName(String _aliasGetName) {
        aliasGetName = _aliasGetName;
    }
    public String getAliasGetField() {
        return aliasGetField;
    }
    public void setAliasGetField(String _aliasGetField) {
        aliasGetField = _aliasGetField;
    }
    public String getAliasSetField() {
        return aliasSetField;
    }
    public void setAliasSetField(String _aliasSetField) {
        aliasSetField = _aliasSetField;
    }
    public String getAliasForName() {
        return aliasForName;
    }
    public void setAliasForName(String _aliasForName) {
        aliasForName = _aliasForName;
    }
    public String getAliasGetDeclaringClass() {
        return aliasGetDeclaringClass;
    }
    public void setAliasGetDeclaringClass(String _aliasGetDeclaringClass) {
        aliasGetDeclaringClass = _aliasGetDeclaringClass;
    }
}
