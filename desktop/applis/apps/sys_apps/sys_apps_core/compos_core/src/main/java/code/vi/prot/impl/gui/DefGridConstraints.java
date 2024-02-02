package code.vi.prot.impl.gui;

import code.gui.AbsGridConstraints;

import java.awt.*;

public final class DefGridConstraints implements AbsGridConstraints {
    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();
    public DefGridConstraints() {
        gridBagConstraints.fill = GridBagConstraints.BOTH;
    }
    @Override
    public int gridwidth() {
        return gridBagConstraints.gridwidth;
    }

    @Override
    public void gridwidth(int _w) {
        gridBagConstraints.gridwidth = _w;
    }

    public GridBagConstraints getGridBagConstraints() {
        return gridBagConstraints;
    }
}
