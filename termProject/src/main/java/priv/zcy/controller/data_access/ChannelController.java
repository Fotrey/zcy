package priv.zcy.controller.data_access;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.zcy.biz.ChannelBiz;
import priv.zcy.pojo.data_mapping.Channel;
import priv.zcy.controller.returning_wrapper.PersonalResponseBodyWrapper;

import java.util.List;


@RestController
@RequestMapping("/channel")
@PersonalResponseBodyWrapper

public class ChannelController {
    @Autowired
    private ChannelBiz channelBiz;

    @GetMapping(params = {"offset","limit"})
    public List<Channel> showChannelList(@RequestParam("offset") int offset, @RequestParam("limit") int limit){
        return channelBiz.showChannelList(offset, limit);

    }

    @GetMapping("/{id}")
    public Channel showChannelDetalis(@PathVariable("id") int id){
        return channelBiz.showChannelDetalis(id);

    }

    @PostMapping
    public Channel newlyIncreasedChannel(@RequestBody Channel channel){
        return channelBiz.newlyIncreasedChannel(channel);

    }

    @DeleteMapping("/{id}")
    public Integer deleteDesignatedChannel(@PathVariable("id") int id){
        return channelBiz.deleteDesignatedChannel(id);
    }

    @PutMapping
    public Channel modifyChannel(@RequestBody Channel channel){
        return channelBiz.modifyChannel(channel);

    }
}
