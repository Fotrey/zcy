package priv.zcy.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.zcy.dao.StarMapper;
import priv.zcy.pojo.data_mapping.Star;

@Service
public class StarBiz {
    @Autowired
    private StarMapper starMapper;

    public Star newlyIncreasedStar(Star star){
        starMapper.newlyIncreasedStar(star);
        return star;
    }
}
