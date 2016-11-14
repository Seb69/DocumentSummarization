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
import java.util.Optional;

/**
 * @author ANDRE
 * @since 17/06/16
 */
@Service
public class DownloadSumFileImpl implements DownloadSumFile {

    private static final Logger LOG = LoggerFactory.getLogger(DownloadSumFileImpl.class);

    private CreateDownloadFile createDownloadFile;

    private SelectionSlides selectionSlides;

    private MaterialService materialService;

    @Autowired
    public void setCreateDownloadFile(CreateDownloadFile createDownloadFile) {
        this.createDownloadFile = createDownloadFile;
    }

    @Autowired
    public void setSelectionSlides(SelectionSlides selectionSlides) {
        this.selectionSlides = selectionSlides;
    }

    @Autowired
    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Override
    public File downloadFile(Integer id) {

        // Find material with this id
        Material material = findMaterial(id);

        int slideNumber = material.getSlideList().size();

        int numberOfSlideSelected = slideNumber / 2;

        material = selectionSlides.selectionSlides(material, numberOfSlideSelected * 60);

        return createDownloadFile.createDownloadFile(material);
    }

    /**
     * Find material By ID
     *
     * @param id of material to retrieve
     * @return the material found
     */
    @Override
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
