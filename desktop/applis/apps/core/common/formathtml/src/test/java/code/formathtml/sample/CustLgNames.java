package code.formathtml.sample;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.bean.nat.BeanNatLgNames;
import code.bean.RealInstanceStruct;
import code.util.*;
import code.util.core.StringUtil;

public final class CustLgNames extends BeanNatLgNames {

    private final String aliasStringList = "code.util.StringList";
    private final String aliasComposite = "code.expressionlanguage.classes.Composite";
    private final String aliasCompositeSec = "$CompositeSec";
    private final String aliasInheritedComposite = "code.expressionlanguage.classes.InheritedComposite";
    private final String aliasFailMethods = "code.expressionlanguage.classes.FailMethods";
    private final String aliasNotRead = "NOT_READ";
    private final String aliasStrangeInit = "code.expressionlanguage.classes.StrangeInit";
    private final String aliasFail = "fail";
    private final String aliasBeanOne = "code.expressionlanguage.classes.BeanOne";
    private final String aliasInts = "code.expressionlanguage.classes.Ints";
    private final String aliasAdd = "add";
    private final String aliasSize = "size";
    private final String aliasIntegerField = "integer";
    private final String aliasObjIntegerField = "objInteger";
    private final String aliasCompositeField = "composite";
    private final String aliasGetList = "getList";
    private final String aliasRemoveAndExistAfter = "removeAndExistAfter";
    private final String aliasGeneObjects = "code.expressionlanguage.classes.GeneObjects";
    private final String aliasPickableList = "code.expressionlanguage.classes.PickableList";
    private final String aliasGetOverridenOne = "getOverridenOne";
    private final String aliasGetOverridenTwo = "getOverridenTwo";
    private final String aliasGetOverridenThree = "getOverridenThree";
    private final String aliasGetOverridenFour = "getOverridenFour";
    private final String aliasGetOverridenFive = "getOverridenFive";
    private final String aliasGetOverridenSix = "getOverridenSix";
    private final String aliasSetPrivateInt = "setPrivateInt";
    private final String aliasGetPrivateInt = "getPrivateInt";
    private final String aliasGetInteger = "getInteger";
    @Override
    public void buildOther() {
        buildBeans();
        CustList<StandardField> fields_;
        StandardField field_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList(getContent().getNbAlias().getAliasInteger());
        stdcl_ = new StandardClass(aliasInts, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasAdd, params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        std_ = stdcl_;
        getStandards().addEntry(aliasInts, std_);
        getIterables().put(aliasInts, getContent().getNbAlias().getAliasInteger());
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasPickableList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetList, params_, aliasGeneObjects, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasRemoveAndExistAfter, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        std_ = stdcl_;
        getStandards().addEntry(aliasPickableList, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasGeneObjects, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasAdd, params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSize, params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
//        params_ = new StringList(aliasSimpleIteratorType);
//        method_ = new StandardMethod(aliasSimpleIterator, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, aliasGeneObjects);
//        methods_.add( method_);
        std_ = stdcl_;
        getStandards().addEntry(aliasGeneObjects, std_);
        getIterables().put(aliasGeneObjects, getAliasObject());
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
//        params_ = new StringList();
//        method_ = new StandardMethod(getAliasNext(), params_, getObj(), false, MethodModifier.FINAL, aliasSimpleIteratorType);
//        methods_.add( method_);
//        params_ = new StringList();
//        method_ = new StandardMethod(getAliasHasNext(), params_, getPrimBool(), false, MethodModifier.FINAL, aliasSimpleIteratorType);
//        methods_.add( method_);
        stdcl_ = new StandardClass(aliasStringList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        std_ = stdcl_;
        getStandards().addEntry(aliasStringList, std_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasComposite, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasGetOverridenOne, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasGetOverridenOne, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasNumber());
        method_ = new StandardMethod(aliasGetOverridenOne, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasGetOverridenTwo, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasGetOverridenTwo, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimDouble());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasDouble());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetOverridenFour, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenFour, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimDouble());
        method_ = new StandardMethod(aliasGetOverridenFive, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenFive, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasDouble());
        method_ = new StandardMethod(aliasGetOverridenSix, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetOverridenSix, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenSix, params_, getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSetPrivateInt, params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrivateInt, params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetInteger, params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        //aliasGetOverridenOne
        field_ = new StandardField(aliasIntegerField, getContent().getPrimTypes().getAliasPrimInteger(), false, false, stdcl_);
        fields_.add( field_);
        field_ = new StandardField(aliasObjIntegerField, getContent().getNbAlias().getAliasInteger(), false, false, stdcl_);
        fields_.add( field_);
        field_ = new StandardField(aliasCompositeField, aliasCompositeSec, false, false, stdcl_);
        fields_.add( field_);
        std_ = stdcl_;
        getStandards().addEntry(aliasComposite, std_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasCompositeSec, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        field_ = new StandardField(aliasIntegerField, getContent().getPrimTypes().getAliasPrimInteger(), false, false, stdcl_);
        fields_.add( field_);
        std_ = stdcl_;
        getStandards().addEntry(aliasCompositeSec, std_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasInheritedComposite, fields_, constructors_, methods_, aliasComposite, MethodModifier.FINAL);
        std_ = stdcl_;
        getStandards().addEntry(aliasInheritedComposite, std_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasFailMethods, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFail, params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC);
        methods_.add( method_);
        std_ = stdcl_;
        getStandards().addEntry(aliasFailMethods, std_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasStrangeInit, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFail, params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC);
        methods_.add( method_);
        fields_.add( new StandardField(aliasNotRead, getAliasString(), true, true, stdcl_));
        std_ = stdcl_;
        getStandards().addEntry(aliasStrangeInit, std_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        stdcl_ = new StandardClass(aliasBeanOne, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        field_ = new StandardField(aliasCompositeField, aliasComposite, false, false, stdcl_);
        fields_.add( field_);
        std_ = stdcl_;
        getStandards().addEntry(aliasBeanOne, std_);
    }
    @Override
    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        Object instance_ = null;
        if (!_method.getConstraints().isStaticMethod()) {
            instance_ = ((RealInstanceStruct)_instance).getInstance();
        }
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(_method.getClassName(), aliasInts)) {
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasAdd)) {
                Integer arg_ = ((NumberStruct) _args[0]).intStruct();
                ((Ints)instance_).add(arg_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringUtil.quickEq(_method.getClassName(), aliasGeneObjects)) {
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasAdd)) {
                Object arg_ = _args[0];
                ((GeneObjects)instance_).add(arg_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasSize)) {
                res_.setResult(new IntStruct(((GeneObjects)instance_).size()));
                return res_;
            }

        }
        if (StringUtil.quickEq(_method.getClassName(), aliasPickableList)) {
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasGetList)) {
                res_.setResult(new StdStruct(((PickableList)instance_).getList(), aliasGeneObjects));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasRemoveAndExistAfter)) {
                Integer arg_ = ((NumberStruct) _args[0]).intStruct();
                res_.setResult(BooleanStruct.of(((PickableList)instance_).removeAndExistAfter(arg_)));
                return res_;
            }
        }
        if (StringUtil.quickEq(_method.getClassName(), aliasComposite)) {
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasGetOverridenOne)) {
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasObject())) {
                    Object arg_ = _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenOne(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getNbAlias().getAliasNumber())) {
                    Object arg_ = _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenOne(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasString())) {
                    String arg_ = ((StringStruct) _args[0]).getInstance();
                    String resLoc_ = ((Composite)instance_).getOverridenOne(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasGetOverridenTwo)) {
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasObject())) {
                    Object arg_ = _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenTwo(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasString())) {
                    if (_args[0] == NullStruct.NULL_VALUE) {
                        String resLoc_ = ((Composite)instance_).getOverridenTwo(null);
                        res_.setResult(new StringStruct(resLoc_));
                        return res_;
                    }
                    String arg_ = ((StringStruct) _args[0]).getInstance();
                    String resLoc_ = ((Composite)instance_).getOverridenTwo(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasGetOverridenThree)) {
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getNbAlias().getAliasDouble())) {
                    Double arg_ = ((NumberStruct) _args[0]).doubleStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getNbAlias().getAliasLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).longStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getPrimTypes().getAliasPrimDouble())) {
                    Double arg_ = ((NumberStruct) _args[0]).doubleStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_.doubleValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getPrimTypes().getAliasPrimLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).longStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_.longValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasGetOverridenFour)) {
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getNbAlias().getAliasLong())) {
                    if (_args[0] == NullStruct.NULL_VALUE) {
                        String resLoc_ = ((Composite)instance_).getOverridenFour(null);
                        res_.setResult(new StringStruct(resLoc_));
                        return res_;
                    }
                    Long arg_ = ((NumberStruct) _args[0]).longStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenFour(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getPrimTypes().getAliasPrimLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).longStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenFour(arg_.longValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasGetOverridenFive)) {
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getNbAlias().getAliasLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).longStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenFive(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getPrimTypes().getAliasPrimDouble())) {
                    Double arg_ = ((NumberStruct) _args[0]).doubleStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenFive(arg_.doubleValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasGetOverridenSix)) {
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getPrimTypes().getAliasPrimLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).longStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenSix(arg_.longValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getNbAlias().getAliasLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).longStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenSix(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringUtil.quickEq(_method.getConstraints().getParametersTypes().first(), getContent().getNbAlias().getAliasDouble())) {
                    Double arg_ = ((NumberStruct) _args[0]).doubleStruct();
                    String resLoc_ = ((Composite)instance_).getOverridenSix(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasGetPrivateInt)) {
                res_.setResult(new IntStruct(((Composite)instance_).getPrivateInt()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasSetPrivateInt)) {
                Integer arg_ = ((NumberStruct) _args[0]).intStruct();
                ((Composite)instance_).setPrivateInt(arg_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringUtil.quickEq(_method.getClassName(), aliasFailMethods)) {
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasFail)) {
                return res_;
            }
        }
        if (StringUtil.quickEq(_method.getClassName(), aliasStrangeInit)) {
            if (StringUtil.quickEq(_method.getConstraints().getName(), aliasFail)) {
                return res_;
            }
        }
        return super.getOtherResult(_stack, _cont, _instance, _method, _args);
    }

    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        return null;
    }


    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(_method.getName(), aliasInts)) {
            res_.setResult(StdStruct.newInstance(new Ints(), aliasInts));
            return res_;
        }
        if (StringUtil.quickEq(_method.getName(), aliasPickableList)) {
            res_.setResult(StdStruct.newInstance(new PickableList(), aliasPickableList));
            return res_;
        }
        if (StringUtil.quickEq(_method.getName(), aliasStringList)) {
            res_.setResult(new StdStruct(new StringList(), aliasStringList));
            return res_;
        }
        if (StringUtil.quickEq(_method.getName(), aliasInheritedComposite)) {
            res_.setResult(new StdStruct(new InheritedComposite(), aliasInheritedComposite));
            return res_;
        }
        if (StringUtil.quickEq(_method.getName(), aliasFailMethods)) {
            return res_;
        }
        if (StringUtil.quickEq(_method.getName(), aliasStrangeInit)) {
            return res_;
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(_classField.getClassName(), aliasComposite)) {
            if (StringUtil.quickEq(fieldName_, aliasIntegerField)) {
                Composite cpt_ = (Composite) ((RealInstanceStruct)_instance).getInstance();
                res_.setResult(new IntStruct(cpt_.getInteger()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, aliasObjIntegerField)) {
                Composite cpt_ = (Composite) ((RealInstanceStruct)_instance).getInstance();
                Integer i_ = cpt_.getObjInteger();
                if (i_ != null) {
                    res_.setResult(new IntStruct(i_));
                } else {
                    res_.setResult(NullStruct.NULL_VALUE);
                }
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, aliasCompositeField)) {
                Composite cpt_ = (Composite) ((RealInstanceStruct)_instance).getInstance();
                CompositeSec i_ = cpt_.getComposite();
                res_.setResult(StdStruct.newInstance(i_, aliasCompositeSec));
                return res_;
            }
        }
        if (StringUtil.quickEq(_classField.getClassName(), aliasCompositeSec)) {
            CompositeSec cpt_ = (CompositeSec) ((RealInstanceStruct)_instance).getInstance();
            res_.setResult(new IntStruct(cpt_.getInteger()));
            return res_;
        }
        if (StringUtil.quickEq(_classField.getClassName(), aliasBeanOne)) {
            if (StringUtil.quickEq(fieldName_, aliasCompositeField)) {
                BeanOne cpt_ = (BeanOne) ((RealInstanceStruct)_instance).getInstance();
                res_.setResult(new StdStruct(cpt_.getComposite(), aliasComposite));
                return res_;
            }
        }
        if (StringUtil.quickEq(_classField.getClassName(), aliasStrangeInit)) {
            if (StringUtil.quickEq(fieldName_, aliasNotRead)) {
                return res_;
            }
        }
        return res_;
    }
    public ResultErrorStd getOtherName(ContextEl _cont, Struct _instance) {
        return new ResultErrorStd();
    }
    @Override
    public ResultErrorStd setOtherResult(ContextEl _cont,
            ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(_classField.getClassName(), aliasComposite)) {
            if (StringUtil.quickEq(fieldName_, aliasIntegerField)) {
                Composite cpt_ = (Composite) ((RealInstanceStruct)_instance).getInstance();
                cpt_.setInteger((Integer) _value);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, aliasObjIntegerField)) {
                Composite cpt_ = (Composite) ((RealInstanceStruct)_instance).getInstance();
                cpt_.setObjInteger((Integer)_value);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        return res_;
    }

    @Override
    public Struct wrapStd(Object _element) {
        if (_element == null) {
            return NullStruct.NULL_VALUE;
        }
        if (_element instanceof Byte) {
            return new ByteStruct((Byte) _element);
        }
        if (_element instanceof Short) {
            return new ShortStruct((Short) _element);
        }
        if (_element instanceof Character) {
            return new CharStruct((Character) _element);
        }
        if (_element instanceof Integer) {
            return new IntStruct((Integer) _element);
        }
        if (_element instanceof Long) {
            return new LongStruct((Long) _element);
        }
        if (_element instanceof Float) {
            return new FloatStruct((Float) _element);
        }
        if (_element instanceof Double) {
            return new DoubleStruct((Double) _element);
        }
        if (_element instanceof Boolean) {
            return BooleanStruct.of((Boolean) _element);
        }
        if (_element instanceof String) {
            return new StringStruct((String) _element);
        }
        if (_element instanceof StringBuilder) {
            return new StringBuilderStruct((StringBuilder) _element);
        }
        String aliasObject_ = getAliasObject();
        return StdStruct.newInstance(_element, aliasObject_);
    }

    @Override
    protected Struct newId(Object _obj, String _className) {
        return StdStruct.newInstance(_obj, _className);
    }
    public ResultErrorStd getOtherStructToBeValidated(StringList _values, String _className, Configuration _context) {
        return new ResultErrorStd();
    }
    public String getAliasInts() {
        return aliasInts;
    }
    public String getAliasComposite() {
        return aliasComposite;
    }
}
