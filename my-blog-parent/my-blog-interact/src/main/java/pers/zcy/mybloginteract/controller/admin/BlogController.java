package pers.zcy.mybloginteract.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pers.zcy.myblogboot.entity.Blog;
import pers.zcy.myblogboot.entity.User;
import pers.zcy.myblogboot.vo.BlogQuery;
import pers.zcy.myblogbusiness.service.BlogService;
import pers.zcy.myblogbusiness.service.TagService;
import pers.zcy.myblogbusiness.service.TypeService;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(
            @PageableDefault(size = 8, sort = {"updateTime"},
                    direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogQuery, Model model){
//        new Builder(model).displayTypes().displayPage(pageable, blogQuery);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable,blogQuery));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(
            @PageableDefault(size = 8, sort = {"updateTime"},
                    direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogQuery, Model model){
        new Builder(model).displayPage(pageable, blogQuery);
//        model.addAttribute("page", blogService.listBlog(pageable,blogQuery));
        return "admin/blogs :: blogList";
    }

    @GetMapping(path = {"/blogs/{id}/input","/blogs/input"})
    public String input(@PathVariable(required = false) Long id, Model model) {
        Blog blog = null == id?new Blog():blogService.getBlog(id);
        blog.initTagIds();
        new Builder(model).displayTypes().displayTags().modifyBlog(blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()).stream().collect(Collectors.toSet()));
        Blog b = null == blog.getId()?blogService.saveBlog(blog):blogService.updateBlog(blog.getId(), blog);
        attributes.addFlashAttribute("message", null == b?"操作失败":"操作成功");
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }

    public class Builder{
        private Model model;
        Builder(Model model){
            this.model=model;
        }
        private Builder displayTypes() {
            model.addAttribute("types", typeService.listType());
            return this;
        }
        private Builder displayTags() {
            model.addAttribute("tags", tagService.listTag());
            return this;
        }
        private Builder modifyBlog(Blog blog) {
            model.addAttribute("blog", blog);
            return this;
        }
        private Builder displayPage(Pageable pageable, BlogQuery blogQuery) {
            model.addAttribute("page", blogService.listBlog(pageable,blogQuery));
            return this;
        }
    }

}
