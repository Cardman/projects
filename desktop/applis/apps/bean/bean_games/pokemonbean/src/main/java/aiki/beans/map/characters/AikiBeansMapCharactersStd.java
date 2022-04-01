package aiki.beans.map.characters;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansMapCharactersStd{
    public static final String TYPE_ALLY_BEAN = "aiki.beans.map.characters.AllyBean";
    public static final String TYPE_DEALER_BEAN = "aiki.beans.map.characters.DealerBean";
    public static final String TYPE_DUAL_FIGHT_BEAN = "aiki.beans.map.characters.DualFightBean";
    public static final String TYPE_SELLER_BEAN = "aiki.beans.map.characters.SellerBean";
    public static final String TYPE_TRAINER_BEAN = "aiki.beans.map.characters.TrainerBean";
    public static final String TYPE_TRAINER_ONE_FIGHT_BEAN = "aiki.beans.map.characters.TrainerOneFightBean";
    private static final String GET_IMAGE = "getImage";
    private static final String CLICK_NAME = "clickName";
    private static final String GET_NAME = "getName";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_ABILITY = "getAbility";
    private static final String CLICK_ITEM = "clickItem";
    private static final String GET_ITEM = "getItem";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_MOVE = "getMove";
    private static final String GET_ITEMS = "getItems";
    private static final String GET_ALL_TM = "getAllTm";
    private static final String CLICK_TM = "clickTm";
    private static final String GET_TM = "getTm";
    private static final String GET_TEAMS_REWARDS = "getTeamsRewards";
    private static final String GET_TR_MOVE = "getTrMove";
    private static final String TEAM = "team";
    private static final String IMAGE = "image";
    private static final String IMAGE_MINI = "imageMini";
    private static final String IMAGE_MINI_SECOND = "imageMiniSecond";
    private static final String PAGE_ALLY = "pageAlly";
    private static final String ALLY = "ally";
    private static final String PAGE_TEAM = "pageTeam";
    private static final String TRAINER = "trainer";
    private static final String MOVE = "move";
    private AikiBeansMapCharactersStd(){}
    public static void build(PokemonStandards _std) {
        buildAllyBean(_std);
        buildDealerBean(_std);
        buildDualFightBean(_std);
        buildSellerBean(_std);
        buildTrainerBean(_std);
        buildTrainerOneFightBean(_std);
    }
    private static void buildAllyBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_ALLY_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(TEAM, BeanNatLgNames.TYPE_LIST,false,false,new AllyBeanTeamGet(),null));
        fields_.add(new StandardField(ALLY,PokemonStandards.TYPE_ALLY,false,false,null,new AllyBeanAllySet()));
        methods_.add( new SpecNatMethod(GET_IMAGE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AllyBeanGetImage()));
        methods_.add( new SpecNatMethod(CLICK_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AllyBeanClickName()));
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AllyBeanGetName()));
        methods_.add( new SpecNatMethod(CLICK_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AllyBeanClickAbility()));
        methods_.add( new SpecNatMethod(GET_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AllyBeanGetAbility()));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AllyBeanClickItem()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AllyBeanGetItem()));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AllyBeanClickMove()));
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AllyBeanGetMove()));
        _std.getStds().addEntry(TYPE_ALLY_BEAN, type_);
    }
    private static void buildDealerBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_DEALER_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        methods_.add( new SpecNatMethod(GET_ITEMS, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new DealerBeanGetItems()));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new DealerBeanClickItem()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new DealerBeanGetItem()));
        methods_.add( new SpecNatMethod(GET_ALL_TM, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new DealerBeanGetAllTm()));
        methods_.add( new SpecNatMethod(CLICK_TM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new DealerBeanClickTm()));
        methods_.add( new SpecNatMethod(GET_TM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new DealerBeanGetTm()));
        _std.getStds().addEntry(TYPE_DEALER_BEAN, type_);
    }
    private static void buildDualFightBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_DUAL_FIGHT_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING,false,false,new DualFightBeanImageGet(),null));
        fields_.add(new StandardField(IMAGE_MINI,BeanNatCommonLgNames.STRING,false,false,new DualFightBeanImageMiniGet(),null));
        fields_.add(new StandardField(IMAGE_MINI_SECOND,BeanNatCommonLgNames.STRING,false,false,new DualFightBeanImageMiniSecondGet(),null));
        fields_.add(new StandardField(PAGE_ALLY,BeanNatCommonLgNames.STRING,false,false,new DualFightBeanPageAllyGet(),null));
        fields_.add(new StandardField(ALLY,PokemonStandards.TYPE_ALLY,false,false,new DualFightBeanAllyGet(),null));
        fields_.add(new StandardField(PAGE_TEAM,BeanNatCommonLgNames.STRING,false,false,new DualFightBeanPageTeamGet(),null));
        fields_.add(new StandardField(TRAINER,PokemonStandards.TYPE_TEMP_TRAINER,false,false,new DualFightBeanTrainerGet(),null));
        _std.getStds().addEntry(TYPE_DUAL_FIGHT_BEAN, type_);
    }
    private static void buildSellerBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_SELLER_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        methods_.add( new SpecNatMethod(GET_ITEMS, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new SellerBeanGetItems()));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new SellerBeanClickItem()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new SellerBeanGetItem()));
        methods_.add( new SpecNatMethod(GET_ALL_TM, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new SellerBeanGetAllTm()));
        methods_.add( new SpecNatMethod(CLICK_TM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new SellerBeanClickTm()));
        methods_.add( new SpecNatMethod(GET_TM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new SellerBeanGetTm()));
        _std.getStds().addEntry(TYPE_SELLER_BEAN, type_);
    }
    private static void buildTrainerBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_TRAINER_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING,false,false,new TrainerBeanImageGet(),null));
        fields_.add(new StandardField(IMAGE_MINI,BeanNatCommonLgNames.STRING,false,false,new TrainerBeanImageMiniGet(),null));
        fields_.add(new StandardField(PAGE_TEAM,BeanNatCommonLgNames.STRING,false,false,new TrainerBeanPageTeamGet(),null));
        fields_.add(new StandardField(TRAINER,PokemonStandards.TYPE_TRAINER,false,false,new TrainerBeanTrainerGet(),null));
        fields_.add(new StandardField(MOVE,BeanNatCommonLgNames.STRING,false,false,new TrainerBeanMoveGet(),null));
        methods_.add( new SpecNatMethod(GET_TEAMS_REWARDS, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new TrainerBeanGetTeamsRewards()));
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new TrainerBeanGetName()));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new TrainerBeanClickMove()));
        methods_.add( new SpecNatMethod(GET_TR_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new TrainerBeanGetTrMove()));
        _std.getStds().addEntry(TYPE_TRAINER_BEAN, type_);
    }
    private static void buildTrainerOneFightBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_TRAINER_ONE_FIGHT_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        _std.getStds().addEntry(TYPE_TRAINER_ONE_FIGHT_BEAN, type_);
    }
}
