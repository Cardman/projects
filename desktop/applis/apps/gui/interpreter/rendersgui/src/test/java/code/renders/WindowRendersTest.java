package code.renders;

import code.expressionlanguage.utilcompo.RateStruct;
import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.Rate;
import code.mock.MockThreadFactory;
import code.renders.utilcompo.LgNamesRenderUtils;
import code.sml.Element;
import code.stream.StreamBinaryFile;
import code.stream.StreamTextFile;
import code.stream.core.ContentTime;
import code.threads.AbstractThread;
import code.threads.ConcreteInteger;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class WindowRendersTest extends EquallableRendersGuiUtil {
    @Test
    public void r1() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\ninitDb=cl.Init.init\n"+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
    }
    @Test
    public void r2() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\ninitDb=cl.Init.init\n"+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText("");
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertFalse(((CustThreadActions)exec_).isRendered());
//        pr_.getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        w_.changeLanguage("");
        w_.quit();
        GuiBaseUtil.tryToReopen(w_.getApplicationName(),w_.getFrames());
    }
    @Test
    public void r3() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t\t<str value='page_.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t\t<str value='page_.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,with(pr_,contConf_,"page.html","<html><body><a href=\"page_.html\">_</a></body></html>"),"page_.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\ninitDb=cl.Init.init\n"+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
        w_.getSession().getStandards().getPage().setForm(false);
        w_.getSession().getStandards().getPage().setUrl(0);
        Element elt_ = w_.getSession().getNavCore().getDocument().getElementsByTagName("a").item(0);
        assertNull(w_.getSession().getRenderAction().execute(false,elt_));
    }
    @Test
    public void r4() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='cl.Init'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t\t<str value='page_.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t\t<str value='page_.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,with(pr_,contConf_,"page.html","<html c:bean='bean_one'><body><a c:command=\"redirectBad\">_</a></body></html>"),"page_.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init:$core.Bean{public void beforeDisplaying(){}public static Object init(String[] names, String[] contents){return new String[0];}public int redirectBad(){return 1/0;}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\ninitDb=cl.Init.init\n"+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
        w_.getSession().getStandards().getPage().setForm(false);
        w_.getSession().getStandards().getPage().setUrl(0);
        Element elt_ = w_.getSession().getNavCore().getDocument().getElementsByTagName("a").item(0);
        assertNotNull(w_.getSession().getRenderAction().execute(false,elt_));
    }
    @Test
    public void r5() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\ninitDb=cl.Init.init\n"+StringUtil.EN+"\nlgs="+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
    }
    @Test
    public void r6() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\ninitDb=cl.Init.init\n_\nlgs="+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
    }
    @Test
    public void r7() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\ninitDb=cl.Init.init", pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
    }
    @Test
    public void r8() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\ninitDb=_", pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
    }
    @Test
    public void r9() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\n_", pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
    }
    @Test
    public void r10() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='cl.Init'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html c:bean='bean_one'><body><c:set className='var' value='i=\"1\"'/><c:set className='var' value='j=\"2\"'/><c:set className='var' value='k=(cl.Inter)((:int)-&gt;{return 1;})'/>_{Class.getClass(new Rate(i+'/'+j))}_{Class.getClass(new Rate(i))}_{this}</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init:$core.Bean{public void beforeDisplaying(){}public static Object init(String[] names, String[] contents){return new String[0];}}public interface cl.Inter{int call();}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml", pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
        ((LgNamesRenderUtils)((CustThreadActions)exec_).getInit().getContext().getStandards()).getStrAlias();
    }
    @Test
    public void r11() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","_\nconf.xml", pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        assertEq(0, ((MockThreadFactory) pr_.getThreadFactory()).getAllThreads().size());
    }
    @Test
    public void r12() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create("/conf.txt");
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","_", pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        assertEq(0, ((MockThreadFactory) pr_.getThreadFactory()).getAllThreads().size());
    }
    @Test
    public void r13() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\ninitDb=cl.Init.init\n"+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.getPath().setText("/conf.txt");
        tryClick(w_.getOpen());
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
    }
    @Test
    public void r14() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\ninitDb=cl.Init.init\n"+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        tryClick(w_.getOpen());
        w_.getFileOpenFrame().getFileDialogContent().getFileName().setText("/conf.txt");
        tryClick((AbsButton) w_.getFileOpenFrame().getFileDialogContent().getButtons().getComponent(0));
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
    }
}
