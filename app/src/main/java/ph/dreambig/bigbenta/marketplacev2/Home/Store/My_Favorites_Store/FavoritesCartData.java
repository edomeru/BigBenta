
package ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class FavoritesCartData extends RealmObject {

    @PrimaryKey
    private int id;

    private String ImageUrl;
    private String ProductName;
    private String Availability;
    private double Price;
    private int Weight;
    private int Quantity;
    private double SubTotal;
    private double GrandTotal;
    private String  storeName;
    private String  availableStocks;

    public String getAvailableStocks() {
        return availableStocks;
    }

    public void setAvailableStocks(String availableStocks) {
        this.availableStocks = availableStocks;
    }

    private String   storeId;
    private String  variantId;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }



    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public int getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        OriginalPrice = originalPrice;
    }

    private int OriginalPrice;

    public int getOriginalWeight() {
        return OriginalWeight;
    }

    public void setOriginalWeight(int originalWeight) {
        OriginalWeight = originalWeight;
    }

    private int OriginalWeight;

    public int getCartTotalItems() {
        return CartTotalItems;
    }

    public void setCartTotalItems(int cartTotalItems) {
        CartTotalItems = cartTotalItems;
    }

    private int CartTotalItems = 0;

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    @Override
    public String toString() {
        return "FavoritesCartData{" +
                "id=" + id +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", Price=" + Price +
                ", Quantity=" + Quantity +
                '}';
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double subTotal) {
        SubTotal = subTotal;
    }

    public double getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        GrandTotal = grandTotal;
    }
}

