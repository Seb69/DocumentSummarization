package com.kyushu.autosum.repositorylayer.repositories;

import com.kyushu.autosum.repositorylayer.domain.Slide;
import com.kyushu.autosum.repositorylayer.repositories.genericrepository.GenericRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ANDRE on 19/04/16.
 */
@Repository
public interface SlideRepository extends GenericRepository<Slide, Integer> {
}
