package priv.zcy.biz;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.zcy.dao.ContentMapper;
import priv.zcy.pojo.data_mapping.Content;


import java.util.List;

@Service
public class ContentBiz {

    @Autowired
    private ContentMapper contentMapper;

    public List<Content> showContentList(int offset, int limit, int id) {
        return contentMapper.showContentList(offset, limit, id);

    }

    public Content showContentDetails(@Param("id") int id) {
        return contentMapper.showContentDetails(id);

    }

    @Transactional
    public Content newlyIncreasedContentDetails(Content content){
        contentMapper.newlyIncreasedContent(content);
        contentMapper.newlyIncreasedDetails(content);
        return   content;

    }

    public Integer deleteDesignatedDetail(int id){
        return contentMapper.deleteDesignatedDetail(id);

    }

    @Transactional
    public Content modifyContentDetails(Content content){
        contentMapper.modifyContentDetails(content);
        contentMapper.newlyIncreasedDetails(content);
        return  contentMapper.showContentDetails(content.getChannelId());

    }

}
