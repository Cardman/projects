package code.sml;

import code.util.LongMap;
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
        ExecTextPart e_ = new ExecTextPart();
        e_.getTexts().add("_");
        assertEq(1,e_.getTexts().size());
        FieldUpdates f_ = new FieldUpdates();
        f_.setClassName("");
        assertEq("",f_.getClassName());
        FormParts i_ = new FormParts();
        i_.setCurrentForm(2);
        assertEq(2,i_.getCurrentForm());
        i_.initForms();
        assertEq(0,i_.getCurrentForm());
        i_.getIndexes().setAnchor(0);
        IndexesFormInput.incr(i_.getIndexes());
        assertEq(1,i_.getIndexes().getAnchor());
        HtmlPage h_ = new HtmlPage();
        h_.setBase(i_);
        h_.setFormatIdMap(new LongMap<StringList>());
        h_.setForm(true);
        assertTrue(h_.isForm());
        h_.setForm(false);
        assertFalse(h_.isForm());
        assertEq(0,h_.getFormatIdMap().size());
        assertEq(0,i_.getFormatIdMapStack().size());
        assertEq(0,i_.getFormsNb().size());
        assertEq(0,i_.getInputs().size());
        h_.setUrl(0);
        assertEq(0,h_.getUrl());
        NodeContainer nc_ = new NodeContainer();
        nc_.getNodeInformation().setValue(new StringList());
        assertEq(0,nc_.getNodeInformation().getValue().size());
        nc_.getNodeInformation().setEnabled(true);
        assertTrue(nc_.getNodeInformation().isEnabled());
        nc_.getNodeInformation().setEnabled(false);
        assertFalse(nc_.getNodeInformation().isEnabled());
        nc_.getNodeInformation().setId("");
        nc_.getNodeInformation().setValidator("");
        nc_.getNodeInformation().setInputClass("");
        assertEq("", nc_.getNodeInformation().getId());
        assertEq("", nc_.getNodeInformation().getInputClass());
        assertEq("", nc_.getNodeInformation().getValidator());
    }
}
