package aiki.beans.endround;

import aiki.beans.*;
import aiki.beans.db.*;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.Combos;
import aiki.fight.abilities.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.status.*;
import aiki.fight.util.ListEffectCombo;
import aiki.instances.*;
import code.bean.nat.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import aiki.comparators.*;
import code.util.*;

public abstract class InitDbEndRound extends InitDbConstr {


    public static final String T_TYPE_ROUND_1 = "T_TYPE1";
    public static final String S_STA_END_ROUND = "S_STA_END_ROUND";
    public static final String T_TYPE_HEAL = "T_TYPE_HEAL";
    public static final String T_TYPE_HEAL_2 = "T_TYPE_HEAL_2";
    public static final String S_STA_DAM = "STA_DAM";
    public static final String S_STA_RELATION = "S_STA_RELATION";
    public static final String M_END_1="M_END_1";
    public static final String M_END_2="M_END_2";
    public static final String M_END_3="M_END_3";
    public static final String M_END_4="M_END_4";
    public static final String M_END_5="M_END_5";
    public static final String M_END_6="M_END_6";
    public static final String M_END_7="M_END_7";
    public static final String M_END_8="M_END_8";
    public static final String I_END_9="I_END_9";
    public static final String I_END_10="I_END_10";
    public static final String I_END_11="I_END_11";
    public static final String I_END_12="I_END_12";
    public static final String A_END_13="A_END_13";
    public static final String A_END_14="A_END_14";
    public static final String A_END_15="A_END_15";
    public static final String A_END_16="A_END_16";
    public static final String S_END_17="S_END_17";
    public static final String S_END_18="S_END_18";
    public static final String S_END_19="S_END_19";
    public static final String S_END_20="S_END_20";
    public static final String S_END_21="S_END_21";
    public static final String S_END_22="S_END_22";
    public static final String M_END_23="M_END_23";
    public static final String M_END_24="M_END_24";
    public static final String M_END_25="M_END_25";
    public static final String M_END_26="M_END_26";
    public static final String M_END_27="M_END_27";
    public static final String M_END_28="M_END_28";
    public static final String M_END_29="M_END_29";
    public static final String M_END_30="M_END_30";
    public static final String T_TYPE_ROUND_1_TR="T_TYPE1_TR";
    public static final String S_STA_END_ROUND_TR="S_STA_END_ROUND_TR";
    public static final String T_TYPE_HEAL_TR="T_TYPE_HEAL_TR";
    public static final String T_TYPE_HEAL_2_TR="T_TYPE_HEAL_TR_2";
    public static final String S_STA_DAM_TR="STA_DAM_TR";
    public static final String S_STA_RELATION_TR="S_STA_RELATION_TR";
    public static final String M_END_1_TR="M_END_1_TR";
    public static final String M_END_2_TR="M_END_2_TR";
    public static final String M_END_3_TR="M_END_3_TR";
    public static final String M_END_4_TR="M_END_4_TR";
    public static final String M_END_5_TR="M_END_5_TR";
    public static final String M_END_6_TR="M_END_6_TR";
    public static final String M_END_7_TR="M_END_7_TR";
    public static final String M_END_8_TR="M_END_8_TR";
    public static final String I_END_9_TR="I_END_9_TR";
    public static final String I_END_10_TR="I_END_10_TR";
    public static final String I_END_11_TR="I_END_11_TR";
    public static final String I_END_12_TR="I_END_12_TR";
    public static final String A_END_13_TR="A_END_13_TR";
    public static final String A_END_14_TR="A_END_14_TR";
    public static final String A_END_15_TR="A_END_15_TR";
    public static final String A_END_16_TR="A_END_16_TR";
    public static final String S_END_17_TR="S_END_17_TR";
    public static final String S_END_18_TR="S_END_18_TR";
    public static final String S_END_19_TR="S_END_19_TR";
    public static final String S_END_20_TR="S_END_20_TR";
    public static final String S_END_21_TR="S_END_21_TR";
    public static final String S_END_22_TR="S_END_22_TR";
    public static final String M_END_23_TR="M_END_23_TR";
    public static final String M_END_24_TR="M_END_24_TR";
    public static final String M_END_25_TR="M_END_25_TR";
    public static final String M_END_26_TR="M_END_26_TR";
    public static final String M_END_27_TR="M_END_27_TR";
    public static final String M_END_28_TR="M_END_28_TR";
    public static final String M_END_29_TR="M_END_29_TR";
    public static final String M_END_30_TR="M_END_30_TR";
    public static final String BEAN_ENDROUND="endround";

