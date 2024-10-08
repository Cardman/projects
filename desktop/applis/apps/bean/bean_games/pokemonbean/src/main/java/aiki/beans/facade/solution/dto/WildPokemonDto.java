package aiki.beans.facade.solution.dto;

public final class WildPokemonDto {
    private final int[][] image;
    private final String name;
    private final String gender;

    public WildPokemonDto(int[][] _image, String _name, String _gender) {
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

    public int[][] getImage() {
        return image;
    }
}