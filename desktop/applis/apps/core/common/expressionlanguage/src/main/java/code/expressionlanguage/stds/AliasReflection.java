package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AbstractReplacingType;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
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
        method_ = new StandardMethod(aliasCall, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasFct0Call0()),new FctLambdaCall());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMetaInfo, params_, aliasAnnotated, false, MethodModifier.FINAL, new FctLambdaMetaInfo());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInstance, params_, aliasObject_, false, MethodModifier.FINAL, new FctLambdaInstance());
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasFct, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasClassType, fields_, constructors_, methods_, aliasAnnotated , StdClassModifier.ABSTRACT, new DfClass());
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, new FctClassGetName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrettyName, params_, aliasString_, false, MethodModifier.FINAL, new FctClassGetPrettyName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrettySingleName, params_, aliasString_, false, MethodModifier.FINAL,new FctClassGetPrettySingleName());
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasGetClass, params_, aliasClassType, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0GetClass0()),new FctClassGetClass());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetEnclosingType, params_, aliasClassType, false, MethodModifier.FINAL, new FctClassGetEnclosingType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredClasses, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctClassGetDeclaredClasses());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasForName, params_, aliasClassType, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0ForName0(),params.getAliasClassType0ForName1()),new FctClassForName());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasForName, params_, aliasClassType, false, MethodModifier.STATIC,new StringList(params.getAliasClassType1ForName0()),new FctClassForName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInit, params_, aliasVoid_, false, MethodModifier.FINAL,new FctClassInit());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAnnotation, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsAnnotation());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsArray, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsArray());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAbstract, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsAbstract());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStatic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsStatic());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsClass());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsSpecialClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsSpecialClass());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsSpecialMuClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsSpecialMuClass());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsRefType, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsRefType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsWildCard, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsWildCard());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEnum, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsEnum());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsFinal());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVariable, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsVariable());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsTypeVariable, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsTypeVariable());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInterface, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsInterface());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPackage, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsPackage());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrimitive, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsPrimitive());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrivate, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsPrivate());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsProtected, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsProtected());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPublic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsPublic());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetActualTypeArguments, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasIsInstance, params_, aliasPrimBoolean_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0IsInstance0()),new FctClassIsInstance());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasIsAssignableFrom, params_, aliasPrimBoolean_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0IsAssignableFrom0()),new FctClassIsAssignableFrom());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDefaultInstance, params_, aliasObject_, false, MethodModifier.FINAL, new FctClassDefaultInstance0());
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasDefaultInstance, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0DefaultInstance0()), new FctClassDefaultInstance1());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasEnumValueOf, params_, aliasEnum_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0EnumValueOf0()),new FctClassEnumValueOf());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetEnumConstants, params_, StringExpUtil.getPrettyArrayType(aliasEnum_), false, MethodModifier.FINAL, new FctClassEnumValues());
        methods_.add( method_);
        params_ = new StringList(aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, StringExpUtil.getPrettyArrayType(aliasConstructor), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredConstructors0(),params.getAliasClassType0GetDeclaredConstructors1()),new FctClassGetDeclaredConstructors1());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, StringExpUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredFields0()), new FctClassGetDeclaredFields1());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredStaticMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredStaticMethods0(),params.getAliasClassType0GetDeclaredStaticMethods1(),params.getAliasClassType0GetDeclaredStaticMethods2(),params.getAliasClassType0GetDeclaredStaticMethods3()),new FctClassGetDeclaredStaticMethods1());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredMethods0(),params.getAliasClassType0GetDeclaredMethods1(),params.getAliasClassType0GetDeclaredMethods2(),params.getAliasClassType0GetDeclaredMethods3()), new FctClassGetDeclaredMethods1());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredExplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredExplicits0()),new FctClassGetDeclaredExplicits1());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredImplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredImplicits0()),new FctClassGetDeclaredImplicits1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, StringExpUtil.getPrettyArrayType(aliasConstructor), false, MethodModifier.FINAL, new FctClassGetDeclaredConstructors0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, StringExpUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL, new FctClassGetDeclaredFields0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredStaticMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredStaticMethods0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredMethods0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredExplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredExplicits0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredImplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredImplicits0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredTrueOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredTrueOperators());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredFalseOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredFalseOperators());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredBlocks, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredBlocks0());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_);
        method_ = new StandardMethod(aliasGetDeclaredBlocks, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredBlocks0(),params.getAliasClassType0GetDeclaredBlocks1()), new FctClassGetDeclaredBlocks1());
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
        method_ = new StandardMethod(aliasGetAllClasses, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.STATIC, new FctClassGetAllClasses());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.STATIC,new StringList(params.getAliasClassType0GetOperators0(),params.getAliasClassType0GetOperators1(),params.getAliasClassType0GetOperators2()),new FctClassGetDeclaredOperators1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.STATIC,new FctClassGetDeclaredOperators0());
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
        method_ = new StandardMethod(aliasTryWrap, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0TryWrap0()),new FctClassTryWrap());
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
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVars0(),params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVars1(),params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVars2()),new FctMethodGetDeclaredAnonymousLambdaLocalVars4());
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod1GetDeclaredAnonymousLambdaLocalVars0(),params.getAliasMethod1GetDeclaredAnonymousLambdaLocalVars1()),new FctMethodGetDeclaredAnonymousLambdaLocalVars2());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod2GetDeclaredAnonymousLambdaLocalVars0(),params.getAliasMethod2GetDeclaredAnonymousLambdaLocalVars1()),new FctMethodGetDeclaredAnonymousLambdaLocalVars3());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod3GetDeclaredAnonymousLambdaLocalVars0()),new FctMethodGetDeclaredAnonymousLambdaLocalVars1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, StringExpUtil.getPrettyArrayType(aliasString_), false, MethodModifier.FINAL,new FctMethodGetDeclaredAnonymousLambdaLocalVars0());
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong(),aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod0GetDeclaredAnonymousLambdaLoopVars0(),params.getAliasMethod0GetDeclaredAnonymousLambdaLoopVars1(),params.getAliasMethod0GetDeclaredAnonymousLambdaLoopVars2()),new FctMethodGetDeclaredAnonymousLambdaLoopVars4());
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, _stds.getContent().getNbAlias().getAliasLong(), false, MethodModifier.FINAL,new StringList(params.getAliasMethod1GetDeclaredAnonymousLambdaLoopVars0(),params.getAliasMethod1GetDeclaredAnonymousLambdaLoopVars1()),new FctMethodGetDeclaredAnonymousLambdaLoopVars2());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod2GetDeclaredAnonymousLambdaLoopVars0(),params.getAliasMethod2GetDeclaredAnonymousLambdaLoopVars1()),new FctMethodGetDeclaredAnonymousLambdaLoopVars3());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, _stds.getContent().getNbAlias().getAliasLong(), false, MethodModifier.FINAL,new StringList(params.getAliasMethod3GetDeclaredAnonymousLambdaLoopVars0()), new FctMethodGetDeclaredAnonymousLambdaLoopVars1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, StringExpUtil.getPrettyArrayType(aliasString_), false, MethodModifier.FINAL, new FctMethodGetDeclaredAnonymousLambdaLoopVars0());
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
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNonInvokableError(_cont,field_, _stackCall)));
                return result_;
            }
            _stackCall.setCallingState(new CustomReflectGetField(ReflectingType.GET_FIELD, field_, new Argument(_args[0]), false));
            return result_;
        }
        if (StringUtil.quickEq(aliasSetField_, name_)) {
            if (!field_.isInvokable()) {
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNonInvokableError(_cont,field_, _stackCall)));
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
            ArrayStruct str_ = FctReflection.getTypes(_cont, methods_);
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
        if (FctReflection.isInvoke(name_, aliasInvoke_, aliasInvokeDirect_)) {
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
            result_.setResult(FctReflection.getFidParamsFct(_cont, method_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetParameterNames)) {
            result_.setResult(FctReflection.getRealIdParamsFct(_cont, method_));
            return result_;
        }
        return invokeMethodInfoDef(_cont, _method, _args, method_);
    }

    private static ResultErrorStd invokeMethodInfoDef(ContextEl _cont, ClassMethodId _id, Struct[] _args, MethodMetaInfo _method) {
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
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredAnonymousTypes)) {
            StringList methods_ = new StringList();
            ExecMemberCallingsBlock callee_ = _method.getCallee();
            FctReflection.fetchAnonymous(methods_, callee_);
            ArrayStruct str_ = FctReflection.getTypes(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredLocalTypes)) {
            StringList methods_ = new StringList();
            ExecMemberCallingsBlock callee_ = _method.getCallee();
            FctReflection.fetchLocalTypes(methods_, callee_);
            ArrayStruct str_ = FctReflection.getTypes(_cont, methods_);
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
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNonInvokableError(_cont, _method, _stackCall)));
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
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNonInvokableError(_cont, _method, _stackCall)));
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

    public static ResultErrorStd invokeClassInfo(ContextEl _cont, ClassMethodId _method, Struct _struct, Struct[] _args, StackCall _stackCall) {
        LgNames lgNames_ = _cont.getStandards();
        AliasReflection ref_ = lgNames_.getReflect();
        String name_ = _method.getConstraints().getName();
        ResultErrorStd result_ = new ResultErrorStd();
        ClassMetaInfo instanceClass_ = NumParsers.getClass(_struct);
        if (StringUtil.quickEq(name_, ref_.aliasMakeRef)) {
            result_.setResult(ClassMetaInfo.makeRef(_cont, instanceClass_, _args));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasMakeWildCard)) {
            result_.setResult(ClassMetaInfo.makeWildCard(_cont, instanceClass_, _args[0]));
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
            ArrayStruct arr_ = FctReflection.getFormattedClassesMeta(_cont, instanceClass_, nameType_);
            result_.setResult(arr_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetGenericInterfaces)) {
            ArrayStruct arr_ = FctReflection.getFormattedClassesMeta(_cont, instanceClass_, ExecFormattedRootBlock.defValue());
            result_.setResult(arr_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetLowerBounds)) {
            ArrayStruct arr_ = FctReflection.getLowerWildCardBounds(_cont, instanceClass_);
            result_.setResult(arr_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetUpperBounds)) {
            ArrayStruct arr_ = FctReflection.getUpperWildCardBounds(_cont, instanceClass_);
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
            result_.setResult(FctReflection.buildArrClass(_cont, list_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetBounds)) {
            ArrayStruct arr_ = FctReflection.fetchBoundsClassesMeta(_cont, instanceClass_);
            result_.setResult(arr_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetGenericBounds)) {
            ArrayStruct arr_ = FctReflection.fetchGenericBoundsClassesMeta(_cont, instanceClass_);
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
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getClassIssue(_cont, clDyn_, lgNames_.getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
                return result_;
            }
            if (clDyn_.contains(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getClassIssue(_cont, clDyn_, lgNames_.getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
                return result_;
            }
            if (instanceClass_.isTypeVoid()) {
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getClassIssue(_cont, clDyn_, lgNames_.getContent().getReflect().getAliasClassNotFoundError(), _stackCall)));
                return result_;
            }
            if (!ExecInherits.correctNbParameters(clDyn_,_cont)) {
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getClassIssue(_cont, clDyn_, lgNames_.getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
                return result_;
            }
            Ints dims_ = new Ints();
            Struct inst_ = _args[0];
            if (!(inst_ instanceof ArrayStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_cont, _stackCall)));
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
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getClassIssue(_cont, inst_.getClassName(_cont), lgNames_.getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
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
            FctReflection.fetchAnonymous(methods_, callee_);
            ArrayStruct str_ = FctReflection.getTypes(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        result_.setResult(FctReflection.buildArrClass(_cont,ClassMetaInfo.getExtendedClassMetaInfoInit(_cont,instanceClass_)));
        return result_;
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
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNonInvokableError(_cont,ctor_, _stackCall)));
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
            result_.setResult(FctReflection.getFidParamsFct(_cont, ctor_));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetParameterNames)) {
            result_.setResult(FctReflection.getRealIdParamsFct(_cont, ctor_));
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
            FctReflection.fetchAnonymous(methods_, callee_);
            ArrayStruct str_ = FctReflection.getTypes(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasGetDeclaredLocalTypes)) {
            StringList methods_ = new StringList();
            ExecMemberCallingsBlock callee_ = ctor_.getPair().getFct();
            FctReflection.fetchLocalTypes(methods_, callee_);
            ArrayStruct str_ = FctReflection.getTypes(_cont, methods_);
            result_.setResult(str_);
            return result_;
        }
        result_.setResult(ClassMetaInfo.getClassMetaInfo(_cont, ctor_));
        return result_;
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
