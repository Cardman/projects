package aiki.beans.status;

import aiki.beans.*;
import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.fight.status.*;
import aiki.fight.status.effects.*;
import code.maths.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class StatusBean extends CommonBean implements BeanRenderWithAppName {
    private String displayName;
    private int[][] animStatus;

    private StatusType statusType;
    private Rate catchingRate;
    private CustList<EffectPartnerStatus> effectsPartner;
    private boolean disabledEffIfSwitch;
    private long incrementEndRound;
    private boolean incrementingEndRound;
    private DictionaryComparator<TranslatedKey, Rate> multStat;
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
    public StatusBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataStatus.M_P_88_TITLE));
        formatMessageAnc(new StatusBeanClickIndex(this),MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_STATUS);
        addImg(animStatus);
        if (endRound) {
            formatMessage(MessagesPkBean.EFF_ENDROUND,MessagesDataEffendround.M_P_47_RANK,Long.toString(endRoundRank));
            formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_ENDROUND_HTML),MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_ENDROUND);
            displayStringList(reasonsEndRound,MessagesPkBean.EFF_ENDROUND,MessagesDataEffendround.M_P_47_REASONS);
            mapVarsInit(mapVarsFailEndRound);
            if (singleStatus) {
                displayBoolFull(toInt(incrementingDamageByRounds),MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_DAMAGE_INCREMENTED_TRUE,MessagesDataStatus.M_P_88_DAMAGE_INCREMENTED_FALSE);
            }
        }
        displayIntDef(catchingRate,MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_CATCHING_RATE);
        displayBoolTrue(toInt(disabledEffIfSwitch),MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_DISABLED_EFF_IF_SWITCH);
        displayIntDef(incrementEndRound,MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_INCREMENT_END_ROUND);
        displayBoolFull(toInt(incrementingEndRound),MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_INCREMENTING_END_ROUND_TRUE,MessagesDataStatus.M_P_88_INCREMENTING_END_ROUND_FALSE);
        displayBoolFull(toInt(isSingle()),MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_SINGLE,MessagesDataStatus.M_P_88_RELATION);
        new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this,multStat,MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_MULT_STAT,MessagesDataStatus.M_P_88_MULT_STAT_KEY,MessagesDataStatus.M_P_88_MULT_STAT_VALUE);
        displayStringList(reasonsEndRound,MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_REASONS);
        mapVarsInit(mapVarsFail);
        displayIntDef(rateForUsingAMove,MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_RATE_USE_MOVE);
        displayBoolTrue(toInt(notAttack),MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_NOT_ATTACK);
        displayIntDef(rateForUsingAMoveIfFoe,MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_RATE_USE_MOVE_FOE);
        displayBoolTrue(toInt(notAttackFoe),MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_NOT_ATTACK_FOE);
        displayIntDef(rateForFullHealIfMove,MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_RATE_HEAL_MOVE);
        new BeanDisplayMap<LgInt,Rate>(new BeanDisplayLgInt(),new BeanDisplayRate()).displayGrid(this,lawForUsingAMoveNbRound,MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_RATE_USE_MOVE_ROUND,MessagesDataStatus.M_P_88_RATE_USE_MOVE_ROUND_KEY,MessagesDataStatus.M_P_88_RATE_USE_MOVE_ROUND_RATE);
        if (!power.isZero()) {
            formatMessage(MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_AUTO_DAMAGE,power.toNumberString(),attack,defense);
        }
        if (!effectsPartner.isEmpty()) {
            displayIntDef(getEffectPartner().getRestoredHpRateLovedAlly(),MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_HEAL_HP);
            if (getEffectPartner().getWeddingAlly()) {
                formatMessage(MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_WEDDING);
                formatMessage(MessagesPkBean.STATUS,MessagesDataStatus.M_P_88_DAMAGED_FOES,getEffectPartner().getMultDamageAgainstFoe().toNumberString());
            }
        }
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.STATUS).getMapping();
    }
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
            reasonsEndRound = getFormattedReasons(data_, effect_.getFailEndRound(), getLanguage());
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
        reasons = getFormattedReasons(data_, status_.getFail(), getLanguage());
        mapVarsFail = getMapVarsFail(data_, status_.getFail(), getLanguage());
        AbsMap<Statistic,String> translatedStatistics_;
        translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        DictionaryComparator<TranslatedKey, Rate> multStat_;
        multStat_ = DictionaryComparatorUtil.buildStatisRate();
        for (Statistic s: status_.getMultStat().getKeys()) {
            multStat_.put(buildSi(getFacade(),s), status_.getMultStat().getVal(s));
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
        return multStat.getKey(_index).getTranslation();
//        Statistic type_ = multStat.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
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

    public DictionaryComparator<TranslatedKey,Rate> getMultStat() {
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