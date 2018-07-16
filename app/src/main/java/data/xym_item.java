package data;

/**
 * Created by xieyumin on 2018/7/16.
 */

public class xym_item {
    private String img;
    private String word;
    public xym_item(String i,String w){
        img=i;
        word=w;
    }

    public String getImg() {
        return img;
    }

    public String getWord() {
        return word;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
