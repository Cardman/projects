package code.formathtml.classes;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public class CustBeanLgNames extends BeanLgNames {

    @Override
    public void buildOther() {
        buildBeans();
        StringMap<StandardField> fields_;
        fields_ = new StringMap<StandardField>();
        StandardClass std_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        constructors_ = new CustList<StandardConstructor>();
        StringList params_;
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        StandardClass cl_;
        cl_ = new StandardClass("code.util.StringList", fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(getAliasSimpleIterableType());
        cl_.setIterative(getAliasString());
        getStandards().put("code.util.StringList", cl_);
    }
    @Override
    public StringList getDefaultValues(ContextEl _cont, String _className,
            String _value) {
        if (StringList.quickEq(_className, "code.formathtml.classes.EnumNumber")) {
            return StringList.splitChars(_value, ',');
        }
        if (StringList.quickEq(_className, "code.formathtml.classes.EnumNumbers")) {
            return new StringList(_value);
        }
        return new StringList();
    }
    @Override
    public String getOtherBeanStructClassName(Object _struct, ContextEl _context) {
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
        if (_struct instanceof Object[][]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasObject(), 2);
        }
        if (_struct instanceof String[]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasString());
        }
        if (_struct instanceof Object[]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasObject());
        }
        if (_struct instanceof EnumNumber) {
            return "code.formathtml.classes.EnumNumber";
        }
        if (_struct instanceof EnumNumbers) {
            return "code.formathtml.classes.EnumNumbers";
        }
        if (_struct instanceof Composite) {
            return "code.formathtml.classes.Composite";
        }
        if (_struct instanceof EncapsFields) {
            return "code.formathtml.classes.EncapsFields";
        }
        if (_struct instanceof BeanOne) {
            return "code.formathtml.classes.BeanOne";
        }
        if (_struct instanceof BeanTwo) {
            return "code.formathtml.classes.BeanTwo";
        }
        if (_struct instanceof BeanThree) {
            return "code.formathtml.classes.BeanThree";
        }
        if (_struct instanceof BeanFour) {
            return "code.formathtml.classes.BeanFour";
        }
        if (_struct instanceof BeanFive) {
            return "code.formathtml.classes.BeanFive";
        }
        if (_struct instanceof BeanSix) {
            return "code.formathtml.classes.BeanSix";
        }
        if (_struct instanceof BeanSeven) {
            return "code.formathtml.classes.BeanSeven";
        }
        if (_struct instanceof BeanEight) {
            return "code.formathtml.classes.BeanEight";
        }
        if (_struct instanceof Rate) {
            return "code.formathtml.classes.Rate";
        }
        if (_struct instanceof RateEq) {
            return "code.formathtml.classes.RateEq";
        }
        if (_struct instanceof StringList) {
            return "code.util.StringList";
        }
        if (_struct instanceof Ints) {
            return "code.formathtml.classes.Ints";
        }
        if (_struct instanceof GeneObjects) {
            return "code.formathtml.classes.GeneObjects";
        }
        if (_struct instanceof GeneObjs) {
            return "code.formathtml.classes.GeneObjs";
        }
        if (_struct instanceof PickableList) {
            return "code.formathtml.classes.PickableList";
        }
        return getAliasObject();
    }
    @Override
    public ResultErrorStd getOtherStructToBeValidated(StringList _values,
            String _className, ContextEl _context) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_className, "code.formathtml.classes.Rate")) {
            res_.setResult(new StdStruct(new Rate(_values.first()), _className));
            return res_;
        }
        if (StringList.quickEq(_className, "code.formathtml.classes.EnumNumber")) {
            EnumNumber en_ = EnumNumber.getByName(_values.first());
            if (en_ == null) {
                res_.setError(getAliasError());
            } else {
                res_.setResult(new StdStruct(en_, _className));
            }
            return res_;
        }
        if (StringList.quickEq(_className, "code.formathtml.classes.EnumNumbers")) {
            EnumNumbers list_ = new EnumNumbers();
            for (String s: _values) {
                list_.add(EnumNumber.getByName(s));
            }
            res_.setResult(new StdStruct(list_, _className));
            return res_;
        }
        return super.getOtherStructToBeValidated(_values, _className, _context);
    }
    @Override
    public ResultErrorStd setOtherElementAtIndex(Struct _struct, int _index, boolean _key,
            Struct _element, ContextEl _context) {
        ResultErrorStd res_ = new ResultErrorStd();
        res_.setResult(NullStruct.NULL_VALUE);
        if (_struct.getInstance() instanceof StringList) {
            ((StringList)_struct.getInstance()).set(_index, (String) _element.getInstance());
            return res_;
        }
        if (_struct.getInstance() instanceof NatTreeMapStringInteger) {
            if (_key) {
                ((NatTreeMapStringInteger)_struct.getInstance()).setKey(_index, (String) _element.getInstance());
                ((NatTreeMapStringInteger)_struct.getInstance()).applyChanges();
                return res_;
            }
            ((NatTreeMapStringInteger)_struct.getInstance()).setValue(_index, (Integer) _element.getInstance());
            return res_;
        }
        return res_;
    }
}
