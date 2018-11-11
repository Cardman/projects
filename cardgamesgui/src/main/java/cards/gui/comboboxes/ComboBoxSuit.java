package cards.gui.comboboxes;
import cards.consts.Suit;
import code.gui.ComboBox;

public final class ComboBoxSuit extends ComboBox<Suit> {

    public void addItemLgKey(Suit _item, String _lg) {
        getElements().put(_item, _item.toString(_lg));
        super.addItem(_item, _item.toString(_lg));
    }
}
