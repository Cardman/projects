package aiki.beans.moves;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.beans.facade.dto.MoveLine;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.instances.Instances;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.*;

public abstract class InitDbMoves extends InitDbConstr {

    public static final String BEAN_MOVES="moves";
    public static final String BEAN_MOVE="move";

    public static void callMovesBeanCategorySet(MovesBean _str, String _args) {
        _str.getTypedCategory().setupValue(_args);
    }

    public static void callMovesBeanMaxAccuracySet(MovesBean _str, String _args) {
        _str.getMaxAccuracy().setupValue(_args);
    }

    public static void callMovesBeanMaxPowerSet(MovesBean _str, String _args) {
        _str.getMaxPower().setupValue(_args);
    }

    public static void callMovesBeanMinAccuracySet(MovesBean _str, String _args) {
        _str.getMinAccuracy().setupValue(_args);
    }

    public static void callMovesBeanMinPowerSet(MovesBean _str, String _args) {
        _str.getMinPower().setupValue(_args);
    }

    public static void callMovesBeanTypedNameSet(MovesBean _str, String _args) {
        _str.getTypedName().setupValue(_args);
    }

    public static void callMovesBeanTypedTypeSet(MovesBean _str, String _args) {
        _str.getTypedType().setupValue(_args);
    }

    public static void callMovesBeanWholeWordSet(MovesBean _str, boolean _args) {
        _str.getWholeWord().setSelected(_args);
    }

    public static void callMovesBeanLearntSet(MovesBean _str, String _args) {
        _str.getLearnt().setupValue(_args);
    }

    public static AbsMap<String,String> callMovesBeanCategoriesGet(MovesBean _str, int... _args) {
        return (_str.getCategories());
    }

    public static String callMovesBeanCategoryGet(MovesBean _str, int... _args) {
        return _str.getTypedCategory().tryRet();
    }

    public static String callMovesBeanMaxAccuracyGet(MovesBean _str, int... _args) {
        return _str.getMaxAccuracy().tryRet();
    }

    public static String callMovesBeanMaxPowerGet(MovesBean _str, int... _args) {
        return _str.getMaxPower().tryRet();
    }

    public static String callMovesBeanMinAccuracyGet(MovesBean _str, int... _args) {
        return _str.getMinAccuracy().tryRet();
    }

    public static String callMovesBeanMinPowerGet(MovesBean _str, int... _args) {
        return _str.getMinPower().tryRet();
    }

    public static AbsMap<String,String> callMovesBeanBooleansGet(MovesBean _str, int... _args) {
        return _str.getBooleans();
    }

    public static CustList<MoveLine> callMovesBeanMovesGet(MovesBean _str, int... _args) {
        return _str.getMoves();
    }

//    public static NaSt callMovesBeanSearch(MovesBean _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MovesBeanSearch(),_str,_args);
//    }

    public static String callMovesBeanTypedNameGet(MovesBean _str, int... _args) {
        return _str.getTypedName().tryRet();
    }

    public static String callMovesBeanTypedTypeGet(MovesBean _str, int... _args) {
        return _str.getTypedType().tryRet();
    }

    public static boolean callMovesBeanWholeWordGet(MovesBean _str, int... _args) {
        return _str.getWholeWord().isSelected();
    }

    public static String callMoveLineBeanAccuracyGet(int _index, int... _args) {
        return dispLine(feedDb(), _index).getAccuracy();
    }

    public static String callMoveLineBeanCategoryGet(int _index, int... _args) {
        return dispLine(feedDb(), _index).getCategory();
    }

    public static String callMoveLineBeanDisplayNameGet(int _index, int... _args) {
        return dispLine(feedDb(), _index).getDisplayName();
    }

//    private static int callMoveLineBeanIndexGet(Struct _str, long... _args) {
//        return toInt(BeanPokemonCommonTs.callLongs(new MoveLineBeanIndexGet(),_str,_args));
//    }

    public static String callMoveLineBeanPowerGet(int _index, int... _args) {
        return dispLine(feedDb(), _index).getPower();
    }

    public static long callMoveLineBeanPpGet(int _index, int... _args) {
        return dispLine(feedDb(), _index).getPp();
    }

    public static long callMoveLineBeanPriorityGet(int _index, int... _args) {
        return dispLine(feedDb(), _index).getPriority();
    }

    public static CustList<String> callMoveLineBeanTypesGet(int _index, int... _args) {
        return (dispLine(feedDb(), _index).getTypes());
    }

    public static String callMovesBeanLearntGet(MovesBean _str, int... _args) {
        return _str.getLearnt().tryRet();
    }

    public static String goToLine(int _index) {
        return moveLine(_index).clickLink( _index);
    }

    public static String goToLineId(int _index) {
        MovesBean moveline_ = moveLine(_index);
        moveline_.clickLink( _index);
        return getValMoveId(moveline_);
    }

