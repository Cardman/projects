package code.renders;

import code.formathtml.ReadConfiguration;
import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.mock.MockPanel;
import code.mock.MockThreadFactory;
import code.renders.utilcompo.LgNamesRenderUtils;
import code.sml.Element;
import code.stream.StreamBinaryFile;
import code.stream.StreamTextFile;
import code.stream.core.ContentTime;
import code.threads.AbstractThread;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class WindowRendersTest extends EquallableRendersGuiUtil {
    @Test
    public void r0() {
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml\n"+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText("/");
        w_.loadRenderConf("/conf.txt");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
    }
    @Test
    public void r1() {
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml\n"+StringUtil.EN, pr_.getStreams());
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
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf_cl.txt'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<_ "+ReadConfiguration.FIELD+"='"+ReadConfiguration.INIT_DB+"' "+ReadConfiguration.VALUE+"='cl.Init.init'/>\n" +
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
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml\n"+StringUtil.EN, pr_.getStreams());
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
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='styleValues' "+ReadConfiguration.VALUE+"='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf_cl.txt'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page_.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page_.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<_ "+ReadConfiguration.FIELD+"='"+ReadConfiguration.INIT_DB+"' "+ReadConfiguration.VALUE+"='cl.Init.init'/>\n" +
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
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml\n"+StringUtil.EN, pr_.getStreams());
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
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='styleValues' "+ReadConfiguration.VALUE+"='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf_cl.txt'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='cl.Init'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page_.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page_.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<_ "+ReadConfiguration.FIELD+"='"+ReadConfiguration.INIT_DB+"' "+ReadConfiguration.VALUE+"='cl.Init.init'/>\n" +
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
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml\n"+StringUtil.EN, pr_.getStreams());
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
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml\n"+StringUtil.EN+"\nlgs="+StringUtil.EN, pr_.getStreams());
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
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml\n_\nlgs="+StringUtil.EN, pr_.getStreams());
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
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml", pr_.getStreams());
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
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml", pr_.getStreams());
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
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml", pr_.getStreams());
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
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='styleValues' "+ReadConfiguration.VALUE+"='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf_cl.txt'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='cl.Init'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<>lt&60;gt&62;amp&38;quot&34;<html c:bean='bean_one'><body><c:set className='var' value='i=\"1\"'/><c:set className='var' value='j=\"2\"'/><c:set className='var' value='k=(cl.Inter)((:int)-&gt;{return 1;})'/>_{Class.getClass(new Rate(i+'/'+j))}_{Class.getClass(new Rate(i))}_{this}</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init:$core.Bean{public void beforeDisplaying(){}public static Object init(String[] names, String[] contents){return new String[0];}}public interface cl.Inter{int call();}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml", pr_.getStreams());
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
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","_\ninitDb=conf.xml", pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        w_.loadRenderConf("/conf.txt");
        assertEq(0, ((MockThreadFactory) pr_.getThreadFactory()).getAllThreads().size());
    }
    @Test
    public void r12() {
        String xmlConf_ = cfg();
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
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml\n"+StringUtil.EN, pr_.getStreams());
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
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\ninitDb=conf.xml\n"+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        tryClick(w_.getOpen());
        w_.getFileOpenFrame().getFileDialogContent().getFileName().setText("/conf.txt");
        tryClick((AbsButton) ((MockPanel)w_.getFileOpenFrame().getFileDialogContent().getButtons()).getComponent(0));
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        Runnable exec_ = th_.getRunnable();
        assertTrue(((CustThreadActions)exec_).isRendered());
    }
    @Test
    public void r15() {
        String xmlConf_ = cfg();
        CreateMainWindowRenders cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        StringMap<ContentTime> cont_ = with(pr_, with(pr_, with(pr_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(pr_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(pr_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(pr_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(pr_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nconf.xml\n"+StringUtil.EN, pr_.getStreams());
        cr_.run();
        WindowRenders w_ = cr_.getWindow();
        w_.getLgCode().setText(StringUtil.EN);
        tryClick(w_.getOpen());
        w_.getFileOpenFrame().getFileDialogContent().getFileName().setText("/conf.txt");
        tryClick((AbsButton) ((MockPanel)w_.getFileOpenFrame().getFileDialogContent().getButtons()).getComponent(0));
        assertEq(0, ((MockThreadFactory) pr_.getThreadFactory()).getAllThreads().size());
    }

    private String cfg() {
        return "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+LgNamesRenderUtils.STYLE_VALUES+"' "+ReadConfiguration.VALUE+"='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf_cl.txt'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<_ "+ReadConfiguration.FIELD+"='"+ReadConfiguration.INIT_DB+"' "+ReadConfiguration.VALUE+"='cl.Init.init'/>\n" +
                "</cfg>";
    }
}
