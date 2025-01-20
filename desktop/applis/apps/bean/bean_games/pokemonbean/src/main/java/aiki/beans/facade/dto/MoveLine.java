package aiki.beans.facade.dto;
import code.util.StringList;

public class MoveLine {
    private String displayName;
    private String name;
    private long pp;
    private StringList types;
    private boolean damageMove;
    private String category;
    private boolean direct;
    private long priority;
    private String accuracy;
    private String power;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String _displayName) {
        displayName = _displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public long getPp() {
        return pp;
    }

    public void setPp(long _pp) {
        pp = _pp;
    }

    public StringList getTypes() {
        return types;
    }

    public void setTypes(StringList _types) {
        types = _types;
    }

    public boolean isDamageMove() {
        return damageMove;
    }

    public void setDamageMove(boolean _damageMove) {
        damageMove = _damageMove;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String _category) {
        category = _category;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean _direct) {
        direct = _direct;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long _priority) {
        priority = _priority;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String _accuracy) {
        accuracy = _accuracy;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String _power) {
        power = _power;
    }
}