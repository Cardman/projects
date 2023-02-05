package code.expressionlanguage.utilimpl;

import code.expressionlanguage.gui.unit.LightTestableFrame;
import code.expressionlanguage.gui.unit.MemoryProgressingTests;
import code.expressionlanguage.gui.unit.ProgTestBar;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.MemInputFiles;
import code.gui.AdvGraphicListGeneratorStruct;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockAdvGraphicListGenerator;
import code.mock.MockFileSet;
import code.mock.MockInterceptor;
import code.mock.MockProgramInfos;
import code.stream.BytesInfo;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class ManageOptionsTest extends EquallableElUtImplUtil {
    @Test
    public void adjLg1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode(""), new BytesInfo(StringUtil.encode(""), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(messages(), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(new LightTestableFrame(pr_, null,new MockInterceptor(), new MockAdvGraphicListGenerator(true), new AdvGraphicListGeneratorStruct(),mem_, bar_));
        ManageOptions man_ = new ManageOptions(new StringList("_"), "__", new StringList(), progTest_, infos_);
        assertEq("",man_.getLanguage());
    }
    @Test
    public void adjLg2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode(""), new BytesInfo(StringUtil.encode(""), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(messages(), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(new LightTestableFrame(pr_, null,new MockInterceptor(), new MockAdvGraphicListGenerator(true), new AdvGraphicListGeneratorStruct(),mem_, bar_));
        ManageOptions man_ = new ManageOptions(new StringList("_"), "_", new StringList(), progTest_, infos_);
        assertEq("_",man_.getLanguage());
    }
}
