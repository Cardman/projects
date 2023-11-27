package code.expressionlanguage.utilimpl;

import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.MemInputFiles;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockInterceptor;
import code.mock.MockProgramInfos;
import code.stream.BytesInfo;
import code.threads.ConcreteBoolean;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class IntegElUtilImplTest extends EquallableElUtImplUtil {
    @Test
    public void test() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        FileInfos file_ = MemoResultContextNext.fileInfos(pr_, null, new MemInputFiles(StringUtil.encode(StringUtil.join(new CustList<String>(), '\n')), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true)));
        LgNamesGui stds_ = new LgNamesGui(file_, new MockInterceptor());
        AbsFileBuilderListGene d_ = new DefFileBuilderListGene();
        assertFalse(d_.build(stds_).isEmpty());
        d_.gene(new ConcreteBoolean());
    }
}
