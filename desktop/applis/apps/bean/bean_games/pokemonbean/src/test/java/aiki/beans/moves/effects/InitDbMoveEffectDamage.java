package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringMap;

public abstract class InitDbMoveEffectDamage extends InitDbMoveEffect{

    public static Struct callEffectDamageBeanBoostStatisOnceKoFoeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanBoostStatisOnceKoFoeGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanChLawGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanChLawGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanChRateGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanChRateGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanConstDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanConstDamageGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanConstPower(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanConstPower(),_str,_args);
    }

    public static Struct callEffectDamageBeanCounterDamageCat(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanCounterDamageCat(),_str,_args);
    }

    public static Struct callEffectDamageBeanDamageLawGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanDamageLawGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanGetTranslatedStatisKo(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanGetTranslatedStatisKo(),_str,_args);
    }

    public static Struct callEffectDamageBeanGetTranslatedStatisTarget(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanGetTranslatedStatisTarget(),_str,_args);
    }

    public static Struct callEffectDamageBeanGetTranslatedStatisUser(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanGetTranslatedStatisUser(),_str,_args);
    }

    public static Struct callEffectDamageBeanHasConstPower(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanHasConstPower(),_str,_args);
    }

    public static Struct callEffectDamageBeanHasDeterminatedLawForDamage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanHasDeterminatedLawForDamage(),_str,_args);
    }

    public static Struct callEffectDamageBeanHasLawForDamage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanHasLawForDamage(),_str,_args);
    }

    public static Struct callEffectDamageBeanHitsLawGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanHitsLawGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanIgnVarStatTargetPosGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanIgnVarStatTargetPosGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanIgnVarStatUserNegGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanIgnVarStatUserNegGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanMapVarsDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanMapVarsDamageGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanMultDamageAgainstGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanMultDamageAgainstGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanNbHitsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanNbHitsGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanPowerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanPowerGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanRandMaxGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanRandMaxGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanStatisAttGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanStatisAttGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanStatisDefGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanStatisDefGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanSummingUserTeamOkFighterGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanSummingUserTeamOkFighterGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanTargetDefenseGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanTargetDefenseGet(),_str,_args);
    }

    public static Struct callEffectDamageBeanUserAttackGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageBeanUserAttackGet(),_str,_args);
    }

    protected static FacadeGame feedDbMoveEffDataDamComp(EffectDamage _dam) {
        FacadeGame f_ = feedDbMoveEffDataDam(TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, true, true, true, _dam);
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
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
        ef_.setChRate((byte) _ch);
        ef_.setStatisAtt(Statistic.ATTACK);
        ef_.setStatisDef(Statistic.DEFENSE);
        ef_.setSummingUserTeamOkFighter(_summingUserTeamOkFighter);
        ef_.setDamageLaw(new MonteCarloString());
        ef_.setMultDamageAgainst(new StringMap<Rate>());
        ef_.setRandMax(_randMax);
        ef_.setConstDamage(_constDamage);
        ef_.setChLaw(new MonteCarloNumber());
        ef_.setHitsLaw(new MonteCarloNumber());
        IdMap<Statistic, Byte> b_ = new IdMap<Statistic, Byte>();
        b_.addEntry(Statistic.SPEED,(byte)1);
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
}
