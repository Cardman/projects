package code.minirts;

import code.gui.PaintableLabel;
import code.gui.images.AbstractImage;
import code.minirts.rts.Soldier;

import java.awt.Color;

public class UnitSoldier extends PaintableLabel {

    private final Soldier soldier;

    public UnitSoldier(Soldier _soldier) {
        setOpaque(true);
        soldier = _soldier;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        if (!soldier.isSelected()) {
            _g.setColor(Color.BLACK);
        } else {
            _g.setColor(Color.RED);
        }
        _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    public Soldier getSoldier() {
        return soldier;
    }
}
