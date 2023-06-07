package org.cluo.framework.management.controller;

import org.cluo.framework.json.JSONUtil;
import org.cluo.framework.management.model.common.CmsContentConfig;
import org.cluo.framework.management.service.CmsSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author canfuu.cts
 * @class ContentController
 * @date 2023/6/3 23:51
 */
@RestController
@RequestMapping("/cms/content")
public class ContentController {

    @Autowired
    private CmsSystemService cmsSystemService;
    @GetMapping("/{topKey}/{leftKey}/{leftSubKey}")
    public void contentConfigs(@PathVariable("topKey") String topKey, @PathVariable("leftKey") String leftKey, @PathVariable("leftSubKey") String leftSubKey, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getOutputStream().write(JSONUtil.fromObjectAsBytes(cmsSystemService.contentConfigs(topKey, leftKey, leftSubKey)));
    }
}
