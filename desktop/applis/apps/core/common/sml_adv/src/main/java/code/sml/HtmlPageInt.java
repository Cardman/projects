package code.sml;

import code.util.LongMap;
import code.util.StringList;

public interface HtmlPageInt {
    NodeInformations getContainer(long _formNb, long _nbId);
    void setBase(FormParts _form);
    long getUrl();
    void setUrl(long _url);
    boolean isForm();
    void setForm(boolean _form);
    LongMap<StringList> getFormatIdMap();
    void setFormatIdMap(LongMap<StringList> _formatIdMap);
}
