package code.gui;

import code.gui.events.AfterValidateText;
import code.gui.initialize.*;
import code.util.StringList;
import code.util.StringMap;

public final class CrudGeneForm extends AbsCrudGeneFormList<EditedCrudPair<String,Integer>> {

    private GeneComponentModelEntryStringInteger geneComponentModelEntryStringInteger;

    public CrudGeneForm(AbstractProgramInfos _fact) {
        super(_fact,null);
    }

    public void initForm(StringMap<Integer> _m, StringList _aDictionary, AfterValidateText _after) {
        ComparingStringKey<Integer> cmp_ = new ComparingStringKey<Integer>();
        geneComponentModelEntryStringInteger = new GeneComponentModelEntryStringInteger(getFactory(), _aDictionary, _after);
        initForm(new StringIntDisplayEntryCust(), geneComponentModelEntryStringInteger,new MapToEntriesListUtil<String,Integer>().build(_m), cmp_,new ValidateElementPair<String, Integer>(cmp_));
    }

    public GeneComponentModelEntryStringInteger getGeneComponentModelEntryStringInteger() {
        return geneComponentModelEntryStringInteger;
    }
}
