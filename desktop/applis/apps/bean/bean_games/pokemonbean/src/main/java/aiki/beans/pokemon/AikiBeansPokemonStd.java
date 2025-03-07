package aiki.beans.pokemon;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansPokemonStd{
    public static final String BEAN_POKEDEX="pokedex";
    public static final String BEAN_PK="pokemon";
    public static final String BEAN_EVO_HAPPY="evo_happy";
    public static final String BEAN_EVO_ITEM="evo_item";
    public static final String BEAN_EVO_LEVEL="evo_level";
    public static final String BEAN_EVO_LEVELGENDER="evo_levelgender";
    public static final String BEAN_EVO_MOVE="evo_move";
    public static final String BEAN_EVO_STONE="evo_stone";
    public static final String BEAN_EVO_STONEGENDER="evo_stonegender";
    public static final String BEAN_EVO_TEAM="evo_team";
    public static final String BEAN_EVO_TYPE="evo_type";
    public static final String TYPE_POKEDEX_BEAN = "aiki.beans.pokemon.PokedexBean";
    public static final String TYPE_POKEMON_BEAN = "aiki.beans.pokemon.PokemonBean";
//    private static final String CLICK_POKEDEX = "clickPokedex";
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
    private static final String IS_APPEARING_ZERO = "isAppearingZero";
    private static final String CLICK_LEVEL = "clickLevel";
    private static final String CLICK_LEVEL_ZERO = "clickLevelZero";
    private static final String GET_MAP_WIDTH = "getMapWidth";
//    private static final String IS_FIRST_ROW = "isFirstRow";
    private static final String GET_PLACE_NAME = "getPlaceName";
    private static final String GET_MINI_MAP_IMAGE = "getMiniMapImage";
//    private static final String SEARCH = "search";
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
    private AikiBeansPokemonStd(){}
    public static void build(PokemonStandards _std) {
        buildPokedexBean(_std);
        buildPokemonBean(_std);
    }
    private static void buildPokedexBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(TYPED_NAME,BeanNatCommonLgNames.STRING, new PokedexBeanTypedNameGet(),new PokedexBeanTypedNameSet()));
        fields_.add(new StandardField(TYPED_TYPE,BeanNatCommonLgNames.STRING, new PokedexBeanTypedTypeGet(),new PokedexBeanTypedTypeSet()));
        fields_.add(new StandardField(WHOLE_WORD,BeanNatCommonLgNames.PRIM_BOOLEAN, new PokedexBeanWholeWordGet(),new PokedexBeanWholeWordSet()));
        fields_.add(new StandardField(TYPED_MIN_NB_POSS_EVOS,BeanNatCommonLgNames.STRING, new PokedexBeanTypedMinNbPossEvosGet(),new PokedexBeanTypedMinNbPossEvosSet()));
        fields_.add(new StandardField(TYPED_MAX_NB_POSS_EVOS,BeanNatCommonLgNames.STRING, new PokedexBeanTypedMaxNbPossEvosGet(),new PokedexBeanTypedMaxNbPossEvosSet()));
        fields_.add(new StandardField(BOOLEANS, BeanNatCommonLgNames.TYPE_MAP, new PokedexBeanBooleansGet(),null));
        fields_.add(new StandardField(IS_EVO,BeanNatCommonLgNames.STRING, new PokedexBeanIsEvoGet(),new PokedexBeanIsEvoSet()));
        fields_.add(new StandardField(IS_LEG,BeanNatCommonLgNames.STRING, new PokedexBeanIsLegGet(),new PokedexBeanIsLegSet()));
        fields_.add(new StandardField(POKEDEX, BeanNatCommonLgNames.TYPE_LIST, new PokedexBeanPokedexGet(),null));
