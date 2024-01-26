package cards.president.beans;

import cards.consts.EnumCardsExporterUtil;
import cards.consts.MixCardsChoice;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.PresidentCardsExporterUtil;
import code.bean.Bean;
import code.util.StringMap;

public final class RulesPresidentBean extends Bean {

    private String cartesBattues;

    private int nbPlayers = 4;

    private int nbStacks = 1;

    private String equalty = "";

    private boolean possibleReversing;

    private boolean hasToPlay;

    private boolean loosingIfFinishByBestCards = true;

    private boolean switchCards = true;

    private boolean looserStartsFirst = true;

    private byte nbCardsPerPlayerMin;

    private byte nbCardsPerPlayerMax;

    private RulesPresident dataBase;
    public RulesPresident db() {
        return dataBase;
    }

    public void setDataBase(RulesPresident _dataBase) {
        dataBase = _dataBase;
    }

    @Override
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
        nbCardsPerPlayerMin = (byte) (nbCards_ / nbPlayers);
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

    public byte getNbCardsPerPlayerMin() {
        return nbCardsPerPlayerMin;
    }

    public byte getNbCardsPerPlayerMax() {
        return nbCardsPerPlayerMax;
    }

}
