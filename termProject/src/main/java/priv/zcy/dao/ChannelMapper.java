package priv.zcy.dao;


import org.apache.ibatis.annotations.Param;

import priv.zcy.pojo.data_mapping.Channel;

import java.util.List;


public interface ChannelMapper {
    List<Channel> showChannelList(@Param("offset") int offset, @Param("limit") int limit);

    Channel showChannelDetalis(@Param("id") int id);

    int newlyIncreasedChannel(Channel channel);

    int deleteDesignatedChannel(@Param("id") int id);

    int modifyChannelName(Channel channel);
}
