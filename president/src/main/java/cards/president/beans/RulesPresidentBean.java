package cards.president.beans;
import code.bean.Accessible;
import code.bean.Bean;
import code.util.consts.Constants;
import cards.president.HandPresident;
import cards.president.RulesPresident;

public final class RulesPresidentBean extends Bean {

    @Accessible
    private String cartesBattues;

    @Accessible
    private int nbPlayers = 4;

    @Accessible
    private int nbStacks = 1;

    @Accessible
    private String equalty = "";

    @Accessible
    private boolean possibleReversing;

    @Accessible
    private boolean hasToPlay;

    @Accessible
    private boolean loosingIfFinishByBestCards = true;

    @Accessible
    private boolean switchCards = true;

    @Accessible
    private boolean looserStartsFirst = true;

    @Accessible
    private byte nbCardsPerPlayerMin;

    @Accessible
    private byte nbCardsPerPlayerMax;

    @Override
    public void beforeDisplaying() {
        RulesPresident rules_ = (RulesPresident) getDataBase();
        cartesBattues=rules_.getMixedCards().toString(Constants.getLanguage());
        nbPlayers = rules_.getNbPlayers();
        nbStacks = rules_.getNbStacks();
        equalty = rules_.getEqualty().toString(Constants.getLanguage());
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

    @Accessible
    private boolean sameAmount() {
        return nbCardsPerPlayerMin == nbCardsPerPlayerMax;
    }
}
