
package ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class ShoppingCartData extends RealmObject {

    @PrimaryKey
    private int id;

    private String ImageUrl;
    private String ProductName;

    private double Price;
    private int Weight;
    private int Quantity;
    private double SubTotal;
    private String StoreName;
    private String availableStocks;
    private String variantId;
    private String storeId;

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

    public String getAvailableStocks() {
        return availableStocks;
    }

    public void setAvailableStocks(String availableStocks) {
        this.availableStocks = availableStocks;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double subTotal) {
        SubTotal = subTotal;
    }

    private int GrandTotal;

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
        return "ShoppingCartData{" +
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



    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }



    public int getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        GrandTotal = grandTotal;
    }
}

