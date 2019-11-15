package com.meizu.common;


public enum City {
    Beijing("1", "Beijing"),
    Shanghai("2", "Shanghai"),
    Shenzhen("3", "Shenzhen"),
    Guangzhou("4", "Guangzhou");


    private String index;
    private String cityName;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    City(String index, String cityName) {
        this.index = index;
        this.cityName = cityName;
    }

    public static String getCityNameByIndex(String index) {
        for (City city : City.values()) {
            if (index.equals(city.getIndex())) {
                return city.getCityName();
            }
        }
        return null;
    }
}


