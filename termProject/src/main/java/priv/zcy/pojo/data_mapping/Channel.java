package priv.zcy.pojo.data_mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Channel {
    @JsonProperty("id")
    private int channelId;

    @JsonProperty("channelname")
    private String channelName;

    @JsonIgnore
    private List<Content> contentList;
}
