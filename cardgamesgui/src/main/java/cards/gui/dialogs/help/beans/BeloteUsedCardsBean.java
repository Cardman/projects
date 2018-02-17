package cards.gui.dialogs.help.beans;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Suit;
import cards.gui.labels.GraphicBeloteCard;
import code.bean.Bean;
import code.images.ConverterBufferedImage;
import code.util.StringList;
import code.util.TreeMap;
import code.util.comparators.ComparatorEnum;

public class BeloteUsedCardsBean extends Bean {

    private TreeMap<Suit, StringList> images = new TreeMap<Suit, StringList>(new ComparatorEnum<Suit>());

    @Override
    public void beforeDisplaying() {
        for (Suit s: Suit.couleursOrdinaires()) {
            StringList list_ = new StringList();
            for (CardBelote c: HandBelote.couleurComplete(s, Order.TRUMP)) {
                list_.add(ConverterBufferedImage.surroundImage(GraphicBeloteCard.getTxtImage(c)));
            }
            images.put(s, list_);
        }
    }
}
