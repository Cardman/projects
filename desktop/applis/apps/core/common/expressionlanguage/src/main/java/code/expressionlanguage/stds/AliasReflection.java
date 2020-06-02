package code.expressionlanguage.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.OperatorBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.opers.util.annotation.ExportAnnotationUtil;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.types.PartTypeUtil;
import code.util.*;

public final class AliasReflection {
    private String aliasAnnotationType;
    private String aliasAnnotated;
    private String aliasGetDefaultValue;
    private String aliasGetAnnotations;
    private String aliasGetAnnotationsParameters;
    private String aliasFct;
    private String aliasCall;
    private String aliasClassType;
    private String aliasGetClass;
    private String aliasGetEnclosingType;
    private String aliasGetDeclaredClasses;
    private String aliasGetDeclaredExplicits;
    private String aliasGetDeclaredImplicits;
    private String aliasGetDeclaredMethods;
    private String aliasGetDeclaredStaticMethods;
    private String aliasGetDeclaredConstructors;
    private String aliasGetDeclaredFields;
    private String aliasGetSuperClass;
    private String aliasGetGenericSuperClass;
    private String aliasGetInterfaces;
    private String aliasGetGenericInterfaces;
    private String aliasGetLowerBounds;
    private String aliasGetUpperBounds;
    private String aliasMakeArray;
    private String aliasMakeWildCard;
    private String aliasGetComponentType;
    private String aliasGetTypeParameters;

