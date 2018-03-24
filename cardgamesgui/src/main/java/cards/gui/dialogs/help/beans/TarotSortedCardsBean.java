package cards.gui.dialogs.help.beans;
import cards.consts.Suit;
import cards.gui.labels.GraphicTarotCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.bean.Bean;
import code.util.StringList;

public class TarotSortedCardsBean extends Bean {

    private StringList imagesTrump = new StringList();

    private StringList imagesSuit = new StringList();

    @Override
    public void beforeDisplaying() {
        for (CardTarot c: HandTarot.couleurComplete(Suit.TRUMP)) {
            imagesTrump.add(GraphicTarotCard.getTxtImage(c));
        }
        for (CardTarot c: HandTarot.couleurComplete(Suit.HEART)) {
            imagesSuit.add(GraphicTarotCard.getTxtImage(c));
        }
    }
}
