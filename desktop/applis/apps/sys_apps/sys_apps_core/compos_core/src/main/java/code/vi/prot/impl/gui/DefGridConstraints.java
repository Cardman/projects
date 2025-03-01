package code.vi.prot.impl.gui;

import code.gui.AbsGridConstraints;

import java.awt.*;

public final class DefGridConstraints implements AbsGridConstraints {
    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();
    public DefGridConstraints() {
        gridBagConstraints.fill = GridBagConstraints.BOTH;
    }

    @Override
    public int gridx() {
        return gridBagConstraints.gridx;
    }

    @Override
    public void gridx(int _w) {
        gridBagConstraints.gridx = _w;
    }

    @Override
    public int gridy() {
        return gridBagConstraints.gridy;
    }

    @Override
    public void gridy(int _w) {
        gridBagConstraints.gridy = _w;
    }

    @Override
    public int gridwidth() {
        return gridBagConstraints.gridwidth;
    }

    @Override
    public void gridwidth(int _w) {
        gridBagConstraints.gridwidth = _w;
    }

    @Override
    public int gridheight() {
        return gridBagConstraints.gridheight;
    }

    @Override
    public void gridheight(int _w) {
        gridBagConstraints.gridheight = _w;
    }

    public GridBagConstraints getGridBagConstraints() {
        return gridBagConstraints;
    }
}
