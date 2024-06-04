package code.mock;

import code.gui.TextAnswerValue;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;

public final class MockProgramInfosSample extends MockAbsProgramInfos{
    public MockProgramInfosSample(String _h, String _t, double _se, long _initMillis, long[] _incrs, boolean _cust, String... _roots) {
        super(_h, _t, new CustomSeedGene(DefaultGenerator.oneEltArr(_se)), new MockFileSet(_initMillis, _incrs,_roots));
    }
    public MockProgramInfosSample(String _h, String _t, long _initMillis, long[] _incrs, boolean _cust, String... _roots) {
        super(_h, _t, new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(_initMillis, _incrs,_roots),new MockRand(new DefaultGenerator(0.5)));
    }

    @Override
    public MockAbsFrameFactory getMockFrameFactory() {
        return new MockFrameFactorySample(this);
    }
}
