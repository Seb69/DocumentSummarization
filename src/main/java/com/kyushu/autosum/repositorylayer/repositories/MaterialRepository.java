package com.kyushu.autosum.repositorylayer.repositories;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.repositories.genericrepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ANDRE on 16/04/16.
 */
@Component
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    List<Material> findByUserId(String userId);
}
