package code.bean.nat;

import code.formathtml.EquallableBeanCoreUtil;
import code.formathtml.Navigation;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import code.util.consts.Constants;
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
        Navigation nav_ = new Navigation();
        nav_.setLanguages(Constants.getAvailableLanguages());
        StringMap<String> other_ = new StringMap<String>();
        other_.addEntry("elt.css","*{}");
        other_.addEntry("sample/en/prop.properties","a=b1\nc=d1");
        other_.addEntry("sample/fr/prop.properties","a=b2\nc=d2");
        other_.addEntry("sample/en/prop2.properties","e=b1\nf=d1");
        other_.addEntry("sample/fr/prop2.properties","e=b2\nf=d2");
        StringMap<String> files_ = NatDualConfigurationContext.files(nav_, d_, other_,other_,"");
        assertEq(5,files_.size());
        assertEq("*{}",files_.getVal("elt.css"));
        assertEq("a=b1\nc=d1",files_.getVal("sample/en/prop.properties"));
        assertEq("a=b2\nc=d2",files_.getVal("sample/fr/prop.properties"));
        assertEq("e=b1\nf=d1",files_.getVal("sample/en/prop2.properties"));
        assertEq("e=b2\nf=d2",files_.getVal("sample/fr/prop2.properties"));
    }
    @Test
    public void docs1() {
        StringMap<Document> in_ = new StringMap<Document>();
        in_.addEntry("", DocumentBuilder.parseSax("<a>t</a>"));
        StringMap<Document> res_ = NatDualConfigurationContext.docs(in_, "");
        assertEq("<a>t</a>",res_.getVal("").export());
    }
}
