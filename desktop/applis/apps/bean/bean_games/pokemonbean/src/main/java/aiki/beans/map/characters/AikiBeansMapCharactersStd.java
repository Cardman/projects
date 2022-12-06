package aiki.beans.map.characters;

import aiki.beans.AikiBeansStd;
import aiki.beans.CommonBean;
import aiki.beans.PokemonStandards;
import aiki.beans.map.elements.AikiBeansMapElementsStd;
import code.bean.nat.*;
import code.util.CustList;
public final class AikiBeansMapCharactersStd{
    public static final String TYPE_ALLY_BEAN = "aiki.beans.map.characters.AllyBean";
    public static final String TYPE_DEALER_BEAN = "aiki.beans.map.characters.DealerBean";
    public static final String TYPE_DUAL_FIGHT_BEAN = "aiki.beans.map.characters.DualFightBean";
    public static final String TYPE_SELLER_BEAN = "aiki.beans.map.characters.SellerBean";
    public static final String TYPE_TRAINER_BEAN = "aiki.beans.map.characters.TrainerBean";
    public static final String TYPE_TRAINER_ONE_FIGHT_BEAN = "aiki.beans.map.characters.TrainerOneFightBean";
    private static final String GET_NAME = "getName";
    private static final String CLICK_ITEM = "clickItem";
    private static final String GET_ITEM = "getItem";
    private static final String CLICK_MOVE = "clickMove";
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
    }
    private static void buildAllyBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(TEAM, BeanNatCommonLgNames.TYPE_LIST, new AllyBeanTeamGet(),null));
        fields_.add(new StandardField(ALLY,PokemonStandards.TYPE_ALLY, null,new AllyBeanAllySet()));
        methods_.add( new SpecNatMethod(CommonBean.GET_IMAGE,BeanNatCommonLgNames.STRING, new AllyBeanGetImage()));
        methods_.add( new SpecNatMethod(CommonBean.CLICK_NAME,BeanNatCommonLgNames.STRING, new AllyBeanClickName()));
        methods_.add( new SpecNatMethod(CommonBean.GET_NAME,BeanNatCommonLgNames.STRING, new AllyBeanGetName()));
        methods_.add( new SpecNatMethod(CommonBean.GET_GENDER,BeanNatCommonLgNames.STRING, new AllyBeanGetGender()));
        methods_.add( new SpecNatMethod(CommonBean.CLICK_ABILITY,BeanNatCommonLgNames.STRING, new AllyBeanClickAbility()));
        methods_.add( new SpecNatMethod(CommonBean.GET_ABILITY,BeanNatCommonLgNames.STRING, new AllyBeanGetAbility()));
        methods_.add( new SpecNatMethod(CommonBean.CLICK_ITEM,BeanNatCommonLgNames.STRING, new AllyBeanClickItem()));
        methods_.add( new SpecNatMethod(CommonBean.GET_ITEM,BeanNatCommonLgNames.STRING, new AllyBeanGetItem()));
        methods_.add( new SpecNatMethod(CommonBean.CLICK_MOVE,BeanNatCommonLgNames.STRING, new AllyBeanClickMove()));
        methods_.add( new SpecNatMethod(CommonBean.GET_MOVE,BeanNatCommonLgNames.STRING, new AllyBeanGetMove()));
        _std.getStds().addEntry(TYPE_ALLY_BEAN, type_);
    }
    private static void buildDealerBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansMapElementsStd.ELTS);
        methods_.add( new SpecNatMethod(GET_ITEMS, BeanNatCommonLgNames.TYPE_LIST, new DealerBeanGetItems()));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, new DealerBeanClickItem()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, new DealerBeanGetItem()));
        methods_.add( new SpecNatMethod(GET_ALL_TM, BeanNatCommonLgNames.TYPE_LIST, new DealerBeanGetAllTm()));
        methods_.add( new SpecNatMethod(CLICK_TM,BeanNatCommonLgNames.STRING, new DealerBeanClickTm()));
        methods_.add( new SpecNatMethod(GET_TM,BeanNatCommonLgNames.STRING, new DealerBeanGetTm()));
        _std.getStds().addEntry(TYPE_DEALER_BEAN, type_);
    }
    private static void buildDualFightBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansMapElementsStd.ELTS);
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING, new DualFightBeanImageGet(),null));
        fields_.add(new StandardField(IMAGE_MINI,BeanNatCommonLgNames.STRING, new DualFightBeanImageMiniGet(),null));
        fields_.add(new StandardField(IMAGE_MINI_SECOND,BeanNatCommonLgNames.STRING, new DualFightBeanImageMiniSecondGet(),null));
        fields_.add(new StandardField(PAGE_ALLY,BeanNatCommonLgNames.STRING, new CstNatCaller(DualFightBean.PAGE_ALLY),null));
        fields_.add(new StandardField(ALLY,PokemonStandards.TYPE_ALLY, new DualFightBeanAllyGet(),null));
        fields_.add(new StandardField(PAGE_TEAM,BeanNatCommonLgNames.STRING, new CstNatCaller(DualFightBean.PAGE_TEAM),null));
        fields_.add(new StandardField(TRAINER,PokemonStandards.TYPE_TEMP_TRAINER, new DualFightBeanTrainerGet(),null));
        _std.getStds().addEntry(TYPE_DUAL_FIGHT_BEAN, type_);
    }
    private static void buildSellerBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansMapElementsStd.ELTS);
        methods_.add( new SpecNatMethod(GET_ITEMS, BeanNatCommonLgNames.TYPE_LIST, new SellerBeanGetItems()));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, new SellerBeanClickItem()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, new SellerBeanGetItem()));
        methods_.add( new SpecNatMethod(GET_ALL_TM, BeanNatCommonLgNames.TYPE_LIST, new SellerBeanGetAllTm()));
        methods_.add( new SpecNatMethod(CLICK_TM,BeanNatCommonLgNames.STRING, new SellerBeanClickTm()));
        methods_.add( new SpecNatMethod(GET_TM,BeanNatCommonLgNames.STRING, new SellerBeanGetTm()));
        _std.getStds().addEntry(TYPE_SELLER_BEAN, type_);
    }
    private static void buildTrainerBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansMapElementsStd.ELTS);
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING, new TrainerBeanImageGet(),null));
        fields_.add(new StandardField(IMAGE_MINI,BeanNatCommonLgNames.STRING, new TrainerBeanImageMiniGet(),null));
        fields_.add(new StandardField(PAGE_TEAM,BeanNatCommonLgNames.STRING, new CstNatCaller(TrainerBean.PAGE_TEAM),null));
        fields_.add(new StandardField(TRAINER,PokemonStandards.TYPE_TRAINER, new TrainerBeanTrainerGet(),null));
        fields_.add(new StandardField(MOVE,BeanNatCommonLgNames.STRING, new TrainerBeanMoveGet(),null));
        methods_.add( new SpecNatMethod(GET_TEAMS_REWARDS, BeanNatCommonLgNames.TYPE_LIST, new TrainerBeanGetTeamsRewards()));
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, new TrainerBeanGetName()));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, new TrainerBeanClickMove()));
        methods_.add( new SpecNatMethod(GET_TR_MOVE,BeanNatCommonLgNames.STRING, new TrainerBeanGetTrMove()));
        _std.getStds().addEntry(TYPE_TRAINER_BEAN, type_);
    }
}
