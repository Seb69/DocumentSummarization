package com.kyushu.autosum.servicelayer.selection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author ANDRE
 * @since 16/08/16
 */
public interface DownloadOriginalFile {

    File downloadOriginalFile(Integer ID);

}
