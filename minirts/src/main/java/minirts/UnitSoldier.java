package minirts;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import minirts.rts.Soldier;

public class UnitSoldier extends JLabel {

    private final Soldier soldier;

    public UnitSoldier(Soldier _soldier) {
        setOpaque(true);
        soldier = _soldier;
    }

    @Override
    protected void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        if (!soldier.isSelected()) {
            _g.setColor(Color.BLACK);
        } else {
            _g.setColor(Color.RED);
        }
        _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
