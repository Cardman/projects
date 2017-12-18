package code.expressionlanguage.classes;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
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
import code.util.SimpleItr;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.SimpleIterable;

public class CustLgNames extends LgNames {

    private String aliasSimpleIterable = "code.util.ints.SimpleIterable";
    private String aliasInts = "code.expressionlanguage.classes.Ints";
    private String aliasAdd = "add";
    private String aliasSize = "size";
    private String aliasGetList = "getList";
    private String aliasRemoveAndExistAfter = "removeAndExistAfter";
    private String aliasSimpleIteratorType = "code.util.SimpleItr";
    private String aliasGeneObjects = "code.expressionlanguage.classes.GeneObjects";
    private String aliasPickableList = "code.expressionlanguage.classes.PickableList";
    @Override
    public void buildOther() {
        StringMap<StandardField> fields_;
        StringList noTypes_ = new StringList();
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList(getAliasInteger());
        method_ = new StandardMethod(aliasAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, aliasInts);
        methods_.put(method_.getId(), method_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        StandardType std_;
        StandardClass stdcl_;
        stdcl_ = new StandardClass(aliasInts, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        stdcl_.setIterative(getAliasInteger());
        std_ = stdcl_;
        getStandards().put(aliasInts, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetList, params_, aliasGeneObjects, false, MethodModifier.FINAL, aliasPickableList);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasRemoveAndExistAfter, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, aliasPickableList);
        methods_.put(method_.getId(), method_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasPickableList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        std_ = stdcl_;
        getStandards().put(aliasPickableList, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, aliasGeneObjects);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSize, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, aliasGeneObjects);
        methods_.put(method_.getId(), method_);
//        params_ = new StringList(aliasSimpleIteratorType);
//        method_ = new StandardMethod(aliasSimpleIterator, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, aliasGeneObjects);
//        methods_.put(method_.getId(), method_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasGeneObjects, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        stdcl_.setIterative(getAliasObject());
        std_ = stdcl_;
        getStandards().put(aliasGeneObjects, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList();
        method_ = new StandardMethod(getAliasNext(), params_, getAliasObject(), false, MethodModifier.FINAL, aliasSimpleIteratorType);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasHasNext(), params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, aliasSimpleIteratorType);
        methods_.put(method_.getId(), method_);
        stdcl_ = new StandardClass(aliasSimpleIteratorType, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        std_ = stdcl_;
        getStandards().put(aliasSimpleIteratorType, std_);
        //aliasSimpleIteratorType
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        Object instance_ = _instance.getInstance();
        ResultErrorStd res_ = new ResultErrorStd();
        if (instance_ instanceof SimpleIterable) {
            if (StringList.quickEq(_method.getConstraints().getName(), getAliasSimpleIterator())) {
                res_.setResult(new StdStruct(((SimpleIterable) instance_).simpleIterator(), aliasSimpleIteratorType));
                return res_;
            }
        }
        if (instance_ instanceof SimpleItr) {
            if (StringList.quickEq(_method.getConstraints().getName(), getAliasNext())) {
                Object result_ = ((SimpleItr)instance_).next();
                if (result_ instanceof Integer) {
                    res_.setResult(new IntStruct((Integer) result_));
                    return res_;
                }
                if (result_ instanceof Long) {
                    res_.setResult(new LongStruct((Long) result_));
                    return res_;
                }
            } else {
                res_.setResult(new BooleanStruct(((SimpleItr)instance_).hasNext()));
                return res_;
            }
        }
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
        return super.getOtherResult(_cont, _instance, _method, _args);
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
        return super.getOtherResult(_cont, _method, _args);
    }
}
