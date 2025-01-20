package aiki.beans.status;

import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundSingleStatus;
import aiki.fight.status.Status;
import aiki.fight.status.StatusBeginRound;
import aiki.fight.status.StatusBeginRoundAutoDamage;
import aiki.fight.status.StatusType;
import aiki.fight.status.effects.EffectPartnerStatus;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.*;

public class StatusBean extends CommonBean {
    private String displayName;
    private int[][] animStatus;

    private StatusType statusType;
    private Rate catchingRate;
    private CustList<EffectPartnerStatus> effectsPartner;
    private boolean disabledEffIfSwitch;
    private long incrementEndRound;
    private boolean incrementingEndRound;
    private DictionaryComparator<Statistic, Rate> multStat;
    private StringList reasons;
    private NatStringTreeMap<String> mapVarsFail;
    private boolean endRound;
    private long endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;
    private Rate rateForUsingAMove;
    private boolean notAttack;
    private DictionaryComparator<LgInt,Rate> lawForUsingAMoveNbRound;
    private Rate rateForUsingAMoveIfFoe;
    private boolean notAttackFoe;
    private Rate rateForFullHealIfMove;
    private Rate power;
    private String attack;
    private String defense;
    private boolean singleStatus;
    private boolean incrementingDamageByRounds;

    @Override
    public void beforeDisplaying() {
        String n_ = getForms().getValStr(CST_STATUS);
        DataBase data_ = getDataBase();
        animStatus = data_.getAnimStatus().getVal(n_).getImage();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        displayName = translatedStatus_.getVal(n_);
        Status status_ = data_.getStatus(n_);
        incrementingDamageByRounds = false;
        singleStatus = false;
        if (!status_.getEffectEndRound().isEmpty()) {
            endRound = true;
            EffectEndRound effect_ = status_.getEffectEndRound().first();
            if (effect_ instanceof EffectEndRoundSingleStatus) {
                singleStatus = true;
                incrementingDamageByRounds = ((EffectEndRoundSingleStatus)effect_).isIncrementingDamageByRounds();
            }
            endRoundRank = effect_.getEndRoundRank();
            reasonsEndRound = getFormattedReasons(data_, getReasons(effect_.getFailEndRound()), getLanguage());
            mapVarsFailEndRound = getMapVarsFail(data_, effect_.getFailEndRound(), getLanguage());
        } else {
            endRound = false;
            endRoundRank = 0;
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatStringTreeMap<String>();
        }
        statusType = status_.getStatusType();
        catchingRate = status_.getCatchingRate();
        effectsPartner = status_.getEffectsPartner();
        disabledEffIfSwitch = status_.getDisabledEffIfSwitch();
        incrementEndRound = status_.getIncrementEndRound();
        incrementingEndRound = status_.getIncrementingEndRound();
        reasons = getFormattedReasons(data_, getReasons(status_.getFail()), getLanguage());
        mapVarsFail = getMapVarsFail(data_, status_.getFail(), getLanguage());
        AbsMap<Statistic,String> translatedStatistics_;
        translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        DictionaryComparator<Statistic, Rate> multStat_;
        multStat_ = DictionaryComparatorUtil.buildStatisRate(data_,getLanguage());
        for (Statistic s: status_.getMultStat().getKeys()) {
            multStat_.put(s, status_.getMultStat().getVal(s));
        }
        multStat = multStat_;
        notAttack = false;
        notAttackFoe = false;
        if (status_ instanceof StatusBeginRound) {
            StatusBeginRound statusBegin_ = (StatusBeginRound) status_;
            rates(statusBegin_);
            DictionaryComparator<LgInt,Rate> lawForUsingAMoveNbRound_;
            lawForUsingAMoveNbRound_ = DictionaryComparatorUtil.buildIntRate();
            for (Rate e: statusBegin_.getLawForUsingAMoveNbRound().eventsDiff()) {
                lawForUsingAMoveNbRound_.put(e.intPart(), statusBegin_.getLawForUsingAMoveNbRound().normalizedRate(e));
            }
            lawForUsingAMoveNbRound = lawForUsingAMoveNbRound_;
            if (status_ instanceof StatusBeginRoundAutoDamage) {
                StatusBeginRoundAutoDamage statusBeginDamage_;
                statusBeginDamage_ = (StatusBeginRoundAutoDamage) status_;
                attack = translatedStatistics_.getVal(statusBeginDamage_.getAttack());
                defense = translatedStatistics_.getVal(statusBeginDamage_.getDefense());
                power = statusBeginDamage_.getPower();
            } else {
                power = Rate.zero();
            }
        } else {
            rateForUsingAMove = Rate.zero();
            rateForUsingAMoveIfFoe = Rate.zero();
            rateForFullHealIfMove = Rate.zero();
            lawForUsingAMoveNbRound = DictionaryComparatorUtil.buildIntRate();
            power = Rate.zero();
        }
    }

