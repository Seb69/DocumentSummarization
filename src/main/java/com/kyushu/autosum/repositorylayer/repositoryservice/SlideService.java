package com.kyushu.autosum.repositorylayer.repositoryservice;

import com.kyushu.autosum.repositorylayer.domain.Slide;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

/**
 * Created by ANDRE on 19/04/16.
 */
public interface SlideService {
    public List<Slide> findAll();

    public Optional<Slide> findById(Integer id);

    public Optional<Slide> save(Slide slide);

    public OptionalLong size();
}
