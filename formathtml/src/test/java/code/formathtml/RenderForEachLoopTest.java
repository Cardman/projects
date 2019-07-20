package code.formathtml;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.MyTranslator;
import code.formathtml.util.BeanStruct;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class RenderForEachLoopTest extends CommonRender {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_LIST_PATH = "pkg/CustList";
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml' bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='java.lang.String'><li>{s;length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        Document doc_ = DocumentBuilder.parseSax(html_);
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.getBeans().put("bean_one", bean_);
        context_.getBuiltBeans().put("bean_one",new BeanStruct(bean_));
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        context_.setTranslators(new StringMap<Translator>());
        context_.getTranslators().put("trans", new MyTranslator());
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html xmlns:c=\"javahtml\" bean=\"bean_one\"><body><ul><li>5</li><li>6</li></ul></body></html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml' bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='$var'><li>{s;length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        Document doc_ = DocumentBuilder.parseSax(html_);
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.getBeans().put("bean_one", bean_);
        context_.getBuiltBeans().put("bean_one",new BeanStruct(bean_));
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        context_.setTranslators(new StringMap<Translator>());
        context_.getTranslators().put("trans", new MyTranslator());
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html xmlns:c=\"javahtml\" bean=\"bean_one\"><body><ul><li>5</li><li>6</li></ul></body></html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:for var=\"s\" list=\"$new java.lang.String[]{&quot;FIRST&quot;,&quot;SECOND&quot;}\" className='java.lang.String'><li>{s;length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        Document doc_ = DocumentBuilder.parseSax(html_);
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.getBeans().put("bean_one", bean_);
        context_.getBuiltBeans().put("bean_one",new BeanStruct(bean_));
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        context_.setTranslators(new StringMap<Translator>());
        context_.getTranslators().put("trans", new MyTranslator());
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
    @Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:for var=\"s\" list=\"$new java.lang.String[]{&quot;FIRST&quot;,&quot;SECOND&quot;}\" className='$var'><li>{s;length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        Document doc_ = DocumentBuilder.parseSax(html_);
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.getBeans().put("bean_one", bean_);
        context_.getBuiltBeans().put("bean_one",new BeanStruct(bean_));
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        context_.setTranslators(new StringMap<Translator>());
        context_.getTranslators().put("trans", new MyTranslator());
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:for var=\"s\" list=\"$new java.lang.Integer[]{$null}\" className='$int'><li>{s;}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        Document doc_ = DocumentBuilder.parseSax(html_);
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.getBeans().put("bean_one", bean_);
        context_.getBuiltBeans().put("bean_one",new BeanStruct(bean_));
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        context_.setTranslators(new StringMap<Translator>());
        context_.getTranslators().put("trans", new MyTranslator());
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        FormatHtml.getRes(rendDocumentBlock_, context_);
        assertNotNull(context_.getException());
    }

    @Test
    public void process6Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        Configuration context_ = contextElThird(files_);
        Document documentResult_ = DocumentBuilder.parseSaxNotNullRowCol("<html><c:for var=\"i\" list=\"$new pkg.CustList&lt;java.lang.Integer&gt;(0,1,2,3)\" className='$int'>{i;}-<c:if condition=\"i;%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>").getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", documentResult_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }

    @Test
    public void process7Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        Configuration context_ = contextElThird(files_);
        Document documentResult_ = DocumentBuilder.parseSaxNotNullRowCol("<html><c:for var=\"i\" list=\"$new pkg.CustList&lt;java.lang.Integer&gt;()\" className='$int'>{i;}-<c:if condition=\"i;%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>").getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", documentResult_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html/>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<#U> :$iterable<#U>{\n");
        xml_.append(" $private #U[] list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $public (){\n");
        xml_.append("  list;;;=$new #U[0i]:\n");
        xml_.append(" }\n");
        xml_.append(" $public (#U... args){\n");
        xml_.append("  list;;;=args;.;:\n");
        xml_.append("  length;;;=args;.;length:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(#U elt){\n");
        xml_.append("  add(length;;;,elt;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,#U elt){\n");
        xml_.append("  #U[] newlist=$new #U[length;;;+1i]:\n");
        xml_.append("  $iter($int i=0i:index;.;:1i){\n");
        xml_.append("   newlist;.[i;]=list;;;[i;]:\n");
        xml_.append("  }\n");
        xml_.append("  newlist;.[index;.;]=elt;.;:\n");
        xml_.append("  $iter($int i=index;.;+1i:length;;;+1i:1i){\n");
        xml_.append("   newlist;.[i;]=list;;;[i;-1i]:\n");
        xml_.append("  }\n");
        xml_.append("  length;;;++:\n");
        xml_.append("  list;;;=newlist;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #U get($int index){\n");
        xml_.append("  $return list;;;[index;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,#U elt){\n");
        xml_.append("  list;;;[index;.;]=elt;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;.;:length;;;-1i:1i){\n");
        xml_.append("   list;;;[i;]=list;;;[i;+1i]:\n");
        xml_.append("  }\n");
        xml_.append("  list;;;[length;;;-1i]=$null:\n");
        xml_.append("  length;;;--:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<#U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<#U>($this):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<#T> :$iterator<#T>{\n");
        xml_.append(" $private pkg.CustList<#T> list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $private $int index:\n");
        xml_.append(" $public (pkg.CustList<#T> i){\n");
        xml_.append("  list;;;=i;.;:\n");
        xml_.append("  length;;;=list;;;size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T next(){\n");
        xml_.append("  #T out=list;;;get(index;;;):\n");
        xml_.append("  index;;;++:\n");
        xml_.append("  $return out;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index;;;<length;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
