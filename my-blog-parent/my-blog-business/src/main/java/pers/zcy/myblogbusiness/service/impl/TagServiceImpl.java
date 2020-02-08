package pers.zcy.myblogbusiness.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zcy.myblogboot.entity.Tag;
import pers.zcy.myblogboot.exception.NoSuchDataException;
import pers.zcy.myblogboot.persist.TagRepository;
import pers.zcy.myblogboot.utils.StringIds2CollectionConverter;
import pers.zcy.myblogbusiness.service.TagService;

import java.util.List;
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    @Transactional
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public Tag getTag(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    @Transactional
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        return null;
    }

    @Override
    @Transactional
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(StringIds2CollectionConverter.apply(ids));
    }

    @Override
    @Transactional
    public Tag updateTag(Long id, Tag tag) {
        Tag ToBeModified = getTag(id);
        if (null == ToBeModified) {
            throw new NoSuchDataException("不存在该标签");
        }
        BeanUtils.copyProperties(tag, ToBeModified);
        return saveTag(ToBeModified);
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
