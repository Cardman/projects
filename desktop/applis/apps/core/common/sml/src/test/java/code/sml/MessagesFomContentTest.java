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
        tr_.getMapping().addEntry("", lg_);
        assertEq("",tr_.getMapping().getVal("").getMapping().getVal("").getMapping().getVal("").getMapping().getVal(""));
    }
}