    public static String callEffectEndRoundBeanAbilityGet(int _ev) {
        return dispEndRound(_ev).getAbility();
    }

    public static String callEffectEndRoundBeanClickAbility(int _ev) {
        return callEffectEndRoundBeanClickAbility(dispEndRound(_ev));
    }

    public static String callEffectEndRoundBeanClickAbility(EffectEndRoundBean _str) {
        return _str.clickAbility();
    }

    public static String callEffectEndRoundBeanClickAbilityId(int _ev) {
        EffectEndRoundBean bean_ = dispEndRound(_ev);
        callEffectEndRoundBeanClickAbility(bean_);
        return getValAbilityId(bean_);
    }

    public static String callEffectEndRoundBeanClickItem(int _ev) {
        return callEffectEndRoundBeanClickItem(dispEndRound(_ev));
    }

    public static String callEffectEndRoundBeanClickItem(EffectEndRoundBean _str) {
        return _str.clickItem();
    }

    public static String callEffectEndRoundBeanClickItemId(int _ev) {
        EffectEndRoundBean bean_ = dispEndRound(_ev);
        callEffectEndRoundBeanClickItem(bean_);
        return getValItemId(bean_);
    }
    public static String callEffectEndRoundBeanClickMove(int _ev) {
        return callEffectEndRoundBeanClickMove(dispEndRound(_ev));
    }

    public static String callEffectEndRoundBeanClickMove(EffectEndRoundBean _str) {
        return _str.clickMove();
    }
    public static String callEffectEndRoundBeanClickMoveId(int _ev) {
        EffectEndRoundBean bean_ = dispEndRound(_ev);
        callEffectEndRoundBeanClickMove(bean_);
        return getValMoveId(bean_);
    }
    public static String callEffectEndRoundBeanClickMoves(int _ev, int _ind) {
        return callEffectEndRoundBeanClickMoves(dispEndRound(_ev),_ind);
    }

    public static String callEffectEndRoundBeanClickMoves(EffectEndRoundBean _str, int _ind) {
        return _str.clickMoves(_ind);
    }
    public static String callEffectEndRoundBeanClickMovesId(int _ev, int _ind) {
        EffectEndRoundBean bean_ = dispEndRound(_ev);
        callEffectEndRoundBeanClickMoves(bean_,_ind);
        return getValMoveId(bean_);
    }

    public static String callEffectEndRoundBeanClickStatus(int _ev) {
        return callEffectEndRoundBeanClickStatus(dispEndRound(_ev));
    }

    public static String callEffectEndRoundBeanClickStatus(EffectEndRoundBean _str) {
        return _str.clickStatus();
    }

    public static String callEffectEndRoundBeanClickStatusId(int _ev) {
        EffectEndRoundBean bean_ = dispEndRound(_ev);
        callEffectEndRoundBeanClickStatus(bean_);
        return getValStatusId(bean_);
    }
//    public static Struct callEffectEndRoundBeanEndRoundHtmlGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanEndRoundHtmlGet(),_str,_args);
//    }

    public static long callEffectEndRoundBeanEndRoundRankGet(int _ev) {
        return dispEndRound(_ev).getEndRoundRank();
    }

    public static String callEffectEndRoundBeanGetTrMoves(int _ev, int _ind) {
        return dispEndRound(_ev).getTrMoves(_ind);
    }

//    public static NaSt callEffectEndRoundBeanIndexGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanIndexGet(),_str,_args);
//    }

    public static String callEffectEndRoundBeanItemGet(int _ev) {
        return dispEndRound(_ev).getItem();
    }

    public static AbsMap<String,String> callEffectEndRoundBeanMapVarsFailEndRoundGet(int _ev) {
        return dispEndRound(_ev).getMapVarsFailEndRound();
    }

    public static String callEffectEndRoundBeanMoveGet(int _ev) {
        return dispEndRound(_ev).getMove();
    }

    public static CustList<String> callEffectEndRoundBeanMovesGet(int _ev) {
        return dispEndRound(_ev).getMoves();
    }

