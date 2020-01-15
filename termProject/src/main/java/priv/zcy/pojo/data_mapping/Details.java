package priv.zcy.pojo.data_mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Details {

    @JsonIgnore
    private int detailsId;

    @JsonProperty("contentId")
    private int content_id;

    @JsonProperty("text")
    private String detailsText;

}
