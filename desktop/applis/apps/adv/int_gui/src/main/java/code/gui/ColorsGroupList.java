package code.gui;

public final class ColorsGroupList {
    private final int bg;
    private final int fg;
    private final int bgSel;
    private final int fgSel;

    public ColorsGroupList(int _b, int _f, int _bs, int _fs) {
        this.bg = _b;
        this.fg = _f;
        this.bgSel = _bs;
        this.fgSel = _fs;
    }

    public int getBg() {
        return bg;
    }

    public int getFg() {
        return fg;
    }

    public int getBgSel() {
        return bgSel;
    }

    public int getFgSel() {
        return fgSel;
    }
}
