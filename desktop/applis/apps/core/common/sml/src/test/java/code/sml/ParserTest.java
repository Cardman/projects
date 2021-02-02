package code.sml;

import org.junit.Test;

public class ParserTest extends EquallableRowColUtil {

    @Test
    public void parseSaxHtmlRowCol1Test() {
        assertNotNull(DocumentBuilder.parseSaxHtmlRowCol("<tag>e</tag>"));
    }

    @Test
    public void parseSax1Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>e</tag>"));
    }

    @Test
    public void parseSax2Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>\u00E9</tag>"));
    }

    @Test
    public void parseSax3Test() {
        assertNull(DocumentBuilder.parseSax("<tag>"));
    }

    @Test
    public void parseSax4Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag/>"));
    }

    @Test
    public void parseSax5Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#233;</tag>"));
    }

    @Test
    public void parseSax6Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&eacute;</tag>"));
    }

    @Test
    public void parseSax7Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml1Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>e</tag>"));
    }

    @Test
    public void parseSaxHtml2Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>\u00E9</tag>"));
    }

    @Test
    public void parseSaxHtml3Test() {
        assertNull(DocumentBuilder.parseSax("<tag>"));
    }

    @Test
    public void parseSaxHtml4Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag/>"));
    }

    @Test
    public void parseSaxHtml5Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#233;</tag>"));
    }

    @Test
    public void parseSaxHtml6Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&eacute;</tag>"));
    }

    @Test
    public void parseSaxHtml7Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml8Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a='inner'>&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml9Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a=''>&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml10Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a=\"inner\">&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml11Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a=\"\">&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml12Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a ='inner'>&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml13Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a =''>&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml14Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a =\"inner\">&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml15Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a =\"\">&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml16Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= 'inner'>&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml17Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= ''>&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml18Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= \"inner\">&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml19Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= \"\">&#0;</tag>"));
    }

    @Test
    public void parseSaxHtml20Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#0;<b/></tag>"));
    }

    @Test
    public void parseSaxHtml21Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#0;<b></b></tag>"));
    }

    @Test
    public void parseSaxHtml22Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag>&#0;<b>Text</b></tag>"));
    }

    @Test
    public void parseSaxHtml23Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= \"inner\"><b>&#0;</b></tag>"));
    }

    @Test
    public void parseSaxHtml24Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= \"inner\"><b></b></tag>"));
    }

    @Test
    public void parseSaxHtml25Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= \"inner\"><b/></tag>"));
    }

    @Test
    public void parseSaxHtml26Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= \"inner\"><b a= \"outer\"/></tag>"));
    }

    @Test
    public void parseSaxHtml27Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= \"inner\"><b>&#0;</b><c>&#0;</c></tag>"));
    }

    @Test
    public void parseSaxHtml28Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= \"inner\"><b>&#0;</b>After</tag>"));
    }

    @Test
    public void parseSaxHtml29Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= \"inner\"/>"));
    }

    @Test
    public void parseSaxHtml30Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag a= \"'&apos;&quot;\" b= '\"&apos;&quot;'/>"));
    }

    @Test
    public void parseSaxHtml31Test() {
        assertNotNull(DocumentBuilder.parseSax("<tag b= '\"&apos;&quot;' a= \"'&apos;&quot;\"/>"));
    }

    @Test
    public void parseSaxHtml32Test() {
        assertNull(DocumentBuilder.parseSax("<tag><"));
    }

    @Test
    public void parseSaxHtml33Test() {
        assertNull(DocumentBuilder.parseSax("<tag></"));
    }

    @Test
    public void parseSaxHtml34Test() {
        assertNull(DocumentBuilder.parseSax("<tag></t"));
    }

    @Test
    public void parseSaxHtml35Test() {
        assertNull(DocumentBuilder.parseSax("<tag></tague"));
    }

    @Test
    public void parseSaxHtml36Test() {
        assertNull(DocumentBuilder.parseSax("<tag></tag"));
    }

    @Test
    public void parseSaxHtml37Test() {
        assertNull(DocumentBuilder.parseSax("<outer><tag remove='inner'/>"));
    }

    @Test
    public void parseSaxHtml38Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove='inner'/<"));
    }

    @Test
    public void parseSaxHtml39Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove='inner'/"));
    }

    @Test
    public void parseSaxHtml40Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove='inner'"));
    }

    @Test
    public void parseSaxHtml41Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove='inner"));
    }

    @Test
    public void parseSaxHtml42Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove='inner>"));
    }

    @Test
    public void parseSaxHtml43Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove='inner<"));
    }

    @Test
    public void parseSaxHtml44Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove='inner&"));
    }

    @Test
    public void parseSaxHtml45Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove='inner&"));
    }

    @Test
    public void parseSaxHtml46Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove='"));
    }
    @Test
    public void parseSaxHtml47Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove=' "));
    }

    @Test
    public void parseSaxHtml48Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove= a"));
    }
    @Test
    public void parseSaxHtml49Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove=a"));
    }
    @Test
    public void parseSaxHtml50Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove="));
    }
    @Test
    public void parseSaxHtml500Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove= "));
    }
    @Test
    public void parseSaxHtml51Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove>"));
    }

    @Test
    public void parseSaxHtml52Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove<"));
    }

    @Test
    public void parseSaxHtml53Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove&"));
    }

    @Test
    public void parseSaxHtml54Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove/"));
    }
    @Test
    public void parseSaxHtml55Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove "));
    }
    @Test
    public void parseSaxHtml56Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove /"));
    }
    @Test
    public void parseSaxHtml57Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove"));
    }
    @Test
    public void parseSaxHtml58Test() {
        assertNull(DocumentBuilder.parseSax("<tag remove='' remove=''/>"));
    }
    @Test
    public void parseSaxHtml59Test() {
        assertNull(DocumentBuilder.parseSax("<outer><tag>t>"));
    }
    @Test
    public void parseSaxHtml60Test() {
        assertNull(DocumentBuilder.parseSax("<outer><tag>t<"));
    }
    @Test
    public void parseSaxHtml61Test() {
        assertNull(DocumentBuilder.parseSax("<outer><tag></tag>"));
    }
    @Test
    public void parseSaxHtml62Test() {
        assertNull(DocumentBuilder.parseSax("<tag>t>"));
    }
    @Test
    public void parseSaxHtml63Test() {
        assertNull(DocumentBuilder.parseSax("<tag>t<"));
    }
    @Test
    public void parseSaxHtml64Test() {
        assertNull(DocumentBuilder.parseSax("<tag>\n\tt<"));
    }
    @Test
    public void parseSaxHtml65Test() {
        assertNull(DocumentBuilder.parseSax("<rm/<"));
    }
    @Test
    public void parseSaxHtml66Test() {
        assertNull(DocumentBuilder.parseSax("<rm/"));
    }
    @Test
    public void parseSaxHtml67Test() {
        assertNull(DocumentBuilder.parseSax("<rm&"));
    }
    @Test
    public void parseSaxHtml68Test() {
        assertNull(DocumentBuilder.parseSax("<rm<"));
    }
    @Test
    public void parseSaxHtml69Test() {
        assertNull(DocumentBuilder.parseSax("<rm "));
    }
    @Test
    public void parseSaxHtml70Test() {
        assertNull(DocumentBuilder.parseSax("< rm"));
    }
    @Test
    public void parseSaxHtml71Test() {
        assertNull(DocumentBuilder.parseSax("<&rm"));
    }
    @Test
    public void parseSaxHtml72Test() {
        assertNull(DocumentBuilder.parseSax("</rm"));
    }
    @Test
    public void parseSaxHtml73Test() {
        assertNull(DocumentBuilder.parseSax("<>rm"));
    }
    @Test
    public void parseSaxHtml74Test() {
        assertNull(DocumentBuilder.parseSax(">rm"));
    }
    @Test
    public void parseSaxHtml75Test() {
        assertNull(DocumentBuilder.parseSax(""));
    }

    @Test
    public void parseSaxNo1Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>e</tag>"));
    }

    @Test
    public void parseSaxNo2Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>\u00E9</tag>"));
    }

    @Test
    public void parseSaxNo3Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag>"));
    }

    @Test
    public void parseSaxNo4Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag/>"));
    }

    @Test
    public void parseSaxNo5Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>&#233;</tag>"));
    }

    @Test
    public void parseSaxNo6Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>&eacute;</tag>"));
    }

    @Test
    public void parseSaxNo7Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>&#0;</tag>"));
    }

    @Test
    public void parseNoText1Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>e</tag>"));
    }

    @Test
    public void parseNoText2Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>\u00E9</tag>"));
    }

    @Test
    public void parseNoText3Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag>"));
    }

    @Test
    public void parseNoText4Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag/>"));
    }

    @Test
    public void parseNoText5Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>&#233;</tag>"));
    }

    @Test
    public void parseNoText6Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>&eacute;</tag>"));
    }

    @Test
    public void parseNoText7Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>&#0;</tag>"));
    }

    @Test
    public void parseNoText8Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a='inner'>&#0;</tag>"));
    }

    @Test
    public void parseNoText9Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a=''>&#0;</tag>"));
    }

    @Test
    public void parseNoText10Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a=\"inner\">&#0;</tag>"));
    }

    @Test
    public void parseNoText11Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a=\"\">&#0;</tag>"));
    }

    @Test
    public void parseNoText12Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a ='inner'>&#0;</tag>"));
    }

    @Test
    public void parseNoText13Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a =''>&#0;</tag>"));
    }

    @Test
    public void parseNoText14Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a =\"inner\">&#0;</tag>"));
    }

    @Test
    public void parseNoText15Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a =\"\">&#0;</tag>"));
    }

    @Test
    public void parseNoText16Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= 'inner'>&#0;</tag>"));
    }

    @Test
    public void parseNoText17Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= ''>&#0;</tag>"));
    }

    @Test
    public void parseNoText18Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= \"inner\">&#0;</tag>"));
    }

    @Test
    public void parseNoText19Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= \"\">&#0;</tag>"));
    }

    @Test
    public void parseNoText20Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>&#0;<b/></tag>"));
    }

    @Test
    public void parseNoText21Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>&#0;<b></b></tag>"));
    }

    @Test
    public void parseNoText22Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag>&#0;<b>Text</b></tag>"));
    }

    @Test
    public void parseNoText23Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= \"inner\"><b>&#0;</b></tag>"));
    }

    @Test
    public void parseNoText24Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= \"inner\"><b></b></tag>"));
    }

    @Test
    public void parseNoText25Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= \"inner\"><b/></tag>"));
    }

    @Test
    public void parseNoText26Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= \"inner\"><b a= \"outer\"/></tag>"));
    }

    @Test
    public void parseNoText27Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= \"inner\"><b>&#0;</b><c>&#0;</c></tag>"));
    }

    @Test
    public void parseNoText28Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= \"inner\"><b>&#0;</b>After</tag>"));
    }

    @Test
    public void parseNoText29Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= \"inner\"/>"));
    }

    @Test
    public void parseNoText30Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag a= \"'&apos;&quot;\" b= '\"&apos;&quot;'/>"));
    }

    @Test
    public void parseNoText31Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("<tag b= '\"&apos;&quot;' a= \"'&apos;&quot;\"/>"));
    }

    @Test
    public void parseNoText32Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag><"));
    }

    @Test
    public void parseNoText33Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag></"));
    }

    @Test
    public void parseNoText34Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag></t"));
    }

    @Test
    public void parseNoText35Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag></tague"));
    }

    @Test
    public void parseNoText36Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag></tag"));
    }

    @Test
    public void parseNoText37Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<outer><tag remove='inner'/>"));
    }

    @Test
    public void parseNoText38Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove='inner'/<"));
    }

    @Test
    public void parseNoText39Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove='inner'/"));
    }

    @Test
    public void parseNoText40Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove='inner'"));
    }

    @Test
    public void parseNoText41Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove='inner"));
    }

    @Test
    public void parseNoText42Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove='inner>"));
    }

    @Test
    public void parseNoText43Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove='inner<"));
    }

    @Test
    public void parseNoText44Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove='inner&"));
    }

    @Test
    public void parseNoText45Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove='inner&"));
    }

    @Test
    public void parseNoText46Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove='"));
    }
    @Test
    public void parseNoText47Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove=' "));
    }

    @Test
    public void parseNoText48Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove= a"));
    }
    @Test
    public void parseNoText49Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove=a"));
    }
    @Test
    public void parseNoText50Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove="));
    }
    @Test
    public void parseNoText500Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove= "));
    }
    @Test
    public void parseNoText51Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove>"));
    }

    @Test
    public void parseNoText52Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove<"));
    }

    @Test
    public void parseNoText53Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove&"));
    }

    @Test
    public void parseNoText54Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove/"));
    }
    @Test
    public void parseNoText55Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove "));
    }
    @Test
    public void parseNoText56Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove /"));
    }
    @Test
    public void parseNoText57Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove"));
    }
    @Test
    public void parseNoText58Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag remove='' remove=''/>"));
    }
    @Test
    public void parseNoText59Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<outer><tag>t>"));
    }
    @Test
    public void parseNoText60Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<outer><tag>t<"));
    }
    @Test
    public void parseNoText61Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<outer><tag></tag>"));
    }
    @Test
    public void parseNoText62Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag>t>"));
    }
    @Test
    public void parseNoText63Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag>t<"));
    }
    @Test
    public void parseNoText64Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<tag>\n\tt<"));
    }
    @Test
    public void parseNoText65Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<rm/<"));
    }
    @Test
    public void parseNoText66Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<rm/"));
    }
    @Test
    public void parseNoText67Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<rm&"));
    }
    @Test
    public void parseNoText68Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<rm<"));
    }
    @Test
    public void parseNoText69Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<rm "));
    }
    @Test
    public void parseNoText70Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("< rm"));
    }
    @Test
    public void parseNoText71Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<&rm"));
    }
    @Test
    public void parseNoText72Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("</rm"));
    }
    @Test
    public void parseNoText73Test() {
        assertNull(DocumentBuilder.parseNoTextDocument("<>rm"));
    }
    @Test
    public void parseNoText74Test() {
        assertNull(DocumentBuilder.parseNoTextDocument(">rm"));
    }
    @Test
    public void parseNoText75Test() {
        assertNull(DocumentBuilder.parseNoTextDocument(""));
    }

    @Test
    public void parseNoText76Test() {
        assertNotNull(DocumentBuilder.parseNoTextDocument("\n<tag>e</tag>"));
    }
}