    public static CustList<String> callEffectEndRoundBeanReasonsEndRoundGet(int _ev) {
        return dispEndRound(_ev).getReasonsEndRound();
    }

    public static String callEffectEndRoundBeanStatusGet(int _ev) {
        return dispEndRound(_ev).getStatus();
    }

    public static int callEndRoundBeanGetEvts(NaSt _str, int... _args) {
        return ( (EndRoundBean) ((PokemonBeanStruct)_str).getInstance()).getEvts().size();
    }

//    public static NaSt callEndRoundBeanGetPage(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EndRoundBeanGetPage(),_str,_args);
//    }
//    public static NaSt callEffectEndRoundBeanIndexSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new EffectEndRoundBeanIndexSet(),_str,_args);
//    }

    public static Rate callEffectEndRoundFoeBeanInflictedRateHpTargetGet(int _ev) {
        return ((EffectEndRoundFoeBean) dispEndRound(_ev)).getInflictedRateHpTarget();
    }

    public static Rate callEffectEndRoundGlobalBeanDamageEndRoundGet(int _ev) {
        return ((EffectEndRoundGlobalBean) dispEndRound(_ev)).getDamageEndRound();
    }

    public static Rate callEffectEndRoundGlobalBeanHealingEndRoundGet(int _ev) {
        return ((EffectEndRoundGlobalBean) dispEndRound(_ev)).getHealingEndRound();
    }

    public static Rate callEffectEndRoundGlobalBeanHealingEndRoundGroundGet(int _ev) {
        return ((EffectEndRoundGlobalBean) dispEndRound(_ev)).getHealingEndRoundGround();
    }

    public static CustList<TranslatedKey> callEffectEndRoundGlobalBeanImmuneTypesGet(int _ev) {
        return ((EffectEndRoundGlobalBean) dispEndRound(_ev)).getImmuneTypes();
    }

    public static boolean callEffectEndRoundGlobalBeanPuttingKoGet(int _ev) {
        return ((EffectEndRoundGlobalBean) dispEndRound(_ev)).getPuttingKo();
    }

    public static String callEffectEndRoundIndividualBeanClickDamageStatus(int _ev, int _ind) {
        return callEffectEndRoundIndividualBeanClickDamageStatus(dispEndRound(_ev),_ind);
    }

    public static String callEffectEndRoundIndividualBeanClickDamageStatus(EffectEndRoundBean _str, int _ind) {
        return ((EffectEndRoundIndividualBean) _str).clickDamageStatus(_ind);
    }

    public static String callEffectEndRoundIndividualBeanClickDamageStatusId(int _ev, int _ind) {
        EffectEndRoundBean bean_ = dispEndRound(_ev);
        callEffectEndRoundIndividualBeanClickDamageStatus(bean_,_ind);
        return getValStatusId(bean_);
    }

    public static String callEffectEndRoundIndividualBeanClickUserStatus(int _ev) {
        return callEffectEndRoundIndividualBeanClickUserStatus(dispEndRound(_ev));
    }

    public static String callEffectEndRoundIndividualBeanClickUserStatus(EffectEndRoundBean _str) {
        return ((EffectEndRoundIndividualBean) _str).clickUserStatus();
    }

    public static String callEffectEndRoundIndividualBeanClickUserStatusId(int _ev) {
        EffectEndRoundBean bean_ = dispEndRound(_ev);
        callEffectEndRoundIndividualBeanClickUserStatus(bean_);
        return getValStatusId(bean_);
    }
    public static Rate callEffectEndRoundIndividualBeanDeleteAllStatusGet(int _ev) {
        return ((EffectEndRoundIndividualBean) dispEndRound(_ev)).getDeleteAllStatus();
    }

    public static String callEffectEndRoundIndividualBeanGetTrDamageStatus(int _ev, int _ind) {
        return ((EffectEndRoundIndividualBean) dispEndRound(_ev)).getTrDamageStatus(_ind);
    }

    public static String callEffectEndRoundIndividualBeanGetTrType(int _ev, int _ind) {
        return ((EffectEndRoundIndividualBean) dispEndRound(_ev)).getTrType(_ind);
    }

