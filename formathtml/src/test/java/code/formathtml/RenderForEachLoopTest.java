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
import static org.junit.Assert.assertTrue;

public final class RenderForEachLoopTest extends CommonRender {
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
}
