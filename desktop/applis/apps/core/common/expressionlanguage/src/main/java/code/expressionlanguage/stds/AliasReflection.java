package code.expressionlanguage.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.types.ExecPartTypeUtil;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AliasReflection {
    private String aliasAnnotationType;
    private String aliasAnnotated;
    private String aliasGetDefaultValue;
    private String aliasGetAnnotations;
    private String aliasGetAnnotationsParameters;
    private String aliasFct;
    private String aliasCall;
    private String aliasMetaInfo;
    private String aliasInstance;
    private String aliasClassType;
    private String aliasGetClass;
    private String aliasGetEnclosingType;
    private String aliasGetDeclaredClasses;
    private String aliasGetDeclaredExplicits;
    private String aliasGetDeclaredImplicits;
    private String aliasGetDeclaredTrueOperators;
    private String aliasGetDeclaredFalseOperators;
    private String aliasGetDeclaredMethods;
    private String aliasGetDeclaredStaticMethods;
    private String aliasGetDeclaredConstructors;
    private String aliasGetDeclaredFields;
    private String aliasGetDeclaredBlocks;
    private String aliasGetDeclaredSwitchMethods;
    private String aliasGetDeclaredAnonymousLambda;
    private String aliasGetDeclaredAnonymousLambdaLocalVars;
    private String aliasGetDeclaredAnonymousLambdaLocalVarsNb;
    private String aliasGetDeclaredAnonymousLambdaLoopVars;
    private String aliasGetDeclaredLocalTypes;
    private String aliasGetDeclaredAnonymousTypes;
    private String aliasGetSuperClass;
    private String aliasGetGenericSuperClass;
    private String aliasGetInterfaces;
    private String aliasGetGenericInterfaces;
    private String aliasGetLowerBounds;
    private String aliasGetUpperBounds;
    private String aliasMakeArray;
    private String aliasMakeWildCard;
    private String aliasMakeRef;
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
    private String aliasIsSpecialClass;
    private String aliasIsSpecialMuClass;
    private String aliasIsWildCard;
    private String aliasIsRefType;
    private String aliasIsInterface;
    private String aliasIsEnum;
    private String aliasIsPrimitive;
    private String aliasIsAnnotation;
    private String aliasIsArray;
    private String aliasIsInstance;
    private String aliasIsAssignableFrom;
    private String aliasInit;
    private String aliasTryWrap;
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
    private final AliasParamReflection params = new AliasParamReflection();

    public void build(LgNames _stds) {
        CustList<CstFieldInfo> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardClass stdcl_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        String aliasPrimBoolean_ = _stds.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasBoolean_ = _stds.getContent().getNbAlias().getAliasBoolean();
        String aliasPrimInt_ = _stds.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasVoid_ = _stds.getContent().getCoreNames().getAliasVoid();
        String aliasError_ = _stds.getContent().getCoreNames().getAliasError();
        String aliasEnum_ = _stds.getContent().getPredefTypes().getAliasEnumType();
        stdcl_ = new StandardClass(aliasFct, fields_, constructors_, methods_, aliasObject_ , StdClassModifier.ABSTRACT);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasCall, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasFct0Call0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMetaInfo, params_, aliasAnnotated, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInstance, params_, aliasObject_, false, MethodModifier.FINAL);
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasFct, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasClassType, fields_, constructors_, methods_, aliasAnnotated , StdClassModifier.ABSTRACT, new DfClass());
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrettyName, params_, aliasString_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrettySingleName, params_, aliasString_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasGetClass, params_, aliasClassType, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0GetClass0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetEnclosingType, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredClasses, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasForName, params_, aliasClassType, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0ForName0(),params.getAliasClassType0ForName1()));
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasForName, params_, aliasClassType, false, MethodModifier.STATIC,new StringList(params.getAliasClassType1ForName0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInit, params_, aliasVoid_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAnnotation, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsArray, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAbstract, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStatic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsSpecialClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsSpecialMuClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsRefType, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsWildCard, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEnum, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVariable, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsTypeVariable, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInterface, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPackage, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrimitive, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrivate, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsProtected, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPublic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetActualTypeArguments, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasIsInstance, params_, aliasPrimBoolean_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0IsInstance0()));
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasIsAssignableFrom, params_, aliasPrimBoolean_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0IsAssignableFrom0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDefaultInstance, params_, aliasObject_, false, MethodModifier.FINAL, new FctClassDefaultInstance0());
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasDefaultInstance, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0DefaultInstance0()), new FctClassDefaultInstance1());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasEnumValueOf, params_, aliasEnum_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0EnumValueOf0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetEnumConstants, params_, StringExpUtil.getPrettyArrayType(aliasEnum_), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, StringExpUtil.getPrettyArrayType(aliasConstructor), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredConstructors0(),params.getAliasClassType0GetDeclaredConstructors1()));
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, StringExpUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredFields0()));
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredStaticMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredStaticMethods0(),params.getAliasClassType0GetDeclaredStaticMethods1(),params.getAliasClassType0GetDeclaredStaticMethods2(),params.getAliasClassType0GetDeclaredStaticMethods3()));
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredMethods0(),params.getAliasClassType0GetDeclaredMethods1(),params.getAliasClassType0GetDeclaredMethods2(),params.getAliasClassType0GetDeclaredMethods3()));
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredExplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredExplicits0()));
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredImplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredImplicits0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, StringExpUtil.getPrettyArrayType(aliasConstructor), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, StringExpUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredStaticMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredExplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredImplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredTrueOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredFalseOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredBlocks, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_);
        method_ = new StandardMethod(aliasGetDeclaredBlocks, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredBlocks0(),params.getAliasClassType0GetDeclaredBlocks1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetSuperClass, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericSuperClass, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetInterfaces, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericInterfaces, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetUpperBounds, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetLowerBounds, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasMakeGeneric, params_, aliasClassType, true, MethodModifier.FINAL,new StringList(params.getAliasClassType0MakeGeneric0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMakeArray, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasMakeRef, params_, aliasClassType, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0MakeRef0()));
        methods_.add( method_);
        params_ = new StringList(aliasBoolean_);
        method_ = new StandardMethod(aliasMakeWildCard, params_, aliasClassType, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0MakeWildCard0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetComponentType, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetVariableOwner, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericVariableOwner, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetTypeParameters, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetBounds, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericBounds, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAllClasses, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.STATIC,new StringList(params.getAliasClassType0GetOperators0(),params.getAliasClassType0GetOperators1(),params.getAliasClassType0GetOperators2()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList(aliasPrimInt_);
        method_ = new StandardMethod(aliasArrayNewInstance, params_, StringExpUtil.getPrettyArrayType(aliasObject_), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0ArrayNewInstance0()));
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasArrayGetLength, params_, aliasPrimInt_, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0ArrayGetLength0()));
        methods_.add( method_);
        params_ = new StringList(aliasObject_,aliasPrimInt_);
        method_ = new StandardMethod(aliasArrayGet, params_, aliasObject_, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0ArrayGet0(),params.getAliasClassType0ArrayGet1()));
        methods_.add( method_);
        params_ = new StringList(aliasObject_,aliasPrimInt_,aliasObject_);
        method_ = new StandardMethod(aliasArraySet, params_, aliasVoid_, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0ArraySet0(),params.getAliasClassType0ArraySet1(),params.getAliasClassType0ArraySet2()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasTryWrap, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0TryWrap0()));
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasClassType, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasConstructor, fields_, constructors_, methods_, aliasAnnotated, StdClassModifier.ABSTRACT, new DfCtor());
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasNewInstance, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasConstructor0NewInstance0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterNames, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaringClass, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericReturnType, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetReturnType, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPackage, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrivate, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsProtected, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPublic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVarargs, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredLocalTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasConstructor, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasField, fields_, constructors_, methods_, aliasAnnotated, StdClassModifier.ABSTRACT, new DfField());
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasGetField, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasField0GetField0()));
        methods_.add( method_);
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasSetField, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasField0SetField0(),params.getAliasField0SetField1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaringClass, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPackage, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrivate, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsProtected, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPublic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStatic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetType, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericType, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasField, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMethod, fields_, constructors_, methods_, aliasAnnotated, StdClassModifier.ABSTRACT, new DfMethod());
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasInvoke, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasMethod0Invoke0(),params.getAliasMethod0Invoke1()));
        methods_.add( method_);
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasInvokeDirect, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasMethod0InvokeDirect0(),params.getAliasMethod0InvokeDirect1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAbstract, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNormal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStatic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStaticCall, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInstanceMethod, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPackage, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrivate, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsProtected, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPublic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVarargs, params_, aliasPrimBoolean_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterNames, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaringClass, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDefaultValue, params_, aliasObject_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericReturnType, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetReturnType, params_, aliasClassType, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVarsNb, params_, _stds.getContent().getNbAlias().getAliasLong(), false, MethodModifier.FINAL,new StringList(params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVarsNb0()));
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong(),aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVars0(),params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVars1(),params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVars2()));
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod1GetDeclaredAnonymousLambdaLocalVars0(),params.getAliasMethod1GetDeclaredAnonymousLambdaLocalVars1()));
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod2GetDeclaredAnonymousLambdaLocalVars0(),params.getAliasMethod2GetDeclaredAnonymousLambdaLocalVars1()));
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod3GetDeclaredAnonymousLambdaLocalVars0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, StringExpUtil.getPrettyArrayType(aliasString_), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong(),aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod0GetDeclaredAnonymousLambdaLoopVars0(),params.getAliasMethod0GetDeclaredAnonymousLambdaLoopVars1(),params.getAliasMethod0GetDeclaredAnonymousLambdaLoopVars2()));
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, _stds.getContent().getNbAlias().getAliasLong(), false, MethodModifier.FINAL,new StringList(params.getAliasMethod1GetDeclaredAnonymousLambdaLoopVars0(),params.getAliasMethod1GetDeclaredAnonymousLambdaLoopVars1()));
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod2GetDeclaredAnonymousLambdaLoopVars0(),params.getAliasMethod2GetDeclaredAnonymousLambdaLoopVars1()));
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, _stds.getContent().getNbAlias().getAliasLong(), false, MethodModifier.FINAL,new StringList(params.getAliasMethod3GetDeclaredAnonymousLambdaLoopVars0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, StringExpUtil.getPrettyArrayType(aliasString_), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredLocalTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasMethod, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasInvokeTarget, fields_, constructors_, methods_, aliasError_, StdClassModifier.ABSTRACT);
        _stds.getStandards().addEntry(aliasInvokeTarget, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasClassNotFoundError, fields_, constructors_, methods_, aliasError_, StdClassModifier.ABSTRACT);
        _stds.getStandards().addEntry(aliasClassNotFoundError, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAnnotationType, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        params_ = new StringList(aliasAnnotationType);
        method_ = new StandardMethod(aliasGetString, params_, aliasString_, false, MethodModifier.STATIC,new StringList(params.getAliasAnnotationType0GetString0()));
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasAnnotationType, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAnnotated, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAnnotations, params_, StringExpUtil.getPrettyArrayType(aliasAnnotationType), false, MethodModifier.FINAL,new FctAnnotatedGetAnnotations0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAnnotationsParameters, params_,
                StringExpUtil.getPrettyArrayType(StringExpUtil.getPrettyArrayType(aliasAnnotationType)), false,
                MethodModifier.FINAL,new FctAnnotatedGetAnnotationsParam0());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetAnnotations, params_,
                StringExpUtil.getPrettyArrayType(aliasAnnotationType), false, MethodModifier.FINAL,new StringList(params.getAliasAnnotated0GetAnnotations0()),new FctAnnotatedGetAnnotations1());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetAnnotationsParameters, params_,
                StringExpUtil.getPrettyArrayType(StringExpUtil.getPrettyArrayType(aliasAnnotationType)),
                false, MethodModifier.FINAL,new StringList(params.getAliasAnnotated0GetAnnotationsParameters0()),new FctAnnotatedGetAnnotationsParam1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetFileName, params_, aliasString_, false, MethodModifier.FINAL,new FctAnnotatedGetFileName());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambda, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasAnnotated0GetDeclaredAnonymousLambda0(),params.getAliasAnnotated0GetDeclaredAnonymousLambda1(),params.getAliasAnnotated0GetDeclaredAnonymousLambda2(),params.getAliasAnnotated0GetDeclaredAnonymousLambda3()),new FctAnnotatedGetDeclaredAnonymousLambda1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambda, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL,new FctAnnotatedGetDeclaredAnonymousLambda0());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredSwitchMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasAnnotated0GetDeclaredSwitchMethods0(),params.getAliasAnnotated0GetDeclaredSwitchMethods1(),params.getAliasAnnotated0GetDeclaredSwitchMethods2(),params.getAliasAnnotated0GetDeclaredSwitchMethods3()),new FctAnnotatedGetDeclaredSwitchMethods1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredSwitchMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL,new FctAnnotatedGetDeclaredSwitchMethods0());
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasAnnotated, stdcl_);
    }

    public static CustList<MethodMetaInfo> listAnonLambda(ContextEl _cont, AnnotatedStruct _annot) {
        CustList<MethodMetaInfo> methods_ = new CustList<MethodMetaInfo>();
        for (ExecAnonymousFunctionBlock f: _annot.getAnonymousLambda()) {
            MethodMetaInfo met_ = new MethodMetaInfo(_cont, f, _annot.getFormatted());
            methods_.add(met_);
        }
        return methods_;
    }

    public static CustList<MethodMetaInfo> listSwitchMethod(ContextEl _cont, AnnotatedStruct _annot) {
        CustList<MethodMetaInfo> methods_ = new CustList<MethodMetaInfo>();
        for (ExecAbstractSwitchMethod f: _annot.getSwitchMethods()) {
            MethodMetaInfo met_ = new MethodMetaInfo(_cont, f, _annot.getFormatted());
            methods_.add(met_);
        }
        return methods_;
    }

    public static ResultErrorStd invokeFieldInfo(ContextEl _cont, ClassMethodId _method, Struct _struct, Struct[] _args, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String name_ = _method.getConstraints().getName();
        ResultErrorStd result_ = new ResultErrorStd();
        String aliasGetField_ = lgNames_.getContent().getReflect().getAliasGetField();
        String aliasSetField_ = lgNames_.getContent().getReflect().getAliasSetField();
        FieldMetaInfo field_ = NumParsers.getField(_struct);
        if (StringUtil.quickEq(aliasGetField_, name_)) {
            if (!field_.isInvokable()) {
                _stackCall.setCallingState(new CustomFoundExc(getNonInvokableError(_cont,field_, _stackCall)));
                return result_;
            }
            _stackCall.setCallingState(new CustomReflectGetField(ReflectingType.GET_FIELD, field_, new Argument(_args[0]), false));
            return result_;
        }
        if (StringUtil.quickEq(aliasSetField_, name_)) {
            if (!field_.isInvokable()) {
                _stackCall.setCallingState(new CustomFoundExc(getNonInvokableError(_cont,field_, _stackCall)));
                return result_;
            }
            _stackCall.setCallingState(new CustomReflectSetField(ReflectingType.SET_FIELD, field_, new Argument(_args[0]),new Argument(_args[1]), false));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsStatic)) {
            result_.setResult(BooleanStruct.of(field_.isStaticField()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsFinal)) {
            result_.setResult(BooleanStruct.of(field_.isFinalField()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPackage)) {
            result_.setResult(BooleanStruct.of(field_.isPackage()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPrivate)) {
            result_.setResult(BooleanStruct.of(field_.isPrivate()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsProtected)) {
            result_.setResult(BooleanStruct.of(field_.isProtected()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPublic)) {
            result_.setResult(BooleanStruct.of(field_.isPublic()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetName)) {
            result_.setResult(new StringStruct(field_.getName()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaringClass)) {
            result_.setResult(ClassMetaInfo.getClassMetaInfo(_cont,field_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetType)) {
            result_.setResult(field_.getFormattedReturnType(_cont));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredAnonymousTypes)) {
            ExecInfoBlock annotableBlock_ = field_.getAnnotableBlock();
            StringList methods_ = new StringList();
            if (annotableBlock_ != null) {
                for (ExecRootBlock c: annotableBlock_.getElementContent().getContainer().getAnonymous()) {
                    methods_.add(c.getFullName());
                }
            }
            ArrayStruct str_ = getTypes(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        result_.setResult(field_.typeGene(_cont));
        return result_;
    }

    public static ResultErrorStd invokeMethodInfo(ContextEl _cont, ClassMethodId _method, Struct _struct, Struct[] _args, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String name_ = _method.getConstraints().getName();
        ResultErrorStd result_ = new ResultErrorStd();
        String aliasInvoke_ = lgNames_.getContent().getReflect().getAliasInvoke();
        String aliasInvokeDirect_ = lgNames_.getContent().getReflect().getAliasInvokeDirect();
        String aliasGetDefaultValue_ = lgNames_.getContent().getReflect().getAliasGetDefaultValue();
        MethodMetaInfo method_ = NumParsers.getMethod(_struct);
        if (StringUtil.quickEq(aliasGetDefaultValue_, name_)) {
            _stackCall.setCallingState(new CustomReflectMethodDefVal(method_, false));
            return result_;
        }
        if (isInvoke(name_, aliasInvoke_, aliasInvokeDirect_)) {
            return invokeReflect(_cont,_method, _args, _stackCall, method_);
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsAbstract)) {
            result_.setResult(BooleanStruct.of(method_.isAbstract()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsFinal)) {
            result_.setResult(BooleanStruct.of(method_.isFinal()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsStatic)) {
            result_.setResult(BooleanStruct.of(method_.isStatic()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsStaticCall)) {
            result_.setResult(BooleanStruct.of(method_.isStaticCall()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsInstanceMethod)) {
            result_.setResult(BooleanStruct.of(method_.isInstanceMethod()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsNormal)) {
            result_.setResult(BooleanStruct.of(method_.isNormal()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsVarargs)) {
            result_.setResult(BooleanStruct.of(method_.isVararg()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPackage)) {
            result_.setResult(BooleanStruct.of(method_.isPackage()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPrivate)) {
            result_.setResult(BooleanStruct.of(method_.isPrivate()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsProtected)) {
            result_.setResult(BooleanStruct.of(method_.isProtected()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPublic)) {
            result_.setResult(BooleanStruct.of(method_.isPublic()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetParameterTypes)) {
            result_.setResult(getFidParamsFct(_cont, method_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetParameterNames)) {
            result_.setResult(getRealIdParamsFct(_cont, method_));
            return result_;
        }
        return invokeMethodInfoDef(_cont, _method, _args, _stackCall, method_);
    }

    private static boolean isInvoke(String _name, String _aliasInvoke, String _aliasInvokeDirect) {
        return StringUtil.quickEq(_aliasInvoke, _name) || StringUtil.quickEq(_aliasInvokeDirect, _name);
    }

    private static ResultErrorStd invokeMethodInfoDef(ContextEl _cont, ClassMethodId _id, Struct[] _args, StackCall _stackCall, MethodMetaInfo _method) {
        ResultErrorStd result_ = new ResultErrorStd();
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String name_ = _id.getConstraints().getName();
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredAnonymousLambdaLocalVarsNb)) {
            Cache cache_ = _method.getCache();
            if (cache_ != null) {
                result_.setResult(cache_.getLocalWrapperCount(NumParsers.getStringValue(_args[0])));
                return result_;
            }
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredAnonymousLambdaLocalVars)) {
            return declaredAnonymousLambdaLocalVars(_cont, _id, _args, _stackCall, _method);
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredAnonymousLambdaLoopVars)) {
            return declaredAnonymousLambdaLoopVars(_cont, _id, _args, _method);
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredAnonymousTypes)) {
            StringList methods_ = new StringList();
            ExecMemberCallingsBlock callee_ = _method.getCallee();
            fetchAnonymous(methods_, callee_);
            ArrayStruct str_ = getTypes(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredLocalTypes)) {
            StringList methods_ = new StringList();
            ExecMemberCallingsBlock callee_ = _method.getCallee();
            fetchLocalTypes(methods_, callee_);
            ArrayStruct str_ = getTypes(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetReturnType)) {
            result_.setResult(_method.getFormattedReturnType(_cont));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetGenericReturnType)) {
            result_.setResult(_method.typeGene(_cont));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetName)) {
            result_.setResult(new StringStruct(_method.getName()));
            return result_;
        }
        result_.setResult(ClassMetaInfo.getClassMetaInfo(_cont, _method));
        return result_;
    }

    private static ResultErrorStd declaredAnonymousLambdaLoopVars(ContextEl _cont, ClassMethodId _id, Struct[] _args, MethodMetaInfo _method) {
        ResultErrorStd result_ = new ResultErrorStd();
        LgNames lgNames_ = _cont.getStandards();
        if (_args.length == 3) {
            Cache cache_ = _method.getCache();
            if (cache_ != null && _args[2] instanceof NumberStruct) {
                cache_.putLoopValue(NumParsers.getStringValue(_args[0]), NumParsers.convertToNumber(_args[1]).longStruct(), ((NumberStruct) _args[2]).longStruct());
            }
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        if (_args.length == 2) {
            Cache cache_ = _method.getCache();
            if (StringUtil.quickEq(_id.getConstraints().getParametersType(1), lgNames_.getContent().getPrimTypes().getAliasPrimLong())) {
                if (cache_ != null) {
                    result_.setResult(cache_.getLoopValue(NumParsers.getStringValue(_args[0]), NumParsers.convertToNumber(_args[1]).longStruct()));
                } else {
                    result_.setResult(NullStruct.NULL_VALUE);
                }
                return result_;
            }
            if (cache_ != null && _args[1] instanceof NumberStruct) {
                cache_.putLoopValue(NumParsers.getStringValue(_args[0]), ((NumberStruct) _args[1]).longStruct());
            }
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        if (_args.length == 1) {
            Cache cache_ = _method.getCache();
            if (cache_ != null) {
                result_.setResult(cache_.getLoopValue(NumParsers.getStringValue(_args[0]),0));
            } else {
                result_.setResult(NullStruct.NULL_VALUE);
            }
            return result_;
        }
        Cache cache_ = _method.getCache();
        String arrStr_ = lgNames_.getContent().getCharSeq().getAliasString();
        arrStr_ = StringExpUtil.getPrettyArrayType(arrStr_);
        if (cache_ != null) {
            StringList localVars_ = cache_.getLoopVars();
            int size_ = localVars_.size();
            ArrayStruct array_ = new ArrayStruct(size_, arrStr_);
            for (int i = 0; i < size_; i++) {
                array_.set(i, Argument.wrapStr(localVars_.get(i)));
            }
            result_.setResult(array_);
            return result_;
        }
        result_.setResult(NullStruct.NULL_VALUE);
        return result_;
    }

    private static ResultErrorStd declaredAnonymousLambdaLocalVars(ContextEl _cont, ClassMethodId _id, Struct[] _args, StackCall _stackCall, MethodMetaInfo _method) {
        ResultErrorStd result_ = new ResultErrorStd();
        LgNames lgNames_ = _cont.getStandards();
        if (_args.length == 3) {
            Cache cache_ = _method.getCache();
            if (cache_ != null) {
                cache_.putLocalWrapperValue(NumParsers.getStringValue(_args[0]), NumParsers.convertToNumber(_args[1]).longStruct(), _args[2], _cont, _stackCall);
            }
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        if (_args.length == 2) {
            Cache cache_ = _method.getCache();
            if (StringUtil.quickEq(_id.getConstraints().getParametersType(1), lgNames_.getContent().getPrimTypes().getAliasPrimLong())) {
                if (cache_ != null) {
                    result_.setResult(cache_.getLocalWrapperValue(NumParsers.getStringValue(_args[0]), NumParsers.convertToNumber(_args[1]).longStruct(), _cont, _stackCall));
                } else {
                    result_.setResult(NullStruct.NULL_VALUE);
                }
                return result_;
            }
            if (cache_ != null) {
                cache_.putLocalWrapperValue(NumParsers.getStringValue(_args[0]), _args[1], _cont, _stackCall);
            }
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        if (_args.length == 1) {
            Cache cache_ = _method.getCache();
            if (cache_ != null) {
                result_.setResult(cache_.getLocalWrapperValue(NumParsers.getStringValue(_args[0]),0, _cont, _stackCall));
            } else {
                result_.setResult(NullStruct.NULL_VALUE);
            }
            return result_;
        }
        Cache cache_ = _method.getCache();
        String arrStr_ = lgNames_.getContent().getCharSeq().getAliasString();
        arrStr_ = StringExpUtil.getPrettyArrayType(arrStr_);
        if (cache_ != null) {
            StringList localVars_ = cache_.getLocalWrappers();
            int size_ = localVars_.size();
            ArrayStruct array_ = new ArrayStruct(size_, arrStr_);
            for (int i = 0; i < size_; i++) {
                array_.set(i, Argument.wrapStr(localVars_.get(i)));
            }
            result_.setResult(array_);
            return result_;
        }
        result_.setResult(NullStruct.NULL_VALUE);
        return result_;
    }

    private static ResultErrorStd invokeReflect(ContextEl _cont, ClassMethodId _id, Struct[] _args, StackCall _stackCall, MethodMetaInfo _method) {
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _id.getConstraints().getName();
        String aliasInvoke_ = lgNames_.getContent().getReflect().getAliasInvoke();
        String aliasInvokeDirect_ = lgNames_.getContent().getReflect().getAliasInvokeDirect();
        boolean invoke_ = StringUtil.quickEq(aliasInvoke_, name_);
        boolean invokeDirect_ = StringUtil.quickEq(aliasInvokeDirect_, name_);
        ResultErrorStd result_ = new ResultErrorStd();
        if (_method.isInvokable()) {
            if (_method.getStdCallee() != null) {
                _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.STD_FCT, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                return result_;
            }
            if (_method.getPairFct() instanceof ExecAnonymousFunctionBlock) {
                if (_method.isStaticCall()) {
                    _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                    return result_;
                }
                _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                return result_;
            }
            if (_method.getCallee() instanceof ExecAbstractSwitchMethod) {
                if (_method.isStaticCall()) {
                    _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                    return result_;
                }
                _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                return result_;
            }
            ExecRootBlock e_ = _method.getPairType();
            if (e_ instanceof ExecAnnotationBlock) {
                _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.ANNOT_FCT, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                return result_;
            }
        }
        if (invoke_) {
            if (!_method.isInvokable()) {
                _stackCall.setCallingState(new CustomFoundExc(getNonInvokableError(_cont, _method, _stackCall)));
                return result_;
            }
            if (_method.getPairFct() != null) {
                if (_method.isExpCast()) {
                    _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.CAST, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                    return result_;
                }
                if (_method.isStaticCall()) {
                    _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                    return result_;
                }
                _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.METHOD, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                return result_;
            }
        }
        if (invokeDirect_) {
            if (!_method.isInvokable()) {
                _stackCall.setCallingState(new CustomFoundExc(getNonInvokableError(_cont, _method, _stackCall)));
                return result_;
            }
            if (_method.getPairFct() != null) {
                if (_method.isExpCast()) {
                    _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.CAST_DIRECT, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                    return result_;
                }
                if (_method.isStaticCall()) {
                    _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                    return result_;
                }
                _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, _method, new Argument(_args[0]),new Argument(_args[1]), false));
                return result_;
            }
        }
        ExecRootBlock e_ = _method.getPairType();
        if (e_ != null) {
            _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.ENUM_METHODS, _method, new Argument(_args[0]),new Argument(_args[1]),false));
            return result_;
        }
        if (_method.isDirectCast()) {
            _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.CAST_DIRECT, _method, new Argument(_args[0]),new Argument(_args[1]), false));
            return result_;
        }
        _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.CLONE_FCT, _method, new Argument(_args[0]),new Argument(_args[1]),false));
        return result_;
    }

    private static ErrorStruct getNonInvokableError(ContextEl _cont, AnnotatedStruct _instance, StackCall _stackCall) {
        return new ErrorStruct(_cont, _instance.getDisplayedString(_cont).getInstance(), _cont.getStandards().getContent().getCoreNames().getAliasNonInvokable(), _stackCall);
    }

    private static ArrayStruct getTypes(ContextEl _cont, StringList _typesNames) {
        return buildArrClass(_cont,ClassMetaInfo.getTypes(_cont, _typesNames));
    }

    private static ArrayStruct getFidParamsFct(ContextEl _cont, AnnotatedParamStruct _declaring) {
        return buildArrClass(_cont,ClassMetaInfo.getFidParamsFct(_cont, _declaring));
    }

    private static ArrayStruct getRealIdParamsFct(ContextEl _cont, AnnotatedParamStruct _declaring) {
        return buildArrClass(_cont,ClassMetaInfo.getRealIdParamsFct(_cont, _declaring));
    }

    public static ResultErrorStd invokeClassInfo(ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, Struct[] _args, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String name_ = _method.getConstraints().getName();
        ResultErrorStd result_ = new ResultErrorStd();
        String aliasForName_ = lgNames_.getContent().getReflect().getAliasForName();
        String aliasValueOf_ = lgNames_.getContent().getReflect().getAliasEnumValueOf();
        String aliasEnumsValues_ = lgNames_.getContent().getReflect().getAliasGetEnumConstants();
        String aliasDefaultInstance_ = lgNames_.getContent().getReflect().getAliasDefaultInstance();
        String aliasInit_ = lgNames_.getContent().getReflect().getAliasInit();
        String aliasTryWrap_ = lgNames_.getContent().getReflect().getAliasTryWrap();
        ClassMetaInfo instanceClass_ = NumParsers.getClass(_struct);
        if (StringUtil.quickEq(aliasValueOf_, name_)) {
            ExecRootBlock r_ = instanceClass_.getFormatted().getRootBlock();
            ClassCategory category_ = instanceClass_.getCategory();
            Argument clArg_ = new Argument(_args[0]);
            result_.setResult(ExecInvokingOperation.tryGetEnumValue(_exit, _cont, r_, category_, clArg_, _stackCall).getStruct());
            return result_;
        }
        if (StringUtil.quickEq(aliasEnumsValues_, name_)) {
            ExecRootBlock r_ = instanceClass_.getFormatted().getRootBlock();
            ClassCategory category_ = instanceClass_.getCategory();
            result_.setResult(ExecInvokingOperation.tryGetEnumValues(_exit, _cont, r_, category_, _stackCall).getStruct());
            return result_;
        }
        if (StringUtil.quickEq(aliasForName_, name_)) {
            return forName(_cont, _exit, _args, _stackCall);
        }
        if (StringUtil.quickEq(aliasInit_, name_)) {
            _exit.hasToExit(_stackCall, instanceClass_.getFormatted().getRootBlock());
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        if (StringUtil.quickEq(aliasTryWrap_, name_)) {
            result_.setResult(instanceClass_.tryWrap(_cont,_args[0]));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsAbstract)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isAbstractType()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsStatic)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isStaticType()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsArray)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isTypeArray()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasMakeRef)) {
            result_.setResult(ClassMetaInfo.makeRef(_cont, instanceClass_, _args));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasMakeWildCard)) {
            result_.setResult(ClassMetaInfo.makeWildCard(_cont, instanceClass_, _args[0]));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsAnnotation)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isTypeAnnotation()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsClass)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isTypeClass()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsSpecialClass)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isTypeSpeClass()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsSpecialMuClass)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isTypeSpeMuClass()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsWildCard)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isTypeWildCard()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsRefType)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isRefType()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsEnum)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isTypeEnum()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsFinal)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isFinalType()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsTypeVariable)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isTypeVariable()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsVariable)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isVariable()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsInterface)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isTypeInterface()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPackage)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isPackage()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPrimitive)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isTypePrimitive()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPrivate)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isPrivate()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsProtected)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isProtected()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPublic)) {
            result_.setResult(BooleanStruct.of(instanceClass_.isPublic()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsInstance)) {
            String param_ = instanceClass_.getFormatted().getFormatted();
            if (param_.contains(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
                result_.setResult(BooleanStruct.of(false));
                return result_;
            }
            if (_args[0] == NullStruct.NULL_VALUE) {
                result_.setResult(BooleanStruct.of(false));
                return result_;
            }
            result_.setResult(BooleanStruct.of(ExecInherits.safeObject(param_, Argument.getNull(_args[0]).getClassName(_cont), _cont) == ErrorType.NOTHING));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsAssignableFrom)) {
            String param_ = instanceClass_.getFormatted().getFormatted();
            Struct subType_ = _args[0];
            if (!(subType_ instanceof ClassMetaInfo)) {
                result_.setResult(BooleanStruct.of(!ExecClassArgumentMatching.isPrimitive(param_,_cont)));
                return result_;
            }
            String arg_ = NumParsers.getClass(subType_).getFormatted().getFormatted();
            result_.setResult(BooleanStruct.of(ExecInherits.isCorrectExecute(arg_,param_, _cont)));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetName)) {
            result_.setResult(new StringStruct(instanceClass_.getFormatted().getFormatted()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetPrettyName)) {
            String nameCl_ = instanceClass_.getFormatted().getFormatted();
            result_.setResult(new StringStruct(ExecPartTypeUtil.processPrettyType(nameCl_)));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetPrettySingleName)) {
            String nameCl_ = instanceClass_.getFormatted().getFormatted();
            result_.setResult(new StringStruct(ExecPartTypeUtil.processPrettySingleType(nameCl_)));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetEnclosingType)) {
            result_.setResult(instanceClass_.typeOwner(_cont));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredClasses)) {
            CustList<ClassMetaInfo> arr_ = ClassMetaInfo.getMembersMetaList(_cont, instanceClass_);
            result_.setResult(buildArrClass(_cont, arr_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetOperators)) {
            CustList<MethodMetaInfo> operators_ = new CustList<MethodMetaInfo>();
            if (_args.length == 0) {
                for (ExecOperatorBlock o: _cont.getClasses().getSortedOperators()) {
                    MethodMetaInfo met_ = new MethodMetaInfo(o);
                    operators_.add(met_);
                }
                ArrayStruct str_ = getMethodsMeta(_cont, operators_);
                result_.setResult(str_);
                return result_;
            }
            AbstractMethodCriteria abs_ = _cont.getDefCriteria();
            CustList<MethodMetaInfo> candidates_ = new CustList<MethodMetaInfo>();
            for (ExecOperatorBlock o: _cont.getClasses().getSortedOperators()) {
                MethodId id_ = o.getId();
                if (eqStatic(id_, _args[0], NullStruct.NULL_VALUE, _args[1], _args[2], abs_.matches(id_))) {
                    MethodMetaInfo met_ = new MethodMetaInfo(o);
                    candidates_.add(met_);
                }
            }
            ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetAllClasses)) {
            CustList<ClassMetaInfo> classes_ = ClassMetaInfo.addTypes(_cont);
            result_.setResult(buildArrClass(_cont, classes_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetClass)) {
            Struct str_ = _args[0];
            result_.setResult(ClassMetaInfo.getClassMetaInfo(_cont,str_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredConstructors)) {
            CustList<ConstructorMetaInfo> ctors_ = instanceClass_.getConstructorsInfos();
            if (_args.length == 0) {
                ArrayStruct str_ = getConstructorsMeta(_cont, ctors_);
                result_.setResult(str_);
                return result_;
            }
            CustList<ConstructorMetaInfo> candidates_;
            candidates_ = new CustList<ConstructorMetaInfo>();
            for (ConstructorMetaInfo e: ctors_) {
                ConstructorId id_ = e.getFid();
                if (eqStatic(id_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, _args[0], _args[1], BooleanStruct.of(false))) {
                    candidates_.add(e);
                }
            }
            ArrayStruct str_ = getConstructorsMeta(_cont, candidates_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredExplicits)) {
            CustList<MethodMetaInfo> methods_ = instanceClass_.getExplicitsInfos();
            if (_args.length == 0) {
                ArrayStruct str_ = getMethodsMeta(_cont, methods_);
                result_.setResult(str_);
                return result_;
            }
            CustList<MethodMetaInfo> candidates_ = filterMethods(_cont, methods_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, _args[0]);
            ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredImplicits)) {
            CustList<MethodMetaInfo> methods_ = instanceClass_.getImplicitsInfos();
            if (_args.length == 0) {
                ArrayStruct str_ = getMethodsMeta(_cont, methods_);
                result_.setResult(str_);
                return result_;
            }
            CustList<MethodMetaInfo> candidates_ = filterMethods(_cont, methods_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, _args[0]);
            ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredTrueOperators)) {
            CustList<MethodMetaInfo> methods_ = instanceClass_.getTruesInfos();
            ArrayStruct str_ = getMethodsMeta(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredFalseOperators)) {
            CustList<MethodMetaInfo> methods_ = instanceClass_.getFalsesInfos();
            ArrayStruct str_ = getMethodsMeta(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredMethods)) {
            CustList<MethodMetaInfo> methods_ = instanceClass_.getMethodsInfos();
            if (_args.length == 0) {
                if (instanceClass_.isTypeArray()) {
                    String instClassName_ = instanceClass_.getFormatted().getFormatted();
                    MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, lgNames_.getContent().getCoreNames().getAliasClone(), new StringList());
                    String idCl_ = StringExpUtil.getIdFromAllTypes(instClassName_);
                    String ret_ = getReturnTypeClone(_cont, instClassName_, idCl_);
//                    str_.set(0, new MethodMetaInfo(_cont,instClassName_, id_, ret_));
                    result_.setResult(getMethodsMeta(_cont,new CustList<MethodMetaInfo>(new MethodMetaInfo(_cont,ret_, id_, instClassName_))));
                    return result_;
                }
                ArrayStruct str_ = getMethodsMeta(_cont, methods_);
                result_.setResult(str_);
                return result_;
            }
            if (instanceClass_.isTypeArray()) {
                MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, lgNames_.getContent().getCoreNames().getAliasClone(), new StringList());
                AbstractMethodCriteria abs_ = _cont.getDefCriteria();
                if (!eqStatic(id_, _args[0], _args[1], _args[2], _args[3], abs_.matches(id_))) {
                    result_.setResult(getMethodsMeta(_cont,new CustList<MethodMetaInfo>()));
                    return result_;
                }
                String instClassName_ = instanceClass_.getFormatted().getFormatted();
                String idCl_ = StringExpUtil.getIdFromAllTypes(instClassName_);
                String ret_ = getReturnTypeClone(_cont, instClassName_, idCl_);
                result_.setResult(getMethodsMeta(_cont,new CustList<MethodMetaInfo>(new MethodMetaInfo(_cont,ret_, id_, instClassName_))));
                return result_;
            }
            CustList<MethodMetaInfo> candidates_ = filterMethods(_cont, methods_, _args[0], _args[1], _args[2], _args[3]);
            ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredStaticMethods)) {
            CustList<MethodMetaInfo> methods_ = instanceClass_.getMethodsInfos();
            if (instanceClass_.isTypeArray()) {
                result_.setResult(getMethodsMeta(_cont,new CustList<MethodMetaInfo>()));
                return result_;
            }
            CustList<MethodMetaInfo> stMethods_ = new CustList<MethodMetaInfo>();
            for (MethodMetaInfo e: methods_) {
                if (e.getKind() == MethodAccessKind.INSTANCE) {
                    continue;
                }
                stMethods_.add(e);
            }
            if (_args.length == 0) {
                ArrayStruct str_ = getMethodsMeta(_cont, stMethods_);
                result_.setResult(str_);
                return result_;
            }
            AbstractMethodCriteria abs_ = _cont.getStaticCriteria();
            CustList<MethodMetaInfo> candidates_ = filterMethods(stMethods_, _args[0], _args[1], _args[2], _args[3], abs_);
            ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredBlocks)) {
            CustList<MethodMetaInfo> methods_ = instanceClass_.getBlocsInfos();
            if (_args.length == 0) {
                ArrayStruct str_ = getMethodsMeta(_cont, methods_);
                result_.setResult(str_);
                return result_;
            }
            CustList<MethodMetaInfo> candidates_ = filterMethods(_cont,methods_, _args[0],_args[1],NullStruct.NULL_VALUE,NullStruct.NULL_VALUE);
            ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredFields)) {
            CustList<FieldMetaInfo> fields_ = instanceClass_.getFieldsInfos();
            if (_args.length == 0 || !(_args[0] instanceof StringStruct)) {
                ArrayStruct str_ = buildArrFields(_cont,fields_);
                result_.setResult(str_);
                return result_;
            }
            String fieldName_ = ((StringStruct) _args[0]).getInstance();
            CustList<FieldMetaInfo> filteredFields_ = new CustList<FieldMetaInfo>();
            for (FieldMetaInfo e: fields_) {
                if (StringUtil.quickEq(fieldName_, e.getName())) {
                    filteredFields_.add(e);
                    break;
                }
            }
            ArrayStruct str_ = buildArrFields(_cont,filteredFields_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetSuperClass)) {
            ExecFormattedRootBlock nameType_ = instanceClass_.getFormatted();
            result_.setResult(ClassMetaInfo.superType(_cont, instanceClass_, nameType_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetGenericSuperClass)) {
            result_.setResult(ClassMetaInfo.superType(_cont, instanceClass_, ExecFormattedRootBlock.defValue()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetInterfaces)) {
            ExecFormattedRootBlock nameType_ = instanceClass_.getFormatted();
            ArrayStruct arr_ = getFormattedClassesMeta(_cont, instanceClass_, nameType_);
            result_.setResult(arr_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetGenericInterfaces)) {
            ArrayStruct arr_ = getFormattedClassesMeta(_cont, instanceClass_, ExecFormattedRootBlock.defValue());
            result_.setResult(arr_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetLowerBounds)) {
            ArrayStruct arr_ = getLowerWildCardBounds(_cont, instanceClass_);
            result_.setResult(arr_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetUpperBounds)) {
            ArrayStruct arr_ = getUpperWildCardBounds(_cont, instanceClass_);
            result_.setResult(arr_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasMakeArray)) {
            result_.setResult(ClassMetaInfo.makeArray(_cont,instanceClass_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetVariableOwner)) {
            result_.setResult(instanceClass_.variableOwner(_cont));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetGenericVariableOwner)) {
            result_.setResult(instanceClass_.variableOwnerId(_cont));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetTypeParameters)) {
            CustList<ClassMetaInfo> list_ = instanceClass_.getTypeParameters();
            result_.setResult(buildArrClass(_cont, list_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetBounds)) {
            ArrayStruct arr_ = fetchBoundsClassesMeta(_cont, instanceClass_);
            result_.setResult(arr_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetGenericBounds)) {
            ArrayStruct arr_ = fetchGenericBoundsClassesMeta(_cont, instanceClass_);
            result_.setResult(arr_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetComponentType)) {
            result_.setResult(ClassMetaInfo.getComponentType(_cont,instanceClass_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasMakeGeneric)) {
            result_.setResult(ClassMetaInfo.getClassMetaInfo(_cont,instanceClass_,_args));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasArrayNewInstance)) {
            String clDyn_ = instanceClass_.getFormatted().getFormatted();
            if (instanceClass_.isTypeWildCard() || instanceClass_.isRefType()) {
                _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, clDyn_, lgNames_.getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
                return result_;
            }
            if (clDyn_.contains(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
                _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, clDyn_, lgNames_.getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
                return result_;
            }
            if (instanceClass_.isTypeVoid()) {
                _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, clDyn_, lgNames_.getContent().getReflect().getAliasClassNotFoundError(), _stackCall)));
                return result_;
            }
            if (!ExecInherits.correctNbParameters(clDyn_,_cont)) {
                _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, clDyn_, lgNames_.getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
                return result_;
            }
            Ints dims_ = new Ints();
            Struct inst_ = _args[0];
            if (!(inst_ instanceof ArrayStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
                return result_;
            }
            for (Struct s: ((ArrayStruct)inst_).list()) {
                int dim_ = NumParsers.convertToNumber(s).intStruct();
                dims_.add(dim_);
            }
            Struct res_ = ExecTemplates.newCustomArrayOrExc(clDyn_, dims_, _cont, _stackCall);
            if (res_ instanceof ErrorStruct) {
                _stackCall.setCallingState(new CustomFoundExc(res_));
                return result_;
            }
            result_.setResult(res_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasArrayGetLength)) {
            Struct inst_ = _args[0];
            if (!(inst_ instanceof ArrayStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, inst_.getClassName(_cont), lgNames_.getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
                return result_;
            }
            ArrayStruct arr_ = (ArrayStruct) inst_;
            int len_ = arr_.getLength();
            result_.setResult(new IntStruct(len_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasArrayGet)) {
            Struct inst_ = _args[0];
            Struct out_ = ExecTemplates.getElement(inst_, _args[1], _cont, _stackCall);
            result_.setResult(out_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasArraySet)) {
            Struct inst_ = _args[0];
            Struct value_ = _args[2];
            ExecTemplates.setElement(inst_, _args[1], value_, _cont, _stackCall);
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredAnonymousTypes)) {
            StringList methods_ = new StringList();
            ExecRootBlock callee_ = instanceClass_.getFormatted().getRootBlock();
            fetchAnonymous(methods_, callee_);
            ArrayStruct str_ = getTypes(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        result_.setResult(buildArrClass(_cont,ClassMetaInfo.getExtendedClassMetaInfoInit(_cont,instanceClass_)));
        return result_;
    }

    private static ResultErrorStd forName(ContextEl _cont, AbstractExiting _exit, Struct[] _args, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        ResultErrorStd result_ = new ResultErrorStd();
        Argument clArg_ = new Argument(_args[0]);
        Struct struct_ = clArg_.getStruct();
        if (!(struct_ instanceof StringStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
            return result_;
        }
        String clDyn_ = ((StringStruct) struct_).getInstance();
        if (StringUtil.quickEq(clDyn_.trim(), _cont.getStandards().getContent().getCoreNames().getAliasVoid())) {
            result_.setResult(ClassMetaInfo.getClassMetaInfo(_cont,clDyn_));
            return result_;
        }
        String res_ = ExecPartTypeUtil.correctClassPartsDynamic(clDyn_, _cont);
        if (res_.isEmpty()) {
            _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, clDyn_, lgNames_.getContent().getReflect().getAliasClassNotFoundError(), _stackCall)));
            return result_;
        }
        if (BooleanStruct.isTrue(_args[_args.length - 1]) && _exit.hasToExit(_stackCall, _cont.getClassBody(StringExpUtil.getIdFromAllTypes(res_)))) {
            return result_;
        }
        result_.setResult(ClassMetaInfo.getClassMetaInfo(_cont,res_));
        return result_;
    }

    public static ErrorStruct getNpe(ContextEl _cont, StackCall _stackCall) {
        return new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall);
    }

    public static ErrorStruct getClassIssue(ContextEl _cont, String _clDyn, String _errorType, StackCall _stackCall) {
        return new ErrorStruct(_cont, _clDyn, _errorType, _stackCall);
    }

    private static ArrayStruct getLowerWildCardBounds(ContextEl _cont, ClassMetaInfo _cl) {
        CustList<ClassMetaInfo> list_ = ClassMetaInfo.getLowerWildCardBoundsList(_cont, _cl);
        return buildArrClass(_cont, list_);
    }
    private static ArrayStruct getUpperWildCardBounds(ContextEl _cont, ClassMetaInfo _cl) {
        CustList<ClassMetaInfo> list_ = ClassMetaInfo.getUpperWildCardBoundsList(_cont, _cl);
        return buildArrClass(_cont, list_);
    }

    private static ArrayStruct getFormattedClassesMeta(ContextEl _cont, ClassMetaInfo _cl, ExecFormattedRootBlock _clName) {
        CustList<ClassMetaInfo> list_ = ClassMetaInfo.getFormattedInterfacesMetaList(_cont, _cl, _clName);
        return buildArrClass(_cont, list_);
    }

    private static ArrayStruct fetchBoundsClassesMeta(ContextEl _cont, ClassMetaInfo _cl) {
        CustList<ClassMetaInfo> list_ = ClassMetaInfo.fetchBoundsClassesMetaList(_cont, _cl);
        return buildArrClass(_cont, list_);
    }

    private static ArrayStruct fetchGenericBoundsClassesMeta(ContextEl _cont, ClassMetaInfo _cl) {
        CustList<ClassMetaInfo> list_ = ClassMetaInfo.fetchGenericBoundsClassesMetaList(_cont, _cl);
        return buildArrClass(_cont, list_);
    }

    public static CustList<MethodMetaInfo> filterMethods(ContextEl _cont, CustList<MethodMetaInfo> _methods, Struct _name, Struct _static, Struct _vararg, Struct _params) {
        AbstractMethodCriteria abs_ = _cont.getDefCriteria();
        return filterMethods(_methods, _name, _static, _vararg, _params, abs_);
    }

    public static CustList<MethodMetaInfo> filterMethods(CustList<MethodMetaInfo> _methods, Struct _name, Struct _static, Struct _vararg, Struct _params, AbstractMethodCriteria _abs) {
        CustList<MethodMetaInfo> candidates_ = new CustList<MethodMetaInfo>();
        for (MethodMetaInfo e : _methods) {
            MethodId id_ = e.getFid();
            if (eqStatic(id_, _name, _static, _vararg, _params, _abs.matches(id_))) {
                candidates_.add(e);
            }
        }
        return candidates_;
    }

    private static ArrayStruct buildArrClass(ContextEl _cont, CustList<ClassMetaInfo> _list) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasClass_ = ref_.aliasClassType;
        String className_= StringExpUtil.getPrettyArrayType(aliasClass_);
        int len_ = _list.size();
        ArrayStruct arr_ = new ArrayStruct(len_, className_);
        for (int i = 0; i < len_; i++) {
            arr_.set(i, _list.get(i));
        }
        return arr_;
    }

    private static ArrayStruct buildArrFields(ContextEl _cont, CustList<FieldMetaInfo> _fields) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasClass_ = ref_.aliasField;
        String className_= StringExpUtil.getPrettyArrayType(aliasClass_);
        ArrayStruct str_ = new ArrayStruct(_fields.size(), className_);
        int index_ = 0;
        for (FieldMetaInfo e: _fields) {
            str_.set(index_, e);
            index_++;
        }
        return str_;
    }

    private static ArrayStruct getConstructorsMeta(ContextEl _cont, CustList<ConstructorMetaInfo> _candidates) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasClass_ = ref_.aliasConstructor;
        String className_= StringExpUtil.getPrettyArrayType(aliasClass_);
        ArrayStruct str_ = new ArrayStruct(_candidates.size(), className_);
        int index_ = 0;
        for (ConstructorMetaInfo c: _candidates) {
            str_.set(index_, c);
            index_++;
        }
        return str_;
    }

    public static ArrayStruct getMethodsMeta(ContextEl _cont, CustList<MethodMetaInfo> _candidates) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String aliasClass_ = ref_.aliasMethod;
        String className_= StringExpUtil.getPrettyArrayType(aliasClass_);
        ArrayStruct str_ = new ArrayStruct(_candidates.size(), className_);
        int index_ = 0;
        for (MethodMetaInfo c: _candidates) {
            str_.set(index_, c);
            index_++;
        }
        return str_;
    }

    public static ResultErrorStd invokeCtorInfo(ContextEl _cont, Struct _struct, ClassMethodId _method, Struct[] _args, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _method.getConstraints().getName();
        AliasReflection ref_ = lgNames_.getReflect();
        ResultErrorStd result_ = new ResultErrorStd();
        String aliasNewInstance_ = lgNames_.getContent().getReflect().getAliasNewInstance();
        ConstructorMetaInfo ctor_ = NumParsers.getCtor(_struct);
        if (StringUtil.quickEq(aliasNewInstance_, name_)) {
            if(!ctor_.isInvokable()) {
                _stackCall.setCallingState(new CustomFoundExc(getNonInvokableError(_cont,ctor_, _stackCall)));
                return result_;
            }
            _stackCall.setCallingState(new CustomReflectConstructor(ctor_, _args[0], false));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsVarargs)) {
            result_.setResult(BooleanStruct.of(ctor_.isVararg()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPackage)) {
            result_.setResult(BooleanStruct.of(ctor_.isPackage()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPrivate)) {
            result_.setResult(BooleanStruct.of(ctor_.isPrivate()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsProtected)) {
            result_.setResult(BooleanStruct.of(ctor_.isProtected()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasIsPublic)) {
            result_.setResult(BooleanStruct.of(ctor_.isPublic()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetParameterTypes)) {
            result_.setResult(getFidParamsFct(_cont, ctor_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetParameterNames)) {
            result_.setResult(getRealIdParamsFct(_cont, ctor_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetReturnType)) {
            result_.setResult(ctor_.getFormattedReturnType(_cont));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetGenericReturnType)) {
            result_.setResult(ctor_.typeGene(_cont));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetName)) {
            result_.setResult(new StringStruct(ctor_.getFormatted().getFormatted()));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredAnonymousTypes)) {
            StringList methods_ = new StringList();
            ExecMemberCallingsBlock callee_ = ctor_.getPair().getFct();
            fetchAnonymous(methods_, callee_);
            ArrayStruct str_ = getTypes(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredLocalTypes)) {
            StringList methods_ = new StringList();
            ExecMemberCallingsBlock callee_ = ctor_.getPair().getFct();
            fetchLocalTypes(methods_, callee_);
            ArrayStruct str_ = getTypes(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        result_.setResult(ClassMetaInfo.getClassMetaInfo(_cont, ctor_));
        return result_;
    }

    private static void fetchLocalTypes(StringList _methods, ExecMemberCallingsBlock _callee) {
        if (_callee != null) {
            for (ExecRootBlock c: _callee.getReserved()) {
                _methods.add(c.getFullName());
            }
        }
    }

    private static void fetchAnonymous(StringList _methods, ExecMemberCallingsBlock _callee) {
        if (_callee != null) {
            for (ExecRootBlock c: _callee.getAnonymous()) {
                _methods.add(c.getFullName());
            }
        }
    }

    private static void fetchAnonymous(StringList _methods, ExecRootBlock _callee) {
        if (_callee instanceof ExecInfoBlock) {
            for (ExecRootBlock c: ((ExecInfoBlock)_callee).getElementContent().getContainer().getAnonymous()) {
                _methods.add(c.getFullName());
            }
        } else if (_callee != null) {
            for (ExecRootBlock c: _callee.getAnonymousRoot()) {
                _methods.add(c.getFullName());
            }
        }
    }
    private static String getReturnTypeClone(ContextEl _cont, String _instClass, String _idCl) {
        String ret_;
        if (ExecInherits.correctNbParameters(_instClass, _cont)) {
            DimComp dc_ = StringExpUtil.getQuickComponentBaseType(_idCl);
            String compo_ = dc_.getComponent();
            String geneForm_ = ExecHelper.getGenericTypeNameOrObject(_cont,StringExpUtil.getIdFromAllTypes(compo_));
            ret_ = StringExpUtil.getPrettyArrayType(geneForm_,dc_.getDim());
        } else {
            ret_ = _instClass;
        }
        return ret_;
    }

    private static boolean eqStatic(Identifiable _id, Struct _name,
                                    Struct _static, Struct _vararg, Struct _params, BooleanStruct _stMeth) {
        if (_name instanceof StringStruct) {
            StringStruct name_ = (StringStruct) _name;
            if (!StringUtil.quickEq(name_.getInstance(), _id.getName())) {
                return false;
            }
        }
        if (_static instanceof BooleanStruct && !_static.sameReference(_stMeth)) {
            return false;
        }
        if (_vararg instanceof BooleanStruct && !_vararg.sameReference(BooleanStruct.of(_id.isVararg()))) {
            return false;
        }
        if (_params instanceof ArrayStruct) {
            ArrayStruct params_ = (ArrayStruct) _params;
            int parLen_ = _id.getParametersTypesLength();
            if (params_.getLength() != parLen_) {
                return false;
            }
            for (int i = 0; i < parLen_; i++) {
                Struct par_ = params_.get(i);
                if (par_ instanceof ClassMetaInfo) {
                    ClassMetaInfo p_ = NumParsers.getClass(par_);
                    if (!StringUtil.quickEq(p_.getFormatted().getFormatted(), _id.getParametersType(i))) {
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

    public String getAliasInstance() {
        return aliasInstance;
    }

    public void setAliasInstance(String _aliasInstance) {
        this.aliasInstance = _aliasInstance;
    }

    public String getAliasMetaInfo() {
        return aliasMetaInfo;
    }

    public void setAliasMetaInfo(String _aliasMetaInfo) {
        this.aliasMetaInfo = _aliasMetaInfo;
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

    public void setAliasGetDeclaredExplicits(String _aliasGetDeclaredExplicits) {
        this.aliasGetDeclaredExplicits = _aliasGetDeclaredExplicits;
    }

    public String getAliasGetDeclaredImplicits() {
        return aliasGetDeclaredImplicits;
    }

    public void setAliasGetDeclaredImplicits(String _aliasGetDeclaredExplicits) {
        this.aliasGetDeclaredImplicits = _aliasGetDeclaredExplicits;
    }

    public String getAliasGetDeclaredTrueOperators() {
        return aliasGetDeclaredTrueOperators;
    }

    public void setAliasGetDeclaredTrueOperators(String _aliasGetDeclaredTrueOperators) {
        this.aliasGetDeclaredTrueOperators = _aliasGetDeclaredTrueOperators;
    }

    public String getAliasGetDeclaredFalseOperators() {
        return aliasGetDeclaredFalseOperators;
    }

    public void setAliasGetDeclaredFalseOperators(String _aliasGetDeclaredFalseOperators) {
        this.aliasGetDeclaredFalseOperators = _aliasGetDeclaredFalseOperators;
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

    public String getAliasGetDeclaredAnonymousTypes() {
        return aliasGetDeclaredAnonymousTypes;
    }

    public void setAliasGetDeclaredAnonymousTypes(String _aliasGetDeclaredAnonymousTypes) {
        this.aliasGetDeclaredAnonymousTypes = _aliasGetDeclaredAnonymousTypes;
    }

    public String getAliasGetDeclaredAnonymousLambda() {
        return aliasGetDeclaredAnonymousLambda;
    }

    public void setAliasGetDeclaredAnonymousLambda(String _aliasGetDeclaredAnonymousLambda) {
        this.aliasGetDeclaredAnonymousLambda = _aliasGetDeclaredAnonymousLambda;
    }

    public String getAliasGetDeclaredAnonymousLambdaLocalVars() {
        return aliasGetDeclaredAnonymousLambdaLocalVars;
    }

    public void setAliasGetDeclaredAnonymousLambdaLocalVars(String _aliasGetDeclaredAnonymousLambdaLocalVars) {
        this.aliasGetDeclaredAnonymousLambdaLocalVars = _aliasGetDeclaredAnonymousLambdaLocalVars;
    }

    public String getAliasGetDeclaredAnonymousLambdaLocalVarsNb() {
        return aliasGetDeclaredAnonymousLambdaLocalVarsNb;
    }

    public void setAliasGetDeclaredAnonymousLambdaLocalVarsNb(String _aliasGetDeclaredAnonymousLambdaLocalVarsNb) {
        this.aliasGetDeclaredAnonymousLambdaLocalVarsNb = _aliasGetDeclaredAnonymousLambdaLocalVarsNb;
    }

    public String getAliasGetDeclaredAnonymousLambdaLoopVars() {
        return aliasGetDeclaredAnonymousLambdaLoopVars;
    }

    public void setAliasGetDeclaredAnonymousLambdaLoopVars(String _aliasGetDeclaredAnonymousLambdaLoopVars) {
        this.aliasGetDeclaredAnonymousLambdaLoopVars = _aliasGetDeclaredAnonymousLambdaLoopVars;
    }

    public String getAliasGetDeclaredBlocks() {
        return aliasGetDeclaredBlocks;
    }

    public void setAliasGetDeclaredBlocks(String _aliasGetDeclaredBlocks) {
        this.aliasGetDeclaredBlocks = _aliasGetDeclaredBlocks;
    }

    public String getAliasGetDeclaredSwitchMethods() {
        return aliasGetDeclaredSwitchMethods;
    }

    public void setAliasGetDeclaredSwitchMethods(String _aliasGetDeclaredSwitchMethods) {
        this.aliasGetDeclaredSwitchMethods = _aliasGetDeclaredSwitchMethods;
    }

    public String getAliasGetDeclaredLocalTypes() {
        return aliasGetDeclaredLocalTypes;
    }

    public void setAliasGetDeclaredLocalTypes(String _aliasGetDeclaredLocalTypes) {
        this.aliasGetDeclaredLocalTypes = _aliasGetDeclaredLocalTypes;
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
    public String getAliasIsSpecialMuClass() {
        return aliasIsSpecialMuClass;
    }
    public void setAliasIsSpecialMuClass(String _aliasIsSpecialMuClass) {
        aliasIsSpecialMuClass = _aliasIsSpecialMuClass;
    }
    public String getAliasIsSpecialClass() {
        return aliasIsSpecialClass;
    }
    public void setAliasIsSpecialClass(String _aliasIsSpecialClass) {
        aliasIsSpecialClass = _aliasIsSpecialClass;
    }

    public String getAliasIsClass() {
        return aliasIsClass;
    }
    public void setAliasIsClass(String _aliasIsClass) {
        aliasIsClass = _aliasIsClass;
    }

    public String getAliasIsRefType() {
        return aliasIsRefType;
    }

    public void setAliasIsRefType(String _aliasIsRefType) {
        aliasIsRefType = _aliasIsRefType;
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

    public String getAliasMakeRef() {
        return aliasMakeRef;
    }

    public void setAliasMakeRef(String _aliasMakeRef) {
        aliasMakeRef = _aliasMakeRef;
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

    public String getAliasTryWrap() {
        return aliasTryWrap;
    }

    public void setAliasTryWrap(String _aliasTryWrap) {
        this.aliasTryWrap = _aliasTryWrap;
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

    public AliasParamReflection getParams() {
        return params;
    }
}
