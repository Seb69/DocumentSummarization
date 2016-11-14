package com.kyushu.autosum.servicelayer.convertorservice;

import com.kyushu.autosum.servicelayer.convertorservice.util.ConverterToImage;
import com.kyushu.autosum.servicelayer.convertorservice.util.PDFSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * @author ANDRE
 * @since 27/05/16
 */
@Service
public class ConverterPPT implements Converter {

    private PowerpointToPDF powerpointToPDF;

    @Autowired
    public void setPowerpointToPDF(PowerpointToPDF powerpointToPDF) {
        this.powerpointToPDF = powerpointToPDF;
    }

    @Override
    public List<BufferedImage> convert(File file) {

        // Convert from PPT to PDF
        file = powerpointToPDF.convertToPDF(file);

        PDFSplitter.splitPDF(file);

        // Convert PDF to Buffered Image
        List<BufferedImage> bufferedImageList = ConverterToImage.convertToImage(file);

        return bufferedImageList;
    }
}
