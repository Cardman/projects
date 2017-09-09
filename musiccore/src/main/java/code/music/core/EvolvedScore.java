package code.music.core;
import java.util.Iterator;
import java.util.ListIterator;

import jm.music.data.Part;
import jm.music.data.Score;
import jm.util.Play;
import code.serialize.XmlTransientable;
import code.util.EqList;
import code.util.annot.RwXml;
import code.util.ints.Listable;
import code.util.ints.MidListable;

@RwXml
public class EvolvedScore implements XmlTransientable, MidListable<EvolvedPart> {

    private static final String STRING = "";

    private transient Score score;

    private EqList<EvolvedPart> parts = new EqList<EvolvedPart>();

    private String name = STRING;

    @RwXml
    public EvolvedScore() {
        score = new Score();
    }

    public EvolvedScore(Score _ph) {
        score = new Score();
        for (Part n: _ph.getPartArray()) {
            score.add(n);
            parts.add(new EvolvedPart(n));
        }
    }

    public void play() {
        Play.midi(score);
    }

    public EqList<EvolvedPart> getParts() {
        return parts;
    }

    public Score getScore() {
        return score;
    }

    public void updateData() {
        parts.clear();
        for (Part n: score.getPartArray()) {
            parts.add(new EvolvedPart(n));
        }
    }

    @Override
    public void beforeSave() {
    }

    @Override
    public void afterLoad() {
        score = new Score(name);
        for (EvolvedPart e: parts) {
            score.addPart(e.getPart());
        }
    }

    @Override
    public String toString() {
        return name+parts.toString();
    }

    @Override
    public int size() {
        return parts.size();
    }

    @Override
    public boolean isEmpty() {
        return parts.isEmpty();
    }

    @Override
    public boolean contains(EvolvedPart _o) {
        return parts.containsObj(_o);
    }

    @Override
    public Iterator<EvolvedPart> iterator() {
        return parts.iterator();
    }

    @Override
    public Object[] toArray() {
        return parts.toArray();
    }

//    @Override
//    public <T> T[] toArray(T[] _a) {
//        return parts.toArray(_a);
//    }

    @Override
    public void add(EvolvedPart _e) {
        score.add(_e.getPart());
        parts.add(_e);
    }

    @Override
    public void remove(EvolvedPart _o) {
        score.removePart(_o.getPart());
        parts.removeObj(_o);
    }

    @Override
    public void clear() {
        score.empty();
        parts.clear();
    }

    @Override
    public EvolvedPart get(int _index) {
        return parts.get(_index);
    }

    @Override
    public void set(int _index, EvolvedPart _element) {
        score.removePart(_index);
        score.insertPart(_element.getPart(), _index);
        parts.set(_index, _element);
    }

    @Override
    public void remove(int _index) {
        score.removePart(_index);
        parts.remove(_index);
    }

//    @Override
//    public int indexOf(Object _o) {
//        return parts.indexOf(_o);
//    }
//
//    @Override
//    public int lastIndexOf(Object _o) {
//        return parts.lastIndexOf(_o);
//    }

    @Override
    public ListIterator<EvolvedPart> listIterator() {
        return parts.listIterator();
    }

    @Override
    public ListIterator<EvolvedPart> listIterator(int _index) {
        return parts.listIterator(_index);
    }

//    @Override
//    public List<EvolvedPart> subList(int _fromIndex, int _toIndex) {
//        return parts.subList(_fromIndex, _toIndex);
//    }

    @Override
    public Listable<EvolvedPart> sub(int _from, int _to) {
        return parts.sub(_from, _to);
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }
}
