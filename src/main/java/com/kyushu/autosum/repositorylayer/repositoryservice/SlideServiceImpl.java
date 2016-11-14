package com.kyushu.autosum.repositorylayer.repositoryservice;

import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.repositories.SlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

/**
 * Created by ANDRE on 19/04/16.
 */
@Service
public class SlideServiceImpl implements SlideService {

    private SlideRepository slideRepository;

    @Autowired
    public void setSlideRepository(SlideRepository slideRepository) {
        this.slideRepository = slideRepository;
    }

    @Override
    public List<Slide> findAll() {

        List<Slide> slideList = slideRepository.findAll();

        if (!slideList.isEmpty())

            return slideList;

        else

            return Collections.emptyList();
    }

    @Override
    public Optional<Slide> findById(Integer id) {

        final OptionalLong SLIDE_TABLE_SIZE = size();


        if (SLIDE_TABLE_SIZE.isPresent() &&
                SLIDE_TABLE_SIZE.getAsLong() > 0 &&
                id > 0 &&
                id.longValue() <= SLIDE_TABLE_SIZE.getAsLong())

            return Optional.ofNullable(slideRepository.getOne(id));

        else

            return Optional.empty();

    }

    @Override
    public Optional<Slide> save(Slide slide) {

        Optional<Slide> slideOptional = Optional.ofNullable(slideRepository.save(slide));

        if (slideOptional.isPresent())

            return slideOptional;

        else

            return Optional.empty();

    }

    @Override
    public OptionalLong size() {

        OptionalLong sizeOptional = OptionalLong.of(slideRepository.count());

        return sizeOptional;
    }
}
