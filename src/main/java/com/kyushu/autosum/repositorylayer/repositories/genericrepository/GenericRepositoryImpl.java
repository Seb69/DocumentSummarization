package com.kyushu.autosum.repositorylayer.repositories.genericrepository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by ANDRE on 18/04/16.
 */
@NoRepositoryBean
public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements GenericRepository<T, ID> {

    public GenericRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }
    // ******** IMPLEMENT GENERIC METHOD ********
}
