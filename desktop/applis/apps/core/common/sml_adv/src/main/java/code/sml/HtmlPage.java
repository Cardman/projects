package code.sml;

import code.util.LongMap;
import code.util.LongTreeMap;
import code.util.StringList;
import code.util.core.IndexConstants;

public class HtmlPage implements HtmlPageInt {
    private final LongMap<LongTreeMap<NodeContainer>> containersBase = new LongMap<LongTreeMap<NodeContainer>>();
    private LongMap<StringList> formatIdMap = new LongMap<StringList>();

    private long url = IndexConstants.INDEX_NOT_FOUND_ELT;

    private boolean form;
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

    public NodeInformations getContainer(long _formNb, long _nbId) {
        LongTreeMap<NodeContainer> v_ = containersBase.getVal(_formNb);
        NodeContainer e_ = v_.getVal(_nbId);
        if (e_ == null) {
            return new NodeInformations();
        }
        return e_.getNodeInformation();
    }

    public LongMap<LongTreeMap<NodeContainer>> getContainersBase() {
        return containersBase;
    }

}
