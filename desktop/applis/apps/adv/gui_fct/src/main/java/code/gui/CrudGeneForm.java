package code.gui;

import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneForm extends AbsCrudGeneFormList<EditedCrudPair<String,Integer>> {

    private GeneComponentModelEntryStringInteger geneComponentModelEntryStringInteger;

    public CrudGeneForm(AbstractProgramInfos _fact) {
        super(_fact,null);
    }

    public void initForm(StringList _aDictionary, AfterValidateText _after) {
        ComparingStringKey<Integer> cmp_ = new ComparingStringKey<Integer>();
        geneComponentModelEntryStringInteger = new GeneComponentModelEntryStringInteger(getFactory(), _aDictionary, _after);
        initForm(new StringIntDisplayEntryCust(), geneComponentModelEntryStringInteger, cmp_,new ValidateElementPair<String, Integer>(cmp_));
    }

    public GeneComponentModelEntryStringInteger getGeneComponentModelEntryStringInteger() {
        return geneComponentModelEntryStringInteger;
    }
}
