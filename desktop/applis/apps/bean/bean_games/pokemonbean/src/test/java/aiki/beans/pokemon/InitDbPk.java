package aiki.beans.pokemon;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.beans.facade.dto.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.items.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.*;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.LevelCave;
import aiki.map.levels.LevelRoad;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.Road;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.TileMiniMap;
import code.expressionlanguage.structs.Struct;
import code.images.*;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbPk extends InitDbConstr {

    public static final String I_STONE = "I_STONE";
    public static final String I_HOLD = "I_HOLD";
    public static final String P_POK_00 = "P_POK00";
    public static final String P_POK_01 = "P_POK01";
    public static final String P_POK_02 = "P_POK02";
    public static final String P_POK_03 = "P_POK03";
    public static final String P_POK_04 = "P_POK04";
    public static final String P_POK_05 = "P_POK05";
    public static final String P_POK_06 = "P_POK06";
    public static final String P_POK_07 = "P_POK07";
    public static final String P_POK_08 = "P_POK08";
    public static final String P_POK_09 = "P_POK09";
    public static final String P_POK_10 = "P_POK10";
    public static final String P_POK_11 = "P_POK11";
    public static final String P_POK_12 = "P_POK12";
    public static final String P_POK_13 = "P_POK13";
    public static final String P_POK_14 = "P_POK14";
    public static final String P_POK_15 = "P_POK15";

    public static final String I_STONE_TR = "I_STONE_TR";
    public static final String I_HOLD_TR = "I_HOLD_TR";
    public static final String P_POK_00_TR = "P_POK00_TR";
    public static final String P_POK_01_TR = "P_POK01_TR";
    public static final String P_POK_02_TR = "P_POK02_TR";
    public static final String P_POK_03_TR = "P_POK03_TR";
    public static final String P_POK_04_TR = "P_POK04_TR";
    public static final String P_POK_05_TR = "P_POK05_TR";
    public static final String P_POK_06_TR = "P_POK06_TR";
    public static final String P_POK_07_TR = "P_POK07_TR";
    public static final String P_POK_08_TR = "P_POK08_TR";
    public static final String P_POK_09_TR = "P_POK09_TR";
    public static final String P_POK_10_TR = "P_POK10_TR";
    public static final String P_POK_11_TR = "P_POK11_TR";
    public static final String P_POK_12_TR = "P_POK12_TR";
    public static final String P_POK_13_TR = "P_POK13_TR";
    public static final String P_POK_14_TR = "P_POK14_TR";
    public static final String P_POK_15_TR = "P_POK15_TR";
    public static final String DEFAULT_EGG_GROUP = "__";
    public static final String FIRST_GROUP = "_";
    public static final String SECOND_GROUP = "___";
    public static final String ROAD = "R 1";
    public static final String CITY = "CI 1";
    public static final String CAVE = "CA 1";

    public static Struct callPokemonLineDisplayNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonLineDisplayNameGet(),_str,_args);
    }


    public static Struct callPokemonLineTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonLineTypesGet(),_str,_args);
    }
    public static Struct callPokemonLineEvolutionsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonLineEvolutionsGet(),_str,_args);
    }
    public static Struct callPokedexBeanBooleansGet() {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanBooleansGet(),dispAllPks());
    }

    public static String callPokedexBeanClickLink(Struct _str, long... _args) {
        return navigateData(new PokedexBeanClickLink(),_str,_args);
    }

    public static String callPokedexBeanClickLink(long... _args) {
        return callPokedexBeanClickLink(dispAllPksSearch(),_args);
    }

    public static String callPokedexBeanClickLinkId(long... _args) {
        Struct bean_ = dispAllPksSearch();
        callPokedexBeanClickLink(bean_,_args);
        return getValPkId(bean_);
    }

    public static Struct callPokedexBeanGetMiniImage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanGetMiniImage(),_str,_args);
    }

    public static Struct callPokedexBeanIsEvoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanIsEvoGet(),_str,_args);
    }

    public static Struct callPokedexBeanHasEvoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanHasEvoGet(),_str,_args);
    }

    public static Struct callPokedexBeanIsLegGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanIsLegGet(),_str,_args);
    }

    public static Struct callPokedexBeanPokedexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanPokedexGet(),_str,_args);
    }

    public static Struct callPokedexBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanSearch(),_str,_args);
    }

    public static Struct callPokedexBeanTypedMaxNbPossEvosGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedMaxNbPossEvosGet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedMinNbPossEvosGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedMinNbPossEvosGet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedNameGet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedTypeGet(),_str,_args);
    }

    public static Struct callPokedexBeanWholeWordGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanWholeWordGet(),_str,_args);
    }

    public static Struct callPokedexBeanIsEvoSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanIsEvoSet(),_str,_args);
    }

    public static Struct callPokedexBeanHasEvoSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanHasEvoSet(),_str,_args);
    }

    public static Struct callPokedexBeanIsLegSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanIsLegSet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedMaxNbPossEvosSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedMaxNbPossEvosSet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedMinNbPossEvosSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedMinNbPossEvosSet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedNameSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedNameSet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedTypeSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedTypeSet(),_str,_args);
    }

    public static Struct callPokedexBeanWholeWordSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new PokedexBeanWholeWordSet(),_str,_args);
    }

    protected static Struct dispAllPks() {
        PkData pk_ = pkDataByFacade(feedDb());
        return dispAllPks(pk_);
    }

    private static Struct dispAllPks(PkData _pk) {
        StringMap<Struct> all_ = beanToPk(_pk);
        Struct welcome_ = all_.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        Struct moves_ = all_.getVal(AikiBeansPokemonStd.BEAN_POKEDEX);
        transit(_pk,new WelcomeBeanClickPokedex(),welcome_,moves_);
        return moves_;
    }

    protected static Struct dispAllPksSearch() {
        PkData pk_ = pkDataByFacade(feedDb());
        Struct moves_ = dispAllPks(pk_);
        transit(pk_,new PokedexBeanSearch(),moves_,moves_);
        return moves_;
    }

    protected static Struct transitToAllPks(PkData _pk, StringMap<Struct> _all,int _index) {
        Struct welcome_ = _all.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        Struct pks_ = _all.getVal(AikiBeansPokemonStd.BEAN_POKEDEX);
        Struct pk_ = _all.getVal(AikiBeansPokemonStd.BEAN_PK);
        transit(_pk,new WelcomeBeanClickPokedex(),welcome_,pks_);
        transit(_pk,new PokedexBeanSearch(),pks_,pks_);
        transit(_pk,new PokedexBeanClickLink(),pks_,pk_,_index);
        return pk_;
    }
    protected static String navigatePkSearch(Struct _moves) {
        return navigateData(new PokedexBeanSearch(), _moves);
    }
    public static StringMap<Struct> beanToPk(PkData _pk) {
        StringMap<Struct> map_ = new StringMap<Struct>();
        map_.addEntry(AikiBeansStd.BEAN_WELCOME,_pk.beanWelcomeBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_POKEDEX,_pk.beanPokedexBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToPk() {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry(AikiBeansStd.WEB_HTML_INDEX_HTML,AikiBeansStd.BEAN_WELCOME);
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML,AikiBeansPokemonStd.BEAN_POKEDEX);
        return map_;
    }

    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        facade_.getData().setDefaultEggGroup(DEFAULT_EGG_GROUP);
        facade_.getData().completeMembers(M_DAM, moveDam(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(M_STA, moveDam(TargetChoice.ANY_FOE));
        EvolvingStone stone_ = Instances.newEvolvingStone();
        EvolvingItem item_ = Instances.newEvolvingItem();
        facade_.getData().completeMembers(I_STONE,stone_);
        facade_.getData().completeMembers(I_HOLD,item_);
        PokemonData pk0_ = specPk(new StringList(FIRST_GROUP), P_POK_00);
        EvolutionLevelSimple el0_ = Instances.newEvolutionLevelSimple();
        el0_.setLevel((short)5);
        pk0_.getEvolutions().addEntry(P_POK_01, el0_);
        facade_.getData().completeMembers(P_POK_00, pk0_);
        PokemonData pk1_ = specPk(new StringList(FIRST_GROUP), P_POK_00);
        EvolutionLevelGender el1_ = Instances.newEvolutionLevelGender();
        el1_.setGender(Gender.NO_GENDER);
        el1_.setLevel((short)5);
        pk1_.getEvolutions().addEntry(P_POK_02, el1_);
        facade_.getData().completeMembers(P_POK_01, pk1_);
        facade_.getData().completeMembers(P_POK_02, specPk(new StringList(FIRST_GROUP), P_POK_00));
        PokemonData pk3_ = specPk(new StringList(SECOND_GROUP), P_POK_03);
        EvolutionStoneSimple el3_ = Instances.newEvolutionStoneSimple();
        el3_.setStone(I_STONE);
        pk3_.getEvolutions().addEntry(P_POK_04, el3_);
        facade_.getData().completeMembers(P_POK_03, pk3_);
        PokemonData pk4_ = specPk(new StringList(SECOND_GROUP), P_POK_03);
        EvolutionStoneGender el4_ = Instances.newEvolutionStoneGender();
        el4_.setGender(Gender.NO_GENDER);
        el4_.setStone(I_STONE);
        pk4_.getEvolutions().addEntry(P_POK_05, el4_);
        facade_.getData().completeMembers(P_POK_04, pk4_);
        PokemonData pk5_ = specPk(new StringList(SECOND_GROUP), P_POK_03);
        EvolutionMove el5m_ = Instances.newEvolutionMove();
        el5m_.setMove(M_DAM);
        pk5_.getEvolutions().addEntry(P_POK_06,el5m_);
        EvolutionMoveType el5t_ = Instances.newEvolutionMoveType();
        el5t_.setType(T_TYPE1);
        pk5_.getEvolutions().addEntry(P_POK_07,el5t_);
        facade_.getData().completeMembers(P_POK_05, pk5_);
        facade_.getData().completeMembers(P_POK_06, specPk(new StringList(SECOND_GROUP), P_POK_03));
        facade_.getData().completeMembers(P_POK_07, specPk(new StringList(SECOND_GROUP), P_POK_03));
        PokemonData pk8_ = specPk(new StringList(DEFAULT_EGG_GROUP), P_POK_08);
        EvolutionTeam el8_ = Instances.newEvolutionTeam();
        el8_.setPokemon(P_POK_11);
        pk8_.getEvolutions().addEntry(P_POK_09,el8_);
        facade_.getData().completeMembers(P_POK_08, pk8_);
        PokemonData pk9_ = specPk(new StringList(DEFAULT_EGG_GROUP), P_POK_08);
        EvolutionItem el9_ = Instances.newEvolutionItem();
        el9_.setItem(I_HOLD);
        pk9_.getEvolutions().addEntry(P_POK_10,el9_);
        facade_.getData().completeMembers(P_POK_09, pk9_);
        facade_.getData().completeMembers(P_POK_10, specPk(new StringList(DEFAULT_EGG_GROUP), P_POK_08));
        facade_.getData().completeMembers(P_POK_11, specPk(new StringList(DEFAULT_EGG_GROUP), P_POK_11));
        PokemonData pk12_ = specPk(new StringList(FIRST_GROUP), P_POK_12);
        pk12_.getEvolutions().addEntry(P_POK_13,Instances.newEvolutionHappiness());
        facade_.getData().completeMembers(P_POK_12, pk12_);
        facade_.getData().completeMembers(P_POK_13, specPk(new StringList(FIRST_GROUP), P_POK_12));
        facade_.getData().completeMembers(P_POK_14, specLeg(new StringList(DEFAULT_EGG_GROUP), P_POK_14));
        facade_.getData().completeMembers(P_POK_15, specLeg(new StringList(SECOND_GROUP), P_POK_15));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        facade_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
        facade_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
        facade_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE2, T_TYPE2_TR);
        facade_.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_STONE,I_STONE_TR);
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_HOLD,I_HOLD_TR);
        facade_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        facade_.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_00, P_POK_00_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_01, P_POK_01_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_02, P_POK_02_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_03, P_POK_03_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_04, P_POK_04_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_05, P_POK_05_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_06, P_POK_06_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_07, P_POK_07_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_08, P_POK_08_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_09, P_POK_09_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_10, P_POK_10_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_11, P_POK_11_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_12, P_POK_12_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_13, P_POK_13_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_14, P_POK_14_TR);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_15, P_POK_15_TR);
        facade_.getData().getMiniPk().addEntry(P_POK_00,BaseSixtyFourUtil.getImageByString("AAACAAAABAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_01,BaseSixtyFourUtil.getImageByString("AAACAAABBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_02,BaseSixtyFourUtil.getImageByString("AAACAAACBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_03,BaseSixtyFourUtil.getImageByString("AAACAAADBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_04,BaseSixtyFourUtil.getImageByString("AAACAAAEBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_05,BaseSixtyFourUtil.getImageByString("AAACAAAFBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_06,BaseSixtyFourUtil.getImageByString("AAACAAAGBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_07,BaseSixtyFourUtil.getImageByString("AAACAAAHBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_08,BaseSixtyFourUtil.getImageByString("AAACAAAIBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_09,BaseSixtyFourUtil.getImageByString("AAACAAAJBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_10,BaseSixtyFourUtil.getImageByString("AAACAAAKBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_11,BaseSixtyFourUtil.getImageByString("AAACAAALBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_12,BaseSixtyFourUtil.getImageByString("AAACAAAMBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_13,BaseSixtyFourUtil.getImageByString("AAACAAANBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_14,BaseSixtyFourUtil.getImageByString("AAACAAAOBAAA////////"));
        facade_.getData().getMiniPk().addEntry(P_POK_15,BaseSixtyFourUtil.getImageByString("AAACAAAPBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_00,BaseSixtyFourUtil.getImageByString("AAACAAAABAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_01,BaseSixtyFourUtil.getImageByString("AAACAAABBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_02,BaseSixtyFourUtil.getImageByString("AAACAAACBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_03,BaseSixtyFourUtil.getImageByString("AAACAAADBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_04,BaseSixtyFourUtil.getImageByString("AAACAAAEBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_05,BaseSixtyFourUtil.getImageByString("AAACAAAFBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_06,BaseSixtyFourUtil.getImageByString("AAACAAAGBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_07,BaseSixtyFourUtil.getImageByString("AAACAAAHBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_08,BaseSixtyFourUtil.getImageByString("AAACAAAIBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_09,BaseSixtyFourUtil.getImageByString("AAACAAAJBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_10,BaseSixtyFourUtil.getImageByString("AAACAAAKBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_11,BaseSixtyFourUtil.getImageByString("AAACAAALBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_12,BaseSixtyFourUtil.getImageByString("AAACAAAMBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_13,BaseSixtyFourUtil.getImageByString("AAACAAANBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_14,BaseSixtyFourUtil.getImageByString("AAACAAAOBAAA////////"));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_15,BaseSixtyFourUtil.getImageByString("AAACAAAPBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_00,BaseSixtyFourUtil.getImageByString("AAACAAAABAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_01,BaseSixtyFourUtil.getImageByString("AAACAAABBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_02,BaseSixtyFourUtil.getImageByString("AAACAAACBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_03,BaseSixtyFourUtil.getImageByString("AAACAAADBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_04,BaseSixtyFourUtil.getImageByString("AAACAAAEBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_05,BaseSixtyFourUtil.getImageByString("AAACAAAFBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_06,BaseSixtyFourUtil.getImageByString("AAACAAAGBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_07,BaseSixtyFourUtil.getImageByString("AAACAAAHBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_08,BaseSixtyFourUtil.getImageByString("AAACAAAIBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_09,BaseSixtyFourUtil.getImageByString("AAACAAAJBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_10,BaseSixtyFourUtil.getImageByString("AAACAAAKBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_11,BaseSixtyFourUtil.getImageByString("AAACAAALBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_12,BaseSixtyFourUtil.getImageByString("AAACAAAMBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_13,BaseSixtyFourUtil.getImageByString("AAACAAANBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_14,BaseSixtyFourUtil.getImageByString("AAACAAAOBAAA////////"));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_15,BaseSixtyFourUtil.getImageByString("AAACAAAPBAAA////////"));
        facade_.getData().getExpGrowth().addEntry(ExpType.E, DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        facade_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        facade_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        facade_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic, String>());
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,ST_ATT_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.DEFENSE,ST_DEF_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_ATTACK,ST_ATT_SPE_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_DEFENSE,ST_DEF_SPE_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,ST_SPEED_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.HP,ST_HP_TR);
        facade_.getData().getTranslatedBooleans().addEntry(EN,new IdMap<SelectedBoolean, String>());
        facade_.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.NO, B_NO);
        facade_.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES, B_YES);
        facade_.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES_AND_NO," ");
        facade_.getData().getTranslatedGenders().addEntry(EN,new IdMap<Gender,String>());
        facade_.getData().getTranslatedGenders().getVal(EN).addEntry(Gender.NO_GENDER,NO_G);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        DataMap map_ = Instances.newDataMap();
        Road r_ = Instances.newRoad();
        r_.setName(ROAD);
        LevelRoad lr_ = r_.getLevelRoad();
        AreaApparition a0_ = area();
        a0_.getWildPokemon().add(wpk(P_POK_00));
        a0_.getWildPokemon().add(wpk(P_POK_03));
        a0_.getWildPokemonFishing().add(wpk(P_POK_08));
        a0_.getWildPokemonFishing().add(wpk(P_POK_12));
        lr_.getWildPokemonAreas().add(a0_);
        AreaApparition a1_ = area();
        a1_.getWildPokemon().add(wpk(P_POK_01));
        a1_.getWildPokemon().add(wpk(P_POK_04));
        a1_.getWildPokemonFishing().add(wpk(P_POK_05));
        a1_.getWildPokemonFishing().add(wpk(P_POK_00));
        lr_.getWildPokemonAreas().add(a1_);
        map_.getPlaces().add(r_);
        City ci_ = Instances.newCity();
        ci_.setName(CITY);
        map_.getPlaces().add(ci_);
        Cave ca_ = Instances.newCave();
        ca_.setName(CAVE);
        LevelCave lcone_ = Instances.newLevelCave();
        AreaApparition a2_ = area();
        a2_.getWildPokemon().add(wpk(P_POK_03));
        a2_.getWildPokemon().add(wpk(P_POK_12));
        a2_.getWildPokemonFishing().add(wpk(P_POK_11));
        a2_.getWildPokemonFishing().add(wpk(P_POK_13));
        lcone_.getWildPokemonAreas().add(a2_);
        AreaApparition a3_ = area();
        a3_.getWildPokemon().add(wpk(P_POK_09));
        a3_.getWildPokemon().add(wpk(P_POK_11));
        a3_.getWildPokemonFishing().add(wpk(P_POK_03));
        a3_.getWildPokemonFishing().add(wpk(P_POK_13));
        lcone_.getWildPokemonAreas().add(a3_);
        ca_.getLevels().add(lcone_);
        LevelCave lctwo_ = Instances.newLevelCave();
        AreaApparition a4_ = area();
        a4_.getWildPokemon().add(wpk(P_POK_14));
        a4_.getWildPokemon().add(wpk(P_POK_03));
        a4_.getWildPokemonFishing().add(wpk(P_POK_09));
        a4_.getWildPokemonFishing().add(wpk(P_POK_12));
        lctwo_.getWildPokemonAreas().add(a4_);
        AreaApparition a5_ = area();
        a5_.getWildPokemon().add(wpk(P_POK_10));
        a5_.getWildPokemon().add(wpk(P_POK_09));
        a5_.getWildPokemonFishing().add(wpk(P_POK_12));
        a5_.getWildPokemonFishing().add(wpk(P_POK_03));
        lctwo_.getWildPokemonAreas().add(a5_);
        lctwo_.getLegendaryPks().addEntry(newPoint(0,0),wpk(P_POK_15));
        ca_.getLevels().add(lctwo_);
        map_.getPlaces().add(ca_);
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)0),tm("0", -1));
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)1),tm("1", 0));
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)0),tm("2", 1));
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)1),tm("3", 2));
        facade_.getData().getMiniMap().addEntry("0",BaseSixtyFourUtil.getImageByString("AAACXXXXCAAADAAA////"));
        facade_.getData().getMiniMap().addEntry("1",BaseSixtyFourUtil.getImageByString("AAACXXXXCAAAEAAA////"));
        facade_.getData().getMiniMap().addEntry("2",BaseSixtyFourUtil.getImageByString("AAACXXXXCAAAFAAA////"));
        facade_.getData().getMiniMap().addEntry("3",BaseSixtyFourUtil.getImageByString("AAACXXXXCAAAGAAA////"));
        facade_.getData().setMap(map_);
        return facade_;
    }

    private static TileMiniMap tm(String _file, int _pl) {
        TileMiniMap t_ = Instances.newTileMiniMap();
        t_.setFile(_file);
        t_.setPlace((short) _pl);
        return t_;
    }

    private static WildPk wpk(String _name) {
        WildPk w_ = Instances.newWildPk();
        w_.setGender(Gender.NO_GENDER);
        w_.setLevel((short)1);
        w_.setName(_name);
        return w_;
    }

    private static AreaApparition area() {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setAvgNbSteps((short)2);
        a_.setMultFight((byte)1);
        return a_;
    }

    private static PokemonData specPk(StringList _g, String _base) {
        PokemonData pk_ = pk(_g, GenderRepartition.NO_GENDER);
        pk_.setBaseEvo(_base);
        return pk_;
    }

    private static PokemonData specLeg(StringList _g, String _base) {
        PokemonData pk_ = pk(_g, GenderRepartition.LEGENDARY);
        pk_.setBaseEvo(_base);
        pk_.setTypes(new StringList(T_TYPE2));
        return pk_;
    }
}
