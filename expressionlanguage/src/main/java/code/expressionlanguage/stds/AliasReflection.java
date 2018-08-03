package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.AnnotationMethodBlock;
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
import code.expressionlanguage.opers.InvokingOperation;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;

public class AliasReflection {
    private String aliasAnnotation;
    private String aliasAnnotated;
    private String aliasGetDefaultValue;
    private String aliasGetAnnotations;
    private String aliasGetAnnotationsParameters;
    private String aliasClass;
    private String aliasGetClass;
    private String aliasGetDeclaredMethods;
    private String aliasGetDeclaredConstructors;
    private String aliasGetDeclaredFields;
    private String aliasGetSuperClass;
    private String aliasGetGenericSuperClass;
    private String aliasGetInterfaces;
    private String aliasGetGenericInterfaces;
    private String aliasMakeArray;
    private String aliasGetComponentType;
    private String aliasGetTypeParameters;

    private String aliasGetParameterTypes;
    private String aliasGetParameterNames;
    private String aliasGetGenericReturnType;
    private String aliasGetReturnType;
    private String aliasGetGenericTypeArguments;
    private String aliasGetActualTypeArguments;
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
    private String aliasIsAnnotation;
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
    private String aliasGetGenericType;
    private String aliasGetType;
    private String aliasForName;
    private String aliasGetDeclaringClass;
    private String aliasInvokeTarget;
    private String aliasClassNotFoundError;
    private String aliasGetVariableOwner;
    private String aliasGetGenericVariableOwner;

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
        String aliasObject_ = _stds.getAliasObject();
        String aliasString_ = _stds.getAliasString();
        String aliasPrimBoolean_ = _stds.getAliasPrimBoolean();
        String aliasPrimInt_ = _stds.getAliasPrimInteger();
        String aliasVoid_ = _stds.getAliasVoid();
        String aliasError_ = _stds.getAliasError();
        String aliasEnum_ = _stds.getAliasEnum();
        stdcl_ = new StandardClass(aliasClass, fields_, constructors_, methods_, aliasAnnotated , MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDefaultValue, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasGetClass, params_, aliasClass, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasForName, params_, aliasClass, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInit, params_, aliasVoid_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAnnotation, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsArray, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAbstract, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEnum, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInterface, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPackage, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrimitive, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrivate, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsProtected, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPublic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasIsInstance, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClass);
        method_ = new StandardMethod(aliasIsAssignableFrom, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDefaultInstance, params_, aliasObject_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasEnumValueOf, params_, aliasEnum_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetEnumConstants, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasEnum_), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_,aliasClass);
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasConstructor), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString_,aliasPrimBoolean_,aliasPrimBoolean_,aliasClass);
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
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetSuperClass, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericSuperClass, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetInterfaces, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericInterfaces, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClass);
        method_ = new StandardMethod(aliasMakeGeneric, params_, aliasClass, true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMakeArray, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetComponentType, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetVariableOwner, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericVariableOwner, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetTypeParameters, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetBounds, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericBounds, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAllClasses, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInt_);
        method_ = new StandardMethod(aliasArrayNewInstance, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasObject_), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasArrayGetLength, params_, aliasPrimInt_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject_,aliasPrimInt_);
        method_ = new StandardMethod(aliasArrayGet, params_, aliasObject_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject_,aliasPrimInt_,aliasObject_);
        method_ = new StandardMethod(aliasArraySet, params_, aliasVoid_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasClass, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasConstructor, fields_, constructors_, methods_, aliasAnnotated, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasNewInstance, params_, aliasObject_, true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterTypes, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterNames, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString_), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaringClass, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericReturnType, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetReturnType, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetActualTypeArguments, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericTypeArguments, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPackage, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrivate, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsProtected, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPublic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVarargs, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasConstructor, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasField, fields_, constructors_, methods_, aliasAnnotated, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasGetField, params_, aliasObject_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasSetField, params_, aliasVoid_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaringClass, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPackage, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrivate, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsProtected, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPublic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStatic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetType, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericType, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasField, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasMethod, fields_, constructors_, methods_, aliasAnnotated, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasInvoke, params_, aliasObject_, true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasSetPolymorph, params_, aliasVoid_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPolymorph, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAbstract, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNormal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStatic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPackage, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrivate, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsProtected, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPublic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVarargs, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterTypes, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClass), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterNames, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString_), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaringClass, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericReturnType, params_, aliasClass, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetReturnType, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasMethod, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasInvokeTarget, fields_, constructors_, methods_, aliasError_, MethodModifier.NORMAL);
        _stds.getStandards().put(aliasInvokeTarget, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasClassNotFoundError, fields_, constructors_, methods_, aliasError_, MethodModifier.NORMAL);
        _stds.getStandards().put(aliasClassNotFoundError, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasAnnotation, fields_, constructors_, methods_, aliasObject_, MethodModifier.NORMAL);
        _stds.getStandards().put(aliasAnnotation, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasAnnotated, fields_, constructors_, methods_, aliasObject_, MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAnnotations, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasAnnotation), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAnnotationsParameters, params_, PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.getPrettyArrayType(aliasAnnotation)), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClass);
        method_ = new StandardMethod(aliasGetAnnotations, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasAnnotation), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClass);
        method_ = new StandardMethod(aliasGetAnnotationsParameters, params_, PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.getPrettyArrayType(aliasAnnotation)), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasAnnotated, stdcl_);
    }
    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = LgNames.getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasString_ = lgNames_.getAliasString();
        String aliasClass_ = ref_.aliasClass;
        String aliasMethod_ = ref_.aliasMethod;
        String aliasConstructor_ = ref_.aliasConstructor;
        String aliasField_ = ref_.aliasField;
        String aliasVoid_ = lgNames_.getAliasVoid();
        if (StringList.quickEq(type_, ref_.aliasField)) {
            if (StringList.quickEq(name_, ref_.aliasIsStatic)) {
                FieldMetaInfo class_ = (FieldMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isStaticField()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsFinal)) {
                FieldMetaInfo class_ = (FieldMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isFinalField()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPackage)) {
                FieldMetaInfo class_ = (FieldMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPackage()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPrivate)) {
                FieldMetaInfo class_ = (FieldMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPrivate()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsProtected)) {
                FieldMetaInfo class_ = (FieldMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isProtected()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPublic)) {
                FieldMetaInfo class_ = (FieldMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPublic()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetName)) {
                FieldMetaInfo field_ = (FieldMetaInfo) _struct;
                result_.setResult(new StringStruct(field_.getName()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetDeclaringClass)) {
                FieldMetaInfo field_ = (FieldMetaInfo) _struct;
                result_.setResult(_cont.getExtendedClassMetaInfo(field_.getDeclaringClass()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetType)) {
                FieldMetaInfo field_ = (FieldMetaInfo) _struct;
                String owner_ = field_.getDeclaringClass();
                String typeField_ = field_.getType();
                typeField_ = Templates.format(owner_, typeField_, _cont);
                result_.setResult(_cont.getExtendedClassMetaInfo(typeField_,owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericType)) {
                FieldMetaInfo field_ = (FieldMetaInfo) _struct;
                String owner_ = field_.getDeclaringClass();
                String typeField_ = field_.getType();
                result_.setResult(_cont.getExtendedClassMetaInfo(typeField_,owner_));
                return result_;
            }
        }
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
            if (StringList.quickEq(name_, ref_.aliasIsFinal)) {
                MethodMetaInfo class_ = (MethodMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isFinal()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsStatic)) {
                MethodMetaInfo class_ = (MethodMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isStatic()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsNormal)) {
                MethodMetaInfo class_ = (MethodMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isNormal()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsVarargs)) {
                MethodMetaInfo class_ = (MethodMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isVararg()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPackage)) {
                MethodMetaInfo class_ = (MethodMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPackage()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPrivate)) {
                MethodMetaInfo class_ = (MethodMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPrivate()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsProtected)) {
                MethodMetaInfo class_ = (MethodMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isProtected()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPublic)) {
                MethodMetaInfo class_ = (MethodMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPublic()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetParameterTypes)) {
                MethodMetaInfo method_ = (MethodMetaInfo) _struct;
                String declaring_ = method_.getFormClassName();
                StringList geneInterfaces_ =  method_.getFid().getParametersTypes();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                if (method_.isVararg()) {
                    int first_ = len_ -1;
                    for (int i = 0; i < first_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = _cont.getExtendedClassMetaInfo(int_,declaring_);
                    }
                    String int_ = geneInterfaces_.get(first_);
                    int_ = PrimitiveTypeUtil.getPrettyArrayType(int_);
                    superInts_[first_] = _cont.getExtendedClassMetaInfo(int_,declaring_);
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = _cont.getExtendedClassMetaInfo(int_,declaring_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetParameterNames)) {
                MethodMetaInfo method_ = (MethodMetaInfo) _struct;
                StringList geneInterfaces_ =  method_.getRealId().getParametersTypes();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasString_);
                Struct[] superInts_ = new Struct[len_];
                if (method_.isVararg()) {
                    int first_ = len_ -1;
                    for (int i = 0; i < first_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = new StringStruct(int_);
                    }
                    String int_ = geneInterfaces_.get(first_);
                    int_ = PrimitiveTypeUtil.getPrettyArrayType(int_);
                    superInts_[first_] = new StringStruct(int_);
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        int_ = Templates.getIdFromAllTypes(int_);
                        superInts_[i] = new StringStruct(int_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetReturnType)) {
                MethodMetaInfo method_ = (MethodMetaInfo) _struct;
                result_.setResult(new StringStruct(method_.getReturnType()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericReturnType)) {
                MethodMetaInfo method_ = (MethodMetaInfo) _struct;
                String owner_ = method_.getFormClassName();
                result_.setResult(_cont.getExtendedClassMetaInfo(method_.getFormattedReturnType(),owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetName)) {
                MethodMetaInfo method_ = (MethodMetaInfo) _struct;
                result_.setResult(new StringStruct(method_.getName()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetDeclaringClass)) {
                MethodMetaInfo method_ = (MethodMetaInfo) _struct;
                result_.setResult(_cont.getExtendedClassMetaInfo(method_.getFormClassName()));
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
            if (StringList.quickEq(name_, ref_.aliasIsAbstract)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isAbstractType()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsArray)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isTypeArray()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsAnnotation)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isTypeAnnotation()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsClass)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isTypeClass()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsEnum)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isTypeEnum()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsFinal)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isFinalType()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsInterface)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isTypeInterface()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPackage)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPackage()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPrimitive)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isTypePrimitive()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPrivate)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPrivate()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsProtected)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isProtected()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPublic)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPublic()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsInstance)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                if (class_.isTypeVariable()) {
                    result_.setResult(new BooleanStruct(false));
                    return result_;
                }
                String param_ = class_.getName();
                String arg_ = lgNames_.getStructClassName(args_[0], _cont);
                Mapping mapping_ = new Mapping();
                mapping_.setArg(arg_);
                mapping_.setParam(param_);
                result_.setResult(new BooleanStruct(Templates.isCorrect(mapping_, _cont)));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsAssignableFrom)) {
                ClassMetaInfo class_ = (ClassMetaInfo) _struct;
                String param_ = class_.getName();
                String arg_ = ((ClassMetaInfo)args_[0]).getName();
                Mapping mapping_ = new Mapping();
                mapping_.setArg(arg_);
                mapping_.setParam(param_);
                result_.setResult(new BooleanStruct(Templates.isCorrect(mapping_, _cont)));
                return result_;
            }
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
                            AccessEnum acc_ = method_.getAccess();
                            FieldMetaInfo met_ = new FieldMetaInfo(forName_, m_, ret_, staticElement_, finalElement_, enumElement_, acc_);
                            infosFields_.put(m_, met_);
                        }
                        if (b instanceof MethodBlock) {
                            MethodBlock method_ = (MethodBlock) b;
                            MethodId id_ = method_.getId();
                            String ret_ = method_.getImportedReturnType();
                            AccessEnum acc_ = method_.getAccess();
                            String formatRet_;
                            MethodId fid_;
                            formatRet_ = ret_;
                            fid_ = id_;
                            String decl_ = method_.getDeclaringType();
                            MethodMetaInfo met_ = new MethodMetaInfo(acc_,decl_, id_, method_.getModifier(), ret_, fid_, formatRet_,decl_);
                            infos_.put(id_, met_);
                        }
                        if (b instanceof AnnotationMethodBlock) {
                            AnnotationMethodBlock method_ = (AnnotationMethodBlock) b;
                            MethodId id_ = method_.getId();
                            String ret_ = method_.getImportedReturnType();
                            AccessEnum acc_ = method_.getAccess();
                            String formatRet_;
                            MethodId fid_;
                            formatRet_ = ret_;
                            fid_ = id_;
                            String decl_ = method_.getDeclaringType();
                            MethodMetaInfo met_ = new MethodMetaInfo(acc_,decl_, id_, method_.getModifier(), ret_, fid_, formatRet_,decl_);
                            infos_.put(id_, met_);
                        }
                        if (b instanceof ConstructorBlock) {
                            ConstructorBlock method_ = (ConstructorBlock) b;
                            ConstructorId id_ = method_.getGenericId();
                            String ret_ = method_.getImportedReturnType();
                            AccessEnum acc_ = method_.getAccess();
                            String formatRet_;
                            ConstructorId fid_;
                            formatRet_ = ret_;
                            fid_ = id_;
                            String decl_ = method_.getDeclaringType();
                            ConstructorMetaInfo met_ = new ConstructorMetaInfo(forName_, acc_, id_, ret_, fid_, formatRet_,decl_);
                            infosConst_.put(id_, met_);
                        }
                    }
                    AccessEnum acc_ = clblock_.getAccess();
                    if (clblock_ instanceof InterfaceBlock) {
                        classes_.add(new ClassMetaInfo(forName_, ((InterfaceBlock)clblock_).getImportedDirectSuperInterfaces(), infosFields_,infos_, infosConst_, ClassCategory.INTERFACE,acc_));
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
                    String superClass_ = ((UniqueRootedBlock) clblock_).getImportedDirectGenericSuperClass();
                    StringList interfaces_ = ((UniqueRootedBlock) clblock_).getImportedDirectGenericSuperInterfaces();
                    classes_.add(new ClassMetaInfo(forName_, superClass_, interfaces_, infosFields_,infos_, infosConst_, cat_, abs_, final_,acc_));
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
                        AccessEnum acc_ = f.getAccess();
                        FieldMetaInfo met_ = new FieldMetaInfo(k_, m_, ret_, staticElement_, finalElement_, false, acc_);
                        infosFields_.put(m_, met_);
                    }
                    for (StandardMethod m: clblock_.getMethods().values()) {
                        MethodId id_ = m.getId();
                        String ret_ = m.getImportedReturnType();
                        AccessEnum acc_ = m.getAccess();
                        String decl_ = m.getDeclaringType();
                        MethodMetaInfo met_ = new MethodMetaInfo(acc_,decl_, id_, m.getModifier(), ret_, id_, ret_,decl_);
                        infos_.put(id_, met_);
                    }
                    for (StandardConstructor d: clblock_.getConstructors()) {
                        ConstructorId id_ = d.getGenericId();
                        String ret_ = d.getImportedReturnType();
                        AccessEnum acc_ = d.getAccess();
                        String decl_ = d.getDeclaringType();
                        ConstructorMetaInfo met_ = new ConstructorMetaInfo(forName_, acc_, id_, ret_, id_, ret_,decl_);
                        infosConst_.put(id_, met_);
                    }
                    AccessEnum acc_ = clblock_.getAccess();
                    if (clblock_ instanceof StandardInterface) {
                        classes_.add(new ClassMetaInfo(forName_, ((StandardInterface)clblock_).getDirectInterfaces(), infosFields_,infos_, infosConst_, ClassCategory.INTERFACE,acc_));
                        continue;
                    }
                    ClassCategory cat_ = ClassCategory.CLASS;
                    if (clblock_ instanceof StandardInterface) {
                        cat_ = ClassCategory.INTERFACE;
                    }
                    boolean abs_ = clblock_.isAbstractType();
                    boolean final_ = clblock_.isFinalType();
                    String superClass_ = ((StandardClass) clblock_).getSuperClass(_cont);
                    StringList interfaces_ = ((StandardClass) clblock_).getDirectInterfaces();
                    classes_.add(new ClassMetaInfo(forName_, superClass_, interfaces_, infosFields_,infos_, infosConst_, cat_, abs_, final_,acc_));
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
                        String ret_ = cl_.getName();
                        AccessEnum acc_ = cl_.getAccess();
                        String decl_ = ret_;
                        candidates_.add(new ConstructorMetaInfo(instClassName_, acc_, idToSearch_, ret_, idToSearch_, ret_,decl_));
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
            if (StringList.quickEq(name_, ref_.aliasGetSuperClass)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                String genericSuperClassName_ = cl_.getSuperClass();
                if (genericSuperClassName_.isEmpty()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                String superClassName_ = Templates.getIdFromAllTypes(genericSuperClassName_);
                ClassMetaInfo superCl_ = _cont.getExtendedClassMetaInfo(superClassName_);
                result_.setResult(superCl_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericSuperClass)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                String genericSuperClassName_ = cl_.getSuperClass();
                if (genericSuperClassName_.isEmpty()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                genericSuperClassName_ = Templates.format(cl_.getName(), genericSuperClassName_, _cont);
                ClassMetaInfo superCl_ = _cont.getExtendedClassMetaInfo(genericSuperClassName_);
                result_.setResult(superCl_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetInterfaces)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                StringList geneInterfaces_ = cl_.getSuperInterfaces();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                for (int i = 0; i < len_; i++) {
                    String int_ = geneInterfaces_.get(i);
                    int_ = Templates.getIdFromAllTypes(int_);
                    superInts_[i] = _cont.getExtendedClassMetaInfo(int_);
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericInterfaces)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                StringList geneInterfaces_ = cl_.getSuperInterfaces();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                for (int i = 0; i < len_; i++) {
                    String int_ = geneInterfaces_.get(i);
                    int_ = Templates.format(cl_.getName(), int_, _cont);
                    superInts_[i] = _cont.getExtendedClassMetaInfo(int_);
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasMakeArray)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                String clName_ = cl_.getName();
                String owner_ = cl_.getVariableOwner();
                if (StringList.quickEq(clName_, aliasVoid_)) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                clName_ = PrimitiveTypeUtil.getPrettyArrayType(clName_);
                result_.setResult(_cont.getExtendedClassMetaInfo(clName_, owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetVariableOwner)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                String owner_ = cl_.getVariableOwner();
                if (owner_.isEmpty()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                owner_ = Templates.getIdFromAllTypes(owner_);
                result_.setResult(_cont.getExtendedClassMetaInfo(owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericVariableOwner)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                String owner_ = cl_.getVariableOwner();
                if (owner_.isEmpty()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                result_.setResult(_cont.getExtendedClassMetaInfo(owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetTypeParameters)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                CustList<ClassMetaInfo> list_ = cl_.getTypeParameters(_cont);
                int len_ = list_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                for (int i = 0; i < len_; i++) {
                    superInts_[i] = list_.get(i);
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetBounds)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                CustList<ClassMetaInfo> list_ = cl_.getBounds(_cont);
                int len_ = list_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                for (int i = 0; i < len_; i++) {
                    superInts_[i] = list_.get(i);
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericBounds)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                CustList<ClassMetaInfo> list_ = cl_.getBounds(_cont);
                int len_ = list_.size();
                String clName_ = cl_.getName();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                for (int i = 0; i < len_; i++) {
                    String nameVar_ = list_.get(i).getName();
                    nameVar_ = Templates.format(clName_, nameVar_, _cont);
                    superInts_[i] = _cont.getExtendedClassMetaInfo(nameVar_);
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetComponentType)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                String owner_ = cl_.getVariableOwner();
                if (!cl_.isArray()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                String clName_ = cl_.getName();
                clName_ = PrimitiveTypeUtil.getQuickComponentType(clName_);
                result_.setResult(_cont.getExtendedClassMetaInfo(clName_, owner_));
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
            if (StringList.quickEq(name_, ref_.aliasArrayNewInstance)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                Numbers<Integer> dims_ = new Numbers<Integer>();
                String size_;
                size_ = lgNames_.getAliasBadSize();
                Struct inst_ = args_[0];
                if (inst_.isNull()) {
                    result_.setError(lgNames_.getAliasNullPe());
                    return result_;
                }
                for (Struct s: ((Struct[])inst_.getInstance())) {
                    int dim_ = ((NumberStruct)s).getInstance().intValue();
                    if (dim_ < 0) {
                        result_.setError(size_);
                        return result_;
                    }
                    dims_.add(dim_);
                }
                String clDyn_ = cl_.getName();
                if (StringList.quickEq(clDyn_, _cont.getStandards().getAliasVoid())) {
                    result_.setError(lgNames_.getAliasClassNotFoundError());
                    return result_;
                }
                result_.setResult(PrimitiveTypeUtil.newCustomArray(clDyn_, dims_, _cont));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasArrayGetLength)) {
                Struct inst_ = args_[0];
                if (!inst_.isArray()) {
                    result_.setError(lgNames_.getAliasNullPe());
                    return result_;
                }
                int len_ = LgNames.getLength(inst_.getInstance());
                result_.setResult(new IntStruct(len_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasArrayGet)) {
                Struct inst_ = args_[0];
                if (!inst_.isArray()) {
                    result_.setError(lgNames_.getAliasNullPe());
                    return result_;
                }
                int index_ = (Integer)args_[1].getInstance();
                Struct out_ = InvokingOperation.getElement(inst_, index_, _cont);
                if (_cont.getException() != null) {
                    return result_;
                }
                result_.setResult(out_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasArraySet)) {
                Struct inst_ = args_[0];
                if (!inst_.isArray()) {
                    result_.setError(lgNames_.getAliasNullPe());
                    return result_;
                }
                int index_ = (Integer)args_[1].getInstance();
                Struct value_ = args_[2];
                InvokingOperation.setElement(inst_, index_, value_, _cont);
                if (_cont.getException() != null) {
                    return result_;
                }
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetActualTypeArguments)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                StringList types_ = Templates.getAllTypes(cl_.getName());
                String owner_ = types_.first();
                int len_ = types_.size();
                String clName_ = cl_.getName();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                for (int i = 1; i < len_; i++) {
                    String nameVar_ = types_.get(i);
                    nameVar_ = Templates.format(clName_, nameVar_, _cont);
                    superInts_[i] = _cont.getExtendedClassMetaInfo(nameVar_, owner_);
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericTypeArguments)) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _struct;
                StringList types_ = Templates.getAllTypes(cl_.getName());
                String owner_ = types_.first();
                int len_ = types_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                for (int i = 1; i < len_; i++) {
                    String nameVar_ = types_.get(i);
                    superInts_[i] = _cont.getExtendedClassMetaInfo(nameVar_, owner_);
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
        }
        if (StringList.quickEq(type_, aliasConstructor_)) {
            if (StringList.quickEq(name_, ref_.aliasIsVarargs)) {
                ConstructorMetaInfo class_ = (ConstructorMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isVararg()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPackage)) {
                ConstructorMetaInfo class_ = (ConstructorMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPackage()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPrivate)) {
                ConstructorMetaInfo class_ = (ConstructorMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPrivate()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsProtected)) {
                ConstructorMetaInfo class_ = (ConstructorMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isProtected()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPublic)) {
                ConstructorMetaInfo class_ = (ConstructorMetaInfo) _struct;
                result_.setResult(new BooleanStruct(class_.isPublic()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetParameterTypes)) {
                ConstructorMetaInfo method_ = (ConstructorMetaInfo) _struct;
                String declaring_ = method_.getFormClassName();
                StringList geneInterfaces_ =  method_.getFid().getParametersTypes();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                if (method_.isVararg()) {
                    int first_ = len_ -1;
                    for (int i = 0; i < first_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = _cont.getExtendedClassMetaInfo(int_,declaring_);
                    }
                    String int_ = geneInterfaces_.get(first_);
                    int_ = PrimitiveTypeUtil.getPrettyArrayType(int_);
                    superInts_[first_] = _cont.getExtendedClassMetaInfo(int_,declaring_);
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = _cont.getExtendedClassMetaInfo(int_,declaring_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetParameterNames)) {
                ConstructorMetaInfo method_ = (ConstructorMetaInfo) _struct;
                StringList geneInterfaces_ =  method_.getRealId().getParametersTypes();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasString_);
                Struct[] superInts_ = new Struct[len_];
                if (method_.isVararg()) {
                    int first_ = len_ -1;
                    for (int i = 0; i < first_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = new StringStruct(int_);
                    }
                    String int_ = geneInterfaces_.get(first_);
                    int_ = PrimitiveTypeUtil.getPrettyArrayType(int_);
                    superInts_[first_] = new StringStruct(int_);
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        int_ = Templates.getIdFromAllTypes(int_);
                        superInts_[i] = new StringStruct(int_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetReturnType)) {
                ConstructorMetaInfo method_ = (ConstructorMetaInfo) _struct;
                result_.setResult(new StringStruct(method_.getReturnType()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericReturnType)) {
                ConstructorMetaInfo method_ = (ConstructorMetaInfo) _struct;
                String owner_ = method_.getFormClassName();
                result_.setResult(_cont.getExtendedClassMetaInfo(method_.getFormattedReturnType(),owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetName)) {
                ConstructorMetaInfo method_ = (ConstructorMetaInfo) _struct;
                result_.setResult(new StringStruct(method_.getName()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetDeclaringClass)) {
                ConstructorMetaInfo method_ = (ConstructorMetaInfo) _struct;
                result_.setResult(_cont.getExtendedClassMetaInfo(method_.getFormClassName()));
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
    public String getAliasAnnotation() {
        return aliasAnnotation;
    }
    public void setAliasAnnotation(String _aliasAnnotation) {
        aliasAnnotation = _aliasAnnotation;
    }
    public String getAliasAnnotated() {
        return aliasAnnotated;
    }
    public void setAliasAnnotated(String _aliasAnnotated) {
        aliasAnnotated = _aliasAnnotated;
    }
    public String getAliasGetAnnotations() {
        return aliasGetAnnotations;
    }
    public void setAliasGetAnnotations(String _aliasGetAnnotations) {
        aliasGetAnnotations = _aliasGetAnnotations;
    }
    public String getAliasGetDefaultValue() {
        return aliasGetDefaultValue;
    }
    public void setAliasGetDefaultValue(String _aliasGetDefaultValue) {
        aliasGetDefaultValue = _aliasGetDefaultValue;
    }
    public String getAliasGetAnnotationsParameters() {
        return aliasGetAnnotationsParameters;
    }
    public void setAliasGetAnnotationsParameters(String _aliasGetAnnotationsParameters) {
        aliasGetAnnotationsParameters = _aliasGetAnnotationsParameters;
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
    public String getAliasMakeArray() {
        return aliasMakeArray;
    }
    public void setAliasMakeArray(String _aliasMakeArray) {
        aliasMakeArray = _aliasMakeArray;
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
    public String getAliasGetParameterNames() {
        return aliasGetParameterNames;
    }
    public void setAliasGetParameterNames(String _aliasGetParameterNames) {
        aliasGetParameterNames = _aliasGetParameterNames;
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
    public String getAliasGetActualTypeArguments() {
        return aliasGetActualTypeArguments;
    }
    public void setAliasGetActualTypeArguments(String _aliasGetActualTypeArguments) {
        aliasGetActualTypeArguments = _aliasGetActualTypeArguments;
    }
    public String getAliasGetGenericTypeArguments() {
        return aliasGetGenericTypeArguments;
    }
    public void setAliasGetGenericTypeArguments(String _aliasGetGenericTypeArguments) {
        aliasGetGenericTypeArguments = _aliasGetGenericTypeArguments;
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
    public String getAliasIsAnnotation() {
        return aliasIsAnnotation;
    }
    public void setAliasIsAnnotation(String _aliasIsAnnotation) {
        aliasIsAnnotation = _aliasIsAnnotation;
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
    public String getAliasGetGenericType() {
        return aliasGetGenericType;
    }
    public void setAliasGetGenericType(String _aliasGetGenericType) {
        aliasGetGenericType = _aliasGetGenericType;
    }
    public String getAliasGetType() {
        return aliasGetType;
    }
    public void setAliasGetType(String _aliasGetType) {
        aliasGetType = _aliasGetType;
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
    public String getAliasInvokeTarget() {
        return aliasInvokeTarget;
    }
    public void setAliasInvokeTarget(String _aliasInvokeTarget) {
        aliasInvokeTarget = _aliasInvokeTarget;
    }
    public String getAliasClassNotFoundError() {
        return aliasClassNotFoundError;
    }
    public void setAliasClassNotFoundError(String _aliasClassNotFoundError) {
        aliasClassNotFoundError = _aliasClassNotFoundError;
    }
    public String getAliasGetVariableOwner() {
        return aliasGetVariableOwner;
    }
    public void setAliasGetVariableOwner(String _aliasGetVariableOwner) {
        aliasGetVariableOwner = _aliasGetVariableOwner;
    }
    public String getAliasGetGenericVariableOwner() {
        return aliasGetGenericVariableOwner;
    }
    public void setAliasGetGenericVariableOwner(String _aliasGetGenericVariableOwner) {
        aliasGetGenericVariableOwner = _aliasGetGenericVariableOwner;
    }
}
