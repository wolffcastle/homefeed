package coding.challenge.homefeed.domain;

public class GreetingModule implements HomefeedModule {
    String title;
    String subtitle;

    public GreetingModule(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String title() {
        return title;
    }

    public String subtitle() {
        return subtitle;
    }

}
