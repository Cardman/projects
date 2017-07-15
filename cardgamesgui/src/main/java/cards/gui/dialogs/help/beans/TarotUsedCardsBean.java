package cards.gui.dialogs.help.beans;
import cards.consts.Suit;
import cards.gui.labels.GraphicTarotCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.bean.Accessible;
import code.bean.Bean;
import code.images.ConverterBufferedImage;
import code.util.StringList;
import code.util.TreeMap;
import code.util.comparators.ComparatorEnum;

public class TarotUsedCardsBean extends Bean {

    @Accessible
    private TreeMap<Suit, StringList> images = new TreeMap<Suit, StringList>(new ComparatorEnum<Suit>());

    @Override
    public void beforeDisplaying() {
        for (Suit s: Suit.values()) {
            StringList list_ = new StringList();
            for (CardTarot c: HandTarot.couleurComplete(s)) {
                list_.add(ConverterBufferedImage.surroundImage(GraphicTarotCard.getTxtImage(c)));
            }
            images.put(s, list_);
        }
    }

    @Accessible
    private String getSuitOrCard(Long _index) {
        Suit suit_ = images.getKey(_index.intValue());
        if (suit_ != Suit.UNDEFINED) {
            return suit_.toString();
        }
        return CardTarot.EXCUSE.toString();
    }
}
