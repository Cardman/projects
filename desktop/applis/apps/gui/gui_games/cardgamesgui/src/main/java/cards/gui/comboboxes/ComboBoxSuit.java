package cards.gui.comboboxes;
import cards.consts.Suit;
import cards.facade.Games;
import code.gui.ComboBox;

public final class ComboBoxSuit extends ComboBox<Suit> {

    public void addItemLgKey(Suit _item, String _lg) {
        getElements().put(_item, Games.toString(_item,_lg));
        super.addItem(_item, Games.toString(_item,_lg));
    }
}
