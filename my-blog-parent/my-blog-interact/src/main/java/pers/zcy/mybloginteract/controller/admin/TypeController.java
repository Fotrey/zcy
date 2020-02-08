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
import pers.zcy.myblogboot.entity.Type;
import pers.zcy.myblogbusiness.service.TypeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String list(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){

        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping(path = {"/types/{id}/input","/types/input"})
    public String editnput(@PathVariable(required = false) Long id, Model model){

        model.addAttribute("type",null == id?new Type():typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping(path = {"/types","/types/{id}"})
    public String post(@Valid Type type, BindingResult bindingResult, RedirectAttributes attributes, @PathVariable(required = false) Long id){

        Type typeByName = typeService.getTypeByName(type.getName());
        if (null != typeByName){
            bindingResult.rejectValue("name","nameError","该分类名已存在！");
        }
        if(bindingResult.hasErrors()){
            return "admin/types-input";
        }
        Type post = null==id?typeService.saveType(type):typeService.updateType(id, type);
        attributes.addFlashAttribute("message",null == post ? "操作失败！":"操作成功！");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