//        methods_.add( new SpecNatMethod(SEARCH,BeanNatCommonLgNames.STRING, new PokedexBeanSearch()));
        methods_.add( new SpecNatMethod(GET_MINI_IMAGE,BeanNatCommonLgNames.STRING, new PokedexBeanGetMiniImage()));
        methods_.add( new SpecNatMethod(CLICK_LINK,BeanNatCommonLgNames.STRING, new PokedexBeanClickLink()));
        _std.getStds().addEntry(TYPE_POKEDEX_BEAN, type_);
    }
    private static void buildPokemonBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING, new PokemonBeanDisplayNameGet(),null));
        fields_.add(new StandardField(BACK_IMAGE,BeanNatCommonLgNames.STRING, new PokemonBeanBackImageGet(),null));
        fields_.add(new StandardField(FRONT_IMAGE,BeanNatCommonLgNames.STRING, new PokemonBeanFrontImageGet(),null));
        fields_.add(new StandardField(WEIGHT,BeanNatCommonLgNames.TYPE_RATE, new PokemonBeanWeightGet(),null));
        fields_.add(new StandardField(HEIGHT,BeanNatCommonLgNames.TYPE_RATE, new PokemonBeanHeightGet(),null));
        fields_.add(new StandardField(POSSIBLE_GENDERS, BeanNatCommonLgNames.TYPE_LIST, new PokemonBeanPossibleGendersGet(),null));
        fields_.add(new StandardField(TYPES, BeanNatCommonLgNames.TYPE_LIST, new PokemonBeanTypesGet(),null));
        fields_.add(new StandardField(ABILITIES, BeanNatCommonLgNames.TYPE_LIST, new PokemonBeanAbilitiesGet(),null));
        fields_.add(new StandardField(CATCHING_RATE, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonBeanCatchingRateGet(),null));
        fields_.add(new StandardField(EVOLUTIONS, BeanNatCommonLgNames.TYPE_LIST, new PokemonBeanEvolutionsGet(),null));
        fields_.add(new StandardField(NAME,BeanNatCommonLgNames.STRING, new PokemonBeanNameGet(),null));
        fields_.add(new StandardField(EVO_BASE,BeanNatCommonLgNames.STRING, new PokemonBeanEvoBaseGet(),null));
        fields_.add(new StandardField(EXP_EVO,BeanNatCommonLgNames.STRING, new PokemonBeanExpEvoGet(),null));
        fields_.add(new StandardField(MAP_VARS, BeanNatCommonLgNames.TYPE_MAP, new PokemonBeanMapVarsGet(),null));
        fields_.add(new StandardField(EXP_RATE,BeanNatCommonLgNames.PRIM_LONG, new PokemonBeanExpRateGet(),null));
        fields_.add(new StandardField(STATISTICS, BeanNatCommonLgNames.TYPE_LIST, new PokemonBeanStatisticsGet(),null));
        fields_.add(new StandardField(LEV_MOVES, BeanNatCommonLgNames.TYPE_LIST, new PokemonBeanLevMovesGet(),null));
        fields_.add(new StandardField(TECHNICAL_MOVES, BeanNatCommonLgNames.TYPE_MAP, new PokemonBeanTechnicalMovesGet(),null));
        fields_.add(new StandardField(HIDDEN_MOVES, BeanNatCommonLgNames.TYPE_MAP, new PokemonBeanHiddenMovesGet(),null));
        fields_.add(new StandardField(MOVE_TUTORS, BeanNatCommonLgNames.TYPE_LIST, new PokemonBeanMoveTutorsGet(),null));
        fields_.add(new StandardField(EGG_GROUPS_PK, BeanNatCommonLgNames.TYPE_LIST, new PokemonBeanEggGroupsPkGet(),null));
        fields_.add(new StandardField(HATCHING_STEPS,BeanNatCommonLgNames.TYPE_LG_INT, new PokemonBeanHatchingStepsGet(),null));
        fields_.add(new StandardField(PLACES, BeanNatCommonLgNames.TYPE_LIST, new PokemonBeanPlacesGet(),null));
        fields_.add(new StandardField(IMAGES, BeanNatCommonLgNames.TYPE_MAP, new PokemonBeanImagesGet(),null));
