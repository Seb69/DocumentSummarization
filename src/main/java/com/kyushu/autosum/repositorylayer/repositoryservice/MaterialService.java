package com.kyushu.autosum.repositorylayer.repositoryservice;

import com.kyushu.autosum.repositorylayer.domain.Material;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

/**
 * Created by ANDRE on 15/04/16.
 */
public interface MaterialService {
     List<Material> findAll();

     Optional<Material> findById(Integer id);

     Optional<Material> update(Material material);

     Optional<Material> save(Material material);

     OptionalLong size();

    List<Material> findByUserId(String ID);


}
