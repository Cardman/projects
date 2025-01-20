package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
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

    public static NaSt callEffectDamageBeanBoostStatisOnceKoFoeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanBoostStatisOnceKoFoeGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanChLawGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanChLawGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanChRateGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanChRateGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanConstDamageGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanConstDamageGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanConstPower(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanConstPower(),_str,_args);
    }

    public static NaSt callEffectDamageBeanCounterDamageCat(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanCounterDamageCat(),_str,_args);
    }

    public static NaSt callEffectDamageBeanDamageLawGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanDamageLawGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanGetTranslatedStatisKo(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanGetTranslatedStatisKo(),_str,_args);
    }

    public static NaSt callEffectDamageBeanGetTranslatedStatisTarget(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanGetTranslatedStatisTarget(),_str,_args);
    }

    public static NaSt callEffectDamageBeanGetTranslatedStatisUser(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanGetTranslatedStatisUser(),_str,_args);
    }

    public static NaSt callEffectDamageBeanHasConstPower(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanHasConstPower(),_str,_args);
    }

    public static NaSt callEffectDamageBeanHasDeterminatedLawForDamage(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanHasDeterminatedLawForDamage(),_str,_args);
    }

    public static NaSt callEffectDamageBeanHasLawForDamage(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanHasLawForDamage(),_str,_args);
    }

    public static NaSt callEffectDamageBeanHitsLawGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanHitsLawGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanIgnVarStatTargetPosGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanIgnVarStatTargetPosGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanIgnVarStatUserNegGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanIgnVarStatUserNegGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanMapVarsDamageGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanMapVarsDamageGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanMultDamageAgainstGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanMultDamageAgainstGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanNbHitsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanNbHitsGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanPowerGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanPowerGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanRandMaxGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanRandMaxGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanStatisAttGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanStatisAttGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanStatisDefGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanStatisDefGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanSummingUserTeamOkFighterGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanSummingUserTeamOkFighterGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanTargetDefenseGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanTargetDefenseGet(),_str,_args);
    }

    public static NaSt callEffectDamageBeanUserAttackGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanUserAttackGet(),_str,_args);
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
