package priv.zcy.pojo.data_mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Content {
    @JsonProperty("id")
    private int contentId;

    @JsonProperty("title")
    private String contentTitle;

    @JsonProperty("url")
    private String contentUrl;

    @JsonProperty("img")
    private String contentImg;

    @JsonProperty("creatDate")
    private Timestamp contentCreateDate;

    @JsonProperty("count")
    private int contentCount;

    @JsonProperty("cid")
    private int channelId;

    private Details contentDetails;
}
