public class OnlineCourse extends Course{
    private String link;

    public OnlineCourse(){
        link = "http://link.com";
    }

    public OnlineCourse(String link) {
        this.link = link;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
