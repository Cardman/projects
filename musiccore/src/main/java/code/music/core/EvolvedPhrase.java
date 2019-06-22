package code.music.core;
import java.util.Iterator;

import jm.music.data.Note;
import jm.music.data.Phrase;
import code.util.EqList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class EvolvedPhrase implements MidListable<EvolvedNote>, Equallable<EvolvedPhrase>, Displayable {

    private static final String SEP_TWO = "/";

    private static final String SEP_ONE = ":";

    private Phrase phrase;

    private EqList<EvolvedNote> notes = new EqList<EvolvedNote>();

    private int numerator;

    private int denominator;

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
    public String display() {
        StringBuilder str_ = new StringBuilder();
        for (EvolvedNote e: notes) {
            str_.append(e.display());
            str_.append(SEP_ONE);
        }
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

    public void setNotes(EqList<EvolvedNote> _notes) {
        notes = _notes;
    }
}
