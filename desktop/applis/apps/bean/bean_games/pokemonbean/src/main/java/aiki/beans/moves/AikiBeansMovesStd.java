package aiki.beans.moves;
import aiki.beans.AikiBeansStd;
import aiki.beans.DefaultStruct;
import aiki.beans.PokemonStandards;
import aiki.beans.facade.dto.AikiBeansFacadeDtoStd;
import aiki.beans.facade.dto.MoveLine;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.*;
import code.bean.RealInstanceStruct;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansMovesStd {
    public static final String TYPE_MOVE_BEAN = "aiki.beans.moves.MoveBean";
    public static final String TYPE_MOVE_LINE_BEAN = "aiki.beans.moves.MoveLineBean";
    public static final String TYPE_MOVES_BEAN = "aiki.beans.moves.MovesBean";

    private static final String CLICK_MOVES = "clickMoves";
    private static final String TYPES_DEPEND_ONLY_ON_ITEM = "typesDependOnlyOnItem";
    private static final String IS_ITEM = "isItem";
    private static final String CLICK_TYPES_BY_OWNED_ITEMS = "clickTypesByOwnedItems";
    private static final String GET_TR_TYPES_BY_OWNED_ITEMS = "getTrTypesByOwnedItems";
    private static final String TYPES_DEPEND_ONLY_ON_WEATHER = "typesDependOnlyOnWeather";
    private static final String IS_WEATHER = "isWeather";
    private static final String CLICK_TYPES_BY_WEATHERS = "clickTypesByWeathers";
    private static final String GET_TR_TYPES_BY_WEATHERS = "getTrTypesByWeathers";
    private static final String TYPES_DEPEND_ON_WEATHER_AND_ITEM = "typesDependOnWeatherAndItem";
    private static final String IS_DAMAGING_MOVE = "isDamagingMove";
    private static final String IS_DAMAGING_DIRECT_MOVE = "isDamagingDirectMove";
    private static final String IS_ZERO_PRIORITY = "isZeroPriority";
    private static final String IS_ADJ_ADV = "isAdjAdv";
    private static final String IS_ADJ_MULT = "isAdjMult";
    private static final String IS_ADJ_UNIQ = "isAdjUniq";
    private static final String IS_ALLIE = "isAllie";
    private static final String IS_ALLIES = "isAllies";
    private static final String IS_ANY_FOE = "isAnyFoe";
    private static final String IS_AUTRE_UNIQ = "isAutreUniq";
    private static final String IS_GLOBALE = "isGlobale";
    private static final String IS_LANCEUR = "isLanceur";
    private static final String IS_PSEUDO_GLOBALE = "isPseudoGlobale";
    private static final String IS_TOUS_ADV = "isTousAdv";
    private static final String IS_UNIQUE_IMPORTE = "isUniqueImporte";
    private static final String IS_CONST_ACCURACY = "isConstAccuracy";
    private static final String IS_ZERO_PREPA_ROUND = "isZeroPrepaRound";
    private static final String CLICK_DELETED_STATUS = "clickDeletedStatus";
    private static final String GET_DELETED_STATUS = "getDeletedStatus";
    private static final String CLICK_REQUIRED_STATUS = "clickRequiredStatus";
    private static final String GET_REQUIRED_STATUS = "getRequiredStatus";
    private static final String GET_TR_ACHIEVE_DISAPPEARED_PK_USING_MOVE = "getTrAchieveDisappearedPkUsingMove";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_TR_ABILITY = "getTrAbility";
    private static final String CLICK_ITEM = "clickItem";
    private static final String GET_TR_ITEM = "getTrItem";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_TR_MOVE = "getTrMove";
    private static final String IS_BEFORE_PRIMARY_EFFECT = "isBeforePrimaryEffect";
    private static final String IS_END_ROUND_EFFECT = "isEndRoundEffect";
    private static final String IS_PRIMARY_EFFECT = "isPrimaryEffect";
    private static final String IS_AFTER_PRIMARY_EFFECT = "isAfterPrimaryEffect";
    private static final String GET_PAGE = "getPage";
    private static final String CLICK_ITEM_SEC_EFFECT = "clickItemSecEffect";
    private static final String TRANSLATE_ITEM_SEC_EFFECT = "translateItemSecEffect";
    private static final String SWITCH_AFTER_USING_MOVE = "switchAfterUsingMove";
    private static final String IS_REPEATED_ROUND = "isRepeatedRound";
    private static final String CAN_BE_LEARNT = "canBeLearnt";
    private static final String CLICK_POKEMON = "clickPokemon";
    private static final String GET_TR_POKEMON = "getTrPokemon";
    private static final String CLICK_POKEMON_TM = "clickPokemonTm";
    private static final String GET_TR_POKEMON_TM = "getTrPokemonTm";
    private static final String CLICK_POKEMON_HM = "clickPokemonHm";
    private static final String GET_TR_POKEMON_HM = "getTrPokemonHm";
    private static final String CLICK_POKEMON_MT = "clickPokemonMt";
    private static final String GET_TR_POKEMON_MT = "getTrPokemonMt";
    private static final String SEARCH = "search";
    private static final String DISPLAY_NAME = "displayName";
    private static final String BOOSTED_TYPES = "boostedTypes";
    private static final String HAS_DEFAULT_TYPES = "hasDefaultTypes";
    private static final String TYPES = "types";
    private static final String TYPES_BY_OWNED_ITEMS = "typesByOwnedItems";
    private static final String TYPES_BY_WEATHERS = "typesByWeathers";
    private static final String CATEGORY = "category";
    private static final String PP = "pp";
    private static final String PRIORITY = "priority";
    private static final String ACCURACY = "accuracy";
    private static final String MAP_VARS_ACCURACY = "mapVarsAccuracy";
    private static final String IGN_VAR_ACCUR_USER_NEG = "ignVarAccurUserNeg";
    private static final String IGN_VAR_EVAS_TARGET_POS = "ignVarEvasTargetPos";
    private static final String NB_PREPA_ROUND = "nbPrepaRound";
    private static final String DISAPPEAR_BEFORE_USE = "disappearBeforeUse";
    private static final String DELETED_STATUS = "deletedStatus";
    private static final String REQUIRED_STATUS = "requiredStatus";
    private static final String ACHIEVE_DISAPPEARED_PK_USING_MOVE = "achieveDisappearedPkUsingMove";
    private static final String ABILITIES = "abilities";
    private static final String ITEMS = "items";
    private static final String CANNOT_KO = "cannotKo";
    private static final String AFFECTED_BY_MOVES = "affectedByMoves";
    private static final String SEC_EFFECTS_BY_ITEM = "secEffectsByItem";
    private static final String EFFECTS = "effects";
    private static final String SEC_EFFECT_IF_NO_DAMAGE = "secEffectIfNoDamage";
    private static final String NAME = "name";
    private static final String RECHARGE_ROUND = "rechargeRound";
    private static final String CONST_USER_CHOICE = "constUserChoice";
    private static final String RANK_INCREMENT_NB_ROUND = "rankIncrementNbRound";
    private static final String REPEAT_ROUND_LAW = "repeatRoundLaw";
    private static final String MOVES_LEVEL_LEARNT_BY_POKEMON = "movesLevelLearntByPokemon";
    private static final String MOVES_TM_LEARNT_BY_POKEMON = "movesTmLearntByPokemon";
    private static final String MOVES_HM_LEARNT_BY_POKEMON = "movesHmLearntByPokemon";
    private static final String MOVES_MT_LEARNT_BY_POKEMON = "movesMtLearntByPokemon";
    private static final String INDEX = "index";
    private static final String MOVE_LINE = "moveLine";
    private static final String POWER = "power";
    private static final String TYPED_NAME = "typedName";
    private static final String CATEGORIES = "categories";
    private static final String TYPED_TYPE = "typedType";
    private static final String WHOLE_WORD = "wholeWord";
    private static final String MIN_ACCURACY = "minAccuracy";
    private static final String MAX_ACCURACY = "maxAccuracy";
    private static final String MIN_POWER = "minPower";
    private static final String MAX_POWER = "maxPower";
    private static final String MOVES = "moves";
    private static final String MOVES_BEAN = "movesBean";
    private static final String SORTED_MOVES = "sortedMoves";

    public static void build(PokemonStandards _std) {
        buildMoveBean(_std);
        buildMoveLineBean(_std);
        buildMovesBean(_std);
    }
    private static void buildMoveBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_MOVE_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(BOOSTED_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(HAS_DEFAULT_TYPES,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(TYPES_BY_OWNED_ITEMS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(TYPES_BY_WEATHERS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(CATEGORY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(PP,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(PRIORITY,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(ACCURACY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MAP_VARS_ACCURACY, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(IGN_VAR_ACCUR_USER_NEG,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(IGN_VAR_EVAS_TARGET_POS,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(NB_PREPA_ROUND,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(DISAPPEAR_BEFORE_USE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(DELETED_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(REQUIRED_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ACHIEVE_DISAPPEARED_PK_USING_MOVE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(CANNOT_KO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(AFFECTED_BY_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(SEC_EFFECTS_BY_ITEM, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(EFFECTS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(SEC_EFFECT_IF_NO_DAMAGE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(RECHARGE_ROUND,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(CONST_USER_CHOICE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(RANK_INCREMENT_NB_ROUND,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(REPEAT_ROUND_LAW, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MOVES_LEVEL_LEARNT_BY_POKEMON, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MOVES_TM_LEARNT_BY_POKEMON, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_HM_LEARNT_BY_POKEMON, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_MT_LEARNT_BY_POKEMON, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(TYPES_DEPEND_ONLY_ON_ITEM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_ITEM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_TYPES_BY_OWNED_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_TYPES_BY_OWNED_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(TYPES_DEPEND_ONLY_ON_WEATHER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_WEATHER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_TYPES_BY_WEATHERS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_TYPES_BY_WEATHERS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(TYPES_DEPEND_ON_WEATHER_AND_ITEM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_DAMAGING_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_DAMAGING_DIRECT_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ZERO_PRIORITY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ADJ_ADV,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ADJ_MULT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ADJ_UNIQ,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ALLIE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ALLIES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ANY_FOE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_AUTRE_UNIQ,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_GLOBALE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_LANCEUR,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_PSEUDO_GLOBALE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_TOUS_ADV,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_UNIQUE_IMPORTE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_CONST_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ZERO_PREPA_ROUND,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_DELETED_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_DELETED_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_REQUIRED_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_REQUIRED_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ACHIEVE_DISAPPEARED_PK_USING_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_BEFORE_PRIMARY_EFFECT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_END_ROUND_EFFECT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_PRIMARY_EFFECT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_AFTER_PRIMARY_EFFECT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_PAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEM_SEC_EFFECT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(TRANSLATE_ITEM_SEC_EFFECT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(SWITCH_AFTER_USING_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_REPEATED_ROUND,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(CAN_BE_LEARNT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_POKEMON_TM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_POKEMON_TM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_POKEMON_HM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_POKEMON_HM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_POKEMON_MT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_POKEMON_MT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_MOVE_BEAN, type_);
    }
    private static void buildMoveLineBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_MOVE_LINE_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(INDEX,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(PP,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(CATEGORY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MOVE_LINE,AikiBeansFacadeDtoStd.TYPE_MOVE_LINE,false,false,type_));
        fields_.add(new StandardField(PRIORITY,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(ACCURACY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(POWER,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(SORTED_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_MOVE_LINE_BEAN, type_);
    }
    private static void buildMovesBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_MOVES_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(TYPED_NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(CATEGORIES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(CATEGORY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(TYPED_TYPE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(WHOLE_WORD,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(MIN_ACCURACY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MAX_ACCURACY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MIN_POWER,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MAX_POWER,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_BEAN,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(SORTED_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(SEARCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_MOVES_BEAN, type_);
    }
    public static ResultErrorStd getResultMoveBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        MoveBean instance_ = (MoveBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(instance_.getDisplayName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BOOSTED_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getBoostedTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HAS_DEFAULT_TYPES)) {
            res_.setResult(BooleanStruct.of(instance_.getHasDefaultTypes()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES_BY_OWNED_ITEMS)) {
            res_.setResult(new DefaultStruct(instance_.getTypesByOwnedItems(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES_BY_WEATHERS)) {
            res_.setResult(new DefaultStruct(instance_.getTypesByWeathers(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CATEGORY)) {
            res_.setResult(new StringStruct(instance_.getCategory()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PP)) {
            res_.setResult(new IntStruct(instance_.getPp()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PRIORITY)) {
            res_.setResult(new IntStruct(instance_.getPriority()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ACCURACY)) {
            res_.setResult(new StringStruct(instance_.getAccuracy()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAP_VARS_ACCURACY)) {
            res_.setResult(new DefaultStruct(instance_.getMapVarsAccuracy(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IGN_VAR_ACCUR_USER_NEG)) {
            res_.setResult(BooleanStruct.of(instance_.getIgnVarAccurUserNeg()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IGN_VAR_EVAS_TARGET_POS)) {
            res_.setResult(BooleanStruct.of(instance_.getIgnVarEvasTargetPos()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_PREPA_ROUND)) {
            res_.setResult(new IntStruct(instance_.getNbPrepaRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DISAPPEAR_BEFORE_USE)) {
            res_.setResult(BooleanStruct.of(instance_.getDisappearBeforeUse()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DELETED_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getDeletedStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REQUIRED_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getRequiredStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ACHIEVE_DISAPPEARED_PK_USING_MOVE)) {
            res_.setResult(new DefaultStruct(instance_.getAchieveDisappearedPkUsingMove(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS)) {
            res_.setResult(new DefaultStruct(instance_.getItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CANNOT_KO)) {
            res_.setResult(BooleanStruct.of(instance_.getCannotKo()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,AFFECTED_BY_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getAffectedByMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SEC_EFFECTS_BY_ITEM)) {
            res_.setResult(new DefaultStruct(instance_.getSecEffectsByItem(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EFFECTS)) {
            res_.setResult(DefaultStruct.newListInt(instance_.getEffects(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SEC_EFFECT_IF_NO_DAMAGE)) {
            res_.setResult(BooleanStruct.of(instance_.getSecEffectIfNoDamage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RECHARGE_ROUND)) {
            res_.setResult(BooleanStruct.of(instance_.getRechargeRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CONST_USER_CHOICE)) {
            res_.setResult(BooleanStruct.of(instance_.getConstUserChoice()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RANK_INCREMENT_NB_ROUND)) {
            res_.setResult(new IntStruct(instance_.getRankIncrementNbRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REPEAT_ROUND_LAW)) {
            res_.setResult(new DefaultStruct(instance_.getRepeatRoundLaw(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_LEVEL_LEARNT_BY_POKEMON)) {
            res_.setResult(new DefaultStruct(instance_.getMovesLevelLearntByPokemon(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_TM_LEARNT_BY_POKEMON)) {
            res_.setResult(new DefaultStruct(instance_.getMovesTmLearntByPokemon(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_HM_LEARNT_BY_POKEMON)) {
            res_.setResult(new DefaultStruct(instance_.getMovesHmLearntByPokemon(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_MT_LEARNT_BY_POKEMON)) {
            res_.setResult(new DefaultStruct(instance_.getMovesMtLearntByPokemon(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultMoveLineBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        MoveLineBean instance_ = (MoveLineBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(instance_.getIndex()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(instance_.getDisplayName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PP)) {
            res_.setResult(new IntStruct(instance_.getPp()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CATEGORY)) {
            res_.setResult(new StringStruct(instance_.getCategory()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVE_LINE)) {
            res_.setResult(DefaultStruct.newInstance(instance_.getMoveLine(),AikiBeansFacadeDtoStd.TYPE_MOVE_LINE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PRIORITY)) {
            res_.setResult(new IntStruct(instance_.getPriority()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ACCURACY)) {
            res_.setResult(new StringStruct(instance_.getAccuracy()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,POWER)) {
            res_.setResult(new StringStruct(instance_.getPower()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultMovesBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        MovesBean instance_ = (MovesBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,TYPED_NAME)) {
            res_.setResult(new StringStruct(instance_.getTypedName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CATEGORIES)) {
            res_.setResult(new DefaultStruct(instance_.getCategories(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CATEGORY)) {
            res_.setResult(new StringStruct(instance_.getCategory()));
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
        if (StringUtil.quickEq(fieldName_,MIN_ACCURACY)) {
            res_.setResult(new StringStruct(instance_.getMinAccuracy()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAX_ACCURACY)) {
            res_.setResult(new StringStruct(instance_.getMaxAccuracy()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MIN_POWER)) {
            res_.setResult(new StringStruct(instance_.getMinPower()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAX_POWER)) {
            res_.setResult(new StringStruct(instance_.getMaxPower()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_BEAN)) {
            res_.setResult(new StringStruct(MovesBean.MOVES_BEAN));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SORTED_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getSortedMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultMoveLineBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val) {
        ResultErrorStd res_ = new ResultErrorStd();
        MoveLineBean instance_ = (MoveLineBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            instance_.setIndex(NumParsers.convertToNumber(_val).intStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVE_LINE)) {
            instance_.setMoveLine((MoveLine) ((RealInstanceStruct)_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SORTED_MOVES)) {
            instance_.setSortedMoves((StringList) ((RealInstanceStruct)_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultMovesBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val) {
        ResultErrorStd res_ = new ResultErrorStd();
        MovesBean instance_ = (MovesBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,TYPED_NAME)) {
            instance_.setTypedName(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CATEGORY)) {
            instance_.setCategory(NumParsers.getString(_val).getInstance());
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
        if (StringUtil.quickEq(fieldName_,MIN_ACCURACY)) {
            instance_.setMinAccuracy(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAX_ACCURACY)) {
            instance_.setMaxAccuracy(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MIN_POWER)) {
            instance_.setMinPower(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAX_POWER)) {
            instance_.setMaxPower(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMoveBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        MoveBean instance_ = (MoveBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickMoves()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,TYPES_DEPEND_ONLY_ON_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.typesDependOnlyOnItem()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.isItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_TYPES_BY_OWNED_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickTypesByOwnedItems(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_TYPES_BY_OWNED_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrTypesByOwnedItems(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,TYPES_DEPEND_ONLY_ON_WEATHER)) {
            res_.setResult(BooleanStruct.of(instance_.typesDependOnlyOnWeather()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_WEATHER)) {
            res_.setResult(BooleanStruct.of(instance_.isWeather(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_TYPES_BY_WEATHERS)) {
            res_.setResult(new StringStruct(instance_.clickTypesByWeathers(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_TYPES_BY_WEATHERS)) {
            res_.setResult(new StringStruct(instance_.getTrTypesByWeathers(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,TYPES_DEPEND_ON_WEATHER_AND_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.typesDependOnWeatherAndItem()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_DAMAGING_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.isDamagingMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_DAMAGING_DIRECT_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.isDamagingDirectMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ZERO_PRIORITY)) {
            res_.setResult(BooleanStruct.of(instance_.isZeroPriority()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ADJ_ADV)) {
            res_.setResult(BooleanStruct.of(instance_.isAdjAdv()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ADJ_MULT)) {
            res_.setResult(BooleanStruct.of(instance_.isAdjMult()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ADJ_UNIQ)) {
            res_.setResult(BooleanStruct.of(instance_.isAdjUniq()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ALLIE)) {
            res_.setResult(BooleanStruct.of(instance_.isAllie()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ALLIES)) {
            res_.setResult(BooleanStruct.of(instance_.isAllies()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ANY_FOE)) {
            res_.setResult(BooleanStruct.of(instance_.isAnyFoe()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_AUTRE_UNIQ)) {
            res_.setResult(BooleanStruct.of(instance_.isAutreUniq()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_GLOBALE)) {
            res_.setResult(BooleanStruct.of(instance_.isGlobale()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_LANCEUR)) {
            res_.setResult(BooleanStruct.of(instance_.isLanceur()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_PSEUDO_GLOBALE)) {
            res_.setResult(BooleanStruct.of(instance_.isPseudoGlobale()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_TOUS_ADV)) {
            res_.setResult(BooleanStruct.of(instance_.isTousAdv()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_UNIQUE_IMPORTE)) {
            res_.setResult(BooleanStruct.of(instance_.isUniqueImporte()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_CONST_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.isConstAccuracy()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ZERO_PREPA_ROUND)) {
            res_.setResult(BooleanStruct.of(instance_.isZeroPrepaRound()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_DELETED_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickDeletedStatus(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_DELETED_STATUS)) {
            res_.setResult(new StringStruct(instance_.getDeletedStatus(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_REQUIRED_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickRequiredStatus(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_REQUIRED_STATUS)) {
            res_.setResult(new StringStruct(instance_.getRequiredStatus(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ACHIEVE_DISAPPEARED_PK_USING_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrAchieveDisappearedPkUsingMove(NumParsers.convertToNumber(_args[0]).intStruct())));
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
        if (StringUtil.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEM)) {
            res_.setResult(new StringStruct(instance_.getTrItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrMove(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BEFORE_PRIMARY_EFFECT)) {
            res_.setResult(BooleanStruct.of(instance_.isBeforePrimaryEffect(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_END_ROUND_EFFECT)) {
            res_.setResult(BooleanStruct.of(instance_.isEndRoundEffect(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_PRIMARY_EFFECT)) {
            res_.setResult(BooleanStruct.of(instance_.isPrimaryEffect(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_AFTER_PRIMARY_EFFECT)) {
            res_.setResult(BooleanStruct.of(instance_.isAfterPrimaryEffect(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_PAGE)) {
            res_.setResult(new StringStruct(instance_.getPage(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM_SEC_EFFECT)) {
            res_.setResult(new StringStruct(instance_.clickItemSecEffect(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,TRANSLATE_ITEM_SEC_EFFECT)) {
            res_.setResult(new StringStruct(instance_.translateItemSecEffect(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,SWITCH_AFTER_USING_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.switchAfterUsingMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_REPEATED_ROUND)) {
            res_.setResult(BooleanStruct.of(instance_.isRepeatedRound()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CAN_BE_LEARNT)) {
            res_.setResult(BooleanStruct.of(instance_.canBeLearnt()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_POKEMON)) {
            res_.setResult(new StringStruct(instance_.clickPokemon(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_POKEMON)) {
            res_.setResult(new StringStruct(instance_.getTrPokemon(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_POKEMON_TM)) {
            res_.setResult(new StringStruct(instance_.clickPokemonTm(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_POKEMON_TM)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonTm(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_POKEMON_HM)) {
            res_.setResult(new StringStruct(instance_.clickPokemonHm(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_POKEMON_HM)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonHm(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_POKEMON_MT)) {
            res_.setResult(new StringStruct(instance_.clickPokemonMt(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_POKEMON_MT)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonMt(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMoveLineBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        MoveLineBean instance_ = (MoveLineBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMovesBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        MovesBean instance_ = (MovesBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,SEARCH)) {
            res_.setResult(new StringStruct(instance_.search()));
            return res_;
        }
        return res_;
    }
}
