package top.xfeng.thread.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xfeng.thread.service.LookupService;

import java.util.concurrent.Executor;

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

    @Autowired
    @Qualifier("taskExecutor")
    private Executor executor;


    @ApiOperation(value = "test")
    @GetMapping
    public ResponseEntity<String> threadTest() {
        //使用线程池开启三个线程
        executor.execute(() -> {
            lookupService.thread("1");
        });
        executor.execute(() -> {
            lookupService.thread("2");
        });
        executor.execute(() -> {
            lookupService.thread("3");
        });
        //不使用线程池开启一个新的线程
        Thread thread = new Thread(() -> {
            lookupService.thread("4");
        });
        thread.start();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
