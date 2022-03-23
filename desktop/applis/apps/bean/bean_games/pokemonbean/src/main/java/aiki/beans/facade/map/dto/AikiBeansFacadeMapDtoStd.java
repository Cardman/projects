package aiki.beans.facade.map.dto;

import aiki.beans.PlaceStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansFacadeMapDtoStd {
    public static final String TYPE_PLACE_INDEX = "aiki.beans.facade.map.dto.PlaceIndex";

    private static final String GET_PLACE = "getPlace";
    private static final String INDEX = "index";

    public static void build(PokemonStandards _std) {
        buildPlaceIndex(_std);
    }
    private static void buildPlaceIndex(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList< SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_PLACE_INDEX, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_PLACE,params_,PokemonStandards.TYPE_PLACE, false, MethodModifier.NORMAL);
        methods_.add(method_);
        _std.getStds().addEntry(TYPE_PLACE_INDEX, type_);
    }
    public static ResultErrorStd getResultPlaceIndex(ClassField _classField, PlaceIndex _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(_inst.getIndex()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodPlaceIndex(ClassMethodId _method, PlaceIndex _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_PLACE)) {
            res_.setResult(new PlaceStruct(_inst.getPlace(),PokemonStandards.TYPE_PLACE));
            return res_;
        }
        return res_;
    }
}
