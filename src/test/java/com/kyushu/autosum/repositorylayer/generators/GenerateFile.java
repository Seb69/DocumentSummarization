package com.kyushu.autosum.repositorylayer.generators;

import java.io.File;

/**
 * @author ANDRE
 * @since 18/05/16
 */
public class GenerateFile {

    public static File createPPT(){
        File file = new File("/Users/ANDRE/Desktop/AutoSum/IMAGES/Presentation1.ppt");
        return file;
    }

    public static File createPPT_Server(){
        File file = new File("src/main/java/com/kyushu/autosum/upload-dir/4mMy/4mMy-Presentation1.pptx");
        return file;
    }

    public static File createPPTX(){
        File file = new File("/Users/ANDRE/Desktop/AutoSum/IMAGES/Presentation1.pptx");
        return file;
    }

    public static File createPDF(){
        File file = new File("/Users/ANDRE/Desktop/AutoSum/IMAGES/Presentation1.pdf");
        return file;
    }

    public static File createPDF_Single(){
        File file = new File("src/main/java/com/kyushu/autosum/upload-dir/IMAGES/PresentationJP.pdf");
        return file;
    }

    public static File createPDF_Server(){
        File file = new File("src/main/java/com/kyushu/autosum/upload-dir/4mMy/PDF_Slides/PDF.pdf");
        return file;
    }

    public static File createPPTX_JP(){
        File file = new File("/Users/ANDRE/Desktop/AutoSum/IMAGES/PresentationJP.pptx");
        return file;
    }

    public static File createJPG(){
        File file = new File("/Users/ANDRE/Desktop/AutoSum/IMAGES/Presentation1.jpg");
        return file;
    }

    public static File createJPG_2(){
        File file = new File("/Users/ANDRE/Desktop/AutoSum/IMAGES/Diapositive2.jpg");
        return file;
    }

    public static File createJPG_3(){
        File file = new File("/Users/ANDRE/Desktop/AutoSum/IMAGES/Diapositive09.jpg");
        return file;
    }

    public static File createJPG_4(){
//        File file = new File("/Users/ANDRE/Desktop/AutoSum/IMAGES/Diapositive06.jpg");
        File file = new File("/Users/ANDRE/Desktop/AutoSum/IMAGES/Diapositive03.jpg");
        return file;
    }

    public static File createError(){
        File file = new File("NoFile");
        return file;
    }

}
