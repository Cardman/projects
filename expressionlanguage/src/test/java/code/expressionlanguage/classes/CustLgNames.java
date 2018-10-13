package code.expressionlanguage.classes;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class CustLgNames extends LgNames {

    private String aliasStringList = "code.util.StringList";
    private String aliasComposite = "code.expressionlanguage.classes.Composite";
    private String aliasInheritedComposite = "code.expressionlanguage.classes.InheritedComposite";
    private String aliasFailMethods = "code.expressionlanguage.classes.FailMethods";
    private String aliasNotRead = "NOT_READ";
    private String aliasStrangeInit = "code.expressionlanguage.classes.StrangeInit";
    private String aliasFail = "fail";
    private String aliasBeanOne = "code.expressionlanguage.classes.BeanOne";
    private String aliasArrayContainer = "code.expressionlanguage.classes.ArrayContainer";
    private String aliasArrayBis = "code.expressionlanguage.classes.ArrayBis";
    private String aliasGetArray = "getArray";
    private String aliasGetCompo = "getCompo";
    private String aliasInts = "code.expressionlanguage.classes.Ints";
    private String aliasAdd = "add";
    private String aliasSize = "size";
    private String aliasIntegerField = "integer";
    private String aliasCompositeField = "composite";
    private String aliasGetList = "getList";
    private String aliasRemoveAndExistAfter = "removeAndExistAfter";
    private String aliasGeneObjects = "code.expressionlanguage.classes.GeneObjects";
    private String aliasPickableList = "code.expressionlanguage.classes.PickableList";
    private String aliasGetOverridenOne = "getOverridenOne";
    private String aliasGetOverridenTwo = "getOverridenTwo";
    private String aliasGetOverridenThree = "getOverridenThree";
    private String aliasGetOverridenFour = "getOverridenFour";
    private String aliasGetOverridenFive = "getOverridenFive";
    private String aliasGetOverridenSix = "getOverridenSix";
    private String aliasSetPrivateInt = "setPrivateInt";
    private String aliasGetPrivateInt = "getPrivateInt";
    private String aliasGetInteger = "getInteger";
    @Override
    public void buildOther() {
        StringMap<StandardField> fields_;
        StandardField field_;
        StringList params_;
        StandardMethod method_;
        StandardConstructor ctor_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        params_ = new StringList(getAliasInteger());
        stdcl_ = new StandardClass(aliasInts, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        stdcl_.setIterative(getAliasInteger());
        stdcl_.getDirectInterfaces().add(getAliasSimpleIterableType());
        std_ = stdcl_;
        getStandards().put(aliasInts, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasPickableList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetList, params_, aliasGeneObjects, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasRemoveAndExistAfter, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasPickableList, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasGeneObjects, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSize, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
//        params_ = new StringList(aliasSimpleIteratorType);
//        method_ = new StandardMethod(aliasSimpleIterator, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, aliasGeneObjects);
//        methods_.put(method_.getId(), method_);
        stdcl_.setIterative(getAliasObject());
        std_ = stdcl_;
        getStandards().put(aliasGeneObjects, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
//        params_ = new StringList();
//        method_ = new StandardMethod(getAliasNext(), params_, getAliasObject(), false, MethodModifier.FINAL, aliasSimpleIteratorType);
//        methods_.put(method_.getId(), method_);
//        params_ = new StringList();
//        method_ = new StandardMethod(getAliasHasNext(), params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, aliasSimpleIteratorType);
//        methods_.put(method_.getId(), method_);
        stdcl_ = new StandardClass(aliasStringList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        ctor_ = new StandardConstructor(new StringList(getAliasString()), true, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("get", params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasStringList, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasComposite, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasGetOverridenOne, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasGetOverridenOne, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasNumber());
        method_ = new StandardMethod(aliasGetOverridenOne, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasGetOverridenTwo, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasGetOverridenTwo, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimDouble());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasDouble());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasGetOverridenFour, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenFour, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimDouble());
        method_ = new StandardMethod(aliasGetOverridenFive, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenFive, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasDouble());
        method_ = new StandardMethod(aliasGetOverridenSix, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasGetOverridenSix, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenSix, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSetPrivateInt, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrivateInt, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetInteger, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        //aliasGetOverridenOne
        field_ = new StandardField(aliasIntegerField, getAliasPrimInteger(), false, false, stdcl_);
        fields_.put(aliasIntegerField, field_);
        std_ = stdcl_;
        getStandards().put(aliasComposite, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasInheritedComposite, fields_, constructors_, methods_, aliasComposite, MethodModifier.FINAL);
        std_ = stdcl_;
        getStandards().put(aliasInheritedComposite, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasFailMethods, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFail, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasFailMethods, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasStrangeInit, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFail, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        fields_.put(aliasNotRead, new StandardField(aliasNotRead, getAliasString(), true, true, stdcl_));
        std_ = stdcl_;
        getStandards().put(aliasStrangeInit, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdcl_ = new StandardClass(aliasBeanOne, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        field_ = new StandardField(aliasCompositeField, aliasComposite, false, false, stdcl_);
        fields_.put(aliasCompositeField, field_);
        std_ = stdcl_;
        getStandards().put(aliasBeanOne, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdcl_ = new StandardClass(aliasArrayBis, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetArray, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger()), false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasArrayBis, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdcl_ = new StandardClass(aliasArrayContainer, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        field_ = new StandardField("array", PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger()), false, false, stdcl_);
        fields_.put("array", field_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetArray, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger()), false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetCompo, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasArrayBis), false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasArrayContainer, std_);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        Object instance_ = _instance.getInstance();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getClassName(), aliasInts)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasAdd)) {
                Integer arg_ = (Integer) _args[0];
                ((Ints)instance_).add(arg_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(_method.getClassName(), aliasGeneObjects)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasAdd)) {
                Object arg_ = _args[0];
                ((GeneObjects)instance_).add(arg_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasSize)) {
                res_.setResult(new IntStruct(((GeneObjects)instance_).size()));
                return res_;
            }

        }
        if (StringList.quickEq(_method.getClassName(), aliasPickableList)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetList)) {
                res_.setResult(new StdStruct(((PickableList)instance_).getList(), aliasGeneObjects));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasRemoveAndExistAfter)) {
                Integer arg_ = (Integer) _args[0];
                res_.setResult(new BooleanStruct(((PickableList)instance_).removeAndExistAfter(arg_)));
                return res_;
            }
        }
        if (StringList.quickEq(_method.getClassName(), aliasComposite)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenOne)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasObject())) {
                    Object arg_ = _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenOne(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasNumber())) {
                    Number arg_ = (Number) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenOne(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasString())) {
                    String arg_ = (String) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenOne(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenTwo)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasObject())) {
                    Object arg_ = _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenTwo(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasString())) {
                    String arg_ = (String) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenTwo(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenThree)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasDouble())) {
                    Double arg_ = (Double)_args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasLong())) {
                    Long arg_ = (Long) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasPrimDouble())) {
                    Double arg_ = (Double)_args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_.doubleValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasPrimLong())) {
                    Long arg_ = (Long) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_.longValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenFour)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasLong())) {
                    Long arg_ = (Long) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenFour(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasPrimLong())) {
                    Long arg_ = (Long) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenFour(arg_.longValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenFive)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasLong())) {
                    Long arg_ = (Long) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenFive(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasPrimDouble())) {
                    Double arg_ = (Double) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenFive(arg_.doubleValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenSix)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasPrimLong())) {
                    Long arg_ = (Long) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenSix(arg_.longValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasLong())) {
                    Long arg_ = (Long) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenSix(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasDouble())) {
                    Double arg_ = (Double) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenSix(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetPrivateInt)) {
                res_.setResult(new IntStruct(((Composite)instance_).getPrivateInt()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasSetPrivateInt)) {
                Integer arg_ = (Integer) _args[0];
                ((Composite)instance_).setPrivateInt(arg_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(_method.getClassName(), aliasFailMethods)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasFail)) {
                res_.setError(getAliasError());
                return res_;
            }
        }
        if (StringList.quickEq(_method.getClassName(), aliasStrangeInit)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasFail)) {
                res_.setError(getAliasError());
                return res_;
            }
        }
        if (StringList.quickEq(_method.getClassName(), aliasArrayBis)) {
            ArrayBis one_ = (ArrayBis) instance_;
            int[] bytes_ = one_.getArray();
            String ret_ = PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger());
            res_.setResult(new StdStruct(bytes_, ret_));
            return res_;
        }
        if (StringList.quickEq(_method.getClassName(), aliasArrayContainer)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetArray)) {
                ArrayContainer one_ = (ArrayContainer) instance_;
                int[] bytes_ = one_.getArray();
                String ret_ = PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger());
                res_.setResult(new StdStruct(bytes_, ret_));
                return res_;
            }
            ArrayContainer one_ = (ArrayContainer) instance_;
            ArrayBis[] bytes_ = one_.getCompo();
            String ret_ = PrimitiveTypeUtil.getPrettyArrayType(aliasArrayBis);
            res_.setResult(new StdStruct(bytes_, ret_));
            return res_;
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getName(), aliasInts)) {
            res_.setResult(new StdStruct(new Ints(), aliasInts));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), aliasPickableList)) {
            res_.setResult(new StdStruct(new PickableList(), aliasPickableList));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), aliasStringList)) {
            res_.setResult(new StdStruct(new StringList(), aliasPickableList));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), aliasInheritedComposite)) {
            res_.setResult(new StdStruct(new InheritedComposite(), aliasPickableList));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), aliasFailMethods)) {
            res_.setError(getAliasError());
            return res_;
        }
        if (StringList.quickEq(_method.getName(), aliasStrangeInit)) {
            res_.setError(getAliasError());
            return res_;
        }
        return super.getOtherResult(_cont, _method, _args);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(_classField.getClassName(), aliasComposite)) {
            if (StringList.quickEq(fieldName_, aliasIntegerField)) {
                Composite cpt_ = (Composite) _instance.getInstance();
                res_.setResult(new IntStruct(cpt_.getInteger()));
                return res_;
            }
        }
        if (StringList.quickEq(_classField.getClassName(), aliasBeanOne)) {
            if (StringList.quickEq(fieldName_, aliasCompositeField)) {
                BeanOne cpt_ = (BeanOne) _instance.getInstance();
                res_.setResult(new StdStruct(cpt_.getComposite(), aliasComposite));
                return res_;
            }
        }
        if (StringList.quickEq(_classField.getClassName(), aliasStrangeInit)) {
            if (StringList.quickEq(fieldName_, aliasNotRead)) {
                res_.setError(getAliasError());
                return res_;
            }
        }
        if (StringList.quickEq(_classField.getClassName(), aliasArrayContainer)) {
            if (StringList.quickEq(fieldName_, "array")) {
                ArrayContainer cpt_ = (ArrayContainer) _instance.getInstance();
                res_.setResult(new StdStruct(cpt_.getArray(), PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger())));
                return res_;
            }
        }
        return super.getOtherResult(_cont, _classField, _instance);
    }
    @Override
    public ResultErrorStd setOtherResult(ContextEl _cont,
            ClassField _classField, Struct _instance, Struct _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(_classField.getClassName(), aliasComposite)) {
            if (StringList.quickEq(fieldName_, aliasIntegerField)) {
                Composite cpt_ = (Composite) _instance.getInstance();
                cpt_.setInteger((Integer) _value.getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        return super.setOtherResult(_cont, _classField, _instance, _value);
    }
    @Override
    public Struct getOtherElement(Object _array, int _index) {
        if (_array instanceof ArrayBis[]) {
            return new StdStruct(((ArrayBis[])_array)[_index], aliasArrayBis);
        }
        if (_array instanceof ArrayContainer[]) {
            return new StdStruct(((ArrayContainer[])_array)[_index], aliasArrayContainer);
        }
        return super.getOtherElement(_array, _index);
    }
    @Override
    public void setOtherElement(Object _array, int _index, Struct _elt) {
        if (_array instanceof ArrayBis[]) {
            ((ArrayBis[])_array)[_index] = (ArrayBis) _elt.getInstance();
        }
        if (_array instanceof ArrayContainer[]) {
            ((ArrayContainer[])_array)[_index] = (ArrayContainer) _elt.getInstance();
        }
    }
    @Override
    public String getOtherStructClassName(Object _struct, ContextEl _context) {
        if (_struct instanceof Composite) {
            return aliasComposite;
        }
        if (_struct instanceof Ints) {
            return aliasInts;
        }
        if (_struct instanceof GeneObjects) {
            return aliasGeneObjects;
        }
        if (_struct instanceof BeanOne) {
            return aliasBeanOne;
        }
        if (_struct instanceof int[]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger());
        }
        if (_struct instanceof Integer[]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasInteger());
        }
        if (_struct instanceof int[][]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger(),2);
        }
        if (_struct instanceof Integer[][]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasInteger(),2);
        }
        if (_struct instanceof ArrayContainer[]) {
            return PrimitiveTypeUtil.getPrettyArrayType(aliasArrayContainer);
        }
        if (_struct instanceof ArrayContainer) {
            return aliasArrayContainer;
        }
        if (_struct instanceof PickableList) {
            return aliasPickableList;
        }
        if (_struct instanceof ArrayBis) {
            return aliasArrayBis;
        }
        return getAliasObject();
    }
    public String getAliasInts() {
        return aliasInts;
    }
    public String getAliasComposite() {
        return aliasComposite;
    }
}
