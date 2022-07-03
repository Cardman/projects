package code.mock;

import code.stream.LineShortListenable;

public final class MockLineShortListenable implements LineShortListenable {
    private String ty="";
    private long pos;
    private int ki;
    @Override
    public void update(String _type, long _position) {
        set(_type,_position,3);
    }

    @Override
    public void updateMp3(String _type, long _position) {
        set(_type,_position,4);
    }
    private void set(String _type, long _position, int _kind) {
        ty = _type;
        pos = _position;
        ki = _kind;
    }

    public int getKi() {
        return ki;
    }

    public long getPos() {
        return pos;
    }

    public String getTy() {
        return ty;
    }
}
