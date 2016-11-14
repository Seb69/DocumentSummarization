package com.kyushu.autosum.servicelayer.convertorservice;

import com.kyushu.autosum.servicelayer.convertorservice.util.ConverterToImage;
import com.kyushu.autosum.servicelayer.convertorservice.util.PDFSplitter;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * @author ANDRE
 * @since 28/05/16
 */
@Service
public class ConverterPDF implements Converter {

    /**
     * Convert PDF file into
     * @param file
     * @return
     */
    @Override
    public List<BufferedImage> convert(File file) {

        PDFSplitter.splitPDF(file);

        List<BufferedImage> bufferedImageList = ConverterToImage.convertToImage(file);

        return bufferedImageList;
    }
}