    private void rates(StatusBeginRound _statusBegin) {
        if (!_statusBegin.getLawForUsingAMove().isZero()) {
            rateForUsingAMove = rateTrue(_statusBegin.getLawForUsingAMove());
            notAttack = rateForUsingAMove.isZero();
        } else {
            rateForUsingAMove = Rate.zero();
        }
        if (!_statusBegin.getLawForUsingAMoveIfFoe().isZero()) {
            rateForUsingAMoveIfFoe = rateTrue(_statusBegin.getLawForUsingAMoveIfFoe());
            notAttackFoe = rateForUsingAMoveIfFoe.isZero();
        } else {
            rateForUsingAMoveIfFoe = Rate.zero();
        }
        rateForFullHealIfMove = rateTrue(_statusBegin.getLawForFullHealIfMove());
    }

    public EffectPartnerStatus getEffectPartner() {
        return effectsPartner.first();
    }
    public boolean isSingle() {
        return statusType == StatusType.INDIVIDUEL;
    }
    public String clickIndex() {
        getForms().safeStatus(CST_STATUS_SET);
        return PkScriptPages.REN_ADD_WEB_HTML_STATUS_STATUS_HTML;
    }
    public boolean incrementEndRoundInt() {
        return incrementEndRound > 0;
    }
    public String getTrMultStat(int _index) {
        Statistic type_ = multStat.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }

    public String getDisplayName() {
        return displayName;
    }

    public int[][] getAnimStatus() {
        return animStatus;
    }

    public boolean getEndRound() {
        return endRound;
    }

    public long getEndRoundRank() {
        return endRoundRank;
    }

    public StringList getReasonsEndRound() {
        return reasonsEndRound;
    }

    public NatStringTreeMap<String> getMapVarsFailEndRound() {
        return mapVarsFailEndRound;
    }

    public boolean getSingleStatus() {
        return singleStatus;
    }

    public boolean getIncrementingDamageByRounds() {
        return incrementingDamageByRounds;
    }

    public Rate getCatchingRate() {
        return catchingRate;
    }

    public boolean getDisabledEffIfSwitch() {
        return disabledEffIfSwitch;
    }

    public long getIncrementEndRound() {
        return incrementEndRound;
    }

    public boolean getIncrementingEndRound() {
        return incrementingEndRound;
    }

    public DictionaryComparator<Statistic,Rate> getMultStat() {
        return multStat;
    }

    public StringList getReasons() {
        return reasons;
    }

    public NatStringTreeMap<String> getMapVarsFail() {
        return mapVarsFail;
    }

    public Rate getRateForUsingAMove() {
        return rateForUsingAMove;
    }

    public boolean getNotAttack() {
        return notAttack;
    }

    public Rate getRateForUsingAMoveIfFoe() {
        return rateForUsingAMoveIfFoe;
    }

    public boolean getNotAttackFoe() {
        return notAttackFoe;
    }

    public Rate getRateForFullHealIfMove() {
        return rateForFullHealIfMove;
    }

    public DictionaryComparator<LgInt,Rate> getLawForUsingAMoveNbRound() {
        return lawForUsingAMoveNbRound;
    }

    public Rate getPower() {
        return power;
    }

    public String getAttack() {
        return attack;
    }

    public String getDefense() {
        return defense;
    }

    public CustList<EffectPartnerStatus> getEffectsPartner() {
        return effectsPartner;
    }
}