package pers.zcy.mybloginteract.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pers.zcy.myblogboot.entity.Tag;
import pers.zcy.myblogbusiness.service.TagService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model) {
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping(path = {"/tags/{id}/input","/tags/input"})
    public String input(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("tag", null == id?new Tag():tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping(path = {"/tags","/tags/{id}"})
    public String post(@Valid Tag tag,BindingResult result, @PathVariable(required = false) Long id, RedirectAttributes attributes) {
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (null != tagByName) {
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag t = null == id?tagService.saveTag(tag):tagService.updateTag(id,tag);
        attributes.addFlashAttribute("message",null == t?"操作失败":"操作成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
