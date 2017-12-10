package code.jsontransformers;
import static code.jsontransformers.EquallableUtil.assertEq;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
@SuppressWarnings("static-method")
public class JsonTransformsTest {

    @Test
    public void fromXml1Test() {
        String test_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b>val1</b><d/></a>";
//        assertEq("{\"b\":\"val1\",\"d\":[]}", getXMLFiletoJSONString(test));
        assertEq("{\"a\":{\"b\":\"val1\",\"d\":[]}}", JsonTransforms.getXMLFiletoJSONString(test_));
    }

    @Test
    public void fromXml2Test() {
//        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b attr=\"val1\"></b><d/></a>";
        String test_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b attr=\"val1\"/><d/></a>";
//        assertEq("{\"b\":{\"@attr\":\"val1\"},\"d\":[]}", getXMLFiletoJSONString(test));
        assertEq("{\"a\":{\"b\":{\"@attr\":\"val1\"},\"d\":[]}}", JsonTransforms.getXMLFiletoJSONString(test_));
    }

    @Test
    public void fromXml3Test() {
//        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b attr=\"val1\"></b><d/></a>";
        String test_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b attr=\"val1\">val2</b><d/></a>";
//        assertEq("{\"b\":{\"@attr\":\"val1\"},\"d\":[]}", getXMLFiletoJSONString(test));
        assertEq("{\"a\":{\"b\":{\"@attr\":\"val1\",\"#text\":\"val2\"},\"d\":[]}}", JsonTransforms.getXMLFiletoJSONString(test_));
    }

    @Test
    public void fromXml4Test() {
//        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b attr=\"val1\"></b><d/></a>";
        String test_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b.c attr=\"val1\">val2</b.c><d/></a>";
//        assertEq("{\"b\":{\"@attr\":\"val1\"},\"d\":[]}", getXMLFiletoJSONString(test));
        assertEq("{\"a\":{\"b.c\":{\"@attr\":\"val1\",\"#text\":\"val2\"},\"d\":[]}}", JsonTransforms.getXMLFiletoJSONString(test_));
    }

    @Test
    public void fromXml5Test() {
//        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b attr=\"val1\"></b><d/></a>";
        String test_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a xmlns:b=\"http://www.example.com\"><b:c attr=\"val1\">val2</b:c><d/></a>";
//        assertEq("{\"b\":{\"@attr\":\"val1\"},\"d\":[]}", getXMLFiletoJSONString(test));
        assertEq("{\"a\":{\"b:c\":{\"@attr\":\"val1\",\"#text\":\"val2\"},\"d\":[]}}", JsonTransforms.getXMLFiletoJSONString(test_));
    }

    @Test
    public void fromXml6Test() {
//        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b attr=\"val1\"></b><d/></a>";
        String test_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b.c class=\"val1\">val2</b.c><d/></a>";
//        assertEq("{\"b\":{\"@attr\":\"val1\"},\"d\":[]}", getXMLFiletoJSONString(test));
        assertEq("{\"a\":{\"b.c\":{\"@class\":\"val1\",\"#text\":\"val2\"},\"d\":[]}}", JsonTransforms.getXMLFiletoJSONString(test_));
    }

//    @Test
//    public void fromXml6Test() {
////        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a><b attr=\"val1\"></b><d/></a>";
//        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><a xmlns:b=\" \"><b:c attr=\"val1\">val2</b:c><d/></a>";
////        assertEq("{\"b\":{\"@attr\":\"val1\"},\"d\":[]}", getXMLFiletoJSONString(test));
//        assertEq("{\"a\":{\"b:c\":{\"@attr\":\"val1\",\"#text\":\"val2\"},\"d\":[]}}", JsonTransforms.getXMLFiletoJSONString(test, new StringList("b")));
//    }

    @Test
    public void toXml1Test() {
        String json_ = "{\"a\":{\"b\":\"val1\",\"d\":[]}}";
        assertEq("<?xml version=\"1.0\" encoding=\"UTF-8\"?><a><b>val1</b><d/></a>", JsonTransforms.getJSONStringtoXMLFile(json_));
    }

    @Test
    public void toXml2Test() {
        String json_ = "{\"a\":{\"b\":{\"@attr\":\"val1\"},\"d\":[]}}";
        assertEq("<?xml version=\"1.0\" encoding=\"UTF-8\"?><a><b attr=\"val1\"/><d/></a>",JsonTransforms.getJSONStringtoXMLFile(json_));
    }

    @Test
    public void toXml3Test() {
        String json_ = "{\"a\":{\"b\":{\"@attr\":\"val1\",\"#text\":\"val2\"},\"d\":[]}}";
        assertEq("<?xml version=\"1.0\" encoding=\"UTF-8\"?><a><b attr=\"val1\">val2</b><d/></a>",JsonTransforms.getJSONStringtoXMLFile(json_));
    }

    @Test
    public void toXml4Test() {
        String json_ = "{\"a\":{\"b.c\":{\"@attr\":\"val1\",\"#text\":\"val2\"},\"d\":[]}}";
        assertEq("<?xml version=\"1.0\" encoding=\"UTF-8\"?><a><b.c attr=\"val1\">val2</b.c><d/></a>",JsonTransforms.getJSONStringtoXMLFile(json_));
    }

    @Test
    public void toXml5Test() {
        String json_ = "{\"a\":{\"b:c\":{\"@attr\":\"val1\",\"#text\":\"val2\"},\"d\":[]}}";
        assertEq("<?xml version=\"1.0\" encoding=\"UTF-8\"?><a><b:c attr=\"val1\">val2</b:c><d/></a>",JsonTransforms.getJSONStringtoXMLFile(json_));
    }

    @Test
    public void toXml6Test() {
        String json_ = "{\"a\":{\"b.c\":{\"@class\":\"val1\",\"#text\":\"val2\"},\"d\":[]}}";
        assertEq("<?xml version=\"1.0\" encoding=\"UTF-8\"?><a><b.c class=\"val1\">val2</b.c><d/></a>",JsonTransforms.getJSONStringtoXMLFile(json_));
    }

//    @Test
//    public void toXml3Test() {
//        String json_ = "{\"b\":\"val1\",\"d\":[]}";
//        System.out.println(getJSONStringtoXMLFileBis(json_));
//    }
//
//    @Test
//    public void toXml4Test() {
//        String json_ = "{\"b\":{\"@attr\":\"val1\"},\"d\":[]}";
//        System.out.println(getJSONStringtoXMLFileBis(json_));
//    }
}
