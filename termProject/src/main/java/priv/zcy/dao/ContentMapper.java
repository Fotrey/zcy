package priv.zcy.dao;


import org.apache.ibatis.annotations.Param;
import priv.zcy.pojo.data_mapping.Content;

import java.util.List;


public interface ContentMapper {
    List<Content> showContentList(@Param("offset") int offset, @Param("limit") int limit, @Param("id") int id);

    Content showContentDetails(@Param("id") int id);

    int newlyIncreasedContent( Content content);

    int newlyIncreasedDetails( Content content);

    int deleteDesignatedDetail(@Param("id")int id);

    int modifyContentDetails( Content content);

}
