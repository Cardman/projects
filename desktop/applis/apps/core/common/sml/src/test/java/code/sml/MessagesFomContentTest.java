package code.sml;

import code.sml.util.*;
import code.util.StringMap;
import org.junit.Test;

public final class MessagesFomContentTest extends EquallableRowColUtil {
    @Test
    public void getMessagesFromContent1Test() {
        StringMap<String> map_ = ResourcesMessagesUtil.getMessagesFromContent("");
        assertTrue(map_.isEmpty());
    }
    @Test
    public void getMessagesFromContent2Test() {
        StringMap<String> map_ = ResourcesMessagesUtil.getMessagesFromContent("k1=v1");
        assertEq(1, map_.size());
        assertEq("v1", map_.getVal("k1"));
    }
    @Test
    public void getMessagesFromContent3Test() {
        StringMap<String> map_ = ResourcesMessagesUtil.getMessagesFromContent("k1=v1\nk2=v2");
        assertEq(2, map_.size());
        assertEq("v1", map_.getVal("k1"));
        assertEq("v2", map_.getVal("k2"));
    }
    @Test
    public void trs() {
        Translations tr_ = new Translations();
        TranslationsLg lg_ = new TranslationsLg();
        TranslationsAppli a_ = new TranslationsAppli();
        TranslationsFile f_ = new TranslationsFile();
        f_.add("","");
        a_.getMapping().addEntry("", f_);
        lg_.getMapping().addEntry("", a_);
        lg_.setTreeCards(DocumentBuilder.newXmlDocument());
        lg_.getTreeCards().appendChild(lg_.getTreeCards().createElement("_"));
        tr_.getMapping().addEntry("", lg_);
        assertEq("",tr_.getMapping().getVal("").getMapping().getVal("").getMapping().getVal("").getMapping().getVal(""));
        assertEq("",lg_.getKey());
        assertEq(0,lg_.getMaxiCards().size());
        assertEq(0,lg_.getMiniCardsDef().size());
        assertEq(0,lg_.getMiniCardsSel().size());
    }
    @Test
    public void byAppl() {
        Translations tr_ = new Translations();
        TranslationsLg lg_ = new TranslationsLg();
        TranslationsAppli a1_ = new TranslationsAppli();
        TranslationsAppli a2_ = new TranslationsAppli();
        TranslationsFile f1_ = new TranslationsFile();
        TranslationsFile f2_ = new TranslationsFile();
        f1_.add("","");
        f2_.add("","");
        a1_.getMapping().addEntry("", f1_);
        a2_.getMapping().addEntry("", f2_);
        lg_.getMapping().addEntry("0", a1_);
        lg_.getMapping().addEntry("1", a2_);
        tr_.getMapping().addEntry("", lg_);
        assertEq(1,tr_.byAppl("0").size());
        assertEq(1,tr_.byAppl("1").size());
        assertEq(0,tr_.byAppl("").size());
    }
    @Test
    public void extKey() {
        TranslationsFile f_ = new TranslationsFile(1);
        f_.add("0","1=2");
        StringMap<String> m_ = TranslationsFile.extractKeys(f_);
        assertEq(1,m_.size());
        assertEq("1",m_.getVal("0"));
    }
    @Test
    public void extMap() {
        TranslationsFile f_ = new TranslationsFile();
        f_.add("0","1=2");
        StringMap<String> m_ = TranslationsFile.extractMap(f_);
        assertEq(1,m_.size());
        assertEq("2",m_.getVal("1"));
    }
}
