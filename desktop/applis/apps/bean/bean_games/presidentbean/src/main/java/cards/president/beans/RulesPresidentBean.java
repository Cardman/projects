package cards.president.beans;

import cards.consts.EnumCardsExporterUtil;
import cards.consts.MixCardsChoice;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.PresidentCardsExporterUtil;
import code.bean.IntBeanBuilderHelperCommon;
import code.scripts.pages.cards.MessagesPresidentPage;
import code.util.StringMap;

public final class RulesPresidentBean {

    private String cartesBattues;

    private int nbPlayers = 4;

    private int nbStacks = 1;

    private String equalty = "";

    private boolean possibleReversing;

    private boolean hasToPlay;

    private boolean loosingIfFinishByBestCards = true;

    private boolean switchCards = true;

    private boolean looserStartsFirst = true;

    private int nbCardsPerPlayerMin;

    private int nbCardsPerPlayerMax;

    private RulesPresident dataBase;
    private IntBeanBuilderHelperCommon builder;
    public RulesPresident db() {
        return dataBase;
    }

    public void setDataBase(RulesPresident _dataBase) {
        dataBase = _dataBase;
    }

    public void build() {
        beforeDisplaying();
        header(MessagesPresidentPage.M_BEAT_CARDS);
        getBuilder().formatMessageDir(cartesBattues);
        header(MessagesPresidentPage.M_NB_PLAYERS);
        getBuilder().formatMessageDir(Long.toString(nbPlayers));
        header(MessagesPresidentPage.M_NB_STACKS);
        getBuilder().formatMessageDir(Long.toString(nbStacks));
        if (sameAmount()) {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_NB_CARDS,Long.toString(nbCardsPerPlayerMin));
        } else {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_NB_CARDS_BET,Long.toString(nbCardsPerPlayerMin),Long.toString(nbCardsPerPlayerMax));
        }
        header(MessagesPresidentPage.M_EQUALTY);
        getBuilder().formatMessageDir(equalty);
        header(MessagesPresidentPage.M_INVERT);
        if (possibleReversing) {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_YES);
        } else {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_NO);
        }
        header(MessagesPresidentPage.M_HAS_TO_PLAY);
        if (hasToPlay) {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_YES);
        } else {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_NO);
        }
        header(MessagesPresidentPage.M_LOOSE_COND);
        if (loosingIfFinishByBestCards) {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_YES);
        } else {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_NO);
        }
        header(MessagesPresidentPage.M_SWITCH);
        if (switchCards) {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_YES);
        } else {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_NO);
        }
        header(MessagesPresidentPage.M_LOSSE_FIRST);
        if (looserStartsFirst) {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_YES);
        } else {
            getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_NO);
        }
    }

    private void header(String _key) {
        getBuilder().setHeader(1);
        getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"", _key);
        getBuilder().setHeader(0);
    }

    public void beforeDisplaying() {
        RulesPresident rules_ = db();
        cartesBattues=toString(rules_.getCommon().getMixedCards(), rules_.getCommon().getGeneral());
        nbPlayers = rules_.getNbPlayers();
        nbStacks = rules_.getNbStacks();
        equalty = toString(rules_.getEqualty(), rules_.getCommon().getSpecific());
        possibleReversing = rules_.isPossibleReversing();
        hasToPlay = rules_.isHasToPlay();
        loosingIfFinishByBestCards = rules_.isLoosingIfFinishByBestCards();
        switchCards = rules_.isSwitchCards();
        looserStartsFirst = rules_.isLooserStartsFirst();
        int nbCards_ = HandPresident.pileBase().total() * nbStacks;
        nbCardsPerPlayerMin = nbCards_ / nbPlayers;
        nbCardsPerPlayerMax = nbCardsPerPlayerMin;
        if (nbCards_ % nbPlayers > 0) {
            nbCardsPerPlayerMax++;
        }
    }
    static String toString(MixCardsChoice _b, StringMap<String> _file) {
        return _file.getVal(EnumCardsExporterUtil.fromMixCardsChoice(_b));
    }

    static String toString(EqualtyPlaying _b, StringMap<String> _file){
        return _file.getVal(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(_b));
    }

    public boolean sameAmount() {
        return nbCardsPerPlayerMin == nbCardsPerPlayerMax;
    }

    public String getCartesBattues() {
        return cartesBattues;
    }

    public int getNbPlayers() {
        return nbPlayers;
    }

    public int getNbStacks() {
        return nbStacks;
    }

    public String getEqualty() {
        return equalty;
    }

    public boolean isPossibleReversing() {
        return possibleReversing;
    }

    public boolean isHasToPlay() {
        return hasToPlay;
    }

    public boolean isLoosingIfFinishByBestCards() {
        return loosingIfFinishByBestCards;
    }

    public boolean isSwitchCards() {
        return switchCards;
    }

    public boolean isLooserStartsFirst() {
        return looserStartsFirst;
    }

    public int getNbCardsPerPlayerMin() {
        return nbCardsPerPlayerMin;
    }

    public int getNbCardsPerPlayerMax() {
        return nbCardsPerPlayerMax;
    }

    public IntBeanBuilderHelperCommon getBuilder() {
        return builder;
    }

    public void setBuilder(IntBeanBuilderHelperCommon _b) {
        this.builder = _b;
    }
}
