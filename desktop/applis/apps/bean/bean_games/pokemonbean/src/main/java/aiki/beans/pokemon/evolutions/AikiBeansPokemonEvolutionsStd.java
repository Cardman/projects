package aiki.beans.pokemon.evolutions;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansPokemonEvolutionsStd{
    public static final String TYPE_EVOLUTION_BEAN = "aiki.beans.pokemon.evolutions.EvolutionBean";
    public static final String TYPE_EVOLUTION_HAPPINESS_BEAN = "aiki.beans.pokemon.evolutions.EvolutionHappinessBean";
    public static final String TYPE_EVOLUTION_ITEM_BEAN = "aiki.beans.pokemon.evolutions.EvolutionItemBean";
    public static final String TYPE_EVOLUTION_LEVEL_BEAN = "aiki.beans.pokemon.evolutions.EvolutionLevelBean";
    public static final String TYPE_EVOLUTION_LEVEL_GENDER_BEAN = "aiki.beans.pokemon.evolutions.EvolutionLevelGenderBean";
    public static final String TYPE_EVOLUTION_MOVE_BEAN = "aiki.beans.pokemon.evolutions.EvolutionMoveBean";
    public static final String TYPE_EVOLUTION_MOVE_TYPE_BEAN = "aiki.beans.pokemon.evolutions.EvolutionMoveTypeBean";
    public static final String TYPE_EVOLUTION_STONE_BEAN = "aiki.beans.pokemon.evolutions.EvolutionStoneBean";
    public static final String TYPE_EVOLUTION_STONE_GENDER_BEAN = "aiki.beans.pokemon.evolutions.EvolutionStoneGenderBean";
    public static final String TYPE_EVOLUTION_TEAM_BEAN = "aiki.beans.pokemon.evolutions.EvolutionTeamBean";
    private static final String CLICK_EVO = "clickEvo";
    private static final String CLICK_ITEM = "clickItem";
    private static final String CLICK_MOVE = "clickMove";
    private static final String CLICK_STONE = "clickStone";
    private static final String CLICK_TEAM = "clickTeam";
    private static final String INDEX = "index";
    private static final String NAME = "name";
    private static final String BASE = "base";
    private static final String DISPLAY_NAME = "displayName";
    private static final String DISPLAY_BASE = "displayBase";
    private static final String MIN = "min";
    private static final String ITEM = "item";
    private static final String LEVEL = "level";
    private static final String GENDER = "gender";
    private static final String MOVE = "move";
    private static final String STONE = "stone";
    private static final String OTHER = "other";
    private static final String TYPE = "type";
    private AikiBeansPokemonEvolutionsStd(){}
    public static void build(PokemonStandards _std) {
        buildEvolutionBean(_std);
        buildEvolutionHappinessBean(_std);
        buildEvolutionItemBean(_std);
        buildEvolutionLevelBean(_std);
        buildEvolutionLevelGenderBean(_std);
        buildEvolutionMoveBean(_std);
        buildEvolutionMoveTypeBean(_std);
        buildEvolutionStoneBean(_std);
        buildEvolutionStoneGenderBean(_std);
        buildEvolutionTeamBean(_std);
    }
    private static void buildEvolutionBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EVOLUTION_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new EvolutionBeanIndexGet(),new EvolutionBeanIndexSet()));
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING,false,false,new EvolutionBeanDisplayNameGet(),null));
        fields_.add(new StandardField(DISPLAY_BASE,BeanNatCommonLgNames.STRING,false,false,new EvolutionBeanDisplayBaseGet(),null));
        fields_.add(new StandardField(NAME,BeanNatCommonLgNames.STRING,false,false,null,new EvolutionBeanNameSet()));
        fields_.add(new StandardField(BASE,BeanNatCommonLgNames.STRING,false,false,null,new EvolutionBeanBaseSet()));
        methods_.add( new SpecNatMethod(CLICK_EVO,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EvolutionBeanClickEvo()));
        _std.getStds().addEntry(TYPE_EVOLUTION_BEAN, type_);
    }
    private static void buildEvolutionHappinessBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EVOLUTION_HAPPINESS_BEAN, fields_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN);
        fields_.add(new StandardField(MIN,BeanNatCommonLgNames.PRIM_LONG,false,false,new EvolutionHappinessBeanMinGet(),null));
        _std.getStds().addEntry(TYPE_EVOLUTION_HAPPINESS_BEAN, type_);
    }
    private static void buildEvolutionItemBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EVOLUTION_ITEM_BEAN, fields_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN);
        fields_.add(new StandardField(ITEM,BeanNatCommonLgNames.STRING,false,false,new EvolutionItemBeanItemGet(),null));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EvolutionItemBeanClickItem()));
        _std.getStds().addEntry(TYPE_EVOLUTION_ITEM_BEAN, type_);
    }
    private static void buildEvolutionLevelBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EVOLUTION_LEVEL_BEAN, fields_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN);
        fields_.add(new StandardField(LEVEL, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new EvolutionLevelBeanLevelGet(),null));
        _std.getStds().addEntry(TYPE_EVOLUTION_LEVEL_BEAN, type_);
    }
    private static void buildEvolutionLevelGenderBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EVOLUTION_LEVEL_GENDER_BEAN, fields_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_LEVEL_BEAN);
        fields_.add(new StandardField(GENDER,BeanNatCommonLgNames.STRING,false,false,new EvolutionLevelGenderBeanGenderGet(),null));
        _std.getStds().addEntry(TYPE_EVOLUTION_LEVEL_GENDER_BEAN, type_);
    }
    private static void buildEvolutionMoveBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EVOLUTION_MOVE_BEAN, fields_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN);
        fields_.add(new StandardField(MOVE,BeanNatCommonLgNames.STRING,false,false,new EvolutionMoveBeanMoveGet(),null));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EvolutionMoveBeanClickMove()));
        _std.getStds().addEntry(TYPE_EVOLUTION_MOVE_BEAN, type_);
    }
    private static void buildEvolutionMoveTypeBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EVOLUTION_MOVE_TYPE_BEAN, fields_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN);
        fields_.add(new StandardField(TYPE,BeanNatCommonLgNames.STRING,false,false,new EvolutionMoveTypeBeanTypeGet(),null));
        _std.getStds().addEntry(TYPE_EVOLUTION_MOVE_TYPE_BEAN, type_);
    }
    private static void buildEvolutionStoneBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EVOLUTION_STONE_BEAN, fields_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN);
        fields_.add(new StandardField(STONE,BeanNatCommonLgNames.STRING,false,false,new EvolutionStoneBeanStoneGet(),null));
        methods_.add( new SpecNatMethod(CLICK_STONE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EvolutionStoneBeanClickStone()));
        _std.getStds().addEntry(TYPE_EVOLUTION_STONE_BEAN, type_);
    }
    private static void buildEvolutionStoneGenderBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EVOLUTION_STONE_GENDER_BEAN, fields_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_STONE_BEAN);
        fields_.add(new StandardField(GENDER,BeanNatCommonLgNames.STRING,false,false,new EvolutionStoneGenderBeanGenderGet(),null));
        _std.getStds().addEntry(TYPE_EVOLUTION_STONE_GENDER_BEAN, type_);
    }
    private static void buildEvolutionTeamBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EVOLUTION_TEAM_BEAN, fields_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN);
        fields_.add(new StandardField(OTHER,BeanNatCommonLgNames.STRING,false,false,new EvolutionTeamBeanOtherGet(),null));
        methods_.add( new SpecNatMethod(CLICK_TEAM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EvolutionTeamBeanClickTeam()));
        _std.getStds().addEntry(TYPE_EVOLUTION_TEAM_BEAN, type_);
    }
}
