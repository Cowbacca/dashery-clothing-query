package uk.co.dashery.products;

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

    public boolean isUsingUrl() {
        return usingUrl;
    }

    public void setUsingUrl(boolean usingUrl) {
        this.usingUrl = usingUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        if (usingUrl != products.usingUrl) return false;
        if (url != null ? !url.equals(products.url) : products.url != null) return false;
        return !(file != null ? !file.equals(products.file) : products.file != null);

    }

    @Override
    public int hashCode() {
        int result = (usingUrl ? 1 : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }
}