//        methods_.add( new SpecNatMethod(CLICK_POKEDEX,BeanNatCommonLgNames.STRING, new PokemonBeanClickPokedex()));
        methods_.add( new SpecNatMethod(ROUND_WEIGHT,BeanNatCommonLgNames.STRING, new PokemonBeanRoundWeight()));
        methods_.add( new SpecNatMethod(ROUND_HEIGHT,BeanNatCommonLgNames.STRING, new PokemonBeanRoundHeight()));
        methods_.add( new SpecNatMethod(CLICK_ABILITY,BeanNatCommonLgNames.STRING, new PokemonBeanClickAbility()));
        methods_.add( new SpecNatMethod(GET_TR_ABILITY,BeanNatCommonLgNames.STRING, new PokemonBeanGetTrAbility()));
        methods_.add( new SpecNatMethod(GET_PAGE,BeanNatCommonLgNames.STRING, new PokemonBeanGetPage()));
        methods_.add( new SpecNatMethod(CLICK_BASE,BeanNatCommonLgNames.STRING, new PokemonBeanClickBase()));
        methods_.add( new SpecNatMethod(GET_BASE, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonBeanGetBase()));
        methods_.add( new SpecNatMethod(GET_EV, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonBeanGetEv()));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, new PokemonBeanClickMove()));
        methods_.add( new SpecNatMethod(CLICK_TECHNICAL_MOVE,BeanNatCommonLgNames.STRING, new PokemonBeanClickTechnicalMove()));
        methods_.add( new SpecNatMethod(CLICK_HIDDEN_MOVE,BeanNatCommonLgNames.STRING, new PokemonBeanClickHiddenMove()));
        methods_.add( new SpecNatMethod(CLICK_MOVE_TUTORS,BeanNatCommonLgNames.STRING, new PokemonBeanClickMoveTutors()));
        methods_.add( new SpecNatMethod(GET_MOVE_TUTOR,BeanNatCommonLgNames.STRING, new PokemonBeanGetMoveTutor()));
        methods_.add( new SpecNatMethod(CLICK_EGG_PK,BeanNatCommonLgNames.STRING, new PokemonBeanClickEggPk()));
        methods_.add( new SpecNatMethod(GET_EGG_PK,BeanNatCommonLgNames.STRING, new PokemonBeanGetEggPk()));
        methods_.add( new SpecNatMethod(IS_APPEARING_ANY_WHERE,BeanNatCommonLgNames.PRIM_BOOLEAN, new PokemonBeanIsAppearingAnyWhere()));
        methods_.add( new SpecNatMethod(IS_APPEARING_PLACE,BeanNatCommonLgNames.PRIM_BOOLEAN, new PokemonBeanIsAppearingPlace()));
        methods_.add( new SpecNatMethod(IS_MULTI_LAYER,BeanNatCommonLgNames.PRIM_BOOLEAN, new PokemonBeanIsMultiLayer()));
        methods_.add( new SpecNatMethod(LAYERS, BeanNatCommonLgNames.TYPE_LIST, new PokemonBeanLayers()));
        methods_.add( new SpecNatMethod(IS_APPEARING,BeanNatCommonLgNames.PRIM_BOOLEAN, new PokemonBeanIsAppearing()));
        methods_.add( new SpecNatMethod(IS_APPEARING_ZERO,BeanNatCommonLgNames.PRIM_BOOLEAN, new PokemonBeanIsAppearingZero()));
        methods_.add( new SpecNatMethod(CLICK_LEVEL,BeanNatCommonLgNames.STRING, new PokemonBeanClickLevel()));
        methods_.add( new SpecNatMethod(CLICK_LEVEL_ZERO,BeanNatCommonLgNames.STRING, new PokemonBeanClickLevelZero()));
        methods_.add( new SpecNatMethod(GET_MAP_WIDTH, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonBeanGetMapWidth()));
//        methods_.add( new SpecNatMethod(IS_FIRST_ROW,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new PokemonBeanIsFirstRow()));
        methods_.add( new SpecNatMethod(GET_PLACE_NAME,BeanNatCommonLgNames.STRING, new PokemonBeanGetPlaceName()));
        methods_.add( new SpecNatMethod(GET_MINI_MAP_IMAGE,BeanNatCommonLgNames.STRING, new PokemonBeanGetMiniMapImage()));
        _std.getStds().addEntry(TYPE_POKEMON_BEAN, type_);
    }
}
