package aiki.beans.pokemon;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import aiki.beans.facade.dto.*;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.items.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.*;
import aiki.instances.Instances;
import aiki.map.pokemon.enums.Gender;
import code.expressionlanguage.structs.Struct;
import code.images.BaseSixtyFourUtil;
import code.images.ConverterBufferedImage;
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

    public static Struct callPokemonLineDisplayNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonLineDisplayNameGet(),_str,_args);
    }


    public static Struct callPokemonLineTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonLineTypesGet(),_str,_args);
    }
    public static Struct callPokemonLineEvolutionsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonLineEvolutionsGet(),_str,_args);
    }
    public static Struct callPokedexBeanBooleansGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanBooleansGet(),_str,_args);
    }

    public static Struct callPokedexBeanClickLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanClickLink(),_str,_args);
    }

    public static Struct callPokedexBeanGetMiniImage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanGetMiniImage(),_str,_args);
    }

    public static Struct callPokedexBeanIsEvoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanIsEvoGet(),_str,_args);
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
//
//    public static class MiniTest{
//        @org.junit.Test
//        public void __() {
//            int[][] image = new int[2][2];
//            image[0][0]= ConverterBufferedImage.WHITE_RGB_INT;
//            image[0][1]= ConverterBufferedImage.WHITE_RGB_INT;
//            image[1][0]= ConverterBufferedImage.WHITE_RGB_INT;
//            image[1][1]= ConverterBufferedImage.WHITE_RGB_INT;
//            System.out.println(BaseSixtyFourUtil.getStringByImage(image));
//        }
//    }
    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM, moveDam(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(M_STA, moveDam(TargetChoice.ANY_FOE));
        EvolvingStone stone_ = Instances.newEvolvingStone();
        EvolvingItem item_ = Instances.newEvolvingItem();
        facade_.getData().completeMembers(I_STONE,stone_);
        facade_.getData().completeMembers(I_HOLD,item_);
        PokemonData pk0_ = specPk(new StringList("__"), P_POK_00);
        EvolutionLevelSimple el0_ = Instances.newEvolutionLevelSimple();
        el0_.setLevel((short)5);
        pk0_.getEvolutions().addEntry(P_POK_01, el0_);
        facade_.getData().completeMembers(P_POK_00, pk0_);
        PokemonData pk1_ = specPk(new StringList("__"), P_POK_00);
        EvolutionLevelGender el1_ = Instances.newEvolutionLevelGender();
        el1_.setGender(Gender.NO_GENDER);
        el1_.setLevel((short)5);
        pk1_.getEvolutions().addEntry(P_POK_02, el1_);
        facade_.getData().completeMembers(P_POK_01, pk1_);
        facade_.getData().completeMembers(P_POK_02, specPk(new StringList("__"), P_POK_00));
        PokemonData pk3_ = specPk(new StringList("__"), P_POK_03);
        EvolutionStoneSimple el3_ = Instances.newEvolutionStoneSimple();
        el3_.setStone(I_STONE);
        pk3_.getEvolutions().addEntry(P_POK_04, el3_);
        facade_.getData().completeMembers(P_POK_03, pk3_);
        PokemonData pk4_ = specPk(new StringList("__"), P_POK_03);
        EvolutionStoneGender el4_ = Instances.newEvolutionStoneGender();
        el4_.setGender(Gender.NO_GENDER);
        el4_.setStone(I_STONE);
        pk4_.getEvolutions().addEntry(P_POK_05, el4_);
        facade_.getData().completeMembers(P_POK_04, pk4_);
        PokemonData pk5_ = specPk(new StringList("__"), P_POK_03);
        EvolutionMove el5m_ = Instances.newEvolutionMove();
        el5m_.setMove(M_DAM);
        pk5_.getEvolutions().addEntry(P_POK_06,el5m_);
        EvolutionMoveType el5t_ = Instances.newEvolutionMoveType();
        el5t_.setType(T_TYPE1);
        pk5_.getEvolutions().addEntry(P_POK_07,el5t_);
        facade_.getData().completeMembers(P_POK_05, pk5_);
        facade_.getData().completeMembers(P_POK_06, specPk(new StringList("__"), P_POK_03));
        facade_.getData().completeMembers(P_POK_07, specPk(new StringList("__"), P_POK_03));
        PokemonData pk8_ = specPk(new StringList("__"), P_POK_09);
        EvolutionTeam el8_ = Instances.newEvolutionTeam();
        el8_.setPokemon(P_POK_11);
        pk8_.getEvolutions().addEntry(P_POK_09,el8_);
        facade_.getData().completeMembers(P_POK_08, pk8_);
        PokemonData pk9_ = specPk(new StringList("__"), P_POK_09);
        EvolutionItem el9_ = Instances.newEvolutionItem();
        el9_.setItem(I_HOLD);
        pk9_.getEvolutions().addEntry(P_POK_10,el9_);
        facade_.getData().completeMembers(P_POK_09, pk9_);
        facade_.getData().completeMembers(P_POK_10, specPk(new StringList("__"), P_POK_09));
        facade_.getData().completeMembers(P_POK_11, specPk(new StringList("__"), P_POK_11));
        PokemonData pk12_ = specPk(new StringList("__"), P_POK_12);
        pk12_.getEvolutions().addEntry(P_POK_13,Instances.newEvolutionHappiness());
        facade_.getData().completeMembers(P_POK_12, pk12_);
        facade_.getData().completeMembers(P_POK_13, specPk(new StringList("__"), P_POK_12));
        facade_.getData().completeMembers(P_POK_14, specLeg(new StringList("__"), P_POK_14));
        facade_.getData().completeMembers(P_POK_15, specLeg(new StringList("__"), P_POK_15));
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
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
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
