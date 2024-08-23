package code.bean.nat;

import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.formathtml.EquallableBeanCoreUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.util.TranslationsFile;
import code.util.*;
import org.junit.Test;

public final class NatDualConfigurationContextTest extends EquallableBeanCoreUtil {
    @Test
    public void files() {
        NatDualConfigurationContext d_ = new NatDualConfigurationContext();
        d_.getAddedFiles().add("elt.css");
        d_.getAddedFiles().add("not_init");
        StringMap<String> props_ = new StringMap<String>();
        props_.addEntry("prop","prop");
        props_.addEntry("prop2","prop2");
        d_.setProperties(props_);
        d_.setMessagesFolder("sample");
        NatNavigation nav_ = new NatNavigation();
        nav_.setLanguages(new StringList("en","fr"));
        StringMap<String> other_ = new StringMap<String>();
        other_.addEntry("elt.css","*{}");
        other_.addEntry("sample/en/prop","a=b1\nc=d1");
        other_.addEntry("sample/fr/prop","a=b2\nc=d2");
        other_.addEntry("sample/en/prop2","e=b1\nf=d1");
        other_.addEntry("sample/fr/prop2","e=b2\nf=d2");
        StringMap<String> files_ = NatDualConfigurationContext.files(d_, other_);
        assertEq(1,files_.size());
        assertEq("*{}",files_.getVal("elt.css"));
    }
    @Test
    public void docs1() {
        StringMap<Document> in_ = new StringMap<Document>();
        in_.addEntry("", DocumentBuilder.parseSax("<a>t</a>"));
        assertEq("<a>t</a>",in_.getVal("").export());
    }
    @Test
    public void file() {
        TranslationsFile f_ = AnaRendBlockHelp.file("a=b1\nc=d1");
        assertEq(2,f_.getMapping().size());
        assertEq("b1",f_.getMapping().getVal("a"));
        assertEq("d1",f_.getMapping().getVal("c"));
    }
}
