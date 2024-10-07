package code.sml;

import code.util.CustList;
import org.junit.Test;

public class AttrTest extends EquallableRowColUtil {
    @Test
    public void hasAttribute1Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag/>").getDocumentElement();
        assertTrue(!elt_.hasAttribute("inexist"));
    }
    @Test
    public void hasAttribute2Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag exist='value'/>").getDocumentElement();
        assertTrue(!elt_.hasAttribute("inexist"));
    }
    @Test
    public void hasAttribute3Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag exist='value'/>").getDocumentElement();
        assertTrue(elt_.hasAttribute("exist"));
        assertEq(1,elt_.getAttributes().getLength());
        assertEq("exist",elt_.getAttributes().item(0).getName());
    }
    @Test
    public void hasAttribute4Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag before='one' exist='value'/>").getDocumentElement();
        assertTrue(elt_.hasAttribute("exist"));
    }
    @Test
    public void getAttribute1Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag/>").getDocumentElement();
        assertEq("",elt_.getAttribute("inexist"));
    }
    @Test
    public void getAttribute2Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag exist='value'/>").getDocumentElement();
        assertEq("",elt_.getAttribute("inexist"));
    }
    @Test
    public void getAttribute3Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag exist='value'/>").getDocumentElement();
        assertEq("value",elt_.getAttribute("exist"));
    }
    @Test
    public void getAttribute4Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag before='one' exist='value'/>").getDocumentElement();
        assertEq("value",elt_.getAttribute("exist"));
    }
    @Test
    public void getAttribute5Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag before='one' exist='value'/>").getDocumentElement();
        TestedSmlImgAnimAttr img_ = new TestedSmlImgAnimAttr("src");
        img_.setAnim(new CustList<int[][]>(new int[0][0]));
        elt_.getAttributes().add(img_);
        assertEq("",elt_.getAttribute("src"));
    }
    @Test
    public void setAttribute1Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag/>").getDocumentElement();
        elt_.setAttribute("exist","value");
        assertEq("value",elt_.getAttribute("exist"));
    }
    @Test
    public void setAttribute2Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag exist='value'/>").getDocumentElement();
        elt_.setAttribute("exist","other");
        assertEq("other",elt_.getAttribute("exist"));
    }
    @Test
    public void setAttribute3Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag exist='value'/>").getDocumentElement();
        elt_.setAttribute("exist",null);
        assertTrue(elt_.hasAttribute("exist"));
        assertEq("",elt_.getAttribute("exist"));
    }
    @Test
    public void setAttribute4Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag before='one' exist='value'/>").getDocumentElement();
        elt_.setAttribute("exist","other");
        assertEq("other",elt_.getAttribute("exist"));
    }
    @Test
    public void removeAttribute1Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag before='one' exist='value'/>").getDocumentElement();
        elt_.removeAttribute("inexist");
        assertEq("one",elt_.getAttribute("before"));
        assertEq("value",elt_.getAttribute("exist"));
        assertEq("",elt_.getAttribute("inexist"));
    }
    @Test
    public void removeAttribute2Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag inexist='value'/>").getDocumentElement();
        elt_.removeAttribute("inexist");
        assertTrue(!elt_.hasAttribute("inexist"));
        assertEq("",elt_.getAttribute("inexist"));
    }
    @Test
    public void removeAttribute3Test() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag before='one' exist='value'/>").getDocumentElement();
        elt_.removeAttribute("inexist");
        assertEq("one",elt_.getAttribute("before"));
        assertTrue(!elt_.hasAttribute("inexist"));
        assertEq("",elt_.getAttribute("inexist"));
    }
    @Test
    public void copyAttr1() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag before='one' exist='value'/>").getDocumentElement();
        Element cp_ = elt_.getOwnerDocument().createElement("cp");
        cp_.setAttributesCopy(elt_.getAttributes());
        assertEq(2,cp_.getAttributes().getLength());
        assertEq("before",cp_.getAttributes().item(0).getName());
        assertEq("one",((DefAttr)cp_.getAttributes().item(0)).getValue());
        assertEq("exist",cp_.getAttributes().item(1).getName());
        assertEq("value",((DefAttr)cp_.getAttributes().item(1)).getValue());
    }
    @Test
    public void copyAttr2() {
        FullElement elt_ = (FullElement) DocumentBuilder.parseSax("<tag before='one' exist='value'/>").getDocumentElement();
        TestedSmlImgAnimAttr img_ = new TestedSmlImgAnimAttr("img");
        CustList<int[][]> imgs_ = new CustList<int[][]>();
        imgs_.add(new int[1][1]);
        imgs_.add(new int[1][1]);
        img_.setAnim(imgs_);
        elt_.getAttributes().add(img_);
        Element cp_ = elt_.getOwnerDocument().createElement("cp");
        cp_.setAttributesCopy(elt_.getAttributes());
        assertEq(3,cp_.getAttributes().getLength());
        assertEq("before",cp_.getAttributes().item(0).getName());
        assertEq("one",((DefAttr)cp_.getAttributes().item(0)).getValue());
        assertEq("exist",cp_.getAttributes().item(1).getName());
        assertEq("value",((DefAttr)cp_.getAttributes().item(1)).getValue());
        assertEq("img",cp_.getAttributes().item(2).getName());
        assertEq(2,((TestedSmlImgAnimAttr)cp_.getAttributes().item(2)).getAnim().size());
        assertEq(1,((TestedSmlImgAnimAttr)cp_.getAttributes().item(2)).getAnim().get(0).length);
        assertEq(1,((TestedSmlImgAnimAttr)cp_.getAttributes().item(2)).getAnim().get(0)[0].length);
        assertEq(1,((TestedSmlImgAnimAttr)cp_.getAttributes().item(2)).getAnim().get(1).length);
        assertEq(1,((TestedSmlImgAnimAttr)cp_.getAttributes().item(2)).getAnim().get(1)[0].length);
    }
    @Test
    public void hasAttributeNoText1Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag/>").getDocumentElement();
        assertTrue(!elt_.hasAttribute("inexist"));
    }
    @Test
    public void hasAttributeNoText2Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag exist='value'/>").getDocumentElement();
        assertTrue(!elt_.hasAttribute("inexist"));
    }
    @Test
    public void hasAttributeNoText3Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag exist='value'/>").getDocumentElement();
        assertTrue(elt_.hasAttribute("exist"));
    }
    @Test
    public void hasAttributeNoText4Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag before='one' exist='value'/>").getDocumentElement();
        assertTrue(elt_.hasAttribute("exist"));
    }
    @Test
    public void getAttributeNoText1Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag/>").getDocumentElement();
        assertEq("",elt_.getAttribute("inexist"));
    }
    @Test
    public void getAttributeNoText2Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag exist='value'/>").getDocumentElement();
        assertEq("",elt_.getAttribute("inexist"));
    }
    @Test
    public void getAttributeNoText3Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag exist='value'/>").getDocumentElement();
        assertEq("value",elt_.getAttribute("exist"));
        assertEq(1,elt_.getAttributes().getLength());
    }
    @Test
    public void getAttributeNoText4Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag before='one' exist='value'/>").getDocumentElement();
        assertEq("value",elt_.getAttribute("exist"));
    }
    @Test
    public void setAttributeNoText1Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag/>").getDocumentElement();
        elt_.setAttribute("exist","value");
        assertEq("value",elt_.getAttribute("exist"));
    }
    @Test
    public void setAttributeNoText2Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag exist='value'/>").getDocumentElement();
        elt_.setAttribute("exist","other");
        assertEq("other",elt_.getAttribute("exist"));
    }
    @Test
    public void setAttributeNoText3Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag exist='value'/>").getDocumentElement();
        elt_.setAttribute("exist",null);
        assertTrue(elt_.hasAttribute("exist"));
        assertEq("",elt_.getAttribute("exist"));
    }
    @Test
    public void setAttributeNoText4Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag before='one' exist='value'/>").getDocumentElement();
        elt_.setAttribute("exist","other");
        assertEq("other",elt_.getAttribute("exist"));
    }
    @Test
    public void removeAttributeNoText1Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag before='one' exist='value'/>").getDocumentElement();
        elt_.removeAttribute("inexist");
        assertEq("one",elt_.getAttribute("before"));
        assertEq("value",elt_.getAttribute("exist"));
        assertEq("",elt_.getAttribute("inexist"));
    }
    @Test
    public void removeAttributeNoText2Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag inexist='value'/>").getDocumentElement();
        elt_.removeAttribute("inexist");
        assertTrue(!elt_.hasAttribute("inexist"));
        assertEq("",elt_.getAttribute("inexist"));
    }
    @Test
    public void removeAttributeNoText3Test() {
        NotTextElement elt_ = (NotTextElement) DocumentBuilder.parseNoTextDocument("<tag before='one' exist='value'/>").getDocumentElement();
        elt_.removeAttribute("inexist");
        assertEq("one",elt_.getAttribute("before"));
        assertTrue(!elt_.hasAttribute("inexist"));
        assertEq("",elt_.getAttribute("inexist"));
    }
}
