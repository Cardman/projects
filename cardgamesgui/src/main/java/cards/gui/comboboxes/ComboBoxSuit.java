package cards.gui.comboboxes;
import code.gui.ComboBox;
import cards.consts.Suit;

public final class ComboBoxSuit extends ComboBox<Suit> {

    public void addItem(Suit _item) {
        getElements().put(_item, _item.toString());
        super.addItem(_item);
    }
}
