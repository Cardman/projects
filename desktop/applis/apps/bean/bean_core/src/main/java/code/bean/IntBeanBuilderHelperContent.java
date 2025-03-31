package code.bean;

import code.formathtml.render.*;
import code.sml.util.*;
import code.util.*;

public final class IntBeanBuilderHelperContent {
    private int partGroup;
    private int rowGroup;
    private final Ints colCount = new Ints();
    private final Ints colIndex = new Ints();
    private Translations translations;
    private int indent;
    private String refLk = "";
    private int header;
    private final Ints orderedLists = new Ints();
    private final IdList<MetaSearchableContent> metaSearchableContents = new IdList<MetaSearchableContent>();
    public void incColIndex() {
        colIndex.set(getColIndex().getLastIndex(),(colIndex() + 1) % colCount());
    }

    public IdList<MetaSearchableContent> getMetaSearchableContents() {
        return metaSearchableContents;
    }

    public int colIndex() {
        return getColIndex().last();
    }

    public Ints getColIndex() {
        return colIndex;
    }
    public int getPartGroup() {
        return partGroup;
    }

    public void setPartGroup(int _p) {
        this.partGroup = _p;
    }

    public int getRowGroup() {
        return rowGroup;
    }

    public void setRowGroup(int _r) {
        this.rowGroup = _r;
    }

    public int getIndent() {
        return indent;
    }

    public void setIndent(int _i) {
        this.indent = _i;
    }

    public String getRefLk() {
        return refLk;
    }

    public void setRefLk(String _r) {
        this.refLk = _r;
    }

    public int getHeader() {
        return header;
    }

    public void setHeader(int _h) {
        this.header = _h;
    }

    public Ints getOrderedLists() {
        return orderedLists;
    }

    public int colCount() {
        return getColCount().last();
    }

    public Ints getColCount() {
        return colCount;
    }

    public void colCount(int _c) {
        colCount.set(colCount.getLastIndex(),_c);
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations _t) {
        this.translations = _t;
    }
}
