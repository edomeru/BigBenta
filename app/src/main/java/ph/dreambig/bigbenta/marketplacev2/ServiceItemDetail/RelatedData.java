package ph.dreambig.bigbenta.marketplacev2.ServiceItemDetail;

/**
 * Created by jenzoned on 3/31/16.
 */
public class RelatedData {

    String ProductName;
    String UrlImage;

    public String getProductName() {
        return ProductName;
    }

    public String getUrlImage() {
        return UrlImage;
    }


    public RelatedData(String urlImage, String productName) {
        UrlImage = urlImage;
        ProductName = productName;
    }

    }


