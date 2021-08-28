package code.minirts;

import code.gui.AbsMetaLabel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;
import code.minirts.rts.Soldier;



public final class UnitSoldier extends AbsMetaLabel {

    private final Soldier soldier;

    public UnitSoldier(Soldier _soldier, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        setOpaque(true);
        soldier = _soldier;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        if (!soldier.isSelected()) {
            _g.setColor(GuiConstants.BLACK);
        } else {
            _g.setColor(GuiConstants.RED);
        }
        _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    public Soldier getSoldier() {
        return soldier;
    }
}
