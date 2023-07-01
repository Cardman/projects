package code.stream;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockProgramInfos;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.CustList;
import org.junit.Test;

public final class StreamLanguageUtilTest extends EquallableStreamUtil {
    @Test
    public void lg1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        StreamLanguageUtil.saveLanguage("/folder","0", pr_.getStreams());
        assertEq("",StreamLanguageUtil.tryToGetXmlLanguage("/folder",pr_.getFileCoreStream(),pr_.getStreams(),new CustList<String>()));
    }
    @Test
    public void lg2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        StreamLanguageUtil.saveLanguage("/folder","0", pr_.getStreams());
        CustList<String> lgs_ = new CustList<String>();
        lgs_.add("0");
        lgs_.add("1");
        assertEq("0",StreamLanguageUtil.tryToGetXmlLanguage("/folder",pr_.getFileCoreStream(),pr_.getStreams(), lgs_));
    }
    @Test
    public void lg3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        StreamLanguageUtil.saveLanguage("/folder","1", pr_.getStreams());
        CustList<String> lgs_ = new CustList<String>();
        lgs_.add("0");
        lgs_.add("1");
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
        CustList<String> lgs_ = new CustList<String>();
        lgs_.add("0");
        lgs_.add("1");
        assertEq("",StreamLanguageUtil.tryToGetXmlLanguage("/folder",pr_.getFileCoreStream(),pr_.getStreams(), lgs_));
    }
    @Test
    public void lg5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), fileSet(0, new long[0], "/"));
        StreamFolderFile.makeParent("/folder/lg.txt",pr_.getFileCoreStream());
        String path_ = StreamLanguageUtil.lg("/folder");
        StreamTextFile.saveTextFile(path_, "",pr_.getStreams());
        CustList<String> lgs_ = new CustList<String>();
        lgs_.add("0");
        lgs_.add("1");
        assertEq("",StreamLanguageUtil.tryToGetXmlLanguage("/folder",pr_.getFileCoreStream(),pr_.getStreams(), lgs_));
    }
}
