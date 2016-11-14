package com.kyushu.autosum.servicelayer.selection;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.repositoryservice.MaterialService;
import com.kyushu.autosum.weblayer.exceptions.customError.NoMaterialFoundWithThisId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * @author ANDRE
 * @since 16/08/16
 */
@Service
public class DownloadOriginalFileImpl implements DownloadOriginalFile {

    private static final Logger LOG = LoggerFactory.getLogger(DownloadOriginalFileImpl.class);


    private MaterialService materialService;

    @Autowired
    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Override
    public File downloadOriginalFile(Integer ID) {

        Material material = findMaterial(ID);

        File originalFile = new File(material.getMaterialFilePATH());

        return originalFile;
    }


    /**
     * Find material By ID
     *
     * @param id of material to retrieve
     * @return the material found
     */
    @Transactional
    public Material findMaterial(Integer id) {

        // Get material
        Optional<Material> materialOptional = materialService.findById(id);

        // Fetch slideList
        materialOptional.get().getSlideList();

        materialOptional.orElseThrow(() -> new NoMaterialFoundWithThisId(id));

        return materialOptional.get();
    }

}
