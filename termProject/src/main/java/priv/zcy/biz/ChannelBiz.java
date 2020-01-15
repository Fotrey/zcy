package priv.zcy.biz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.zcy.dao.ChannelMapper;
import priv.zcy.pojo.data_mapping.Channel;

import java.util.List;

@Service
public class ChannelBiz {
    @Autowired
    private ChannelMapper channelMapper;

    public List<Channel> showChannelList(int offset, int limit) {
        return channelMapper.showChannelList(offset, limit);

    }

    public Channel showChannelDetalis(int id) {
        return channelMapper.showChannelDetalis(id);

    }

    public Channel newlyIncreasedChannel(Channel channel) {
        channelMapper.newlyIncreasedChannel(channel);
        return channel;

    }

    public Integer deleteDesignatedChannel(int id) {
        return channelMapper.deleteDesignatedChannel(id);

    }

    @Transactional
    public Channel modifyChannel(Channel channel){
        channelMapper.modifyChannelName(channel);
        int channelId = channel.getChannelId();
        Channel channelAfterModify = channelMapper.showChannelDetalis(channelId);
        return channelAfterModify;
    }
}
