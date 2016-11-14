package com.kyushu.autosum.repositorylayer.repositories.genericrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.io.Serializable;

/**
 * Created by ANDRE on 15/04/16.
 */
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    // ******** ADD GENERIC METHOD ********
}