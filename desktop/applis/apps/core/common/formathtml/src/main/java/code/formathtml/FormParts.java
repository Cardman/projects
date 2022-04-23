package code.formathtml;

import code.formathtml.util.IndexesFormInput;
import code.util.CustList;
import code.util.LongMap;
import code.util.Longs;
import code.util.StringList;

public abstract class FormParts {

    private IndexesFormInput indexes;

    private LongMap<StringList> formatIdMap = new LongMap<StringList>();
    private CustList<StringList> formatIdMapStack = new CustList<StringList>();
    private Longs formsNb = new Longs();
    private Longs inputs = new Longs();
    private long currentForm;
    protected FormParts(){
    }

    public void initForms() {
        indexes = new IndexesFormInput();
        formatIdMap = new LongMap<StringList>();
        formatIdMapStack = new CustList<StringList>();
        formsNb = new Longs();
        inputs = new Longs();
        currentForm = 0;
    }

    public IndexesFormInput getIndexes() {
        return indexes;
    }

    public LongMap<StringList> getFormatIdMap() {
        return formatIdMap;
    }

    public CustList<StringList> getFormatIdMapStack() {
        return formatIdMapStack;
    }

    public Longs getFormsNb() {
        return formsNb;
    }

    public Longs getInputs() {
        return inputs;
    }

    public long getCurrentForm() {
        return currentForm;
    }

    public void setCurrentForm(long _currentForm) {
        this.currentForm = _currentForm;
    }
}
