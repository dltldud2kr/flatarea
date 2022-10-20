package com.example.flatarea.admin.service;

import com.example.flatarea.admin.dto.BrandDto;
import com.example.flatarea.admin.entity.Brand;
import com.example.flatarea.admin.mapper.BrandMapper;
import com.example.flatarea.admin.model.BrandInput;
import com.example.flatarea.admin.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }

    @Override
    public List<BrandDto> list() {
        List<Brand> brands = brandRepository.findAll(getSortBySortValueDesc());
        return BrandDto.of(brands);

    }

    @Override
    public boolean add(String brandName) {

        Brand brand = Brand.builder()
                .brandName(brandName)
                .usingYn(true)
                .sortValue(0)
                .build();
        brandRepository.save(brand);

        return true;
    }

    @Override
    public boolean update(BrandInput parameter) {

        Optional<Brand> optionalBrand = brandRepository.findById(parameter.getId());
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            brand.setBrandName(parameter.getBrandName());
            brand.setSortValue(parameter.getSortValue());
            brand.setUsingYn(parameter.isUsingYn());
            brandRepository.save(brand);
        }

        return true;
    }

    @Override
    public boolean del(long id) {
        brandRepository.deleteById(id);
        return true;
    }

    @Override
    public List<BrandDto> frontList(BrandDto parameter) {
        return brandMapper.select(parameter);

    }
}
