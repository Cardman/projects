package aiki.beans.map.elements;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansMapElementsStd{
    public static final String GM = "gm";
    public static final String WEB_HTML_MAP_MAP_HTML = "web/html/map/map.html";
    public static final String ELTS = "Elts";
    public static final String TYPE_AREA_BEAN = "aiki.beans.map.elements.AreaBean";
    public static final String TYPE_LEGENDARY_POKEMON_BEAN = "aiki.beans.map.elements.LegendaryPokemonBean";
    public static final String WEB_HTML_MAP_ELEMENTS_AREA_HTML="web/html/map/elements/area.html";
    public static final String WEB_HTML_MAP_ELEMENTS_DEALER_HTML="web/html/map/elements/dealer.html";
    public static final String WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML="web/html/map/elements/dual_fight.html";
    public static final String WEB_HTML_MAP_ELEMENTS_LEG_PK_HTML="web/html/map/elements/leg_pk.html";
    public static final String WEB_HTML_MAP_ELEMENTS_SELLER_HTML="web/html/map/elements/seller.html";
    public static final String WEB_HTML_MAP_ELEMENTS_TRAINER_MULTI_FIGHT_HTML="web/html/map/elements/trainer_multi_fight.html";
    public static final String WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML="web/html/map/elements/trainer_one_fight.html";

    private static final String GET_IMAGE = "getImage";
    private static final String CLICK_NAME = "clickName";
    private static final String GET_NAME = "getName";
    private static final String GET_GENDER = "getGender";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_ABILITY = "getAbility";
    private static final String CLICK_ITEM = "clickItem";
    private static final String GET_ITEM = "getItem";
    private static final String GET_MOVES_AT_LEVEL = "getMovesAtLevel";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_MOVE = "getMove";
    private static final String GET_IMAGE_FISHING = "getImageFishing";
    private static final String CLICK_NAME_FISHING = "clickNameFishing";
    private static final String GET_NAME_FISHING = "getNameFishing";
    private static final String CLICK_ABILITY_FISHING = "clickAbilityFishing";
    private static final String GET_ABILITY_FISHING = "getAbilityFishing";
    private static final String CLICK_ITEM_FISHING = "clickItemFishing";
    private static final String GET_ITEM_FISHING = "getItemFishing";
    private static final String GET_MOVES_AT_LEVEL_FISHING = "getMovesAtLevelFishing";
    private static final String CLICK_MOVE_FISHING = "clickMoveFishing";
    private static final String GET_MOVE_FISHING = "getMoveFishing";
    private static final String GET_LEVEL = "getLevel";
    private static final String AREA = "area";
    private static final String POKEMON = "pokemon";
    private static final String GL = "gl";

    private AikiBeansMapElementsStd(){}
    public static void build(PokemonStandards _std) {
        buildEltsBean(_std);
        buildAreaBean(_std);
        buildLegendaryPokemonBean(_std);
    }
    private static void buildEltsBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(ELTS, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        methods_.add( new SpecNatMethod(GM, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller(WEB_HTML_MAP_MAP_HTML)));
        methods_.add( new SpecNatMethod(GL, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/map/level.html")));
        _std.getStds().addEntry(ELTS, type_);
    }
    private static void buildAreaBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_AREA_BEAN, fields_, methods_, ELTS);
        fields_.add(new StandardField(AREA,PokemonStandards.TYPE_AREA_APPARITION,false,false,new AreaBeanAreaGet(),null));
        methods_.add( new SpecNatMethod(GET_IMAGE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetImage()));
        methods_.add( new SpecNatMethod(CLICK_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanClickName()));
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetName()));
        methods_.add( new SpecNatMethod(GET_GENDER,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetGender()));
        methods_.add( new SpecNatMethod(CLICK_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanClickAbility()));
        methods_.add( new SpecNatMethod(GET_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetAbility()));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanClickItem()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetItem()));
        methods_.add( new SpecNatMethod(GET_MOVES_AT_LEVEL, BeanNatCommonLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new AreaBeanGetMovesAtLevel()));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanClickMove()));
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetMove()));
        methods_.add( new SpecNatMethod(GET_IMAGE_FISHING,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetImageFishing()));
        methods_.add( new SpecNatMethod(CLICK_NAME_FISHING,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanClickNameFishing()));
        methods_.add( new SpecNatMethod(GET_NAME_FISHING,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetNameFishing()));
        methods_.add( new SpecNatMethod(CLICK_ABILITY_FISHING,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanClickAbilityFishing()));
        methods_.add( new SpecNatMethod(GET_ABILITY_FISHING,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetAbilityFishing()));
        methods_.add( new SpecNatMethod(CLICK_ITEM_FISHING,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanClickItemFishing()));
        methods_.add( new SpecNatMethod(GET_ITEM_FISHING,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetItemFishing()));
        methods_.add( new SpecNatMethod(GET_MOVES_AT_LEVEL_FISHING, BeanNatCommonLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new AreaBeanGetMovesAtLevelFishing()));
        methods_.add( new SpecNatMethod(CLICK_MOVE_FISHING,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanClickMoveFishing()));
        methods_.add( new SpecNatMethod(GET_MOVE_FISHING,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AreaBeanGetMoveFishing()));
        _std.getStds().addEntry(TYPE_AREA_BEAN, type_);
    }
    private static void buildLegendaryPokemonBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_LEGENDARY_POKEMON_BEAN, fields_, methods_, ELTS);
        fields_.add(new StandardField(POKEMON,PokemonStandards.TYPE_WILD_PK,false,false,new LegendaryPokemonBeanPokemonGet(),null));
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LegendaryPokemonBeanGetName()));
        methods_.add( new SpecNatMethod(GET_IMAGE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LegendaryPokemonBeanGetImage()));
        methods_.add( new SpecNatMethod(CLICK_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LegendaryPokemonBeanClickName()));
        methods_.add( new SpecNatMethod(GET_GENDER,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LegendaryPokemonBeanGetGender()));
        methods_.add( new SpecNatMethod(GET_LEVEL, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL,new LegendaryPokemonBeanGetLevel()));
        methods_.add( new SpecNatMethod(CLICK_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LegendaryPokemonBeanClickAbility()));
        methods_.add( new SpecNatMethod(GET_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LegendaryPokemonBeanGetAbility()));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LegendaryPokemonBeanClickItem()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LegendaryPokemonBeanGetItem()));
        methods_.add( new SpecNatMethod(GET_MOVES_AT_LEVEL, BeanNatCommonLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new LegendaryPokemonBeanGetMovesAtLevel()));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LegendaryPokemonBeanClickMove()));
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LegendaryPokemonBeanGetMove()));
        _std.getStds().addEntry(TYPE_LEGENDARY_POKEMON_BEAN, type_);
    }
}
