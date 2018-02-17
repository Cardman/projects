package cards.gui.dialogs.help.beans;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Suit;
import cards.gui.labels.GraphicBeloteCard;
import code.bean.Bean;
import code.images.ConverterBufferedImage;
import code.util.StringList;

public class BeloteSortedCardsBean extends Bean {

    private StringList imagesTrump = new StringList();

    private StringList imagesSuit = new StringList();

    @Override
    public void beforeDisplaying() {
        for (CardBelote c: HandBelote.couleurComplete(Suit.HEART, Order.TRUMP)) {
            imagesTrump.add(ConverterBufferedImage.surroundImage(GraphicBeloteCard.getTxtImage(c)));
        }
        for (CardBelote c: HandBelote.couleurComplete(Suit.SPADE, Order.SUIT)) {
            imagesSuit.add(ConverterBufferedImage.surroundImage(GraphicBeloteCard.getTxtImage(c)));
        }
    }
}
