package code.formathtml;

import code.formathtml.util.IndexesFormInput;
import code.util.CustList;
import code.util.LongMap;
import code.util.Longs;
import code.util.StringList;

public abstract class FormParts {

    private IndexesFormInput indexes;
    private CustList<StringList> anchorsArgs = new CustList<StringList>();
    private CustList<StringList> anchorsVars = new CustList<StringList>();

    private CustList<StringList> formsArgs = new CustList<StringList>();
    private CustList<StringList> formsVars = new CustList<StringList>();
    private LongMap<StringList> formatIdMap = new LongMap<StringList>();
    private CustList<StringList> formatIdMapStack = new CustList<StringList>();
    private Longs formsNb = new Longs();
    private Longs inputs = new Longs();
    private long currentForm;
    protected FormParts(){
    }

    public void initForms() {
        anchorsArgs = new CustList<StringList>();
        anchorsVars = new CustList<StringList>();
        indexes = new IndexesFormInput();
        formatIdMap = new LongMap<StringList>();
        formatIdMapStack = new CustList<StringList>();
        formsNb = new Longs();
        inputs = new Longs();
        formsArgs = new CustList<StringList>();
        formsVars = new CustList<StringList>();
        currentForm = 0;
    }

    public IndexesFormInput getIndexes() {
        return indexes;
    }

    public CustList<StringList> getAnchorsArgs() {
        return anchorsArgs;
    }

    public CustList<StringList> getAnchorsVars() {
        return anchorsVars;
    }

    public CustList<StringList> getFormsArgs() {
        return formsArgs;
    }

    public CustList<StringList> getFormsVars() {
        return formsVars;
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
