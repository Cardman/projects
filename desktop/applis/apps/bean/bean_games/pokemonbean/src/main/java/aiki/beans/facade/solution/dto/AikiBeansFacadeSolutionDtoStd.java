package aiki.beans.facade.solution.dto;
import aiki.beans.DefaultStruct;
import aiki.beans.PokemonStandards;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
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
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_PLACE_TRAINER_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.add(new StandardField(TRAINER,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(PLACE,_std.getAliasString(),false,false,type_));
        _std.getStandards().addEntry(TYPE_PLACE_TRAINER_DTO, type_);
    }
    private static void buildStepDto(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_STEP_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_POKEMON,params_, BeanNatLgNames.TYPE_MAP, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NAMES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_STEP_DTO, type_);
    }
    private static void buildWildPokemonDto(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_WILD_POKEMON_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.add(new StandardField(IMAGE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(GENDER,_std.getAliasString(),false,false,type_));
        _std.getStandards().addEntry(TYPE_WILD_POKEMON_DTO, type_);
    }
    public static ResultErrorStd getResultPlaceTrainerDto(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        PlaceTrainerDto instance_ = (PlaceTrainerDto) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,TRAINER)) {
            res_.setResult(new StringStruct(instance_.getTrainer()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PLACE)) {
            res_.setResult(new StringStruct(instance_.getPlace()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultWildPokemonDto(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        WildPokemonDto instance_ = (WildPokemonDto) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodStepDto(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        StepDto instance_ = (StepDto) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_POKEMON)) {
            res_.setResult(new DefaultStruct(instance_.getPokemon(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NAMES)) {
            res_.setResult(new DefaultStruct(instance_.getNames(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
}
