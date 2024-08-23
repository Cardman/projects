package code.gui.events;

import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.MockAbsFrameFactory;
import code.mock.MockAbsProgramInfos;
import code.mock.MockFileSet;

public final class MockProgramInfosSecSample extends MockAbsProgramInfos {
    public MockProgramInfosSecSample(String _h, String _t, long _initMillis, long[] _incrs, boolean _cust, String... _roots) {
        super(_h, _t, new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(_initMillis, _incrs,_roots));
    }

    @Override
    public MockAbsFrameFactory getMockFrameFactory() {
        return new MockFrameFactorySecSample();
    }
}
