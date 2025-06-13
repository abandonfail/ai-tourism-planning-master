package com.aitourismplanningmaster.tools;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ResourceDownloadToolTest {

    private static final Logger log = LoggerFactory.getLogger(ResourceDownloadToolTest.class);

    @Test
    public void testDownloadResource() {
        ResourceDownloadTool tool = new ResourceDownloadTool();
        String url = "https://haowallpaper.com/link/common/file/previewFileImg/17000456929594752";
        String fileName = "logo.png";
        String result = tool.downloadResource(url, fileName);
        log.info("result:{}", result);
        assertNotNull(result);
    }
}
