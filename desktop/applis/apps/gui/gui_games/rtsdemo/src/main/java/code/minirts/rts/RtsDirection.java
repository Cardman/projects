package code.minirts.rts;

public enum RtsDirection {
    UP((byte)0,(byte)-1),DOWN((byte)0,(byte)1),LEFT((byte)-1,(byte)0),RIGHT((byte)1,(byte)0);
    private final byte xCoords;
    private final byte yCoords;
    RtsDirection(byte _x, byte _y) {
        xCoords = _x;
        yCoords = _y;
    }
    public RtsDirection getOpposite() {
        for (RtsDirection d: values()) {
            if (d.getx()+getx() != 0) {
                continue;
            }
            if (d.gety()+gety() != 0) {
                continue;
            }
            return d;
        }
        return null;
    }
    public byte getx() {
        return xCoords;
    }
    public byte gety() {
        return yCoords;
    }
}
