package com.example.blossomassistant;

import java.util.ArrayList;

public final class DesignStore {
    public static final ArrayList<DesignModel> designs = new ArrayList<>();

    private DesignStore() {}

    public static void ensureSeedData() {
        if (!designs.isEmpty()) return;

        designs.add(new DesignModel("Big Rose Wedding", R.drawable.big_rose_wedding, 1800, "Wedding"));
        designs.add(new DesignModel("Orchid Wedding", R.drawable.orchid_wedding, 2200, "Wedding"));
        designs.add(new DesignModel("Small Rose Wedding", R.drawable.small_rose_wedding, 1600, "Wedding"));
        designs.add(new DesignModel("Engagement Decor", R.drawable.engagement_decor, 2000, "Wedding"));

        designs.add(new DesignModel("Birthday Theme", R.drawable.birthday_theme, 1200, "Birthday"));
        designs.add(new DesignModel("Marigold Birthday", R.drawable.marigold_birthday, 1000, "Birthday"));
        designs.add(new DesignModel("Default Decor", R.drawable.default_decor, 900, "Birthday"));
        designs.add(new DesignModel("Housewarming Decor", R.drawable.housewarming_decor, 1100, "Birthday"));
    }
}

