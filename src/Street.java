public class Street {
    private String englishName;
    private String cantoneseName;
    private DistrictCode district;

    public Street() {
        englishName = null;
        cantoneseName = null;
        district = null;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public void setCantoneseName(String cantoneseName) {
        this.cantoneseName = cantoneseName;
    }

    public void setDistrict(DistrictCode district) {
        this.district = district;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getCantoneseName() {
        return cantoneseName;
    }

    public DistrictCode getDistrict() {
        return district;
    }
}
