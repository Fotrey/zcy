package priv.zcy.controller.data_access;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.zcy.biz.ContentBiz;
import priv.zcy.controller.returning_wrapper.PersonalResponseBodyWrapper;
import priv.zcy.pojo.data_mapping.Content;

import java.util.List;

@RestController
@PersonalResponseBodyWrapper
public class ContentController {
    @Autowired
    private ContentBiz contentBiz;

    @GetMapping("/content")
    public List<Content> showContentList(@RequestParam("offset") int offset, @RequestParam("limit") int limit){
        //接口文档有误 实际无cid参数
        return contentBiz.showContentList(offset, limit,0);

    }

    @GetMapping("/content/{id}")
    public Content showContentDetails(@PathVariable("id") int id){
        return contentBiz.showContentDetails(id);

    }

    @PutMapping("/content")
    public Content modifyContentDetails(@RequestBody Content content){
        return contentBiz.modifyContentDetails(content);
    }

    @PostMapping("/detail")
    public Content newlyIncreasedContentDetails(@RequestBody Content content){
        return contentBiz.newlyIncreasedContentDetails(content);

    }

    @DeleteMapping("/detail/{id}")
    public Integer deleteDesignatedDetail(@PathVariable("id") int id){
        return contentBiz.deleteDesignatedDetail(id);

    }
}
