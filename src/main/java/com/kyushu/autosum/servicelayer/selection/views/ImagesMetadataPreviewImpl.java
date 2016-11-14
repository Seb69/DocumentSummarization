package com.kyushu.autosum.servicelayer.selection.views;

import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.repositoryservice.MaterialService;
import com.kyushu.autosum.servicelayer.convertorservice.ConvertToImageService;
import com.kyushu.autosum.weblayer.exceptions.customError.NoMaterialFoundWithThisId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ANDRE
 * @since 17/08/16
 */
@Service
public class ImagesMetadataPreviewImpl implements ImagesMetadataPreview {

    private static final Logger LOG = LoggerFactory.getLogger(ImagesMetadataPreviewImpl.class);

    private MaterialService materialService;

    private ConvertToImageService convertToImageService;

    @Autowired
    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Autowired
    public void setConvertToImageService(ConvertToImageService convertToImageService) {
        this.convertToImageService = convertToImageService;
    }

    /**
     * Transform original file into single page file and return the list of the file created
     * @param id
     * @return
     */
    @Override
    public List<File> originalImage(Integer id) {

        Material material = findMaterial(id);
        List<BufferedImage> bufferedImageList = new ArrayList<>();
        List<File> fileList = new ArrayList<>();

        File file = new File(material.getMaterialFilePATH());

        LOG.debug(String.valueOf(material.getSlideList().size()));

        String PATH = null;

        String FOLDER = "/ORIGINAL";

        File outputfile = new File(String.valueOf(file.getParentFile()).concat(FOLDER));

        LOG.debug(String.valueOf(outputfile.exists()));

        // If the file were already created, not processing
        if(!outputfile.exists()){

            for (int i = 0; i < material.getSlideList().size(); i++) {
                PATH = String.valueOf(file.getParentFile()).concat("/PDF_Slides/").concat(String.valueOf(i)).concat(".pdf");

                File filePATH = new File(PATH);

                createNewFile(filePATH);
                filePATH.mkdirs();

                LOG.debug("PATH: " + filePATH.getPath());

                // Add the convert file into buffered Image file
                BufferedImage bufferedImage = convertToImageService.ConvertToImage(filePATH).get(0);

                // create IMAGE folder
                LOG.debug("OUTPUT FILE PATH: " + String.valueOf(file.getParentFile()).concat(FOLDER));
                outputfile.mkdirs();

                // create the image jpeg file
                LOG.debug("RETURN FILE PATH: " + String.valueOf(file.getParentFile()).concat(FOLDER+"/").concat(i + ".jpeg"));
                File returnFile = new File(String.valueOf(file.getParentFile()).concat(FOLDER + "/").concat(i + ".jpeg"));
                createNewFile(returnFile);
                returnFile.mkdirs();

                writeFile(bufferedImage, "jpeg", returnFile);

                fileList.add(returnFile);
            }
        }

        return fileList;

    }

    @Override
    public int countNumberOriginalImage(Integer id) {

        Material material = findMaterial(id);

        return material.getSlideList().size();

    }

    @Override
    public List<String> getMaterialPreviewLink(Integer id) {

        List<String> links = new ArrayList<>();

        Material material = findMaterial(id);

        int size = material.getSlideList().size();

        String PATH = material.getMaterialFilePATH();

        PATH = PATH.substring(0, PATH.lastIndexOf("/")).concat("/ORIGINAL/");

        for (int i = 0; i < size; i++) {
            String link = PATH.concat(String.valueOf(i)).concat(".jpeg");
            links.add(link);
        }

        return links;
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

    /**
     * Handle IOException
     *
     * @param file to create
     */
    void createNewFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOG.error(e.toString());
        }
    }


    /**
     * Handle IOException
     *
     * @param bufferedImage
     * @param type
     * @param fileToWrite
     */
    void writeFile(BufferedImage bufferedImage, String type, File fileToWrite) {

        try {
            ImageIO.write(bufferedImage, type, fileToWrite);
        } catch (IOException e) {
            LOG.error(e.toString());
        }
    }

}
