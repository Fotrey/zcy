package priv.zcy.pojo.data_wrapper_utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberInfoFormat implements Format{
    @Autowired
    private Map<String,Object> links;
    @Autowired
    private Map<String,Object> data;

    @PostConstruct
    public void init(){
        links.put("self","self");
        data.put("type","UmsMember");
    }
}