    private String aliasGetParameterTypes;
    private String aliasGetParameterNames;
    private String aliasGetGenericReturnType;
    private String aliasGetReturnType;
    private String aliasGetActualTypeArguments;
    private String aliasIsFinal;
    private String aliasIsTypeVariable;
    private String aliasIsVariable;
    private String aliasIsStatic;
    private String aliasIsStaticCall;
    private String aliasIsInstanceMethod;
    private String aliasIsVarargs;
    private String aliasIsNormal;
    private String aliasIsPublic;
    private String aliasIsProtected;
    private String aliasIsPackage;
    private String aliasIsPrivate;
    private String aliasIsClass;
    private String aliasIsWildCard;
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
    private String aliasGetOperators;
    private String aliasConstructor;
    private String aliasField;
    private String aliasMethod;
    private String aliasInvoke;
    private String aliasInvokeDirect;
    private String aliasNewInstance;
    private String aliasIsAbstract;
    private String aliasGetFileName;
    private String aliasGetName;
    private String aliasGetPrettyName;
    private String aliasGetPrettySingleName;
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
    private String aliasGetString;

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
        String aliasBoolean_ = _stds.getAliasBoolean();
        String aliasPrimInt_ = _stds.getAliasPrimInteger();
        String aliasVoid_ = _stds.getAliasVoid();
        String aliasError_ = _stds.getAliasError();
        String aliasEnum_ = _stds.getAliasEnumType();
        stdcl_ = new StandardClass(aliasFct, fields_, constructors_, methods_, aliasObject_ , MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasCall, params_, aliasObject_, true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasFct, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasClassType, fields_, constructors_, methods_, aliasAnnotated , MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrettyName, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrettySingleName, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasGetClass, params_, aliasClassType, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetEnclosingType, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredClasses, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasForName, params_, aliasClassType, false, MethodModifier.STATIC, stdcl_);
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
        method_ = new StandardMethod(aliasIsStatic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsWildCard, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEnum, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVariable, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsTypeVariable, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
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
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetActualTypeArguments, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasIsInstance, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasIsAssignableFrom, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDefaultInstance, params_, aliasObject_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasDefaultInstance, params_, aliasObject_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasEnumValueOf, params_, aliasEnum_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetEnumConstants, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasEnum_), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasConstructor), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredStaticMethods, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredMethods, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredExplicits, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredImplicits, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasConstructor), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredStaticMethods, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredMethods, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredExplicits, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredImplicits, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetSuperClass, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericSuperClass, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetInterfaces, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericInterfaces, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetUpperBounds, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetLowerBounds, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasMakeGeneric, params_, aliasClassType, true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMakeArray, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasBoolean_);
        method_ = new StandardMethod(aliasMakeWildCard, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetComponentType, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetVariableOwner, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericVariableOwner, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetTypeParameters, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetBounds, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericBounds, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAllClasses, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetOperators, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetOperators, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.STATIC, stdcl_);
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
        _stds.getStandards().put(aliasClassType, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasConstructor, fields_, constructors_, methods_, aliasAnnotated, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasNewInstance, params_, aliasObject_, true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterTypes, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterNames, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaringClass, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericReturnType, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetReturnType, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
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
        method_ = new StandardMethod(aliasGetDeclaringClass, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
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
        method_ = new StandardMethod(aliasGetType, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericType, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasField, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasMethod, fields_, constructors_, methods_, aliasAnnotated, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasInvoke, params_, aliasObject_, true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasInvokeDirect, params_, aliasObject_, true, MethodModifier.FINAL, stdcl_);
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
        method_ = new StandardMethod(aliasIsStaticCall, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInstanceMethod, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, stdcl_);
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
        method_ = new StandardMethod(aliasGetParameterTypes, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterNames, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaringClass, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDefaultValue, params_, aliasObject_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericReturnType, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetReturnType, params_, aliasClassType, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasMethod, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasInvokeTarget, fields_, constructors_, methods_, aliasError_, MethodModifier.ABSTRACT);
        _stds.getStandards().put(aliasInvokeTarget, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasClassNotFoundError, fields_, constructors_, methods_, aliasError_, MethodModifier.ABSTRACT);
        _stds.getStandards().put(aliasClassNotFoundError, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasAnnotationType, fields_, constructors_, methods_, aliasObject_, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasAnnotationType);
        method_ = new StandardMethod(aliasGetString, params_, aliasString_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasAnnotationType, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasAnnotated, fields_, constructors_, methods_, aliasObject_, MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAnnotations, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasAnnotationType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAnnotationsParameters, params_,
                PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.getPrettyArrayType(aliasAnnotationType)), false,
                MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetAnnotations, params_,
                PrimitiveTypeUtil.getPrettyArrayType(aliasAnnotationType), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetAnnotationsParameters, params_,
                PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.getPrettyArrayType(aliasAnnotationType)),
                false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetFileName, params_, aliasString_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasAnnotated, stdcl_);
    }
    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = ApplyCoreMethodUtil.getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasClass_ = ref_.aliasClassType;
        String aliasMethod_ = ref_.aliasMethod;
        String aliasConstructor_ = ref_.aliasConstructor;
        String aliasField_ = ref_.aliasField;
        if (StringList.quickEq(type_, ref_.aliasAnnotationType)) {
            DisplayedStrings dis_ = lgNames_.getDisplayedStrings();
            result_.setResult(new StringStruct(ExportAnnotationUtil.exportAnnotation(
                    dis_.getInfinity(),
                    dis_.getNan(),
                    dis_.getExponent(),args_[0])));
            return result_;
        }
        if (StringList.quickEq(type_, ref_.aliasField)) {
            if (StringList.quickEq(name_, ref_.aliasIsStatic)) {
                FieldMetaInfo class_ = ApplyCoreMethodUtil.getField(_struct);
                result_.setResult(BooleanStruct.of(class_.isStaticField()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsFinal)) {
                FieldMetaInfo class_ = ApplyCoreMethodUtil.getField(_struct);
                result_.setResult(BooleanStruct.of(class_.isFinalField()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPackage)) {
                FieldMetaInfo class_ = ApplyCoreMethodUtil.getField(_struct);
                result_.setResult(BooleanStruct.of(class_.isPackage()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPrivate)) {
                FieldMetaInfo class_ = ApplyCoreMethodUtil.getField(_struct);
                result_.setResult(BooleanStruct.of(class_.isPrivate()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsProtected)) {
                FieldMetaInfo class_ = ApplyCoreMethodUtil.getField(_struct);
                result_.setResult(BooleanStruct.of(class_.isProtected()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPublic)) {
                FieldMetaInfo class_ = ApplyCoreMethodUtil.getField(_struct);
                result_.setResult(BooleanStruct.of(class_.isPublic()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetName)) {
                FieldMetaInfo field_ = ApplyCoreMethodUtil.getField(_struct);
                result_.setResult(new StringStruct(field_.getName()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetDeclaringClass)) {
                FieldMetaInfo field_ = ApplyCoreMethodUtil.getField(_struct);
                result_.setResult(ExecutingUtil.getClassMetaInfo(_cont,field_.getDeclaringClass()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetType)) {
                FieldMetaInfo field_ = ApplyCoreMethodUtil.getField(_struct);
                String owner_ = field_.getDeclaringClass();
                String typeField_ = field_.getType();
                if (Templates.correctNbParameters(owner_,_cont)) {
                    typeField_ = Templates.reflectFormat(owner_, typeField_, _cont);
                }
                result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,typeField_,owner_));
                return result_;
            }
            FieldMetaInfo field_ = ApplyCoreMethodUtil.getField(_struct);
            String owner_ = field_.getDeclaringClass();
            String typeField_ = field_.getType();
            result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,typeField_,owner_));
            return result_;
        }
        if (StringList.quickEq(type_, ref_.aliasMethod)) {
            if (StringList.quickEq(name_, ref_.aliasIsAbstract)) {
                MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(method_.isAbstract()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsFinal)) {
                MethodMetaInfo class_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(class_.isFinal()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsStatic)) {
                MethodMetaInfo class_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(class_.isStatic()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsStaticCall)) {
                MethodMetaInfo class_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(class_.isStaticCall()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsInstanceMethod)) {
                MethodMetaInfo class_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(class_.isInstanceMethod()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsNormal)) {
                MethodMetaInfo class_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(class_.isNormal()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsVarargs)) {
                MethodMetaInfo class_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(class_.isVararg()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPackage)) {
                MethodMetaInfo class_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(class_.isPackage()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPrivate)) {
                MethodMetaInfo class_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(class_.isPrivate()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsProtected)) {
                MethodMetaInfo class_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(class_.isProtected()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPublic)) {
                MethodMetaInfo class_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(BooleanStruct.of(class_.isPublic()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetParameterTypes)) {
                MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(_struct);
                String declaring_ = method_.getFormClassName();
                StringList geneInterfaces_ =  method_.getFid().getParametersTypes();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                if (method_.isVararg()) {
                    int first_ = len_ -1;
                    for (int i = 0; i < first_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                    }
                    String int_ = geneInterfaces_.get(first_);
                    int_ = PrimitiveTypeUtil.getPrettyArrayType(int_);
                    superInts_[first_] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetParameterNames)) {
                MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(_struct);
                String declaring_ = method_.getFormClassName();
                StringList geneInterfaces_ = method_.getParameterNames();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                if (method_.isVararg()) {
                    int first_ = len_ -1;
                    for (int i = 0; i < first_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                    }
                    String int_ = geneInterfaces_.get(first_);
                    int_ = PrimitiveTypeUtil.getPrettyArrayType(int_);
                    superInts_[first_] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetReturnType)) {
                MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(_struct);
                String owner_ = method_.getFormClassName();
                String typeMethod_ = method_.getReturnType();
                if (Templates.correctNbParameters(owner_,_cont)) {
                    typeMethod_ = Templates.reflectFormat(owner_, typeMethod_, _cont);
                }
                result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,typeMethod_,owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericReturnType)) {
                MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(_struct);
                String owner_ = method_.getFormClassName();
                String typeMethod_ = method_.getReturnType();
                result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,typeMethod_,owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetName)) {
                MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(_struct);
                result_.setResult(new StringStruct(method_.getName()));
                return result_;
            }
            MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(_struct);
            String cl_ = method_.getFormClassName();
            if (cl_.isEmpty()) {
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            result_.setResult(ExecutingUtil.getClassMetaInfo(_cont,cl_));
            return result_;
        }
        if (StringList.quickEq(type_, aliasClass_)) {
            if (StringList.quickEq(name_, ref_.aliasIsAbstract)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isAbstractType()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsStatic)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isStaticType()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsArray)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isTypeArray()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasMakeWildCard)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                if (class_.isTypeVoid()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                Struct isUpper_ = args_[0];
                String varOwn_ = class_.getVariableOwner();
                String nameCl_ = class_.getName();
                if (!(isUpper_ instanceof BooleanStruct)) {
                    result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,Templates.SUB_TYPE, varOwn_));
                    return result_;
                }
                String baseWildCard_ = nameCl_;
                if (StringList.quickEq(nameCl_,Templates.SUB_TYPE)) {
                    result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,Templates.SUB_TYPE, varOwn_));
                    return result_;
                }
                if (nameCl_.startsWith(Templates.SUB_TYPE)) {
                    baseWildCard_ = nameCl_.substring(Templates.SUB_TYPE.length());
                } else if (nameCl_.startsWith(Templates.SUP_TYPE)) {
                    baseWildCard_ = nameCl_.substring(Templates.SUP_TYPE.length());
                }
                if (BooleanStruct.isTrue(isUpper_)) {
                    result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,StringList.concat(Templates.SUB_TYPE,baseWildCard_), varOwn_));
                } else {
                    result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,StringList.concat(Templates.SUP_TYPE,baseWildCard_), varOwn_));
                }
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsAnnotation)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isTypeAnnotation()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsClass)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isTypeClass()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsWildCard)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isTypeWildCard()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsEnum)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isTypeEnum()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsFinal)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isFinalType()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsTypeVariable)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isTypeVariable()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsVariable)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isVariable()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsInterface)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isTypeInterface()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPackage)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isPackage()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPrimitive)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isTypePrimitive()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPrivate)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isPrivate()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsProtected)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isProtected()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPublic)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                result_.setResult(BooleanStruct.of(class_.isPublic()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsInstance)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                String param_ = class_.getName();
                if (param_.contains(Templates.PREFIX_VAR_TYPE)) {
                    result_.setResult(BooleanStruct.of(false));
                    return result_;
                }
                Argument arg_ = new Argument(args_[0]);
                if (arg_.isNull()) {
                    result_.setResult(BooleanStruct.of(false));
                    return result_;
                }
                result_.setResult(BooleanStruct.of(Templates.safeObject(param_, arg_, _cont) == ErrorType.NOTHING));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsAssignableFrom)) {
                ClassMetaInfo class_ = ApplyCoreMethodUtil.getClass(_struct);
                String param_ = class_.getName();
                Struct subType_ = args_[0];
                if (!(subType_ instanceof ClassMetaInfo)) {
                    result_.setResult(BooleanStruct.of(!PrimitiveTypeUtil.isPrimitive(param_,_cont)));
                    return result_;
                }
                String arg_ = ApplyCoreMethodUtil.getClass(subType_).getName();
                result_.setResult(BooleanStruct.of(Templates.isCorrectExecute(arg_,param_, _cont)));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetName)) {
                result_.setResult(new StringStruct((ApplyCoreMethodUtil.getClass(_struct)).getName()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetPrettyName)) {
                String nameCl_ = (ApplyCoreMethodUtil.getClass(_struct)).getName();
                result_.setResult(new StringStruct(PartTypeUtil.processPrettyType(nameCl_)));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetPrettySingleName)) {
                String nameCl_ = (ApplyCoreMethodUtil.getClass(_struct)).getName();
                result_.setResult(new StringStruct(PartTypeUtil.processPrettySingleType(nameCl_)));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetEnclosingType)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                String t_ = cl_.getTypeOwner();
                if (t_.isEmpty()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,t_,cl_.getVariableOwner()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetDeclaredClasses)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                StringList methods_;
                methods_ = cl_.getMemberTypes();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] methodsArr_ = new Struct[methods_.size()];
                int index_ = 0;
                for (String t: methods_) {
                    methodsArr_[index_] = ExecutingUtil.getExtendedClassMetaInfo(_cont,t,cl_.getVariableOwner());
                    index_++;
                }
                ArrayStruct str_ = new ArrayStruct(methodsArr_, className_);
                result_.setResult(str_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetOperators)) {
                CustList<MethodMetaInfo> operators_ = new CustList<MethodMetaInfo>();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasMethod_);
                if (args_.length == 0) {
                    for (OperatorBlock o: _cont.getClasses().getOperators()) {
                        MethodId id_ = o.getId();
                        String ret_ = o.getImportedReturnType();
                        AccessEnum acc_ = o.getAccess();
                        MethodId fid_;
                        fid_ = id_;
                        String decl_ = o.getDeclaringType();
                        MethodMetaInfo met_ = new MethodMetaInfo(acc_, decl_, id_, o.getModifier(), ret_, fid_, decl_);
                        met_.setFileName(o.getFile().getFileName());
                        met_.setOperator(true);
                        operators_.add(met_);
                    }
                    operators_.sortElts(new OperatorCmp());
                    Struct[] ctorsArr_ = new Struct[operators_.size()];
                    int index_ = 0;
                    for (MethodMetaInfo e: operators_) {
                        ctorsArr_[index_] = e;
                        index_++;
                    }
                    ArrayStruct str_ = new ArrayStruct(ctorsArr_, className_);
                    result_.setResult(str_);
                    return result_;
                }
                CustList<MethodMetaInfo> candidates_;
                candidates_ = new CustList<MethodMetaInfo>();
                for (OperatorBlock o: _cont.getClasses().getOperators()) {
                    MethodId id_ = o.getId();
                    if (eq(id_,args_[0],NullStruct.NULL_VALUE,args_[1],args_[2])) {
                        String ret_ = o.getImportedReturnType();
                        AccessEnum acc_ = o.getAccess();
                        MethodId fid_;
                        fid_ = id_;
                        String decl_ = o.getDeclaringType();
                        MethodMetaInfo met_ = new MethodMetaInfo(acc_, decl_, id_, o.getModifier(), ret_, fid_, decl_);
                        met_.setFileName(o.getFile().getFileName());
                        met_.setOperator(true);
                        candidates_.add(met_);
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
            if (StringList.quickEq(name_, ref_.aliasGetAllClasses)) {
                CustList<ClassMetaInfo> classes_  = new CustList<ClassMetaInfo>();
                Classes classesInfo_ = _cont.getClasses();
                for (RootBlock c: classesInfo_.getClassBodies()) {
                    String forName_ = c.getGenericString();
                    classes_.add(Classes.getClassMetaInfo(c, forName_, _cont));
                }
                for (EntryCust<String, StandardType> c: _cont.getStandards().getStandards().entryList()) {
                    String k_ = c.getKey();
                    StandardType clblock_ = c.getValue();
                    classes_.add(ExecutingUtil.getClassMetaInfo(_cont,clblock_, k_));
                }
                classes_.sortElts(new ClassNameCmp());
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
                if (str_ == NullStruct.NULL_VALUE) {
                    result_.setResult(NullStruct.NULL_VALUE);
                } else {
                    String className_ = lgNames_.getStructClassName(str_, _cont);
                    result_.setResult(ExecutingUtil.getClassMetaInfo(_cont,className_));
                }
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetDeclaredConstructors)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                ObjectMap<ConstructorId, ConstructorMetaInfo> ctors_;
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
                CustList<ConstructorMetaInfo> candidates_;
                candidates_ = new CustList<ConstructorMetaInfo>();
                String instClassName_ = cl_.getName();
                if (Templates.correctNbParameters(instClassName_,_cont)) {
                    for (EntryCust<ConstructorId, ConstructorMetaInfo> e: ctors_.entryList()) {
                        ConstructorId id_ = e.getKey();
                        if (eq(id_.reflectFormat(instClassName_, _cont), NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, args_[0],args_[1])) {
                            candidates_.add(e.getValue());
                        }
                    }
                } else {
                    for (EntryCust<ConstructorId, ConstructorMetaInfo> e: ctors_.entryList()) {
                        ConstructorId id_ = e.getKey();
                        if (eq(id_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, args_[0],args_[1])) {
                            candidates_.add(e.getValue());
                        }
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
            if (StringList.quickEq(name_, ref_.aliasGetDeclaredExplicits)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasMethod_);
                ObjectMap<MethodId, MethodMetaInfo> methods_;
                methods_ = cl_.getExplicitsInfos();
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
                CustList<MethodMetaInfo> candidates_;
                candidates_ = new CustList<MethodMetaInfo>();
                String instClassName_ = cl_.getName();
                if (Templates.correctNbParameters(instClassName_,_cont)) {
                    for (EntryCust<MethodId, MethodMetaInfo> e: methods_.entryList()) {
                        MethodId id_ = e.getKey();
                        if (eq(id_.reflectFormat(instClassName_, _cont),NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,args_[0])) {
                            candidates_.add(e.getValue());
                        }
                    }
                } else {
                    for (EntryCust<MethodId, MethodMetaInfo> e : methods_.entryList()) {
                        MethodId id_ = e.getKey();
                        if (eq(id_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,args_[0])) {
                            candidates_.add(e.getValue());
                        }
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
            if (StringList.quickEq(name_, ref_.aliasGetDeclaredImplicits)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasMethod_);
                ObjectMap<MethodId, MethodMetaInfo> methods_;
                methods_ = cl_.getImplicitsInfos();
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
                CustList<MethodMetaInfo> candidates_;
                candidates_ = new CustList<MethodMetaInfo>();
                String instClassName_ = cl_.getName();
                if (Templates.correctNbParameters(instClassName_,_cont)) {
                    for (EntryCust<MethodId, MethodMetaInfo> e: methods_.entryList()) {
                        MethodId id_ = e.getKey();
                        if (eq(id_.reflectFormat(instClassName_, _cont),NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,args_[0])) {
                            candidates_.add(e.getValue());
                        }
                    }
                } else {
                    for (EntryCust<MethodId, MethodMetaInfo> e : methods_.entryList()) {
                        MethodId id_ = e.getKey();
                        if (eq(id_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,args_[0])) {
                            candidates_.add(e.getValue());
                        }
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
            if (StringList.quickEq(name_, ref_.aliasGetDeclaredMethods)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                ObjectMap<MethodId, MethodMetaInfo> methods_;
                methods_ = cl_.getMethodsInfos();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasMethod_);
                if (args_.length == 0) {
                    if (cl_.isTypeArray()) {
                        String instClassName_ = cl_.getName();
                        String realInstClassName_ = PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasObject());
                        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, lgNames_.getAliasClone(), new StringList());
                        AccessEnum acc_ = AccessEnum.PUBLIC;
                        String idCl_ = Templates.getIdFromAllTypes(instClassName_);
                        String idRealCl_ = Templates.getIdFromAllTypes(realInstClassName_);
                        String ret_ = getReturnTypeClone(_cont, instClassName_, idCl_);
                        Struct[] methodsArr_ = new Struct[1];
                        methodsArr_[0] = new MethodMetaInfo(acc_, idRealCl_, id_, MethodModifier.FINAL, ret_, id_, instClassName_);
                        ArrayStruct str_ = new ArrayStruct(methodsArr_, className_);
                        result_.setResult(str_);
                        return result_;
                    }
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
                if (cl_.isTypeArray()) {
                    MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, lgNames_.getAliasClone(), new StringList());
                    if (!eq(id_,args_[0],args_[1],args_[2],args_[3])) {
                        Struct[] methodsArr_ = new Struct[0];
                        ArrayStruct str_ = new ArrayStruct(methodsArr_, className_);
                        result_.setResult(str_);
                        return result_;
                    }
                    String instClassName_ = cl_.getName();
                    String realInstClassName_ = PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasObject());
                    AccessEnum acc_ = AccessEnum.PUBLIC;
                    String idCl_ = Templates.getIdFromAllTypes(instClassName_);
                    String idRealCl_ = Templates.getIdFromAllTypes(realInstClassName_);
                    String ret_ = getReturnTypeClone(_cont, instClassName_, idCl_);
                    Struct[] methodsArr_ = new Struct[1];
                    methodsArr_[0] = new MethodMetaInfo(acc_, idRealCl_, id_, MethodModifier.FINAL, ret_, id_, instClassName_);
                    ArrayStruct str_ = new ArrayStruct(methodsArr_, className_);
                    result_.setResult(str_);
                    return result_;
                }
                CustList<MethodMetaInfo> candidates_;
                candidates_ = new CustList<MethodMetaInfo>();
                String instClassName_ = cl_.getName();
                if (Templates.correctNbParameters(instClassName_,_cont)) {
                    for (EntryCust<MethodId, MethodMetaInfo> e: methods_.entryList()) {
                        MethodId id_ = e.getKey();
                        if (eq(id_.reflectFormat(instClassName_, _cont),args_[0],args_[1],args_[2],args_[3])) {
                            candidates_.add(e.getValue());
                        }
                    }
                } else {
                    for (EntryCust<MethodId, MethodMetaInfo> e : methods_.entryList()) {
                        MethodId id_ = e.getKey();
                        if (eq(id_,args_[0],args_[1],args_[2],args_[3])) {
                            candidates_.add(e.getValue());
                        }
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
            if (StringList.quickEq(name_, ref_.aliasGetDeclaredStaticMethods)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                ObjectMap<MethodId, MethodMetaInfo> methods_;
                methods_ = cl_.getMethodsInfos();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasMethod_);
                if (cl_.isTypeArray()) {
                    Struct[] methodsArr_ = new Struct[0];
                    ArrayStruct str_ = new ArrayStruct(methodsArr_, className_);
                    result_.setResult(str_);
                    return result_;
                }
                if (args_.length == 0) {
                    CustList<MethodMetaInfo> stMethods_ = new CustList<MethodMetaInfo>();
                    for (EntryCust<MethodId, MethodMetaInfo> e: methods_.entryList()) {
                        if (e.getValue().getKind() == MethodAccessKind.INSTANCE) {
                            continue;
                        }
                        stMethods_.add(e.getValue());
                    }
                    Struct[] methodsArr_ = new Struct[stMethods_.size()];
                    int index_ = 0;
                    for (MethodMetaInfo e: stMethods_) {
                        methodsArr_[index_] = e;
                        index_++;
                    }
                    ArrayStruct str_ = new ArrayStruct(methodsArr_, className_);
                    result_.setResult(str_);
                    return result_;
                }
                CustList<MethodMetaInfo> candidates_;
                candidates_ = new CustList<MethodMetaInfo>();
                String instClassName_ = cl_.getName();
                if (Templates.correctNbParameters(instClassName_,_cont)) {
                    for (EntryCust<MethodId, MethodMetaInfo> e: methods_.entryList()) {
                        MethodId id_ = e.getKey();
                        if (eqType(id_.reflectFormat(instClassName_, _cont),args_[0],args_[1],args_[2],args_[3])) {
                            candidates_.add(e.getValue());
                        }
                    }
                } else {
                    for (EntryCust<MethodId, MethodMetaInfo> e : methods_.entryList()) {
                        MethodId id_ = e.getKey();
                        if (eqType(id_,args_[0],args_[1],args_[2],args_[3])) {
                            candidates_.add(e.getValue());
                        }
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
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                StringMap<FieldMetaInfo> fields_;
                fields_ = cl_.getFieldsInfos();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasField_);
                if (args_.length == 0 || !(args_[0] instanceof StringStruct)) {
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
                String fieldName_ = ((StringStruct) args_[0]).getInstance();
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
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                String genericSuperClassName_ = cl_.getSuperClass();
                if (genericSuperClassName_.isEmpty()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                String nameType_ = cl_.getName();
                if (Templates.correctNbParameters(nameType_,_cont)) {
                    genericSuperClassName_ = Templates.reflectFormat(nameType_, genericSuperClassName_, _cont);
                }
                ClassMetaInfo superCl_ = ExecutingUtil.getExtendedClassMetaInfo(_cont,genericSuperClassName_,cl_.getVariableOwner());
                result_.setResult(superCl_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericSuperClass)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                String genericSuperClassName_ = cl_.getSuperClass();
                if (genericSuperClassName_.isEmpty()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                ClassMetaInfo superCl_ = ExecutingUtil.getExtendedClassMetaInfo(_cont,genericSuperClassName_,cl_.getVariableOwner());
                result_.setResult(superCl_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetInterfaces)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                StringList geneInterfaces_ = cl_.getSuperInterfaces();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                String nameType_ = cl_.getName();
                Struct[] superInts_ = new Struct[len_];
                if (Templates.correctNbParameters(nameType_,_cont)) {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        int_ = Templates.reflectFormat(nameType_, int_, _cont);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,cl_.getVariableOwner());
                    }
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,cl_.getVariableOwner());
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericInterfaces)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                StringList geneInterfaces_ = cl_.getSuperInterfaces();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                for (int i = 0; i < len_; i++) {
                    String int_ = geneInterfaces_.get(i);
                    superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,cl_.getVariableOwner());
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetLowerBounds)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                StringList geneInterfaces_ = cl_.getLowerBounds();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                String clName_ = cl_.getVariableOwner();
                if (clName_.isEmpty()) {
                    Struct[] superInts_ = new Struct[0];
                    ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                    result_.setResult(arr_);
                    return result_;
                }
                Struct[] superInts_ = new Struct[len_];
                if (Templates.correctNbParameters(clName_,_cont)) {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        int_ = Templates.reflectFormat(clName_, int_, _cont);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,clName_);
                    }
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,clName_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetUpperBounds)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                StringList geneInterfaces_ = cl_.getUpperBounds();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                String clName_ = cl_.getVariableOwner();
                if (clName_.isEmpty()) {
                    Struct[] superInts_ = new Struct[0];
                    ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                    result_.setResult(arr_);
                    return result_;
                }
                Struct[] superInts_ = new Struct[len_];
                if (Templates.correctNbParameters(clName_,_cont)) {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        int_ = Templates.reflectFormat(clName_, int_, _cont);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,clName_);
                    }
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,clName_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasMakeArray)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                if (cl_.isTypeVoid()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                String clName_ = cl_.getName();
                String baseWildCard_ = clName_;
                if (StringList.quickEq(clName_, Templates.SUB_TYPE)) {
                    baseWildCard_ = lgNames_.getAliasObject();
                } else if (clName_.startsWith(Templates.SUB_TYPE)) {
                    baseWildCard_ = clName_.substring(Templates.SUB_TYPE.length());
                } else if (clName_.startsWith(Templates.SUP_TYPE)) {
                    baseWildCard_ = clName_.substring(Templates.SUP_TYPE.length());
                }
                String owner_ = cl_.getVariableOwner();
                String arrayName_ = PrimitiveTypeUtil.getPrettyArrayType(baseWildCard_);
                if (clName_.startsWith(Templates.SUB_TYPE)) {
                    arrayName_ = StringList.concat(Templates.SUB_TYPE,arrayName_);
                } else if (clName_.startsWith(Templates.SUP_TYPE)) {
                    arrayName_ = StringList.concat(Templates.SUP_TYPE,arrayName_);
                }
                result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,arrayName_, owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetVariableOwner)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                String owner_ = cl_.getVariableOwner();
                if (owner_.isEmpty()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                result_.setResult(ExecutingUtil.getClassMetaInfo(_cont,owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericVariableOwner)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                String owner_ = cl_.getVariableOwner();
                if (owner_.isEmpty()) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                owner_ = Templates.getIdFromAllTypes(owner_);
                result_.setResult(ExecutingUtil.getClassMetaInfo(_cont,owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetTypeParameters)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
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
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                CustList<ClassMetaInfo> list_ = cl_.getBounds(_cont);
                int len_ = list_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                String clName_ = cl_.getVariableOwner();
                if (clName_.isEmpty()) {
                    Struct[] superInts_ = new Struct[0];
                    ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                    result_.setResult(arr_);
                    return result_;
                }
                Struct[] superInts_ = new Struct[len_];
                if (Templates.correctNbParameters(clName_,_cont)) {
                    for (int i = 0; i < len_; i++) {
                        String nameVar_ = list_.get(i).getName();
                        nameVar_ = Templates.reflectFormat(clName_, nameVar_, _cont);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,nameVar_,clName_);

                    }
                } else {
                    for (int i = 0; i < len_; i++) {
                        String nameVar_ = list_.get(i).getName();
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,nameVar_,clName_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericBounds)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
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
            if (StringList.quickEq(name_, ref_.aliasGetComponentType)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                String owner_ = cl_.getVariableOwner();
                String clName_ = cl_.getName();
                String baseWildCard_ = clName_;
                if (StringList.quickEq(clName_, Templates.SUB_TYPE)) {
                    baseWildCard_ = lgNames_.getAliasObject();
                } else if (clName_.startsWith(Templates.SUB_TYPE)) {
                    baseWildCard_ = clName_.substring(Templates.SUB_TYPE.length());
                } else if (clName_.startsWith(Templates.SUP_TYPE)) {
                    baseWildCard_ = clName_.substring(Templates.SUP_TYPE.length());
                }
                if (!baseWildCard_.startsWith(Templates.ARR_BEG_STRING)) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                String pre_ = "";
                if (clName_.startsWith(Templates.SUB_TYPE)) {
                    pre_ = Templates.SUB_TYPE;
                } else if (clName_.startsWith(Templates.SUP_TYPE)) {
                    pre_ = Templates.SUP_TYPE;
                }
                clName_ = baseWildCard_.substring(Templates.ARR_BEG_STRING.length());
                clName_ = StringList.concat(pre_,clName_);
                result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,clName_, owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasMakeGeneric)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                StringList classesNames_ = new StringList();
                if (!(args_[0] instanceof ArrayStruct)) {
                    result_.setResult(NullStruct.NULL_VALUE);
                    return result_;
                }
                for (Struct s: ((ArrayStruct)args_[0]).getInstance()) {
                    if (!(s instanceof ClassMetaInfo)) {
                        result_.setResult(NullStruct.NULL_VALUE);
                        return result_;
                    }
                    classesNames_.add(ApplyCoreMethodUtil.getClass(s).getName());
                }
                String res_ = Templates.getMadeVarTypes(cl_.getName(), classesNames_, _cont);
                if (res_ == null) {
                    result_.setResult(NullStruct.NULL_VALUE);
                } else {
                    result_.setResult(ExecutingUtil.getClassMetaInfo(_cont,res_));
                }
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasArrayNewInstance)) {
                ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
                String clDyn_ = cl_.getName();
                if (cl_.isTypeWildCard()) {
                    result_.setErrorMessage(clDyn_);
                    result_.setError(lgNames_.getAliasIllegalArg());
                    return result_;
                }
                if (clDyn_.contains(Templates.PREFIX_VAR_TYPE)) {
                    result_.setErrorMessage(clDyn_);
                    result_.setError(lgNames_.getAliasIllegalArg());
                    return result_;
                }
                if (cl_.isTypeVoid()) {
                    result_.setErrorMessage(clDyn_);
                    result_.setError(lgNames_.getAliasClassNotFoundError());
                    return result_;
                }
                if (!Templates.correctNbParameters(clDyn_,_cont)) {
                    result_.setErrorMessage(clDyn_);
                    result_.setError(lgNames_.getAliasIllegalArg());
                    return result_;
                }
                Ints dims_ = new Ints();
                String size_;
                size_ = lgNames_.getAliasBadSize();
                Struct inst_ = args_[0];
                if (!(inst_ instanceof ArrayStruct)) {
                    result_.setError(lgNames_.getAliasNullPe());
                    return result_;
                }
                Struct[] arrayDim_ = ((ArrayStruct)inst_).getInstance();
                if (arrayDim_.length == 0) {
                    result_.setError(size_);
                    return result_;
                }
                for (Struct s: arrayDim_) {
                    int dim_ = ClassArgumentMatching.convertToNumber(s).intStruct();
                    if (dim_ < 0) {
                        result_.setErrorMessage(StringList.concat(Integer.toString(dim_),"<0"));
                        result_.setError(size_);
                        return result_;
                    }
                    dims_.add(dim_);
                }
                result_.setResult(PrimitiveTypeUtil.newCustomArray(clDyn_, dims_, _cont));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasArrayGetLength)) {
                Struct inst_ = args_[0];
                if (!(inst_ instanceof ArrayStruct)) {
                    result_.setErrorMessage(inst_.getClassName(_cont));
                    result_.setError(lgNames_.getAliasIllegalArg());
                    return result_;
                }
                ArrayStruct arr_ = (ArrayStruct) inst_;
                int len_ = arr_.getInstance().length;
                result_.setResult(new IntStruct(len_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasArrayGet)) {
                Struct inst_ = args_[0];
                Struct out_ = ExecInvokingOperation.getElement(inst_, args_[1], _cont);
                result_.setResult(out_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasArraySet)) {
                Struct inst_ = args_[0];
                Struct value_ = args_[2];
                ExecInvokingOperation.setElement(inst_, args_[1], value_, _cont);
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            ClassMetaInfo cl_ = ApplyCoreMethodUtil.getClass(_struct);
            StringList types_ = Templates.getAllTypes(cl_.getName());
            String owner_ = cl_.getName();
            int len_ = types_.size();
            String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
            Struct[] superInts_ = new Struct[len_-1];
            for (int i = 1; i < len_; i++) {
                String nameVar_ = types_.get(i);
                superInts_[i-1] = ExecutingUtil.getExtendedClassMetaInfo(_cont,nameVar_, owner_);
            }
            ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
            result_.setResult(arr_);
            return result_;
        }
        if (StringList.quickEq(type_, aliasConstructor_)) {
            if (StringList.quickEq(name_, ref_.aliasIsVarargs)) {
                ConstructorMetaInfo class_ = ApplyCoreMethodUtil.getCtor(_struct);
                result_.setResult(BooleanStruct.of(class_.isVararg()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPackage)) {
                ConstructorMetaInfo class_ = ApplyCoreMethodUtil.getCtor(_struct);
                result_.setResult(BooleanStruct.of(class_.isPackage()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPrivate)) {
                ConstructorMetaInfo class_ = ApplyCoreMethodUtil.getCtor(_struct);
                result_.setResult(BooleanStruct.of(class_.isPrivate()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsProtected)) {
                ConstructorMetaInfo class_ = ApplyCoreMethodUtil.getCtor(_struct);
                result_.setResult(BooleanStruct.of(class_.isProtected()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasIsPublic)) {
                ConstructorMetaInfo class_ = ApplyCoreMethodUtil.getCtor(_struct);
                result_.setResult(BooleanStruct.of(class_.isPublic()));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetParameterTypes)) {
                ConstructorMetaInfo method_ = ApplyCoreMethodUtil.getCtor(_struct);
                String declaring_ = method_.getFormClassName();
                StringList geneInterfaces_ =  method_.getFid().getParametersTypes();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                if (method_.isVararg()) {
                    int first_ = len_ -1;
                    for (int i = 0; i < first_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                    }
                    String int_ = geneInterfaces_.get(first_);
                    int_ = PrimitiveTypeUtil.getPrettyArrayType(int_);
                    superInts_[first_] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetParameterNames)) {
                ConstructorMetaInfo method_ = ApplyCoreMethodUtil.getCtor(_struct);
                String declaring_ = method_.getFormClassName();
                StringList geneInterfaces_ =  method_.getParametersTypes();
                int len_ = geneInterfaces_.size();
                String className_= PrimitiveTypeUtil.getPrettyArrayType(aliasClass_);
                Struct[] superInts_ = new Struct[len_];
                if (method_.isVararg()) {
                    int first_ = len_ -1;
                    for (int i = 0; i < first_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                    }
                    String int_ = geneInterfaces_.get(first_);
                    int_ = PrimitiveTypeUtil.getPrettyArrayType(int_);
                    superInts_[first_] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                } else {
                    for (int i = 0; i < len_; i++) {
                        String int_ = geneInterfaces_.get(i);
                        superInts_[i] = ExecutingUtil.getExtendedClassMetaInfo(_cont,int_,declaring_);
                    }
                }
                ArrayStruct arr_ = new ArrayStruct(superInts_, className_);
                result_.setResult(arr_);
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetReturnType)) {
                ConstructorMetaInfo method_ = ApplyCoreMethodUtil.getCtor(_struct);
                String owner_ = method_.getFormClassName();
                String typeMethod_ = method_.getReturnType();
                if (Templates.correctNbParameters(owner_,_cont)) {
                    typeMethod_ = Templates.reflectFormat(owner_, typeMethod_, _cont);
                }
                result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,typeMethod_,owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetGenericReturnType)) {
                ConstructorMetaInfo method_ = ApplyCoreMethodUtil.getCtor(_struct);
                String owner_ = method_.getFormClassName();
                String typeMethod_ = method_.getReturnType();
                result_.setResult(ExecutingUtil.getExtendedClassMetaInfo(_cont,typeMethod_,owner_));
                return result_;
            }
            if (StringList.quickEq(name_, ref_.aliasGetName)) {
                ConstructorMetaInfo method_ = ApplyCoreMethodUtil.getCtor(_struct);
                result_.setResult(new StringStruct(method_.getName()));
                return result_;
            }
            ConstructorMetaInfo method_ = ApplyCoreMethodUtil.getCtor(_struct);
            result_.setResult(ExecutingUtil.getClassMetaInfo(_cont,method_.getFormClassName()));
            return result_;
        }
        return result_;
    }

    private static String getReturnTypeClone(ContextEl _cont, String _instClass, String _idCl) {
        String ret_;
        if (Templates.correctNbParameters(_instClass, _cont)) {
            DimComp dc_ = PrimitiveTypeUtil.getQuickComponentBaseType(_idCl);
            String compo_ = dc_.getComponent();
            String geneForm_ = _cont.getClassBody(Templates.getIdFromAllTypes(compo_)).getGenericString();
            ret_ = PrimitiveTypeUtil.getPrettyArrayType(geneForm_,dc_.getDim());
        } else {
            ret_ = _instClass;
        }
        return ret_;
    }

    private static boolean eq(Identifiable _id, Struct _name, Struct _static, Struct _vararg, Struct _params) {
        BooleanStruct stMeth_ = BooleanStruct.of(_id.isStaticMethod());
        return eqStatic(_id, _name, _static, _vararg, _params, stMeth_);
    }

    private static boolean eqType(MethodId _id, Struct _name, Struct _static, Struct _vararg, Struct _params) {
        if (_id.getKind() == MethodAccessKind.INSTANCE) {
            return false;
        }
        BooleanStruct stMeth_ = BooleanStruct.of(!_id.canAccessParamTypes());
        return eqStatic(_id, _name, _static, _vararg, _params, stMeth_);
    }

    private static boolean eqStatic(Identifiable _id, Struct _name,
                                    Struct _static, Struct _vararg, Struct _params, BooleanStruct _stMeth) {
        if (_name instanceof StringStruct) {
            StringStruct name_ = (StringStruct) _name;
            if (!StringList.quickEq(name_.getInstance(), _id.getName())) {
                return false;
            }
        }
        if (_static instanceof BooleanStruct) {
            if (!_static.sameReference(_stMeth)) {
                return false;
            }
        }
        if (_vararg instanceof BooleanStruct) {
            if (!_vararg.sameReference(BooleanStruct.of(_id.isVararg()))) {
                return false;
            }
        }
        if (_params instanceof ArrayStruct) {
            ArrayStruct params_ = (ArrayStruct) _params;
            Struct[] pars_ = params_.getInstance();
            StringList parTypes_ = _id.getParametersTypes();
            int parLen_ = parTypes_.size();
            if (pars_.length != parLen_) {
                return false;
            }
            for (int i = 0; i < parLen_; i++) {
                Struct par_ = pars_[i];
                if (par_ instanceof ClassMetaInfo) {
                    ClassMetaInfo p_ = ApplyCoreMethodUtil.getClass(par_);
                    if (!StringList.quickEq(p_.getName(), parTypes_.get(i))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String getAliasClassType() {
        return aliasClassType;
    }
    public void setAliasClassType(String _aliasClass) {
        aliasClassType = _aliasClass;
    }
    public String getAliasFct() {
        return aliasFct;
    }
    public void setAliasFct(String _aliasFct) {
        aliasFct = _aliasFct;
    }
    public String getAliasCall() {
        return aliasCall;
    }
    public void setAliasCall(String _aliasCall) {
        aliasCall = _aliasCall;
    }
    public String getAliasAnnotationType() {
        return aliasAnnotationType;
    }
    public void setAliasAnnotationType(String _aliasAnnotation) {
        aliasAnnotationType = _aliasAnnotation;
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
    public String getAliasGetEnclosingType() {
        return aliasGetEnclosingType;
    }
    public void setAliasGetEnclosingType(String _aliasGetEnclosingType) {
        aliasGetEnclosingType = _aliasGetEnclosingType;
    }
    public String getAliasGetDeclaredClasses() {
        return aliasGetDeclaredClasses;
    }
    public void setAliasGetDeclaredClasses(String _aliasGetDeclaredClasses) {
        aliasGetDeclaredClasses = _aliasGetDeclaredClasses;
    }

    public String getAliasGetDeclaredExplicits() {
        return aliasGetDeclaredExplicits;
    }

    public void setAliasGetDeclaredExplicits(String aliasGetDeclaredExplicits) {
        this.aliasGetDeclaredExplicits = aliasGetDeclaredExplicits;
    }

    public String getAliasGetDeclaredImplicits() {
        return aliasGetDeclaredImplicits;
    }

    public void setAliasGetDeclaredImplicits(String aliasGetDeclaredExplicits) {
        this.aliasGetDeclaredImplicits = aliasGetDeclaredExplicits;
    }

    public String getAliasGetDeclaredMethods() {
        return aliasGetDeclaredMethods;
    }
    public void setAliasGetDeclaredMethods(String _aliasGetDeclaredMethods) {
        aliasGetDeclaredMethods = _aliasGetDeclaredMethods;
    }

    public String getAliasGetDeclaredStaticMethods() {
        return aliasGetDeclaredStaticMethods;
    }

    public void setAliasGetDeclaredStaticMethods(String _aliasGetDeclaredStaticMethods) {
        aliasGetDeclaredStaticMethods = _aliasGetDeclaredStaticMethods;
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
    public String getAliasGetLowerBounds() {
        return aliasGetLowerBounds;
    }
    public void setAliasGetLowerBounds(String _aliasGetLowerBounds) {
        aliasGetLowerBounds = _aliasGetLowerBounds;
    }
    public String getAliasGetUpperBounds() {
        return aliasGetUpperBounds;
    }
    public void setAliasGetUpperBounds(String _aliasGetUpperBounds) {
        aliasGetUpperBounds = _aliasGetUpperBounds;
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
    public String getAliasIsStaticCall() {
        return aliasIsStaticCall;
    }
    public void setAliasIsStaticCall(String _aliasIsStaticCall) {
        aliasIsStaticCall = _aliasIsStaticCall;
    }

    public String getAliasIsInstanceMethod() {
        return aliasIsInstanceMethod;
    }

    public void setAliasIsInstanceMethod(String _aliasIsInstanceMethod) {
        aliasIsInstanceMethod = _aliasIsInstanceMethod;
    }

    public String getAliasIsTypeVariable() {
        return aliasIsTypeVariable;
    }

    public void setAliasIsTypeVariable(String _aliasIsTypeVariable) {
        aliasIsTypeVariable = _aliasIsTypeVariable;
    }

    public String getAliasIsVariable() {
        return aliasIsVariable;
    }

    public void setAliasIsVariable(String _aliasIsVariable) {
        aliasIsVariable = _aliasIsVariable;
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
    public String getAliasIsWildCard() {
        return aliasIsWildCard;
    }
    public void setAliasIsWildCard(String _aliasIsWildCard) {
        aliasIsWildCard = _aliasIsWildCard;
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
    public String getAliasMakeWildCard() {
        return aliasMakeWildCard;
    }
    public void setAliasMakeWildCard(String _aliasMakeWildCard) {
        aliasMakeWildCard = _aliasMakeWildCard;
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
    public String getAliasGetOperators() {
        return aliasGetOperators;
    }
    public void setAliasGetOperators(String _aliasGetOperators) {
        aliasGetOperators = _aliasGetOperators;
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

    public String getAliasInvokeDirect() {
        return aliasInvokeDirect;
    }

    public void setAliasInvokeDirect(String _aliasInvokeDirect) {
        aliasInvokeDirect = _aliasInvokeDirect;
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

    public String getAliasGetFileName() {
        return aliasGetFileName;
    }

    public void setAliasGetFileName(String _aliasGetFileName) {
        aliasGetFileName = _aliasGetFileName;
    }

    public String getAliasGetName() {
        return aliasGetName;
    }
    public void setAliasGetName(String _aliasGetName) {
        aliasGetName = _aliasGetName;
    }
    public String getAliasGetPrettyName() {
        return aliasGetPrettyName;
    }
    public void setAliasGetPrettyName(String _aliasGetPrettyName) {
        aliasGetPrettyName = _aliasGetPrettyName;
    }
    public String getAliasGetPrettySingleName() {
        return aliasGetPrettySingleName;
    }
    public void setAliasGetPrettySingleName(String _aliasGetPrettyName) {
        aliasGetPrettySingleName = _aliasGetPrettyName;
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
    public String getAliasGetString() {
        return aliasGetString;
    }
    public void setAliasGetString(String _aliasGetString) {
        aliasGetString = _aliasGetString;
    }
    
}
