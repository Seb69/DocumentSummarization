package com.kyushu.autosum.weblayer.controllers;

import boofcv.io.image.UtilImageIO;
import com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.backgroundsubstraction.BackgroundSubtraction;
import com.kyushu.autosum.servicelayer.scoreprocessing.imageprocessing.interframesubtraction.InterFrameSubtraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

/**
 * @author ANDRE
 * @since 20/05/16
 */
@RestController
public class ImageProcessingController {

    private BackgroundSubtraction backgroundSubtraction;

    private InterFrameSubtraction interFrameSubtraction;


    @Autowired
    public void setBackgroundSubtraction(BackgroundSubtraction backgroundSubtraction) {
        this.backgroundSubtraction = backgroundSubtraction;
    }

    @Autowired
    public void setInterFrameSubtraction(InterFrameSubtraction interFrameSubtraction) {
        this.interFrameSubtraction = interFrameSubtraction;
    }

    @RequestMapping(
            value = "/image")
    @ResponseStatus(HttpStatus.OK)
    public void getMaterials()  {

//        backgroundSubtraction.calculationBackSubtraction("DiapositiveJP.jpg");

        // BUILD
        BufferedImage image = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");

        // OPERATE
        backgroundSubtraction.calculationBackSubtraction(image);


    }

    @RequestMapping(
            value = "/imageInter")
    @ResponseStatus(HttpStatus.OK)
    public void getImageInter()  {


        // BUILD
        BufferedImage imageA = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive1.jpg");
        BufferedImage imageB = UtilImageIO.loadImage("/Users/ANDRE/Desktop/AutoSum/backend_autosum/src/main/resources/IMAGES/PresentationInF/Diapositive2.jpg");

        // OPERATE
        Long result = interFrameSubtraction.calculationInterFrameSubtraction(imageA, imageB);


    }


}
