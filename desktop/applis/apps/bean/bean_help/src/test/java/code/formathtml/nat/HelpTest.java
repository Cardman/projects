package code.formathtml.nat;

import code.bean.help.HelpCaller;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.NatNavigation;
import code.bean.nat.analyze.NatConfigurationCore;
//import code.formathtml.Configuration;
import code.formathtml.EquallableBeanHelpUtil;
//import code.formathtml.Navigation;
//import code.formathtml.util.DualConfigurationContext;
import code.formathtml.render.*;
import code.images.BaseSixtyFourUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.CustList;
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
        MetaDocument doc_ = text("page1.html", html_, files_, folder_, pr_,new StringMap<int[][]>());
        CustList<MetaComponent> chs_ = doc_.getRoot().getChildren();
        assertEq(1,chs_.size());
        CustList<MetaComponent> chs1_ = ((MetaLine)chs_.get(0)).getChildren();
        assertEq(1,chs1_.size());
        MetaSimpleImage img_ = (MetaSimpleImage) chs1_.get(0);
        assertEq(0,img_.getImage().length);
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
        MetaDocument doc_ = text("page1.html", html_, files_, folder_, pr_,new StringMap<int[][]>());
        CustList<MetaComponent> chs_ = doc_.getRoot().getChildren();
        assertEq(3,chs_.size());
        CustList<MetaComponent> chs1_ = ((MetaLine)chs_.get(0)).getChildren();
        assertEq(0,chs1_.size());
        CustList<MetaComponent> chs2_ = ((MetaLine)chs_.get(1)).getChildren();
        assertEq(2,chs2_.size());
        assertEq("",((MetaIndentLabel)chs2_.get(0)).getText());
        CustList<MetaComponent> chs3_ = ((MetaOrderedList)chs2_.get(1)).getChildren();
        assertEq(1,chs3_.size());
        assertEq("ONE",((MetaPlainLabel)chs3_.get(0)).getText());
        CustList<MetaComponent> chs4_ = ((MetaLine)chs_.get(2)).getChildren();
        assertEq(0,chs4_.size());
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
        MetaDocument doc_ = text("page1.html", html_, files_, folder_, pr_,new StringMap<int[][]>());
        CustList<MetaComponent> chs_ = doc_.getRoot().getChildren();
        assertEq(3,chs_.size());
        CustList<MetaComponent> chs1_ = ((MetaLine)chs_.get(0)).getChildren();
        assertEq(0,chs1_.size());
        CustList<MetaComponent> chs2_ = ((MetaLine)chs_.get(1)).getChildren();
        assertEq(2,chs2_.size());
        assertEq("",((MetaIndentLabel)chs2_.get(0)).getText());
        CustList<MetaComponent> chs3_ = ((MetaOrderedList)chs2_.get(1)).getChildren();
        assertEq(1,chs3_.size());
        assertEq("Message",((MetaPlainLabel)chs3_.get(0)).getText());
        CustList<MetaComponent> chs4_ = ((MetaLine)chs_.get(2)).getChildren();
        assertEq(0,chs4_.size());
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
        MetaDocument doc_ = text("page1.html", html_, files_, folder_, pr_,new StringMap<int[][]>());
        CustList<MetaComponent> chs_ = doc_.getRoot().getChildren();
        assertEq(3,chs_.size());
        CustList<MetaComponent> chs1_ = ((MetaLine)chs_.get(0)).getChildren();
        assertEq(0,chs1_.size());
        CustList<MetaComponent> chs2_ = ((MetaLine)chs_.get(1)).getChildren();
        assertEq(3,chs2_.size());
        assertEq("",((MetaIndentLabel)chs2_.get(0)).getText());
        CustList<MetaComponent> chs3_ = ((MetaOrderedList)chs2_.get(1)).getChildren();
        assertEq(2,chs3_.size());
        assertEq("Message",((MetaPlainLabel)chs3_.get(0)).getText());
        assertEq("",((MetaEndLine)chs3_.get(1)).getText());
        CustList<MetaComponent> chs4_ = ((MetaLine)chs2_.get(2)).getChildren();
        assertEq(1,chs4_.size());
        assertEq("Two",((MetaPlainLabel)chs4_.get(0)).getText());
        CustList<MetaComponent> chs5_ = ((MetaLine)chs_.get(2)).getChildren();
        assertEq(0,chs5_.size());
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
        MetaDocument doc_ = text("page1.html", html_, files_, folder_, pr_,new StringMap<int[][]>());
        CustList<MetaComponent> chs_ = doc_.getRoot().getChildren();
        assertEq(2,chs_.size());
        CustList<MetaComponent> chs1_ = ((MetaLine)chs_.get(0)).getChildren();
        assertEq(0,chs1_.size());
        CustList<MetaComponent> chs2_ = ((MetaLine)chs_.get(1)).getChildren();
        assertEq(2,chs2_.size());
        assertEq("",((MetaIndentLabel)chs2_.get(0)).getText());
        CustList<MetaComponent> chs3_ = ((MetaOrderedList)chs2_.get(1)).getChildren();
        assertEq(0,chs3_.size());
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
        MetaDocument doc_ = text("page1.html", html_, files_, folder_, pr_,new StringMap<int[][]>());
        CustList<MetaComponent> chs_ = doc_.getRoot().getChildren();
        assertEq(1,chs_.size());
        CustList<MetaComponent> chs2_ = ((MetaLine)chs_.get(0)).getChildren();
        assertEq(1,chs2_.size());
        assertEq("Esc'ape",((MetaPlainLabel)chs2_.get(0)).getText());
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
        StringMap<int[][]> filesImgs_ = new StringMap<int[][]>();
        files_.put(EquallableBeanHelpUtil.formatFile(folder_,locale_,relative_), content_);
        StringList add_ = new StringList();
        add_.add("added");
        StringMap<String> pr_ = new StringMap<String>();
        pr_.put("msg_example", relative_);
        filesImgs_.put("added", BaseSixtyFourUtil.getImageByString("AAACAAABAAADAAAEAAAFAAAGAAAH"));
