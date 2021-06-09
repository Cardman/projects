package aiki.beans.pokemon;
import aiki.beans.*;
import code.bean.nat.SpecialNatClass;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardConstructor;
import code.bean.nat.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansPokemonStd {
    public static final String TYPE_POKEDEX_BEAN = "aiki.beans.pokemon.PokedexBean";
    public static final String TYPE_POKEMON_BEAN = "aiki.beans.pokemon.PokemonBean";

    private static final String CLICK_POKEDEX = "clickPokedex";
    private static final String ROUND_WEIGHT = "roundWeight";
    private static final String ROUND_HEIGHT = "roundHeight";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_TR_ABILITY = "getTrAbility";
    private static final String GET_PAGE = "getPage";
    private static final String CLICK_BASE = "clickBase";
    private static final String GET_BASE = "getBase";
    private static final String GET_EV = "getEv";
    private static final String CLICK_MOVE = "clickMove";
    private static final String CLICK_TECHNICAL_MOVE = "clickTechnicalMove";
    private static final String CLICK_HIDDEN_MOVE = "clickHiddenMove";
    private static final String CLICK_MOVE_TUTORS = "clickMoveTutors";
    private static final String GET_MOVE_TUTOR = "getMoveTutor";
    private static final String CLICK_EGG_PK = "clickEggPk";
    private static final String GET_EGG_PK = "getEggPk";
    private static final String IS_APPEARING_ANY_WHERE = "isAppearingAnyWhere";
    private static final String IS_APPEARING_PLACE = "isAppearingPlace";
    private static final String IS_MULTI_LAYER = "isMultiLayer";
    private static final String LAYERS = "layers";
    private static final String IS_APPEARING = "isAppearing";
    private static final String CLICK_LEVEL = "clickLevel";
    private static final String GET_MAP_WIDTH = "getMapWidth";
    private static final String IS_FIRST_ROW = "isFirstRow";
    private static final String GET_PLACE_NAME = "getPlaceName";
    private static final String GET_MINI_MAP_IMAGE = "getMiniMapImage";
    private static final String SEARCH = "search";
    private static final String GET_MINI_IMAGE = "getMiniImage";
    private static final String CLICK_LINK = "clickLink";
    private static final String DISPLAY_NAME = "displayName";
    private static final String BACK_IMAGE = "backImage";
    private static final String FRONT_IMAGE = "frontImage";
    private static final String WEIGHT = "weight";
    private static final String HEIGHT = "height";
    private static final String POSSIBLE_GENDERS = "possibleGenders";
    private static final String TYPES = "types";
    private static final String ABILITIES = "abilities";
    private static final String CATCHING_RATE = "catchingRate";
    private static final String EVOLUTIONS = "evolutions";
    private static final String NAME = "name";
    private static final String EVO_BASE = "evoBase";
    private static final String EXP_EVO = "expEvo";
    private static final String MAP_VARS = "mapVars";
    private static final String EXP_RATE = "expRate";
    private static final String STATISTICS = "statistics";
    private static final String LEV_MOVES = "levMoves";
    private static final String TECHNICAL_MOVES = "technicalMoves";
    private static final String HIDDEN_MOVES = "hiddenMoves";
    private static final String MOVE_TUTORS = "moveTutors";
    private static final String EGG_GROUPS_PK = "eggGroupsPk";
    private static final String HATCHING_STEPS = "hatchingSteps";
    private static final String PLACES = "places";
    private static final String IMAGES = "images";
    private static final String TYPED_NAME = "typedName";
    private static final String TYPED_TYPE = "typedType";
    private static final String WHOLE_WORD = "wholeWord";
    private static final String TYPED_MIN_NB_POSS_EVOS = "typedMinNbPossEvos";
    private static final String TYPED_MAX_NB_POSS_EVOS = "typedMaxNbPossEvos";
    private static final String BOOLEANS = "booleans";
    private static final String IS_EVO = "isEvo";
    private static final String IS_LEG = "isLeg";
    private static final String POKEDEX = "pokedex";

    public static void build(PokemonStandards _std) {
        buildPokedexBean(_std);
        buildPokemonBean(_std);
    }
    private static void buildPokedexBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POKEDEX_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(TYPED_NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(TYPED_TYPE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(WHOLE_WORD,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(TYPED_MIN_NB_POSS_EVOS,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(TYPED_MAX_NB_POSS_EVOS,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(BOOLEANS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(IS_EVO,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(IS_LEG,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(POKEDEX, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(SEARCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_MINI_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_LINK,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_POKEDEX_BEAN, type_);
    }
    private static void buildPokemonBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POKEMON_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(BACK_IMAGE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(FRONT_IMAGE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(WEIGHT,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(HEIGHT,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(POSSIBLE_GENDERS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(CATCHING_RATE,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(EVOLUTIONS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(EVO_BASE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(EXP_EVO,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MAP_VARS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(EXP_RATE,_std.getAliasPrimLong(),false,false,type_));
        fields_.add(new StandardField(STATISTICS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(LEV_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(TECHNICAL_MOVES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(HIDDEN_MOVES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MOVE_TUTORS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(EGG_GROUPS_PK, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(HATCHING_STEPS,PokemonStandards.TYPE_LG_INT,false,false,type_));
        fields_.add(new StandardField(PLACES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IMAGES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_POKEDEX,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ROUND_WEIGHT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ROUND_HEIGHT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_PAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_BASE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_BASE,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_EV,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_TECHNICAL_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_HIDDEN_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVE_TUTORS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_MOVE_TUTOR,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_EGG_PK,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_EGG_PK,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_APPEARING_ANY_WHERE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_APPEARING_PLACE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_MULTI_LAYER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(LAYERS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_APPEARING,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_LEVEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_MAP_WIDTH,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_FIRST_ROW,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_PLACE_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_MINI_MAP_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_POKEMON_BEAN, type_);
    }
    public static ResultErrorStd getResultPokedexBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        PokedexBean instance_ = (PokedexBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,TYPED_NAME)) {
            res_.setResult(new StringStruct(instance_.getTypedName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPED_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTypedType()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WHOLE_WORD)) {
            res_.setResult(BooleanStruct.of(instance_.getWholeWord()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPED_MIN_NB_POSS_EVOS)) {
            res_.setResult(new StringStruct(instance_.getTypedMinNbPossEvos()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPED_MAX_NB_POSS_EVOS)) {
            res_.setResult(new StringStruct(instance_.getTypedMaxNbPossEvos()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BOOLEANS)) {
            res_.setResult(PokemonStandards.getStrStr(_cont,instance_.getBooleans()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IS_EVO)) {
            res_.setResult(BeanLgNames.wrapStd(instance_.getIsEvo()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IS_LEG)) {
            res_.setResult(BeanLgNames.wrapStd(instance_.getIsLeg()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,POKEDEX)) {
            res_.setResult(PokemonStandards.getPkLine(instance_.getPokedex()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultPokemonBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        PokemonBean instance_ = (PokemonBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(instance_.getDisplayName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BACK_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getBackImage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,FRONT_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getFrontImage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WEIGHT)) {
            res_.setResult(new RateStruct(instance_.getWeight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HEIGHT)) {
            res_.setResult(new RateStruct(instance_.getHeight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,POSSIBLE_GENDERS)) {
            res_.setResult(std_.getStringArray(instance_.getPossibleGenders()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES)) {
            res_.setResult(std_.getStringArray(instance_.getTypes()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES)) {
            res_.setResult(std_.getStringArray(instance_.getAbilities()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CATCHING_RATE)) {
            res_.setResult(new IntStruct(instance_.getCatchingRate()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EVOLUTIONS)) {
            res_.setResult(std_.getStringArray(instance_.getEvolutions()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EVO_BASE)) {
            res_.setResult(new StringStruct(instance_.getEvoBase()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EXP_EVO)) {
            res_.setResult(new StringStruct(instance_.getExpEvo()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAP_VARS)) {
            res_.setResult(PokemonStandards.getStrStr(_cont,instance_.getMapVars()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EXP_RATE)) {
            res_.setResult(new LongStruct(instance_.getExpRate()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATISTICS)) {
            res_.setResult(std_.getStringArray(instance_.getStatistics()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LEV_MOVES)) {
            res_.setResult(PokemonStandards.getLvMv(instance_.getLevMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TECHNICAL_MOVES)) {
            res_.setResult(PokemonStandards.getShortStr(_cont,instance_.getTechnicalMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HIDDEN_MOVES)) {
            res_.setResult(PokemonStandards.getShortStr(_cont,instance_.getHiddenMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVE_TUTORS)) {
            res_.setResult(std_.getStringArray(instance_.getMoveTutors()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EGG_GROUPS_PK)) {
            res_.setResult(std_.getStringArray(instance_.getEggGroupsPk()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HATCHING_STEPS)) {
            res_.setResult(new LgIntStruct(instance_.getHatchingSteps(),PokemonStandards.TYPE_LG_INT));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PLACES)) {
            res_.setResult(PokemonStandards.getPlInd(instance_.getPlaces()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IMAGES)) {
            res_.setResult(PokemonStandards.getWcStr(_cont,instance_.getImages()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultPokedexBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val) {
        ResultErrorStd res_ = new ResultErrorStd();
        PokedexBean instance_ = (PokedexBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,TYPED_NAME)) {
            instance_.setTypedName(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPED_TYPE)) {
            instance_.setTypedType(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WHOLE_WORD)) {
            instance_.setWholeWord(BooleanStruct.isTrue(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPED_MIN_NB_POSS_EVOS)) {
            instance_.setTypedMinNbPossEvos(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPED_MAX_NB_POSS_EVOS)) {
            instance_.setTypedMaxNbPossEvos(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IS_EVO)) {
            instance_.setIsEvo(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IS_LEG)) {
            instance_.setIsLeg(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodPokedexBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        PokedexBean instance_ = (PokedexBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,SEARCH)) {
            res_.setResult(new StringStruct(instance_.search()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MINI_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getMiniImage(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_LINK)) {
            res_.setResult(new StringStruct(instance_.clickLink(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodPokemonBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        PokemonBean instance_ = (PokemonBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_POKEDEX)) {
            res_.setResult(new StringStruct(instance_.clickPokedex()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ROUND_WEIGHT)) {
            res_.setResult(new StringStruct(instance_.roundWeight()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ROUND_HEIGHT)) {
            res_.setResult(new StringStruct(instance_.roundHeight()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTrAbility(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_PAGE)) {
            res_.setResult(new StringStruct(instance_.getPage(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_BASE)) {
            res_.setResult(new StringStruct(instance_.clickBase()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_BASE)) {
            res_.setResult(new IntStruct(instance_.getBase(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_EV)) {
            res_.setResult(new IntStruct(instance_.getEv(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_TECHNICAL_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickTechnicalMove(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_HIDDEN_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickHiddenMove(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE_TUTORS)) {
            res_.setResult(new StringStruct(instance_.clickMoveTutors(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE_TUTOR)) {
            res_.setResult(new StringStruct(instance_.getMoveTutor(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_EGG_PK)) {
            res_.setResult(new StringStruct(instance_.clickEggPk(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_EGG_PK)) {
            res_.setResult(new StringStruct(instance_.getEggPk(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_APPEARING_ANY_WHERE)) {
            res_.setResult(BooleanStruct.of(instance_.isAppearingAnyWhere()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_APPEARING_PLACE)) {
            res_.setResult(BooleanStruct.of(instance_.isAppearingPlace(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_MULTI_LAYER)) {
            res_.setResult(BooleanStruct.of(instance_.isMultiLayer(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,LAYERS)) {
            res_.setResult(PokemonStandards.getLayers(_cont,instance_.layers(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_APPEARING)) {
            res_.setResult(BooleanStruct.of(instance_.isAppearing(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_LEVEL)) {
            res_.setResult(new StringStruct(instance_.clickLevel(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MAP_WIDTH)) {
            res_.setResult(new IntStruct(instance_.getMapWidth()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FIRST_ROW)) {
            res_.setResult(BooleanStruct.of(instance_.isFirstRow(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_PLACE_NAME)) {
            res_.setResult(new StringStruct(instance_.getPlaceName(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MINI_MAP_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getMiniMapImage(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
}
