package code.sml;

import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class NavigationCoreTest extends EquallableSmlAdvUtil {
    @Test
    public void setupText1() {
        NavigationCore n_ = new NavigationCore();
        n_.setCurrentUrl("url");
        setupText(n_,"<_/>");
        assertEq("<_/>",n_.getHtmlText());
        assertEq("<_/>",n_.getDocument().export());
        assertEq("",n_.getTitle());
        assertEq("",n_.getReferenceScroll());
        assertEq("url",n_.getCurrentUrl());
    }
    @Test
    public void setupText2() {
        NavigationCore n_ = new NavigationCore();
        n_.setCurrentUrl("url");
        setupText(n_,"<head><title>TITLE</title></head>");
        assertEq("TITLE",n_.getTitle());
        assertEq("",n_.getReferenceScroll());
        assertEq("url",n_.getCurrentUrl());
    }
    @Test
    public void setupText3() {
        NavigationCore n_ = new NavigationCore();
        n_.setCurrentUrl("url#local");
        setupText(n_,"<head><title>TITLE</title></head>");
        assertEq("TITLE",n_.getTitle());
        assertEq("local",n_.getReferenceScroll());
        assertEq("url",n_.getCurrentUrl());
    }
    @Test
    public void setupText4() {
        NavigationCore n_ = new NavigationCore();
        n_.setLanguage("");
        n_.setFiles(new StringMap<String>());
        n_.setCurrentBeanName("");
        n_.setLanguages(new StringList());
        setupTextEmpty(n_);
        assertEq("",n_.getTitle());
        assertEq("",n_.getLanguage());
        assertEq("",n_.getCurrentBeanName());
        assertEq(0,n_.getFiles().size());
        assertEq(0,n_.getLanguages().size());
    }
    private static void setupText(NavigationCore _al,String _txt) {
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(_txt);
        Document document_ = res_.getDocument();
        _al.setupText(_txt,document_,"head","title");
    }
    private static void setupTextEmpty(NavigationCore _al) {
        _al.setupText("",null,"head","title");
    }
}
