package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringMap;

public abstract class InitDbMoveEffectDamage extends InitDbMoveEffect{

    public static NaSt callEffectDamageBeanBoostStatisOnceKoFoeGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrLongVal(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getBoostStatisOnceKoFoe());
    }

    public static NaSt callEffectDamageBeanChLawGet(NaSt _str, int... _args) {
        return PokemonStandards.getRateRate(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getChLaw());
    }

    public static NaSt callEffectDamageBeanChRateGet(NaSt _str, int... _args) {
        return new NaNbSt(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getChRate());
    }

    public static NaSt callEffectDamageBeanConstDamageGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getConstDamage());
    }

    public static NaSt callEffectDamageBeanConstPower(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).constPower());
    }

    public static NaSt callEffectDamageBeanCounterDamageCat(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).counterDamageCat());
    }

    public static NaSt callEffectDamageBeanDamageLawGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrRate(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getDamageLaw());
    }

    public static String callEffectDamageBeanGetTranslatedStatisKo(NaSt _str, int... _args) {
        return ( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getTranslatedStatisKo(_args[0]);
    }

    public static String callEffectDamageBeanGetTranslatedStatisTarget(NaSt _str, int... _args) {
        return ( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getTranslatedStatisTarget(_args[0]);
    }

    public static String callEffectDamageBeanGetTranslatedStatisUser(NaSt _str, int... _args) {
        return ( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getTranslatedStatisUser(_args[0]);
    }

    public static NaSt callEffectDamageBeanHasConstPower(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).hasConstPower());
    }

    public static NaSt callEffectDamageBeanHasDeterminatedLawForDamage(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).hasDeterminatedLawForDamage());
    }

    public static NaSt callEffectDamageBeanHasLawForDamage(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).hasLawForDamage());
    }

    public static NaSt callEffectDamageBeanHitsLawGet(NaSt _str, int... _args) {
        return PokemonStandards.getLongRate(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getHitsLaw());
    }

    public static NaSt callEffectDamageBeanIgnVarStatTargetPosGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getIgnVarStatTargetPos());
    }

    public static NaSt callEffectDamageBeanIgnVarStatUserNegGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getIgnVarStatUserNeg());
    }

    public static NaSt callEffectDamageBeanMapVarsDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getMapVarsDamage());
    }

    public static NaSt callEffectDamageBeanMultDamageAgainstGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrRateVal(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getMultDamageAgainst());
    }

    public static NaSt callEffectDamageBeanNbHitsGet(NaSt _str, int... _args) {
        return new NaNbSt(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getNbHits());
    }

    public static String callEffectDamageBeanPowerGet(NaSt _str, int... _args) {
        return ( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getPower();
    }

    public static NaSt callEffectDamageBeanRandMaxGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getRandMax());
    }

    public static String callEffectDamageBeanStatisAttGet(NaSt _str, int... _args) {
        return ( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getStatisAtt();
    }

    public static String callEffectDamageBeanStatisDefGet(NaSt _str, int... _args) {
        return ( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getStatisDef();
    }

    public static NaSt callEffectDamageBeanSummingUserTeamOkFighterGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getSummingUserTeamOkFighter());
    }

    public static NaSt callEffectDamageBeanTargetDefenseGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getTargetDefense());
    }

    public static NaSt callEffectDamageBeanUserAttackGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectDamageBean) ((PokemonBeanStruct)_str).getInstance()).getUserAttack());
    }

    protected static FacadeGame feedDbMoveEffDataDamComp(EffectDamage _dam) {
        FacadeGame f_ = feedDbMoveEffDataDam(TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, true, true, true, _dam);
        f_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        return f_;
    }
    protected static FacadeGame feedDbMoveEffDataDam(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, EffectDamage _efDam) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        target(dam_, _efDam);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }

    protected static EffectDamage effDam(String _power, boolean _targetDefense, boolean _userAttack, int _ch, boolean _summingUserTeamOkFighter, boolean _randMax, boolean _constDamage) {
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        ef_.setTargetDefense(_targetDefense);
        ef_.setUserAttack(_userAttack);
        ef_.setChRate(_ch);
        ef_.setStatisAtt(Statistic.ATTACK);
        ef_.setStatisDef(Statistic.DEFENSE);
        ef_.setSummingUserTeamOkFighter(_summingUserTeamOkFighter);
        ef_.setDamageLaw(new MonteCarloString());
        ef_.setMultDamageAgainst(new StringMap<Rate>());
        ef_.setRandMax(_randMax);
        ef_.setConstDamage(_constDamage);
        ef_.setChLaw(new MonteCarloNumber());
        ef_.setHitsLaw(new MonteCarloNumber());
        IdMap<Statistic,Long> b_ = new IdMap<Statistic,Long>();
        b_.addEntry(Statistic.SPEED,1L);
        ef_.setBoostStatisOnceKoFoe(b_);
        IdList<Statistic> d_ = new IdList<Statistic>();
        d_.add(Statistic.DEFENSE);
        ef_.setIgnVarStatTargetPos(d_);
        IdList<Statistic> a_ = new IdList<Statistic>();
        a_.add(Statistic.ATTACK);
        ef_.setIgnVarStatUserNeg(a_);
        return ef_;
    }
    protected static EffectDamage withDamageLawEv(EffectDamage _eff, String _ev, LgInt _freq) {
        _eff.getDamageLaw().addQuickEvent(_ev,_freq);
        return _eff;
    }
    protected static EffectDamage withHitLawEv(EffectDamage _eff, Rate _ev, LgInt _freq) {
        _eff.getHitsLaw().addQuickEvent(_ev,_freq);
        return _eff;
    }
    protected static EffectDamage withChLawEv(EffectDamage _eff, Rate _ev, LgInt _freq) {
        _eff.getChLaw().addQuickEvent(_ev,_freq);
        return _eff;
    }
    protected static EffectDamage withMultDamageAgainst(EffectDamage _eff, String _ev, Rate _freq) {
        _eff.getMultDamageAgainst().addEntry(_ev,_freq);
        return _eff;
    }
}