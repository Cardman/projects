package code.formathtml.nat;

import code.bean.help.HelpCaller;
import code.formathtml.*;
import code.util.*;
import org.junit.Test;

public final class HelpTest extends EquallableExUtil {
    @Test
    public void process___1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Message\ntwo=Two";
        String html_ = "<html><body><img value='val'/></body></html>";
//        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><img value=\"val\"/></body></html>", HelpCaller.text(locale_,"page1.html",html_,files_,folder_,pr_));
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
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><ul>ONE</ul></body></html>", HelpCaller.text(locale_,"page1.html",html_,files_,folder_,pr_));
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
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><ul>Message</ul></body></html>", HelpCaller.text(locale_,"page1.html",html_,files_,folder_,pr_));
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
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><ul>Message<br/>Two</ul></body></html>", HelpCaller.text(locale_,"page1.html",html_,files_,folder_,pr_));
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
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><ul value=\"val\"/></body></html>", HelpCaller.text(locale_,"page1.html",html_,files_,folder_,pr_));
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
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body>Esc'ape</body></html>", HelpCaller.text(locale_,"page1.html",html_,files_,folder_,pr_));
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
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        StringList add_ = new StringList();
        add_.add("added");
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
        files_.put("added", "IMG");
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        assertEq("<html><body><img src=\"IMG\"/></body></html>", HelpCaller.text(locale_,"page1.html",html_,add_,files_,folder_,pr_));
//        assertEq("<html><body><ul>Message</ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
    }
}