    private static MovesBean moveLine(int _index) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<BeanRenderWithAppName> all_ = beanToMoves(pk_);
        return transitToAllMoves(pk_, all_, _index);
    }

    protected static MoveLine dispLine(FacadeGame _fac, int _index) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMoves(pk_);
        MovesBean moves_ = transitToAllMoves(pk_, all_, _index);
        return eltMvLine(callMovesBeanMovesGet(moves_),_index);
    }

    private static MoveLine eltMvLine(CustList<MoveLine> _ls, int _i) {
        return _ls.get(_i);
    }
    protected static MovesBean transitToAllMoves(PkData _pk, StringMap<BeanRenderWithAppName> _all, int _index) {
        WelcomeBean welcome_ = (WelcomeBean) _all.getVal(BeanPokemonCommonTs.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        MovesBean moves_ = (MovesBean) _all.getVal(BEAN_MOVES);
        transit(_pk,new WelcomeBeanSeeAllMoves(welcome_),welcome_,moves_);
        transit(_pk,new MovesBeanSearch(moves_),moves_,moves_);
        return moves_;
    }

    protected static MovesBean dispAllMoves(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        return dispAllMoves(pk_);
    }

    protected static MovesBean dispAllMovesTutors(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        return dispAllMoveslMovesTutors(pk_);
    }

    private static MovesBean dispAllMoves(PkData _pk) {
        StringMap<BeanRenderWithAppName> all_ = beanToMoves(_pk);
        WelcomeBean welcome_ = (WelcomeBean) all_.getVal(BeanPokemonCommonTs.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        MovesBean moves_ = (MovesBean) all_.getVal(BEAN_MOVES);
        transit(_pk,new WelcomeBeanSeeAllMoves(welcome_),welcome_,moves_);
        return moves_;
    }


    private static MovesBean dispAllMoveslMovesTutors(PkData _pk) {
        StringMap<BeanRenderWithAppName> all_ = beanToMoves(_pk);
        WelcomeBean welcome_ = (WelcomeBean) all_.getVal(BeanPokemonCommonTs.BEAN_WELCOME);
        _pk.getDataBase().getData().completeMoveTutors();
        _pk.getDataBase().getData().setView(_pk.getDataBase().getData().computeLearn());
        beforeDisplaying(welcome_);
        MovesBean moves_ = (MovesBean) all_.getVal(BEAN_MOVES);
        transit(_pk,new WelcomeBeanSeeAllMoves(welcome_),welcome_,moves_);
        return moves_;
    }

    protected static MovesBean dispAllMovesSearch(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        MovesBean moves_ = dispAllMoves(pk_);
        transit(pk_,new MovesBeanSearch(moves_),moves_,moves_);
        return moves_;
    }

    protected static String navigateMovesSearch(MovesBean _moves) {
        return navigateData(new MovesBeanSearch(_moves), _moves);
    }

//    private static NaSt displayMoveLine(StringMap<NaSt> _all, int _index, StringMap<String> _mapping) {
//        NaSt moves_ = _all.getVal(AikiBeansMovesStd.BEAN_MOVES);
////        Struct moveline_ = byStr(_all, _mapping, callMovesBeanMovesBeanGet(moves_));
////        fwdLineFull(moveline_, moves_, _index);
////        beforeDisplaying(moveline_);
//        return moves_;
//    }

//    private static void fwdLineFull(Struct _update, Struct _use, int _index) {
//        PokemonStandards.fwd((PokemonBeanStruct) _use, (PokemonBeanStruct) _update);
//        callMoveLineBeanIndexSet(_update,_index);
//        fwdLine(_update, _use,_index);
//    }

//    private static void fwdLine(Struct _update, Struct _use, int _index) {
//        fwdMoveLine(_update, _use,_index);
//        fwdMoveSet(_update, _use);
//    }

//    private static void fwdMoveLine(Struct _update, Struct _use, int _index) {
//        callMoveLineBeanMoveLineSet(_update,elt(callMovesBeanMovesGet(_use),_index));
//    }
//    private static void fwdMoveSet(Struct _update, Struct _use) {
//        callMoveLineBeanSortedMovesSet(_update,callMovesBeanSortedMovesGet(_use));
//    }
//    private static Struct callMovesBeanMovesBeanGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MovesBeanMovesBeanGet(),_str,_args);
//    }

//    private static MoveLineBeanClickMove clickMoveLineBeanMove() {
//        return new MoveLineBeanClickMove();
//    }

//    private static Struct callMoveLineBeanIndexSet(Struct _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new MoveLineBeanIndexSet(),_str,_args);
//    }
    public static MoveLine callMoveLineBeanMoveLineGet(int _index, long... _args) {
        return dispLine(feedDb(), _index);
//        return BeanPokemonCommonTs.callLongs(new MoveLineBeanMoveLineGet(),dispLine(feedDb(), _index),_args);
    }
    public static boolean callMoveLineIsDamageMove(MoveLine _str, int... _args) {
        return _str.isDamageMove();
    }

    public static String callMoveLineCategoryGet(MoveLine _str, int... _args) {
        return _str.getCategory();
    }

    public static String callMoveLineDisplayNameGet(MoveLine _str, int... _args) {
        return _str.getDisplayName();
    }

    public static CustList<String> callMoveLineGetTypes(MoveLine _str, int... _args) {
        return (_str.getTypes());
    }

    public static long callMoveLinePpGet(MoveLine _str, int... _args) {
        return _str.getPp();
    }

    public static long callMoveLinePriorityGet(MoveLine _str, int... _args) {
        return _str.getPriority();
    }

    public static boolean callMoveLineIsDirect(MoveLine _str, int... _args) {
        return _str.isDirect();
    }
    //    private static Struct callMoveLineBeanMoveLineSet(Struct _str, Struct _args) {
//        return BeanPokemonCommonTs.callStruct(new MoveLineBeanMoveLineSet(),_str,_args);
//    }
//    private static Struct callMovesBeanSortedMovesGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MovesBeanSortedMovesGet(),_str,_args);
//    }
//    private static Struct callMoveLineBeanSortedMovesSet(Struct _str, Struct _args) {
//        return BeanPokemonCommonTs.callStruct(new MoveLineBeanSortedMovesSet(),_str,_args);
//    }
    public static StringMap<BeanRenderWithAppName> beanToMoves(PkData _pk) {
        StringMap<BeanRenderWithAppName> map_ = new StringMap<BeanRenderWithAppName>();
        WelcomeBean w_ = beanWelcomeBean(_pk, EN);
        map_.addEntry(BeanPokemonCommonTs.BEAN_WELCOME, w_);
        MovesBean moves_ = new MovesBean();
        moves_.setBuilder(w_.getBuilder());
        initBean(moves_,EN,_pk.getDataBase());
        map_.addEntry(BEAN_MOVES, moves_);
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.MOVES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.MOVES,new TranslationsFile());
        w_.getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MOVES_MOVES_HTML,moves_);
//        map_.addEntry(AikiBeansMovesStd.BEAN_MOVE_LINE,_pk.beanMoveLineBean(EN));
        return map_;
    }
