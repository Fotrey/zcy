package pers.zcy.myblogbusiness.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zcy.myblogboot.entity.Type;
import pers.zcy.myblogboot.exception.NoSuchDataException;
import pers.zcy.myblogboot.persist.TypeRepository;
import pers.zcy.myblogbusiness.service.TypeService;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    @Transactional
    public Type saveType(Type type) {

        return typeRepository.save(type);
    }

    @Override
    @Transactional
    public Type getType(Long id) {

        return typeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Type getTypeByName(String name) {

        return typeRepository.findByName(name);
    }

    @Override
    @Transactional
    public Page<Type> listType(Pageable pageable) {

        return typeRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Type> listType() {

        return typeRepository.findAll();
    }

    @Override
    @Transactional
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    @Override
    @Transactional
    public Type updateType(Long id, Type type) {

        Type ToBeModified = getType(id);
        if(null == ToBeModified){
            throw new NoSuchDataException("该类型不存在！");
        }
        BeanUtils.copyProperties(type,ToBeModified);
        return saveType(ToBeModified);
    }

    @Override
    public void deleteType(Long id) {

        typeRepository.deleteById(id);
    }
}
