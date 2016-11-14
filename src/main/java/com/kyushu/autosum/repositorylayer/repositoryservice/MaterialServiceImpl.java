package com.kyushu.autosum.repositorylayer.repositoryservice;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.repositories.MaterialRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;


/**
 * Created by ANDRE on 15/04/16.
 */
@Repository
public class MaterialServiceImpl implements MaterialService {

    private MaterialRepository materialRepository;

    @Autowired
    public void setMaterialRepository(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    @Transactional
    public List<Material> findAll() {

        List<Material> materialList = materialRepository.findAll();

        if (!materialList.isEmpty())

            return materialList;
        else
            return Collections.EMPTY_LIST;

    }

    @Override
    @Transactional
    public Optional<Material> findById(Integer id) {


        final OptionalLong MATERIAL_TABLE_SIZE = size();

        if (    MATERIAL_TABLE_SIZE.isPresent() &&
                MATERIAL_TABLE_SIZE.getAsLong() > 0 &&
                id > 0 &&
                id.longValue() <= MATERIAL_TABLE_SIZE.getAsLong() ) {

            Material material = materialRepository.getOne(id);

            return Optional.ofNullable(material);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Material> update(Material material) {

        Optional<Material> materialOptional = Optional.ofNullable(materialRepository.save(material));

        if (materialOptional.isPresent())

            return materialOptional;

        else

            return Optional.empty();
    }


    @Override
    public Optional<Material> save(Material material) {

        // Save the material
        Optional<Material> materialOptional = Optional.ofNullable(materialRepository.save(material));

        if (materialOptional.isPresent())

            return materialOptional;

        else

            return Optional.empty();
    }

    @Override
    public OptionalLong size() {

        // Retrieve Material table size
        OptionalLong sizeOptional = OptionalLong.of(materialRepository.count());

        return sizeOptional;
    }

    @Override
    public List<Material> findByUserId(String ID) {

        return materialRepository.findByUserId(ID);

    }

}
