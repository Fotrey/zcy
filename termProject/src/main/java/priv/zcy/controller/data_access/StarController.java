package priv.zcy.controller.data_access;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.zcy.biz.StarBiz;
import priv.zcy.controller.returning_wrapper.PersonalResponseBodyWrapper;
import priv.zcy.pojo.data_mapping.Star;

@RestController
@RequestMapping("/star")
@PersonalResponseBodyWrapper
public class StarController {
    @Autowired
    private StarBiz starBiz;

    @GetMapping
    public Star newlyIncreasedStar(@RequestBody Star star){
        return starBiz.newlyIncreasedStar(star);

    }

    @GetMapping("/{id}")
    public Object showStarList(@PathVariable("id") int id){
        //star表字段含义未给出相关解释
        System.out.println("star表字段含义未给出相关解释");
        return null;

    }
}
