package pers.zcy.mybloginteract.controller.reception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pers.zcy.myblogboot.entity.Type;
import pers.zcy.myblogboot.vo.BlogQuery;
import pers.zcy.myblogbusiness.service.BlogService;
import pers.zcy.myblogbusiness.service.TypeService;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    BlogService blogService;


    @GetMapping("/types/{id}")
    public String types(
            @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Long id, Model model) {
        List<Type> types = typeService.listTypeTop(1000);
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(-1 == id?types.get(0).getId():id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
