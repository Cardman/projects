package code.mock;

import code.gui.AbsGridConstraints;

public final class MockGridConstraints implements AbsGridConstraints {
    private int gridwidth = 1;
    private int gridheight = 1;
    private int gridx = 1;
    private int gridy = 1;

    @Override
    public int gridx() {
        return gridx;
    }

    @Override
    public void gridx(int _w) {
        gridx = _w;
    }

    @Override
    public int gridy() {
        return gridy;
    }

    @Override
    public void gridy(int _w) {
        gridy = _w;
    }

    @Override
    public int gridwidth() {
        return gridwidth;
    }

    @Override
    public void gridwidth(int _w) {
        gridwidth = _w;
    }

    @Override
    public int gridheight() {
        return gridheight;
    }

    @Override
    public void gridheight(int _w) {
        gridheight = _w;
    }
}
