package cards.president.beans;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import code.bean.Bean;

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
        cartesBattues=rules_.getMixedCards().toString(getLanguage());
        nbPlayers = rules_.getNbPlayers();
        nbStacks = rules_.getNbStacks();
        equalty = rules_.getEqualty().toString(getLanguage());
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
