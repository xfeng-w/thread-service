package top.xfeng.thread.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xfeng.thread.service.LookupService;

/**
 * @author xuefeng.wang
 * @date 2020-07-02
 */
@Api(description = "test ")
@RestController
@RequiredArgsConstructor
@RequestMapping("test/v1")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final LookupService lookupService;

    @ApiOperation(value = "test")
    @GetMapping
    public ResponseEntity<String> threadTest() throws InterruptedException {
        lookupService.thread("1");
        lookupService.thread("2");
        lookupService.thread("3");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
