package app.hairmelodysalon.com.noname_hairmelodysalon;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Item implements Parcelable {

    private Bitmap pic;
    private String id;
    private String name;
    private String category;
    private int stock;

    public Item(Bitmap pic, String id, String name, String category, int stock) {
        this.pic=pic;
        this.id = id;
        this.name = name;
        this.category = category;
        this.stock = stock;
    }
    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pic);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeInt(stock);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Item(Parcel in) {
        this.pic=(Bitmap)in.readValue(Bitmap.class.getClassLoader());
        this.id=in.readString();
        this.name=in.readString();
        this.category=in.readString();
        this.stock=in.readInt();
    }
}