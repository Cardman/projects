package cards.gui.dialogs.help.beans;
import cards.consts.Suit;
import cards.gui.labels.GraphicTarotCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.bean.Accessible;
import code.bean.Bean;
import code.images.ConverterBufferedImage;
import code.util.StringList;

public class TarotSortedCardsBean extends Bean {

    @Accessible
    private StringList imagesTrump = new StringList();

    @Accessible
    private StringList imagesSuit = new StringList();

    @Override
    public void beforeDisplaying() {
        for (CardTarot c: HandTarot.couleurComplete(Suit.TRUMP)) {
            imagesTrump.add(ConverterBufferedImage.surroundImage(GraphicTarotCard.getTxtImage(c)));
        }
        for (CardTarot c: HandTarot.couleurComplete(Suit.HEART)) {
            imagesSuit.add(ConverterBufferedImage.surroundImage(GraphicTarotCard.getTxtImage(c)));
        }
    }
}
