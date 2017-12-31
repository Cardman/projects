package code.music.core;
import java.util.Iterator;
import java.util.ListIterator;

import jm.music.data.Note;
import jm.music.data.Phrase;
import code.serialize.XmlTransientable;
import code.util.EqList;
import code.util.annot.RwXml;
import code.util.ints.Equallable;
import code.util.ints.Listable;
import code.util.ints.MidListable;

@RwXml
public final class EvolvedPhrase implements XmlTransientable, MidListable<EvolvedNote>, Equallable<EvolvedPhrase> {

    private static final String SEP_TWO = "/";

    private static final String SEP_ONE = ":";

    private transient Phrase phrase;

    private EqList<EvolvedNote> notes = new EqList<EvolvedNote>();

    private int numerator;

    private int denominator;

    @RwXml
    public EvolvedPhrase() {
        phrase = new Phrase();
        numerator = Phrase.DEFAULT_NUMERATOR;
        denominator = Phrase.DEFAULT_DENOMINATOR;
    }

    public EvolvedPhrase(Phrase _ph) {
        numerator = _ph.getNumerator();
        denominator = _ph.getDenominator();
        phrase = new Phrase();
        for (Note n: _ph.getNoteArray()) {
            phrase.add(n);
            notes.add(new EvolvedNote(n));
        }
    }

    public EqList<EvolvedNote> getNotes() {
        return notes;
    }

    public Phrase getPhrase() {
        return phrase;
    }

    public void updateData() {
        notes.clear();
        for (Note n: phrase.getNoteArray()) {
            notes.add(new EvolvedNote(n));
        }
    }

    @Override
    public void beforeSave() {
    }

    @Override
    public void afterLoad() {
        phrase = new Phrase();
        for (EvolvedNote e: notes) {
            phrase.addNote(e.getNote());
        }
        phrase.setNumerator(numerator);
        phrase.setDenominator(denominator);
    }

    @Override
    public boolean eq(EvolvedPhrase _g) {
        return notes.eq(_g.notes);
    }

    @Override
    public String toString() {
        StringBuilder str_ = new StringBuilder();
        str_.append(notes.toString());
        str_.append(SEP_ONE);
        str_.append(numerator);
        str_.append(SEP_TWO);
        str_.append(denominator);
        return str_.toString();
    }

    @Override
    public int size() {
        return notes.size();
    }

    @Override
    public boolean isEmpty() {
        return notes.isEmpty();
    }

    @Override
    public boolean contains(EvolvedNote _o) {
        return notes.containsObj(_o);
    }

    @Override
    public Iterator<EvolvedNote> iterator() {
        return notes.iterator();
    }

    @Override
    public Object[] toArray() {
        return notes.toArray();
    }

//    @Override
//    public <T> T[] toArray(T[] _a) {
//        return notes.toArray(_a);
//    }

    @Override
    public void add(EvolvedNote _e) {
        phrase.add(_e.getNote());
        notes.add(_e);
    }

    @Override
    public void remove(EvolvedNote _o) {
        phrase.removeNote(_o.getNote());
        notes.removeObj(_o);
    }

    @Override
    public void clear() {
        phrase.empty();
        notes.clear();
    }

    @Override
    public EvolvedNote get(int _index) {
        return notes.get(_index);
    }

    @Override
    public void set(int _index, EvolvedNote _element) {
        phrase.setNote(_element.getNote(), _index);
        notes.set(_index, _element);
    }

    @Override
    public void remove(int _index) {
        phrase.removeNote(_index);
        notes.remove(_index);
    }

//    @Override
//    public int indexOf(Object _o) {
//        return notes.indexOf(_o);
//    }
//
//    @Override
//    public int lastIndexOf(Object _o) {
//        return notes.lastIndexOf(_o);
//    }

    @Override
    public ListIterator<EvolvedNote> listIterator() {
        return notes.listIterator();
    }

    @Override
    public ListIterator<EvolvedNote> listIterator(int _index) {
        return notes.listIterator(_index);
    }

//    @Override
//    public EqList<EvolvedNote> subList(int _fromIndex, int _toIndex) {
//        return notes.subList(_fromIndex, _toIndex);
//    }

    @Override
    public Listable<EvolvedNote> sub(int _from, int _to) {
        return notes.sub(_from, _to);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setNumerator(int _numerator) {
        numerator = _numerator;
    }

    public void setDenominator(int _denominator) {
        denominator = _denominator;
    }

}
