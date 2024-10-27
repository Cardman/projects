package code.mock;

import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;

public final class MockProgramInfosSample extends MockAbsProgramInfos{
    public MockProgramInfosSample(long _initMillis, long[] _incrs, String... _roots) {
        this(_initMillis, _incrs, new MockTrueRand(),_roots);
    }
    public MockProgramInfosSample(long _initMillis, long[] _incrs, MockAbsRand _mar, String... _roots) {
        super("", "", new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(_initMillis, _incrs,_roots),_mar);
    }
    public MockProgramInfosSample(String _h, String _t, long _initMillis, long[] _incrs, String... _roots) {
        super(_h, _t, new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(_initMillis, _incrs,_roots),new MockTrueRand());
    }

    @Override
    public MockAbsFrameFactory getMockFrameFactory() {
        return new MockFrameFactorySample();
    }
}
