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
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import aiki.comparators.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbMoveEffectDamage extends InitDbMoveEffect{

    public static DictionaryComparator<TranslatedKey,Long> callEffectDamageBeanBoostStatisOnceKoFoeGet(EffectDamageBean _str, int... _args) {
        return _str.getBoostStatisOnceKoFoe();
    }

    public static AbsMap<Rate,Rate> callEffectDamageBeanChLawGet(EffectDamageBean _str, int... _args) {
        return _str.getChLaw();
    }

    public static long callEffectDamageBeanChRateGet(EffectDamageBean _str, int... _args) {
        return _str.getChRate();
    }

    public static boolean callEffectDamageBeanConstDamageGet(EffectDamageBean _str, int... _args) {
        return _str.getConstDamage();
    }

    public static boolean callEffectDamageBeanConstPower(EffectDamageBean _str, int... _args) {
        return _str.constPower();
    }

    public static boolean callEffectDamageBeanCounterDamageCat(EffectDamageBean _str, int... _args) {
        return _str.counterDamageCat();
    }

    public static AbsMap<String,Rate> callEffectDamageBeanDamageLawGet(EffectDamageBean _str, int... _args) {
        return _str.getDamageLaw();
    }

    public static String callEffectDamageBeanGetTranslatedStatisKo(EffectDamageBean _str, int... _args) {
        return _str.getTranslatedStatisKo(_args[0]);
    }

    public static String callEffectDamageBeanGetTranslatedStatisTarget(EffectDamageBean _str, int... _args) {
        return _str.getTranslatedStatisTarget(_args[0]);
    }

    public static String callEffectDamageBeanGetTranslatedStatisUser(EffectDamageBean _str, int... _args) {
        return _str.getTranslatedStatisUser(_args[0]);
    }

    public static boolean callEffectDamageBeanHasConstPower(EffectDamageBean _str, int... _args) {
        return _str.hasConstPower();
    }

    public static boolean callEffectDamageBeanHasDeterminatedLawForDamage(EffectDamageBean _str, int... _args) {
        return _str.hasDeterminatedLawForDamage();
    }

    public static boolean callEffectDamageBeanHasLawForDamage(EffectDamageBean _str, int... _args) {
        return _str.hasLawForDamage();
    }

    public static AbsMap<Long,Rate> callEffectDamageBeanHitsLawGet(EffectDamageBean _str, int... _args) {
        return _str.getHitsLaw();
    }

    public static CustList<TranslatedKey> callEffectDamageBeanIgnVarStatTargetPosGet(EffectDamageBean _str, int... _args) {
        return _str.getIgnVarStatTargetPos();
    }

    public static CustList<TranslatedKey> callEffectDamageBeanIgnVarStatUserNegGet(EffectDamageBean _str, int... _args) {
        return _str.getIgnVarStatUserNeg();
    }

    public static AbsMap<String,String> callEffectDamageBeanMapVarsDamageGet(EffectDamageBean _str, int... _args) {
        return _str.getMapVarsDamage();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectDamageBeanMultDamageAgainstGet(EffectDamageBean _str, int... _args) {
        return _str.getMultDamageAgainst();
    }

    public static long callEffectDamageBeanNbHitsGet(EffectDamageBean _str, int... _args) {
        return _str.getNbHits();
    }

    public static String callEffectDamageBeanPowerGet(EffectDamageBean _str, int... _args) {
        return _str.getPower();
    }

    public static boolean callEffectDamageBeanRandMaxGet(EffectDamageBean _str, int... _args) {
        return _str.getRandMax();
    }

    public static String callEffectDamageBeanStatisAttGet(EffectDamageBean _str, int... _args) {
        return _str.getStatisAtt();
    }

    public static String callEffectDamageBeanStatisDefGet(EffectDamageBean _str, int... _args) {
        return _str.getStatisDef();
    }

    public static boolean callEffectDamageBeanSummingUserTeamOkFighterGet(EffectDamageBean _str, int... _args) {
        return _str.getSummingUserTeamOkFighter();
    }

    public static boolean callEffectDamageBeanTargetDefenseGet(EffectDamageBean _str, int... _args) {
        return _str.getTargetDefense();
    }

    public static boolean callEffectDamageBeanUserAttackGet(EffectDamageBean _str, int... _args) {
        return _str.getUserAttack();
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