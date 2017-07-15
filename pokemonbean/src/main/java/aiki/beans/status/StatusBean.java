package aiki.beans.status;
import code.bean.Accessible;
import code.images.ConverterBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.NatCmpTreeMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
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

public class StatusBean extends CommonBean {

    @Accessible
    private String name;

    @Accessible
    private String displayName;

    @Accessible
    private String animStatus;

    private StatusType statusType;

    @Accessible
    private Rate catchingRate;

    @Accessible
    private CustList<EffectPartnerStatus> effectsPartner;

    @Accessible
    private boolean disabledEffIfSwitch;

    @Accessible
    private int incrementEndRound;

    @Accessible
    private boolean incrementingEndRound;

    @Accessible
    private TreeMap<Statistic, Rate> multStat;

    @Accessible
    private StringList reasons;

    @Accessible
    private NatTreeMap<String,String> mapVarsFail;

    @Accessible
    private boolean endRound;

    @Accessible
    private int endRoundRank;

    @Accessible
    private StringList reasonsEndRound;

    @Accessible
    private NatTreeMap<String,String> mapVarsFailEndRound;

    @Accessible
    private Rate rateForUsingAMove;

    @Accessible
    private boolean notAttack;

    @Accessible
    private NatCmpTreeMap<LgInt,Rate> lawForUsingAMoveNbRound;

    @Accessible
    private Rate rateForUsingAMoveIfFoe;

    @Accessible
    private boolean notAttackFoe;

    @Accessible
    private Rate rateForFullHealIfMove;

    @Accessible
    private Rate power;

    @Accessible
    private String attack;

    @Accessible
    private String defense;

    @Accessible
    private boolean singleStatus;

    @Accessible
    private boolean incrementingDamageByRounds;

    @Override
    public void beforeDisplaying() {
        name = (String) getForms().getVal(STATUS);
        DataBase data_ = (DataBase) getDataBase();
        animStatus = ConverterBufferedImage.surroundImage(data_.getAnimStatus().getVal(name));
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
            if (!statusBegin_.getLawForUsingAMove().events().isEmpty()) {
                if (statusBegin_.getLawForUsingAMove().containsEvent(true)) {
                    rateForUsingAMove = new Rate(statusBegin_.getLawForUsingAMove().rate(true), statusBegin_.getLawForUsingAMove().sum());
                } else {
                    rateForUsingAMove = Rate.zero();
                }
                notAttack = rateForUsingAMove.isZero();
            } else {
                rateForUsingAMove = Rate.zero();
            }
            if (!statusBegin_.getLawForUsingAMoveIfFoe().events().isEmpty()) {
                if (statusBegin_.getLawForUsingAMoveIfFoe().containsEvent(true)) {
                    rateForUsingAMoveIfFoe = new Rate(statusBegin_.getLawForUsingAMoveIfFoe().rate(true), statusBegin_.getLawForUsingAMoveIfFoe().sum());
                } else {
                    rateForUsingAMoveIfFoe = Rate.zero();
                }
                notAttackFoe = rateForUsingAMoveIfFoe.isZero();
            } else {
                rateForUsingAMoveIfFoe = Rate.zero();
            }
            if (!statusBegin_.getLawForFullHealIfMove().events().isEmpty()) {
                if (statusBegin_.getLawForFullHealIfMove().containsEvent(true)) {
                    rateForFullHealIfMove = new Rate(statusBegin_.getLawForFullHealIfMove().rate(true), statusBegin_.getLawForFullHealIfMove().sum());
                } else {
                    rateForFullHealIfMove = Rate.zero();
                }
            } else {
                rateForFullHealIfMove = Rate.zero();
            }
            NatCmpTreeMap<LgInt,Rate> lawForUsingAMoveNbRound_;
            lawForUsingAMoveNbRound_ = new NatCmpTreeMap<LgInt, Rate>();
            LgInt sum_ = statusBegin_.getLawForUsingAMoveNbRound().sum();
            for (Rate e: statusBegin_.getLawForUsingAMoveNbRound().events()) {
                lawForUsingAMoveNbRound_.put(e.intPart(), new Rate(statusBegin_.getLawForUsingAMoveNbRound().rate(e),sum_));
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

    @Accessible
    private EffectPartnerStatus getEffectPartner() {
        return effectsPartner.first();
    }

    @Accessible
    private boolean isSingle() {
        return statusType == StatusType.INDIVIDUEL;
    }

    @Accessible
    private String clickIndex() {
        if (!getForms().contains(STATUS_SET)) {
            getForms().put(STATUS_SET, new StringList());
        }
        return STATUS_SET;
    }

    @Accessible
    private boolean incrementEndRoundInt() {
        return incrementEndRound > 0;
    }

    @Accessible
    private String getTrMultStat(Long _index) {
        Statistic type_ = multStat.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
}
