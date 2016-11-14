package com.kyushu.autosum.servicelayer.convertorservice;

import com.kyushu.autosum.configurationlayer.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tester : PowerpointToPDF
 *
 * @author ANDRE
 * @since 28/05/16
 */
public class PowerpointToPDF_IT extends AbstractIntegrationTest {

    private PowerpointToPDF powerpointToPDF;

    @Autowired
    public void setPowerpointToPDF(PowerpointToPDF powerpointToPDF) {
        this.powerpointToPDF = powerpointToPDF;
    }



}
