package cards.president.beans;
import cards.consts.CoreResourcesAccess;
import cards.consts.MixCardsChoice;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.PresidentResoucesAccess;
import code.bean.Bean;
import code.format.Format;

final class RulesPresidentBean extends Bean {

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

    @Override
    public void beforeDisplaying() {
        RulesPresident rules_ = (RulesPresident) getDataBase();
        cartesBattues=toString(rules_.getMixedCards(), rules_.getGeneral());
        nbPlayers = rules_.getNbPlayers();
        nbStacks = rules_.getNbStacks();
        equalty = toString(rules_.getEqualty(), rules_.getSpecific());
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
    static String toString(MixCardsChoice _b, String _file) {
        return Format.getConstanteLangue(_file, CoreResourcesAccess.MIX,_b.name());
    }
    static String toString(EqualtyPlaying _b, String _file){
        return Format.getConstanteLangue(_file, PresidentResoucesAccess.PRESIDENT_EQUAL_PLAY,_b.name());
    }
    boolean sameAmount() {
        return nbCardsPerPlayerMin == nbCardsPerPlayerMax;
    }

    String getCartesBattues() {
        return cartesBattues;
    }

    int getNbPlayers() {
        return nbPlayers;
    }

    int getNbStacks() {
        return nbStacks;
    }

    String getEqualty() {
        return equalty;
    }

    boolean isPossibleReversing() {
        return possibleReversing;
    }

    boolean isHasToPlay() {
        return hasToPlay;
    }

    boolean isLoosingIfFinishByBestCards() {
        return loosingIfFinishByBestCards;
    }

    boolean isSwitchCards() {
        return switchCards;
    }

    boolean isLooserStartsFirst() {
        return looserStartsFirst;
    }

    byte getNbCardsPerPlayerMin() {
        return nbCardsPerPlayerMin;
    }

    byte getNbCardsPerPlayerMax() {
        return nbCardsPerPlayerMax;
    }

}
