package data;

/**
 * Created by xieyumin on 2018/7/16.
 */

public class food {
    String name;
    String img;
    String href;
    public food(String n,String i,String h){
        name=n;
        img=i;
        href=h;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }
}
