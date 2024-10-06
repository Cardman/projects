package aiki.beans.pokemon;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.beans.facade.dto.*;
import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.items.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.*;
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
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
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
    public static final String IMG_M00 = "AAACAAAABAAA////////";
    public static final String IMG_M01 = "AAACAAABBAAA////////";
    public static final String IMG_M02 = "AAACAAACBAAA////////";
    public static final String IMG_M03 = "AAACAAADBAAA////////";
    public static final String IMG_M04 = "AAACAAAEBAAA////////";
    public static final String IMG_M05 = "AAACAAAFBAAA////////";
    public static final String IMG_M06 = "AAACAAAGBAAA////////";
    public static final String IMG_M07 = "AAACAAAHBAAA////////";
    public static final String IMG_M08 = "AAACAAAIBAAA////////";
    public static final String IMG_M09 = "AAACAAAJBAAA////////";
    public static final String IMG_M10 = "AAACAAAKBAAA////////";
    public static final String IMG_M11 = "AAACAAALBAAA////////";
    public static final String IMG_M12 = "AAACAAAMBAAA////////";
    public static final String IMG_M13 = "AAACAAANBAAA////////";
    public static final String IMG_M14 = "AAACAAAOBAAA////////";
    public static final String IMG_M15 = "AAACAAAPBAAA////////";
    public static final String IMG_B00 = "AAACAAAADAAA////////";
    public static final String IMG_B01 = "AAACAAABDAAA////////";
    public static final String IMG_B02 = "AAACAAACDAAA////////";
    public static final String IMG_B03 = "AAACAAADDAAA////////";
    public static final String IMG_B04 = "AAACAAAEDAAA////////";
    public static final String IMG_B05 = "AAACAAAFDAAA////////";
    public static final String IMG_B06 = "AAACAAAGDAAA////////";
    public static final String IMG_B07 = "AAACAAAHDAAA////////";
    public static final String IMG_B08 = "AAACAAAIDAAA////////";
    public static final String IMG_B09 = "AAACAAAJDAAA////////";
    public static final String IMG_B10 = "AAACAAAKDAAA////////";
    public static final String IMG_B11 = "AAACAAALDAAA////////";
    public static final String IMG_B12 = "AAACAAAMDAAA////////";
    public static final String IMG_B13 = "AAACAAANDAAA////////";
    public static final String IMG_B14 = "AAACAAAODAAA////////";
    public static final String IMG_B15 = "AAACAAAPDAAA////////";
    public static final String IMG_F00 = "AAACAAAAEAAA////////";
    public static final String IMG_F01 = "AAACAAABEAAA////////";
    public static final String IMG_F02 = "AAACAAACEAAA////////";
    public static final String IMG_F03 = "AAACAAADEAAA////////";
    public static final String IMG_F04 = "AAACAAAEEAAA////////";
    public static final String IMG_F05 = "AAACAAAFEAAA////////";
    public static final String IMG_F06 = "AAACAAAGEAAA////////";
    public static final String IMG_F07 = "AAACAAAHEAAA////////";
    public static final String IMG_F08 = "AAACAAAIEAAA////////";
    public static final String IMG_F09 = "AAACAAAJEAAA////////";
    public static final String IMG_F10 = "AAACAAAKEAAA////////";
    public static final String IMG_F11 = "AAACAAALEAAA////////";
    public static final String IMG_F12 = "AAACAAAMEAAA////////";
    public static final String IMG_F13 = "AAACAAANEAAA////////";
    public static final String IMG_F14 = "AAACAAAOEAAA////////";
    public static final String IMG_F15 = "AAACAAAPEAAA////////";
    public static final String IMG_0 = "AAACXXXXCAAADAAA////";
    public static final String IMG_1 = "AAACXXXXCAAAEAAA////";
    public static final String IMG_2 = "AAACXXXXCAAAFAAA////";
    public static final String IMG_3 = "AAACXXXXCAAAGAAA////";

    public static NaSt callPokemonLineDisplayNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonLineDisplayNameGet(),_str,_args);
    }


    public static NaSt callPokemonLineTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonLineTypesGet(),_str,_args);
    }
    public static NaSt callPokemonLineEvolutionsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonLineEvolutionsGet(),_str,_args);
    }
    public static NaSt callPokedexBeanBooleansGet() {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanBooleansGet(),dispAllPks());
    }

    public static String callPokedexBeanClickLink(NaSt _str, long... _args) {
        return navigateData(new PokedexBeanClickLink(),_str,_args);
    }

    public static String callPokedexBeanClickLink(long... _args) {
        return callPokedexBeanClickLink(dispAllPksSearch(),_args);
    }

    public static String callPokedexBeanClickLinkId(long... _args) {
        NaSt bean_ = dispAllPksSearch();
        callPokedexBeanClickLink(bean_,_args);
        return getValPkId(bean_);
    }

    public static NaSt callPokedexBeanGetMiniImage(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanGetMiniImage(),_str,_args);
    }

    public static NaSt callPokedexBeanIsEvoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanIsEvoGet(),_str,_args);
    }

    public static NaSt callPokedexBeanHasEvoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanHasEvoGet(),_str,_args);
    }

    public static NaSt callPokedexBeanIsLegGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanIsLegGet(),_str,_args);
    }

    public static NaSt callPokedexBeanPokedexGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanPokedexGet(),_str,_args);
    }

    public static NaSt callPokedexBeanSearch(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanSearch(),_str,_args);
    }

    public static NaSt callPokedexBeanTypedMaxNbPossEvosGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedMaxNbPossEvosGet(),_str,_args);
    }

    public static NaSt callPokedexBeanTypedMinNbPossEvosGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedMinNbPossEvosGet(),_str,_args);
    }

    public static NaSt callPokedexBeanTypedNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedNameGet(),_str,_args);
    }

    public static NaSt callPokedexBeanTypedTypeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedTypeGet(),_str,_args);
    }

    public static NaSt callPokedexBeanWholeWordGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanWholeWordGet(),_str,_args);
    }

    public static NaSt callPokedexBeanIsEvoSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanIsEvoSet(),_str,_args);
    }

    public static NaSt callPokedexBeanHasEvoSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanHasEvoSet(),_str,_args);
    }

    public static NaSt callPokedexBeanIsLegSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanIsLegSet(),_str,_args);
    }

    public static NaSt callPokedexBeanTypedMaxNbPossEvosSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedMaxNbPossEvosSet(),_str,_args);
    }

    public static NaSt callPokedexBeanTypedMinNbPossEvosSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedMinNbPossEvosSet(),_str,_args);
    }

    public static NaSt callPokedexBeanTypedNameSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedNameSet(),_str,_args);
    }

    public static NaSt callPokedexBeanTypedTypeSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedTypeSet(),_str,_args);
    }

    public static NaSt callPokedexBeanWholeWordSet(NaSt _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new PokedexBeanWholeWordSet(),_str,_args);
    }

    protected static NaSt dispAllPks() {
        PkData pk_ = pkDataByFacade(feedDb());
        return dispAllPks(pk_);
    }

    private static NaSt dispAllPks(PkData _pk) {
        StringMap<NaSt> all_ = beanToPk(_pk);
        NaSt welcome_ = all_.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt moves_ = all_.getVal(AikiBeansPokemonStd.BEAN_POKEDEX);
        transit(_pk,new WelcomeBeanClickPokedex(),welcome_,moves_);
        return moves_;
    }

    protected static NaSt dispAllPksSearch() {
        PkData pk_ = pkDataByFacade(feedDb());
        NaSt moves_ = dispAllPks(pk_);
        transit(pk_,new PokedexBeanSearch(),moves_,moves_);
        return moves_;
    }

    protected static NaSt transitToAllPks(PkData _pk, StringMap<NaSt> _all,int _index) {
        NaSt welcome_ = _all.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt pks_ = _all.getVal(AikiBeansPokemonStd.BEAN_POKEDEX);
        NaSt pk_ = _all.getVal(AikiBeansPokemonStd.BEAN_PK);
        transit(_pk,new WelcomeBeanClickPokedex(),welcome_,pks_);
        transit(_pk,new PokedexBeanSearch(),pks_,pks_);
        transit(_pk,new PokedexBeanClickLink(),pks_,pk_,_index);
        return pk_;
    }
    protected static String navigatePkSearch(NaSt _moves) {
        return navigateData(new PokedexBeanSearch(), _moves);
    }
    public static StringMap<NaSt> beanToPk(PkData _pk) {
        StringMap<NaSt> map_ = new StringMap<NaSt>();
        map_.addEntry(AikiBeansStd.BEAN_WELCOME,_pk.beanWelcomeBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_POKEDEX,_pk.beanPokedexBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToPk() {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,AikiBeansStd.BEAN_WELCOME);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML,AikiBeansPokemonStd.BEAN_POKEDEX);
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
        facade_.getData().getMiniPk().addEntry(P_POK_00,getImageByString(IMG_M00));
        facade_.getData().getMiniPk().addEntry(P_POK_01,getImageByString(IMG_M01));
        facade_.getData().getMiniPk().addEntry(P_POK_02,getImageByString(IMG_M02));
        facade_.getData().getMiniPk().addEntry(P_POK_03,getImageByString(IMG_M03));
        facade_.getData().getMiniPk().addEntry(P_POK_04,getImageByString(IMG_M04));
        facade_.getData().getMiniPk().addEntry(P_POK_05,getImageByString(IMG_M05));
        facade_.getData().getMiniPk().addEntry(P_POK_06,getImageByString(IMG_M06));
        facade_.getData().getMiniPk().addEntry(P_POK_07,getImageByString(IMG_M07));
        facade_.getData().getMiniPk().addEntry(P_POK_08,getImageByString(IMG_M08));
        facade_.getData().getMiniPk().addEntry(P_POK_09,getImageByString(IMG_M09));
        facade_.getData().getMiniPk().addEntry(P_POK_10,getImageByString(IMG_M10));
        facade_.getData().getMiniPk().addEntry(P_POK_11,getImageByString(IMG_M11));
        facade_.getData().getMiniPk().addEntry(P_POK_12,getImageByString(IMG_M12));
        facade_.getData().getMiniPk().addEntry(P_POK_13,getImageByString(IMG_M13));
        facade_.getData().getMiniPk().addEntry(P_POK_14,getImageByString(IMG_M14));
        facade_.getData().getMiniPk().addEntry(P_POK_15,getImageByString(IMG_M15));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_00,getImageByString(IMG_B00));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_01,getImageByString(IMG_B01));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_02,getImageByString(IMG_B02));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_03,getImageByString(IMG_B03));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_04,getImageByString(IMG_B04));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_05,getImageByString(IMG_B05));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_06,getImageByString(IMG_B06));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_07,getImageByString(IMG_B07));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_08,getImageByString(IMG_B08));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_09,getImageByString(IMG_B09));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_10,getImageByString(IMG_B10));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_11,getImageByString(IMG_B11));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_12,getImageByString(IMG_B12));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_13,getImageByString(IMG_B13));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_14,getImageByString(IMG_B14));
        facade_.getData().getMaxiPkBack().addEntry(P_POK_15,getImageByString(IMG_B15));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_00,getImageByString(IMG_F00));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_01,getImageByString(IMG_F01));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_02,getImageByString(IMG_F02));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_03,getImageByString(IMG_F03));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_04,getImageByString(IMG_F04));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_05,getImageByString(IMG_F05));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_06,getImageByString(IMG_F06));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_07,getImageByString(IMG_F07));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_08,getImageByString(IMG_F08));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_09,getImageByString(IMG_F09));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_10,getImageByString(IMG_F10));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_11,getImageByString(IMG_F11));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_12,getImageByString(IMG_F12));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_13,getImageByString(IMG_F13));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_14,getImageByString(IMG_F14));
        facade_.getData().getMaxiPkFront().addEntry(P_POK_15,getImageByString(IMG_F15));
        facade_.getData().getExpGrowth().addEntry(ExpType.E, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        facade_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
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
        facade_.getData().addConstNumTest(DataBase.EVO_BONHEUR, Rate.newRate("128"));
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
        facade_.getData().getMiniMap().addEntry("0",getImageByString(IMG_0));
        facade_.getData().getMiniMap().addEntry("1",getImageByString(IMG_1));
        facade_.getData().getMiniMap().addEntry("2",getImageByString(IMG_2));
        facade_.getData().getMiniMap().addEntry("3",getImageByString(IMG_3));
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
