package code.formathtml.nat;

import code.bean.help.HelpCaller;
import code.bean.nat.NatDualConfigurationContext;
import code.formathtml.Configuration;
import code.formathtml.EquallableBeanHelpUtil;
import code.formathtml.Navigation;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class HelpTest extends EquallableBeanHelpUtil {

    @Test
    public void process___1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Message\ntwo=Two";
        String html_ = "<html><body><img value='val'/></body></html>";
//        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanHelpUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><img value=\"val\"/></body></html>", text("page1.html",html_,files_,folder_,pr_));
//        assertEq("<html><body><ul>Message</ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
    }
    @Test
    public void process___2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Message";
        String html_ = "<html><body><ul>ONE</ul></body></html>";
//        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanHelpUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><ul>ONE</ul></body></html>", text("page1.html",html_,files_,folder_,pr_));
//        assertEq("<html><body><ul>Message</ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
    }
    @Test
    public void process___3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Message";
        String html_ = "<html><body><ul><c:message value='msg_example,one'/></ul></body></html>";
//        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanHelpUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><ul>Message</ul></body></html>", text("page1.html",html_,files_,folder_,pr_));
//        assertEq("<html><body><ul>Message</ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
    }
    @Test
    public void process___4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Message\ntwo=Two";
        String html_ = "<html><body><ul><c:message value='msg_example,one'/><br/><c:message value='msg_example,two'/></ul></body></html>";
//        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanHelpUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><ul>Message<br/>Two</ul></body></html>", text("page1.html",html_,files_,folder_,pr_));
//        assertEq("<html><body><ul>Message</ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
    }
    @Test
    public void process___5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Message\ntwo=Two";
        String html_ = "<html><body><ul value='val'/></body></html>";
//        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanHelpUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><ul value=\"val\"/></body></html>", text("page1.html",html_,files_,folder_,pr_));
//        assertEq("<html><body><ul>Message</ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
    }

    @Test
    public void process___6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Message\ntwo=Two";
        String html_ = "<html><body>Esc''ape</body></html>";
//        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanHelpUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body>Esc'ape</body></html>", text("page1.html",html_,files_,folder_,pr_));
//        assertEq("<html><body><ul>Message</ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
    }
    @Test
    public void process___7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Message\ntwo=Two";
        String html_ = "<html><body><img src='added'/></body></html>";
//        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanHelpUtil.formatFile(folder_,locale_,relative_), content_);
        StringList add_ = new StringList();
        add_.add("added");
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
        files_.put("added", "IMG");
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><img src=\"IMG\"/></body></html>", text("page1.html",html_,add_,files_,folder_,pr_));
//        assertEq("<html><body><ul>Message</ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
    }
    public static String text(String _realFilePath, String _uniq, StringMap<String> _ms, String _messagesFolder, StringMap<String> _properties) {
        return text(_realFilePath, _uniq,new StringList(), _ms, _messagesFolder, _properties);
    }

    public static String text(String _realFilePath, String _uniq, StringList _add, StringMap<String> _ms, String _messagesFolder, StringMap<String> _properties) {
        Navigation navigation_= new Navigation();
        Configuration session_ = new Configuration();
        session_.setPrefix("c:");
        navigation_.setSession(session_);
        navigation_.setLanguage("en");
        navigation_.setLanguages(new StringList("en"));
        NatDualConfigurationContext contextConf_ = new NatDualConfigurationContext();
        contextConf_.setMessagesFolder(_messagesFolder);
        contextConf_.setProperties(_properties);
        contextConf_.setAddedFiles(_add);
        Document text_ = HelpCaller.text(contextConf_, navigation_, _realFilePath, DocumentBuilder.parseSaxNotNullRowCol(_uniq).getDocument(), _ms, "en");
        return text_.export();
    }

}
