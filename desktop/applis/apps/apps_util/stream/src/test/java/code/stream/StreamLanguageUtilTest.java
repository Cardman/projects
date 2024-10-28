package code.stream;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockProgramInfos;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.*;
import org.junit.Test;

public final class StreamLanguageUtilTest extends EquallableStreamUtil {
    @Test
    public void lg1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        StreamLanguageUtil.saveLanguage("/folder","0", pr_);
        assertEq("",StreamLanguageUtil.tryToGetXmlLanguage("/folder",pr_.getFileCoreStream(),pr_.getStreams(),new StringMap<String>()));
    }
    @Test
    public void lg2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        StringMap<String> lgs_ = new StringMap<String>();
        lgs_.addEntry("0","0");
        lgs_.addEntry("1","1");
        pr_.getTranslations().getIndexes().addAllEntries(lgs_);
        StreamLanguageUtil.saveLanguage("/folder","0", pr_);
        assertEq("0",StreamLanguageUtil.tryToGetXmlLanguage("/folder",pr_.getFileCoreStream(),pr_.getStreams(), lgs_));
    }
    @Test
    public void lg3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        StringMap<String> lgs_ = new StringMap<String>();
        lgs_.addEntry("0","0");
        lgs_.addEntry("1","1");
        pr_.getTranslations().getIndexes().addAllEntries(lgs_);
        StreamLanguageUtil.saveLanguage("/folder","1", pr_);
        assertEq("1",StreamLanguageUtil.tryToGetXmlLanguage("/folder",pr_.getFileCoreStream(),pr_.getStreams(), lgs_));
    }
    @Test
    public void lg4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        String path_ = StreamLanguageUtil.lg("/folder");
        Document document_= DocumentBuilder.newXmlDocument();
        Element info_=document_.createElement("_");
        Element infoPart_ = document_.createElement("__");
        info_.appendChild(infoPart_);
        document_.appendChild(info_);
        StreamTextFile.saveTextFile(path_, document_.export(),pr_.getStreams());
        StringMap<String> lgs_ = new StringMap<String>();
        lgs_.addEntry("0","0");
        lgs_.addEntry("1","1");
        pr_.getTranslations().getIndexes().addAllEntries(lgs_);
        assertEq("",StreamLanguageUtil.tryToGetXmlLanguage("/folder",pr_.getFileCoreStream(),pr_.getStreams(), lgs_));
    }
    @Test
    public void lg5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        String path_ = StreamLanguageUtil.lg("/folder");
        StreamTextFile.saveTextFile(path_, "",pr_.getStreams());
        StringMap<String> lgs_ = new StringMap<String>();
        lgs_.addEntry("0","0");
        lgs_.addEntry("1","1");
        pr_.getTranslations().getIndexes().addAllEntries(lgs_);
        assertEq("",StreamLanguageUtil.tryToGetXmlLanguage("/folder",pr_.getFileCoreStream(),pr_.getStreams(), lgs_));
    }
    @Test
    public void lg6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        StreamLanguageUtil.saveLanguage("/folder","", pr_);
        assertFalse(pr_.getFileCoreStream().newFile(StreamLanguageUtil.lg("/folder")).exists());
    }
    @Test
    public void lg7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        String path_ = StreamLanguageUtil.lg("/folder");
        Document document_= DocumentBuilder.newXmlDocument();
        Element info_=document_.createElement("_");
        Element infoPart_ = document_.createElement(StreamLanguageUtil.LOCALE);
        info_.appendChild(infoPart_);
        document_.appendChild(info_);
        StreamTextFile.saveTextFile(path_, document_.export(),pr_.getStreams());
        StringMap<String> lgs_ = new StringMap<String>();
        lgs_.addEntry("0","0");
        lgs_.addEntry("1","1");
        pr_.getTranslations().getIndexes().addAllEntries(lgs_);
        assertEq("",StreamLanguageUtil.tryToGetXmlLanguage("/folder",pr_.getFileCoreStream(),pr_.getStreams(), lgs_));
    }
}