//        HelpRendBlockHelp.text("","page1.html",html_,files_,folder_,pr_);
        MetaDocument doc_ = text("page1.html", html_, add_, files_, folder_, pr_,filesImgs_);
        CustList<MetaComponent> chs_ = doc_.getRoot().getChildren();
        assertEq(1,chs_.size());
        CustList<MetaComponent> chs1_ = ((MetaLine)chs_.get(0)).getChildren();
        assertEq(1,chs1_.size());
        MetaSimpleImage img_ = (MetaSimpleImage) chs1_.get(0);
        int[][] arr_ = img_.getImage();
        assertEq(3, arr_.length);
        assertEq(2, arr_[0].length);
        assertEq(1, arr_[0][0]);
        assertEq(3, arr_[0][1]);
        assertEq(2, arr_[1].length);
        assertEq(4, arr_[1][0]);
        assertEq(5, arr_[1][1]);
        assertEq(2, arr_[2].length);
        assertEq(6, arr_[2][0]);
        assertEq(7, arr_[2][1]);
//        assertEq("<html><body><ul>Message</ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
    }
    public static MetaDocument text(String _realFilePath, String _uniq, StringMap<String> _ms, String _messagesFolder, StringMap<String> _properties, StringMap<int[][]> _imgs) {
        return text(_realFilePath, _uniq,new StringList(), _ms, _messagesFolder, _properties,_imgs);
    }

    public static MetaDocument text(String _realFilePath, String _uniq, StringList _add, StringMap<String> _ms, String _messagesFolder, StringMap<String> _properties, StringMap<int[][]> _imgs) {
        NatNavigation navigation_= new NatNavigation();
        NatConfigurationCore session_ = new NatConfigurationCore();
        session_.setPrefix("c:");
        navigation_.setSession(session_);
        navigation_.setLanguage("en");
        navigation_.setLanguages(new StringList("en"));
        NatDualConfigurationContext contextConf_ = new NatDualConfigurationContext();
        contextConf_.setMessagesFolder(_messagesFolder);
        contextConf_.setProperties(_properties);
        contextConf_.setAddedFiles(_add);
        return HelpCaller.text(contextConf_, navigation_, _realFilePath, DocumentBuilder.parseSaxNotNullRowCol(_uniq).getDocument(), _ms, "en",_imgs);
//        return text_.export();
    }

}
