package aiki.beans.facade.solution.dto;

import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansFacadeSolutionDtoStd {
    public static final String TYPE_PLACE_TRAINER_DTO = "aiki.beans.facade.solution.dto.PlaceTrainerDto";
    public static final String TYPE_STEP_DTO = "aiki.beans.facade.solution.dto.StepDto";
    public static final String TYPE_WILD_POKEMON_DTO = "aiki.beans.facade.solution.dto.WildPokemonDto";

    private static final String GET_POKEMON = "getPokemon";
    private static final String GET_NAMES = "getNames";
    private static final String IMAGE = "image";
    private static final String NAME = "name";
    private static final String GENDER = "gender";
    private static final String TRAINER = "trainer";
    private static final String PLACE = "place";

    public static void build(PokemonStandards _std) {
        buildPlaceTrainerDto(_std);
        buildStepDto(_std);
        buildWildPokemonDto(_std);
    }
    private static void buildPlaceTrainerDto(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_PLACE_TRAINER_DTO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(TRAINER,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(PLACE,BeanNatCommonLgNames.STRING,false,false));
        _std.getStds().addEntry(TYPE_PLACE_TRAINER_DTO, type_);
    }
    private static void buildStepDto(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_STEP_DTO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_POKEMON,params_, BeanNatLgNames.TYPE_MAP, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NAMES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_STEP_DTO, type_);
    }
    private static void buildWildPokemonDto(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_WILD_POKEMON_DTO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(NAME,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(GENDER,BeanNatCommonLgNames.STRING,false,false));
        _std.getStds().addEntry(TYPE_WILD_POKEMON_DTO, type_);
    }
    public static ResultErrorStd getResultPlaceTrainerDto(ClassField _classField, PlaceTrainerDto _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,TRAINER)) {
            res_.setResult(new StringStruct(_inst.getTrainer()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PLACE)) {
            res_.setResult(new StringStruct(_inst.getPlace()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultWildPokemonDto(ClassField _classField, WildPokemonDto _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,IMAGE)) {
            res_.setResult(new StringStruct(_inst.getImage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NAME)) {
            res_.setResult(new StringStruct(_inst.getName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,GENDER)) {
            res_.setResult(new StringStruct(_inst.getGender()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodStepDto(ClassMethodId _method, StepDto _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_POKEMON)) {
            res_.setResult(PokemonStandards.getPlLevWildPkDto(_inst.getPokemon()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NAMES)) {
            res_.setResult(PokemonStandards.getPlTr(_inst.getNames()));
            return res_;
        }
        return res_;
    }
}