//    public static StringMap<String> mappingToMoves() {
//        StringMap<String> map_ = new StringMap<String>();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,AikiBeansStd.BEAN_WELCOME);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML,AikiBeansMovesStd.BEAN_MOVES);
////        map_.addEntry(AikiBeansMovesStd.WEB_HTML_MOVES_MOVE_LINE_HTML,AikiBeansMovesStd.BEAN_MOVE_LINE);
//        return map_;
//    }

    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = moveDam(TargetChoice.ANY_FOE);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower("1");
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        DamagingMoveData dam2_ = moveDam(TargetChoice.ANY_FOE);
        dam2_.setDirect(false);
        dam2_.setAccuracy(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        EffectDamage ef2_ = Instances.newEffectDamage();
        ef2_.setPower(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        target(dam2_, ef2_);
        facade_.getData().completeMembers(M_DAM_VAR, dam2_);
        MoveData damBad_ = moveDam(TargetChoice.ADJ_ADV);
        EffectStatistic st_ = Instances.newEffectStatistic();
        target(damBad_,st_);
        facade_.getData().completeMembers(M_DAM_BAD, damBad_);
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveDam(TargetChoice.ADJ_UNIQ));
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        DamagingMoveData dam3_ = moveDam(TargetChoice.ANY_FOE);
        dam3_.setAccuracy("7/10");
        EffectDamage ef3_ = Instances.newEffectDamage();
        ef3_.setPower("10");
        target(dam3_, ef3_);
        facade_.getData().completeMembers(M_DAM_POW, dam3_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        facade_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
        facade_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
        facade_.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
        facade_.getData().getTranslatedCategories().getVal(EN).addEntry(AUTRE,C_CAT2_TR);
        facade_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_ITEM,I_ITEM_TR);
        facade_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_WEA,M_WEA_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VERY_BAD,M_DAM_VERY_BAD_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_POW,M_DAM_POW_TR);
        facade_.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POKEMON,P_POKEMON_TR);
        facade_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_REL,S_STA_REL_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_SIM,S_STA_SIM_TR);
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

    private static void target(MoveData _dam, Effect _ef) {
        _ef.setTargetChoice(_dam.getTargetChoice());
        _dam.getEffects().add(_ef);
    }

    protected static MoveBean transitMove(int _index, PkData _pk, StringMap<BeanRenderWithAppName> _all) {
        MovesBean moveline_ = transitToAllMoves(_pk, _all, _index);
        MoveBean mbean_ = (MoveBean) _all.getVal(BEAN_MOVE);
        transit(_pk,new EntityClickFormEvent(moveline_,moveline_.getMoves().get(_index).getTranslatedKey()), moveline_, mbean_);
        return mbean_;
    }

    protected static MoveBean transitMoveQuick(int _index, PkData _pk, StringMap<BeanRenderWithAppName> _all) {
//        return transitMove(_index, _pk, _all, _mapping);
        transitToAllMoves(_pk, _all, _index);
        return (MoveBean) _all.getVal(BEAN_MOVE);
    }
}
