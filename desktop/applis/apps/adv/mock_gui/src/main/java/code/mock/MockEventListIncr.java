package code.mock;

import code.gui.TextAnswerValue;

public final class MockEventListIncr {
    private final double[] se;
    private final int[] ans;
    private final String[] files;
    private final TextAnswerValue[] text;

    public MockEventListIncr(double[] _s, int[] _a, String[] _f, TextAnswerValue[] _t) {
        this.se = _s;
        this.ans = _a;
        this.files = _f;
        this.text = _t;
    }

    public double[] getSe() {
        return se;
    }

    public int[] getAns() {
        return ans;
    }

    public String[] getFiles() {
        return files;
    }

    public TextAnswerValue[] getText() {
        return text;
    }
}
