package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.FileInfos;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import org.junit.Test;

public final class InitializeTranslationsTest extends EquallableElAdvUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        update(pr_);
        String old_ = pr_.getTranslations().getMapping().getVal(FileInfos.EN).getMapping()
                .getVal(FileInfos.CDM).getMapping()
                .getVal(FileInfos.COMMENTS).getMapping().getVal(FileInfos.COMM_BEGIN);
        WindowCdmEditor.updateComments(pr_.getTranslations());
        assertEq(old_,pr_.getTranslations().getMapping().getVal(FileInfos.EN).getMapping()
                .getVal(FileInfos.CDM).getMapping()
                .getVal(FileInfos.COMMENTS).getMapping().getVal(FileInfos.COMM_BEGIN));
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        update(pr_);
        pr_.getTranslations().getMapping().getVal(FileInfos.EN).getMapping().addEntry(FileInfos.CDM+"/",new TranslationsAppli());
        pr_.getTranslations().getMapping().getVal(FileInfos.FR).getMapping().addEntry(FileInfos.CDM+"/",new TranslationsAppli());
        String old_ = pr_.getTranslations().getMapping().getVal(FileInfos.EN).getMapping()
                .getVal(FileInfos.CDM).getMapping()
                .getVal(FileInfos.COMMENTS).getMapping().getVal(FileInfos.COMM_BEGIN);
        WindowCdmEditor.updateComments(pr_.getTranslations());
        assertEq(old_,pr_.getTranslations().getMapping().getVal(FileInfos.EN).getMapping()
                .getVal(FileInfos.CDM).getMapping()
                .getVal(FileInfos.COMMENTS).getMapping().getVal(FileInfos.COMM_BEGIN));
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        update(pr_);
        pr_.getTranslations().getMapping().getVal(FileInfos.EN).getMapping().removeKey(FileInfos.CDM);
        pr_.getTranslations().getMapping().getVal(FileInfos.FR).getMapping().removeKey(FileInfos.CDM);
        WindowCdmEditor.updateComments(pr_.getTranslations());
        assertEq("",pr_.getTranslations().getMapping().getVal(FileInfos.EN).getMapping()
                .getVal(FileInfos.CDM).getMapping()
                .getVal(FileInfos.COMMENTS).getMapping().getVal(FileInfos.COMM_BEGIN));
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        update(pr_);
        pr_.getTranslations().getMapping().getVal(FileInfos.EN).getMapping()
                .getVal(FileInfos.CDM).getMapping()
                .addEntry(FileInfos.COMMENTS+"/",new TranslationsFile());
        pr_.getTranslations().getMapping().getVal(FileInfos.FR).getMapping()
                .getVal(FileInfos.CDM).getMapping()
                .addEntry(FileInfos.COMMENTS+"/",new TranslationsFile());
        String old_ = pr_.getTranslations().getMapping().getVal(FileInfos.EN).getMapping()
                .getVal(FileInfos.CDM).getMapping()
                .getVal(FileInfos.COMMENTS).getMapping().getVal(FileInfos.COMM_BEGIN);
        WindowCdmEditor.updateComments(pr_.getTranslations());
        assertEq(old_,pr_.getTranslations().getMapping().getVal(FileInfos.EN).getMapping()
                .getVal(FileInfos.CDM).getMapping()
                .getVal(FileInfos.COMMENTS).getMapping().getVal(FileInfos.COMM_BEGIN));
    }
}
