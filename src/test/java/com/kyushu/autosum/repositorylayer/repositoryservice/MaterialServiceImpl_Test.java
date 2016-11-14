package com.kyushu.autosum.repositorylayer.repositoryservice;

import com.kyushu.autosum.repositorylayer.display.DisplayData;
import com.kyushu.autosum.repositorylayer.domain.Material;
import com.kyushu.autosum.repositorylayer.repositories.MaterialRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

/**
 * Tester : MaterialServiceImpl
 *
 * @author ANDRE
 * @since 10/08/16
 */
@RunWith(MockitoJUnitRunner.class)
public class MaterialServiceImpl_Test {

    @InjectMocks
    MaterialServiceImpl materialServiceImpl = new MaterialServiceImpl();
    @Mock //Mockito Mock object
    private MaterialRepository materialRepository;

    @Test
    public void findByUser_Id() throws Exception {

        // BUILD
        String ID = "1";
        List<Material> materialList = Arrays.asList(new Material(), new Material());

        // MOCK
        when(materialRepository.findByUserId(any(String.class))).thenReturn(materialList);

        // OPERATE
        List<Material> materialList2 = materialServiceImpl.findByUserId(ID);

        // CHECK
        DisplayData.output(materialList);
        assertEquals(false, materialList.isEmpty());

    }


}
