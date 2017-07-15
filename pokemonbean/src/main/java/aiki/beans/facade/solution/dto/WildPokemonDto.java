package aiki.beans.facade.solution.dto;
import code.bean.Accessible;

public class WildPokemonDto {

    @Accessible
    private String image;

    @Accessible
    private String name;

    @Accessible
    private String gender;

    public WildPokemonDto(String _image, String _name, String _gender) {
        image = _image;
        name = _name;
        gender = _gender;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
}
