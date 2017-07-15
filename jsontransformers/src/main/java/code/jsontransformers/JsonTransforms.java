package code.jsontransformers;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import code.util.StringList;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

public final class JsonTransforms {

    private static final String LINE_RETURN_N = "\n";
    private static final String LINE_RETURN_R = "\r";
    private static final String COM = ".com";
    private static final String WEB_SEPARATOR = "/";
    private static final String SEPARATOR_HTTP_WEB_PAGE = WEB_SEPARATOR+WEB_SEPARATOR;
    private static final String HTTP = "http:"+SEPARATOR_HTTP_WEB_PAGE;
    private static final String END_DOC = "</o>";
    private static final String END_HEADER = "?>";
    private static final String END_HEADER_BEG_DOC = "?><o>";
    private JsonTransforms() {
    }

    public static JSONObject getXMLFiletoJson(String _xml) {
        InputStream is_ = new ByteArrayInputStream(_xml.getBytes());
        JSON json_ = null;
        XMLSerializer xmlSerializer_ = new XMLSerializer();
        xmlSerializer_.setForceTopLevelObject(true);
        xmlSerializer_.setSkipNamespaces(true);
        xmlSerializer_.setTypeHintsCompatibility(false);
        json_ = xmlSerializer_.readFromStream(is_);
        return (JSONObject) json_;
    }

    public static String getXMLFiletoJSONString(String _xml) {
        InputStream is_ = new ByteArrayInputStream(_xml.getBytes());
        JSON json_ = null;
        XMLSerializer xmlSerializer_ = new XMLSerializer();
        xmlSerializer_.setForceTopLevelObject(true);
        xmlSerializer_.setSkipNamespaces(true);
        xmlSerializer_.setTypeHintsCompatibility(false);
        json_ = xmlSerializer_.readFromStream(is_);
        return json_.toString();
    }

    public static String getXMLFiletoJSONString(String _xml, StringList _namespaces) {
        InputStream is_ = new ByteArrayInputStream(_xml.getBytes());
        JSON json_ = null;
        XMLSerializer xmlSerializer_ = new XMLSerializer();
        xmlSerializer_.setForceTopLevelObject(true);
        xmlSerializer_.setSkipNamespaces(true);
        xmlSerializer_.setNamespaceLenient(true);
        xmlSerializer_.setTypeHintsCompatibility(false);
        for (String n: _namespaces) {
            xmlSerializer_.addNamespace(n, HTTP+n+COM);
        }
        json_ = xmlSerializer_.readFromStream(is_);
        return json_.toString();
    }

//    public static String getJSONStringtoXMLFileBis(String _json) {
//        JSONObject json_ = new JSONObject();
//        json_.
//        return XML.toString(json_);
//    }
    public static String getJSONtoXMLFile(JSON _json) {
        XMLSerializer xmlSerializer_ = new XMLSerializer();
        xmlSerializer_.setTypeHintsCompatibility(false);
        xmlSerializer_.setTypeHintsEnabled(false);
        String str_ = xmlSerializer_.write(_json);
        String xml_ = str_;
//        xml_ = xml_.replace("\r\n", "");
        xml_ = StringList.removeStrings(xml_, LINE_RETURN_R, LINE_RETURN_N);
        xml_ = StringList.replace(xml_, END_HEADER_BEG_DOC, END_HEADER);
        xml_ = xml_.substring(0, xml_.length() - END_DOC.length());
        return xml_;
    }
    public static String getJSONStringtoXMLFile(String _json) {
        JSON json_ = JSONSerializer.toJSON(_json);
        XMLSerializer xmlSerializer_ = new XMLSerializer();
        xmlSerializer_.setTypeHintsCompatibility(false);
        xmlSerializer_.setTypeHintsEnabled(false);
        String str_ = xmlSerializer_.write(json_);
        String xml_ = str_;
//        xml_ = xml_.replace("\r\n", "");
        xml_ = StringList.removeStrings(xml_, LINE_RETURN_R, LINE_RETURN_N);
        xml_ = StringList.replace(xml_, END_HEADER_BEG_DOC, END_HEADER);
        xml_ = xml_.substring(0, xml_.length() - END_DOC.length());
        return xml_;
    }
}
