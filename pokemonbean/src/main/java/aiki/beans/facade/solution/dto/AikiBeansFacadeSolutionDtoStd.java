package aiki.beans.facade.solution.dto;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.StdStruct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

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

    public static void build(BeanLgNames _std) {
        buildPlaceTrainerDto(_std);
        buildStepDto(_std);
        buildWildPokemonDto(_std);
    }
    private static void buildPlaceTrainerDto(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_PLACE_TRAINER_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.put(TRAINER,new StandardField(TRAINER,_std.getAliasString(),false,false,type_));
        fields_.put(PLACE,new StandardField(PLACE,_std.getAliasString(),false,false,type_));
        _std.getStandards().put(TYPE_PLACE_TRAINER_DTO, type_);
    }
    private static void buildStepDto(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_STEP_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_POKEMON,params_,_std.getCustMap(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NAMES,params_,_std.getCustList(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_STEP_DTO, type_);
    }
    private static void buildWildPokemonDto(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_WILD_POKEMON_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.put(IMAGE,new StandardField(IMAGE,_std.getAliasString(),false,false,type_));
        fields_.put(NAME,new StandardField(NAME,_std.getAliasString(),false,false,type_));
        fields_.put(GENDER,new StandardField(GENDER,_std.getAliasString(),false,false,type_));
        _std.getStandards().put(TYPE_WILD_POKEMON_DTO, type_);
    }
    public static ResultErrorStd getResultPlaceTrainerDto(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        PlaceTrainerDto instance_ = (PlaceTrainerDto) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TRAINER)) {
            res_.setResult(new StringStruct(instance_.getTrainer()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLACE)) {
            res_.setResult(new StringStruct(instance_.getPlace()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultWildPokemonDto(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        WildPokemonDto instance_ = (WildPokemonDto) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodStepDto(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        StepDto instance_ = (StepDto) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_POKEMON)) {
            res_.setResult(new StdStruct(instance_.getPokemon(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_NAMES)) {
            res_.setResult(new StdStruct(instance_.getNames(),std_.getCustList()));
            return res_;
        }
        return res_;
    }
}
