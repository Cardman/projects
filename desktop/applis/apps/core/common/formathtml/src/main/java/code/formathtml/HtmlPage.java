package code.formathtml;

import code.formathtml.util.NodeContainer;
import code.util.LongMap;
import code.util.StringList;
import code.util.core.IndexConstants;

public abstract class HtmlPage {

    private LongMap<StringList> formatIdMap = new LongMap<StringList>();

    private long url = IndexConstants.INDEX_NOT_FOUND_ELT;

    private boolean form;
    protected HtmlPage() {
    }

    public void setBase(FormParts _form) {
        setFormatIdMap(_form.getFormatIdMap());
    }

    public long getUrl() {
        return url;
    }

    public void setUrl(long _url) {
        url = _url;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean _form) {
        form = _form;
    }

    public LongMap<StringList> getFormatIdMap() {
        return formatIdMap;
    }

    public void setFormatIdMap(LongMap<StringList> _formatIdMap) {
        formatIdMap = _formatIdMap;
    }

    public abstract NodeContainer getContainer(long _formNb, long _nbId);
}
