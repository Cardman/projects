package aiki.map.characters;
import aiki.db.DataBase;

public interface CharacterInRoadCave {

    void validate(DataBase _data);

    boolean hasValidImage(DataBase _data);
}
