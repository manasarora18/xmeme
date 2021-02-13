package crio.stage.xmeme.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * This class is for Data Transfer Object purposes of the Meme entity that needs to sent out by the backend to the frontend.
 */

@Getter
@Setter
public class MemeDTO {
    private String id;
    private String name;
    private String url;
    private String caption;
}
