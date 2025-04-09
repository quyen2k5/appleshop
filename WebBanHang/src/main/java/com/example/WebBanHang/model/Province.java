package com.example.WebBanHang.model;

import java.util.List;


public class Province {
    private int id;
    private String name;
    private List<District> districts;

    public Province(int id, String name, List<District> districts) {
        this.id = id;
        this.name = name;
        this.districts = districts;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<District> getDistricts() { return districts; }
}
