package code.mock;

import code.gui.TextAnswerValue;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;

public final class MockEventListIncr {
    private final CustomSeedGene se;
    private final int[] ans;
    private final String[] files;
    private final TextAnswerValue[] text;

    public MockEventListIncr(int[] _a, String[] _f, TextAnswerValue[] _t) {
        this(DefaultGenerator.oneEltArr(),_a,_f,_t);
    }

    public MockEventListIncr(double _v,int[] _a, String[] _f, TextAnswerValue[] _t) {
        this(DefaultGenerator.oneEltArr(_v),_a,_f,_t);
    }

    public MockEventListIncr(double[] _v,int[] _a, String[] _f, TextAnswerValue[] _t) {
        this(new CustomSeedGene(_v),_a,_f,_t);
    }

    public MockEventListIncr(CustomSeedGene _s, int[] _a, String[] _f, TextAnswerValue[] _t) {
        this.se = _s;
        this.ans = _a;
        this.files = _f;
        this.text = _t;
    }

    public CustomSeedGene getSe() {
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
