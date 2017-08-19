package code.formathtml;
import code.formathtml.util.FormInputCoords;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.ObjectMap;
import code.util.StringList;

public class HtmlPage {

    private NumberMap<Long,NatTreeMap<Long,NodeContainer>> containers = new NumberMap<Long,NatTreeMap<Long,NodeContainer>>();

    private ObjectMap<FormInputCoords,StringList> selects = new ObjectMap<FormInputCoords,StringList>();

    private long url = CustList.INDEX_NOT_FOUND_ELT;

    private boolean form;

    private String usedFieldUrl;

    public NumberMap<Long,NatTreeMap<Long,NodeContainer>> getContainers() {
        return containers;
    }

    public void setContainers(NumberMap<Long,NatTreeMap<Long,NodeContainer>> _containers) {
        containers = _containers;
    }

    public ObjectMap<FormInputCoords,StringList> getSelects() {
        return selects;
    }

    public void setSelects(ObjectMap<FormInputCoords,StringList> _selects) {
        selects = _selects;
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

    public String getUsedFieldUrl() {
        return usedFieldUrl;
    }

    public void setUsedFieldUrl(String _usedFieldUrl) {
        usedFieldUrl = _usedFieldUrl;
    }
}
