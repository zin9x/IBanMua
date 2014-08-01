package project.banmua.com.item;

/**
 * Created by Dat on 8/1/2014.
 */
public class ItemHome {
    private String id;
    private String url;
    private String title;
    private String testGit;

    public ItemHome(String id, String url, String title){
        this.id = id;
        this.url = url;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
