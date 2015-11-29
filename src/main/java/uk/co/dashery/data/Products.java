package uk.co.dashery.data;

import org.springframework.web.multipart.MultipartFile;

public class Products {

    private boolean usingUrl;

    private String url;

    private MultipartFile file;

    public Products() {
    }

    public Products(String url) {
        this.usingUrl = true;
        this.url = url;
    }

    public Products(MultipartFile file) {
        this.usingUrl = false;
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsingUrl() {
        return usingUrl;
    }

    public MultipartFile getFile() {
        return file;
    }
}
