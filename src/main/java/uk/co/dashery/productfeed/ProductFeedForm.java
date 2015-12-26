package uk.co.dashery.productfeed;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;

public class ProductFeedForm {

    private boolean usingUrl;
    private String url;

    private MultipartFile file;

    private boolean isAffiliateWindowFormat;

    public ProductFeedForm() {
    }

    public ProductFeedForm(String url) {
        this.usingUrl = true;
        this.url = url;
    }

    public ProductFeedForm(MultipartFile file) {
        this.usingUrl = false;
        this.file = file;
    }

    public ProductFeedForm(MultipartFile file, boolean isAffiliateWindowFormat) {
        this.file = file;
        this.isAffiliateWindowFormat = isAffiliateWindowFormat;
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

    public boolean isAffiliateWindowFormat() {
        return isAffiliateWindowFormat;
    }

    public void setAffiliateWindowFormat(boolean isAffiliateWindowFormat) {
        this.isAffiliateWindowFormat = isAffiliateWindowFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductFeedForm productFeedForm = (ProductFeedForm) o;

        if (usingUrl != productFeedForm.usingUrl) return false;
        if (url != null ? !url.equals(productFeedForm.url) : productFeedForm.url != null)
            return false;
        return !(file != null ? !file.equals(productFeedForm.file) : productFeedForm.file != null);

    }

    @Override
    public int hashCode() {
        int result = (usingUrl ? 1 : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }

    public Reader generateReader() throws IOException {
        InputStream csvInputStream;
        if (usingUrl) {
            csvInputStream = new URL(url).openStream();
        } else {
            csvInputStream = file.getInputStream();
        }
        return new BufferedReader(new InputStreamReader(csvInputStream));
    }
}
