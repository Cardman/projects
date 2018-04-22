package cards.gui.comboboxes;
import cards.consts.Suit;
import code.gui.ComboBox;

public final class ComboBoxSuit extends ComboBox<Suit> {

    public void addItem(Suit _item) {
        getElements().put(_item, _item.display());
        super.addItem(_item, _item.display());
    }
}
