package com.valhalla.ehrplugin.elation.dto.reportDto;

import lombok.*;

@Getter
@Setter
public class File {
    private String original_filename;
    private String content_type;
    private String base64_content;
}
