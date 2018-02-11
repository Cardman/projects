package aiki.beans.facade.solution.dto;

public final class WildPokemonDto {
    private String image;
    private String name;
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

    public String getImage() {
        return image;
    }
}