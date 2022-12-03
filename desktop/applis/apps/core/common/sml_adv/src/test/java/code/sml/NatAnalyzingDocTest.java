package code.sml;

import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class NatAnalyzingDocTest extends EquallableSmlAdvUtil {
    @Test
    public void test() {
        NatAnalyzingDoc n_ = new NatAnalyzingDoc();
        n_.setRendKeyWords(new RendKeyWordsGroup());
        n_.getRendKeyWords().getKeyWordsAttrs().setAttrAction("");
        ConfigurationCore c_ = new ConfigurationCore();
        c_.setFiles(new StringMap<String>());
        c_.setCurrentLanguage("");
        c_.setPrefix("");
        c_.setTabWidth(5);
        c_.setFirstUrl("");
        n_.setInternGlobalClass("_");
        assertTrue(n_.isInternGlobal());
        n_.setInternGlobalClass("");
        assertFalse(n_.isInternGlobal());
        n_.setupCommon(c_,new StringMap<String>(),"");
        assertEq("",c_.getPrefix());
        assertEq("",n_.getPrefix());
        assertEq("",c_.getFirstUrl());
        n_.setLanguages(new StringList());
        assertEq(0,n_.getLanguages().size());
        assertEq(0,n_.getFiles().size());
        assertEq(0,n_.getProperties().size());
        n_.setNextIndex(15);
        assertEq(15,n_.getNextIndex());
        assertEq(5,c_.getTabWidth());
        assertEq("",n_.getMessagesFolder());
        assertEq("",c_.getCurrentLanguage());
    }
}