    public static String callEffectEndRoundIndividualBeanGetTrUserStatus(int _ev) {
        return ((EffectEndRoundIndividualBean) dispEndRound(_ev)).getTrUserStatus();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectEndRoundIndividualBeanHealHpByOwnerTypesGet(int _ev) {
        return ((EffectEndRoundIndividualBean) dispEndRound(_ev)).getHealHpByOwnerTypes();
    }

    public static Rate callEffectEndRoundIndividualBeanHealHpGet(int _ev) {
        return ((EffectEndRoundIndividualBean) dispEndRound(_ev)).getHealHp();
    }

    public static boolean callEffectEndRoundIndividualBeanIsType(int _ev,int _ind) {
        return ((EffectEndRoundIndividualBean) dispEndRound(_ev)).isType(_ind);
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectEndRoundIndividualBeanMultDamageStatusGet(int _ev) {
        return ((EffectEndRoundIndividualBean) dispEndRound(_ev)).getMultDamageStatus();
    }

    public static Rate callEffectEndRoundIndividualBeanRecoilDamageGet(int _ev) {
        return ((EffectEndRoundIndividualBean) dispEndRound(_ev)).getRecoilDamage();
    }

    public static String callEffectEndRoundIndividualBeanUserStatusEndRoundGet(int _ev) {
        return ((EffectEndRoundIndividualBean) dispEndRound(_ev)).getUserStatusEndRound();
    }

    public static String callEffectEndRoundMultiRelationBeanClickDamageStatus(int _ev, int _ind) {
        EffectEndRoundBean bean_ = dispEndRound(_ev);
        return callEffectEndRoundMultiRelationBeanClickDamageStatus(bean_,_ind);
    }

    public static String callEffectEndRoundMultiRelationBeanClickDamageStatusId(int _ev, int _ind) {
        EffectEndRoundBean bean_ = dispEndRound(_ev);
        callEffectEndRoundMultiRelationBeanClickDamageStatus(bean_,_ind);
        return getValStatusId(bean_);
    }

    public static String callEffectEndRoundMultiRelationBeanClickDamageStatus(EffectEndRoundBean _str, int _ind) {
        return ((EffectEndRoundMultiRelationBean) _str).clickDamageStatus(_ind);
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectEndRoundMultiRelationBeanDamageByStatusGet(int _ev) {
        return ((EffectEndRoundMultiRelationBean) dispEndRound(_ev)).getDamageByStatus();
    }

    public static String callEffectEndRoundMultiRelationBeanGetTrDamageStatus(int _ev, int _ind) {
        return ((EffectEndRoundMultiRelationBean) dispEndRound(_ev)).getTrDamageStatus(_ind);
    }

    public static Rate callEffectEndRoundPositionRelationBeanHealHpGet(int _ev){
        return ((EffectEndRoundPositionRelationBean) dispEndRound(_ev)).getHealHp();
    }

    public static String callEffectEndRoundPositionTargetBeanClickTargetRelationMove(int _ev, int _ind) {
        return callEffectEndRoundPositionTargetBeanClickTargetRelationMove(dispEndRound(_ev),_ind);
    }

    public static String callEffectEndRoundPositionTargetBeanClickTargetRelationMove(EffectEndRoundBean _str, int _ind) {
        return ((EffectEndRoundPositionTargetBean) _str).clickTargetRelationMove(_ind);
    }

    public static String callEffectEndRoundPositionTargetBeanClickTargetRelationMoveId(int _ev, int _ind) {
        EffectEndRoundBean bean_ = dispEndRound(_ev);
        callEffectEndRoundPositionTargetBeanClickTargetRelationMove(bean_,_ind);
        return getValMoveId(bean_);
    }
    public static CustList<TranslatedKey> callEffectEndRoundPositionTargetBeanGetMovesSameCategory(int _ev) {
        return ((EffectEndRoundPositionTargetBean) dispEndRound(_ev)).getMovesSameCategory();
    }

    public static String callEffectEndRoundPositionTargetBeanGetTrTargetRelationMove(int _ev, int _ind) {
        return ((EffectEndRoundPositionTargetBean) dispEndRound(_ev)).getTrTargetRelationMove(_ind);
    }

    public static AbsMap<LgInt,Rate> callEffectEndRoundSingleRelationBeanLawForEnablingEffectGet(int _ev) {
        return ((EffectEndRoundSingleRelationBean) dispEndRound(_ev)).getLawForEnablingEffect();
    }

    public static AbsMap<Long,Rate> callEffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet(int _ev) {
        return ((EffectEndRoundSingleRelationBean) dispEndRound(_ev)).getRateDamageFunctionOfNbRounds();
    }
//
//    public static Struct callEffectEndRoundStatusBeanEndRoundStatusHtmlGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EffectEndRoundStatusBeanEndRoundStatusHtmlGet(),_str,_args);
//    }

    public static Rate callEffectEndRoundStatusBeanInflictedRateHpTargetGet(int _ev) {
        return ((EffectEndRoundStatusBean) dispEndRound(_ev)).getInflictedRateHpTarget();
    }

    public static Rate callEffectEndRoundStatusRelationBeanThievedHpRateTargetToUserGet(int _ev) {
        return ((EffectEndRoundStatusRelationBean) dispEndRound(_ev)).getThievedHpRateTargetToUser();
    }

    public static Rate callEffectEndRoundTeamBeanDeleteAllStatusAllyGet(int _ev) {
        return ((EffectEndRoundTeamBean) dispEndRound(_ev)).getDeleteAllStatusAlly();
    }

    public static Rate callEffectEndRoundTeamBeanDeleteAllStatusGet(int _ev) {
        return ((EffectEndRoundTeamBean) dispEndRound(_ev)).getDeleteAllStatus();
    }
    protected static int dispEndRoundEvts() {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<NaSt> all_ = beanToEndRound(pk_);
        return callEndRoundBeanGetEvts(transitToAllPks(all_));
    }
    protected static EffectEndRoundBean dispEndRound(int _index) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<NaSt> all_ = beanToEndRound(pk_);
        NaSt pkbean_ = transitToAllPks(all_);
//        NaSt evobean_ = new PokemonBeanStruct(new EffectEndRoundBean());
//        callEndRoundBeanGetPage(pkbean_, _index);
////        NaSt evobean_ = byStr(all_, mapping_, callEndRoundBeanGetPage(pkbean_, _index));
//        setFormsBy(pk_,evobean_,pkbean_);
//        callEffectEndRoundBeanIndexSet(evobean_,_index);
//        beforeDisplaying(evobean_);
        return ((EndRoundBean)((PokemonBeanStruct)pkbean_).getBean()).getEffects().get(_index);
    }

    protected static NaSt transitToAllPks(StringMap<NaSt> _all) {
        NaSt welcome_ = _all.getVal(BEAN_ENDROUND);
        MockBeanBuilderHelper bu_ = new MockBeanBuilderHelper();
        Translations tr_ = new Translations();
        TranslationsLg en_ = new TranslationsLg();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, new TranslationsAppli());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.INDEX,new TranslationsFile());
        tr_.getMapping().addEntry(EN, en_);
        TranslationsLg fr_ = new TranslationsLg();
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, new TranslationsAppli());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.INDEX,new TranslationsFile());
        tr_.getMapping().addEntry(FR, fr_);
        bu_.setTranslations(tr_);
        bu_.setFacade(((BeanRenderWithAppName) ((PokemonBeanStruct)welcome_).getBean()).getFacade());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).setBuilder(bu_);
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_GLOBAL,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_EVENT,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_FOE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_INDIVIDUAL,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_MULTIRELATION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_POSITIONRELATION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_POSITIONTARGET,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_SINGLERELATION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_STATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_STATUSRELATION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_TEAM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_GLOBAL,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_EVENT,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_FOE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_INDIVIDUAL,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_MULTIRELATION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_POSITIONRELATION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_POSITIONTARGET,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_SINGLERELATION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_STATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_STATUSRELATION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)welcome_).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ENDROUND_TEAM,new TranslationsFile());
        ((BeanRenderWithAppName)((PokemonBeanStruct)welcome_).getBean()).setBuilder(bu_);
        ((BeanRenderWithAppName)((PokemonBeanStruct)welcome_).getBean()).build(((BeanRenderWithAppName) ((PokemonBeanStruct)welcome_).getBean()).getFacade());
