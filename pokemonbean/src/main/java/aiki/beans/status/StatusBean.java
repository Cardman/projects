package aiki.beans.status;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStringStatistic;
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
import code.util.CustList;
import code.util.EnumMap;
import code.util.NatCmpTreeMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.opers.BaseSixtyFourUtil;

public class StatusBean extends CommonBean {
    private String name;
    private String displayName;
    private String animStatus;

    private StatusType statusType;
    private Rate catchingRate;
    private CustList<EffectPartnerStatus> effectsPartner;
    private boolean disabledEffIfSwitch;
    private int incrementEndRound;
    private boolean incrementingEndRound;
    private TreeMap<Statistic, Rate> multStat;
    private StringList reasons;
    private NatTreeMap<String,String> mapVarsFail;
    private boolean endRound;
    private int endRoundRank;
    private StringList reasonsEndRound;
    private NatTreeMap<String,String> mapVarsFailEndRound;
    private Rate rateForUsingAMove;
    private boolean notAttack;
    private NatCmpTreeMap<LgInt,Rate> lawForUsingAMoveNbRound;
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
        name = (String) getForms().getVal(STATUS);
        DataBase data_ = (DataBase) getDataBase();
        animStatus = BaseSixtyFourUtil.getSringByImage(data_.getAnimStatus().getVal(name));
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        displayName = translatedStatus_.getVal(name);
        Status status_ = data_.getStatus(name);
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
            mapVarsFailEndRound = new NatTreeMap<String,String>();
        }
        statusType = status_.getStatusType();
        catchingRate = status_.getCatchingRate();
        effectsPartner = status_.getEffectsPartner();
        disabledEffIfSwitch = status_.getDisabledEffIfSwitch();
        incrementEndRound = status_.getIncrementEndRound();
        incrementingEndRound = status_.getIncrementingEndRound();
        reasons = getFormattedReasons(data_, getReasons(status_.getFail()), getLanguage());
        mapVarsFail = getMapVarsFail(data_, status_.getFail(), getLanguage());
        EnumMap<Statistic,String> translatedStatistics_;
        translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        TreeMap<Statistic, Rate> multStat_;
        multStat_ = new TreeMap<Statistic, Rate>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: status_.getMultStat().getKeys()) {
            multStat_.put(s, status_.getMultStat().getVal(s));
        }
        multStat = multStat_;
        notAttack = false;
        notAttackFoe = false;
        if (status_ instanceof StatusBeginRound) {
            StatusBeginRound statusBegin_ = (StatusBeginRound) status_;
            if (!statusBegin_.getLawForUsingAMove().isZero()) {
                if (statusBegin_.getLawForUsingAMove().containsEvent(true)) {
                    rateForUsingAMove = statusBegin_.getLawForUsingAMove().normalizedRate(true);
                } else {
                    rateForUsingAMove = Rate.zero();
                }
                notAttack = rateForUsingAMove.isZero();
            } else {
                rateForUsingAMove = Rate.zero();
            }
            if (!statusBegin_.getLawForUsingAMoveIfFoe().isZero()) {
                if (statusBegin_.getLawForUsingAMoveIfFoe().containsEvent(true)) {
                    rateForUsingAMoveIfFoe = statusBegin_.getLawForUsingAMoveIfFoe().normalizedRate(true);
                } else {
                    rateForUsingAMoveIfFoe = Rate.zero();
                }
                notAttackFoe = rateForUsingAMoveIfFoe.isZero();
            } else {
                rateForUsingAMoveIfFoe = Rate.zero();
            }
            if (!statusBegin_.getLawForFullHealIfMove().isZero()) {
                if (statusBegin_.getLawForFullHealIfMove().containsEvent(true)) {
                    rateForFullHealIfMove = statusBegin_.getLawForFullHealIfMove().normalizedRate(true);
                } else {
                    rateForFullHealIfMove = Rate.zero();
                }
            } else {
                rateForFullHealIfMove = Rate.zero();
            }
            NatCmpTreeMap<LgInt,Rate> lawForUsingAMoveNbRound_;
            lawForUsingAMoveNbRound_ = new NatCmpTreeMap<LgInt, Rate>();
            for (Rate e: statusBegin_.getLawForUsingAMoveNbRound().events()) {
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
            lawForUsingAMoveNbRound = new NatCmpTreeMap<LgInt, Rate>();
            power = Rate.zero();
        }
    }
    public EffectPartnerStatus getEffectPartner() {
        return effectsPartner.first();
    }
    public boolean isSingle() {
        return statusType == StatusType.INDIVIDUEL;
    }
    public String clickIndex() {
        if (!getForms().contains(STATUS_SET)) {
            getForms().put(STATUS_SET, new StringList());
        }
        return STATUS_SET;
    }
    public boolean incrementEndRoundInt() {
        return incrementEndRound > 0;
    }
    public String getTrMultStat(Long _index) {
        Statistic type_ = multStat.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAnimStatus() {
        return animStatus;
    }

    public boolean getEndRound() {
        return endRound;
    }

    public int getEndRoundRank() {
        return endRoundRank;
    }

    public StringList getReasonsEndRound() {
        return reasonsEndRound;
    }

    public NatTreeMap<String,String> getMapVarsFailEndRound() {
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

    public int getIncrementEndRound() {
        return incrementEndRound;
    }

    public boolean getIncrementingEndRound() {
        return incrementingEndRound;
    }

    public TreeMap<Statistic,Rate> getMultStat() {
        return multStat;
    }

    public StringList getReasons() {
        return reasons;
    }

    public NatTreeMap<String,String> getMapVarsFail() {
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

    public NatCmpTreeMap<LgInt,Rate> getLawForUsingAMoveNbRound() {
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