package com.fractalmedia.codechallenge.atom.repositories.responses;

import com.fractalmedia.codechallenge.atom.models.Image;

public class ConfigurationResponse {
    private Image images;

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }
}
