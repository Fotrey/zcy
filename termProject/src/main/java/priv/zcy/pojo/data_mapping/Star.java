package priv.zcy.pojo.data_mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Star {
    @JsonProperty("id")
    private long starId;

    @JsonProperty("memberId")
    private long member_id;

    @JsonProperty("news")
    private long news_id;
}
