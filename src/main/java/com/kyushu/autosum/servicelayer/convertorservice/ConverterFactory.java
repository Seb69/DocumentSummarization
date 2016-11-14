package com.kyushu.autosum.servicelayer.convertorservice;

import com.kyushu.autosum.servicelayer.convertorservice.exceptions.FailedToConvert;
import com.kyushu.autosum.servicelayer.uploadservices.exceptions.FileTypeNotDetect;
import com.kyushu.autosum.servicelayer.util.DetectMimeType;
import com.kyushu.autosum.servicelayer.util.enumeration.MimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author ANDRE
 * @since 28/05/16
 */
@Service
public class ConverterFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ConverterFactory.class);

    private ConverterPPT converterPPT;

    private ConverterPDF converterPDF;

    @Autowired
    public void setConverterPPT(ConverterPPT converterPPT) {
        this.converterPPT = converterPPT;
    }

    @Autowired
    public void setConverterPDF(ConverterPDF converterPDF) {
        this.converterPDF = converterPDF;
    }

    public Converter getConverter(File file) {

        String mimeType = getMimeType(file);
        LOG.debug(mimeType);

        switch (mimeType) {
            case MimeType.PDF:
                return converterPDF;
            case MimeType.PPT:
                return converterPPT;
            case MimeType.PPTX:
                return converterPPT;
            default:
                throw new FailedToConvert("Unable to find mimeType and return the proper convert");
        }

    }

    String getMimeType(File file) {
        StringBuilder str = new StringBuilder();
        try {
            str.append(DetectMimeType.getMimeType(file));
        } catch (FileTypeNotDetect fileTypeNotDetect) {
            LOG.error(fileTypeNotDetect.toString());
        }
        return str.toString();
    }

}
