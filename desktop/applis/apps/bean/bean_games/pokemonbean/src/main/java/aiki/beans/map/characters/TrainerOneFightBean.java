package aiki.beans.map.characters;
import aiki.beans.CommonBean;

public class TrainerOneFightBean extends CommonBean {

    @Override
    public void beforeDisplaying() {
        setLanguage(getLanguage());
    }
}