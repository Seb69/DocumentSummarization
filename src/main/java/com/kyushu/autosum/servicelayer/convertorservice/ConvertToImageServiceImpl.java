package com.kyushu.autosum.servicelayer.convertorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * This class is the higher level of converting file
 * This convert file (ppt, pptx, pdf) into image jpeg
 * @author ANDRE
 * @since 09/05/16
 */
@Service
public class ConvertToImageServiceImpl implements ConvertToImageService {

//    private PowerpointToPDFImpl powerpointToPDF;

    private ConverterFactory converterFactory;

//    @Autowired
//    public void setPowerpointToPDF(PowerpointToPDFImpl powerpointToPDF) {
//        this.powerpointToPDF = powerpointToPDF;
//    }

    @Autowired
    public void setConverterFactory(ConverterFactory converterFactory) {
        this.converterFactory = converterFactory;
    }

    /**
     * Convert whatever format to Image File
     * @param file in whatever format type
     * @return buffered Image list
     */
    @Override
    public List<BufferedImage> ConvertToImage(File file) {

        Converter converter = converterFactory.getConverter(file);

        List<BufferedImage> bufferedImageList = converter.convert(file);

        return bufferedImageList;
    }

}