//        beforeDisplaying(welcome_);
        return welcome_;
    }
    public static StringMap<NaSt> beanToEndRound(PkData _pk) {
        StringMap<NaSt> map_ = new StringMap<NaSt>();
        map_.addEntry(BEAN_ENDROUND,_pk.bean(new EndRoundBean(),EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END,_pk.beanEffectEndRoundBean(EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END_FOE,_pk.beanEffectEndRoundFoeBean(EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END_GLOBAL,_pk.beanEffectEndRoundGlobalBean(EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END_INDIVIDUAL,_pk.beanEffectEndRoundIndividualBean(EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END_MULTIRELATION,_pk.beanEffectEndRoundMultiRelationBean(EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END_POSITIONRELATION,_pk.beanEffectEndRoundPositionRelationBean(EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END_POSITIONTARGET,_pk.beanEffectEndRoundPositionTargetBean(EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END_SINGLERELATION,_pk.beanEffectEndRoundSingleRelationBean(EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END_STATUS,_pk.beanEffectEndRoundSingleStatusBean(EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END_STATUSRELATION,_pk.beanEffectEndRoundStatusRelationBean(EN));
//        map_.addEntry(AikiBeansEndroundStd.BEAN_END_TEAM,_pk.beanEffectEndRoundTeamBean(EN));
        return map_;
    }
    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_END_1,incr(1));
        facade_.getData().completeMembers(M_END_2,incr(2));
        facade_.getData().completeMembers(M_END_3,endRoundGlobal(true, 3));
        facade_.getData().completeMembers(M_END_4,endRoundGlobal(false, 4));
        facade_.getData().completeMembers(M_END_5,moveEndPositionTargetRelation(5));
        facade_.getData().completeMembers(M_END_6,moveEndPositionTargetRelation(6));
        facade_.getData().completeMembers(M_END_7,endRound(individual(7)));
        facade_.getData().completeMembers(M_END_8,endRound(individual(8)));
        facade_.getData().completeMembers(I_END_9,item(multRelation(9)));
        facade_.getData().completeMembers(I_END_10,item(multRelation(10)));
        facade_.getData().completeMembers(I_END_11,item(multPosRelation(11)));
        facade_.getData().completeMembers(I_END_12,item(multPosRelation(12)));
        facade_.getData().completeMembers(A_END_13,ability(singlePosRelation(13)));
        facade_.getData().completeMembers(A_END_14,ability(singlePosRelation(14)));
        facade_.getData().completeMembers(A_END_15,ability(team(15)));
        facade_.getData().completeMembers(A_END_16,ability(team(16)));
        facade_.getData().completeMembers(S_END_17,simpleStatus(true,17,StatusType.INDIVIDUEL));
        facade_.getData().completeMembers(S_END_18,simpleStatus(false,18,StatusType.RELATION_UNIQUE));
        facade_.getData().completeMembers(S_END_19,simpleStatus(statRel(20),true,19,StatusType.INDIVIDUEL));
        facade_.getData().completeMembers(S_END_20,simpleStatus(statRel(22),false,21,StatusType.RELATION_UNIQUE));
        facade_.getData().completeMembers(S_END_21,simpleStatus(statSimple(true,24),true,23,StatusType.INDIVIDUEL));
        facade_.getData().completeMembers(S_END_22,simpleStatus(statSimple(false,26),false,25,StatusType.RELATION_UNIQUE));
        facade_.getData().completeMembers(M_END_23,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_END_24,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_END_25,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_END_26,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_END_27,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_END_28,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_END_29,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_END_30,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(S_STA_END_ROUND,staSimple(NULL_REF));
        facade_.getData().completeMembers(S_STA_DAM,staSimple(NULL_REF));
        facade_.getData().completeMembers(S_STA_RELATION,staSimple(NULL_REF));
        Combos co_ = Instances.newCombos();
        co_.getEffects().add(new ListEffectCombo(new StringList(M_END_23,M_END_24), combo(27)));
        co_.getEffects().add(new ListEffectCombo(new StringList(M_END_25,M_END_26), combo(28)));
        co_.getEffects().add(new ListEffectCombo(new StringList(M_END_27,M_END_28), comboFoe(29,30)));
        co_.getEffects().add(new ListEffectCombo(new StringList(M_END_29,M_END_30), comboFoe(31,32)));
        facade_.getData().setCombos(co_);
        facade_.getData().completeMembersCombos();
        facade_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        facade_.getData().completeVariables();
        facade_.getData().sortEndRound();
        facade_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedAbilities().getVal(EN).addEntry(A_END_13,A_END_13_TR);
        facade_.getData().getTranslatedAbilities().getVal(EN).addEntry(A_END_14,A_END_14_TR);
        facade_.getData().getTranslatedAbilities().getVal(EN).addEntry(A_END_15,A_END_15_TR);
        facade_.getData().getTranslatedAbilities().getVal(EN).addEntry(A_END_16,A_END_16_TR);
        facade_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_END_9,I_END_9_TR);
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_END_10,I_END_10_TR);
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_END_11,I_END_11_TR);
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_END_12,I_END_12_TR);
        facade_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_1,M_END_1_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_2,M_END_2_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_3,M_END_3_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_4,M_END_4_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_5,M_END_5_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_6,M_END_6_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_7,M_END_7_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_8,M_END_8_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_23,M_END_23_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_24,M_END_24_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_25,M_END_25_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_26,M_END_26_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_27,M_END_27_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_28,M_END_28_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_29,M_END_29_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_END_30,M_END_30_TR);
        facade_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_END_ROUND,S_STA_END_ROUND_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_DAM,S_STA_DAM_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_RELATION,S_STA_RELATION_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_END_17,S_END_17_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_END_18,S_END_18_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_END_19,S_END_19_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_END_20,S_END_20_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_END_21,S_END_21_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_END_22,S_END_22_TR);
        facade_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE_ROUND_1,T_TYPE_ROUND_1_TR);
        facade_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE_HEAL,T_TYPE_HEAL_TR);
        facade_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE_HEAL_2,T_TYPE_HEAL_2_TR);
        return facade_;
    }
    private static MoveData incr(int _rank) {
        MoveData m_ = Instances.newDamagingMoveData();
        m_.setRankIncrementNbRound( _rank);
        return m_;
    }

    private static MoveData moveEndPositionTargetRelation(int _rk) {
        MoveData m_ = Instances.newDamagingMoveData();
        m_.getEffects().add(Instances.newEffectDamage());
        EffectEndRoundPositionTargetRelation end_ = Instances.newEffectEndRoundPositionTargetRelation();
        end_.setEndRoundRank(_rk);
        m_.getEffects().add(end_);
        return m_;
    }

    private static MoveData endRound(EffectEndRound _endRound) {
        MoveData m_ = Instances.newDamagingMoveData();
        m_.getEffects().add(_endRound);
        return m_;
    }

    private static MoveData endRoundGlobal(boolean _puttingKo, int _rk) {
        MoveData m_ = Instances.newDamagingMoveData();
        EffectGlobal gl_ = Instances.newEffectGlobal();
        gl_.setDamageEndRound(Rate.one());
        gl_.setHealingEndRoundGround(Rate.one());
        gl_.setHealingEndRound(Rate.one());
        gl_.setPuttingKo(_puttingKo);
        gl_.getImmuneTypes().add(T_TYPE_ROUND_1);
        m_.getEffects().add(gl_);
        EffectEndRoundGlobal c_ = Instances.newEffectEndRoundGlobal();
        c_.setEndRoundRank(_rk);
        m_.getEffects().add(c_);
        return m_;
    }
    private static ItemForBattle item(EffectEndRound _endRound) {
        ItemForBattle it_ = Instances.newItemForBattle();
        it_.getEffectEndRound().add(_endRound);
        return it_;
    }
    private static AbilityData ability(EffectEndRound _endRound) {
        AbilityData it_ = Instances.newAbilityData();
        it_.getEffectEndRound().add(_endRound);
        return it_;
    }
    private static StatusSimple simpleStatus(boolean _incrementingEndRound, int _incrementEndRound, StatusType _relation) {
        StatusSimple st_ = Instances.newStatusSimple();
        st_.setIncrementingEndRound(_incrementingEndRound);
        st_.setIncrementEndRound(_incrementEndRound);
        st_.setStatusType(_relation);
        return st_;
    }
    private static StatusSimple simpleStatus(EffectEndRoundStatus _endRound, boolean _incrementingEndRound, int _incrementEndRound, StatusType _relation) {
        StatusSimple st_ = Instances.newStatusSimple();
        st_.getEffectEndRound().add(_endRound);
        st_.setIncrementingEndRound(_incrementingEndRound);
        st_.setIncrementEndRound(_incrementEndRound);
        st_.setStatusType(_relation);
        return st_;
    }
    private static EffectCombo combo(int _rank) {
        EffectCombo ec_ = Instances.newEffectCombo();
        ec_.setRankIncrementNbRound(_rank);
        return ec_;
    }

    private static EffectCombo comboFoe(int _incr, int _act) {
        EffectCombo ec_ = Instances.newEffectCombo();
        ec_.setRankIncrementNbRound(_incr);
        ec_.getEffectEndRound().add(foe(_act));
        return ec_;
    }
    private static EffectEndRoundFoe foe(int _rk) {
        EffectEndRoundFoe e_ = Instances.newEffectEndRoundFoe();
        e_.setEndRoundRank(_rk);
        e_.setInflictedRateHpTarget(Rate.one());
        return e_;
    }
    private static EffectEndRoundIndividual individual(int _rk) {
        EffectEndRoundIndividual e_ = Instances.newEffectEndRoundIndividual();
        e_.setFailEndRound(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e_.setEndRoundRank(_rk);
        e_.setDeleteAllStatus(Rate.one());
        e_.setRecoilDamage(Rate.one());
        e_.setHealHp(Rate.one());
        e_.setUserStatusEndRound(S_STA_END_ROUND);
        e_.getHealHpByOwnerTypes().addEntry(T_TYPE_HEAL,Rate.one());
        e_.getHealHpByOwnerTypes().addEntry(NULL_REF,Rate.newRate("2"));
        e_.getHealHpByOwnerTypes().addEntry(T_TYPE_HEAL_2,Rate.newRate("-2"));
        e_.getMultDamageStatus().addEntry(S_STA_DAM,Rate.one());
        return e_;
    }
    private static EffectEndRoundMultiRelation multRelation(int _rk) {
        EffectEndRoundMultiRelation e_ = Instances.newEffectEndRoundMultiRelation();
        e_.setEndRoundRank(_rk);
        e_.getDamageByStatus().addEntry(S_STA_RELATION,Rate.one());
        return e_;
    }
    private static EffectEndRoundPositionRelation multPosRelation(int _rk) {
        EffectEndRoundPositionRelation e_ = Instances.newEffectEndRoundPositionRelation();
        e_.setEndRoundRank(_rk);
        e_.setHealHp(Rate.one());
        return e_;
    }
    private static EffectEndRoundSingleRelation singlePosRelation(int _rk) {
        EffectEndRoundSingleRelation e_ = Instances.newEffectEndRoundSingleRelation();
        e_.setEndRoundRank(_rk);
        e_.getRateDamageFunctionOfNbRounds().addEntry(1L,Rate.one());
        e_.getRateDamageFunctionOfNbRounds().addEntry(3L,Rate.newRate("5"));
        e_.getLawForEnablingEffect().addQuickEvent(Rate.newRate("5"), LgInt.newLgInt("15"));
        e_.getLawForEnablingEffect().addQuickEvent(Rate.newRate("7"), LgInt.newLgInt("12"));
        return e_;
    }
    private static EffectEndRoundTeam team(int _rk) {
        EffectEndRoundTeam e_ = Instances.newEffectEndRoundTeam();
        e_.setEndRoundRank(_rk);
        e_.setDeleteAllStatus(Rate.one());
        e_.setDeleteAllStatusAlly(Rate.one());
        return e_;
    }
    private static EffectEndRoundStatusRelation statRel(int _rk) {
        EffectEndRoundStatusRelation e_ = Instances.newEffectEndRoundStatusRelation();
        e_.setEndRoundRank(_rk);
        e_.setInflictedRateHpTarget(Rate.one());
        e_.setThievedHpRateTargetToUser(Rate.one());
        return e_;
    }
    private static EffectEndRoundSingleStatus statSimple(boolean _incr, int _rk) {
        EffectEndRoundSingleStatus e_ = Instances.newEffectEndRoundSingleStatus();
        e_.setEndRoundRank(_rk);
        e_.setInflictedRateHpTarget(Rate.one());
        e_.setIncrementingDamageByRounds(_incr);
        return e_;
    }
}