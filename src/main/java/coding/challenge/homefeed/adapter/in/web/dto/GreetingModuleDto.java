package coding.challenge.homefeed.adapter.in.web.dto;
/**
 * 
 * GreetingModuleDto
 * Delivers concrete JSON structure for the greeting module in the homefeed response.
 * It implements the HomefeedModuleDto interface, which defines the common structure for all homefeed
 */
public class GreetingModuleDto implements HomefeedModuleDto {
    String type;
    String title;
    String subtitle;

    public GreetingModuleDto( String title, String subtitle) {
        this.type = "greeting";
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public String type() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'type'");
    }
}
