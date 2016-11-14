package com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices;

import com.kyushu.autosum.servicelayer.scoreprocessing.textprocessing.parseservices.exceptions.FileNotSupport;
import com.kyushu.autosum.servicelayer.uploadservices.exceptions.FileTypeNotDetect;
import com.kyushu.autosum.servicelayer.util.DetectMimeType;
import com.kyushu.autosum.servicelayer.util.enumeration.MimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * This is a factory class which create the appropriate object according to the type of file uploaded
 *
 * @author ANDRE
 * @date 03/06/16
 */
@Component
public class ParserFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ParserFactory.class);

    /**
     * Create the proper Parser according to the Mime type of the file
     *
     * @param file uploaded in server side
     * @return Parse object
     * @throws FileNotSupport if any object stand for the mime types
     */
    public Parser createParser(File file) throws FileNotSupport {

        String pdf = MimeType.PDF;
        String ppt = MimeType.PPT;
        String pptx = MimeType.PPTX;

        String mimeType = getMimeType(file);

        if (mimeType.equals(pdf)) return new ParserPDF();

        if (mimeType.equals(ppt)) return new ParserPPT();

        if (mimeType.equals(pptx)) return new ParserPPTX();

        else throw new FileNotSupport();

    }

    String getMimeType(File file) {
        StringBuilder str = new StringBuilder();
        try {
            str.append(DetectMimeType.getMimeType(file));
        } catch (FileTypeNotDetect fileTypeNotDetect) {
            LOG.error(fileTypeNotDetect.getMessage());
        }
        return str.toString();
    }
}
