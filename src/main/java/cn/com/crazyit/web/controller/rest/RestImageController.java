package cn.com.crazyit.web.controller.rest;

import cn.com.crazyit.core.constant.ResultCode;
import cn.com.crazyit.core.environment.ImageLocationEnvironment;
import cn.com.crazyit.core.result.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/21.
 */
@RestController
@RequestMapping(value = RestImageController.URL_MAPPING, produces = "application/json;charset=utf-8")
public class RestImageController {

    @Autowired
    private ImageLocationEnvironment imageLocationEnvironment;

    protected final static String URL_MAPPING = "/rest/images";

    @PostMapping
    public RestResult<?> restImagesPost(@RequestParam MultipartFile data, String type) {
        String name = data.getOriginalFilename();
        try {
            String url = imageLocationEnvironment.uploadImage(name,
                    Enum.valueOf(ImageLocationEnvironment.ImageType.class, type),
                    data.getInputStream());
            return new RestResult<>(ResultCode.SUCCESS, null, url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RestResult<>(ResultCode.SUCCESS, null, null);
    }
}
